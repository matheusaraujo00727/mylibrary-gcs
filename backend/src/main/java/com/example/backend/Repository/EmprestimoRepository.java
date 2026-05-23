package com.example.backend.Repository;

import com.example.backend.Entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    List<Emprestimo> findByDataDevolucaoEfetivaIsNull();

    @Query("""
            SELECT e
            FROM Emprestimo e
            WHERE e.dataDevolucaoPrevista < CURRENT_DATE
            AND e.dataDevolucaoEfetiva IS NULL
            """)
    List<Emprestimo> buscarAtrasados();

    List<Emprestimo> findTop5ByOrderByDataEmprestimoDesc();

    long countByDataDevolucaoEfetivaIsNull();
}
