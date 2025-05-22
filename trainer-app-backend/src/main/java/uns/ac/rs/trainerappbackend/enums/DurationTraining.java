package uns.ac.rs.trainerappbackend.enums;

public enum DurationTraining {

    HALF_HOUR(30),
    ONE_HOUR(60);

    private final int minutes;

    DurationTraining(int minutes) {
        this.minutes = minutes;
    }

    public int getMinutes() {
        return minutes;
    }
}
