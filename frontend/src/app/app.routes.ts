import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { Dashboard } from './components/dashboard/dashboard';
import { Board } from './components/board/board';
import { TicketDetail } from './components/ticket-detail/ticket-detail';
import { Register } from './components/register/register';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  { path: 'dashboard', component: Dashboard },
  { path: 'board/:id', component: Board },
  { path: 'ticket/:id', component: TicketDetail },
];
