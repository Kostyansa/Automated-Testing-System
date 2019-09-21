package ATS.DAO;

import ATS.DTO.TestResult;

import java.util.List;

public interface ITestResultRepository {
    public void create(TestResult testResult);
    public TestResult getByIdProgram(Long idProgram);
    public void updateTestResult(TestResult testResult);
}
