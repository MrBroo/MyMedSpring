package byfayzullayev.mymed.controller.clientsController;

import byfayzullayev.mymed.model.receive.ClientsReceiveModel;
import byfayzullayev.mymed.service.clientsService.ClientsService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/myMed/clients")
public class ClientsController {
    private final ClientsService clientsService;

    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @CrossOrigin
    @PostMapping("/add")
    public HttpEntity<?> addClients(@RequestBody ClientsReceiveModel clientsReceiveModel){
        return ResponseEntity.ok(clientsService.addClients(clientsReceiveModel));
    }

    @CrossOrigin
    @GetMapping("/list")
    public HttpEntity<?> getClientsList(){
        return ResponseEntity.ok(clientsService.getClientsList());
    }
}
