package com.itrsa.monolith.repository;

import com.itrsa.monolith.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findRoleById(Long id);
}
