import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule, MatDialog } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ProjectService, Project } from '../../services/project';

@Component({
  selector: 'app-dashboard',
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
  ],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard implements OnInit {
  projects: Project[] = [];
  usuario: any;
  showForm = false;
  nuevoProyecto: Project = { nombre: '', descripcion: '' };

  constructor(private projectService: ProjectService, private router: Router) {}

  ngOnInit() {
    this.usuario = JSON.parse(localStorage.getItem('usuario') || '{}');
    this.cargarProyectos();
  }

  cargarProyectos() {
    this.projectService.getByUser(this.usuario.idUser).subscribe({
      next: (data) => this.projects = data,
      error: (err) => console.error(err)
    });
  }

  crearProyecto() {
    this.nuevoProyecto.owner = { idUser: this.usuario.idUser };
    this.projectService.create(this.nuevoProyecto).subscribe({
      next: () => {
        this.showForm = false;
        this.nuevoProyecto = { nombre: '', descripcion: '' };
        this.cargarProyectos();
      }
    });
  }

  abrirBoard(project: Project) {
    this.router.navigate(['/board', project.idProject]);
  }

  cerrarSesion() {
    localStorage.removeItem('usuario');
    this.router.navigate(['/login']);
  }
}
