package byfayzullayev.mymed.repository;

import byfayzullayev.mymed.entity.sale.InSaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InSaleRepository extends JpaRepository<InSaleEntity, Long> {

    Optional<InSaleEntity> findByName(String name);

    @Query(value = "select t.* from in_sale_entity t where t.category_entity_id = ?1", nativeQuery = true)
    List<InSaleEntity> findByCategoryId(long id);
}
