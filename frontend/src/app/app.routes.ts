import { Routes } from '@angular/router';
import { LivroListComponent } from './pages/livros/livro-list/livro-list.component';
import { LivroFormComponent } from './pages/livros/livro-form/livro-form.component';

export const routes: Routes = [
  { path: '', redirectTo: 'livros', pathMatch: 'full' },
  { path: 'livros', component: LivroListComponent },
  { path: 'livros/novo', component: LivroFormComponent }
];
