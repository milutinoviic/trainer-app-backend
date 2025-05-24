import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TrainingRoutingModule } from './training-routing.module';
import { HomeComponent } from './components/home/home.component';
import { TrainerComponent } from './components/trainer/trainer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TrainerPageComponent } from './pages/trainer-page/trainer-page.component';
import { CreateReservationComponent } from './components/create-reservation/create-reservation.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AccessDeniedComponent } from './pages/access-denied/access-denied.component';
import { CertainReservationsComponent } from './pages/certain-reservations/certain-reservations.component';


@NgModule({
  declarations: [
    HomeComponent,
    TrainerComponent,
    TrainerPageComponent,
    CreateReservationComponent,
    NavbarComponent,
    AccessDeniedComponent,
    CertainReservationsComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule, 
    TrainingRoutingModule
  ],
  exports: [
    NavbarComponent  
  ]
})
export class TrainingModule { }
