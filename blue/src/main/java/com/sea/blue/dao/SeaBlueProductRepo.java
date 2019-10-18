package com.sea.blue.dao;

import com.sea.blue.model.SeaBlueProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeaBlueProductRepo extends CrudRepository<SeaBlueProduct,Long>
{
    @Query("select e from SeaBlueProduct e where e.date = ?1")
    List<SeaBlueProduct> findByDate(String date);

    @Query("select e from SeaBlueProduct e where e.dayAge = ?1")
    List<SeaBlueProduct> findByDayAge(String dayAge);
}
