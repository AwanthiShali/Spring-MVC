package com.awa.entity;
/*
 * @author Awanthi Shalika
 * @since 10/25/2021
 */

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String user_Id;
    private String email;
    private String password;
}
