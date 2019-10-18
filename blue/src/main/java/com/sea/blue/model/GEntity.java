package com.sea.blue.model;

import org.apache.commons.lang.time.DateUtils;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

public interface GEntity
{
    public default String numberConvertDate(String strDate)
    {
        String[] split = strDate.split("/");
        if(split != null && split.length>1)
            return strDate;
        Date time = new GregorianCalendar(1900, 0, -1).getTime();

        Date date = DateUtils.addDays(time, Integer.parseInt(strDate.replace(".00","").replace(".0","")));
        return date.toLocaleString().split(" ")[0].replaceAll("-","/");
    }
}
