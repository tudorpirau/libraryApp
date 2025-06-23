package com.app.library.repository;

import com.app.library.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Customer, Long> {}
