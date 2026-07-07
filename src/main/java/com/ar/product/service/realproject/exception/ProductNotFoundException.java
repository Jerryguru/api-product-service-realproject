package com.ar.product.service.realproject.exception;
/*
* Creating A CustomException
* @author--> Jerry
* */
public class ProductNotFoundException  extends RuntimeException{
    public ProductNotFoundException( String message){
        // here Super Keyword will go into a RunTimeException
        super(message);
    }
}
