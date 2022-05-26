package byfayzullayev.mymed.controller.specialist;

import byfayzullayev.mymed.entity.specialist.SpecialistEntity;
import byfayzullayev.mymed.model.receive.SpecialistReceiveModel;
import byfayzullayev.mymed.model.response.ApiResponse;
import byfayzullayev.mymed.service.specialist.SpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/myMed/category/inSpecialist/specialist")
public class SpecialistController {

    private final SpecialistService specialistService;

    @Autowired
    public SpecialistController(SpecialistService specialistService) {
        this.specialistService = specialistService;
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PostMapping("/add")
    public HttpEntity<?> addSpecialist(@RequestBody SpecialistReceiveModel specialistReceiveModel) {
        return ResponseEntity.ok(specialistService.addSpecialist(specialistReceiveModel));
    }

    @CrossOrigin
    @GetMapping("/list")
    public HttpEntity<?> getSpecialistList() {
        return ResponseEntity.ok(specialistService.getSpecialistList());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> getSpecialist(@PathVariable ("id") long id) {
        ApiResponse list = specialistService.getSpecialistList(id);
        return ResponseEntity.ok(list);
    }
    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PutMapping("/update/{id}")
    public HttpEntity<?> updateSpecialist(@RequestBody SpecialistEntity specialistEntity, @PathVariable("id") long id) {
        return ResponseEntity.ok(specialistService.updateSpecialist(id, specialistEntity));
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteSpecialist(@PathVariable("id") long id) {
        ApiResponse delete = specialistService.deleteSpecialist(id);
        return ResponseEntity.ok(delete);

    }

}
