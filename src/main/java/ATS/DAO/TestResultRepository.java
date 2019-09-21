package ATS.DAO;

import ATS.DTO.TestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestResultRepository implements ITestResultRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<TestResult> RowMap = (rowStr, rowNum) -> new TestResult(
            rowStr.getLong("Program_idProgram1"),
            rowStr.getString("result")
    );

    @Override
    public void create(TestResult testResult) {
        String sql = "INSERT INTO `mydb`.`TestResult` (`result`, `Program_idProgram1`) " +
                "VALUES (?, ?)";
        jdbcTemplate.update(sql, testResult.getResult(), testResult.getIdProgram());
    }

    @Override
    public TestResult getByIdProgram(Long idProgram) {
        String sql = "select * from testResult where Program_idProgram1 = ?";
        List<TestResult> testResults = jdbcTemplate.query(sql, new Object[]{idProgram}, RowMap);
        if(testResults.isEmpty()){
            return null;
        }
        else{
            return testResults.get(0);
        }
    }

    public void updateTestResult(TestResult testResult) {
        String sql = "UPDATE TestResult SET result = ? where Program_idProgram1 = ?";
        jdbcTemplate.update(sql, testResult.getResult(), testResult.getIdProgram());
    }
}
