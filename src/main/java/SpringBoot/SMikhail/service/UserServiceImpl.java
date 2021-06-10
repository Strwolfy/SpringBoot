package SpringBoot.SMikhail.service;

import SpringBoot.SMikhail.dao.UserRepository;
import SpringBoot.SMikhail.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User theUser) {
        userRepository.saveAndFlush(theUser);
    }

    @Override
    public User getUser(int id) {
        Optional<User> result = userRepository.findById(id);
        User theUser;
        if (result.isPresent()) {
            theUser = result.get();
        } else {
            // we didn't find the employee
            throw new RuntimeException("Did not find user id - " + id);
        }
        return theUser;
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByName(String s) {
        return userRepository.findUserByEmail(s);
    }
}
