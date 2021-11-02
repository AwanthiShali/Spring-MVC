package com.awa.service;
/*
 * @author Yohan Samitha
 * @since 10/25/2021
 */

import com.awa.dto.ProductDTO;
import com.awa.dto.ProductImageDTO;

import java.util.ArrayList;

public interface ProductService {
    boolean addProduct(ProductDTO dto);

    boolean addProductImage(ProductImageDTO dto);

//    ArrayList<ProductDTO> getAllProducts();
//
//    ProductDTO searchProducts(String id);
//
//    boolean deleteProduct(String id);
//
//    boolean updateProduct(ProductDTO dto);
}
