/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tala.enterprise.talabank;

/**
 *
 * @author kimando
 */
public class TalaBankConstants {
    private static final int MAX_DEPOSIT_PER_DAY = 150000;
    private static final int MAX_DEPOSIT_PER_TXN = 40000;
    private static final int MAX_DEP_FREQ = 4;
    private static final int MAX_WITHDRAW_PER_DAY = 50000;
    private static final int MAX_WITHDRAW_PER_TXN = 20000;
    private static final int MAX_WITHDRAW_FREQ = 3;
    private static final String BALANCE_ENQUIRY_TYPE = "BAL_ENQ";
    private static final String CASH_DEPOSIT_TYPE = "CASH_DEP";
    private static final String CASH_WITHDRAW_TYPE = "CASH_WITH";

    public static int getMAX_DEPOSIT_PER_DAY() {
        return MAX_DEPOSIT_PER_DAY;
    }

    public static int getMAX_DEPOSIT_PER_TXN() {
        return MAX_DEPOSIT_PER_TXN;
    }

    public static int getMAX_DEP_FREQ() {
        return MAX_DEP_FREQ;
    }

    public static int getMAX_WITHDRAW_PER_DAY() {
        return MAX_WITHDRAW_PER_DAY;
    }

    public static int getMAX_WITHDRAW_PER_TXN() {
        return MAX_WITHDRAW_PER_TXN;
    }

    public static int getMAX_WITHDRAW_FREQ() {
        return MAX_WITHDRAW_FREQ;
    }

    public static String getBALANCE_ENQUIRY_TYPE() {
        return BALANCE_ENQUIRY_TYPE;
    }

    public static String getCASH_DEPOSIT_TYPE() {
        return CASH_DEPOSIT_TYPE;
    }

    public static String getCASH_WITHDRAW_TYPE() {
        return CASH_WITHDRAW_TYPE;
    }
}
