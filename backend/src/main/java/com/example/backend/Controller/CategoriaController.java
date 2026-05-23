package com.example.backend.Controller;

import com.example.backend.Service.CategoriaService;
import com.example.backend.dto.CategoriaRequest;
import com.example.backend.dto.CategoriaResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin("*")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<CategoriaResponse> listarTodas() {
        return categoriaService.listarTodas();
    }

    @PostMapping
    public CategoriaResponse criar(
            @RequestBody @Valid CategoriaRequest dto
    ) {

        return categoriaService.criar(dto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        categoriaService.excluir(id);
    }
}
