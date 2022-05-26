package byfayzullayev.mymed.controller.news;

import byfayzullayev.mymed.entity.news.NewsEntity;
import byfayzullayev.mymed.model.receive.NewsReceiveModel;
import byfayzullayev.mymed.model.response.ApiResponse;
import byfayzullayev.mymed.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/myMed/category/inNews/news")
public class NewsController {

    private final NewsService newsService;


    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PostMapping("/add")
    public HttpEntity<?> addNews(@RequestBody NewsReceiveModel newsReceiveModel) {
        return ResponseEntity.ok(newsService.addNews(newsReceiveModel));
    }

    @CrossOrigin
    @GetMapping("/list")
    public HttpEntity<?> getNewsList() {
        return ResponseEntity.ok(newsService.getNewsList());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> getNewsId(@PathVariable ("id") long id) {
        ApiResponse list = newsService.getNewsList(id);
        return ResponseEntity.ok(list);
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PutMapping("/update/{id}")
    public HttpEntity<?> updateNews(@RequestBody NewsEntity newsEntity, @PathVariable("id") long id){
        return ResponseEntity.ok(newsService.updateNews(id, newsEntity));

    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteNews(@PathVariable("id") long id) {
        ApiResponse delete = newsService.deleteNews(id);
        return ResponseEntity.ok(delete);

    }
}
