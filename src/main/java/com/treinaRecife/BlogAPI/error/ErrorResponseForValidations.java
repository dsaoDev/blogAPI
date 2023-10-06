package com.treinaRecife.BlogAPI.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
import java.util.Map;
@JsonPropertyOrder({"timestamp","status", "error", "erros", "path"})
public record ErrorResponseForValidations(Integer status,
                                          String path,
                                          @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
                                          LocalDateTime timestamp,
                                          String error,

                                          Map<String,String> erros) {
}
