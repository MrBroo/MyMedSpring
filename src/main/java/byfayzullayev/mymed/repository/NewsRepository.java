package byfayzullayev.mymed.repository;

import byfayzullayev.mymed.entity.news.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    Optional<NewsEntity> findByName(String name);

    @Query(value = "select t.* from news_entity t where t.in_news_entity_id = ?1 ",nativeQuery = true)
    List<NewsEntity> findByInNewsId(long id);
}
