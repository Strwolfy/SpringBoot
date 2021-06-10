package SpringBoot.SMikhail.service;

import SpringBoot.SMikhail.entity.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    void saveUser(User user);

    User getUser(int id);

    void deleteUser(int id);

    User getUserByName(String s);

}
