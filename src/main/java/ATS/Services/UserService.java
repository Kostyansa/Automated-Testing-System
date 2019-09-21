package ATS.Services;

import ATS.DAO.IUserRepository;
import ATS.DTO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public User getById(Long idUser) {
        return userRepository.getUser(idUser);
    }

    @Override
    public void regUser(User user) {
        userRepository.create(user);
    }

    @Override
    public User checkUser(User user) {
        User currentUser = userRepository.getUser(user.getIdUser());
        if (
                currentUser != null
                && user.getIdUser().equals(currentUser.getIdUser())
                && user.getPassword().equals(currentUser.getPassword())
        ) {
            return currentUser;
        } else {
            return null;
        }
    }



    @Override
    public List<User> getAllUser(){
        return userRepository.getAllUsers();
    }
}
