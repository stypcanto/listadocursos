package edu.cibertec.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorEntity {
    private String status;
    private String message;
    private Integer error;   
}
