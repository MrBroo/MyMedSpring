package byfayzullayev.mymed.service.reviews;

import byfayzullayev.mymed.repository.CategoryRepository;
import byfayzullayev.mymed.repository.ReviewsRepository;
import byfayzullayev.mymed.entity.category.CategoryEntity;
import byfayzullayev.mymed.entity.reviews.ReviewsEntity;
import byfayzullayev.mymed.model.receive.ReviewsReceiveModel;
import byfayzullayev.mymed.model.response.ApiResponse;
import byfayzullayev.mymed.service.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ReviewsService implements BaseService {

    private final ReviewsRepository reviewsRepository;
    private final CategoryRepository categoryRepository;

    public ReviewsService(ReviewsRepository reviewsRepository, CategoryRepository categoryRepository) {
        this.reviewsRepository = reviewsRepository;
        this.categoryRepository = categoryRepository;
    }

    public ApiResponse addReviews(
            ReviewsReceiveModel reviewsReceiveModel
    ) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(reviewsReceiveModel.getCategoryId());
        if (optionalCategoryEntity.isEmpty())
            return ERROR_CATEGORY_NOT_FOUND;

        ReviewsEntity reviewsEntity = new ReviewsEntity();
        reviewsEntity.setCategoryEntity(optionalCategoryEntity.get());
        reviewsEntity.setName(reviewsReceiveModel.getName());
        reviewsEntity.setRemainedDate(reviewsReceiveModel.getRemainedDate());
        reviewsEntity.setReviews(reviewsReceiveModel.getReviews());
        reviewsRepository.save(reviewsEntity);
        return SUCCESS_V2;

    }


    public ApiResponse getReviewsList() {
        SUCCESS.setData(reviewsRepository.findAll());
        return SUCCESS;
    }


    public ApiResponse getReviewsList(long id) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(id);
        if (optionalCategoryEntity.isEmpty())
            return ERROR_CATEGORY_NOT_FOUND;

        List<ReviewsEntity> reviewsEntityList = reviewsRepository.findByCategoryId(id);
        SUCCESS.setData(reviewsEntityList);
        return SUCCESS;

    }

    public ApiResponse updateReviews(long id, ReviewsEntity reviewsEntity) {
        Optional<ReviewsEntity> optionalReviewsEntity = reviewsRepository.findById(id);
        if (optionalReviewsEntity.isPresent()) {
            ReviewsEntity updateId = optionalReviewsEntity.get();
            Optional<ReviewsEntity> updateCategory = reviewsRepository.findByName(reviewsEntity.getName());

            if (updateCategory.isPresent())
                return USER_EXIST;

            updateId.setName(reviewsEntity.getName());
            reviewsRepository.save(updateId);
        }
        return SUCCESS_V2;


    }

    public ApiResponse deleteReviews(long id) {
        Optional<ReviewsEntity> optionalReviewsEntity = reviewsRepository.findById(id);
        if (optionalReviewsEntity.isPresent()) {
            reviewsRepository.deleteById(id);

            return SUCCESS_V2;
        }
        return ERROR_DELETE;
    }
}
