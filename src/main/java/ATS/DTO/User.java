package ATS.DTO;

import javax.validation.constraints.NotNull;

public class User {
    @NotNull
    private Long idUser;
    private String email;
    private String name;
    private String password;
    private String userMode;

    public User(Long idUser, String name, String email, String password, String userMode) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userMode = userMode;
        this.idUser = idUser;
    }

    public User(Long idUser, String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.idUser = idUser;
    }

    public User(){

    }

    public String getName() {
        return name;
    }

    public Long getIdUser() {
        return idUser;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserMode() {
        return userMode;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }
}

