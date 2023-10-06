package com.treinaRecife.BlogAPI.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ErrorResponseForExceptions(Integer status,
                                         String path,
                                         @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
                                         LocalDateTime timestamp,
                                         String error,
                                         String message) {
}
