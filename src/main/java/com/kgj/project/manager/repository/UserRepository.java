package com.kgj.project.manager.repository;

import com.kgj.project.manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT COUNT(u) FROM User u  WHERE u.userId LIKE :prefix%")
    int getNextSequence(@Param("prefix") String prefix);

    User findByEmail(String email);

    User findByUserId(String userId);

}
