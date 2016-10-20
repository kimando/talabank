/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tala.enterprise.talabank.model.json;

import java.io.Serializable;

/**
 *
 * @author kimando
 */
public class BankAccount implements Serializable {
    private String name = "";
    private String number = "";
    private String type = "";
    private String branch = "";
    private int balance = 0;

    public BankAccount(String name, String number, String type, String branch, int balance){
        this.name = name;
        this.number = number;
        this.type = type;
        this.branch = branch;
        this.balance = balance;
    }
    
    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public String getBranch() {
        return branch;
    }

    public int getBalance() {
        return balance;
    }
}
