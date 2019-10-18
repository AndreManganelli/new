package com.sea.blue.model;

import com.sea.blue.constant.ExcelConstant;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Map;

import static com.sea.blue.constant.ExcelConstant.*;

@Entity
@Table(name = "vaccine")
public class Vaccine implements GEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "procedureNumber")
    private String procedureNumber;
    @Column(name = "dayAge")
    private String dayAge;
    @Column(name = "type")
    private String type;
    @Column(name = "detail")
    private String detail;
    @Column(name = "seedingPlants")
    private String seedingPlants;
    @Column(name = "LorK")
    private String LorK;
    @Column(name = "dosage")
    private String dosage;
    @Column(name = "useMethod")
    private String useMethod;
    @Column(name = "other")
    private String other;
    @Column(name = "price")
    private String price;

    @Column(name = "rowNumber")
    private String rowNumber;

    public Vaccine()
    {
    }

    public Vaccine(Map<String, String> data)
    {
        procedureNumber = data.get(ProcedureNumber);
        dayAge = data.get(DayAge);
        type = data.get(Type);
        detail = data.get(Detail);
        seedingPlants = data.get(SeedingPlants);
        LorK = data.get(ExcelConstant.LorK);
        dosage = data.get(Dosage);
        useMethod = data.get(UseMethod);
        other = data.get(Other);
        price = data.get(Price);

        rowNumber = data.get(ExcelConstant.rowNumber);
    }

    public Vaccine(long id, String procedure, String dayAge, String type, String detail, String seedingPlants, String lorK, String dosage, String useMethod, String other, String price)
    {
        this.id = id;
        this.procedureNumber = procedure;
        this.dayAge = dayAge;
        this.type = type;
        this.detail = detail;
        this.seedingPlants = seedingPlants;
        LorK = lorK;
        this.dosage = dosage;
        this.useMethod = useMethod;
        this.other = other;
        this.price = price;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getProcedureNumber()
    {
        return procedureNumber;
    }

    public void setProcedureNumber(String procedureNumber)
    {
        this.procedureNumber = procedureNumber;
    }

    public String getDayAge()
    {
        return dayAge;
    }

    public void setDayAge(String dayAge)
    {
        this.dayAge = dayAge;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getDetail()
    {
        return detail;
    }

    public void setDetail(String detail)
    {
        this.detail = detail;
    }

    public String getSeedingPlants()
    {
        return seedingPlants;
    }

    public void setSeedingPlants(String seedingPlants)
    {
        this.seedingPlants = seedingPlants;
    }

    public String getLorK()
    {
        return LorK;
    }

    public void setLorK(String lorK)
    {
        LorK = lorK;
    }

    public String getDosage()
    {
        return dosage;
    }

    public void setDosage(String dosage)
    {
        this.dosage = dosage;
    }

    public String getUseMethod()
    {
        return useMethod;
    }

    public void setUseMethod(String useMethod)
    {
        this.useMethod = useMethod;
    }

    public String getOther()
    {
        return other;
    }

    public void setOther(String other)
    {
        this.other = other;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    @Override
    public String toString()
    {
        return "行号: " + rowNumber + "\nVaccine{" + "id=" + id + ", procedureNumber='" + procedureNumber + '\'' + ", dayAge='" + dayAge + '\'' + ", type='" + type + '\'' + ", detail='" + detail + '\'' + ", seedingPlants='" + seedingPlants + '\'' + ", LorK='" + LorK + '\'' + ", dosage='" + dosage + '\'' + ", useMethod='" + useMethod + '\'' + ", other='" + other + '\'' + ", price='" + price + '\'' + ", rowNumber='" + rowNumber + '\'' + '}';
    }
}
