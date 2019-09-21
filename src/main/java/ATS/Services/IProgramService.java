package ATS.Services;

import ATS.DTO.Program;

import java.util.List;

public interface IProgramService {
    Program getProgram(Long idProgram);

    void updateProgram(Program program);

    void createProgram(Program program);

    void createConnection(Long idUser, Long idExercise);

    Long getIdProgram(Long idUser, Long idExercise);

    void addProgram(Program program);

    void deleteProgram(Long idProgram);

    public List<Long> getIdPrograms(Long idExercise);
}
