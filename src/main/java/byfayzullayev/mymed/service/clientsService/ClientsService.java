package byfayzullayev.mymed.service.clientsService;

import byfayzullayev.mymed.entity.clients.ClientsEntity;
import byfayzullayev.mymed.model.receive.ClientsReceiveModel;
import byfayzullayev.mymed.model.response.ApiResponse;
import byfayzullayev.mymed.repository.CategoryRepository;
import byfayzullayev.mymed.repository.ClientsRepository;
import byfayzullayev.mymed.service.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ClientsService implements BaseService {

    private final ClientsRepository clientsRepository;
    private final CategoryRepository categoryRepository;

    public ClientsService(ClientsRepository clientsRepository, CategoryRepository categoryRepository) {
        this.clientsRepository = clientsRepository;
        this.categoryRepository = categoryRepository;
    }

    public ApiResponse addClients(ClientsReceiveModel clientsReceiveModel){

        ClientsEntity clientsEntity = new ClientsEntity();
        clientsEntity.setName(clientsReceiveModel.getName());
        clientsEntity.setPhoneNumber(clientsReceiveModel.getPhoneNumber());
        clientsEntity.setBooking(clientsReceiveModel.getBooking());
        clientsRepository.save(clientsEntity);

        return SUCCESS_V2;
    }

    public ApiResponse getClientsList(){
        SUCCESS.setData(categoryRepository.findAll());
        return SUCCESS;
    }

}
