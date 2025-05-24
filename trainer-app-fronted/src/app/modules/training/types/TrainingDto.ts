import { TrainerTrainingDto } from "./TrainerTrainingDto";
import { UserTrainingDto } from "./UserTrainingDto";

export interface TrainingDto {
  id: number;
  startTime: string;  
  endTime: string;
  status: string;
  duration: string;
  createdAt: string;
  canceledAt?: string | null;
  user?: UserTrainingDto | null;
  trainer: TrainerTrainingDto;
}