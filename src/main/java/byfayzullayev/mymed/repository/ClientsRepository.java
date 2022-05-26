package byfayzullayev.mymed.repository;

import byfayzullayev.mymed.entity.clients.ClientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<ClientsEntity, Long> {
}
