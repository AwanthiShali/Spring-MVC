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
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int productID;
    private String name;
    private String description;
    private String price;
    private String warranty;
    private String length;
    private String width;
    private String height;
}
