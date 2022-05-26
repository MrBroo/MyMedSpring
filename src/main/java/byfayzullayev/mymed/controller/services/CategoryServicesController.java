package byfayzullayev.mymed.controller.services;


import byfayzullayev.mymed.entity.services.CategoryServicesEntity;
import byfayzullayev.mymed.model.receive.CategoryServicesReceiveModel;
import byfayzullayev.mymed.model.response.ApiResponse;
import byfayzullayev.mymed.service.service.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/myMed/category/categoryServices")
public class CategoryServicesController {

    private final CategoryServices categoryServices;

    @Autowired
    public CategoryServicesController(CategoryServices categoryServices) {
        this.categoryServices = categoryServices;
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PostMapping("/add")
    public HttpEntity<?> addCategoryServices(@RequestBody CategoryServicesReceiveModel categoryServicesReceiveModel) {
        return ResponseEntity.ok(categoryServices.addCategoryServices(categoryServicesReceiveModel));
    }

    @CrossOrigin
    @GetMapping("/list")
    public HttpEntity<?> getCategoryServicesList() {
        return ResponseEntity.ok(categoryServices.getCategoryServicesList());
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PutMapping("/update/{id}")
    public HttpEntity<?> updateCategoryServices(@RequestBody CategoryServicesEntity categoryServicesEntity, @PathVariable("id") long id) {
        return ResponseEntity.ok(categoryServices.updateCategoryServices(id, categoryServicesEntity));
    }


    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteCategoryServices(@PathVariable("id") long id) {
        ApiResponse delete = categoryServices.deleteCategoryServices(id);
        return ResponseEntity.ok(delete);

    }

}
