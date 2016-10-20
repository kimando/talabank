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
public class ServiceResponse implements Serializable {
    private int code = 200;
    private String message = "Success";
    private Object response = null;
    
    public ServiceResponse(int code, String message, Object response){
        this.code = code;
        this.message = message;
        this.response = response;
    }
    
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getResponse() {
        return response;
    }
}
