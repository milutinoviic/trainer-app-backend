import { Component } from '@angular/core';
import { TrainingDto } from '../../types/TrainingDto';
import { TrainingService } from '../../services/training.service';
import { CreateReservationDto } from '../../types/createReservation';

@Component({
  selector: 'app-home',
  standalone: false,
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  
  trainings: TrainingDto[] = [];
  selectedTraining: TrainingDto | null = null;
  emailInput: string = '';
  emailError: string = '';
  successMessage: string = '';
  errorMessage: string = '';
  emailNotRegistered: boolean = false;


  constructor(private trainingService: TrainingService) {}

  ngOnInit(): void {
    localStorage.removeItem('userEmail');
    this.loadTrainings();
  }

  loadTrainings(): void {
    this.trainingService.getTrainings().subscribe({
      next: (data) => this.trainings = data,
      error: (err) => console.error('Greška pri učitavanju treninga:', err)
    });
  }

  onAction(training: TrainingDto): void {
    this.selectedTraining = training;
    this.emailInput = '';
    this.emailError = '';
    this.successMessage = '';
    this.errorMessage = '';
  }

confirmEmail(): void {
  if (!this.emailInput.includes('@')) {
    this.emailError = 'Enter a valid email address (must contain "@")';
    return;
  }

  if (!this.selectedTraining) {
    this.errorMessage = 'No training has been selected.';
    return;
  }

  const reservation: CreateReservationDto = {
    email: this.emailInput,
    trainingId: this.selectedTraining.id 
  };

  this.trainingService.reserveTraining(reservation).subscribe(
    success => {
      success
        ? this.successMessage = 'Reservation created successfully'
        : this.errorMessage = 'Reservation failed';
      this.resetForm();
    },
    err => {
      console.error('Greška pri rezervaciji:', err);
      if (err.status === 400 && err.error && err.error.includes('User not found with email')) {
        
        this.emailNotRegistered = true;
        this.errorMessage = ''; 
      } else {
        this.errorMessage = 'There was an error while sending the reservation.';
      }
    }
  );
}




  cancelEmailInput(): void {
    this.resetForm();
  }

  private resetForm(): void {
    this.selectedTraining = null;
    this.emailInput = '';
    this.emailError = '';
    this.successMessage = '';
    this.errorMessage = '';
  }
}
