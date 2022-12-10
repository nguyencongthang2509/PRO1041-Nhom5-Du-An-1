/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package core.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DateTimePicker;
import core.quanly.service.HoaDonservice;
import core.quanly.service.impl.HoaDonserviceImpl;
import core.quanly.viewmodel.BhHoaDonChiTietResponse;
import core.quanly.viewmodel.HdHoaDonChiTietResponse1;
import core.quanly.viewmodel.HdHoaDonResponse1;
import core.quanly.viewmodel.HdHoaDonResponse2;
import domainmodels.HoaDon;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.ExportPdfHoaDon;
import view.JFrameQuanLy;

/**
 *
 * @author longnh26222
 */
public class ViewHoaDon extends javax.swing.JPanel {

    private HoaDonservice hoadonservice = new HoaDonserviceImpl();
    private DefaultTableModel modelHoaDon;
    private DefaultTableModel modelHoaDon2;
    private DefaultTableModel modelHoaDonChiTiet;
    private DefaultTableModel modelHoaDonChiTiet2;
    private List<HdHoaDonResponse1> listHoaDon;
    private List<HdHoaDonResponse2> listHoaDon2;
    private List<HdHoaDonChiTietResponse1> listHDCT;
    private int tranghientai = 0;
    private int tongsoTrang = 1;
    private int count = 1;

    public ViewHoaDon() {
        initComponents();
        this.modelHoaDon = (DefaultTableModel) tblHoadon.getModel();
        this.modelHoaDon2 = (DefaultTableModel) tblHD2.getModel();
        listHoaDon = hoadonservice.getList(0);
        listHoaDon2 = hoadonservice.getList2(1);
        this.loadTableHoaDon(listHoaDon);
        this.loadTableHoaDon2(listHoaDon2);
    }

    public void loadTableHoaDon(List<HdHoaDonResponse1> listHD) {
        modelHoaDon.setRowCount(0);
        if (listHD.size() == 0) {
            tranghientai = 0;
            tongsoTrang = 1;
            lblRecordHD.setText(tranghientai + 1 + "/" + tongsoTrang);
            return;
        }
        count = listHD.size();
        if (count % 10 == 0) {
            tongsoTrang = count / 10;
        } else {
            tongsoTrang = count / 10 + 1;
        }
        int n = 10 * (tranghientai);
        int m = n + 10;
        if (count % 10 == 0) {
            for (int i = n; i < m; i++) {
                modelHoaDon.addRow(new Object[]{i + 1, listHD.get(i).getMa(), listHD.get(i).getNgayTao(), listHD.get(i).getNgayThanhToanStr(),
                    listHD.get(i).getThanhTienStr(), listHD.get(i).getMaNV(), listHD.get(i).getTenKHStr(), listHD.get(i).getLyDoStr(), listHD.get(i).getTT()});
            }
        } else {
            if (tranghientai == tongsoTrang - 1) {
                int k = n + count % 10;
                for (int i = n; i < k; i++) {
                    modelHoaDon.addRow(new Object[]{i + 1, listHD.get(i).getMa(), listHD.get(i).getNgayTao(), listHD.get(i).getNgayThanhToanStr(),
                        listHD.get(i).getThanhTienStr(), listHD.get(i).getMaNV(), listHD.get(i).getTenKHStr(), listHD.get(i).getLyDoStr(), listHD.get(i).getTT()});
                }
            } else {
                for (int i = n; i < m; i++) {

                    modelHoaDon.addRow(new Object[]{i + 1, listHD.get(i).getMa(), listHD.get(i).getNgayTao(), listHD.get(i).getNgayThanhToanStr(),
                        listHD.get(i).getThanhTienStr(), listHD.get(i).getMaNV(), listHD.get(i).getTenKHStr(), listHD.get(i).getLyDoStr(), listHD.get(i).getTT()});
                }
            }
        }
        lblRecordHD.setText(tranghientai + 1 + "/" + tongsoTrang);
    }

    public void loadTableHoaDon2(List<HdHoaDonResponse2> listHD2) {
        modelHoaDon2.setRowCount(0);
        if (listHD2.size() == 0) {
            tranghientai = 0;
            tongsoTrang = 1;
            lblRecordHD2.setText(tranghientai + 1 + "/" + tongsoTrang);
            return;
        }
        count = listHD2.size();
        if (count % 10 == 0) {
            tongsoTrang = count / 10;
        } else {
            tongsoTrang = count / 10 + 1;
        }
        int n = 10 * (tranghientai);
        int m = n + 10;
        if (count % 10 == 0) {
            for (int i = n; i < m; i++) {
                modelHoaDon2.addRow(new Object[]{i + 1, listHD2.get(i).getMa(), listHD2.get(i).getNgayTao(), listHD2.get(i).getNgayThanhToanStr(),
                    listHD2.get(i).getThanhTienStr(), listHD2.get(i).getMaNV(), listHD2.get(i).getTenKHStr(), listHD2.get(i).getDiaChiStr(),
                    listHD2.get(i).getTenNguoiShipStr(), listHD2.get(i).getNgayShipStr(), listHD2.get(i).getNgayNhanStr(), listHD2.get(i).getLyDoStr(), listHD2.get(i).getTT()});
            }
        } else {
            if (tranghientai == tongsoTrang - 1) {
                int k = n + count % 10;
                for (int i = n; i < k; i++) {
                    modelHoaDon2.addRow(new Object[]{i + 1, listHD2.get(i).getMa(), listHD2.get(i).getNgayTao(), listHD2.get(i).getNgayThanhToanStr(),
                        listHD2.get(i).getThanhTienStr(), listHD2.get(i).getMaNV(), listHD2.get(i).getTenKHStr(), listHD2.get(i).getDiaChiStr(),
                        listHD2.get(i).getTenNguoiShipStr(), listHD2.get(i).getNgayShipStr(), listHD2.get(i).getNgayNhanStr(), listHD2.get(i).getLyDoStr(), listHD2.get(i).getTT()});
                }
            } else {
                for (int i = n; i < m; i++) {
                    modelHoaDon2.addRow(new Object[]{i + 1, listHD2.get(i).getMa(), listHD2.get(i).getNgayTao(), listHD2.get(i).getNgayThanhToanStr(),
                        listHD2.get(i).getThanhTienStr(), listHD2.get(i).getMaNV(), listHD2.get(i).getTenKHStr(), listHD2.get(i).getDiaChiStr(),
                        listHD2.get(i).getTenNguoiShipStr(), listHD2.get(i).getNgayShipStr(), listHD2.get(i).getNgayNhanStr(), listHD2.get(i).getLyDoStr(), listHD2.get(i).getTT()});
                }
            }
        }
        lblRecordHD2.setText(tranghientai + 1 + "/" + tongsoTrang);
    }

    public void loadTableHDbyText() {
        tranghientai = 0;
        listHoaDon = hoadonservice.getListbyText(0, txtSearch.getText());
        if (listHoaDon == null) {
            return;
        }
        loadTableHoaDon(listHoaDon);
    }

    public void loadTableHD2byText() {
        tranghientai = 0;
        listHoaDon2 = hoadonservice.getListHD2byText(txtSearch2.getText(), 1);
        if (listHoaDon2 == null) {
            return;
        }
        loadTableHoaDon2(listHoaDon2);
    }

    public void loadTblHD1byTime() {
        try {
            LocalDateTime time1 = txtTime1.getDateTimePermissive();
            LocalDateTime time2 = txtTime2.getDateTimePermissive();
            System.out.println(time1);
            System.out.println(time2);
            String t1 = String.valueOf(time1);
            String t2 = String.valueOf(time2);
            String array1[] = t1.split("T");
            String arrayDaoChuoi[] = array1[0].split("-");
            String ngayDao = arrayDaoChuoi[2] + "-" + arrayDaoChuoi[1] + "-" + arrayDaoChuoi[0];
            String timeDau = "";
            if (Integer.parseInt(t1.substring(11, 13)) < 12) {
                timeDau = ngayDao + " " + array1[1] + " AM";
            } else {
                timeDau = ngayDao + " " + array1[1] + " PM";
            }

            String array2[] = t2.split("T");
            String arrayDaoChuoi2[] = array2[0].split("-");
            String ngayDao2 = arrayDaoChuoi2[2] + "-" + arrayDaoChuoi2[1] + "-" + arrayDaoChuoi2[0];
            String timeCuoi = ngayDao2 + " " + array2[1];
            if (Integer.parseInt(t2.substring(11, 13)) < 12) {
                timeCuoi = ngayDao2 + " " + array2[1] + " AM";
            } else {
                timeCuoi = ngayDao2 + " " + array2[1] + " PM";
            }

            SimpleDateFormat fomat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
            Date time3 = fomat.parse(timeDau);
            Date time4 = fomat.parse(timeCuoi);
            System.out.println(time3);
            System.out.println(time4);
            if (time3.getTime() > time4.getTime()) {
                JOptionPane.showMessageDialog(this, "Khoảng thời gian không hợp lệ");
                return;
            }
            if (cboHinhthucthanhtoan.getSelectedIndex() == 0 && cboTrangthaithanhtoan.getSelectedIndex() == 0) {
                listHoaDon = hoadonservice.getListbyTime(time3, time4, 0);
            } else if (cboHinhthucthanhtoan.getSelectedIndex() == 0 && cboTrangthaithanhtoan.getSelectedIndex() > 0) {
                int tt = cboTrangthaithanhtoan.getSelectedIndex();
                if (cboTrangthaithanhtoan.getSelectedIndex() > 3) {
                    listHoaDon = hoadonservice.getListbyTrangThaiandTime(tt + 2, time3, time4, 0);
                } else {
                    listHoaDon = hoadonservice.getListbyTrangThaiandTime(tt - 1, time3, time4, 0);
                }
            } else if (cboHinhthucthanhtoan.getSelectedIndex() > 0 && cboTrangthaithanhtoan.getSelectedIndex() == 0) {
                int ht = cboHinhthucthanhtoan.getSelectedIndex();
                listHoaDon = hoadonservice.getListbyHinhthucandTime(ht - 1, time3, time4, 0);
            } else if (cboHinhthucthanhtoan.getSelectedIndex() > 0 && cboTrangthaithanhtoan.getSelectedIndex() > 0) {
                int tt = cboTrangthaithanhtoan.getSelectedIndex();
                int ht = cboHinhthucthanhtoan.getSelectedIndex();
                if (cboTrangthaithanhtoan.getSelectedIndex() > 3) {
                    listHoaDon = hoadonservice.getListbyAllComboBox(tt + 2, ht - 1, time3, time4, 0);
                } else {
                    listHoaDon = hoadonservice.getListbyAllComboBox(tt - 1, ht - 1, time3, time4, 0);
                }
            }
            tranghientai = 0;
            modelHoaDon.setRowCount(0);
            loadTableHoaDon(listHoaDon);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadTblHD2byTime() {
        try {
            modelHoaDon2.setRowCount(0);
            listHoaDon2 = hoadonservice.getList2(1);
            LocalDateTime time1 = txtTime3.getDateTimePermissive();
            LocalDateTime time2 = txtTime4.getDateTimePermissive();
            System.out.println(time1);
            String t1 = String.valueOf(time1);
            String t2 = String.valueOf(time2);
            String array1[] = t1.split("T");
            String arrayDaoChuoi[] = array1[0].split("-");
            String ngayDao = arrayDaoChuoi[2] + "-" + arrayDaoChuoi[1] + "-" + arrayDaoChuoi[0];
            String timeDau = "";
            if (Integer.parseInt(t1.substring(11, 13)) < 12) {
                timeDau = ngayDao + " " + array1[1] + " AM";
            } else {
                timeDau = ngayDao + " " + array1[1] + " PM";
            }

            String array2[] = t2.split("T");
            String arrayDaoChuoi2[] = array2[0].split("-");
            String ngayDao2 = arrayDaoChuoi2[2] + "-" + arrayDaoChuoi2[1] + "-" + arrayDaoChuoi2[0];
            String timeCuoi = ngayDao2 + " " + array2[1];
            if (Integer.parseInt(t2.substring(11, 13)) < 12) {
                timeCuoi = ngayDao2 + " " + array2[1] + " AM";
            } else {
                timeCuoi = ngayDao2 + " " + array2[1] + " PM";
            }

            SimpleDateFormat fomat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
            Date time3 = fomat.parse(timeDau);
            Date time4 = fomat.parse(timeCuoi);
            if (time3.getTime() > time4.getTime()) {
                JOptionPane.showMessageDialog(this, "Khoảng thời gian không hợp lệ");
                return;
            }
            if (cboHinhThucTT2.getSelectedIndex() == 0 && cboTrangThai2.getSelectedIndex() == 0) {
                listHoaDon2 = hoadonservice.getList2byTime(time3, time4, 1);
            } else if (cboHinhThucTT2.getSelectedIndex() == 0 && cboTrangThai2.getSelectedIndex() > 0) {
                int tt = cboTrangThai2.getSelectedIndex();
                listHoaDon2 = hoadonservice.getList2byTrangThaiandTime(tt - 1, time3, time4, 1);
            } else if (cboHinhThucTT2.getSelectedIndex() > 0 && cboTrangThai2.getSelectedIndex() == 0) {
                int ht = cboHinhThucTT2.getSelectedIndex();
                listHoaDon2 = hoadonservice.getList2byHinhthucandTime(ht - 1, time3, time4, 1);
            } else if (cboHinhThucTT2.getSelectedIndex() > 0 && cboTrangThai2.getSelectedIndex() > 0) {
                int tt = cboTrangThai2.getSelectedIndex();
                int ht = cboHinhThucTT2.getSelectedIndex();
                listHoaDon2 = hoadonservice.getList2byAllComboBox(tt - 1, ht - 1, time3, time4, 1);
            }
            tranghientai = 0;
            loadTableHoaDon2(listHoaDon2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadTableHoaDonChiTiet() {
        int row = this.tblHoadon.getSelectedRow();
        if (row == -1) {
            return;
        }
        String mahd = this.tblHoadon.getValueAt(row, 1).toString();
        modelHoaDonChiTiet = (DefaultTableModel) tblHoadonchitiet.getModel();
        modelHoaDonChiTiet.setRowCount(0);
        listHDCT = hoadonservice.getListHDCT(mahd);
        if (listHDCT == null) {
            return;
        }
        for (HdHoaDonChiTietResponse1 hd : listHDCT) {
            modelHoaDonChiTiet.addRow(hd.toDataRow());
        }
    }

    public void loadTableHoaDonChiTiet2() {
        int row = this.tblHD2.getSelectedRow();
        if (row == -1) {
            return;
        }
        String mahd = this.tblHD2.getValueAt(row, 1).toString();
        modelHoaDonChiTiet2 = (DefaultTableModel) tblHoaDonChiTiet2.getModel();
        modelHoaDonChiTiet2.setRowCount(0);
        listHDCT = hoadonservice.getListHDCT(mahd);
        if (listHDCT == null) {
            return;
        }
        for (HdHoaDonChiTietResponse1 hd : listHDCT) {
            modelHoaDonChiTiet2.addRow(hd.toDataRow());
        }
    }

    public void resetTableHDCT() {
        if (tblHoadonchitiet.getRowCount() > 0) {
            modelHoaDonChiTiet.setRowCount(0);
        }
    }

    public void resetTableHDCT2() {
        if (tblHoaDonChiTiet2.getRowCount() > 0) {
            modelHoaDonChiTiet2.setRowCount(0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chiTietHoaDon = new javax.swing.JFrame();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        lblNgayTao = new javax.swing.JLabel();
        lblNgayThanhToan = new javax.swing.JLabel();
        lblHinhThucThanhToan = new javax.swing.JLabel();
        lblTongtien = new javax.swing.JLabel();
        lblTienkhachCK = new javax.swing.JLabel();
        lblTienkhachtra = new javax.swing.JLabel();
        lblTienthua = new javax.swing.JLabel();
        lblMaNV = new javax.swing.JLabel();
        lblTenNV = new javax.swing.JLabel();
        lblTenKhachHang = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        lblTrangthai = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        chiTietHoaDon2 = new javax.swing.JFrame();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lblHD2 = new javax.swing.JLabel();
        lblNgayTao2 = new javax.swing.JLabel();
        lblNgayThanhToan2 = new javax.swing.JLabel();
        lblHinhthucthanhtoan2 = new javax.swing.JLabel();
        lblTongtien2 = new javax.swing.JLabel();
        lblTienkhachCk2 = new javax.swing.JLabel();
        lblTienkhachtra2 = new javax.swing.JLabel();
        lblTienthua2 = new javax.swing.JLabel();
        lblMaNV2 = new javax.swing.JLabel();
        lblTenNV2 = new javax.swing.JLabel();
        lblTennguoinhan = new javax.swing.JLabel();
        lblDiachi2 = new javax.swing.JLabel();
        lblSDT2 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        lblTennguoiship = new javax.swing.JLabel();
        lblSDTnguoiship = new javax.swing.JLabel();
        lblTienship = new javax.swing.JLabel();
        lblNgayship = new javax.swing.JLabel();
        lblNgaynhan = new javax.swing.JLabel();
        lblTrangthai2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        tpb1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoadon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cboTrangthaithanhtoan = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cboHinhthucthanhtoan = new javax.swing.JComboBox<>();
        txtTime1 = new com.github.lgooddatepicker.components.DateTimePicker();
        txtTime2 = new com.github.lgooddatepicker.components.DateTimePicker();
        btnLoadByTime = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnXuatDachSach = new javax.swing.JButton();
        btnXemchitiet = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        lblRecordHD = new javax.swing.JLabel();
        btnInHoaDon = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoadonchitiet = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtSearch2 = new javax.swing.JTextField();
        cboTrangThai2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cboHinhThucTT2 = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHD2 = new javax.swing.JTable();
        txtTime4 = new com.github.lgooddatepicker.components.DateTimePicker();
        txtTime3 = new com.github.lgooddatepicker.components.DateTimePicker();
        btnLoadByTime2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnXemchitiet2 = new javax.swing.JButton();
        btnXuatDanhSach2 = new javax.swing.JButton();
        btnFirst2 = new javax.swing.JButton();
        btnNext2 = new javax.swing.JButton();
        btnLast2 = new javax.swing.JButton();
        btnBack2 = new javax.swing.JButton();
        btnHuy2 = new javax.swing.JButton();
        lblRecordHD2 = new javax.swing.JLabel();
        btnInHoaDon2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet2 = new javax.swing.JTable();

        chiTietHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        chiTietHoaDon.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setText("THÔNG TIN HÓA ĐƠN");
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        chiTietHoaDon.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 13, -1, -1));

        jLabel12.setText("Ngày tạo:");
        chiTietHoaDon.getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 103, 114, -1));

        jLabel13.setText("Mã hóa đơn:");
        chiTietHoaDon.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 69, 114, -1));

        jLabel14.setText("Ngày thanh toán:");
        chiTietHoaDon.getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 137, 114, -1));

        jLabel15.setText("Hình thức thanh toán:");
        chiTietHoaDon.getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 171, -1, -1));

        jLabel16.setText("Tổng tiền:");
        chiTietHoaDon.getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 205, 114, -1));

        jLabel17.setText("Tiền khách CK:");
        chiTietHoaDon.getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 239, 114, -1));

        jLabel18.setText("Tiền khách trả:");
        chiTietHoaDon.getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 273, 114, -1));

        jLabel19.setText("Tiền thừa:");
        chiTietHoaDon.getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 307, 114, -1));

        jLabel20.setText("Mã NV:");
        chiTietHoaDon.getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 341, 114, -1));

        jLabel21.setText("Tên NV:");
        chiTietHoaDon.getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 375, 114, -1));

        jLabel22.setText("Tên khách hàng:");
        chiTietHoaDon.getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 409, 114, -1));

        jLabel23.setText("Địa chỉ:");
        chiTietHoaDon.getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 443, 114, -1));

        jLabel24.setText("Số điện thoại:");
        chiTietHoaDon.getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 477, 114, -1));

        jLabel25.setText("Trạng thái:");
        chiTietHoaDon.getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 511, 114, -1));

        lblMaHD.setText(".....");
        chiTietHoaDon.getContentPane().add(lblMaHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 69, 136, -1));

        lblNgayTao.setText(".....");
        chiTietHoaDon.getContentPane().add(lblNgayTao, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 103, 137, -1));

        lblNgayThanhToan.setText(".....");
        chiTietHoaDon.getContentPane().add(lblNgayThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 137, 137, -1));

        lblHinhThucThanhToan.setText(".....");
        chiTietHoaDon.getContentPane().add(lblHinhThucThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 171, 137, -1));

        lblTongtien.setText(".....");
        chiTietHoaDon.getContentPane().add(lblTongtien, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 205, 136, -1));

        lblTienkhachCK.setText(".....");
        chiTietHoaDon.getContentPane().add(lblTienkhachCK, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 239, 136, -1));

        lblTienkhachtra.setText(".....");
        chiTietHoaDon.getContentPane().add(lblTienkhachtra, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 273, 136, -1));

        lblTienthua.setText(".....");
        chiTietHoaDon.getContentPane().add(lblTienthua, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 307, 136, -1));

        lblMaNV.setText(".....");
        chiTietHoaDon.getContentPane().add(lblMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 341, 136, -1));

        lblTenNV.setText(".....");
        chiTietHoaDon.getContentPane().add(lblTenNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 375, 136, -1));

        lblTenKhachHang.setText(".....");
        chiTietHoaDon.getContentPane().add(lblTenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 409, 136, -1));

        lblDiaChi.setText(".....");
        chiTietHoaDon.getContentPane().add(lblDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 443, 136, -1));

        lblSDT.setText(".....");
        chiTietHoaDon.getContentPane().add(lblSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 477, 136, -1));

        lblTrangthai.setText(".....");
        chiTietHoaDon.getContentPane().add(lblTrangthai, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 511, 136, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        chiTietHoaDon.getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 580));

        chiTietHoaDon2.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setText("THÔNG TIN HÓA ĐƠN");
        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        chiTietHoaDon2.getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 13, -1, -1));

        jLabel27.setText("Mã hóa đơn:");
        chiTietHoaDon2.getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 46, 118, -1));

        jLabel28.setText("Ngày tạo:");
        chiTietHoaDon2.getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 80, 118, -1));

        jLabel29.setText("Ngày thanh toán:");
        chiTietHoaDon2.getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 114, 118, -1));

        jLabel30.setText("Hình thức thanh toán:");
        chiTietHoaDon2.getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 148, 118, -1));

        jLabel31.setText("Tổng tiền:");
        chiTietHoaDon2.getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 182, 118, -1));

        jLabel32.setText("Tiền khách CK:");
        chiTietHoaDon2.getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 216, 118, -1));

        jLabel33.setText("Tiền khách trả:");
        chiTietHoaDon2.getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 250, 118, -1));

        jLabel34.setText("Tiền thừa:");
        chiTietHoaDon2.getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 284, 118, -1));

        jLabel35.setText("Mã NV:");
        chiTietHoaDon2.getContentPane().add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 318, 118, -1));

        jLabel36.setText("Tên NV:");
        chiTietHoaDon2.getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 352, 118, -1));

        jLabel37.setText("Tên người nhận:");
        chiTietHoaDon2.getContentPane().add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 46, 118, -1));

        jLabel38.setText("Địa chỉ:");
        chiTietHoaDon2.getContentPane().add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 80, 118, -1));

        jLabel39.setText("SĐT:");
        chiTietHoaDon2.getContentPane().add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 114, 118, -1));

        lblHD2.setText("jLabel40");
        chiTietHoaDon2.getContentPane().add(lblHD2, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 46, 119, -1));

        lblNgayTao2.setText("jLabel40");
        chiTietHoaDon2.getContentPane().add(lblNgayTao2, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 80, 119, -1));

        lblNgayThanhToan2.setText("jLabel40");
        chiTietHoaDon2.getContentPane().add(lblNgayThanhToan2, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 114, 119, -1));

        lblHinhthucthanhtoan2.setText("jLabel40");
        chiTietHoaDon2.getContentPane().add(lblHinhthucthanhtoan2, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 148, 119, -1));

        lblTongtien2.setText("jLabel40");
        chiTietHoaDon2.getContentPane().add(lblTongtien2, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 182, 119, -1));

        lblTienkhachCk2.setText("jLabel40");
        chiTietHoaDon2.getContentPane().add(lblTienkhachCk2, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 216, 119, -1));

        lblTienkhachtra2.setText("jLabel40");
        chiTietHoaDon2.getContentPane().add(lblTienkhachtra2, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 250, 119, -1));

        lblTienthua2.setText("jLabel40");
        chiTietHoaDon2.getContentPane().add(lblTienthua2, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 284, 119, -1));

        lblMaNV2.setText("jLabel40");
        chiTietHoaDon2.getContentPane().add(lblMaNV2, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 318, 119, -1));

        lblTenNV2.setText("jLabel40");
        chiTietHoaDon2.getContentPane().add(lblTenNV2, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 352, 119, -1));

        lblTennguoinhan.setText("jLabel40");
        chiTietHoaDon2.getContentPane().add(lblTennguoinhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 46, 119, -1));

        lblDiachi2.setText("jLabel40");
        chiTietHoaDon2.getContentPane().add(lblDiachi2, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 80, 119, -1));

        lblSDT2.setText("jLabel40");
        chiTietHoaDon2.getContentPane().add(lblSDT2, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 114, 119, -1));

        jLabel53.setText("Tên người ship:");
        chiTietHoaDon2.getContentPane().add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 148, 118, -1));

        jLabel54.setText("SĐT người ship:");
        chiTietHoaDon2.getContentPane().add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 182, 118, -1));

        jLabel55.setText("Tiền ship:");
        chiTietHoaDon2.getContentPane().add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 216, 118, -1));

        jLabel56.setText("Ngày ship:");
        chiTietHoaDon2.getContentPane().add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 250, 118, -1));

        jLabel57.setText("Ngày nhận:");
        chiTietHoaDon2.getContentPane().add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 284, 118, -1));

        jLabel58.setText("Trạng thái:");
        chiTietHoaDon2.getContentPane().add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 318, 118, -1));

        lblTennguoiship.setText("jLabel40");
        chiTietHoaDon2.getContentPane().add(lblTennguoiship, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 148, 119, -1));

        lblSDTnguoiship.setText("jLabel40");
        chiTietHoaDon2.getContentPane().add(lblSDTnguoiship, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 182, 119, -1));

        lblTienship.setText("jLabel40");
        chiTietHoaDon2.getContentPane().add(lblTienship, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 216, 119, -1));

        lblNgayship.setText("jLabel40");
        chiTietHoaDon2.getContentPane().add(lblNgayship, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 250, 119, -1));

        lblNgaynhan.setText("jLabel40");
        chiTietHoaDon2.getContentPane().add(lblNgaynhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 284, 119, -1));

        lblTrangthai2.setText("jLabel40");
        chiTietHoaDon2.getContentPane().add(lblTrangthai2, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 318, 119, -1));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );

        chiTietHoaDon2.getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 390));

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1292, 784));
        setMinimumSize(new java.awt.Dimension(1292, 784));
        setPreferredSize(new java.awt.Dimension(1292, 784));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tpb1.setBackground(new java.awt.Color(255, 255, 255));
        tpb1.setPreferredSize(new java.awt.Dimension(1292, 784));
        tpb1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tpb1MouseClicked(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1292, 784));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(1240, 300));
        jPanel1.setMinimumSize(new java.awt.Dimension(1240, 300));
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1240, 300));
        jPanel1.setToolTipText("");

        tblHoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hóa đơn", "Ngày tạo", "Ngày thanh toán", "Tổng tiền", "Mã NV", "Tên KH", "Lý do", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoadon.setRowHeight(25);
        tblHoadon.setShowGrid(false);
        tblHoadon.getTableHeader().setReorderingAllowed(false);
        tblHoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoadonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoadon);
        if (tblHoadon.getColumnModel().getColumnCount() > 0) {
            tblHoadon.getColumnModel().getColumn(0).setMinWidth(35);
            tblHoadon.getColumnModel().getColumn(0).setPreferredWidth(35);
            tblHoadon.getColumnModel().getColumn(0).setMaxWidth(35);
        }

        jLabel1.setText("Tìm kiếm hóa đơn:");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        txtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchCaretUpdate(evt);
            }
        });

        jLabel2.setText("Trạng thái hóa đơn:");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        cboTrangthaithanhtoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Chờ thanh toán", "Đã hủy", "Đã thanh toán", "Khách hẹn lại", "Đã trả hàng" }));
        cboTrangthaithanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangthaithanhtoanActionPerformed(evt);
            }
        });

        jLabel4.setText("Hình thức thanh toán:");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        cboHinhthucthanhtoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Tiền mặt", "Chuyển khoản", "Kết hợp" }));
        cboHinhthucthanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHinhthucthanhtoanActionPerformed(evt);
            }
        });

        btnLoadByTime.setText("Lọc");
        btnLoadByTime.setBackground(new java.awt.Color(153, 204, 255));
        btnLoadByTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadByTimeActionPerformed(evt);
            }
        });

        jLabel5.setText("Đến:");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel6.setText("Từ:");
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnXuatDachSach.setText("Xuất danh sách");
        btnXuatDachSach.setBackground(new java.awt.Color(153, 204, 255));
        btnXuatDachSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatDachSachActionPerformed(evt);
            }
        });

        btnXemchitiet.setText("Xem chi tiết");
        btnXemchitiet.setBackground(new java.awt.Color(153, 204, 255));
        btnXemchitiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemchitietActionPerformed(evt);
            }
        });

        btnBack.setText("<");
        btnBack.setBackground(new java.awt.Color(153, 204, 255));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnNext.setText(">");
        btnNext.setBackground(new java.awt.Color(153, 204, 255));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setText(">>");
        btnLast.setBackground(new java.awt.Color(153, 204, 255));
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnFirst.setText("<<");
        btnFirst.setBackground(new java.awt.Color(153, 204, 255));
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnHuy.setText("Hủy");
        btnHuy.setBackground(new java.awt.Color(255, 0, 0));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        lblRecordHD.setText("jLabel40");

        btnInHoaDon.setText("In hóa đơn");
        btnInHoaDon.setBackground(new java.awt.Color(153, 204, 255));
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTrangthaithanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboHinhthucthanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTime1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTime2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLoadByTime, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblRecordHD)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(72, 72, 72)
                                        .addComponent(btnInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnXemchitiet)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnXuatDachSach)
                                        .addGap(59, 59, 59)
                                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(691, 691, 691)))
                                .addGap(0, 3, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboHinhthucthanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTrangthaithanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(txtTime1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtTime2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoadByTime))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLast)
                        .addComponent(btnXemchitiet)
                        .addComponent(btnInHoaDon)
                        .addComponent(btnHuy)
                        .addComponent(btnXuatDachSach))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFirst)
                        .addComponent(btnBack)
                        .addComponent(lblRecordHD)
                        .addComponent(btnNext)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(1268, 350));

        tblHoadonchitiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Hãng", "Màu", "Size", "Số lượng", "Đơn giá", "Giá bán", "Giảm giá KM", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoadonchitiet.setRowHeight(25);
        tblHoadonchitiet.setShowGrid(false);
        tblHoadonchitiet.getTableHeader().setReorderingAllowed(false);
        tblHoadonchitiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoadonchitietMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoadonchitiet);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1267, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1267, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        tpb1.addTab("Hóa đơn tại quầy", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(1285, 578));

        txtSearch2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearch2CaretUpdate(evt);
            }
        });
        txtSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearch2ActionPerformed(evt);
            }
        });

        cboTrangThai2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Chờ thanh toán", "Đã hủy", "Đã thanh toán", "Chờ giao hàng", "Đang giao", "Đã giao", "Khách hẹn lại", "Đã trả hàng" }));
        cboTrangThai2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThai2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Trạng thái hóa đơn:");
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel7.setText("Hình thức thanh toán:");
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel8.setText("Tìm kiếm hóa đơn:");
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        cboHinhThucTT2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Tiền mặt", "Chuyển khoản", "Kết hợp" }));
        cboHinhThucTT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHinhThucTT2ActionPerformed(evt);
            }
        });

        tblHD2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hóa đơn", "Ngày tạo", "Ngày thanh toán", "Thành tiền", "Mã NV", "Tên người nhận", "Địa chỉ", "Tên người ship", "Ngày ship", "Ngày nhận", "Lý do", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHD2.setRowHeight(25);
        tblHD2.getTableHeader().setReorderingAllowed(false);
        tblHD2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHD2MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblHD2);
        if (tblHD2.getColumnModel().getColumnCount() > 0) {
            tblHD2.getColumnModel().getColumn(0).setMinWidth(35);
            tblHD2.getColumnModel().getColumn(0).setPreferredWidth(35);
            tblHD2.getColumnModel().getColumn(0).setMaxWidth(35);
        }

        btnLoadByTime2.setText("Lọc");
        btnLoadByTime2.setBackground(new java.awt.Color(153, 204, 255));
        btnLoadByTime2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadByTime2ActionPerformed(evt);
            }
        });

        jLabel9.setText("Đến:");
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel10.setText("Từ:");
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnXemchitiet2.setText("Xem chi tiết");
        btnXemchitiet2.setBackground(new java.awt.Color(153, 204, 255));
        btnXemchitiet2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemchitiet2ActionPerformed(evt);
            }
        });

        btnXuatDanhSach2.setText("Xuất danh sách");
        btnXuatDanhSach2.setBackground(new java.awt.Color(153, 204, 255));
        btnXuatDanhSach2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatDanhSach2ActionPerformed(evt);
            }
        });

        btnFirst2.setText("<<");
        btnFirst2.setBackground(new java.awt.Color(153, 204, 255));
        btnFirst2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirst2ActionPerformed(evt);
            }
        });

        btnNext2.setText(">");
        btnNext2.setBackground(new java.awt.Color(153, 204, 255));
        btnNext2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext2ActionPerformed(evt);
            }
        });

        btnLast2.setText(">>");
        btnLast2.setBackground(new java.awt.Color(153, 204, 255));
        btnLast2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLast2ActionPerformed(evt);
            }
        });

        btnBack2.setText("<");
        btnBack2.setBackground(new java.awt.Color(153, 204, 255));
        btnBack2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack2ActionPerformed(evt);
            }
        });

        btnHuy2.setText("Hủy");
        btnHuy2.setBackground(new java.awt.Color(255, 0, 0));
        btnHuy2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuy2ActionPerformed(evt);
            }
        });

        lblRecordHD2.setText("jLabel40");

        btnInHoaDon2.setText("In hóa đơn");
        btnInHoaDon2.setBackground(new java.awt.Color(153, 204, 255));
        btnInHoaDon2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDon2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnFirst2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btnBack2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblRecordHD2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(btnLast2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(btnInHoaDon2)
                        .addGap(18, 18, 18)
                        .addComponent(btnXemchitiet2)
                        .addGap(18, 18, 18)
                        .addComponent(btnXuatDanhSach2)
                        .addGap(47, 47, 47)
                        .addComponent(btnHuy2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboTrangThai2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboHinhThucTT2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTime3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTime4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLoadByTime2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboTrangThai2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboHinhThucTT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTime4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTime3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoadByTime2)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirst2)
                    .addComponent(btnBack2)
                    .addComponent(btnNext2)
                    .addComponent(btnLast2)
                    .addComponent(btnXemchitiet2)
                    .addComponent(btnHuy2)
                    .addComponent(lblRecordHD2)
                    .addComponent(btnInHoaDon2)
                    .addComponent(btnXuatDanhSach2))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(488, 350));

        tblHoaDonChiTiet2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Hãng", "Màu", "Size", "Số lượng", "Đơn giá", "Giá bán", "Giảm giá KM", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonChiTiet2.setRowHeight(25);
        tblHoaDonChiTiet2.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblHoaDonChiTiet2);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1233, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(201, 201, 201))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 1273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpb1.addTab("Hóa đơn đặt hàng", jPanel5);

        add(tpb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 780));
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoadonchitietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoadonchitietMouseClicked

    }//GEN-LAST:event_tblHoadonchitietMouseClicked

    private void cboTrangthaithanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangthaithanhtoanActionPerformed
        resetTableHDCT();
        if (cboTrangthaithanhtoan.getSelectedIndex() == 0) {
            if (cboHinhthucthanhtoan.getSelectedIndex() == 0) {
                listHoaDon = hoadonservice.getList(0);
            } else if (cboHinhthucthanhtoan.getSelectedIndex() > 0) {
                int hinhthuctt = cboHinhthucthanhtoan.getSelectedIndex();
                modelHoaDon.setRowCount(0);
                listHoaDon = hoadonservice.getListbyHinhThucThanhToan(hinhthuctt - 1, 0);
            }
        }
        if (cboTrangthaithanhtoan.getSelectedIndex() > 0) {
            if (cboTrangthaithanhtoan.getSelectedIndex() > 3) {
                if (cboHinhthucthanhtoan.getSelectedIndex() == 0) {
                    int trangthai = cboTrangthaithanhtoan.getSelectedIndex();
                    modelHoaDon.setRowCount(0);
                    listHoaDon = hoadonservice.getListbyTrangThai(trangthai + 2, 0);

                } else if (cboHinhthucthanhtoan.getSelectedIndex() > 0) {
                    int trangthaihd = cboTrangthaithanhtoan.getSelectedIndex();
                    int hinhthuctt = cboHinhthucthanhtoan.getSelectedIndex();
                    modelHoaDon.setRowCount(0);
                    listHoaDon = hoadonservice.getListbyAllTT(trangthaihd + 2, hinhthuctt - 1, 0);
                }
            } else {
                if (cboHinhthucthanhtoan.getSelectedIndex() == 0) {
                    int trangthai = cboTrangthaithanhtoan.getSelectedIndex();
                    modelHoaDon.setRowCount(0);
                    listHoaDon = hoadonservice.getListbyTrangThai(trangthai - 1, 0);
                } else if (cboHinhthucthanhtoan.getSelectedIndex() > 0) {
                    int trangthaihd = cboTrangthaithanhtoan.getSelectedIndex();
                    int hinhthuctt = cboHinhthucthanhtoan.getSelectedIndex();
                    modelHoaDon.setRowCount(0);
                    listHoaDon = hoadonservice.getListbyAllTT(trangthaihd - 1, hinhthuctt - 1, 0);
                }
            }

        }
        tranghientai = 0;
        loadTableHoaDon(listHoaDon);
    }//GEN-LAST:event_cboTrangthaithanhtoanActionPerformed

    private void tblHoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoadonMouseClicked
        loadTableHoaDonChiTiet();
    }//GEN-LAST:event_tblHoadonMouseClicked

    private void cboHinhthucthanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHinhthucthanhtoanActionPerformed
        resetTableHDCT();
        if (cboHinhthucthanhtoan.getSelectedIndex() == 0) {
            if (cboTrangthaithanhtoan.getSelectedIndex() == 0) {
                listHoaDon = hoadonservice.getList(0);
            } else if (cboTrangthaithanhtoan.getSelectedIndex() > 0) {
                int trangthai = cboTrangthaithanhtoan.getSelectedIndex();
                if (cboTrangthaithanhtoan.getSelectedIndex() > 3) {
                    listHoaDon = hoadonservice.getListbyTrangThai(trangthai + 2, 0);
                } else {
                    listHoaDon = hoadonservice.getListbyTrangThai(trangthai - 1, 0);
                }
            }
        }
        if (cboHinhthucthanhtoan.getSelectedIndex() > 0) {
            if (cboTrangthaithanhtoan.getSelectedIndex() == 0) {
                int hinhthuc = cboHinhthucthanhtoan.getSelectedIndex();
                listHoaDon = hoadonservice.getListbyHinhThucThanhToan(hinhthuc - 1, 0);
            } else if (cboTrangthaithanhtoan.getSelectedIndex() > 0) {
                int hinhthuctt = cboHinhthucthanhtoan.getSelectedIndex();
                int trangthaihd = cboTrangthaithanhtoan.getSelectedIndex();
                if (cboTrangthaithanhtoan.getSelectedIndex() > 3) {
                    listHoaDon = hoadonservice.getListbyAllTT(trangthaihd + 2, hinhthuctt - 1, 0);
                } else {
                    listHoaDon = hoadonservice.getListbyAllTT(trangthaihd - 1, hinhthuctt - 1, 0);
                }
            }
        }
        tranghientai = 0;
        loadTableHoaDon(listHoaDon);
    }//GEN-LAST:event_cboHinhthucthanhtoanActionPerformed

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate
        resetTableHDCT();
        try {
            loadTableHDbyText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtSearchCaretUpdate

    private void tpb1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpb1MouseClicked

    }//GEN-LAST:event_tpb1MouseClicked

    private void tblHD2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHD2MouseClicked
        loadTableHoaDonChiTiet2();
    }//GEN-LAST:event_tblHD2MouseClicked

    private void txtSearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearch2ActionPerformed

    }//GEN-LAST:event_txtSearch2ActionPerformed

    private void txtSearch2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearch2CaretUpdate
        resetTableHDCT2();
        try {
            loadTableHD2byText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtSearch2CaretUpdate

    private void cboTrangThai2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThai2ActionPerformed
        resetTableHDCT2();
        tranghientai = 0;
        if (cboTrangThai2.getSelectedIndex() == 0) {
            if (cboHinhThucTT2.getSelectedIndex() == 0) {
                listHoaDon2 = hoadonservice.getList2(1);
                loadTableHoaDon2(listHoaDon2);
            } else if (cboHinhThucTT2.getSelectedIndex() > 0) {
                int hinhthuctt = cboHinhThucTT2.getSelectedIndex();
                modelHoaDon2.setRowCount(0);
                listHoaDon2 = hoadonservice.getList2byHinhThucThanhToan(hinhthuctt - 1, 1);
                loadTableHoaDon2(listHoaDon2);
            }
        }
        if (cboTrangThai2.getSelectedIndex() > 0) {
            if (cboHinhThucTT2.getSelectedIndex() == 0) {
                int trangthai = cboTrangThai2.getSelectedIndex();
                modelHoaDon2.setRowCount(0);
                listHoaDon2 = hoadonservice.getList2byTrangThai(trangthai - 1, 1);
                loadTableHoaDon2(listHoaDon2);
            } else if (cboHinhThucTT2.getSelectedIndex() > 0) {
                int trangthaihd = cboTrangThai2.getSelectedIndex();
                int hinhthuctt = cboHinhThucTT2.getSelectedIndex();
                modelHoaDon2.setRowCount(0);
                listHoaDon2 = hoadonservice.getList2byAllTT(trangthaihd - 1, hinhthuctt - 1, 1);
                loadTableHoaDon2(listHoaDon2);
            }
        }
    }//GEN-LAST:event_cboTrangThai2ActionPerformed

    private void cboHinhThucTT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHinhThucTT2ActionPerformed
        resetTableHDCT2();
        tranghientai = 0;
        if (cboHinhThucTT2.getSelectedIndex() == 0) {
            if (cboTrangThai2.getSelectedIndex() == 0) {
                listHoaDon2 = hoadonservice.getList2(1);
                loadTableHoaDon2(listHoaDon2);
            } else if (cboTrangThai2.getSelectedIndex() > 0) {
                int trangthai = cboTrangThai2.getSelectedIndex();
                modelHoaDon2.setRowCount(0);
                listHoaDon2 = hoadonservice.getList2byTrangThai(trangthai - 1, 1);
                loadTableHoaDon2(listHoaDon2);
            }
        }
        if (cboHinhThucTT2.getSelectedIndex() > 0) {
            if (cboTrangThai2.getSelectedIndex() == 0) {
                int hinhthuctt = cboHinhThucTT2.getSelectedIndex();
                modelHoaDon2.setRowCount(0);
                listHoaDon2 = hoadonservice.getList2byHinhThucThanhToan(hinhthuctt - 1, 1);
                loadTableHoaDon2(listHoaDon2);
            } else if (cboTrangThai2.getSelectedIndex() > 0) {
                int hinhthuctt = cboHinhThucTT2.getSelectedIndex();
                int trangthaihd = cboTrangThai2.getSelectedIndex();
                modelHoaDon2.setRowCount(0);
                listHoaDon2 = hoadonservice.getList2byAllTT(trangthaihd - 1, hinhthuctt - 1, 1);
                loadTableHoaDon2(listHoaDon2);
            }
        }
    }//GEN-LAST:event_cboHinhThucTT2ActionPerformed

    private void btnLoadByTime2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadByTime2ActionPerformed
        resetTableHDCT2();
        if (txtTime3.datePicker.toString().trim().isEmpty() || txtTime3.timePicker.toString().trim().isEmpty()
                || txtTime4.datePicker.toString().trim().isEmpty() || txtTime4.timePicker.toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ ngày và giờ");
            return;
        }
        loadTblHD2byTime();
    }//GEN-LAST:event_btnLoadByTime2ActionPerformed

    private void btnLoadByTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadByTimeActionPerformed
        resetTableHDCT();
        if (txtTime1.datePicker.toString().trim().isEmpty() || txtTime1.timePicker.toString().trim().isEmpty()
                || txtTime2.datePicker.toString().trim().isEmpty() || txtTime2.timePicker.toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ ngày và giờ");
            return;
        }
        loadTblHD1byTime();
    }//GEN-LAST:event_btnLoadByTimeActionPerformed

    private void btnXuatDachSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatDachSachActionPerformed
        try {
            if (listHoaDon == null) {
                JOptionPane.showMessageDialog(this, "Danh sách trống");
                return;
            }
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Danh Sách Hóa Đơn");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(1);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã HD");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Ngày tạo");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Ngày thanh toán");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Tổng tiền");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Mã NV");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Tên KH");

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Lý do");

            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Trạng thái");
            int index = 2;
            int x = 1;

            for (HdHoaDonResponse1 h : listHoaDon) {
                row = sheet.createRow(index);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(x);

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(h.getMa());

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(String.valueOf(h.getNgayTao()));

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(h.getNgayThanhToanStr());

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(h.getThanhTienStr());

                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(h.getMaNV());

                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(h.getTenKHStr());

                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue(h.getLyDoStr());

                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue(h.getTT());
                x++;
                index++;
            }
            try {
                FileOutputStream outputStream = new FileOutputStream("DanhSachHoaDon" + Calendar.getInstance().getTimeInMillis() + ".xlsx");
                workbook.write(outputStream);
                workbook.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(this, "Xuất danh sách thành công");
    }//GEN-LAST:event_btnXuatDachSachActionPerformed

    private void btnXemchitietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemchitietActionPerformed
        int row = this.tblHoadon.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hóa đơn");
            return;
        }
        String ma = tblHoadon.getValueAt(row, 1).toString();
        for (HdHoaDonResponse1 h : listHoaDon) {
            if (h.getMa().equals(ma)) {
                lblMaHD.setText(h.getMa());
                lblNgayTao.setText(String.valueOf(h.getNgayTao()));
                lblNgayThanhToan.setText(String.valueOf(h.getNgayThanhToanStr()));
                lblHinhThucThanhToan.setText(h.getHinhThucTT());
                lblTongtien.setText(String.valueOf(h.getThanhTienStr()));
                lblTienkhachCK.setText(String.valueOf(h.getTienKhachCKStr()));
                lblTienkhachtra.setText(String.valueOf(h.getTienKhachTraStr()));
                lblTienthua.setText(String.valueOf(h.getTienThuaStr()));
                lblMaNV.setText(h.getMaNV());
                lblTenNV.setText(h.getTenNV());
                lblTenKhachHang.setText(h.getTenKHStr());
                lblDiaChi.setText(h.getDiaChiStr());
                lblSDT.setText(h.getSDTStr());
                lblTrangthai.setText(h.getTT());
            }
        }
        chiTietHoaDon.setSize(387, 578);
        chiTietHoaDon.setVisible(true);
        chiTietHoaDon.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnXemchitietActionPerformed

    private void btnXemchitiet2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemchitiet2ActionPerformed
        int row = this.tblHD2.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hóa đơn");
            return;
        }
        String ma = this.tblHD2.getValueAt(row, 1).toString();
//        HdHoaDonResponse2 h = listHoaDon2.get(row);
        for (HdHoaDonResponse2 h : listHoaDon2) {
            if (h.getMa().equals(ma)) {
                lblHD2.setText(h.getMa());
                lblNgayTao2.setText(String.valueOf(h.getNgayTao()));
                lblNgayThanhToan2.setText(String.valueOf(h.getNgayThanhToanStr()));
                lblHinhthucthanhtoan2.setText(h.getHinhThucTT());
                lblTongtien2.setText(String.valueOf(h.getThanhTienStr()));
                lblTienkhachCk2.setText(String.valueOf(h.getTienKhachCKStr()));
                lblTienkhachtra2.setText(String.valueOf(h.getTienKhachTraStr()));
                lblTienthua2.setText(String.valueOf(h.getTienThuaStr()));
                lblMaNV2.setText(h.getMaNV());
                lblTenNV2.setText(h.getTenNV());
                lblDiachi2.setText(h.getDiaChiStr());
                lblSDT2.setText(h.getSDTStr());
                lblTennguoinhan.setText(h.getTenKHStr());
                lblSDTnguoiship.setText(h.getSDTShipStr());
                lblTennguoiship.setText(h.getTenNguoiShipStr());
                lblTrangthai2.setText(h.getTT());
                lblNgayship.setText(String.valueOf(h.getNgayShipStr()));
                lblNgaynhan.setText(String.valueOf(h.getNgayNhanStr()));
                lblTienship.setText(String.valueOf(h.getTienshipStr()));
            }
        }
        chiTietHoaDon2.setSize(554, 420);
        chiTietHoaDon2.setVisible(true);
        chiTietHoaDon2.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnXemchitiet2ActionPerformed

    private void btnXuatDanhSach2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatDanhSach2ActionPerformed
        try {
            if (listHoaDon2 == null) {
                JOptionPane.showMessageDialog(this, "Danh sách trống");
                return;
            }
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Danh Sách Hóa Đơn Đặt Hàng");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(1);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã HD");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Ngày tạo");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Ngày thanh toán");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Tổng tiền");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Mã NV");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Tên người nhận");

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Địa chỉ");

            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Tên người ship");

            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("Ngày ship");

            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue("Ngày nhận");

            cell = row.createCell(11, CellType.STRING);
            cell.setCellValue("Lý do");

            cell = row.createCell(12, CellType.STRING);
            cell.setCellValue("Trạng thái");
            int index = 2;
            int x = 1;

            for (HdHoaDonResponse2 h : listHoaDon2) {
                row = sheet.createRow(index);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(x);

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(h.getMa());

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(String.valueOf(h.getNgayTao()));

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(h.getNgayThanhToanStr());

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(h.getThanhTienStr());

                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(h.getMaNV());

                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(h.getTenKHStr());

                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue(h.getDiaChiStr());

                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue(h.getTenNguoiShipStr());

                cell = row.createCell(9, CellType.STRING);
                cell.setCellValue(h.getNgayShipStr());

                cell = row.createCell(10, CellType.STRING);
                cell.setCellValue(h.getNgayNhanStr());

                cell = row.createCell(11, CellType.STRING);
                cell.setCellValue(h.getLyDoStr());

                cell = row.createCell(12, CellType.STRING);
                cell.setCellValue(h.getTT());
                x++;
                index++;
            }
            try {
                FileOutputStream outputStream = new FileOutputStream("DanhSachHoaDonDatHang" + Calendar.getInstance().getTimeInMillis() + ".xlsx");
                workbook.write(outputStream);
                workbook.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(this, "Xuất danh sách thành công");
    }//GEN-LAST:event_btnXuatDanhSach2ActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        tranghientai++;
        System.out.println(tranghientai);
        if (tranghientai > tongsoTrang - 1) {
            tranghientai = tongsoTrang - 1;
        }
        loadTableHoaDon(listHoaDon);
        lblRecordHD.setText(tranghientai + 1 + "/" + tongsoTrang);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        tranghientai = 0;
        loadTableHoaDon(listHoaDon);
        lblRecordHD.setText(tranghientai + 1 + "/" + tongsoTrang);

    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        tranghientai = tongsoTrang - 1;
        loadTableHoaDon(listHoaDon);
        lblRecordHD.setText(tranghientai + 1 + "/" + tongsoTrang);

    }//GEN-LAST:event_btnLastActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        tranghientai--;
        if (tranghientai < 0) {
            tranghientai = 0;
        }
        loadTableHoaDon(listHoaDon);
        lblRecordHD.setText(tranghientai + 1 + "/" + tongsoTrang);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnFirst2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirst2ActionPerformed
        tranghientai = 0;
        lblRecordHD2.setText(tranghientai + 1 + "/" + tongsoTrang);

        loadTableHoaDon2(listHoaDon2);
    }//GEN-LAST:event_btnFirst2ActionPerformed

    private void btnNext2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext2ActionPerformed
        tranghientai++;
        if (tranghientai > tongsoTrang - 1) {
            tranghientai = tongsoTrang - 1;
        }
        lblRecordHD2.setText(tranghientai + 1 + "/" + tongsoTrang);
        loadTableHoaDon2(listHoaDon2);
    }//GEN-LAST:event_btnNext2ActionPerformed

    private void btnLast2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLast2ActionPerformed
        tranghientai = tongsoTrang - 1;
        lblRecordHD2.setText(tranghientai + 1 + "/" + tongsoTrang);
        loadTableHoaDon2(listHoaDon2);
    }//GEN-LAST:event_btnLast2ActionPerformed

    private void btnBack2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack2ActionPerformed
        tranghientai--;
        if (tranghientai < 0) {
            tranghientai = 0;
        }
        lblRecordHD2.setText(tranghientai + 1 + "/" + tongsoTrang);
        loadTableHoaDon2(listHoaDon2);
    }//GEN-LAST:event_btnBack2ActionPerformed

    private void btnHuy2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuy2ActionPerformed
        int row = this.tblHD2.getSelectedRow();
        boolean check = false;
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn 1 hóa đơn");
            return;
        }
        String ma = this.tblHD2.getValueAt(row, 1).toString();
        String id = hoadonservice.findIdbyMa(ma);
        for (HdHoaDonResponse2 h : listHoaDon2) {
            if (h.getId().equals(id)) {
                if (h.getTrangThai() == 1) {
                    JOptionPane.showMessageDialog(this, "Hóa đơn này đã bị hủy trước đó");
                    return;
                }
                if (h.getTrangThai() == 7) {
                    JOptionPane.showMessageDialog(this, "Không thể hủy hóa đơn đã trả hàng");
                    return;
                }
                String lydo = JOptionPane.showInputDialog("Lý do hủy:");
                if (lydo == null || lydo.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Bạn chưa nhập lý do");
                    return;
                }
                if (lydo.length() > 255) {
                    JOptionPane.showMessageDialog(this, "Lý do không được vượt quá 255 kí tự");
                    return;
                }
                hoadonservice.updateTTHoaDon(h.getId(), lydo);
                if (listHDCT != null) {
                    for (HdHoaDonChiTietResponse1 hdct : listHDCT) {
                        String idsp = hoadonservice.findIdSPbyHDCT(hdct.getId());
                        hoadonservice.updateSoLuong(idsp, hdct.getSoLuong());
                        System.out.
                                println(hdct.getId());
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(this, "Hủy thành công hóa đơn " + ma);
        listHoaDon2 = hoadonservice.getList2(1);
        loadTableHoaDon2(listHoaDon2);
    }//GEN-LAST:event_btnHuy2ActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        int row = this.tblHoadon.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn 1 hóa đơn");
            return;
        }
        String ma = this.tblHoadon.getValueAt(row, 1).toString();
        String id = hoadonservice.findIdbyMa(ma);
        for (HdHoaDonResponse1 h : listHoaDon) {
            if (h.getId().equals(id)) {
                if (h.getTrangThai() == 1) {
                    JOptionPane.showMessageDialog(this, "Hóa đơn này đã bị hủy trước đó");
                    return;
                }
                if (h.getTrangThai() == 7) {
                    JOptionPane.showMessageDialog(this, "Không thể hủy hóa đơn đã trả hàng");
                    return;
                }
                String lydo = JOptionPane.showInputDialog("Lý do hủy:");
                if (lydo == null || lydo.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Bạn chưa nhập lý do");
                    return;
                }
                if (lydo.length() > 255) {
                    JOptionPane.showMessageDialog(this, "Lý do không được vượt quá 255 kí tự");
                    return;
                }
                hoadonservice.updateTTHoaDon(h.getId(), lydo);
                if (listHDCT != null) {
                    for (HdHoaDonChiTietResponse1 hdct : listHDCT) {
                        String idsp = hoadonservice.findIdSPbyHDCT(hdct.getId());
                        hoadonservice.updateSoLuong(idsp, hdct.getSoLuong());
                        System.out.println(hdct.getId());
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(this, "Hủy thành công hóa đơn " + ma);
        listHoaDon = hoadonservice.getList(0);
        loadTableHoaDon(listHoaDon);

    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonActionPerformed
        try {
            int row = this.tblHoadon.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Chọn 1 hóa đơn để in");
                return;
            }
            String ma = this.tblHoadon.getValueAt(row, 1).toString();
            HoaDon hoaDon = hoadonservice.findHdByMa(ma);
            if (hoaDon.getTrangThai() == 2 || hoaDon.getTrangThai() == 5) {
                System.out.println(hoaDon.getMa());
                ExportPdfHoaDon export = new ExportPdfHoaDon();
                export.exportBill(hoaDon, listHDCT);
                JOptionPane.showMessageDialog(this, "In hóa đơn thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Chọn 1 hóa đơn đã thanh toán hoặc đã giao");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnInHoaDonActionPerformed

    private void btnInHoaDon2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDon2ActionPerformed
        try {
            int row = this.tblHD2.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Chọn 1 hóa đơn để in");
                return;
            }
            String ma = this.tblHD2.getValueAt(row, 1).toString();
            HoaDon hoaDon = hoadonservice.findHdByMa(ma);
            if (hoaDon.getTrangThai() == 2 || hoaDon.getTrangThai() == 5) {
                System.out.println(hoaDon.getMa());
                ExportPdfHoaDon export = new ExportPdfHoaDon();
                export.exportBill2(hoaDon, listHDCT);
                JOptionPane.showMessageDialog(this, "In hóa đơn thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Chọn 1 hóa đơn đã thanh toán hoặc đã giao");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnInHoaDon2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBack2;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnFirst2;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnHuy2;
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnInHoaDon2;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLast2;
    private javax.swing.JButton btnLoadByTime;
    private javax.swing.JButton btnLoadByTime2;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnNext2;
    private javax.swing.JButton btnXemchitiet;
    private javax.swing.JButton btnXemchitiet2;
    private javax.swing.JButton btnXuatDachSach;
    private javax.swing.JButton btnXuatDanhSach2;
    private javax.swing.JComboBox<String> cboHinhThucTT2;
    private javax.swing.JComboBox<String> cboHinhthucthanhtoan;
    private javax.swing.JComboBox<String> cboTrangThai2;
    private javax.swing.JComboBox<String> cboTrangthaithanhtoan;
    private javax.swing.JFrame chiTietHoaDon;
    private javax.swing.JFrame chiTietHoaDon2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblDiachi2;
    private javax.swing.JLabel lblHD2;
    private javax.swing.JLabel lblHinhThucThanhToan;
    private javax.swing.JLabel lblHinhthucthanhtoan2;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblMaNV2;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblNgayTao2;
    private javax.swing.JLabel lblNgayThanhToan;
    private javax.swing.JLabel lblNgayThanhToan2;
    private javax.swing.JLabel lblNgaynhan;
    private javax.swing.JLabel lblNgayship;
    private javax.swing.JLabel lblRecordHD;
    private javax.swing.JLabel lblRecordHD2;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblSDT2;
    private javax.swing.JLabel lblSDTnguoiship;
    private javax.swing.JLabel lblTenKhachHang;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblTenNV2;
    private javax.swing.JLabel lblTennguoinhan;
    private javax.swing.JLabel lblTennguoiship;
    private javax.swing.JLabel lblTienkhachCK;
    private javax.swing.JLabel lblTienkhachCk2;
    private javax.swing.JLabel lblTienkhachtra;
    private javax.swing.JLabel lblTienkhachtra2;
    private javax.swing.JLabel lblTienship;
    private javax.swing.JLabel lblTienthua;
    private javax.swing.JLabel lblTienthua2;
    private javax.swing.JLabel lblTongtien;
    private javax.swing.JLabel lblTongtien2;
    private javax.swing.JLabel lblTrangthai;
    private javax.swing.JLabel lblTrangthai2;
    private javax.swing.JTable tblHD2;
    private javax.swing.JTable tblHoaDonChiTiet2;
    private javax.swing.JTable tblHoadon;
    private javax.swing.JTable tblHoadonchitiet;
    private javax.swing.JTabbedPane tpb1;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch2;
    private com.github.lgooddatepicker.components.DateTimePicker txtTime1;
    private com.github.lgooddatepicker.components.DateTimePicker txtTime2;
    private com.github.lgooddatepicker.components.DateTimePicker txtTime3;
    private com.github.lgooddatepicker.components.DateTimePicker txtTime4;
    // End of variables declaration//GEN-END:variables
}
