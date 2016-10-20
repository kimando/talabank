/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tala.enterprise.talabank.bo;

import com.tala.enterprise.talabank.model.json.ServiceResponse;

/**
 *
 * @author kimando
 */
public interface TransactionManager {
    public ServiceResponse checkBalance(String accountNumber);
    public ServiceResponse deposit(String accountNumber, int amount);
    public ServiceResponse withdraw(String accountNumber, int amount);
}
