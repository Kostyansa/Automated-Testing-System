package ATS.DAO;

import ATS.DTO.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ExerciseRepository implements IExerciseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Exercise> RowMap = (rowStr, rowNum) -> new Exercise(
            rowStr.getString("text"),
            rowStr.getLong("idExercise")
    );

    public List<Exercise> getAllExercises() {
        String sql = "select * from `mydb`.`Exercise`";

        return jdbcTemplate.query(sql, new Object[]{}, RowMap);
    }

    public Exercise getExercise(Long idExercise) {
        String sql = "select * from `mydb`.`Exercise` where idExercise = ?";
        List<Exercise> exercises = jdbcTemplate.query(sql, new Object[]{idExercise},RowMap);
        if(exercises.isEmpty()){
            return null;
        }
        else{
            return exercises.get(0);
        }
    }

    public void deleteExercise(Long idExercise) {
        String sql = "delete Exercise from `mydb`.`Exercise` where idExercise = ?";
        jdbcTemplate.update(sql, new Object[]{idExercise});
    }

    @Override
    public void create(Exercise exercise) {
        String sql = "insert into `mydb`.`exercise` (`idExercise`,`Text`) VALUES(?, ?)";
        jdbcTemplate.update(sql, exercise.getIdExercise(), exercise.getText());
    }
}
