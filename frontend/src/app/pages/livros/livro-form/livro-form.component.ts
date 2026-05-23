import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

import { LivroService } from '../../../services/livro.service';
import { CategoriaService } from '../../../services/categoria.service';
import { Categoria } from '../../../models/categoria';

import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-livro-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './livro-form.component.html'
})
export class LivroFormComponent implements OnInit {

  form: FormGroup;
  categorias: Categoria[] = [];

  constructor(
    private fb: FormBuilder,
    private livroService: LivroService,
    private categoriaService: CategoriaService,
    private router: Router
  ) {
    this.form = this.fb.group({
      titulo: [''],
      autor: [''],
      isbn: [''],
      ano: [''],
      categoriaId: [''],
      status: ['DISPONIVEL']
    });
  }

  ngOnInit(): void {
    this.carregarCategorias();
  }

  carregarCategorias(): void {
    this.categoriaService.listar().subscribe((data: Categoria[]) => {
      this.categorias = data;
    });
  }

  salvar(): void {
    this.livroService.salvar(this.form.value).subscribe(() => {
      this.router.navigate(['/livros']);
    });
  }
}
