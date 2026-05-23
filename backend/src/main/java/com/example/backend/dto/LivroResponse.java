package com.example.backend.dto;

public record LivroResponse(

        Long id,
        String titulo,
        String autor,
        String isbn,
        Integer ano,
        String status,
        String categoria

) {
}
