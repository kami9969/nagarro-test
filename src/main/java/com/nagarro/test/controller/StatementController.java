package com.nagarro.test.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.test.dto.AccountStatementDTO;
import com.nagarro.test.service.StatementService;

@RestController
@RequestMapping("/rest/api/v1/statement")
public class StatementController {

	@Autowired
	private StatementService statementService;

	@PreAuthorize("hasRole('ROLE_ADMIN') or "
			+ "(hasRole('ROLE_USER') and #fromDate == null and #toDate == null"
			+ " and #fromAmount == null and #toAmount == null )")
	@RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
	public AccountStatementDTO findOne(
			@PathVariable("accountId") Integer accountId,
			@DateTimeFormat(pattern = "yyyy-MM-dd") @Param("fromDate") LocalDate fromDate,
			@DateTimeFormat(pattern = "yyyy-MM-dd") @Param("toDate") LocalDate toDate,
			@Param("fromAmount") Double fromAmount,
			@Param("toAmount") Double toAmount) {

		return statementService.getAccountStatement(accountId, fromDate,
				toDate, fromAmount, toAmount);
	}
}
