package byfayzullayev.mymed.controller.reviews;

import byfayzullayev.mymed.entity.reviews.ReviewsEntity;
import byfayzullayev.mymed.model.receive.ReviewsReceiveModel;
import byfayzullayev.mymed.model.response.ApiResponse;
import byfayzullayev.mymed.service.reviews.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/myMed/category/reviews")
public class ReviewsController {

    private final ReviewsService reviewsService;

    @Autowired
    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PostMapping("/add")
    public HttpEntity<?> addReviews(@RequestBody ReviewsReceiveModel reviewsReceiveModel){
        return ResponseEntity.ok(reviewsService.addReviews(reviewsReceiveModel));
    }

    @CrossOrigin
    @GetMapping("/list")
    public HttpEntity<?> getReviewsList(){
        return ResponseEntity.ok(reviewsService.getReviewsList());
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PutMapping("/update/{id}")
    public HttpEntity<?> updateReviews(@RequestBody ReviewsEntity reviewsEntity, @PathVariable("id") long id){
        return ResponseEntity.ok(reviewsService.updateReviews(id, reviewsEntity));

    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteReviews(@PathVariable("id") long id) {
        ApiResponse delete = reviewsService.deleteReviews(id);
        return ResponseEntity.ok(delete);

    }
}
