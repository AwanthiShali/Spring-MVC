package com.awa.dto;
/*
 * @author Awanthi Shalika
 * @since 10/25/2021
 */

import com.awa.entity.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class ProductImageDTO {
    private int imageId;
    private String productName;
    private String imageName;
    private String imagePath;
    private Product productID;
}
