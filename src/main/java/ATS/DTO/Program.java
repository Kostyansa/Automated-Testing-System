package ATS.DTO;

public class Program {
    private Long idProgram;
    private String path;

    public Program(Long idProgram, String path) {
        this.idProgram = idProgram;
        this.path = path;
    }

    public Long getIdProgram() {
        return idProgram;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
