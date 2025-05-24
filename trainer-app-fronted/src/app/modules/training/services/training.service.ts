import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CreateTrainingDto } from '../types/createTraining';
import { TrainingDto } from '../types/TrainingDto';
import { CreateReservationDto } from '../types/createReservation';
import { CancelReservationAsTrainerDto, CancelReservationDto, GetUserTrainingsDto } from '../types/cancleReservationAsTrainerDto';

@Injectable({
  providedIn: 'root'
})
export class TrainingService {

  private baseUrl = 'http://localhost:8080/api/trainings'; 

  constructor(private http: HttpClient) { }

 createTraining(training: CreateTrainingDto): Observable<TrainingDto> {
    return this.http.post<TrainingDto>(`${this.baseUrl}/create`, training);
  }


  getTrainings(): Observable<TrainingDto[]> {
    return this.http.get<TrainingDto[]>(`${this.baseUrl}/all`);
  }

  reserveTraining(reservation: CreateReservationDto): Observable<boolean> {
    return this.http.post<boolean>(`${this.baseUrl}/reserve`, reservation);
  }
  
  getTrainingsForTrainerForTodayFromNow(trainerId: number): Observable<TrainingDto[]> {
    const url = `${this.baseUrl}/${trainerId}/today-from-now`;
    return this.http.get<TrainingDto[]>(url);
  }

 
  getTrainingsForTrainerForWeek(trainerId: number): Observable<TrainingDto[]> {
    const url = `${this.baseUrl}/${trainerId}/week`;
    return this.http.get<TrainingDto[]>(url);
  }

  cancelReservationAsTrainer(dto: CancelReservationAsTrainerDto) {
  return this.http.put<boolean>('http://localhost:8080/api/trainings/cancel-training-by-trainer', dto);
}

 cancelReservationAsUser(dto: CancelReservationDto) {
  return this.http.put<boolean>('http://localhost:8080/api/trainings/cancel-training-by-user', dto);
}



getTrainingsByUserEmail(dto: GetUserTrainingsDto): Observable<TrainingDto[]> {
    return this.http.post<TrainingDto[]>(`${this.baseUrl}/user`, dto);
  }


}
