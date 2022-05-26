package byfayzullayev.mymed.service.specialist;

import byfayzullayev.mymed.entity.category.CategoryEntity;
import byfayzullayev.mymed.repository.CategoryRepository;
import byfayzullayev.mymed.repository.InSpecialistRepository;
import byfayzullayev.mymed.entity.specialist.InSpecialistEntity;
import byfayzullayev.mymed.model.receive.InSpecialistReceiveModel;
import byfayzullayev.mymed.model.response.ApiResponse;
import byfayzullayev.mymed.service.base.BaseService;
import byfayzullayev.mymed.service.file.FileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InSpecialistService implements BaseService {
    private final InSpecialistRepository inSpecialistRepository;
    private final CategoryRepository categoryRepository;
    private final FileService fileService;

    public InSpecialistService(InSpecialistRepository inSpecialistRepository, CategoryRepository categoryRepository, FileService fileService) {
        this.inSpecialistRepository = inSpecialistRepository;
        this.categoryRepository = categoryRepository;
        this.fileService = fileService;
    }

    public ApiResponse addInSpecialist(InSpecialistReceiveModel inSpecialistReceiveModel){

        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(inSpecialistReceiveModel.getCategoryId());
        if (optionalCategoryEntity.isEmpty())
            return ERROR_CATEGORY_NOT_FOUND;

        String imageUrl = fileService.saveFile(inSpecialistReceiveModel.getBase64(), inSpecialistReceiveModel.getContentType());
        if (imageUrl == null)
            return ERROR_IMAGE_CREATE;
        InSpecialistEntity inSpecialistEntity = new InSpecialistEntity();
        inSpecialistEntity.setCategoryEntity(optionalCategoryEntity.get());
        inSpecialistEntity.setName(inSpecialistReceiveModel.getName());
        inSpecialistEntity.setImageUrl(imageUrl);
        inSpecialistEntity.setDirection(inSpecialistReceiveModel.getDirection());
        inSpecialistRepository.save(inSpecialistEntity);
        return SUCCESS_V2;
    }

    public ApiResponse getInSpecialistList(){
        SUCCESS.setData(inSpecialistRepository.findAll());
        return SUCCESS;


    }

    public ApiResponse getInSpecialistList(long id){
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(id);
        if (optionalCategoryEntity.isEmpty())
            return ERROR_CATEGORY_NOT_FOUND;
        List<InSpecialistEntity> inSpecialistEntityList = inSpecialistRepository.findByCategoryId(id);
        SUCCESS.setData(inSpecialistEntityList);
        return SUCCESS;

    }


    public ApiResponse updateInSpecialist(long id, InSpecialistEntity inSpecialistEntity) {
        Optional<InSpecialistEntity> optionalInSpecialistEntity = inSpecialistRepository.findById(id);
        if (optionalInSpecialistEntity.isPresent()) {
            InSpecialistEntity updateId = optionalInSpecialistEntity.get();
            Optional<InSpecialistEntity> updateCategory = inSpecialistRepository.findByName(inSpecialistEntity.getName());

            if (updateCategory.isEmpty())
                return USER_EXIST;

            updateId.setName(inSpecialistEntity.getName());
            inSpecialistRepository.save(updateId);
        }
        return SUCCESS_V2;


    }
    public ApiResponse deleteInSpecialist(long id) {
        Optional<InSpecialistEntity> optionalInSpecialistEntity = inSpecialistRepository.findById(id);
        if (optionalInSpecialistEntity.isPresent()) {
            inSpecialistRepository.deleteById(id);

            return SUCCESS_V2;
        }
        return ERROR_DELETE;
    }
}
