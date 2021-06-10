package SpringBoot.SMikhail.controller;


import SpringBoot.SMikhail.entity.Role;
import SpringBoot.SMikhail.entity.User;
import SpringBoot.SMikhail.service.RoleService;
import SpringBoot.SMikhail.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminAPI")
public class AdminRestController {
    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminRestController(UserService userService, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> listUsers() {
        final List<User> users = userService.getUsers();
        return users != null
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> listRoles() {
        final List<Role> roles = roleService.findAll();
        return roles != null
                ? new ResponseEntity<>(roles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody User theUser) {

        String encode = theUser.getPassword();
        if (theUser.getId() != 0) { // update user
            if (encode.isEmpty()) { //  password not changed
                theUser.setPassword(userService.getUser(theUser.getId()).getPassword());
            } else {
                passwordChanged(theUser, encode);
            }
        } else { //new user
            passwordChanged(theUser, encode);
        }
        userService.saveUser(theUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void passwordChanged(User theUser, String encode) {
        encode = passwordEncoder.encode(encode);
        theUser.setPassword(encode);
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody User theUser) {
        userService.deleteUser(theUser.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
