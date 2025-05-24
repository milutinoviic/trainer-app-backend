import { Component } from '@angular/core';
import { TrainingDto } from '../../types/TrainingDto';
import { TrainingService } from '../../services/training.service';
import { GetUserTrainingsDto } from '../../types/cancleReservationAsTrainerDto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-certain-reservations',
  standalone: false,
  templateUrl: './certain-reservations.component.html',
  styleUrl: './certain-reservations.component.css'
})
export class CertainReservationsComponent {


  userEmail: string = '';
  trainings: TrainingDto[] = [];
  showModal: boolean = true;

  constructor(private trainingService: TrainingService,private router:Router) {}

  ngOnInit(): void {
    const savedEmail = localStorage.getItem('userEmail');
    if (savedEmail) {
      this.userEmail = savedEmail;
      this.loadTrainings(); 
    }
  }

  loadTrainings(): void {
    if (!this.userEmail.trim()) {
      alert('Unesite validan email!');
      return;
    }

   
    localStorage.setItem('userEmail', this.userEmail);

    const dto: GetUserTrainingsDto = { email: this.userEmail };

    this.trainingService.getTrainingsByUserEmail(dto).subscribe({
      next: (data) => {
        this.trainings = data;
        this.showModal = false;
      },
      error: (err) => {
        console.error(err);
        alert('Greška pri učitavanju treninga.');
      }
    });
  }

 onCancelClick(trainingId: number): void {
  const email = localStorage.getItem('userEmail');
  if (!email) {
    alert('Email korisnika nije pronađen.');
    return;
  }

  const dto = {
    email: email,
    trainingId: trainingId
  };

  this.trainingService.cancelReservationAsUser(dto).subscribe({
    next: (response) => {
      alert('Trening je uspešno otkazan.');
      this.loadTrainings(); 
    },
    error: (err) => {
      console.error(err);
      alert('Došlo je do greške pri otkazivanju treninga.');
    }
  });
}


  isCancelDisabled(trainingStartTime: string | undefined | null): boolean {
    if (!trainingStartTime) return true;

    const now = new Date();
    const trainingDate = new Date(trainingStartTime);

    if (isNaN(trainingDate.getTime())) return true;

    const diffMs = trainingDate.getTime() - now.getTime();
    const diffHours = diffMs / (1000 * 60 * 60);

    return diffHours < 24;
  }
closeModalAndGoHome() {
  this.showModal = false;  
  this.router.navigate(['/home']); 
}



}
