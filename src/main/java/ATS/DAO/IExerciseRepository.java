package ATS.DAO;

import ATS.DTO.Exercise;

import java.util.List;

public interface IExerciseRepository {
    public List<Exercise> getAllExercises();
    public Exercise getExercise(Long idExercise);
    public void deleteExercise(Long idExercise);
    public void create(Exercise exercise);
}
