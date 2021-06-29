package org.example.dao;


import org.example.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UsersDao extends JpaRepository<Users,Long> {
    Users findByUsername(String username);

    ArrayList<Users> findByUsernameContaining(String username);

    ArrayList<Users> findByOrderById();
}
