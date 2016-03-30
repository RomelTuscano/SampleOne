package com.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.util.HibernateUtil;
import com.model.accounts;
import com.model.stock;

public class DaoService {
	public accounts getAccountByAccountId(String accountid) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		accounts accounts = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session
					.createQuery("from accounts where Account_ID='" + accountid
							+ "'");
			accounts = (accounts) query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} 
		return accounts;
	}

	public boolean update(accounts account) {
		Session session = HibernateUtil.openSession();

		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.saveOrUpdate(account);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return true;
	}

	public List<stock> getStockByID(String accountid) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<stock> stocks = null;
		try {
			tx = session.getTransaction();
			tx.begin();

			Query query = session
					.createQuery("from stock where A_Id =(SELECT id FROM accounts where Account_ID = '"
							+ accountid + "')");
			stocks = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return stocks;
	}
}