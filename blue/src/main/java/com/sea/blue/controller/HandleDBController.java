package com.sea.blue.controller;

import com.sea.blue.servcie.ReadExcelDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HandleDBController
{
    @Autowired
    private ReadExcelDataService excelService;

    @RequestMapping(path="/extractExcelDataToDB")
    public String extractExcelDataToDB()
    {
        excelService.readExcelData("C:\\Users\\len\\Desktop\\excelData");
        return "import excel data to DB";
    }
}
