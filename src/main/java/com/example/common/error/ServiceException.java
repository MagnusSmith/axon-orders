package com.example.common.error;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 04/07/13
 * Time: 10:02
 *
 *
 *
 * Used to pass errors from the service layer.
 * In addition to normal exception use can store validation errors as <code>ServiceError</code> that are
 * easily digestible in the Controller layer.
 *
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 5192352813200212050L;


    protected List<ServiceError> errors   = new ArrayList<>();

    public boolean hasErrors(){
        return !errors.isEmpty();
    }


    public ServiceException() {
          super();
    }

    public ServiceException(ServiceError error) {
        super();
        errors.add(error);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message, Throwable cause, ServiceError error) {
        super(message, cause);
        errors.add(error);
    }


    public ServiceException(String message, ServiceError error) {
        super(message);
        errors.add(error);
    }


    public void addServiceError(ServiceError error){
        errors.add(error);
    }

    public List<ServiceError> getServiceErrors() {
        return errors;
    }


    public String toErrorString() {
        return new ToStringBuilder(this)
                .append("message", this.getLocalizedMessage())
                .append("errors",  StringUtils.join(errors.toArray(), "\n")).toString();
    }

}
