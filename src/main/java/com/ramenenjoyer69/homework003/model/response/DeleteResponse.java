package com.ramenenjoyer69.homework003.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DeleteResponse <T>{
    private String message;
    private HttpStatus status;
    private LocalDateTime time;
}