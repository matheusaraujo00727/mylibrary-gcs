package com.example.backend.Service;

import com.example.backend.Entity.Emprestimo;
import com.example.backend.Entity.Livro;
import com.example.backend.Entity.StatusLivro;
import com.example.backend.Exception.RegraNegocioException;
import com.example.backend.Repository.EmprestimoRepository;
import com.example.backend.Repository.LivroRepository;
import com.example.backend.dto.EmprestimoRequest;
import com.example.backend.dto.EmprestimoResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroRepository livroRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository,
                             LivroRepository livroRepository) {

        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
    }

    public List<EmprestimoResponse> listarTodos() {

        return emprestimoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public List<EmprestimoResponse> listarAtivos() {

        return emprestimoRepository.findByDataDevolucaoEfetivaIsNull()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public List<EmprestimoResponse> listarAtrasados() {

        return emprestimoRepository.buscarAtrasados()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public EmprestimoResponse emprestar(EmprestimoRequest dto) {

        Livro livro = livroRepository.findById(dto.livroId())
                .orElseThrow(() ->
                        new RegraNegocioException("Livro não encontrado")
                );

        if (livro.getStatus() == StatusLivro.EMPRESTADO) {
            throw new RegraNegocioException(
                    "Livro já está emprestado"
            );
        }

        Emprestimo emprestimo = new Emprestimo();

        emprestimo.setLivro(livro);
        emprestimo.setNomePessoa(dto.nomePessoa());
        emprestimo.setTelefone(dto.telefone());
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucaoPrevista(dto.dataDevolucaoPrevista());

        livro.setStatus(StatusLivro.EMPRESTADO);

        Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);

        livroRepository.save(livro);

        return converterParaDTO(emprestimoSalvo);
    }

    public EmprestimoResponse devolver(Long id) {

        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() ->
                        new RegraNegocioException("Empréstimo não encontrado")
                );

        if (emprestimo.getDataDevolucaoEfetiva() != null) {
            throw new RegraNegocioException(
                    "Esse empréstimo já foi finalizado"
            );
        }

        emprestimo.setDataDevolucaoEfetiva(LocalDate.now());

        Livro livro = emprestimo.getLivro();

        livro.setStatus(StatusLivro.DISPONIVEL);

        emprestimoRepository.save(emprestimo);

        livroRepository.save(livro);

        return converterParaDTO(emprestimo);
    }

    private EmprestimoResponse converterParaDTO(Emprestimo emprestimo) {

        boolean atrasado =
                emprestimo.getDataDevolucaoEfetiva() == null
                        && emprestimo.getDataDevolucaoPrevista()
                        .isBefore(LocalDate.now());

        return new EmprestimoResponse(
                emprestimo.getId(),
                emprestimo.getLivro().getTitulo(),
                emprestimo.getNomePessoa(),
                emprestimo.getTelefone(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucaoPrevista(),
                emprestimo.getDataDevolucaoEfetiva(),
                atrasado
        );
    }
}
