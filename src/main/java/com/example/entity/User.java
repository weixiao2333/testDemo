package com.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * ????
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * ???
     */
    private Long id;
    
    /**
     * ???
     */
    private String username;
    
    /**
     * ??
     */
    private String email;
    
    /**
     * ??
     */
    private String password;
    
    /**
     * ??
     */
    private Integer status;
    
    /**
     * ??
     */
    private LocalDateTime createTime;
    
    /**
     * ??
     */
    private LocalDateTime updateTime;
}
