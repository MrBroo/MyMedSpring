package byfayzullayev.mymed.repository;

import byfayzullayev.mymed.entity.role.RoleEntity;
import byfayzullayev.mymed.entity.role.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByRoleEnum(RoleEnum roleEnum);
}
