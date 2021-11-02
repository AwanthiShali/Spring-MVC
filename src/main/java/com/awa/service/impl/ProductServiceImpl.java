package com.awa.service.impl;
/*
 * @author Yohan Samitha
 * @since 10/25/2021
 */

import com.awa.dto.ProductDTO;
import com.awa.dto.ProductImageDTO;
import com.awa.entity.Product;
import com.awa.entity.ProductImage;
import com.awa.exception.ValidateException;
import com.awa.repo.CategoryRepo;
import com.awa.repo.ProductImageRepo;
import com.awa.repo.ProductRepo;
import com.awa.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductImageRepo productImageRepo;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public boolean addProduct(ProductDTO dto) {
        System.out.println("dto " + dto.toString());
        if (categoryRepo.existsById(dto.getCategoryID())) {
            if (productRepo.existsById(dto.getProductID())) {
                throw new ValidateException("Product Already Exist");
            } else {
                productRepo.save(mapper.map(dto, Product.class));
                return true;
            }
        } else {
            throw new ValidateException("Wrong Category Id");
        }
    }

    @Override
    public boolean addProductImage(ProductImageDTO dto) {
        Optional<Product> productByName = productRepo.findProductByName(dto.getProductName());
        if (productByName.isPresent()) {
            if (productImageRepo.findProductImageByImageName(dto.getImageName()).isPresent()) {
                throw new ValidateException("Product image Already Exist");
            } else {
                dto.setProductID(productByName.get());
                productImageRepo.save(mapper.map(dto, ProductImage.class));
                return true;
            }
        } else {
            throw new ValidateException("No Product for this product ID");
        }
    }

//    @Override
//    public boolean deleteProduct(String id) {
//        if (!customerRepo.existsById(id)) {
//            throw new ValidateException("No Customer for Delete..!");
//        }
//        customerRepo.deleteById(id);
//        return true;
//    }
//
//    @Override
//    public ProductDTO searchProducts(String id) {
//        Optional<Product> product = productRepo.findById(id);
//        if (product.isPresent()) {
//            return mapper.map(product.get(), ProductDTO.class);
//        }
//        throw new ValidateException("There is no product for this product id");
//    }
//
//    @Override
//    public ArrayList<ProductDTO> getAllProducts() {
//        List<Product> all = customerRepo.findAll();
//        return mapper.map(all, new TypeToken<ArrayList<CustomerDTO>>() {
//        }.getType());
//    }
//
//    @Override
//    public boolean updateProduct(ProductDTO dto) {
//        if (productRepo.existsById(dto.getProductID())) {
//            Optional<User> user = userRepo.findById(dto.getUser_Id());
//            if (user.isPresent()) {
//                dto.setUser(mapper.map(user.get(), UserDTO.class));
//                customerRepo.save(mapper.map(dto, Customer.class));
//                return true;
//            } else {
//                throw new ValidateException("There is no User for this customer provided user id");
//            }
//        } else {
//            throw new ValidateException("There is no customer for this customer id");
//        }
//    }
}
