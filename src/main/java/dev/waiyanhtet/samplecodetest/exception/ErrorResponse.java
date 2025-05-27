package dev.waiyanhtet.samplecodetest.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private int code;
    private String message;
    private LocalDateTime timestamp;
    private String path;
}
