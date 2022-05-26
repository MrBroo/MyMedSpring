package byfayzullayev.mymed.controller.sale;

import byfayzullayev.mymed.entity.sale.SaleEntity;
import byfayzullayev.mymed.model.receive.SaleReceiveModel;
import byfayzullayev.mymed.model.response.ApiResponse;
import byfayzullayev.mymed.service.base.BaseService;
import byfayzullayev.mymed.service.sale.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/myMed/category/inSale/sale")
public class SaleController implements BaseService {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService service) {
        this.saleService = service;
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PostMapping("/add")
    public HttpEntity<?> addSale(@RequestBody SaleReceiveModel saleReceiveModel){
        return ResponseEntity.ok(saleService.addSale(saleReceiveModel));

    }

    @CrossOrigin
    @GetMapping("/list")
    public HttpEntity<?> getSaleList(){
        return ResponseEntity.ok(saleService.getSaleList());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> getSaleListId(@PathVariable ("id") long id) {
        ApiResponse list = saleService.getSaleListId(id);
        return ResponseEntity.ok(list);
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PutMapping("/update/{id}")
    public HttpEntity<?> updateSale(@RequestBody SaleEntity saleEntity, @PathVariable("id") long id){
        return ResponseEntity.ok(saleService.updateSale(id, saleEntity));
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteSale(@PathVariable("id") long id) {
        ApiResponse delete = saleService.deleteSale(id);
        return ResponseEntity.ok(delete);

    }
}
