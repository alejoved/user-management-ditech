package com.project.usermanagement.user.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.usermanagement.user.infrastructure.entities.UserEntity;

@Repository
public interface IUserJpaRepository extends JpaRepository<UserEntity, UUID> {

}
