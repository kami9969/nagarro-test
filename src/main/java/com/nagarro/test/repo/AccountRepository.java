package com.nagarro.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.test.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
