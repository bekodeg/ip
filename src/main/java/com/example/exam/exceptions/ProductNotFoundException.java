package com.example.exam.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id){
        super(String.format("Product with id [%s] is not found", id));
    }
}
