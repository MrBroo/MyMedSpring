package byfayzullayev.mymed.service.service;

import byfayzullayev.mymed.repository.CategoryServicesRepository;
import byfayzullayev.mymed.repository.ServicesRepository;
import byfayzullayev.mymed.entity.services.CategoryServicesEntity;
import byfayzullayev.mymed.entity.services.ServicesEntity;
import byfayzullayev.mymed.model.receive.ServicesReceiveModel;
import byfayzullayev.mymed.model.response.ApiResponse;
import byfayzullayev.mymed.service.base.BaseService;
import byfayzullayev.mymed.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesService implements BaseService {

    private final ServicesRepository servicesRepository;
    private final CategoryServicesRepository categoryServicesRepository;
    private final FileService fileService;

    @Autowired
    public ServicesService(ServicesRepository servicesRepository, CategoryServicesRepository categoryServicesRepository, FileService fileService) {
        this.servicesRepository = servicesRepository;
        this.categoryServicesRepository = categoryServicesRepository;
        this.fileService = fileService;
    }

    public ApiResponse addServices(ServicesReceiveModel servicesReceiveModel) {
        Optional<CategoryServicesEntity> optionalCategoryServicesEntity = categoryServicesRepository.findById(servicesReceiveModel.getCategoryId());
        if (optionalCategoryServicesEntity.isEmpty())
            return ERROR_CATEGORY_NOT_FOUND;

        String imageUrl = fileService.saveFile(servicesReceiveModel.getBase64(), servicesReceiveModel.getContentType());
        if (imageUrl == null)
            return ERROR_IMAGE_CREATE;

        ServicesEntity servicesEntity = new ServicesEntity();
        servicesEntity.setCategoryServicesEntity(optionalCategoryServicesEntity.get());
        servicesEntity.setName(servicesReceiveModel.getName());
        servicesEntity.setAbout(servicesReceiveModel.getAbout());
        servicesEntity.setAllDefinition(servicesReceiveModel.getAllDefinition());
        servicesEntity.setImageUrl(imageUrl);
        servicesRepository.save(servicesEntity);
        return SUCCESS_V2;
    }


    public ApiResponse getServicesList() {
        SUCCESS.setData(servicesRepository.findAll());
        return SUCCESS;
    }

    public ApiResponse getServicesList(long id) {
        Optional<CategoryServicesEntity> optionalCategoryServicesEntity = categoryServicesRepository.findById(id);
        if (optionalCategoryServicesEntity.isEmpty())
            return ERROR_CATEGORY_NOT_FOUND;

        List<ServicesEntity> servicesEntityList = servicesRepository.findByCategoryId(id);
        SUCCESS.setData(servicesEntityList);
        return SUCCESS;
    }

    public ApiResponse updateServices(long id, ServicesEntity servicesEntity) {
        Optional<ServicesEntity> optionalServicesEntity = servicesRepository.findById(id);
        if (optionalServicesEntity.isPresent()) {
            ServicesEntity updateId = optionalServicesEntity.get();
            Optional<ServicesEntity> updateCategory = servicesRepository.findByName(servicesEntity.getName());

            if (updateCategory.isPresent())
                return USER_EXIST;

            updateId.setName(servicesEntity.getName());
            servicesRepository.save(updateId);
        }
        return SUCCESS_V2;


    }


    public ApiResponse deleteServices(long id) {
        Optional<ServicesEntity> optionalServicesEntity = servicesRepository.findById(id);
        if (optionalServicesEntity.isPresent()) {
            servicesRepository.deleteById(id);

            return SUCCESS_V2;
        }
        return ERROR_DELETE;
    }
}
