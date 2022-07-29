package ro.msg.learning.shop.controller.exception;

import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorResponse {
    private final Date timestamp;
    private final String message;

    public ErrorResponse(String message) {
        this.timestamp = new Date();
        this.message = message;
    }
}
