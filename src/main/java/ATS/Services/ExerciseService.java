package ATS.Services;

import ATS.DAO.IExerciseRepository;
import ATS.DTO.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService implements IExerciseService {
    @Autowired
    private IExerciseRepository exerciseRepository;


    @Override
    public void addItem(Exercise exercise) {
        exerciseRepository.create(exercise);
    }

    @Override
    public boolean checkUniqueExercise(Exercise exercise){
        return exerciseRepository.getExercise(exercise.getIdExercise()) == null;
    }

    @Override
    public Exercise getExercise(Long idExercise){
        return exerciseRepository.getExercise(idExercise);
    }

}
