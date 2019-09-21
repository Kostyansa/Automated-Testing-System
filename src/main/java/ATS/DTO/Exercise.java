package ATS.DTO;

public class Exercise {
    private String text;
    private Long idExercise;

    public Exercise(String text, Long idExercise) {
        this.text = text;
        this.idExercise = idExercise;
    }

    public String getText() {
        return text;
    }

    public Long getIdExercise() {
        return idExercise;
    }
}
