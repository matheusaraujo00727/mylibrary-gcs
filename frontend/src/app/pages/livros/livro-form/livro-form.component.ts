import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { LivroService } from '../../../services/livro.service';
import { Router } from '@angular/router';
import { CategoriaService } from '../../../services/categoria.service';

@Component({
  selector: 'app-livro-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './livro-form.component.html'
})
export class LivroFormComponent implements OnInit {

  form: FormGroup;
  categorias: any[] = [];

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
      categoriaId: ['']
    });
  }

  ngOnInit(): void {
    this.carregarCategorias();
  }

  carregarCategorias() {
    this.categoriaService.listar().subscribe(data => {
      this.categorias = data;
    });
  }

  salvar() {
    this.livroService.salvar(this.form.value).subscribe(() => {
      this.router.navigate(['/livros']);
    });
  }
}
