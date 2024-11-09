package com.micro.userService.payload;

import org.springframework.http.HttpStatus;

public class ApiResponse {
    
    private String message;
    private boolean success;
    private HttpStatus status;

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    // Constructors
    public ApiResponse(String message, boolean success, HttpStatus status) {
        this.message = message;
        this.success = success;
        this.status = status;
    }

    public ApiResponse() {}

    // Static builder() method to initiate building
    public static Builder builder() {
        return new Builder();
    }

    // Static inner Builder class
    public static class Builder {
        
        private String message;
        private boolean success;
        private HttpStatus status;

        // Builder methods for setting fields
        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        // Final build method to return the constructed ApiResponse object
        public ApiResponse build() {
            return new ApiResponse(this.message, this.success, this.status);
        }
    }
}
