package byfayzullayev.mymed.repository;


import byfayzullayev.mymed.entity.category.CategoryEntity;
import byfayzullayev.mymed.entity.reviews.ReviewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewsRepository extends JpaRepository<ReviewsEntity, Long> {

    Optional<ReviewsEntity> findByName(String name);
    @Query(value = "select t.* from reviews_entity t where t.category_entity_id = ?1 ",nativeQuery = true)
    List<ReviewsEntity> findByCategoryId(long id);
}
