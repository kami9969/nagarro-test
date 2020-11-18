package com.nagarro.test.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.test.entity.Statement;

public interface StatementRepository extends JpaRepository<Statement, Integer> {

	List<Statement> findByAccountId(Integer accountId);
}
