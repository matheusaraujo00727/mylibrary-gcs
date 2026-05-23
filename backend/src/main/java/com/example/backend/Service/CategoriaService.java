package com.example.backend.Service;

import com.example.backend.Entity.Categoria;
import com.example.backend.Exception.RegraNegocioException;
import com.example.backend.Repository.CategoriaRepository;
import com.example.backend.dto.CategoriaRequest;
import com.example.backend.dto.CategoriaResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaResponse> listarTodas() {

        return categoriaRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public CategoriaResponse criar(CategoriaRequest dto) {

        if (categoriaRepository.existsByNome(dto.nome())) {
            throw new RegraNegocioException(
                    "Já existe uma categoria com esse nome"
            );
        }

        Categoria categoria = new Categoria();

        categoria.setNome(dto.nome());
        categoria.setDescricao(dto.descricao());

        Categoria categoriaSalva = categoriaRepository.save(categoria);

        return converterParaDTO(categoriaSalva);
    }

    public void excluir(Long id) {

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new RegraNegocioException("Categoria não encontrada")
                );

        if (!categoria.getLivros().isEmpty()) {
            throw new RegraNegocioException(
                    "Não é possível excluir categoria com livros vinculados"
            );
        }

        categoriaRepository.delete(categoria);
    }

    private CategoriaResponse converterParaDTO(Categoria categoria) {

        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao(),
                categoria.getLivros().size()
        );
    }

}
