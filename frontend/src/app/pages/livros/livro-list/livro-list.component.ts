import { Component, OnInit } from '@angular/core';
import { LivroService } from '../../../services/livro.service';
import { Livro } from '../../../models/livro';
import { CategoriaService } from '../../../services/categoria.service';
import { Categoria } from '../../../models/categoria';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


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
    categoriaId: '',
    status: '',
    busca: ''
  };

  constructor(private livroService: LivroService,  private categoriaService: CategoriaService) {}

  ngOnInit(): void {
    this.carregar();
    this.carregarCategorias();
  }

  carregar() {
    this.livroService.listar(this.filtros).subscribe((data: any) => {
      this.livros = data;
    });
  }

carregarCategorias() {
   this.categoriaService.listar().subscribe((data: Categoria[]) => {
      this.categorias = data;
    });
  }

  aplicarFiltro() {
    this.carregar();
  }

  limpar() {
    this.filtros = { categoriaId: '', status: '', busca: '' };
    this.carregar();

  }

}
