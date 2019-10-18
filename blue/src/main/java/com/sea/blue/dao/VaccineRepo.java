package com.sea.blue.dao;

import com.sea.blue.model.Vaccine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineRepo extends CrudRepository<Vaccine,Integer>
{
    @Query("select e from Vaccine e where e.procedureNumber = ?1")
    List<Vaccine> findByStep(String procedureNumber);
}
