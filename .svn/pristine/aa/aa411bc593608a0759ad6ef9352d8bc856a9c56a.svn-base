package com.bizz.customersupport.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizz.customersupport.entities.DailyTask;
import com.bizz.customersupport.entities.User;

@Repository
public interface DailyTaskRepository extends JpaRepository<DailyTask, Long>{
	public List<DailyTask> findByDate(Date date);
	public List<DailyTask> findByDateAndUserName(Date date,User userName);
	public List<DailyTask> findByDateBetween(Date startDate, Date endDate);
	public List<DailyTask> findByDateBetweenAndUserName(Date startDate, Date endDate,User userName);
	
}
