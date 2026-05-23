package com.example.backend.dto;

import java.time.LocalDate;

public record EmprestimoResponse(

        Long id,

        String livro,

        String nomePessoa,

        String telefone,

        LocalDate dataEmprestimo,

        LocalDate dataDevolucaoPrevista,

        LocalDate dataDevolucaoEfetiva,

        Boolean atrasado

) {
}
