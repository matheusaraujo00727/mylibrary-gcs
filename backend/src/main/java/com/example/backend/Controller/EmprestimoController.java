package com.example.backend.Controller;

import com.example.backend.Service.EmprestimoService;
import com.example.backend.dto.EmprestimoRequest;
import com.example.backend.dto.EmprestimoResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
@CrossOrigin("*")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping
    public List<EmprestimoResponse> listarTodos() {
        return emprestimoService.listarTodos();
    }

    @GetMapping("/ativos")
    public List<EmprestimoResponse> listarAtivos() {
        return emprestimoService.listarAtivos();
    }

    @GetMapping("/atrasados")
    public List<EmprestimoResponse> listarAtrasados() {
        return emprestimoService.listarAtrasados();
    }

    @PostMapping("/emprestar")
    public EmprestimoResponse emprestar(
            @RequestBody @Valid EmprestimoRequest dto
    ) {

        return emprestimoService.emprestar(dto);
    }

    @PostMapping("/{id}/devolver")
    public EmprestimoResponse devolver(
            @PathVariable Long id
    ) {

        return emprestimoService.devolver(id);
    }
}
