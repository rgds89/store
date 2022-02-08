package com.store.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardError  implements Serializable{
    private static final long serialVersionUID = 1L;

    private LocalDateTime timestamp;
    private Long status;
    private String error;
    private String message;
    private String path;
}
