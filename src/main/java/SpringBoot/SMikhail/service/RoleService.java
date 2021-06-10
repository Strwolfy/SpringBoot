package SpringBoot.SMikhail.service;

import SpringBoot.SMikhail.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role getRole(int id);

}
