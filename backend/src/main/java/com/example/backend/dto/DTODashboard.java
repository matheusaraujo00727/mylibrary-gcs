package com.example.backend.dto;

import java.util.List;

public record DTODashboard(

        Long totalLivros,

        Long livrosDisponiveis,

        Long livrosEmprestados,

        Long emprestimosAtivos,

        List<EmprestimoResponse> ultimosEmprestimos
) {
}
