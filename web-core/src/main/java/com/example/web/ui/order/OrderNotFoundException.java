package com.example.web.ui.order;

import com.example.common.error.ServiceException;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 20/03/2014
 * Time: 15:56
 */
public class OrderNotFoundException extends ServiceException {
    public OrderNotFoundException(String message){
        super(message);
    }

}
