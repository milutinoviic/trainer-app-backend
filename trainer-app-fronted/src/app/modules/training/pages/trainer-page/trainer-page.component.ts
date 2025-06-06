import { Component } from '@angular/core';
import { TrainingDto } from '../../types/TrainingDto';
import { TrainingService } from '../../services/training.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-trainer-page',
  standalone: false,
  templateUrl: './trainer-page.component.html',
  styleUrl: './trainer-page.component.css'
})
export class TrainerPageComponent {


  trainingsTodayFromNow: TrainingDto[] = [];
  trainingsThisWeek: TrainingDto[] = [];
  trainerId: number = 0;
  showPopup = false;
  popupMessage = '';
  errorCancelMessage: string = '';
  showErrorCancelModal: boolean = false;


  
  isCancelModalOpen: boolean = false;

  cancelForm = {
  emailUser: '',
  trainingId: 0,
  accessCode: ''
};

  constructor(private trainingService: TrainingService,private router: Router) { }

  ngOnInit(): void {

  const trainerIdStr = localStorage.getItem('trainerId');
  this.trainerId = trainerIdStr ? Number(trainerIdStr) : 0;
  if(this.trainerId == 0){

      this.router.navigate(['/access-denied']);


  }

    if (this.trainerId > 0) {
    this.loadTrainingsTodayFromNow();
    this.loadTrainingsThisWeek();
  } else {
    console.warn('Trainer ID not found or invalid, skipping training loads.');
  }
  }

  loadTrainingsTodayFromNow(): void {
    this.trainingService.getTrainingsForTrainerForTodayFromNow(this.trainerId).subscribe({
      next: (data) => this.trainingsTodayFromNow = data,
      error: (err) => console.error('Error loading trainings today from now', err)
    });
  }

  

  loadTrainingsThisWeek(): void {
    this.trainingService.getTrainingsForTrainerForWeek(this.trainerId).subscribe({
      next: (data) => this.trainingsThisWeek = data,
      error: (err) => console.error('Error loading trainings this week', err)
    });
  }



reserveTraining(trainingId: number) {
  console.log('Reserve training', trainingId);
  this.selectedTrainingId = trainingId;
  this.isAddExerciseModalOpen = true;
}

selectedTrainingId: number | null = null;

openCancelModal(training: TrainingDto) {

  if (training.status === 'ACTIVE') {
  this.popupMessage = 'Training is not reserved';
  this.showPopup = true;
  return
} 

  this.selectedTrainingId = training.id;
  this.cancelForm = {
    emailUser: '',
    trainingId: training.id,
    accessCode: ''
  };
  this.isCancelModalOpen = true;
}

closeCancelModal() {
  this.isCancelModalOpen = false;
}

submitCancelForm() {
  this.trainingService.cancelReservationAsTrainer(this.cancelForm).subscribe({
    next: (response) => {
      console.log('Reservation cancelled/reserved successfully', response);
      this.closeCancelModal();
      this.loadTrainingsThisWeek();
      this.loadTrainingsTodayFromNow();
    },
    error: (err) => {
      if (err.status === 400) {
        // Prikaži modal sa porukom
        this.errorCancelMessage = 'You are not the trainer of this training.';
        this.showErrorCancelModal = true;
        
      } else {
        console.error('Error on reservation cancel', err);
      }
    }
  });
}


isAddExerciseModalOpen: boolean = false;

closeAddExerciseModalOpen() {
  this.isAddExerciseModalOpen = false;
}

onReservationCreated() {
 
  this.closeAddExerciseModalOpen();
}

goToTrainer(): void {
  console.log('Kliknuto na Create Training dugme');
  this.router.navigate(['/trainers']);
}



}
