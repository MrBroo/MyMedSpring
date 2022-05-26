package byfayzullayev.mymed.service.sale;

import byfayzullayev.mymed.repository.CategoryRepository;
import byfayzullayev.mymed.repository.InSaleRepository;
import byfayzullayev.mymed.entity.category.CategoryEntity;
import byfayzullayev.mymed.entity.sale.InSaleEntity;
import byfayzullayev.mymed.model.receive.InSaleReceiveModel;
import byfayzullayev.mymed.model.response.ApiResponse;
import byfayzullayev.mymed.service.base.BaseService;
import byfayzullayev.mymed.service.file.FileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class InSaleService implements BaseService {

    private final InSaleRepository inSaleRepository;
    private final CategoryRepository categoryRepository;
    private final FileService fileService;

    public InSaleService(InSaleRepository inSaleRepository, CategoryRepository categoryRepository, FileService fileService) {
        this.inSaleRepository = inSaleRepository;
        this.categoryRepository = categoryRepository;
        this.fileService = fileService;
    }


    public ApiResponse addInSale(InSaleReceiveModel inSaleReceiveModel) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(inSaleReceiveModel.getCategory_Id());
        if (optionalCategoryEntity.isEmpty())
            return ERROR_CATEGORY_NOT_FOUND;

        String imageUrl = fileService.saveFile(inSaleReceiveModel.getBase64(), inSaleReceiveModel.getContentType());
        if (imageUrl == null)
        return ERROR_IMAGE_CREATE;

        InSaleEntity inSaleEntity = new InSaleEntity();
        inSaleEntity.setImageUrl(imageUrl);
        inSaleEntity.setRemainedDate(inSaleReceiveModel.getRemainedDate());
        inSaleEntity.setCategoryEntity(optionalCategoryEntity.get());
        inSaleEntity.setName(inSaleReceiveModel.getName());
        inSaleRepository.save(inSaleEntity);

        return SUCCESS_V2;
    }

    public ApiResponse getInSaleList() {
        SUCCESS.setData(inSaleRepository.findAll());
        return SUCCESS;
    }

    public ApiResponse getInSaleList(long id) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(id);
        if (optionalCategoryEntity.isEmpty())
            return ERROR_CATEGORY_NOT_FOUND;

        List<InSaleEntity> inSaleEntityList = inSaleRepository.findByCategoryId(id);
        SUCCESS.setData(inSaleEntityList);
        return SUCCESS;
    }


    public ApiResponse updateInSale(long id, InSaleEntity inSaleEntity) {
        Optional<InSaleEntity> optionalInSaleEntity = inSaleRepository.findById(id);
        if (optionalInSaleEntity.isPresent()) {
            InSaleEntity updateId = optionalInSaleEntity.get();
            Optional<InSaleEntity> updateCategory = inSaleRepository.findByName(inSaleEntity.getName());

            if (updateCategory.isPresent())
                return USER_EXIST;

            updateId.setName(inSaleEntity.getName());
            inSaleRepository.save(updateId);
        }
        return SUCCESS_V2;

    }

    public ApiResponse deleteInSale(long id) {
        Optional<InSaleEntity> optionalInSaleEntity = inSaleRepository.findById(id);
        if (optionalInSaleEntity.isPresent()) {
            inSaleRepository.deleteById(id);

            return SUCCESS_V2;
        }
        return ERROR_DELETE;
    }
}
