package ATS.Services;

import ATS.DTO.User;
import java.util.List;

public interface IUserService {

    User getById(Long idUser);

    void regUser(User user);

    User checkUser(User user);

    List<User> getAllUser();

}
