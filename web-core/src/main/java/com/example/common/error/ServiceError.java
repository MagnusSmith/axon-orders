package com.example.common.error;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 05/07/13
 * Time: 09:20
 *
 *
 * This is a holder for service errors so that they can be passed from the service layer via exceptions  to the controller.
 * This has the benefit that the api is not polluted with validation concerns
 *
 */
public final class ServiceError implements Serializable {

    private static final long serialVersionUID = 983252813203476550L;

    private final String errorCode;
    private final Object[] errorArgs;
    private final String defaultMessage;
    private final String field;

    public boolean isGlobal(){
        return field == null;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("defaultMessage", defaultMessage)
                .append("field", isGlobal() ? "global" : field)
                .append("errorCode", errorCode)
                .append("errorArgs", errorArgs).toString();
    }



    private ServiceError(String field, String errorCode, Object[] errorArgs, String defaultMessage) {
        this.field = field;
        this.errorCode = errorCode;
        this.errorArgs = errorArgs;
        this.defaultMessage = defaultMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object[] getErrorArgs() {
        return errorArgs;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public String getField() {
        return field;
    }

    public static class Builder {
        private String field;
        private String errorCode;
        private Object[] errorArgs;
        private String defaultMessage;



        public Builder(String errorCode){
            this.errorCode = errorCode;
        }

        public Builder withDefaultMessage(String defaultMessage){
            this.defaultMessage = defaultMessage;
            return this;
        }

        public Builder withField(String field) {
            this.field = field;
            return this;
        }


        public Builder withErrorArgs(Object... errorArgs) {
            this.errorArgs = errorArgs;
            return this;
        }


        public ServiceError build() {
            if(isBlank(errorCode)){
              throw new RuntimeException("Require error code to define ServiceError!");
            }
            return new ServiceError(field, errorCode, errorArgs, defaultMessage);
        }
    }
}
