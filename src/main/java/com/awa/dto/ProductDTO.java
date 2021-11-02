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
public class ProductDTO {
    private int productID;
    private int categoryID;
    private String name;
    private String description;
    private String price;
    private String warranty;
    private String length;
    private String width;
    private String height;
}
