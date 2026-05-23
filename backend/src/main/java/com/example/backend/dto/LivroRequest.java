package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LivroRequest(

        @NotBlank(message = "Título é obrigatório")
        @Size(min = 2, max = 150)
        String titulo,

        @NotBlank(message = "Autor é obrigatório")
        @Size(min = 2, max = 100)
        String autor,

        @NotBlank(message = "ISBN é obrigatório")
        String isbn,

        @NotNull(message = "Ano é obrigatório")
        Integer ano,

        @NotNull(message = "Categoria é obrigatória")
        Long categoriaId

) {
}
