package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.data.general.DefaultPieDataset;

import com.model.accounts;
import com.model.stock;
import com.service.DaoService;

public class TradeState extends HttpServlet {
	DaoService doaservise = new DaoService();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String acountid = request.getParameter("acountid");
		String symbolid = request.getParameter("symbolid");
		String quantity = request.getParameter("quantity");
		String price = request.getParameter("price");

		tradinOordersExecutions(acountid, symbolid, Integer.parseInt(quantity),
				Float.parseFloat(price));

		DefaultPieDataset dataset = createGraph(acountid);
		request.setAttribute("dataset", dataset);
		RequestDispatcher rd = request.getRequestDispatcher("graphService");
		rd.forward(request, response);
	}

	private DefaultPieDataset createGraph(String acountid) {
		DefaultPieDataset dataset = new DefaultPieDataset();

		accounts account = doaservise.getAccountByAccountId(acountid);
		if (account != null && account.getStocks() != null
				&& !account.getStocks().isEmpty()) {

			for (stock st : account.getStocks()) {
				dataset.setValue(st.getStock_Name(), st.getStock_no());
			}

		}
		return dataset;
	}

	private void tradinOordersExecutions(String acountid, String symbolid,
			int quantity, float price) {

		accounts account = doaservise.getAccountByAccountId(acountid);

		float bal = account.getBalance();
		float equ = account.getEquaty_value();

		if (account != null) {

			bal = bal - (quantity * price);
			equ = equ + (quantity * price);

		}
		account.setBalance(bal);
		account.setEquaty_value(equ);
		doaservise.update(account);

	}
}