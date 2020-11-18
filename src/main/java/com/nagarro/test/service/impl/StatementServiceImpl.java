package com.nagarro.test.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.nagarro.test.dto.AccountStatementDTO;
import com.nagarro.test.dto.StatementDTO;
import com.nagarro.test.entity.Account;
import com.nagarro.test.entity.Statement;
import com.nagarro.test.repo.AccountRepository;
import com.nagarro.test.repo.StatementRepository;
import com.nagarro.test.service.StatementService;
import com.nagarro.test.util.StringUtil;

@Service
public class StatementServiceImpl implements StatementService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private StatementRepository statementRepository;

	@Override
	public AccountStatementDTO getAccountStatement(Integer accountId,
			LocalDate fromDate, LocalDate toDate, Double fromAmount,
			Double toAmount) {
		Optional<Account> account = accountRepository.findById(accountId);
		if (!account.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Account not found with id:" + accountId);
		}
		validateRequestParam(fromDate, toDate, fromAmount, toAmount);
		AccountStatementDTO accountStatementDTO = new AccountStatementDTO();
		accountStatementDTO.setAccountNumber(StringUtil.hashWith256(account.get().getAccountNumber()));
		accountStatementDTO.setAccountType(account.get().getAccountType());
		List<StatementDTO> statementDTOList = new ArrayList<StatementDTO>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		List<Statement> accountStatements = statementRepository.findByAccountId(accountId);
		accountStatements.forEach(statement -> {
			LocalDate savedDate = LocalDate.parse(statement.getDateField(), formatter);
			BigDecimal bigDecimal = new BigDecimal(statement.getAmount()).setScale(2, RoundingMode.HALF_UP);
			Double savedAmount = bigDecimal.doubleValue();
			if (fromDate == null && toDate == null && fromAmount == null
					&& toAmount == null) {
				if (ChronoUnit.DAYS.between(savedDate, LocalDate.now()) <= 30) {
					statementDTOList.add(new StatementDTO(savedDate, savedAmount));
				}
			}
			if (fromDate != null && toDate != null) {
				if (savedDate.compareTo(fromDate) >= 0
						&& savedDate.compareTo(toDate) <= 0) {
					statementDTOList.add(new StatementDTO(savedDate, savedAmount));
				}
			} else if (fromAmount != null && toAmount != null) {
				if (savedAmount.compareTo(fromAmount) >= 0
						&& savedAmount.compareTo(toAmount) <= 0) {
					statementDTOList.add(new StatementDTO(savedDate, savedAmount));
				}
			}
		});
		accountStatementDTO.setStatements(statementDTOList);
		return accountStatementDTO;
	}

	private void validateRequestParam(LocalDate fromDate, LocalDate toDate,
			Double fromAmount, Double toAmount) {
		if(fromDate != null && toDate == null || fromDate == null && toDate != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"fromDate and toDate are required!");
		}
		if(fromAmount != null && toAmount == null || fromAmount == null && toAmount != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"fromAmount and toAmount are required!");
		}
	}
}
