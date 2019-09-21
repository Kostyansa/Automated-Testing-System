package ATS.DAO;

import ATS.DTO.User;

import java.util.List;

public interface IUserRepository {
    public List<User> getAllUsers();
    public void create(User user);
    public User getUser(Long idUser);
    public void updateUser(User user);
    public void deleteUser(Long idUser);
    public User getUserByLogin(String login);
}
