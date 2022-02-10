package com.example.poitest.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
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
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Service
public class PoitestService {

    private final JdbcTemplate jdbcTemplate;
    private XSSFWorkbook xssfWorkbook = null;
    private SXSSFWorkbook sxssfWorkbook = null;
    private FileOutputStream fileOutputStream = null;
    private String FILE_NAME;


    @Autowired
    public PoitestService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void makeExcel(String sql, String headerData) {

        UUID one = UUID.randomUUID();
        FILE_NAME = one.toString();


        /*

        try {
            String[] headers = headerData.split(",");

            FileInputStream fis = new FileInputStream("test.xlsx");
            //InputStream templateFile = resourceLoader.getResource("classpath:template/excel/" + templateFileName).getInputStream();
            xssfWorkbook = new XSSFWorkbook(fis);

            // 메모리에 100개의 행을 유지, 행의 수가 100개 행을 넘어가면 디스크에 적는다
            //SXSSFWorkbook wb = new SXSSFWorkbook(100);
            Sheet originalSheet = xssfWorkbook.getSheetAt(0);
            int rowNum = originalSheet.getLastRowNum();

            //SXSSF 생성
            sxssfWorkbook = new SXSSFWorkbook(xssfWorkbook, 10);
            Sheet sheet = sxssfWorkbook.getSheetAt(0);

            Row row = null;
            Cell cell = null;

            // Excel Header 정의
            row = sheet.createRow(++rowNum);
            for (int i=0; i<headers.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(headers[i]);
            }

            jdbcTemplate.setFetchSize(10);

            // Excel Body 정의
            jdbcTemplate.query(sql, new RowCallbackHandler() {

                int rNum = 1;
                Row row = null;
                Cell cell = null;

                @Override
                public void processRow(ResultSet rs) throws SQLException {

                    row = sheet.createRow(rNum++);
                    for (int i=0; i<headers.length; i++) {
                        String temp = rs.getString(headers[i]);
                        cell = row.createCell(i);
                        cell.setCellValue(temp);
                    }


                    if (rNum % 10 == 0) {
                        try {
                            System.out.println("write excel file rNum = " + rNum);
                            fileOutputStream = new FileOutputStream("test2.xlsx", true);
                            sxssfWorkbook.write(fileOutputStream);
                            fileOutputStream.close();

                            ((SXSSFSheet)sheet).flushRows(10);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sxssfWorkbook.dispose();
        }
        return sxssfWorkbook;
        */


        /*
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
            Row row = null;
            Cell cell = null;

            @Override
            public void processRow(ResultSet rs) throws SQLException {

                row = sheet.createRow(rNum++);
                for (int i=0; i<headers.length; i++) {
                    String temp = rs.getString(headers[i]);
                    cell = row.createCell(i);
                    cell.setCellValue(temp);
                }
            }
        });
        return wb;

         */
        String[] headers = headerData.split(",");

        sxssfWorkbook = new SXSSFWorkbook(1000);
        Sheet sheet = sxssfWorkbook.createSheet();

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
            Row row = null;
            Cell cell = null;

            @Override
            public void processRow(ResultSet rs) throws SQLException {

                row = sheet.createRow(rNum++);
                for (int i=0; i<headers.length; i++) {
                    String temp = rs.getString(i+1);
                    cell = row.createCell(i);
                    cell.setCellValue(temp);
                }

                System.out.println("rNum -> " + rNum);

                if (rNum % 1000 == 1) {
                    System.out.println("flush !!");

                    try {
                        ((SXSSFSheet)sheet).flushRows(1000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        try {
            fileOutputStream = new FileOutputStream(FILE_NAME + ".xlsx", true);
            sxssfWorkbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("fileOutputStream.close()");
            sxssfWorkbook.close();
            System.out.println("sxssfWorkbook.close()");

        } catch (Exception e) {
            e.printStackTrace();
        }

        sxssfWorkbook.dispose();
        System.out.println("sxssfWorkbook.dispose()");

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
