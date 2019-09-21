package ATS.DAO;

import ATS.DTO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements IUserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user";
        List<User> users = jdbcTemplate.query(sql, new Object[]{}, RowMap);
        return users;
    }

    private RowMapper<User> RowMap = (rowStr, rowNum) -> new User(
            rowStr.getLong("idUser"),
            rowStr.getString("username"),
            rowStr.getString("email"),
            rowStr.getString("password"),
            rowStr.getString("userMode")
    );

    @Override
    public void create(User user) {
        String sql = "insert into `mydb`.`user` (`idUser`, `username`, `email`, `password`, `userMode`) VALUES (?, ?, ?, ?,'user')";
        jdbcTemplate.update(sql, user.getIdUser(),
                user.getName(),
                user.getEmail(),
                user.getPassword());
    }

    @Override
    public User getUser(Long idUser) {
        String sql = "SELECT * FROM user where idUser = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{idUser}, RowMap);
        if(users.isEmpty()){
            return null;
        } else {
            return users.get(0);
        }
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE user SET username = ?, email = ?, password = ?, role = ?, ban = ? where idUser = ?";
        jdbcTemplate.update(sql, user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getUserMode(),
                user.getIdUser());
    }

    @Override
    public void deleteUser(Long idUser) {
        String sql = "DELETE user from user where idUser = ?";
        jdbcTemplate.update(sql, new Object[]{idUser});
    }

    @Override
    public User getUserByLogin(String name) {
        String sql = "SELECT * FROM user where username = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{name}, RowMap);
        if(users.isEmpty()){
            return null;
        } else {
            return users.get(0);
        }
    }
}
