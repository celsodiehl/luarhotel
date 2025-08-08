package com.celsodev.LuarHotel.exception;

public class OurException extends RuntimeException{

    //Classe/método responsável por enviar mensagens de exceção
    public OurException(String message){
        super(message);
    }

}
