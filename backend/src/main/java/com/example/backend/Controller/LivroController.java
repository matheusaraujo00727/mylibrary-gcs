package com.example.backend.Controller;

import com.example.backend.Entity.StatusLivro;
import com.example.backend.Service.LivroService;
import com.example.backend.dto.LivroRequest;
import com.example.backend.dto.LivroResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
@CrossOrigin("*")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public List<LivroResponse> listarTodos(
            @RequestParam(required = false) Long categoriaId,
            @RequestParam(required = false) StatusLivro status,
            @RequestParam(required = false) String busca
    ) {

        return livroService.filtrar(
                categoriaId,
                status,
                busca
        );
    }

    @GetMapping("/{id}")
    public LivroResponse buscarPorId(
            @PathVariable Long id
    ) {

        return livroService.buscarPorId(id);
    }

    @PostMapping
    public LivroResponse criar(
            @RequestBody @Valid LivroRequest dto
    ) {

        return livroService.criar(dto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        livroService.excluir(id);
    }
}
