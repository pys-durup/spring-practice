package com.example.poitest.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class PoitestService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PoitestService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Workbook makeExcel(String sql, String headerData) {

        String[] headers = headerData.split(",");

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("첫번째 시트");
        Row row = null;
        Cell cell = null;
        int rowNum = 0;


        // Excel Header 정의
        row = sheet.createRow(rowNum++);
        for (int i=0; i<headers.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Excel Body 정의
        jdbcTemplate.query(sql, new RowCallbackHandler() {

            int rNum = 1;

            @Override
            public void processRow(ResultSet rs) throws SQLException {
                Row row = null;
                Cell cell = null;
                row = sheet.createRow(rNum++);
                for (int i=0; i<headers.length; i++) {
                    String temp = rs.getString(headers[i]);
                    cell = row.createCell(i);
                    cell.setCellValue(temp);
                }
            }
        });

//        jdbcTemplate.query(sql, rs ->  {
//            row = getRow(sheet, rowNum++);
//            for (int i=0; i<headers.length; i++) {
//                String temp = rs.getString(headers[i]);
//                cell = row.createCell(i);
//                cell.setCellValue(temp);
//            }
//        });


//        // Body
//        for (int i=0; i<3; i++) {
//            row = sheet.createRow(rowNum++);
//            cell = row.createCell(0);
//            cell.setCellValue(i);
//            cell = row.createCell(1);
//            cell.setCellValue(i+"_name");
//            cell = row.createCell(2);
//            cell.setCellValue(i+"_title");
//        }

        return wb;
    }

    // Sheet로 부터 Row를 취득, 생성하기
    public Row getRow(Sheet sheet, int rownum) {
        Row row = sheet.getRow(rownum);
        if (row == null) {
            row = sheet.createRow(rownum);
        }
        return row;
    }
    // Row로 부터 Cell를 취득, 생성하기
    public Cell getCell(Row row, int cellnum) {
        Cell cell = row.getCell(cellnum);
        if (cell == null) {
            cell = row.createCell(cellnum);
        }
        return cell;
    }
}
