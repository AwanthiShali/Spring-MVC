package com.awa.controller;
/*
 * @author Awanthi Shalika
 * @since 10/25/2021
 */

import com.awa.dto.ProductDTO;
import com.awa.dto.ProductImageDTO;
import com.awa.exception.NotFoundException;
import com.awa.service.ProductService;
import com.awa.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> saveProduct(@RequestBody ProductDTO dto) {
        System.out.println("product post method " + dto.toString());
        String validateProductData = validateProductData(dto);
        if (validateProductData.equals("true")) {
            if (productService.addProduct(dto)) {
                return new ResponseEntity<>(new StandardResponse("201", "Done", dto), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new StandardResponse("500", "Internal Server Error Custom", dto), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new NotFoundException(validateProductData);
        }
    }

    @PostMapping("/productimage")
    public ResponseEntity<StandardResponse> saveProductImage(@RequestParam("file") MultipartFile myFile, @RequestHeader("productName") String productName) {
        try {
            System.out.println(myFile.getOriginalFilename());
            String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
            File uploadsDir = new File(projectPath + "/productImages");
            uploadsDir.mkdir();
            if (productService.addProductImage(new ProductImageDTO(0, productName, myFile.getOriginalFilename(), uploadsDir.getAbsolutePath() + "/" + myFile.getOriginalFilename(), null))) {
                myFile.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + myFile.getOriginalFilename()));
                return new ResponseEntity<>(new StandardResponse("201", "Done", (uploadsDir.canRead()) ? "Readable" : "Not readable"), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new StandardResponse("500", "Internal Server Error Custom", false), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StandardResponse("500", "Internal Server Error Custom", false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String validateProductData(ProductDTO dto) {
        if (dto.getName().isEmpty() || dto.getName().trim().length() <= 0) {
            return "Product Name is missing";
        } else if (dto.getDescription().isEmpty() || dto.getDescription().trim().length() <= 0) {
            return "Product Description is missing";
        } else if (dto.getCategoryID() < 0) {
            return "Product Category Id is missing";
        } else if (dto.getPrice().isEmpty() || dto.getPrice().trim().length() <= 0) {
            return "Product Price is missing";
        } else if (dto.getWarranty().isEmpty() || dto.getWarranty().trim().length() <= 0) {
            return "Product Warranty is missing";
        } else if (dto.getLength().isEmpty() || dto.getLength().trim().length() <= 0) {
            return "Product Length is missing";
        } else if (dto.getWidth().isEmpty() || dto.getWidth().trim().length() <= 0) {
            return "Product Width is missing";
        } else if (dto.getHeight().isEmpty() || dto.getHeight().trim().length() <= 0) {
            return "Product Height is missing";
        } else {
            return "true";
        }
    }

    @GetMapping()
    public ResponseEntity<StandardResponse> getAllProduct() {
//        ArrayList<ProductDTO> allProducts = productService.getAllProducts();
        ArrayList<ProductDTO> allProducts = new ArrayList<>();
        return new ResponseEntity<>(new StandardResponse("500", "e.getMessage()", allProducts), HttpStatus.INTERNAL_SERVER_ERROR);
    }


//    @DeleteMapping(params = {"id"}, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<StandardResponse> deleteCustomer(@RequestParam String id) {
//        if (id.trim().length() <= 0) {
//            throw new NotFoundException("Customer id cannot be empty");
//        } else {
//            boolean b = productService.deleteProduct(id);
//            return new ResponseEntity<>(new StandardResponse("200", "Product Delete Successful", id + " deleted " + b), HttpStatus.OK);
//        }
//    }

//    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody ProductDTO dto) {
//        String validate = validateProductData(dto);
//        if (validate.equals("true")) {
//            if (dto.getProductID() > 0) {
//                if (productService.updateProduct(dto)) {
//                    return new ResponseEntity<>(new StandardResponse("200", "Customer Update Successful", dto), HttpStatus.OK);
//                } else {
//                    return new ResponseEntity<>(new StandardResponse("500", "Internal Server Error Custom", dto), HttpStatus.INTERNAL_SERVER_ERROR);
//                }
//            } else {
//                throw new NotFoundException("No User Id is Provided");
//            }
//        } else {
//            throw new NotFoundException(validate);
//        }
//    }

    //    @GetMapping()
//    public ResponseEntity<StandardResponse> getAllProductImages(HttpServletResponse response, HttpServletRequest request) {
//        ArrayList<ProductDTO> allProducts = productService.getAllProducts();
//        try {
//            String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
//            File imageFile = new File(projectPath + "/" + "2cb4c6ef-a45a-4b74-bc73-92ddac8ff757.jpg");
//
//            InputStream in = getClass().getResourceAsStream("/com/baeldung/produceimage/image.jpg");
//            IOUtils.toByteArray(in);
//            String imageFilePath = imageFile.getAbsolutePath();
//            System.out.println(imageFilePath + " image load path");
//            System.out.println(imageFile.getName() + " image name");
//
//            BufferedImage bImage = ImageIO.read(imageFile);
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            ImageIO.write(bImage, "jpg", bos);
//            byte[] data = bos.toByteArray();
//
//
////            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
////            response.getOutputStream().write(selectedImage);
////            response.getOutputStream().close();
//            return new ResponseEntity<>(new StandardResponse("200", "", data), HttpStatus.OK);
//        } catch (IOException | URISyntaxException e) {
//            e.printStackTrace();
//        return new ResponseEntity<>(new StandardResponse("500", "e.getMessage()", false), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
