package byfayzullayev.mymed.repository;

import byfayzullayev.mymed.entity.news.InNewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InNewsRepository extends JpaRepository<InNewsEntity, Long> {
    Optional<InNewsEntity> findByName(String name);
    @Query(value = "select t.* from in_news_entity t where t.category_entity_id = ?1", nativeQuery = true)
    List<InNewsEntity> findByCategoryId(long id);
}
