package com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.repository;

import com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
