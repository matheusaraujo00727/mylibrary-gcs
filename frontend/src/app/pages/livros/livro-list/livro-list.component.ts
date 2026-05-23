import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { LivroService } from '../../../services/livro.service';
import { CategoriaService } from '../../../services/categoria.service';

import { Livro } from '../../../models/livro';
import { Categoria } from '../../../models/categoria';

import { Router } from '@angular/router';

@Component({
  selector: 'app-livro-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './livro-list.component.html'
})

export class LivroListComponent implements OnInit {

  livros: Livro[] = [];
  categorias: Categoria[] = [];

  filtros = {
    categoriaId: null,
    status: '',
    busca: ''
  };

  constructor(
    private livroService: LivroService,
    private categoriaService: CategoriaService,
    private router: Router
  ) {}

  novoLivro() {
    this.router.navigate(['/livros/novo']);
  }

  ngOnInit(): void {
    this.carregar();
    this.carregarCategorias();
  }

  carregar() {
    this.livroService.listar(this.filtros).subscribe((data: Livro[]) => {
      this.livros = data;
    });
  }

  carregarCategorias() {
    this.categoriaService.listar().subscribe((data: Categoria[]) => {
      this.categorias = data;
    });
  }

  getCategoriaNome(id: number): string {
    return this.categorias.find(c => c.id === id)?.nome ?? 'Sem categoria';
  }

  deletar(livro: any) {
    if (livro.status === 'EMPRESTADO') {
      alert('Não pode excluir livro emprestado');
      return;
    }

    this.livroService.deletar(livro.id).subscribe(() => {
      this.carregar();
    });
  }

  aplicarFiltro() {
    this.carregar();
  }

  limpar() {
    this.filtros = { categoriaId: null, status: '', busca: '' };
    this.carregar();
  }
}
