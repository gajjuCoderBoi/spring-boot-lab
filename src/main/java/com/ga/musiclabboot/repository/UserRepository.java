package com.ga.musiclabboot.repository;

import com.ga.musiclabboot.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("FROM User u where u.username=?1 and u.password=?2")
    public User login(String username, String password);

    @Query("FROM User u where u.username=?1")
    public User findByUsername(String username);
}
