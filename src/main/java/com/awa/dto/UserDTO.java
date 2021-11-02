package com.awa.dto;
/*
 * @author Awanthi Shalika
 * @since 10/25/2021
 */

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class UserDTO {
    private String user_Id;
    private String email;
    private String password;
}
