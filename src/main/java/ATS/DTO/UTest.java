package ATS.DTO;

public class UTest {
    private int idTest;
    private int idExercise;
    private String data;
    private String expectedResult;

    public UTest(int idTest, int idExercise, String data, String expectedResult) {
        this.idTest = idTest;
        this.idExercise = idExercise;
        this.data = data;
        this.expectedResult = expectedResult;
    }

    public int getIdTest() {
        return idTest;
    }

    public int getIdExercise() {
        return idExercise;
    }

    public String getData() {
        return data;
    }

    public String getExpectedResult() {
        return expectedResult;
    }
}
