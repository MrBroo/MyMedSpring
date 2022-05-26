package byfayzullayev.mymed.repository;

import byfayzullayev.mymed.entity.category.CategoryEntity;
import byfayzullayev.mymed.entity.services.ServicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface  ServicesRepository extends JpaRepository<ServicesEntity, Long> {

    Optional<ServicesEntity> findByName(String name);
    @Query(value = "select t.* from services_entity t where t.category_services_entity_id = ?1", nativeQuery = true)
    List<ServicesEntity> findByCategoryId(long id);

}
