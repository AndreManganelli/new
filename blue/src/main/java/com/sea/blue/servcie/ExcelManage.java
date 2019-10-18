package com.sea.blue.servcie;

import com.sea.blue.constant.ExcelConstant;
import com.sea.blue.model.GEntity;
import com.sea.blue.model.SeaBlueProduct;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.sea.blue.constant.ExcelConstant.*;

public class ExcelManage
{

    private static FormulaEvaluator formulaEvaluator;
    private static Map<String, List<Map<String, String>>> excels;
    private static Map<String,ExcelHandle> excelHandleFactory;
    private static Map<String,List> excelModelListMap;

    static{
        // excel all data
        excels = new HashMap<>();

        // excel handle factory
        excelHandleFactory = new HashMap<>();
        excelHandleFactory.put(SeaBuleExecl, new SeaBuleExcelHandle());
        excelHandleFactory.put(VaccineExcel, new VaccineExcelHandle());
        excelHandleFactory.put(EggProducePerformanceStandExcel, new EggProducePerformanceStandExcelHandle());

        // excel all data convert all model
        excelModelListMap = new HashMap<>();
        excelModelListMap.put(SeaBuleExecl + "One", new LinkedList());
        excelModelListMap.put(SeaBuleExecl + "Second", new LinkedList());
        excelModelListMap.put(VaccineExcel, new LinkedList());
    }

    public static Map<String, List<Map<String, String>>> getExcels()
    {
        return excels;
    }

    public static ExcelHandle getExcelHandle(String excelName)
    {
        return excelHandleFactory.get(excelName);
    }

    public static void addModel(String excelName, GEntity seaBlueProduct)
    {
        List list = excelModelListMap.get(excelName);
        if (list == null)
        {
            System.out.println(excelName + " is null");
            return;
        }
        list.add(seaBlueProduct);
    }

    public static List<GEntity> getExcelModelList(String excelName)
    {
        return excelModelListMap.get(excelName);
    }

    static class SeaBuleExcelHandle implements ExcelHandle
    {

        @Override
        public List<Map<String, String>> read(File file) throws Exception
        {
            LinkedList<Map<String, String>> dataListOne = new LinkedList<>();
            LinkedList<Map<String, String>> dataListSecond = new LinkedList<>();

            excels.put(file.getName()+"One", dataListOne);
            excels.put(file.getName()+"Second", dataListSecond);


            Workbook workbook = new XSSFWorkbook(file);
            FormulaEvaluator  formulaEvaluator =  new XSSFFormulaEvaluator((XSSFWorkbook) workbook);

            Sheet sheet = workbook.getSheetAt(0);

            // get key of map one session
            List<String> keyListOne = new LinkedList<>();
            sheet.getRow(20).forEach(cell -> {
                keyListOne.add(getCellValueFormula(cell,formulaEvaluator));
            });

            // get key of map two session
            List<String> keyListSecond = new LinkedList<>();
            sheet.getRow(140).forEach(cell -> {
                keyListSecond.add(getCellValueFormula(cell,formulaEvaluator));
            });

            for(Row row : sheet)
            {
                if (row.getRowNum() < 21)
                    continue;

                if (row.getRowNum() < 140)
                {
                    Map<String,String> data = new HashMap<>();
                    data.put(rowNumber, row.getRowNum() + 1 +"");      // record row number of the data in excel
                    for(int i=0; i<keyListOne.size(); i++)
                    {
                        Cell cell = row.getCell(i);
                        data.put(keyListOne.get(i),cell==null?"": getCellValueFormula(cell,formulaEvaluator));
                    }

                    dataListOne.add(data);
                }

                if (row.getRowNum() > 140)
                {
                    Map<String,String> data = new HashMap<>();
                    data.put(rowNumber, row.getRowNum() + 1 +"");      // record row number of the data in excel
                    for(int i=0; i<keyListSecond.size(); i++)
                    {
                        Cell cell = row.getCell(i);
                        if (Integer.parseInt(data.get(rowNumber)) >162)
                        {
                            List<Map<String,String>> dataList = ExcelManage.getExcels().get(EggProducePerformanceStandExcel);

                            final int specificNumber = i;

                            if (cell == null)
                                System.out.println(data.get(rowNumber) + " cell is null");

                            if (cell != null &&cell.getColumnIndex() == 9 && Integer.parseInt(data.get(rowNumber)) <582)
                            {
                                dataList.forEach(eggData ->
                                {
                                    String weekAge = eggData.get(ExcelConstant.WeekAge);
                                    if (weekAge.replace(".00","").replace(".0", "").equals(data.get(ExcelConstant.WeekAge).replace(".00","").replace(".0","")))
                                    {
                                        int rate = Integer.parseInt(eggData.get(ExcelConstant.EggProduceRateDay).replace(".00", "").replace(".0", "")) + 2;
                                        data.put(keyListSecond.get(specificNumber), "" + rate);
                                    }
                                });
                                continue;
                            }

                            if(cell != null &&cell.getColumnIndex() == 10 && Integer.parseInt(data.get(rowNumber)) <582)
                            {
                                dataList.forEach(eggData ->
                                {
                                    if (eggData.get(ExcelConstant.WeekAge).replace(".00","").replace(".0", "").equals(data.get(ExcelConstant.WeekAge).replace(".00","").replace(".0","")))
                                    {
                                        int rate = Integer.parseInt(data.get(ExcelConstant.EggProduceRate));
                                        int number = (int) Double.parseDouble(data.get(ExcelConstant.Herds));
                                        String value = rate * number/100 + "";
                                        data.put(keyListSecond.get(specificNumber), value);
                                    }
                                });
                                continue;
                            }

                            data.put(keyListSecond.get(i), cell==null?"": getCellValueFormula(cell,formulaEvaluator));
                        }else
                        {
                            data.put(keyListSecond.get(i), cell==null?"": getCellValueFormula(cell,formulaEvaluator));
                        }
                    }

                    dataListSecond.add(data);
                }
            }
            return null;
        }
    }

    static class VaccineExcelHandle implements ExcelHandle
    {
        @Override
        public List<Map<String, String>> read(File file) throws Exception
        {
            LinkedList<Map<String, String>> dataList = new LinkedList<>();
            excels.put(file.getName(), dataList);
            Workbook workbook = new XSSFWorkbook(file);
            FormulaEvaluator  formulaEvaluator =  new XSSFFormulaEvaluator((XSSFWorkbook) workbook);

            DataFormatter dataFormatter = new DataFormatter();

            Sheet sheet = workbook.getSheetAt(0);

            // get key of map
            List<String> keyList = new LinkedList<>();
            sheet.getRow(1).forEach(cell -> {
                keyList.add(getCellValueFormula(cell,formulaEvaluator));
            });

            for(Row row : sheet)
            {
                if ( row.getRowNum() < 2)
                    continue;

                Map<String,String> data = new HashMap<>();
                data.put(rowNumber, row.getRowNum() + 1 +"");      // record row number of the data in excel
                for(int i=0; i<keyList.size(); i++)
                {
                    Cell cell = row.getCell(i);
                    data.put(keyList.get(i), cell==null?"": getCellValueFormula(cell,formulaEvaluator));
                }

                dataList.add(data);
            }
            return null;
        }
    }

    private static class EggProducePerformanceStandExcelHandle implements ExcelHandle
    {
        @Override
        public List<Map<String, String>> read(File file) throws Exception
        {
            LinkedList<Map<String, String>> dataList = new LinkedList<>();
            excels.put(file.getName(), dataList);
            Workbook workbook = new XSSFWorkbook(file);

            FormulaEvaluator  formulaEvaluator =  new XSSFFormulaEvaluator((XSSFWorkbook) workbook);

            Sheet sheet = workbook.getSheetAt(0);

            // get key of map
            List<String> keyList = new LinkedList<>();
            sheet.getRow(1).forEach(cell -> {
                keyList.add(getCellValueFormula(cell,formulaEvaluator));
            });

            for(Row row : sheet)
            {
                if ( row.getRowNum() < 2)
                    continue;

                Map<String,String> data = new HashMap<>();
                data.put(rowNumber, row.getRowNum() + 1 +"");      // record row number of the data in excel
                for(int i=0; i<keyList.size(); i++)
                {
                    Cell cell = row.getCell(i);
                    data.put(keyList.get(i), cell==null?"": getCellValueFormula(cell,formulaEvaluator));
                }

                dataList.add(data);
            }

            List<Map<String, String>> maps = ExcelManage.getExcels().get(ExcelConstant.EggProducePerformanceStandExcel);
//            System.out.println("-------------------------------------------------");
//            maps.forEach(data -> {
//                System.out.println(data);
//            });
//            System.out.println("-------------------------------------------------");

            return null;
        }
    }

    //未处理公式
    public static String getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getRichStringCellValue().getString().trim().replace(" ","").replace("\n","");
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");//非线程安全
                    return sdf.format(cell.getDateCellValue());
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case Cell.CELL_TYPE_BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            default:
                return null;
        }
    }

    //处理公式
    public static String getCellValueFormula(Cell cell, FormulaEvaluator formulaEvaluator) {
        if (cell == null || formulaEvaluator == null) {
            return null;
        }

        if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            String number = String.valueOf(formulaEvaluator.evaluate(cell).getNumberValue());
            try
            {
                Double lnumber = Double.parseDouble(number);
                number = String.format("%.2f", lnumber);
            } catch (Exception e)
            {
            }
            return number;
        }
        return getCellValue(cell);
    }
}
