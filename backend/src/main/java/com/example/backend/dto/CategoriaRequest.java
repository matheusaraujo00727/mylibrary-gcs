package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaRequest(

        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 2, max = 100)
        String nome,

        @Size(max = 255)
        String descricao

) {
}
