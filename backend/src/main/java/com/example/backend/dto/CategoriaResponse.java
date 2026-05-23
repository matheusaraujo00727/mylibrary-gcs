package com.example.backend.dto;

public record CategoriaResponse(

        Long id,
        String nome,
        String descricao,
        Integer quantidadeLivros
) {
}
