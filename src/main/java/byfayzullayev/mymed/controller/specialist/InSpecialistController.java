package byfayzullayev.mymed.controller.specialist;

import byfayzullayev.mymed.entity.specialist.InSpecialistEntity;
import byfayzullayev.mymed.model.receive.InSpecialistReceiveModel;
import byfayzullayev.mymed.model.response.ApiResponse;
import byfayzullayev.mymed.service.specialist.InSpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/myMed/category/inSpecialist")
public class InSpecialistController {


    private final InSpecialistService inSpecialistService;

    @Autowired
    public InSpecialistController(InSpecialistService inSpecialistService) {
        this.inSpecialistService = inSpecialistService;
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PostMapping("/add")
    public HttpEntity<?> addInSpecialist(@RequestBody InSpecialistReceiveModel inSpecialistReceiveModel) {
        return ResponseEntity.ok(inSpecialistService.addInSpecialist(inSpecialistReceiveModel));
    }

    @CrossOrigin
    @GetMapping("/list")
    public HttpEntity<?> getInSpecialistList(){
        return ResponseEntity.ok(inSpecialistService.getInSpecialistList());
    }


    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> getInSpecialistId(@PathVariable ("id") long id){
        ApiResponse list = inSpecialistService.getInSpecialistList(id);
        return ResponseEntity.ok(list);

    }
    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PutMapping("/update/{id}")
    public HttpEntity<?> updateInSpecialist(@RequestBody InSpecialistEntity inSpecialistEntity, @PathVariable("id") long id){
        return ResponseEntity.ok(inSpecialistService.updateInSpecialist(id, inSpecialistEntity));

    }
    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteInSpecialist(@PathVariable("id") long id) {
        ApiResponse delete = inSpecialistService.deleteInSpecialist(id);
        return ResponseEntity.ok(delete);

    }
}
