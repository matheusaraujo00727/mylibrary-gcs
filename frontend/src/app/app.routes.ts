import { Routes } from '@angular/router';
import { LivroListComponent } from './pages/livros/livro-list/livro-list.component';

export const routes: Routes = [
  { path: '', redirectTo: 'livros', pathMatch: 'full' },
  { path: 'livros', component: LivroListComponent }
];
