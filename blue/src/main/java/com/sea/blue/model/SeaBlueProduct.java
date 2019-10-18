package com.sea.blue.model;

import com.sea.blue.constant.ExcelConstant;

import javax.persistence.*;
import java.util.Map;

import static com.sea.blue.constant.ExcelConstant.*;

@Entity
@Table(name="SeaBlueProduct")
public class SeaBlueProduct implements GEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "stage")
    private String stage;
    @Column(name = "weekAge")
    private String weekAge;
    @Column(name = "dayAge")
    private String dayAge;
    @Column(name = "date")
    private String date;
    @Column(name = "herds")
    private String herds;
    @Column(name = "weekOutRate")
    private String weekOutRate;
    @Column(name = "totalWeekOutNumber")
    private String totalWeekOutNumber;
    @Column(name = "dayFeedStand")
    private String dayFeedStand;  // g/只/日
    @Column(name = "totalDayFeed")
    private String totalDayFeed; // kg
    @Column(name = "weight")
    private String weight;  // g/只
    @Column(name = "uniformity")
    private String uniformity;  // %

    // 产蛋期
    @Column(name = "eggProduceRate")
    private String eggProduceRate;
    @Column(name = "eggProduceNumber")
    private String eggProduceNumber;
    @Column(name = "eggWeight")
    private String eggWeight;

    // 暂时还没有的费用
    @Column(name = "vaccineCost")
    private String vaccineCost;
    @Column(name = "preventiveCost")
    private String preventiveCost;
    @Column(name = "veterinaryDrugCost")
    private String veterinaryDrugCost;
    @Column(name = "materialCost")
    private String materialCost;
    @Column(name = "maintainCost")
    private String maintainCost;

    @Column(name = "rowNumber")
    private String rowNumber;

    public SeaBlueProduct()
    {
    }

    public SeaBlueProduct(Map<String, String> data)
    {
        int rowNumber = Integer.parseInt(data.get(ExcelConstant.rowNumber));
        if (rowNumber <= 84)
        {
            stage = "育雏期";
        }else if(rowNumber<140)
        {
            stage = "成长期";
        }else
        {
            stage = "产蛋期";
        }
        weekAge = data.get(WeekAge).replace(".0","");
        dayAge = data.get(DayAge).replace(".0","");
        date = numberConvertDate(data.get(Date));
        herds = data.get(Herds);
        weekOutRate = data.get(WeekOutRate); // no
        totalWeekOutNumber = data.get(TotalWeekOutNumber);
        dayFeedStand = data.get(DayFeedStand);
        totalDayFeed = data.get(TotalDayFeed);
        weight = data.get(Weight);  // not extract value  , it's null;
        this.uniformity = data.get(Uniformity);
        this.eggProduceRate = data.get(EggProduceRate);
        this.eggProduceNumber = data.get(EggProduceNumber);
        this.eggWeight = data.get(EggWeight);
        vaccineCost = data.get(VaccineCost);
        preventiveCost = data.get(PreventiveCost);
        veterinaryDrugCost = data.get(VeterinaryDrugCost);
        materialCost = data.get(MaterialCost);
        maintainCost = data.get(MaintainCost);

        this.rowNumber = data.get(ExcelConstant.rowNumber);
    }

    public SeaBlueProduct(String stage, String weekAge, String dayAge, String date, String herds, String weekOutRate, String totalWeekOutNumber, String dayFeed, String totalDayFeed, String weight, String uniformity, String eggProduceRate, String eggProduceNumber, String eggWeight, String vaccineCost, String preventiveCost, String veterinaryDrugCost, String materialCost, String maintainCost)
    {
        this.stage = stage;
        this.weekAge = weekAge;
        this.dayAge = dayAge;
        this.date = date;
        this.herds = herds;
        this.weekOutRate = weekOutRate;
        this.totalWeekOutNumber = totalWeekOutNumber;
        this.dayFeedStand = dayFeed;
        this.totalDayFeed = totalDayFeed;
        this.weight = weight;
        this.uniformity = uniformity;
        this.eggProduceRate = eggProduceRate;
        this.eggProduceNumber = eggProduceNumber;
        this.eggWeight = eggWeight;
        this.vaccineCost = vaccineCost;
        this.preventiveCost = preventiveCost;
        this.veterinaryDrugCost = veterinaryDrugCost;
        this.materialCost = materialCost;
        this.maintainCost = maintainCost;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getStage()
    {
        return stage;
    }

    public void setStage(String stage)
    {
        this.stage = stage;
    }

    public String getWeekAge()
    {
        return weekAge;
    }

    public void setWeekAge(String weekAge)
    {
        this.weekAge = weekAge;
    }

    public String getDayAge()
    {
        return dayAge;
    }

    public void setDayAge(String dayAge)
    {
        this.dayAge = dayAge;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getHerds()
    {
        return herds;
    }

    public void setHerds(String herds)
    {
        this.herds = herds;
    }

    public String getWeekOutRate()
    {
        return weekOutRate;
    }

    public void setWeekOutRate(String weekOutRate)
    {
        this.weekOutRate = weekOutRate;
    }

    public String getTotalWeekOutNumber()
    {
        return totalWeekOutNumber;
    }

    public void setTotalWeekOutNumber(String totalWeekOutNumber)
    {
        this.totalWeekOutNumber = totalWeekOutNumber;
    }

    public String getDayFeedStand()
    {
        return dayFeedStand;
    }

    public void setDayFeedStand(String dayFeedStand)
    {
        this.dayFeedStand = dayFeedStand;
    }

    public String getTotalDayFeed()
    {
        return totalDayFeed;
    }

    public void setTotalDayFeed(String totalDayFeed)
    {
        this.totalDayFeed = totalDayFeed;
    }

    public String getWeight()
    {
        return weight;
    }

    public void setWeight(String weight)
    {
        this.weight = weight;
    }

    public String getUniformity()
    {
        return uniformity;
    }

    public void setUniformity(String uniformity)
    {
        this.uniformity = uniformity;
    }

    public String getEggProduceRate()
    {
        return eggProduceRate;
    }

    public void setEggProduceRate(String eggProduceRate)
    {
        this.eggProduceRate = eggProduceRate;
    }

    public String getEggProduceNumber()
    {
        return eggProduceNumber;
    }

    public void setEggProduceNumber(String eggProduceNumber)
    {
        this.eggProduceNumber = eggProduceNumber;
    }

    public String getEggWeight()
    {
        return eggWeight;
    }

    public void setEggWeight(String eggWeight)
    {
        this.eggWeight = eggWeight;
    }

    public String getVaccineCost()
    {
        return vaccineCost;
    }

    public void setVaccineCost(String vaccineCost)
    {
        this.vaccineCost = vaccineCost;
    }

    public String getPreventiveCost()
    {
        return preventiveCost;
    }

    public void setPreventiveCost(String preventiveCost)
    {
        this.preventiveCost = preventiveCost;
    }

    public String getVeterinaryDrugCost()
    {
        return veterinaryDrugCost;
    }

    public void setVeterinaryDrugCost(String veterinaryDrugCost)
    {
        this.veterinaryDrugCost = veterinaryDrugCost;
    }

    public String getMaterialCost()
    {
        return materialCost;
    }

    public void setMaterialCost(String materialCost)
    {
        this.materialCost = materialCost;
    }

    public String getMaintainCost()
    {
        return maintainCost;
    }

    public void setMaintainCost(String maintainCost)
    {
        this.maintainCost = maintainCost;
    }

    @Override
    public String toString()
    {
        return "行号: "+ rowNumber+"\nSeaBlueProduct{" + "id=" + id + ", stage='" + stage + '\'' + ", weekAge='" + weekAge + '\'' + ", dayAge='" + dayAge + '\'' + ", date='" + date + '\'' + ", herds='" + herds + '\'' + ", weekOutRate='" + weekOutRate + '\'' + ", totalWeekOutNumber='" + totalWeekOutNumber + '\'' + ", dayFeedStand='" + dayFeedStand + '\'' + ", totalDayFeed='" + totalDayFeed + '\'' + ", weight='" + weight + '\'' + ", uniformity='" + uniformity + '\'' + ", eggProduceRate='" + eggProduceRate + '\'' + ", eggProduceNumber='" + eggProduceNumber + '\'' + ", eggWeight='" + eggWeight + '\'' + ", vaccineCost='" + vaccineCost + '\'' + ", preventiveCost='" + preventiveCost + '\'' + ", veterinaryDrugCost='" + veterinaryDrugCost + '\'' + ", materialCost='" + materialCost + '\'' + ", maintainCost='" + maintainCost + '\'' + ", rowNumber='" + rowNumber + '\'' + '}';
    }
}
