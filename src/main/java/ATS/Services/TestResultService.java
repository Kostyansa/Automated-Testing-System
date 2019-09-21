package ATS.Services;

import ATS.DAO.TestResultRepository;
import ATS.DTO.TestResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestResultService{
    @Autowired
    TestResultRepository testResultRepository;

    public void addTestResult(TestResult testResult){
        if(testResultRepository.getByIdProgram(testResult.getIdProgram()) != null){
            testResultRepository.updateTestResult(testResult);
        }
        else{
            testResultRepository.create(testResult);
        }
    }

    public TestResult getTestResult(Long idProgram){
        return testResultRepository.getByIdProgram(idProgram);
    }
}
