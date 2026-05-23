package com.example.backend.Service;

import com.example.backend.Entity.Categoria;
import com.example.backend.Entity.Livro;
import com.example.backend.Entity.StatusLivro;
import com.example.backend.Exception.RegraNegocioException;
import com.example.backend.Repository.CategoriaRepository;
import com.example.backend.Repository.LivroRepository;
import com.example.backend.dto.LivroRequest;
import com.example.backend.dto.LivroResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final CategoriaRepository categoriaRepository;

    public LivroService(LivroRepository livroRepository,
                        CategoriaRepository categoriaRepository) {

        this.livroRepository = livroRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<LivroResponse> listarTodos() {

        return livroRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public LivroResponse buscarPorId(Long id) {

        Livro livro = livroRepository.findById(id)
                .orElseThrow(() ->
                        new RegraNegocioException("Livro não encontrado")
                );

        return converterParaDTO(livro);
    }

    public LivroResponse criar(LivroRequest dto) {

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() ->
                        new RegraNegocioException("Categoria não encontrada")
                );

        Livro livro = new Livro();

        livro.setTitulo(dto.titulo());
        livro.setAutor(dto.autor());
        livro.setIsbn(dto.isbn());
        livro.setAno(dto.ano());
        livro.setCategoria(categoria);
        livro.setStatus(StatusLivro.DISPONIVEL);

        Livro livroSalvo = livroRepository.save(livro);

        return converterParaDTO(livroSalvo);
    }

    public void excluir(Long id) {

        Livro livro = livroRepository.findById(id)
                .orElseThrow(() ->
                        new RegraNegocioException("Livro não encontrado")
                );

        if (livro.getStatus() == StatusLivro.EMPRESTADO) {
            throw new RegraNegocioException(
                    "Livro emprestado não pode ser excluído"
            );
        }

        livroRepository.delete(livro);
    }

    public List<LivroResponse> filtrar(
            Long categoriaId,
            StatusLivro status,
            String busca
    ) {

        List<Livro> livros = livroRepository.findAll();

        if (categoriaId != null) {
            livros = livros.stream()
                    .filter(l ->
                            l.getCategoria().getId().equals(categoriaId)
                    )
                    .toList();
        }

        if (status != null) {
            livros = livros.stream()
                    .filter(l ->
                            l.getStatus().equals(status)
                    )
                    .toList();
        }

        if (busca != null && !busca.isBlank()) {

            String buscaLower = busca.toLowerCase();

            livros = livros.stream()
                    .filter(l ->
                            l.getTitulo().toLowerCase().contains(buscaLower)
                                    || l.getAutor().toLowerCase().contains(buscaLower)
                    )
                    .toList();
        }

        return livros.stream()
                .map(this::converterParaDTO)
                .toList();
    }

    private LivroResponse converterParaDTO(Livro livro) {

        return new LivroResponse(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getIsbn(),
                livro.getAno(),
                livro.getStatus().name(),
                livro.getCategoria().getNome()
        );
    }
}
