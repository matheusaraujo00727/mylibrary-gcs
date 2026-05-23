package com.example.backend.Repository;

import com.example.backend.Entity.Livro;
import com.example.backend.Entity.StatusLivro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByStatus(StatusLivro status);

    List<Livro> findByCategoriaId(Long categoriaId);

    List<Livro> findByCategoriaIdAndStatus(Long categoriaId, StatusLivro status);

    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    List<Livro> findByAutorContainingIgnoreCase(String autor);

    List<Livro> findByTituloContainingIgnoreCaseOrAutorContainingIgnoreCase(String titulo, String autor);

    long countByStatus(StatusLivro status);
}
