package byfayzullayev.mymed.controller.services;

import byfayzullayev.mymed.entity.services.ServicesEntity;
import byfayzullayev.mymed.model.receive.ServicesReceiveModel;
import byfayzullayev.mymed.model.response.ApiResponse;
import byfayzullayev.mymed.service.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/myMed/category/categoryServices/services")
public class ServicesController {
    private final ServicesService servicesService;

    @Autowired
    public ServicesController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PostMapping("/add")
    public HttpEntity<?> addServices(@RequestBody ServicesReceiveModel servicesReceiveModel) {
        return ResponseEntity.ok(servicesService.addServices(servicesReceiveModel));
    }

    @CrossOrigin
    @GetMapping("/list")
    public HttpEntity<?> getServicesList() {
        return ResponseEntity.ok(servicesService.getServicesList());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> getServicesId(@PathVariable ("id") long id) {
        ApiResponse list = servicesService.getServicesList(id);
        return ResponseEntity.ok(list);
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PutMapping("/update/{id}")
    public HttpEntity<?> updateServices(@RequestBody ServicesEntity servicesEntity, @PathVariable("id") long id) {
        return ResponseEntity.ok(servicesService.updateServices(id, servicesEntity));

    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteServices(@PathVariable("id") long id) {
        ApiResponse delete = servicesService.deleteServices(id);
        return ResponseEntity.ok(delete);

    }
}
