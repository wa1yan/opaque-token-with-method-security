package dev.waiyanhtet.samplecodetest.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessLogicException extends RuntimeException {

    private final int code;

    public BusinessLogicException(int code, String message) {
        super(message);
        this.code = code;
    }
}