package io.sattler.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sattler.db.UserPermission;
import org.hibernate.validator.constraints.NotEmpty;

public class PermissionResponse {

    @NotEmpty
    private String status;

    @NotEmpty
    private String transactionId;

    private String message;

    private UserPermission data;

    @JsonCreator
    public PermissionResponse(@JsonProperty("status") String status,
                              @JsonProperty("transaction_id") String transactionId,
                              @JsonProperty("message") String message,
                              @JsonProperty("data") UserPermission data) {
        this.status = status;
        this.transactionId = transactionId;
        this.message = message;
        this.data = data;
    }

    @JsonCreator
    public PermissionResponse(@JsonProperty("status") String status,
                              @JsonProperty("transaction_id") String transactionId,
                              @JsonProperty("message") String message) {
        this.status = status;
        this.transactionId = transactionId;
        this.message = message;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public UserPermission getData() {
        return data;
    }

}