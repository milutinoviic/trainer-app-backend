import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { TrainerComponent } from './components/trainer/trainer.component';
import { TrainerPageComponent } from './pages/trainer-page/trainer-page.component';
import { CreateReservationComponent } from './components/create-reservation/create-reservation.component';
import { AccessDeniedComponent } from './pages/access-denied/access-denied.component';
import { CertainReservationsComponent } from './pages/certain-reservations/certain-reservations.component';

const routes: Routes = [
  { path: 'trainers', component: TrainerComponent },
  { path: 'profile', component: TrainerPageComponent },
  { path: 'createR', component: CreateReservationComponent },
  { path: 'access-denied', component: AccessDeniedComponent },
  { path: 'reservations', component: CertainReservationsComponent }
];



@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TrainingRoutingModule { }
