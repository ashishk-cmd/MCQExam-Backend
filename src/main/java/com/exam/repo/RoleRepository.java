package com.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.modal.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
