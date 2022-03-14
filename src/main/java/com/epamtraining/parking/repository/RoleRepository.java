package com.epamtraining.parking.repository;

import com.epamtraining.parking.domain.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);

    @Override
    void delete(RoleEntity role);
}
