package com.nagarro.test.service;

import java.time.LocalDate;

import com.nagarro.test.dto.AccountStatementDTO;

public interface StatementService {

	AccountStatementDTO getAccountStatement(Integer accountId,
			LocalDate fromDate, LocalDate toDate, Double fromAmount,
			Double toAmount);

}
