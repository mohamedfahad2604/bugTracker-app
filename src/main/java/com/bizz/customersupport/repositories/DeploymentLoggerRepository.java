package com.bizz.customersupport.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizz.customersupport.entities.DeploymentPlanner;

@Repository
public interface DeploymentLoggerRepository extends JpaRepository<DeploymentPlanner, Long> {
}
