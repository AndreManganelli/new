package com.sea.blue.servcie;

import com.sea.blue.constant.ExcelConstant;
import com.sea.blue.dao.SeaBlueProductRepo;
import com.sea.blue.dao.VaccineRepo;
import com.sea.blue.model.SeaBlueProduct;
import com.sea.blue.model.Vaccine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class ReadExcelDataService
{
    @Autowired
    private SeaBlueProductRepo seaBlueProductRepo;

    @Autowired
    private VaccineRepo vaccineRepo;

    @Test
    public void test()
    {
        readExcelData("C:\\Users\\len\\Desktop\\excelData");
    }

    public void readExcelData(String excelPath)
    {
        // judge excel Path is ok
        File importDictionary = new File(excelPath);
        if(!importDictionary.exists() || !importDictionary.getName().equals("excelData"))
        {
            System.out.println("excelPath is not excelData dictionary , current dictionary name: "+ importDictionary.getName() );
            return;
        }

        // read excel by excel name
        extractExcelData(importDictionary);

        // convert data to model
        convertDataToModel();
        // save all model to database
        persistenceAllModel();
    }

    private void persistenceAllModel()
    {
        ExcelManage.getExcelModelList(ExcelConstant.SeaBuleExecl+"One").forEach(model -> {
            seaBlueProductRepo.save((SeaBlueProduct)model);
        });

        ExcelManage.getExcelModelList(ExcelConstant.SeaBuleExecl+"Second").forEach(model ->{
            seaBlueProductRepo.save((SeaBlueProduct) model);
        });

        ExcelManage.getExcelModelList(ExcelConstant.VaccineExcel).forEach(model -> {
            vaccineRepo.save((Vaccine) model);
        });
    }

    private void printAllModelInConsole()
    {
        ExcelManage.getExcelModelList(ExcelConstant.SeaBuleExecl+"One").forEach(model -> {
            System.out.println((SeaBlueProduct)model);
        });
        ExcelManage.getExcelModelList(ExcelConstant.SeaBuleExecl+"Second").forEach(model -> {
            System.out.println((SeaBlueProduct)model);
        });
        ExcelManage.getExcelModelList(ExcelConstant.VaccineExcel).forEach(model -> {
            System.out.println((Vaccine)model);
        });
    }

    private void convertDataToModel()
    {
        // 封装海兰褐 第一部分
        convertSeaBlueOneData(ExcelManage.getExcels().get(ExcelConstant.SeaBuleExecl+"One"));
        // 封装海兰褐 第二部分
        convertSeaBlueSecondData(ExcelManage.getExcels().get(ExcelConstant.SeaBuleExecl + "Second"));
        // 封装疫苗
        convertVaccineData(ExcelManage.getExcels().get(ExcelConstant.VaccineExcel));
    }

    private void convertVaccineData(List<Map<String, String>> dataList)
    {
        dataList.forEach(data ->{
            ExcelManage.addModel(ExcelConstant.VaccineExcel,new Vaccine(data));
        });
    }

    private void convertSeaBlueSecondData(List<Map<String, String>> dataList)
    {
        dataList.forEach(data->{
            ExcelManage.addModel(ExcelConstant.SeaBuleExecl + "Second", new SeaBlueProduct(data));
        });
    }

    private void convertSeaBlueOneData(List<Map<String, String>> dataList)
    {
        dataList.forEach(data->{
            ExcelManage.addModel(ExcelConstant.SeaBuleExecl+"One", new SeaBlueProduct(data));
        });
    }

    private void extractExcelData(File importDictionary)
    {
        // judge file , find file handle to handle, get all data
        File[] files = importDictionary.listFiles();
        List<File> fileAll = Arrays.asList(files);
        fileAll.forEach(file -> {
            if (file.getName().equals(ExcelConstant.EggProducePerformanceStandExcel))
            {
                try
                {
                    ExcelManage.getExcelHandle(file.getName()).read(file);

                } catch (Exception e)
                {
                    System.out.println("read fail " + file.getName());
                    System.out.println(e.getMessage());
                }
            }
        });
        fileAll.forEach(file -> {

            if( !file.getName().contains(".xlsx") || file.getName().contains("~"))
                return;

            if(file.getName().equals(ExcelConstant.EggProducePerformanceStandExcel))
                return;

            try
            {
                ExcelManage.getExcelHandle(file.getName()).read(file);
            } catch (Exception e)
            {
                System.out.println("fail read file " + file.getName());
                e.printStackTrace();
            }
        });
    }
}
