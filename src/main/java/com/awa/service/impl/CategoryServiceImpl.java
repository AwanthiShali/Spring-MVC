package com.awa.service.impl;
/*
 * @author Yohan Samitha
 * @since 11/2/2021
 */

import com.awa.dto.CategoryDTO;
import com.awa.entity.Category;
import com.awa.exception.ValidateException;
import com.awa.repo.CategoryRepo;
import com.awa.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper mapper;

    @Override
    public boolean addCategory(CategoryDTO dto) {
        if (categoryRepo.existsById(dto.getCategoryID())) {
            throw new ValidateException("Category Already Exist");
        } else {
            categoryRepo.save(mapper.map(dto, Category.class));
            return true;
        }
    }
}
