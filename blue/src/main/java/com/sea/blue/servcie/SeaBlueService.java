package com.sea.blue.servcie;

import com.sea.blue.dao.SeaBlueProductRepo;
import com.sea.blue.model.SeaBlueProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class SeaBlueService
{
    @Autowired
    private SeaBlueProductRepo seaBlueDao;

    public List<SeaBlueProduct> getAllSeaBlue()
    {
        return (List<SeaBlueProduct>) seaBlueDao.findAll();
    }

    public List<String> getAllDateList()
    {
        Iterable<SeaBlueProduct> all = seaBlueDao.findAll();
        List<String> dateList = new LinkedList<>();
        for (SeaBlueProduct seaBlueProduct : all)
        {
            dateList.add(seaBlueProduct.getDate());
        }
        return dateList;
    }

    public List<String> getAllHerdsList()
    {
        Iterable<SeaBlueProduct> all = seaBlueDao.findAll();
        List<String> dateList = new LinkedList<>();
        for (SeaBlueProduct seaBlueProduct : all)
        {
            dateList.add(seaBlueProduct.getHerds());
        }
        return dateList;
    }

    public SeaBlueProduct findByDate(String date)
    {
        List<SeaBlueProduct> seaBlueProductList = seaBlueDao.findByDate(date);
        if(seaBlueProductList == null || seaBlueProductList.size() == 0)
            return new SeaBlueProduct();
        return seaBlueProductList.get(0);
    }

    public List<String> getAllFeedList() {
        Iterable<SeaBlueProduct> all = seaBlueDao.findAll();
        List<String> dateList = new LinkedList<>();
        for (SeaBlueProduct seaBlueProduct : all)
        {
            dateList.add(seaBlueProduct.getDayFeedStand());
        }
        return dateList;
    }

    public List<String> getAllFeedTotalList() {
        Iterable<SeaBlueProduct> all = seaBlueDao.findAll();
        List<String> dateList = new LinkedList<>();
        for (SeaBlueProduct seaBlueProduct : all)
        {
            dateList.add(seaBlueProduct.getTotalDayFeed());
        }
        return dateList;
    }

    public List<String> getAllEggProduceRate() {
        Iterable<SeaBlueProduct> all = seaBlueDao.findAll();
        List<String> dateList = new LinkedList<>();
        for (SeaBlueProduct seaBlueProduct : all)
        {
            dateList.add(seaBlueProduct.getEggProduceRate());
        }
        return dateList;
    }

    public List<String> getAllWeekOutNumberList() {
        Iterable<SeaBlueProduct> all = seaBlueDao.findAll();
        List<String> dateList = new LinkedList<>();
        for (SeaBlueProduct seaBlueProduct : all)
        {
            dateList.add(seaBlueProduct.getTotalWeekOutNumber());
        }
        return dateList;
    }

    public SeaBlueProduct findByDayAge(String dayAge) {
        List<SeaBlueProduct> seaBlueProductList = seaBlueDao.findByDayAge(dayAge);
        if(seaBlueProductList == null || seaBlueProductList.size() == 0)
            return new SeaBlueProduct();
        return seaBlueProductList.get(0);
    }

    public void saveSeaBlueProduct(SeaBlueProduct seaBlueProduct) {
        seaBlueDao.save(seaBlueProduct);
    }
}
