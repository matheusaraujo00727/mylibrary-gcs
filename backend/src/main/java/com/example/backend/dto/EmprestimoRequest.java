package com.example.backend.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmprestimoRequest(

        @NotNull(message = "Livro é obrigatório")
        Long livroId,

        @NotBlank(message = "Nome da pessoa é obrigatório")
        String nomePessoa,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone,

        @NotNull(message = "Data prevista é obrigatória")
        @Future(message = "Data prevista deve ser futura")
        LocalDate dataDevolucaoPrevista

) {
}
