import { Component, ChangeDetectorRef } from '@angular/core';
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
  selector: 'app-register',
  imports: [
    CommonModule, FormsModule, RouterModule, MatCardModule,
    MatFormFieldModule, MatInputModule, MatButtonModule, MatIconModule
  ],
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class Register {
  nombre = '';
  email = '';
  password = '';
  confirmarPassword = '';
  errorMsg = '';
  successMsg = '';

  constructor(private router: Router, private http: HttpClient, private cdr: ChangeDetectorRef) {}

  registrar() {
    if (!this.nombre || !this.email || !this.password) {
      this.errorMsg = 'Completá todos los campos';
      this.cdr.detectChanges();
      return;
    }
    if (this.password !== this.confirmarPassword) {
      this.errorMsg = 'Las contraseñas no coinciden';
      this.cdr.detectChanges();
      return;
    }

    const usuario = {
      nombre: this.nombre,
      email: this.email,
      password: this.password,
      role: { idRole: 2 }
    };

    this.http.post('http://localhost:8080/api/usuarios', usuario).subscribe({
      next: () => {
        this.successMsg = '¡Cuenta creada! Redirigiendo...';
        this.errorMsg = '';
        this.cdr.detectChanges();
        setTimeout(() => this.router.navigate(['/login']), 1500);
      },
      error: () => {
        this.errorMsg = 'Error al crear la cuenta. El email puede estar en uso.';
        this.cdr.detectChanges();
      }
    });
  }
}
