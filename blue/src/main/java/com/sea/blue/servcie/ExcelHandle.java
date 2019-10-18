package com.sea.blue.servcie;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface ExcelHandle
{
    List<Map<String,String>> read(File file) throws Exception;
}
