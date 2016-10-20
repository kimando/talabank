/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tala.enterprise.talabank.bo.impl;

import com.tala.enterprise.talabank.TalaBankConstants;
import com.tala.enterprise.talabank.bo.TransactionManager;
import com.tala.enterprise.talabank.commons.DateManager;
import com.tala.enterprise.talabank.dao.account.AccountDao;
import com.tala.enterprise.talabank.model.db.Account;
import com.tala.enterprise.talabank.model.db.Transaction;
import com.tala.enterprise.talabank.model.json.AccountDailyStatistics;
import com.tala.enterprise.talabank.model.json.BankAccount;
import com.tala.enterprise.talabank.model.json.ServiceResponse;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author kimando
 */

/*
 * Perform some business level validations
 * Clean up before hitting the DAO layer
 * Call other business level functionalities
 * Compile a response for the service layer
 * Alot skipped for brevity
 */
public class TransactionManagerImpl implements TransactionManager {

    @Autowired
    AccountDao accountDao;

    final static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(TransactionManagerImpl.class);

    @Override
    public ServiceResponse checkBalance(String accountNumber) {
        ServiceResponse serviceResponse = null;

        Account account = accountDao.getAccount(accountNumber);
        if (account != null) {
            serviceResponse = new ServiceResponse(200, "Success", new BankAccount(account.getAccountName(), account.getAccountNumber(), account.getAccountType().getType(), account.getBranch().getBranchName(), account.getAccountBalance()));
        } else {
            serviceResponse = new ServiceResponse(404, "Error", "Account Number " + accountNumber + " Not Found");
        }

        return serviceResponse;
    }

    @Override
    public ServiceResponse deposit(String accountNumber, int amount) {
        ServiceResponse serviceResponse = null;

        Account account = accountDao.getAccount(accountNumber);
        if (account != null) {
            if (amount <= TalaBankConstants.getMAX_DEPOSIT_PER_TXN()) {
                AccountDailyStatistics accountDailyStatistics = getAccountDailyStatistics(account);

                int sumDepositedToday = accountDailyStatistics.getSumDepositedToday();
                int depositsDoneToday = accountDailyStatistics.getDepositsDoneToday();

                if (depositsDoneToday < TalaBankConstants.getMAX_DEP_FREQ()) {
                    if (sumDepositedToday <= TalaBankConstants.getMAX_DEPOSIT_PER_DAY()) {
                        if ((sumDepositedToday + amount) <= TalaBankConstants.getMAX_DEPOSIT_PER_DAY()) {
                            if (accountDao.deposit(account, amount)) {
                                serviceResponse = new ServiceResponse(200, "Success", "Deposited " + amount + " In Account " + accountNumber);
                            } else {
                                serviceResponse = new ServiceResponse(500, "Error", "Internal Server Error. This Has Been Escalated To Management");
                            }
                        } else {
                            serviceResponse = new ServiceResponse(500, "Error", "Depositing " + amount + " Will Exceed Maximum Amount Depositable Per Day. "
                                    + "Try Amount Less Or Equal To " + (TalaBankConstants.getMAX_DEPOSIT_PER_DAY() - sumDepositedToday));
                        }
                    } else {
                        serviceResponse = new ServiceResponse(500, "Error", "Exceeded Maximum Deposits Per Day");
                    }
                } else {
                    serviceResponse = new ServiceResponse(500, "Error", "Exceeded Maximum Deposit Frequency For Today");
                }
            } else {
                serviceResponse = new ServiceResponse(500, "Error", "The Amount Exceeds Maximum Amount Depositable Per Transaction");
            }
        } else {
            serviceResponse = new ServiceResponse(404, "Error", "Account Number " + accountNumber + " Not Found");
        }

        return serviceResponse;
    }

    @Override
    public ServiceResponse withdraw(String accountNumber, int amount) {
        ServiceResponse serviceResponse = null;

        Account account = accountDao.getAccount(accountNumber);
        if (account != null) {
            if (amount <= TalaBankConstants.getMAX_WITHDRAW_PER_TXN()) {
                AccountDailyStatistics accountDailyStatistics = getAccountDailyStatistics(account);

                int sumWithdrawnToday = accountDailyStatistics.getSumWithdrawnToday();
                int withdrawalsDoneToday = accountDailyStatistics.getWithdrawalsDoneToday();

                if (withdrawalsDoneToday < TalaBankConstants.getMAX_WITHDRAW_FREQ()) {
                    if (sumWithdrawnToday <= TalaBankConstants.getMAX_DEPOSIT_PER_DAY()) {
                        if ((sumWithdrawnToday + amount) <= TalaBankConstants.getMAX_WITHDRAW_PER_DAY()) {
                            if (account.getAccountBalance() >= amount) {
                                if (accountDao.withdraw(account, amount)) {
                                    serviceResponse = new ServiceResponse(200, "Success", "Withdrawn " + amount + " From Account " + accountNumber);
                                } else {
                                    serviceResponse = new ServiceResponse(500, "Error", "Internal Server Error. This Has Been Escalated To Management");
                                }
                            } else {
                                serviceResponse = new ServiceResponse(500, "Error", "Insufficient Account Balance");
                            }
                        } else {
                            serviceResponse = new ServiceResponse(500, "Error", "Withdrawing " + amount + " Will Exceed Maximum Amount Withdrawable Per Day. "
                                    + "Try Amount Less Or Equal To " + (TalaBankConstants.getMAX_WITHDRAW_PER_DAY() - sumWithdrawnToday));
                        }
                    } else {
                        serviceResponse = new ServiceResponse(500, "Error", "Exceeded Maximum Withdrawals Per Day");
                    }
                } else {
                    serviceResponse = new ServiceResponse(500, "Error", "Exceeded Maximum Withdrawal Frequency For Today");
                }
            } else {
                serviceResponse = new ServiceResponse(500, "Error", "The Amount Exceeds Maximum Amount Withdrawable Per Transaction");
            }
        } else {
            serviceResponse = new ServiceResponse(404, "Error", "Account Number " + accountNumber + " Not Found");
        }

        return serviceResponse;
    }

    private AccountDailyStatistics getAccountDailyStatistics(Account account) {
        AccountDailyStatistics accountDailyStatistics = new AccountDailyStatistics();

        int depositsDoneToday = 0;
        int withdrawalsDoneToday = 0;
        int sumDepositedToday = 0;
        int sumWithdrawnToday = 0;

        try {
            List<Transaction> transactions = accountDao.getTransactions(account, DateManager.getDate("yyyy-MM-dd"));
            if (transactions != null) {
                for (Transaction transaction : transactions) {
                    if (transaction.getTransactionType().getType().equals(TalaBankConstants.getCASH_DEPOSIT_TYPE())) {
                        depositsDoneToday = depositsDoneToday + 1;
                        sumDepositedToday = sumDepositedToday + transaction.getAmount();
                    }
                    if (transaction.getTransactionType().getType().equals(TalaBankConstants.getCASH_WITHDRAW_TYPE())) {
                        withdrawalsDoneToday = withdrawalsDoneToday + 1;
                        sumWithdrawnToday = sumWithdrawnToday + transaction.getAmount();
                    }
                }
            }
            accountDailyStatistics.setDepositsDoneToday(depositsDoneToday);
            accountDailyStatistics.setSumDepositedToday(sumDepositedToday);
            accountDailyStatistics.setWithdrawalsDoneToday(withdrawalsDoneToday);
            accountDailyStatistics.setSumWithdrawnToday(sumWithdrawnToday);
        } catch (ParseException ex) {
            LOGGER.error(ex);
        }

        return accountDailyStatistics;
    }
}
