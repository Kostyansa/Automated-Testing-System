package ATS.DAO;

import ATS.DTO.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProgramRepository implements IProgramRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Program> RowMap = (rowStr, rowNum) -> new Program(
            rowStr.getLong("idProgram"),
            rowStr.getString("Path")
    );

    public Program getProgram(Long idProgram) {
        String sql = "select * from program where idProgram = ?";
        List<Program> programs = jdbcTemplate.query(sql, new Object[]{idProgram}, RowMap);
        if (programs.isEmpty()){
            return null;
        }
        else{
            return programs.get(0);
        }
    }

    public Long getIdProgram(Long idUser, Long idExercise){
        String sql = "select idProgram from user_Has_Exercise where User_idUser = ? and Exercise_idExercise = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{idUser, idExercise}, Long.TYPE);
        }
        catch (EmptyResultDataAccessException exc){
            return null;
        }
    }

    public void create(Program program){
        String sql = "insert into `mydb`.`program` (`idProgram`,`path`) VALUES(?, ?)";
        jdbcTemplate.update(sql, program.getIdProgram(), program.getPath());
    }

    @Override
    public void createConnection(Long idUser, Long idExercise){
        String sql = "insert into `mydb`.`User_has_Exercise` (`User_idUser`,`Exercise_idExercise`) VALUES(?, ?)";
        jdbcTemplate.update(sql, idUser, idExercise);
    }

    public void updateProgram(Program program) {
        String sql = "update program set path = ? where idProgram = ?";
        jdbcTemplate.update(sql, program.getPath(), program.getIdProgram());
    }

    public void deleteProgram(Long idProgram) {
        String sql = "delete program from program where idProgram = ?";
        jdbcTemplate.update(sql, new Object[]{idProgram});
    }

    public List<Long> getIdPrograms(Long idExercise){
        String sql = "select idProgram from user_Has_Exercise where Exercise_idExercise = ?";
        try {
            return jdbcTemplate.queryForList(sql,new Object[]{idExercise} , Long.TYPE);
        }
        catch (EmptyResultDataAccessException exc){
            return null;
        }
    }

    public Long getIdUserByIdProgram(Long idProgram){
        String sql = "select User_idUser from user_Has_Exercise where idProgram = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{idProgram}, Long.TYPE);
        }
        catch (EmptyResultDataAccessException exc){
            return null;
        }
    }
}
