package com.mops.registrar.web.page.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.mops.registrar.entities.MopsUser;

/**
 * Builds an Excel spreadsheet view of the Admin list {@link MopsUser} page
 * 
 * @author dylants
 * 
 */
public class AdminListUsersExcelView extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HSSFSheet sheet = workbook.createSheet("MOPS Users");

        // create the header
        HSSFFont headerFont = workbook.createFont();
        headerFont.setFontName(HSSFFont.FONT_ARIAL);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(headerFont);

        HSSFRow header = sheet.createRow(0);
        createCell(header, 0, "User Name", headerStyle);
        createCell(header, 1, "First Name", headerStyle);
        createCell(header, 2, "M.I.", headerStyle);
        createCell(header, 3, "Last Name", headerStyle);
        createCell(header, 4, "Street Address", headerStyle);
        createCell(header, 5, "City", headerStyle);
        createCell(header, 6, "State", headerStyle);
        createCell(header, 7, "Zip", headerStyle);
        createCell(header, 8, "Home Phone", headerStyle);
        createCell(header, 9, "Cell Phone", headerStyle);
        createCell(header, 10, "Date of Birth", headerStyle);

        header.setHeightInPoints(14);

        // create the table data
        HSSFFont dataFont = workbook.createFont();
        dataFont.setFontName(HSSFFont.FONT_ARIAL);
        dataFont.setFontHeightInPoints((short) 12);

        HSSFCellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setFont(dataFont);

        @SuppressWarnings("unchecked")
        List<MopsUser> mopsUsers = (List<MopsUser>) model.get("mopsUsers");
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        // add the rows of data
        int rowNum = 2;
        for (MopsUser mopsUser : mopsUsers) {
            HSSFRow row = sheet.createRow(rowNum);
            createCell(row, 0, mopsUser.getUsername(), dataStyle);
            createCell(row, 1, mopsUser.getRegistrationInformation().getFirstName(), dataStyle);
            createCell(row, 2, mopsUser.getRegistrationInformation().getMiddleInitial(), dataStyle);
            createCell(row, 3, mopsUser.getRegistrationInformation().getLastName(), dataStyle);
            createCell(row, 4, mopsUser.getRegistrationInformation().getAddress().getStreetAddress(), dataStyle);
            createCell(row, 5, mopsUser.getRegistrationInformation().getAddress().getCity(), dataStyle);
            createCell(row, 6, mopsUser.getRegistrationInformation().getAddress().getState(), dataStyle);
            createCell(row, 7, mopsUser.getRegistrationInformation().getAddress().getZipCode(), dataStyle);
            createCell(row, 8, mopsUser.getRegistrationInformation().getHomePhoneNumber(), dataStyle);
            createCell(row, 9, mopsUser.getRegistrationInformation().getCellPhoneNumber(), dataStyle);
            createCell(row, 10, dateFormat.format(mopsUser.getRegistrationInformation().getDateOfBirth()), dataStyle);

            row.setHeightInPoints(14);

            // increase the row count
            rowNum++;
        }

        // adjust column width
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);
        sheet.autoSizeColumn(7);
        sheet.autoSizeColumn(8);
        sheet.autoSizeColumn(9);
        sheet.autoSizeColumn(10);
    }

    /**
     * Creates a single cell off of the <code>row</code>, while also applying the <code>cellStyle</code>
     * 
     * @param row
     *            The row to create the cell within
     * @param cellNumber
     *            The cell index within the row
     * @param cellText
     *            The text to set in the cell
     * @param cellStyle
     *            The style to set on the cell
     */
    protected void createCell(HSSFRow row, int cellNumber, String cellText, HSSFCellStyle cellStyle) {
        HSSFCell cell = row.createCell(cellNumber);
        cell.setCellValue(cellText);
        cell.setCellStyle(cellStyle);
    }
}