package ATS.Services;

import ATS.DTO.Exercise;

public interface IExerciseService {
    void addItem(Exercise exercise);
    boolean checkUniqueExercise(Exercise exercise);
    Exercise getExercise(Long idExercise);
}
