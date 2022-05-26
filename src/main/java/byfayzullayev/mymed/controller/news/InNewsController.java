package byfayzullayev.mymed.controller.news;

import byfayzullayev.mymed.entity.news.InNewsEntity;
import byfayzullayev.mymed.model.receive.InNewsReceiveModel;
import byfayzullayev.mymed.model.response.ApiResponse;
import byfayzullayev.mymed.service.news.InNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/myMed/category/inNews")
public class InNewsController {

    private final InNewsService inNewsService;

    @Autowired
    public InNewsController(InNewsService inNewsService) {
        this.inNewsService = inNewsService;
    }


    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PostMapping("/add")
    public HttpEntity<?> addInNews(@RequestBody InNewsReceiveModel inNewsReceiveModel){
        return ResponseEntity.ok(inNewsService.addInNews(inNewsReceiveModel));
    }

    @CrossOrigin
    @GetMapping("/list")
    public HttpEntity<?> getInNewsList(){
        return ResponseEntity.ok(inNewsService.getInNewsList());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> getInNewsId(@PathVariable ("id") long id) {
        ApiResponse list = inNewsService.getInNewsList(id);
        return ResponseEntity.ok(list);
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PutMapping("/update/{id}")
    public HttpEntity<?> updateInNews(@RequestBody InNewsEntity inNewsEntity, @PathVariable("id") long id){
        return ResponseEntity.ok(inNewsService.updateInNews(id, inNewsEntity));

    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteInNews(@PathVariable("id") long id) {
        ApiResponse delete = inNewsService.deleteInNews(id);
        return ResponseEntity.ok(delete);

    }
}
