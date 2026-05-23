import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Livro } from '../models/livro';

@Injectable({
  providedIn: 'root'
})
export class LivroService {

  private api = 'http://localhost:8080/livros';

  constructor(private http: HttpClient) {}

  listar(filtros?: any) {
    return this.http.get<Livro[]>('http://localhost:8080/livros', {
      params: filtros
    });
  }

  salvar(livro: any) {
    return this.http.post('http://localhost:8080/livros', livro);
  }

  deletar(id: number) {
    return this.http.delete(`http://localhost:8080/livros/${id}`);
  }

  buscarPorId(id: number) {
    return this.http.get(`http://localhost:8080/livros/${id}`);
  }

  atualizar(id: number, livro: any) {
    return this.http.put(`http://localhost:8080/livros/${id}`, livro);
  }
}
