package com.ajay.repositotries;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ajay.entites.User;
import com.ajay.enums.UserRole;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String email);

    User findByUserrole(UserRole userRole);
}
