import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Categoria } from '../models/categoria';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  private api = '/api/categorias';

  constructor(private http: HttpClient) {}

  listar() {
    return this.http.get<Categoria[]>(this.api);
  }
}
