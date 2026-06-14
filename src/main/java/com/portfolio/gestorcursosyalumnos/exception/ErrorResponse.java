package com.portfolio.gestorcursosyalumnos.exception;

import lombok.*;

import java.util.Map;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;
    private Map<String,String> multipleErrors;
}
