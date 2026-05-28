import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Comment {
  idComment?: number;
  texto: string;
  fechaComentario?: string;
  usuario?: any;
  ticket?: any;
}

export interface TicketHistorial {
  idHistory?: number;
  campoModificado: string;
  valorAnterior: string;
  valorNuevo: string;
  fechaCambio?: string;
  usuario?: any;
}

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private commentUrl = 'http://localhost:8080/api/comments';

  constructor(private http: HttpClient) {}

  getByTicket(idTicket: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(`${this.commentUrl}/ticket/${idTicket}`);
  }

  create(comment: Comment): Observable<Comment> {
    return this.http.post<Comment>(this.commentUrl, comment);
  }
}
