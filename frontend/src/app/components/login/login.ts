import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-login',
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
  email = '';
  password = '';
  errorMsg = '';

  constructor(private router: Router, private http: HttpClient) {}

  login() {
    this.http.get<any[]>('http://localhost:8080/api/usuarios').subscribe({
      next: (usuarios) => {
        const usuario = usuarios.find(u => u.email === this.email && u.password === this.password);
        if (usuario) {
          localStorage.setItem('usuario', JSON.stringify(usuario));
          this.router.navigate(['/dashboard']);
        } else {
          this.errorMsg = 'Email o contraseña incorrectos';
        }
      },
      error: () => {
        this.errorMsg = 'Error conectando con el servidor';
      }
    });
  }
}
