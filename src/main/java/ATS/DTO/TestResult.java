package ATS.DTO;

public class TestResult {
    private String result;
    private Long idProgram;

    public TestResult(Long idProgram, String result) {
        this.result = result;
        this.idProgram = idProgram;
    }

    public String getResult() {
        return result;
    }

    public Long getIdProgram() {
        return idProgram;
    }

}
