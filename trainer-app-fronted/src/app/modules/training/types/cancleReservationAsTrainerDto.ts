export interface CancelReservationAsTrainerDto {
  emailUser: string;
  trainingId: number;
  accessCode: string;
}

export interface TrainerResponseVerifyDto {
  id: number;
}

export interface TrainerVerifyDto {
  accessCode: string;
}

export interface GetUserTrainingsDto {
  email: string;
}

export interface CancelReservationDto {
  email: string;
  trainingId: number;
}

