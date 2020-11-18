package com.nagarro.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * Author: TOS
 */
@Entity
@Table(name = "account")
public class Account {

	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "account_type")
	@JsonProperty("accountType")
	private String accountType;

	@Column(name = "account_number")
	@JsonProperty("accountNumber")
	private String accountNumber;

	// @OneToMany(fetch = FetchType.EAGER)
	// @JoinColumn(name="account_id")
	// private Set<Statement> statements;

	public Account() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
