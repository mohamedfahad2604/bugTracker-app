package com.bizz.customersupport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizz.customersupport.entities.IssueType;

@Repository
public interface IssueTypeRepository extends JpaRepository<IssueType, Long> {

}
