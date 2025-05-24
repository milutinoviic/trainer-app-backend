import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { CreateTrainingDto } from '../../types/createTraining';
import { TrainingService } from '../../services/training.service';
import { TrainerVerifyDto } from '../../types/cancleReservationAsTrainerDto';
import { TrainerService } from '../../services/trainer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-trainer',
  standalone: false,
  templateUrl: './trainer.component.html',
  styleUrl: './trainer.component.css'
})
export class TrainerComponent {
  trainingForm!: FormGroup;
  showForm = false;
   storedTrainerId!: number;  
  

  constructor(private fb: FormBuilder, private trainingService: TrainingService,private router:Router) {}

  ngOnInit(): void {

     
    var storedTrainerId = localStorage.getItem('trainerId');
    if (!storedTrainerId) {
      
      this.router.navigate(['/access-denied']);
      return;
    }
    this.storedTrainerId = +storedTrainerId;



    this.trainingForm = this.fb.group({
      startTime: [
        '',
        [
          Validators.required,
          this.noPastDateTime(),
          this.onlyHalfOrFullHour()
        ]
      ],
      duration: ['', Validators.required]
    });
  }

  noPastDateTime(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      if (!control.value) return null;
      const selected = new Date(control.value);
      const now = new Date();
      return selected < now ? { pastDate: true } : null;
    };
  }

  
  onlyHalfOrFullHour(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      if (!control.value) return null;
      const date = new Date(control.value);
      const minutes = date.getMinutes();
      return minutes !== 0 && minutes !== 30 ? { invalidMinutes: true } : null;
    };
  }

  onSubmit(): void {
    console.log(this.storedTrainerId)
    if (this.trainingForm.valid) {
      const formValues = this.trainingForm.value;

      const trainingDto: CreateTrainingDto = {
        trainerId: this.storedTrainerId,
        startTime: formValues.startTime,
        duration: formValues.duration
      };

      this.trainingService.createTraining(trainingDto).subscribe({
        next: (response) => {
          console.log('Training kreiran:', response);
          alert('The training has been successfully created!');
          this.trainingForm.reset();
        },
        error: (error) => {
          console.error('Greška prilikom kreiranja treninga:', error);
          alert('An error occurred while creating the training.');
        }
      });
    }
  }
}
