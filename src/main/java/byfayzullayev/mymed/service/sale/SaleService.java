package byfayzullayev.mymed.service.sale;

import byfayzullayev.mymed.repository.InSaleRepository;
import byfayzullayev.mymed.repository.SaleRepository;
import byfayzullayev.mymed.entity.sale.InSaleEntity;
import byfayzullayev.mymed.entity.sale.SaleEntity;
import byfayzullayev.mymed.model.receive.SaleReceiveModel;
import byfayzullayev.mymed.model.response.ApiResponse;
import byfayzullayev.mymed.service.base.BaseService;
import byfayzullayev.mymed.service.file.FileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService implements BaseService {

    private final SaleRepository saleRepository;
    private final InSaleRepository inSaleRepository;
    private final FileService fileService;

    public SaleService(SaleRepository saleRepository, InSaleRepository inSaleRepository, FileService fileService) {
        this.saleRepository = saleRepository;
        this.inSaleRepository = inSaleRepository;
        this.fileService = fileService;
    }

    public ApiResponse addSale(SaleReceiveModel saleReceiveModel) {
        Optional<InSaleEntity> optionalInSaleEntity = inSaleRepository.findById(saleReceiveModel.getInSaleId());
        if (optionalInSaleEntity.isEmpty())
            return ERROR_CATEGORY_NOT_FOUND;

        String imageUrl = fileService.saveFile(saleReceiveModel.getBase64(), saleReceiveModel.getContentType());
        if (imageUrl == null)
            return ERROR_IMAGE_CREATE;

        SaleEntity saleEntity = new SaleEntity();
        saleEntity.setImageUrl(imageUrl);
        saleEntity.setInSaleEntity(optionalInSaleEntity.get());
        saleEntity.setName(saleReceiveModel.getName());
        saleEntity.setAbout(saleReceiveModel.getAbout());
        saleEntity.setRemainedDate(saleReceiveModel.getRemainedDate());
        saleRepository.save(saleEntity);

        return SUCCESS_V2;

    }

    public ApiResponse getSaleList(){
        SUCCESS.setData(saleRepository.findAll());
        return SUCCESS;
    }


    public ApiResponse getSaleListId(long id){
        Optional<InSaleEntity> optionalInSaleEntity = inSaleRepository.findById(id);
        if (optionalInSaleEntity.isEmpty())
            return ERROR_CATEGORY_NOT_FOUND;

        List<SaleEntity> saleEntityList = saleRepository.findByInSaleId(id);
        SUCCESS.setData(saleEntityList);
        return SUCCESS;
    }


    public ApiResponse updateSale(long id, SaleEntity saleEntity) {
        Optional<SaleEntity> optionalSaleEntity = saleRepository.findById(id);
        if (optionalSaleEntity.isPresent()) {
            SaleEntity updateId = optionalSaleEntity.get();
            Optional<SaleEntity> updateCategory = saleRepository.findByName(saleEntity.getName());

            if (updateCategory.isPresent())
                return USER_EXIST;

            updateId.setName(saleEntity.getName());
            saleRepository.save(updateId);
        }
        return SUCCESS_V2;


    }


    public ApiResponse deleteSale(long id) {
        Optional<SaleEntity> optionalSaleEntity = saleRepository.findById(id);
        if (optionalSaleEntity.isPresent()) {
            saleRepository.deleteById(id);

            return SUCCESS_V2;
        }
        return ERROR_DELETE;
    }
}
