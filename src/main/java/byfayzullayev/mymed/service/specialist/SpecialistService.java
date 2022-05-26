package byfayzullayev.mymed.service.specialist;

import byfayzullayev.mymed.repository.InSpecialistRepository;
import byfayzullayev.mymed.repository.SpecialistRepository;
import byfayzullayev.mymed.entity.specialist.InSpecialistEntity;
import byfayzullayev.mymed.entity.specialist.SpecialistEntity;
import byfayzullayev.mymed.model.receive.SpecialistReceiveModel;
import byfayzullayev.mymed.model.response.ApiResponse;
import byfayzullayev.mymed.service.base.BaseService;
import byfayzullayev.mymed.service.file.FileService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialistService implements BaseService {


    private final SpecialistRepository specialistRepository;
    private final InSpecialistRepository inSpecialistRepository;
    private final FileService fileService;

    public SpecialistService(SpecialistRepository specialistRepository, InSpecialistRepository inSpecialistRepository, FileService fileService) {
        this.specialistRepository = specialistRepository;
        this.inSpecialistRepository = inSpecialistRepository;
        this.fileService = fileService;
    }

    public ApiResponse addSpecialist(SpecialistReceiveModel specialistReceiveModel) {
        Optional<InSpecialistEntity> optionalInSpecialistEntity = inSpecialistRepository.findById(specialistReceiveModel.getCategoryId());
        if (optionalInSpecialistEntity.isEmpty())
            return ERROR_CATEGORY_NOT_FOUND;
        String imageUrl = fileService.saveFile(specialistReceiveModel.getBase64(), specialistReceiveModel.getContentType());
        if (imageUrl == null)
            return ERROR_IMAGE_CREATE;

        SpecialistEntity specialistEntity = new SpecialistEntity();
        specialistEntity.setInSpecialistEntity(optionalInSpecialistEntity.get());
        specialistEntity.setName(specialistReceiveModel.getName());
        specialistEntity.setSeniority(specialistReceiveModel.getSeniority());
        specialistEntity.setCategory(specialistReceiveModel.getCategory());
        specialistEntity.setEducation(specialistReceiveModel.getEducation());
        specialistEntity.setEmploymentHistory(specialistReceiveModel.getEmploymentHistory());
        specialistEntity.setImageUrl(imageUrl);
        specialistRepository.save(specialistEntity);
        return SUCCESS_V2;

    }

    public ApiResponse getSpecialistList() {
        SUCCESS.setData(specialistRepository.findAll());
        return SUCCESS;
    }

    public ApiResponse getSpecialistList(long id) {
        Optional<InSpecialistEntity> optionalInSpecialistEntity = inSpecialistRepository.findById(id);
        if (optionalInSpecialistEntity.isEmpty())
            return ERROR_CATEGORY_NOT_FOUND;


        List<SpecialistEntity> specialistEntityList = specialistRepository.findByInSpecialistId(id);
        SUCCESS.setData(specialistEntityList);
        return SUCCESS;

    }

    public ApiResponse updateSpecialist(long id, SpecialistEntity specialistEntity) {
        Optional<SpecialistEntity> optionalSpecialistEntity = specialistRepository.findById(id);
        if (optionalSpecialistEntity.isPresent()) {
            SpecialistEntity updateId = optionalSpecialistEntity.get();
            Optional<SpecialistEntity> updateCategory = specialistRepository.findByName(specialistEntity.getName());

            if (updateCategory.isEmpty())
                return USER_EXIST;

            updateId.setName(specialistEntity.getName());
            specialistRepository.save(updateId);
        }
        return SUCCESS_V2;


    }

    public ApiResponse deleteSpecialist(long id) {
        Optional<SpecialistEntity> optionalSpecialistEntity = specialistRepository.findById(id);
        if (optionalSpecialistEntity.isPresent()) {
            specialistRepository.deleteById(id);

            return SUCCESS_V2;
        }
        return ERROR_DELETE;
    }

}
