package byfayzullayev.mymed.controller.sale;

import byfayzullayev.mymed.entity.sale.InSaleEntity;
import byfayzullayev.mymed.model.receive.InSaleReceiveModel;
import byfayzullayev.mymed.model.response.ApiResponse;
import byfayzullayev.mymed.service.base.BaseService;
import byfayzullayev.mymed.service.sale.InSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/myMed/category/inSale")
public class InSaleController implements BaseService {

    private final InSaleService inSaleService;

    @Autowired
    public InSaleController(InSaleService inSaleService) {
        this.inSaleService = inSaleService;
    }


    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PostMapping("/add")
    public HttpEntity<?> addInSale(@RequestBody InSaleReceiveModel inSaleReceiveModel) {
        return ResponseEntity.ok(inSaleService.addInSale(inSaleReceiveModel));
    }

    @CrossOrigin
    @GetMapping("/list")
    public HttpEntity<?> getInSaleList() {
        return ResponseEntity.ok(inSaleService.getInSaleList());
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PutMapping("/update/{id}")
    public HttpEntity<?> updateInSale(@RequestBody InSaleEntity inSaleEntity, @PathVariable("id") long id) {
        return ResponseEntity.ok(inSaleService.updateInSale(id, inSaleEntity));

    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteInSale(@PathVariable("id") long id) {
        ApiResponse delete = inSaleService.deleteInSale(id);
        return ResponseEntity.ok(delete);

    }
}
