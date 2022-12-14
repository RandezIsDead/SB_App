package com.randez_trying.SB_App.repository;

import com.randez_trying.SB_App.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}