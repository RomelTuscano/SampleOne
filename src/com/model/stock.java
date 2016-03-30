package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "stock")
public class stock implements Serializable {
	
	@ManyToOne
    @JoinColumn(name="A_Id")
    private accounts accounts;
	
	
	@Id
	@GeneratedValue
	@Column(name="Stock_Id")
	private int Stock_Id;
	public int getStock_Id() {
		return Stock_Id;
	}

	public void setStock_Id(int stock_Id) {
		Stock_Id = stock_Id;
	}

	@Column(name="Stock_Name")
    private String Stock_Name;
	@Column(name="Stock_no")
    private int Stock_no;
     
    public accounts getAccounts() {
		return accounts;
	}

	public void setAccounts(accounts accounts) {
		this.accounts = accounts;
	}

	public String getStock_Name() {
		return Stock_Name;
	}

	public void setStock_Name(String stock_Name) {
		Stock_Name = stock_Name;
	}

	public int getStock_no() {
		return Stock_no;
	}

	public void setStock_no(int stock_no) {
		Stock_no = stock_no;
	}

	
}
