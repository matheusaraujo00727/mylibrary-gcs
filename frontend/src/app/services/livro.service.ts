import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Livro } from '../models/livro';

@Injectable({
  providedIn: 'root'
})
export class LivroService {

  private api = '/api/livros';

  constructor(private http: HttpClient) {}

listar(filtros: any) {
  let params: any = {};

  if (filtros.categoriaId) {
    params.categoriaId = filtros.categoriaId;
  }

  if (filtros.status) {
    params.status = filtros.status;
  }

  if (filtros.busca) {
    params.busca = filtros.busca;
  }

  return this.http.get(this.api, { params });
}
}
