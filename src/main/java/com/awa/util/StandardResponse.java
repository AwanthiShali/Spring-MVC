package com.awa.util;
/*
 * @author Awanthi Shalika
 * @since 10/25/2021
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardResponse {
    private String code;
    private String message;
    private Object data;
}
