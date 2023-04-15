package com.subspaceclone.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subspaceclone.backend.Model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
