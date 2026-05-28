import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatChipsModule } from '@angular/material/chips';
import { MatDividerModule } from '@angular/material/divider';
import { TicketService, Ticket } from '../../services/ticket';
import { CommentService, Comment } from '../../services/auth';

@Component({
  selector: 'app-ticket-detail',
  imports: [
    CommonModule, FormsModule, RouterModule, MatCardModule, MatButtonModule,
    MatIconModule, MatFormFieldModule, MatInputModule, MatSelectModule,
    MatChipsModule, MatDividerModule
  ],
  templateUrl: './ticket-detail.html',
  styleUrl: './ticket-detail.css'
})
export class TicketDetail implements OnInit {
  ticket: Ticket | null = null;
  comments: Comment[] = [];
  usuario: any;
  nuevoComentario = '';
  editando = false;
  idTicket!: number;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private ticketService: TicketService,
    private commentService: CommentService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.usuario = JSON.parse(localStorage.getItem('usuario') || '{}');
    this.idTicket = Number(this.route.snapshot.paramMap.get('id'));
    this.cargarTicket();
    this.cargarComentarios();
  }

  cargarTicket() {
    this.ticketService.getById(this.idTicket).subscribe({
      next: (ticket) => {
        this.ticket = ticket;
        this.cdr.detectChanges();
      }
    });
  }

  cargarComentarios() {
    this.commentService.getByTicket(this.idTicket).subscribe({
      next: (comments) => {
        this.comments = comments;
        this.cdr.detectChanges();
      }
    });
  }

  guardarCambios() {
    if (!this.ticket) return;
    this.ticketService.update(this.idTicket, this.ticket).subscribe({
      next: () => {
        this.editando = false;
        this.cargarTicket();
      }
    });
  }

  agregarComentario() {
    if (!this.nuevoComentario.trim()) return;
    const comment: Comment = {
      texto: this.nuevoComentario,
      ticket: { idTicket: this.idTicket },
      usuario: { idUser: this.usuario.idUser }
    };
    this.commentService.create(comment).subscribe({
      next: () => {
        this.nuevoComentario = '';
        this.cargarComentarios();
      }
    });
  }

  getPrioridadColor(prioridad: string): string {
    switch(prioridad) {
      case 'ALTA': return '#e53935';
      case 'MEDIA': return '#fb8c00';
      case 'BAJA': return '#43a047';
      default: return '#999';
    }
  }

  volver() {
    this.router.navigate(['/board', this.ticket?.column?.board?.project?.idProject || '']);
  }
}
