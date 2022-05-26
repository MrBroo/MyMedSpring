package byfayzullayev.mymed.repository;

import byfayzullayev.mymed.entity.specialist.InSpecialistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InSpecialistRepository extends JpaRepository<InSpecialistEntity, Long> {

    Optional<InSpecialistEntity> findByName(String name);

    @Query(value = "select t.* from in_specialist_entity t where t.category_entity_id = ?1", nativeQuery = true)
    List<InSpecialistEntity> findByCategoryId(long id);

}
