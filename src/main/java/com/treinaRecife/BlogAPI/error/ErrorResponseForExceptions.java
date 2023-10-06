package com.treinaRecife.BlogAPI.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
@JsonPropertyOrder({"timestamp","status", "message", "error", "path"})
public record ErrorResponseForExceptions(Integer status,
                                         String path,
                                         @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
                                         LocalDateTime timestamp,
                                         String error,
                                         String message) {
}
