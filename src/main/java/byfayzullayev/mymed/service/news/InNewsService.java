package byfayzullayev.mymed.service.news;

import byfayzullayev.mymed.repository.CategoryRepository;
import byfayzullayev.mymed.repository.InNewsRepository;
import byfayzullayev.mymed.entity.category.CategoryEntity;
import byfayzullayev.mymed.entity.news.InNewsEntity;
import byfayzullayev.mymed.model.receive.InNewsReceiveModel;
import byfayzullayev.mymed.model.response.ApiResponse;
import byfayzullayev.mymed.service.base.BaseService;
import byfayzullayev.mymed.service.file.FileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InNewsService implements BaseService {

    private final InNewsRepository inNewsRepository;
    private final FileService fileService;
    private final CategoryRepository categoryRepository;

    public InNewsService(InNewsRepository inNewsRepository, FileService fileService, CategoryRepository categoryRepository) {
        this.inNewsRepository = inNewsRepository;
        this.fileService = fileService;
        this.categoryRepository = categoryRepository;
    }

    public ApiResponse addInNews(
            InNewsReceiveModel inNewsReceiveModel
    ) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(inNewsReceiveModel.getCategoryId());
        if (optionalCategoryEntity.isEmpty())
            return ERROR_CATEGORY_NOT_FOUND;

        String imageUrl = fileService.saveFile(inNewsReceiveModel.getBase64(), inNewsReceiveModel.getContentType());
        if (imageUrl == null)
            return ERROR_IMAGE_CREATE;

        InNewsEntity inNewsEntity = new InNewsEntity();
        inNewsEntity.setCategoryEntity(optionalCategoryEntity.get());
        inNewsEntity.setRemainedDate(inNewsReceiveModel.getRemainedDate());
        inNewsEntity.setName(inNewsReceiveModel.getName());
        inNewsEntity.setImageUrl(imageUrl);
        inNewsRepository.save(inNewsEntity);

        return SUCCESS_V2;

    }

    public ApiResponse getInNewsList() {
        SUCCESS.setData(inNewsRepository.findAll());
        return SUCCESS;
    }

    public ApiResponse getInNewsList(long id) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(id);
        if (optionalCategoryEntity.isEmpty())
            return ERROR_CATEGORY_NOT_FOUND;

        List<InNewsEntity> inNewsEntityList = inNewsRepository.findByCategoryId(id);
        SUCCESS.setData(inNewsEntityList);
        return SUCCESS;

    }

    public ApiResponse updateInNews(long id, InNewsEntity inNewsEntity) {
        Optional<InNewsEntity> optionalInNewsEntity = inNewsRepository.findById(id);
        if (optionalInNewsEntity.isPresent()) {
            InNewsEntity updateId = optionalInNewsEntity.get();
            Optional<InNewsEntity> updateCategory = inNewsRepository.findByName(inNewsEntity.getName());

            if (updateCategory.isPresent())
                return USER_EXIST;

            updateId.setName(inNewsEntity.getName());
            inNewsRepository.save(updateId);
        }
        return SUCCESS_V2;


    }

    public ApiResponse deleteInNews(long id) {
        Optional<InNewsEntity> optionalInNewsEntity = inNewsRepository.findById(id);
        if (optionalInNewsEntity.isPresent()) {
            inNewsRepository.deleteById(id);

            return SUCCESS_V2;
        }
        return ERROR_DELETE;
    }


}
