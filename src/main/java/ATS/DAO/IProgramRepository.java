package ATS.DAO;

import ATS.DTO.Program;

import java.util.List;

public interface IProgramRepository {
    void create(Program program);
    void createConnection(Long idUser, Long idExercise);
    Program getProgram(Long idProgram);
    Long getIdProgram(Long idUser, Long idExercise);
    void updateProgram(Program program);
    void deleteProgram(Long idProgram);
    List<Long> getIdPrograms(Long idExercise);
    Long getIdUserByIdProgram(Long idProgram);
}
