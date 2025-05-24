export interface CreateTrainingDto {
  trainerId: number;
  startTime: string; 
  duration: 'HALF_HOUR' | 'ONE_HOUR'; 
}
