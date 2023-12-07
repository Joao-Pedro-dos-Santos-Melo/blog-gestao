package br.com.unifalmg.blog.exception;

public class UserNoFoundException extends RuntimeException{
    public UserNoFoundException(String mensage){
        super(mensage);
    }
}
