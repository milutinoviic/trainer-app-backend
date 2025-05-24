import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CancelReservationAsTrainerDto, TrainerResponseVerifyDto, TrainerVerifyDto } from '../types/cancleReservationAsTrainerDto';

@Injectable({
  providedIn: 'root'
})
export class TrainerService {

  constructor(private http: HttpClient) { }


  verifyAccessCode(dto: TrainerVerifyDto): Observable<TrainerResponseVerifyDto> {
  return this.http.post<TrainerResponseVerifyDto>('http://localhost:8080/api/trainer/verify-access-code', dto);;
}



}



