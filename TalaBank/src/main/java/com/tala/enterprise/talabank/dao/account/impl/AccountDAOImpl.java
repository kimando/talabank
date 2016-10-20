/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tala.enterprise.talabank.dao.account.impl;

import com.tala.enterprise.talabank.TalaBankConstants;
import com.tala.enterprise.talabank.commons.DateManager;
import com.tala.enterprise.talabank.dao.TalaBankHibernateUtil;
import com.tala.enterprise.talabank.dao.account.AccountDao;
import com.tala.enterprise.talabank.model.db.Account;
import com.tala.enterprise.talabank.model.db.TransactionType;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.lf5.util.DateFormatManager;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author kimando
 */

/*
 * Perform some DAO level computations
 * Compile a response for the Business Objects layer
 * Alot skipped for brevity
 */
public class AccountDAOImpl implements AccountDao{
    
    final static Logger LOGGER = Logger.getLogger(AccountDAOImpl.class);
    
    @Override
    public Account getAccount(String accountNumber) {
        Account account = null;
        Session session = null;
        Transaction transaction = null;

        try {
            SessionFactory sessionFactory = TalaBankHibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery("From Account a where a.accountNumber = :accountNumber");
            query.setParameter("accountNumber", accountNumber);
            if (query.list().size() > 0) {
                account = (Account) query.list().get(0);
            } 
            transaction.commit();
        } 
        catch (Exception exception) {  
            if(transaction != null){
                transaction.rollback();
                if(session != null){
                    session.close();
                }
            }
            LOGGER.error(exception);
        }
        
        return account;
    }

    @Override
    public List<com.tala.enterprise.talabank.model.db.Transaction> getTransactions(Account account, Date date){
        List<com.tala.enterprise.talabank.model.db.Transaction> transactions = null;
        Session session = null;
        Transaction transaction = null;

        try {
            SessionFactory sessionFactory = TalaBankHibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery("From Transaction t where t.account = :account and t.transactionDate = :transactionDate");
            query.setParameter("account", account);
            query.setParameter("transactionDate", date);
                        
            if (query.list().size() > 0) {
                transactions = query.list();
            } 
            transaction.commit();
        } 
        catch (Exception exception) {  
            if(transaction != null){
                transaction.rollback();
                if(session != null){
                    session.close();
                }
            }
            LOGGER.error(exception);
        }
        
        return transactions;
    }
    
    @Override
    public boolean deposit(Account account, int amount) {
        boolean status = false;
        Session session = null;
        Transaction transaction = null;

        try {
            SessionFactory sessionFactory = TalaBankHibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            com.tala.enterprise.talabank.model.db.Transaction entry = new com.tala.enterprise.talabank.model.db.Transaction(account, getTransactiontype(TalaBankConstants.getCASH_DEPOSIT_TYPE()), amount, new Date(), new Date());
            session.save(entry);            
            
            account.setAccountBalance(account.getAccountBalance() + amount);            
            session.merge(account);
            
            transaction.commit();
            session.close();
            
            status = true;
        }  
        catch (HibernateException exception) {  
            if(transaction != null){
                transaction.rollback();
                if(session != null){
                    session.close();
                }
            }
            LOGGER.error(exception);
        }
        
        return status;
    }

    @Override
    public boolean withdraw(Account account, int amount) {
        boolean status = false;
        Session session = null;
        Transaction transaction = null;

        try {
            SessionFactory sessionFactory = TalaBankHibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            com.tala.enterprise.talabank.model.db.Transaction entry = new com.tala.enterprise.talabank.model.db.Transaction(account, getTransactiontype(TalaBankConstants.getCASH_WITHDRAW_TYPE()), amount, new Date(), new Date());
            session.save(entry); 
            
            account.setAccountBalance(account.getAccountBalance() - amount);
            session.merge(account);
            
            transaction.commit();
            session.close();
            
            status = true;
        } 
        catch (HibernateException exception) {  
            if(transaction != null){
                transaction.rollback();
                if(session != null){
                    session.close();
                }
            }
            LOGGER.error(exception);
        }
        
        return status;
    }
    
    private TransactionType getTransactiontype(String type) {
        TransactionType transactionType = null;
        Session session = null;
        Transaction transaction = null;

        try {
            SessionFactory sessionFactory = TalaBankHibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery("From TransactionType t where t.type = :type");
            query.setParameter("type", type);
            if (query.list().size() > 0) {
                transactionType = (TransactionType) query.list().get(0);
            } 
            transaction.commit();
        } 
        catch (Exception exception) {  
            if(transaction != null){
                transaction.rollback();
                if(session != null){
                    session.close();
                }
            }
            LOGGER.error(exception);
        }
        
        return transactionType;
    }
    
    public static void main(String [] args) throws ParseException{
        AccountDAOImpl accountDAOImpl = new AccountDAOImpl();
        System.out.println(accountDAOImpl.getTransactions(accountDAOImpl.getAccount("121343454"), DateManager.getDate("yyyy-MM-dd")).size());
    }
}
