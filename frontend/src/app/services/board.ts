import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Board {
  idBoard?: number;
  nombre: string;
  tipo: string;
  project?: any;
}

export interface BoardColumn {
  idColumn?: number;
  nombre: string;
  posicion: number;
  board?: any;
}

@Injectable({
  providedIn: 'root'
})
export class BoardService {
  private boardUrl = 'http://localhost:8080/api/boards';
  private columnUrl = 'http://localhost:8080/api/columns';

  constructor(private http: HttpClient) {}

  getByProject(idProject: number): Observable<Board[]> {
    return this.http.get<Board[]>(`${this.boardUrl}/project/${idProject}`);
  }

  getById(id: number): Observable<Board> {
    return this.http.get<Board>(`${this.boardUrl}/${id}`);
  }

  createBoard(board: Board): Observable<Board> {
    return this.http.post<Board>(this.boardUrl, board);
  }

  getColumns(idBoard: number): Observable<BoardColumn[]> {
    return this.http.get<BoardColumn[]>(`${this.columnUrl}/board/${idBoard}`);
  }

  createColumn(column: BoardColumn): Observable<BoardColumn> {
    return this.http.post<BoardColumn>(this.columnUrl, column);
  }
}
