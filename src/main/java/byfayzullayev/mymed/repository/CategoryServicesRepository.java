package byfayzullayev.mymed.repository;


import byfayzullayev.mymed.entity.services.CategoryServicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryServicesRepository extends JpaRepository<CategoryServicesEntity, Long> {

    Optional<CategoryServicesEntity> findByName(String name);

    @Query(value = "select t.* from category_services_entity t where t.category_entity_id = ?1", nativeQuery = true)
    List<CategoryServicesEntity> findByCategoryId(long id);
}
