import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { CdkDragDrop, DragDropModule, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { BoardService, BoardColumn } from '../../services/board';
import { TicketService, Ticket } from '../../services/ticket';

@Component({
  selector: 'app-board',
  imports: [
    CommonModule, FormsModule, RouterModule, MatCardModule, MatButtonModule,
    MatIconModule, MatFormFieldModule, MatInputModule, MatSelectModule, DragDropModule
  ],
  templateUrl: './board.html',
  styleUrl: './board.css'
})
export class Board implements OnInit {
  idProject!: number;
  columns: BoardColumn[] = [];
  ticketsPorColumna: { [key: number]: Ticket[] } = {};
  usuario: any;
  showColumnForm = false;
  showTicketForm: { [key: number]: boolean } = {};
  nuevaColumna: BoardColumn = { nombre: '', posicion: 0 };
  nuevoTicket: Ticket = { titulo: '', descripcion: '', estado: 'TODO', prioridad: 'MEDIA' };

  constructor(
    private route: ActivatedRoute,
    private boardService: BoardService,
    private ticketService: TicketService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.usuario = JSON.parse(localStorage.getItem('usuario') || '{}');
    this.idProject = Number(this.route.snapshot.paramMap.get('id'));
    this.cargarBoard();
  }

  cargarBoard() {
    this.boardService.getByProject(this.idProject).subscribe({
      next: (boards) => {
        if (boards && boards.length > 0) {
          this.cargarColumnas(boards[0].idBoard!);
        }
      },
      error: (err) => console.error('Error cargando board:', err)
    });
  }

  cargarColumnas(idBoard: number) {
    this.boardService.getColumns(idBoard).subscribe({
      next: (cols) => {
        this.columns = [...cols];
        cols.forEach(col => {
          this.ticketsPorColumna[col.idColumn!] = [];
          this.cargarTickets(col.idColumn!);
        });
        this.cdr.detectChanges();
      },
      error: (err) => console.error('Error cargando columnas:', err)
    });
  }

  cargarTickets(idColumn: number) {
    this.ticketService.getByColumn(idColumn).subscribe({
      next: (tickets) => {
        this.ticketsPorColumna[idColumn] = tickets;
        this.cdr.detectChanges();
      }
    });
  }

  getConnectedLists(): string[] {
    return this.columns.map(col => 'col-' + col.idColumn);
  }

  onDrop(event: CdkDragDrop<Ticket[]>, idColumnaDestino: number) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
      const ticket = event.container.data[event.currentIndex];
      ticket.column = { idColumn: idColumnaDestino };
      this.ticketService.update(ticket.idTicket!, ticket).subscribe({
        next: () => this.cdr.detectChanges(),
        error: (err) => console.error('Error moviendo ticket:', err)
      });
    }
  }

  crearColumna() {
    this.nuevaColumna.posicion = this.columns.length;
    this.boardService.getByProject(this.idProject).subscribe({
      next: (boards) => {
        this.nuevaColumna.board = { idBoard: boards[0].idBoard };
        this.boardService.createColumn(this.nuevaColumna).subscribe({
          next: () => {
            this.showColumnForm = false;
            this.nuevaColumna = { nombre: '', posicion: 0 };
            this.cargarBoard();
          }
        });
      }
    });
  }

  crearTicket(idColumn: number) {
    this.nuevoTicket.column = { idColumn };
    this.nuevoTicket.creadoPor = { idUser: this.usuario.idUser };
    this.ticketService.create(this.nuevoTicket).subscribe({
      next: () => {
        this.showTicketForm[idColumn] = false;
        this.nuevoTicket = { titulo: '', descripcion: '', estado: 'TODO', prioridad: 'MEDIA' };
        this.cargarTickets(idColumn);
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
}
