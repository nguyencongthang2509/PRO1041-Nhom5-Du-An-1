/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import core.quanly.repository.CTSanPhamRepository;
import domainmodels.ChatLieu;
import domainmodels.ChiTietSP;
import domainmodels.Hang;
import domainmodels.KichThuoc;
import domainmodels.MauSac;
import domainmodels.SanPham;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author HP
 */
public class ImportExcelCTSP {

    private CTSanPhamRepository cTSanPhamRepository;

    public ImportExcelCTSP() {
        cTSanPhamRepository = new CTSanPhamRepository();
    }

    public void ImportFile(String path) {
        try {
            List<ChiTietSP> listctsp = new ArrayList<>();
            FileInputStream excelFile = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            DataFormatter fmt = new DataFormatter();
            Iterator<Row> iterator = datatypeSheet.iterator();
            Row firstRow = iterator.next();
            Cell firstCell = firstRow.getCell(0);
            int mactsp = cTSanPhamRepository.genMaCTSP();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();

                String sanPhamStr = String.valueOf(getCellValue(currentRow.getCell(1))).trim();
                String mauSacStr = String.valueOf(getCellValue(currentRow.getCell(2))).trim();
                String tenkichThuoc = String.valueOf(getCellValue(currentRow.getCell(3))).trim();
                String hangStr = String.valueOf(getCellValue(currentRow.getCell(4))).trim();
                String chatLieuStr = String.valueOf(getCellValue(currentRow.getCell(5))).trim();
                String moTa = String.valueOf(getCellValue(currentRow.getCell(6))).trim();
                String soLuongTon = String.valueOf(getCellValue(currentRow.getCell(7))).trim();
                String giaBan = String.valueOf(getCellValue(currentRow.getCell(8))).trim();
                String maVach = String.valueOf(getCellValue(currentRow.getCell(9))).trim();

                if (mauSacStr.isEmpty() || sanPhamStr.isEmpty() || tenkichThuoc.isEmpty() || hangStr.isEmpty()
                        || chatLieuStr.isEmpty() || moTa.isEmpty() || soLuongTon.isEmpty() || giaBan.isEmpty() || maVach.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không để trống");
                    return;
                }
                SanPham sanPham = cTSanPhamRepository.findSanPhamByTen(sanPhamStr);
                if (sanPham == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm");
                    return;
                }
                MauSac mauSac = cTSanPhamRepository.findMauSacByTen(mauSacStr);
                if (mauSac == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy màu sắc");
                    return;
                }
                KichThuoc kichThuoc1 = cTSanPhamRepository.findKichThuocByTen(String.valueOf((int) Double.parseDouble(tenkichThuoc)));
                if (kichThuoc1 == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy kích thước");
                    return;
                }
                Hang hang = cTSanPhamRepository.findHangByTen(hangStr);
                if (hang == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy hãng");
                    return;
                }
                ChatLieu chatLieu = cTSanPhamRepository.findChatLieuByTen(chatLieuStr);
                if (chatLieu == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy chất liệu");
                    return;
                }
                ChiTietSP chiTietSP = new ChiTietSP();
                ChiTietSP chiTietSPcheck = cTSanPhamRepository.findByCBB(sanPhamStr, hangStr, mauSacStr, String.valueOf((int) Double.parseDouble(tenkichThuoc)), chatLieuStr);
                JOptionPane.showMessageDialog(null, chiTietSPcheck.getId());
                if (chiTietSPcheck == null) {
                    chiTietSP.setMaChiTietSP("CTSP" + mactsp++);
                    chiTietSP.setSanPham(sanPham);
                    chiTietSP.setMauSac(mauSac);
                    chiTietSP.setKichThuoc(kichThuoc1);
                    chiTietSP.setHang(hang);
                    chiTietSP.setChatLieu(chatLieu);
                    chiTietSP.setMoTa(moTa);
                    chiTietSP.setSoLuongTon((int) Double.parseDouble(soLuongTon));
                    chiTietSP.setGiaBan(new BigDecimal(giaBan));
                    chiTietSP.setMaVach(String.valueOf((int) Double.parseDouble(maVach))); 
                    listctsp.add(chiTietSP);
                }else{
                    chiTietSPcheck.setMaVach(String.valueOf((int) Double.parseDouble(maVach)));
                    chiTietSPcheck.setGiaBan(new BigDecimal(giaBan));
                    chiTietSPcheck.setMoTa(moTa);
                    chiTietSPcheck.setSoLuongTon((int) Double.parseDouble(soLuongTon) + chiTietSPcheck.getSoLuongTon());
                    listctsp.add(chiTietSPcheck);
                }

               
//                System.out.println(currentRow.getCell(1).getStringCellValue());
//                System.out.println(currentRow.getCell(2).getStringCellValue());
//                System.out.println(currentRow.getCell(3).getStringCellValue());
//                System.out.println(currentRow.getCell(4).getStringCellValue());
//                System.out.println(currentRow.getCell(5).getStringCellValue());
//                System.out.println(currentRow.getCell(6).getStringCellValue());
//                System.out.println(currentRow.getCell(7).getStringCellValue());
//                System.out.println(currentRow.getCell(8).getStringCellValue());
//                System.out.println(currentRow.getCell(9).getStringCellValue());

            }
            cTSanPhamRepository.saveAll(listctsp);
            JOptionPane.showMessageDialog(null, "Import file excel thành công");
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object getCellValue(Cell cell) {
        try {
            switch (cell.getCellType()) {
                case NUMERIC -> {
                    return cell.getNumericCellValue();
                }
                case BOOLEAN -> {
                    return cell.getBooleanCellValue();
                }
                default -> {
                    return cell.getStringCellValue();
                }
            }
        } catch (Exception e) {
            return "";
        }
    }
}
