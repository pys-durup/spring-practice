package com.example.poitest.controller;

import com.example.poitest.service.PoitestService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PoitestController {

    @Autowired
    private PoitestService poitestService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/excel/download")
    public void excelDownload(HttpServletResponse response) throws IOException {

        String sql = "select a.*, b.*, c.*, a.*, b.*, c.*, a.*, b.*, c.* from help_keyword a \n" +
                "cross join help_keyword b \n" +
                "cross join help_keyword c\n" +
                "limit 1000000;";
        String headerData = "id,name,id,name,id,name,id,name,id,name,id,name,id,name,id,name";

        poitestService.makeExcel(sql, headerData);
//        SXSSFWorkbook wb = poitestService.makeExcel(sql, headerData);

        // 컨텐츠 타입과 파일명 지정
//        response.setContentType("ms-vnd/excel");
//        response.setHeader("Content-Disposition", "attachment;filename=example.xls");
//        response.setHeader("Content-Disposition", "attachment;filename=example.xlsx");

        // Excel File Output
//        wb.write(response.getOutputStream());
//        wb.close();


    }
}
