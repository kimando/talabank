/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tala.enterprise.talabank.dao.account;

import com.tala.enterprise.talabank.model.db.Account;
import com.tala.enterprise.talabank.model.db.Transaction;
import java.util.Date;
import java.util.List;

/**
 *
 * @author kimando
 */
public interface AccountDao {
    public Account getAccount(String accountNumber);
    public List<Transaction> getTransactions(Account account, Date date);
    public boolean deposit(Account account, int amount);
    public boolean withdraw(Account account, int amount);
}
