package com.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.modal.exam.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
