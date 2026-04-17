package core;

public record StudySession(String subject, int minutes, String difficulty) {

    public StudySession {
        if (subject == null || subject.isBlank()) {
            throw new IllegalArgumentException("Subject cannot be blank.");
        }
        if (minutes <= 0) {
            throw new IllegalArgumentException("Minutes must be greater than 0.");
        }
        if (difficulty == null || difficulty.isBlank()) {
            throw new IllegalArgumentException("Difficulty cannot be blank.");
        }
    }

    public String toCsv() {
        return subject + "," + minutes + "," + difficulty;
    }

    public static StudySession fromCsv(String line) {
        String[] parts = line.split(",");
        return new StudySession(parts[0], Integer.parseInt(parts[1]), parts[2]);
    }

    @Override
    public String toString() {
        return subject + " - " + minutes + " min - " + difficulty;
    }
}