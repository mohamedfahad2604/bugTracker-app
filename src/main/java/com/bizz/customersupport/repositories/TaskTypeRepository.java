package com.bizz.customersupport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizz.customersupport.entities.TaskType;

@Repository
public interface TaskTypeRepository extends JpaRepository<TaskType, Long> {

}
