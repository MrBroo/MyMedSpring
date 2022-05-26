package byfayzullayev.mymed.repository;

import byfayzullayev.mymed.entity.category.CategoryEntity;
import byfayzullayev.mymed.entity.specialist.SpecialistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecialistRepository extends JpaRepository<SpecialistEntity, Long> {

    Optional<SpecialistEntity> findByName(String name);
    @Query(value = "select t.* from specialist_entity t where t.in_specialist_entity_id = ?1", nativeQuery = true)
    List<SpecialistEntity> findByInSpecialistId(long id);
}
