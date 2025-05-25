import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges } from '@angular/core';
import { CreateReservationDto } from '../../types/createReservation';
import { TrainingService } from '../../services/training.service';

@Component({
  selector: 'app-create-reservation',
  standalone: false,
  templateUrl: './create-reservation.component.html',
  styleUrl: './create-reservation.component.css'
})
export class CreateReservationComponent implements OnChanges{


  @Input() trainingId: number | null = null;
  @Output() reservationCreated = new EventEmitter<void>();

  


  ngOnChanges(changes: SimpleChanges): void {
    if (changes['trainingId'] && this.trainingId != null) {
      this.reservation.trainingId = this.trainingId;
    }
  }


  reservation: CreateReservationDto = {
    email: '',
    trainingId: 0
  };

  constructor(private trainingService: TrainingService) {}

  submitReservation() {
    console.log('Šaljem podatke:', this.reservation);
    this.trainingService.reserveTraining(this.reservation).subscribe({
      next: (res) => {
        console.log('Uspešna rezervacija!', res);
        alert('Uspešno ste rezervisali!');
      },
      error: (err) => {
        alert("Email isn't verification");

      }
    });
  }

}
