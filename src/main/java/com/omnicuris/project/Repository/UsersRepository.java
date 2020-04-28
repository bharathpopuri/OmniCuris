package com.omnicuris.project.Repository;

import java.util.Optional;

import com.omnicuris.project.Entity.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {

	Optional<Users> findUserByEmail(String email);
}