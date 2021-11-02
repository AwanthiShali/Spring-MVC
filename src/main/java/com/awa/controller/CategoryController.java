package com.awa.controller;
/*
 * @author Awanthi Shalika
 * @since 10/25/2021
 */
import com.awa.dto.CategoryDTO;
import com.awa.exception.NotFoundException;
import com.awa.service.CategoryService;
import com.awa.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> saveCategory(@RequestBody CategoryDTO dto) {
        System.out.println("Category post method " + dto.toString());
        String validateCustomerData = validateCategoryData(dto);
        if (validateCustomerData.equals("true")) {
            if (categoryService.addCategory(dto)) {
                return new ResponseEntity<>(new StandardResponse("201", "Category Added Done", dto), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new StandardResponse("500", "Internal Server Error Custom", dto), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new NotFoundException(validateCustomerData);
        }
    }

    private String validateCategoryData(CategoryDTO dto) {
        if (dto.getCategoryID() < 0) {
            return "CategoryID is missing";
        } else if (dto.getCategoryName().isEmpty() || dto.getCategoryName().trim().length() <= 0) {
            return "Category Name is missing";
        } else if (dto.getDescription().isEmpty() || dto.getDescription().trim().length() <= 0) {
            return "Category Description is missing";
        } else {
            return "true";
        }
    }

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<StandardResponse> getAllCategory() {
//        ArrayList<CategoryDTO> allCustomers = categoryService.getAllCategory();
//        return new ResponseEntity<>(new StandardResponse("200", "", allCustomers), HttpStatus.OK);
//    }
//
//    @GetMapping(params = {"id"}, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<StandardResponse> SearchCategory(@RequestParam String id) {
//        if (id.trim().length() <= 0) {
//            throw new NotFoundException("Customer id cannot be empty");
//        } else {
//            CategoryDTO customerDTO = categoryService.searchCategory(id);
//            return new ResponseEntity<>(new StandardResponse("200", "", customerDTO), HttpStatus.OK);
//        }
//    }
//
//
//    @DeleteMapping(params = {"id"}, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<StandardResponse> deleteCategory(@RequestParam String id) {
//        if (id.trim().length() <= 0) {
//            throw new NotFoundException("Customer id cannot be empty");
//        } else {
//            boolean b = categoryService.deleteCategory(id);
//            return new ResponseEntity<>(new StandardResponse("200", "Customer Delete Successful", id + " deleted " + b), HttpStatus.OK);
//        }
//    }
//
//    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<StandardResponse> updateCategory(@RequestBody CategoryDTO dto) {
//        String validate = validateCategoryData(dto);
//        if (validate.equals("true")) {
//            if (dto.getUser_Id().trim().length() > 0) {
//                if (categoryService.updateCategory(dto)) {
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
}
