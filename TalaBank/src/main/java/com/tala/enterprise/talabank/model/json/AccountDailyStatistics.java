/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tala.enterprise.talabank.model.json;

/**
 *
 * @author kimando
 */
public class AccountDailyStatistics {
    private int sumDepositedToday = 0;
    private int depositsDoneToday = 0;
    private int sumWithdrawnToday = 0;
    private int withdrawalsDoneToday = 0;

    public int getSumDepositedToday() {
        return sumDepositedToday;
    }

    public void setSumDepositedToday(int sumDepositedToday) {
        this.sumDepositedToday = sumDepositedToday;
    }

    public int getDepositsDoneToday() {
        return depositsDoneToday;
    }

    public void setDepositsDoneToday(int depositsDoneToday) {
        this.depositsDoneToday = depositsDoneToday;
    }

    public int getSumWithdrawnToday() {
        return sumWithdrawnToday;
    }

    public void setSumWithdrawnToday(int sumWithdrawnToday) {
        this.sumWithdrawnToday = sumWithdrawnToday;
    }

    public int getWithdrawalsDoneToday() {
        return withdrawalsDoneToday;
    }

    public void setWithdrawalsDoneToday(int withdrawalsDoneToday) {
        this.withdrawalsDoneToday = withdrawalsDoneToday;
    }
}
