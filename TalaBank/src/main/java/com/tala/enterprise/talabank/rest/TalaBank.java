/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tala.enterprise.talabank.rest;

import com.tala.enterprise.talabank.bo.TransactionManager;
import com.tala.enterprise.talabank.model.json.BankTransaction;
import com.tala.enterprise.talabank.model.json.ServiceResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author kimando
 */
@Component
@Path("/api")
public class TalaBank {

    @Autowired
    TransactionManager transactionManager;

    @GET
    @Path("/balance/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkBalance(@PathParam("accountNumber") String accountNumber) {
        ServiceResponse serviceResponse = transactionManager.checkBalance(accountNumber);
        Response response;
        
        if(serviceResponse.getCode() == 200){
            response = Response.status(serviceResponse.getCode()).entity(serviceResponse.getResponse()).build();
        }
        else{
            response = Response.status(serviceResponse.getCode()).entity(serviceResponse.getResponse()).build();
        }

        return response;
    }
    
    @POST
    @Path("/deposit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deposit(BankTransaction bankTransaction){ 
        ServiceResponse serviceResponse = transactionManager.deposit(bankTransaction.getAccountNumber(), bankTransaction.getAmount());
        Response response;
        
        if(serviceResponse.getCode() == 200){
            response = Response.status(serviceResponse.getCode()).entity(serviceResponse.getResponse()).build();
        }
        else{
            response = Response.status(serviceResponse.getCode()).entity(serviceResponse.getResponse()).build();
        }

        return response;
    }
    
    @POST
    @Path("/withdraw")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response withdraw(BankTransaction bankTransaction){ 
        ServiceResponse serviceResponse = transactionManager.withdraw(bankTransaction.getAccountNumber(), bankTransaction.getAmount());
        Response response;
        
        if(serviceResponse.getCode() == 200){
            response = Response.status(serviceResponse.getCode()).entity(serviceResponse.getResponse()).build();
        }
        else{
            response = Response.status(serviceResponse.getCode()).entity(serviceResponse.getResponse()).build();
        }

        return response;
    }
}
