package com.model;



import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name = "accounts")
public class accounts implements Serializable {

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	private float balance;
	private String Account_ID;
	private float Equaty_value;
	private float Account_Valur;
	@OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="A_Id")
    private List<stock> stocks;
	public accounts() {
	}

	public accounts(float balance, String Account_ID, float Equaty_value,
			float Account_Value) {
		this.balance = balance;
		this.Account_ID = Account_ID;
		this.Equaty_value = Equaty_value;
		this.Account_Valur = Account_Value;

	}

	public void setEquaty_value(float equaty_value) {
		Equaty_value = equaty_value;
	}

	public void setAccount_Valur(float account_Valur) {
		Account_Valur = account_Valur;
	}
	public float getEquaty_value() {
		return Equaty_value;
	}

	public float getAccount_Valur() {
		return Account_Valur;
	}
	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getAccount_ID() {
		return Account_ID;
	}

	public void setAccount_ID(String account_ID) {
		Account_ID = account_ID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	public List<stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<stock> stocks) {
		this.stocks = stocks;
	}
	
	public void addStocks(stock stocks) {
		this.stocks.add(stocks);
	}

}
