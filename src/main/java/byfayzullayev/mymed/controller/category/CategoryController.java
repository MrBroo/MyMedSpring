package byfayzullayev.mymed.controller.category;

import byfayzullayev.mymed.entity.category.CategoryEntity;
import byfayzullayev.mymed.model.receive.CategoryReceiveModel;
import byfayzullayev.mymed.model.response.ApiResponse;
import byfayzullayev.mymed.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/myMed/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PostMapping("/add")
    public HttpEntity<?> addCategory(@RequestBody CategoryReceiveModel categoryReceiveModel) {

        return ResponseEntity.ok(categoryService.addCategory(categoryReceiveModel));
    }

    @CrossOrigin
    @GetMapping("/list")
    public HttpEntity<?> getCategoryList() {
        return ResponseEntity.ok(categoryService.getCategoryList());

    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @PutMapping("/update/{id}")
    public HttpEntity<?> updateCategory(@RequestBody CategoryEntity categoryEntity, @PathVariable("id") long id){
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryEntity));

    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteCategory(@PathVariable("id") long id) {
        ApiResponse delete = categoryService.deleteCategory(id);
        return ResponseEntity.ok(delete);

    }
}
