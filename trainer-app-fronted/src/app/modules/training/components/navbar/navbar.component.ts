import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TrainerService } from '../../services/trainer.service';
import { TrainerResponseVerifyDto, TrainerVerifyDto } from '../../types/cancleReservationAsTrainerDto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: false,
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {


  isModalOpen = false;
  form!: FormGroup;
  loading = false;
   hasTrainerId = false;  

  constructor(private fb: FormBuilder, private trainerService: TrainerService, private router: Router) {}

  ngOnInit(): void {
    localStorage.removeItem('userEmail');
    this.form = this.fb.group({
      accessCode: ['', Validators.required]
    });
    this.checkTrainerId(); 
  }


  checkTrainerId(): void {
    const trainerId = localStorage.getItem('trainerId');
    this.hasTrainerId = !!trainerId && trainerId !== '-1';
  }

  openModal(): void {
    this.isModalOpen = true;
  }

  closeModal(): void {
    this.isModalOpen = false;
    this.form.reset();
    this.loading = false;
  }

verify(): void {
  if (this.form.invalid) return;

  this.loading = true;

  const dto: TrainerVerifyDto = {
    accessCode: this.form.value.accessCode
  };

  this.trainerService.verifyAccessCode(dto).subscribe({
    next: (res: TrainerResponseVerifyDto) => {
     
      localStorage.setItem('trainerId', res.id.toString());

      alert(`You have successfully verified yourself.`);
      this.closeModal();
      this.checkTrainerId();
      this.router.navigate(['/profile']);

    },
    error: (err) => {
      alert('Greška pri verifikaciji koda.');
      this.loading = false;
    }
  });
}

clearTrainerId() {
  if (localStorage.getItem('trainerId')) {
    localStorage.removeItem('trainerId');
    console.log('trainerId je obrisan iz localStorage');
    this.hasTrainerId = false;  
     this.router.navigate(['/home']);
  } else {
    console.log('Nema trainerId u localStorage');
  }
}


onProfileClick(): void {
  const trainerId = localStorage.getItem('trainerId');
  if (trainerId && trainerId !== '-1') {
    this.router.navigate(['/profile']);  
  } else {
    this.openModal();
  }
}

hoverButton(event: MouseEvent, color: string) {
  (event.target as HTMLElement).style.backgroundColor = color;
}









}
