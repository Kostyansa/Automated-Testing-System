package ATS.DAO;

import ATS.DTO.UTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestRepository implements ITestRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<UTest> RowMap = (rowStr, rowNum) -> new UTest(
            rowStr.getInt("idTest"),
            rowStr.getInt("Exercise_idExercise"),
            rowStr.getString("TestInput"),
            rowStr.getString("Answer")
    );

    @Override
    public void create(UTest test) {
        String sql = "insert into `mydb`.`test` (`Exercise_idExercise`,`testInput`,`answer`)" +
                " VALUES(idExercise, data, expectedResult)";
        jdbcTemplate.update(sql,
                test.getIdExercise(),
                test.getData(),
                test.getExpectedResult());
    }

    @Override
    public UTest getTest(Long idTest) {
        String sql = "select * from test where idTest = ?";
        List<UTest> tests = jdbcTemplate.query(sql,new Object[]{idTest}, RowMap);
        if(tests.isEmpty()){
            return null;
        }
        else{
            return tests.get(0);
        }
    }

    public List<UTest> getTestsByExerciseId(Long idExercise){
        String sql = "select * from test where Exercise_idExercise = ?";
        return jdbcTemplate.query(sql,new Object[]{idExercise}, RowMap );
    }

    @Override
    public void deleteTest(Long idTest) {
        String sql = "delete test from test where idTest = ?";
        jdbcTemplate.update(sql, new Object[]{idTest});
    }
}
