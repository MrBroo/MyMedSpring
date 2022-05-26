package byfayzullayev.mymed.service.service;

import byfayzullayev.mymed.repository.CategoryRepository;
import byfayzullayev.mymed.repository.CategoryServicesRepository;
import byfayzullayev.mymed.entity.category.CategoryEntity;
import byfayzullayev.mymed.entity.services.CategoryServicesEntity;
import byfayzullayev.mymed.model.receive.CategoryServicesReceiveModel;
import byfayzullayev.mymed.model.response.ApiResponse;
import byfayzullayev.mymed.service.base.BaseService;
import byfayzullayev.mymed.service.file.FileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServices implements BaseService {

    private final CategoryServicesRepository categoryServicesRepository;
    private final CategoryRepository categoryRepository;
    private final FileService fileService;


    public CategoryServices(CategoryServicesRepository categoryServicesRepository, CategoryRepository categoryRepository, FileService fileService) {
        this.categoryServicesRepository = categoryServicesRepository;
        this.categoryRepository = categoryRepository;
        this.fileService = fileService;
    }

    public ApiResponse addCategoryServices(CategoryServicesReceiveModel categoryServicesReceiveModel) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(categoryServicesReceiveModel.getCategoryId());
        if (optionalCategoryEntity.isEmpty())
            return ERROR_CATEGORY_NOT_FOUND;


        String imageUrl = fileService.saveFile(categoryServicesReceiveModel.getBase64(), categoryServicesReceiveModel.getContentType());
        if (imageUrl == null)
            return ERROR_IMAGE_CREATE;

        CategoryServicesEntity categoryServicesEntity = new CategoryServicesEntity();
        categoryServicesEntity.setCategoryEntity(optionalCategoryEntity.get());
        categoryServicesEntity.setName(categoryServicesReceiveModel.getName());
        categoryServicesEntity.setImageUrl(imageUrl);
        categoryServicesRepository.save(categoryServicesEntity);
        return SUCCESS_V2;

    }

    public ApiResponse getCategoryServicesList() {
        SUCCESS.setData(categoryServicesRepository.findAll());
        return SUCCESS;
    }

    public ApiResponse getCategoryServicesList(long id) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(id);
        if (optionalCategoryEntity.isEmpty())
            return ERROR_CATEGORY_NOT_FOUND;

        List<CategoryServicesEntity> categoryServicesEntityList = categoryServicesRepository.findByCategoryId(id);
        SUCCESS.setData(categoryServicesEntityList);
        return SUCCESS;
    }

    public ApiResponse updateCategoryServices(long id, CategoryServicesEntity categoryServicesEntity) {
        Optional<CategoryServicesEntity> optionalCategoryServicesEntity = categoryServicesRepository.findById(id);
        if (optionalCategoryServicesEntity.isPresent()) {
            CategoryServicesEntity updateId = optionalCategoryServicesEntity.get();
            Optional<CategoryServicesEntity> updateCategory = categoryServicesRepository.findByName(categoryServicesEntity.getName());

            if (updateCategory.isPresent())
                return USER_EXIST;

            updateId.setName(categoryServicesEntity.getName());
            categoryServicesRepository.save(updateId);
        }
        return SUCCESS_V2;


    }


    public ApiResponse deleteCategoryServices(long id) {
        Optional<CategoryServicesEntity> optionalCategoryServicesEntity = categoryServicesRepository.findById(id);
        if (optionalCategoryServicesEntity.isPresent()) {
            categoryServicesRepository.deleteById(id);

            return SUCCESS_V2;
        }
        return ERROR_DELETE;
    }
}
