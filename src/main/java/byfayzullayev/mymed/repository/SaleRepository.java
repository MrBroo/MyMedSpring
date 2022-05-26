package byfayzullayev.mymed.repository;

import byfayzullayev.mymed.entity.sale.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

    Optional<SaleEntity> findByName(String name);

    @Query(value = "select t.* from sale_entity t where t.in_sale_entity_id = ?1", nativeQuery = true)
    List<SaleEntity> findByInSaleId(long id);
}
