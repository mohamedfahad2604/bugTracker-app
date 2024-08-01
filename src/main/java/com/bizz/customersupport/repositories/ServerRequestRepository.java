package com.bizz.customersupport.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizz.customersupport.entities.ServerRequestLog;

@Repository
public interface ServerRequestRepository extends JpaRepository<ServerRequestLog, Long> {
}
