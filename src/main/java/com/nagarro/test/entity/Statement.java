package com.nagarro.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "statement")
public class Statement {

	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "account_id")
	@JsonProperty("accountId")
	private Integer accountId;

	@Column(name = "datefield")
	@JsonProperty("dateField")
	private String dateField;

	@Column(name = "amount")
	@JsonProperty("amount")
	private String amount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getDateField() {
		return dateField;
	}

	public void setDateField(String dateField) {
		this.dateField = dateField;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
}
