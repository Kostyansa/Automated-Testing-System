package ATS.Services;

import ATS.DAO.IProgramRepository;
import ATS.DTO.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService implements IProgramService {
    @Autowired
    private IProgramRepository programRepository;

    @Override
    public void addProgram(Program program){
        programRepository.create(program);
    }

    @Override
    public void deleteProgram(Long idProgram){
        programRepository.deleteProgram(idProgram);
    }



    @Override
    public Program getProgram(Long programId){
        return programRepository.getProgram(programId);
    }

    @Override
    public Long getIdProgram(Long idUser, Long idExercise){
        return programRepository.getIdProgram(idUser,idExercise);
    }

    @Override
    public void updateProgram(Program program) {
        programRepository.updateProgram(program);
    }

    public void createProgram(Program program){
        programRepository.create(program);
    }

    public void createConnection(Long idUser, Long idExercise){
        programRepository.createConnection(idUser,idExercise);
    }

    public List<Long> getIdPrograms(Long idExercise){
        return programRepository.getIdPrograms(idExercise);
    }

    public Long getIdUser(Long idProgram){
        return programRepository.getIdUserByIdProgram(idProgram);
    }

}
