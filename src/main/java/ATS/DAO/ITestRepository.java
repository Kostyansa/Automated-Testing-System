package ATS.DAO;

import ATS.DTO.UTest;

import java.util.List;

public interface ITestRepository {
    public void create(UTest test);
    public UTest getTest(Long idTest);
    public List<UTest> getTestsByExerciseId(Long idTest);
    public void deleteTest(Long idTest);
}
