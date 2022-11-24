/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package core.view;

import core.quanly.repository.CTSanPhamRepository;
import core.quanly.service.CTSanPhamService;
import core.quanly.service.HangService;
import core.quanly.service.KhuyenMaiService;
import core.quanly.service.SPKhuyenMaiService;
import core.quanly.service.SanPhamService;
import core.quanly.service.impl.CTSanPhamServiceImpl;
import core.quanly.service.impl.KhuyenMaiImpl;
import core.quanly.service.impl.SPHangServiceImpl;
import core.quanly.service.impl.SPKhuyenMaiServiceImpl;
import core.quanly.service.impl.SanPhamserviceImpl;
import core.quanly.viewmodel.CTSanPhamResponse;
import core.quanly.viewmodel.SanPhamResponse;
import crud.service.KichThuocService;
import crud.service.MauSacService;
import crud.service.impl.KichThuocServiceImpl;
import crud.service.impl.MauSacServiceImpl;
import domainmodels.ChiTietSP;
import domainmodels.Hang;
import domainmodels.KhuyenMai;
import domainmodels.KichThuoc;
import domainmodels.MauSac;
import domainmodels.SanPham;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import core.quanly.viewmodel.SPHangResponse;
import core.quanly.viewmodel.SPKichThuocResponse;
import core.quanly.viewmodel.SPMauSacResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

/**
 *
 * @author thangncph26123
 */
public class ViewSanPham extends javax.swing.JPanel {

    private static final String FILE_NAME = "/tmp/itext.pdf";
    private final SanPhamService sanPhamService;
    private final CTSanPhamService ctsanPhamService;
    private final HangService hangService;
    private final MauSacService mauSacService;
    private final KichThuocService kichThuocService;
    private final SPKhuyenMaiService khuyenMaiService;
    DefaultTableModel dtm;
    DefaultTableModel dtm1;
    DefaultTableModel dtmshowctsp;
    DefaultTableModel dtmThuocTinh;
    DefaultComboBoxModel dcbcHang;
    DefaultComboBoxModel dcbcMauSac;
    DefaultComboBoxModel dcbcKichThuoc;
    DefaultComboBoxModel dcbcKhuyenMai;
    DefaultComboBoxModel dcbcSanPham;
    List<Hang> lstHang = new ArrayList<>();
    List<SPHangResponse> lstHangRepon = new ArrayList<>();
    List<MauSac> lstMauSac = new ArrayList<>();
    List<SPMauSacResponse> lstMauSacRespon = new ArrayList<>();
    List<KichThuoc> lstKichThuoc = new ArrayList<>();
    List<SPKichThuocResponse> lstKichThuocRepon = new ArrayList<>();
    List<KhuyenMai> lstkhuyenMai = new ArrayList<>();
    List<SanPham> lstSanPham = new ArrayList<>();
    List<SanPhamResponse> lstSpViewModel = new ArrayList<>();
    List<CTSanPhamResponse> lstctSpViewModel = new ArrayList<>();

    public ViewSanPham() {
        initComponents();
        sanPhamService = new SanPhamserviceImpl();
        ctsanPhamService = new CTSanPhamServiceImpl();
        hangService = new SPHangServiceImpl();
        mauSacService = new MauSacServiceImpl();
        kichThuocService = new KichThuocServiceImpl();
        khuyenMaiService = new SPKhuyenMaiServiceImpl();
        dtm = (DefaultTableModel) tblSanPhamm.getModel();
        dtm1 = (DefaultTableModel) tblChiTietSP.getModel();
        dtmThuocTinh = (DefaultTableModel) tblThuocTinhSP.getModel();
        dcbcHang = (DefaultComboBoxModel) cbbHangct.getModel();
        dcbcMauSac = (DefaultComboBoxModel) cbbMauSac.getModel();
        dcbcKichThuoc = (DefaultComboBoxModel) cbbKichThuocct.getModel();
        dcbcKhuyenMai = (DefaultComboBoxModel) cbbKhuyenMaict.getModel();
        dcbcSanPham = (DefaultComboBoxModel) cbbSanPham.getModel();
        lstHang = hangService.getAll();
        lstMauSac = mauSacService.getAll();
        lstKichThuoc = kichThuocService.getAll();
        lstkhuyenMai = khuyenMaiService.getAll();
        lstSanPham = sanPhamService.getAll();
        lstSpViewModel = sanPhamService.getAllViewModel();
        lstctSpViewModel = ctsanPhamService.getAllViewModel();
        ShowDataComboBoxHang(lstHang);
        ShowDataComboBoxMauSac(lstMauSac);
        ShowDataComboBoxKichThuoc(lstKichThuoc);
        ShowDataComboBoxKhuyenMai(lstkhuyenMai);
        ShowDataComboBoxSanPham(lstSanPham);
        LoadToTableSp(lstSpViewModel);
        LoadToTableCTSanPham(lstctSpViewModel);
        int index = 0;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JTextField();
        txtTenSanPham = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnChiTietSanPham = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPhamm = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtTimKiemSp = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtSoLuongTon = new javax.swing.JTextField();
        txtMaSpct = new javax.swing.JTextField();
        txtMaVach = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaMoTa = new javax.swing.JTextArea();
        cbbKhuyenMaict = new javax.swing.JComboBox<>();
        cbbHangct = new javax.swing.JComboBox<>();
        cbbKichThuocct = new javax.swing.JComboBox<>();
        cbbMauSacct = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        btnUpdateCTSP = new javax.swing.JButton();
        btnClearCtsp = new javax.swing.JButton();
        btXuatFile = new javax.swing.JButton();
        cbbSanPham = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        cbbMauSac = new javax.swing.JComboBox<>();
        btnAddCTSP = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txtFindCTSanPham = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChiTietSP = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMaThuocTinh = new javax.swing.JTextField();
        txtTenThuocTinh = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        radiomauSac = new javax.swing.JRadioButton();
        radioHang = new javax.swing.JRadioButton();
        radioKichThuoc = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jPanel11 = new javax.swing.JPanel();
        btnThemThuocTinh = new javax.swing.JButton();
        btnSuaThuocTinh = new javax.swing.JButton();
        btnLamMoiThuocTinh = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblThuocTinhSP = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1252, 747));

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1252, 747));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N
        jPanel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Mã sản phẩm:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Tên sản phẩm:");

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThem.setBackground(new java.awt.Color(153, 204, 255));
        btnThem.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(153, 204, 255));
        btnSua.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnChiTietSanPham.setBackground(new java.awt.Color(153, 204, 255));
        btnChiTietSanPham.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnChiTietSanPham.setText("Chi tiết sản phẩm");
        btnChiTietSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietSanPhamActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(153, 204, 255));
        btnClear.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnClear.setText("Làm mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnClear)
                    .addComponent(btnThem)
                    .addComponent(btnChiTietSanPham)
                    .addComponent(btnSua))
                .addGap(30, 30, 30))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnChiTietSanPham, btnClear, btnSua, btnThem});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem)
                .addGap(28, 28, 28)
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(btnChiTietSanPham)
                .addGap(26, 26, 26)
                .addComponent(btnClear)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(82, 82, 82)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(1300, 154));

        tblSanPhamm.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tblSanPhamm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm"
            }
        ));
        tblSanPhamm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhammMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPhamm);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Tìm kiếm sản phẩm");

        txtTimKiemSp.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemSpCaretUpdate(evt);
            }
        });
        txtTimKiemSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemSpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel9)
                        .addGap(45, 45, 45)
                        .addComponent(txtTimKiemSp))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTimKiemSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1272, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(83, 83, 83))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("SẢN PHẨM", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Sản phẩm:");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setText("Mã sản phẩm CT:");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("Số lượng tồn:");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setText("Giá bán:");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setText("Mô tả:");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel15.setText("Màu sắc:");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel16.setText("Kích thước:");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel17.setText("Hãng:");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setText("Khuyến mãi:");

        txaMoTa.setColumns(20);
        txaMoTa.setRows(5);
        jScrollPane2.setViewportView(txaMoTa);

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnUpdateCTSP.setBackground(new java.awt.Color(153, 204, 255));
        btnUpdateCTSP.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnUpdateCTSP.setText("Sửa");
        btnUpdateCTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateCTSPMouseClicked(evt);
            }
        });
        btnUpdateCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCTSPActionPerformed(evt);
            }
        });

        btnClearCtsp.setBackground(new java.awt.Color(153, 204, 255));
        btnClearCtsp.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnClearCtsp.setText("Làm mới");
        btnClearCtsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearCtspActionPerformed(evt);
            }
        });

        btXuatFile.setBackground(new java.awt.Color(153, 204, 255));
        btXuatFile.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btXuatFile.setText("Xuất File");
        btXuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXuatFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btXuatFile)
                    .addComponent(btnClearCtsp)
                    .addComponent(btnUpdateCTSP))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel12Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btXuatFile, btnClearCtsp, btnUpdateCTSP});

        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(btnUpdateCTSP)
                .addGap(37, 37, 37)
                .addComponent(btnClearCtsp)
                .addGap(39, 39, 39)
                .addComponent(btXuatFile)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel19.setText("Mã vạch:");

        btnAddCTSP.setBackground(new java.awt.Color(153, 204, 255));
        btnAddCTSP.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnAddCTSP.setText("Thêm");
        btnAddCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCTSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaSpct)
                            .addComponent(txtSoLuongTon)
                            .addComponent(cbbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(cbbMauSacct, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbbKichThuocct, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbHangct, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbKhuyenMaict, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaVach, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbMauSac, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnAddCTSP))
                .addGap(79, 79, 79)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(cbbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtMaSpct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(cbbMauSacct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(cbbKichThuocct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(cbbHangct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(cbbKhuyenMaict, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(txtMaVach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAddCTSP)))))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel23.setText("Tìm kiếm");

        txtFindCTSanPham.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFindCTSanPhamCaretUpdate(evt);
            }
        });

        tblChiTietSP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblChiTietSP.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tblChiTietSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sản phẩm", "Mã chi tiết sản phẩm", "Màu sắc", "Kích thước", "Hãng", "Khuyến mãi", "Mô tả", "Số lượng tồn", "Giá bán", "Mã vạch"
            }
        ));
        tblChiTietSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietSPMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblChiTietSP);

        jButton1.setBackground(new java.awt.Color(204, 255, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Hoạt động");

        jButton2.setBackground(new java.awt.Color(255, 204, 204));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("Không hoạt động");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)))
                        .addGap(33, 33, 33))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(txtFindCTSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(541, Short.MAX_VALUE))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtFindCTSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 236, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("CHI TIẾT SẢN PHẨM", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thuộc tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Mã thuộc tính: ");

        jLabel5.setText("Tên thuộc tính:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(44, 44, 44)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaThuocTinh)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup1.add(radiomauSac);
        radiomauSac.setText("Màu sắc");
        radiomauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radiomauSacMouseClicked(evt);
            }
        });

        buttonGroup1.add(radioHang);
        radioHang.setText("Hãng");
        radioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioHangMouseClicked(evt);
            }
        });

        buttonGroup1.add(radioKichThuoc);
        radioKichThuoc.setText("Kích thước");
        radioKichThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioKichThuocMouseClicked(evt);
            }
        });

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setText("jRadioButton4");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radiomauSac)
                    .addComponent(radioKichThuoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioHang)
                    .addComponent(jRadioButton4))
                .addGap(70, 70, 70))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radiomauSac)
                    .addComponent(radioHang))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(radioKichThuoc))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jRadioButton4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThemThuocTinh.setBackground(new java.awt.Color(153, 255, 255));
        btnThemThuocTinh.setText("Thêm");

        btnSuaThuocTinh.setBackground(new java.awt.Color(153, 255, 255));
        btnSuaThuocTinh.setText("Sửa");

        btnLamMoiThuocTinh.setBackground(new java.awt.Color(153, 255, 255));
        btnLamMoiThuocTinh.setText("Làm mới");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLamMoiThuocTinh)
                    .addComponent(btnSuaThuocTinh)
                    .addComponent(btnThemThuocTinh))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        jPanel11Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLamMoiThuocTinh, btnSuaThuocTinh, btnThemThuocTinh});

        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(btnThemThuocTinh)
                .addGap(50, 50, 50)
                .addComponent(btnSuaThuocTinh)
                .addGap(56, 56, 56)
                .addComponent(btnLamMoiThuocTinh)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tblThuocTinhSP.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tblThuocTinhSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã thuộc tính", "Tên thuộc tính"
            }
        ));
        jScrollPane4.setViewportView(tblThuocTinhSP);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("THUỘC TÍNH SẢN PHẨM", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void LoadToTableSp(List<SanPhamResponse> lst) {
//                List<SanPhamResponse> list = sanPhamService.getAllViewModel();
//        lstSpViewModel = sanPhamService.getAllViewModel();
//        if (lstSpViewModel == null) {
//            JOptionPane.showMessageDialog(this, "Error");
//        } else if (lstSpViewModel.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Danh sách rỗng");
//        } else {
        dtm.setRowCount(0);
        int index = 1;
        for (SanPhamResponse ap : lstSpViewModel) {
            dtm.addRow(ap.toDataRow(index));
            index++;
        }
    }

    private void LoadToTableCTSanPham(List<CTSanPhamResponse> listctspRespon) {
//        List<CTSanPhamResponse> lstCTSanPhamViewModels = ctsanPhamService.getAllViewModel();
//        if (lstCTSanPhamViewModels == null) {
//            JOptionPane.showMessageDialog(this, "Error");
//        } else if (lstCTSanPhamViewModels.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Danh sách rỗng");
//        } else {
        dtm1.setRowCount(0);
        for (CTSanPhamResponse ct : lstctSpViewModel) {
            dtm1.addRow(ct.toDateRow());
        }

    }

    private void showChiTietSp() {
        int row = this.tblSanPhamm.getSelectedRow();
        if (row == -1) {
            return;
        }
        String maSp = this.tblSanPhamm.getValueAt(row, 0).toString();
        dtmshowctsp = (DefaultTableModel) tblChiTietSP.getModel();
        dtmshowctsp.setRowCount(0);
        lstctSpViewModel = ctsanPhamService.getFormCTSP(maSp);
        for (CTSanPhamResponse ctsp : lstctSpViewModel) {
            dtmshowctsp.addRow(ctsp.toDateRow());
        }
    }

    private void ShowDataComboBoxHang(List<Hang> lstHang) {
        for (Hang hang : lstHang) {
            dcbcHang.addElement(hang.getTen());
        }
    }

    private void ShowDataComboBoxMauSac(List<MauSac> lstMauSac) {
        for (MauSac mauSac : lstMauSac) {
            dcbcMauSac.addElement(mauSac.getTen());
        }
    }

    private void ShowDataComboBoxKichThuoc(List<KichThuoc> lstKichThuoc) {
        for (KichThuoc kichThuoc : lstKichThuoc) {
            dcbcKichThuoc.addElement(kichThuoc.getTen());
        }
    }

    private void ShowDataComboBoxKhuyenMai(List<KhuyenMai> lstKhuyenMai) {
        for (KhuyenMai khuyenMai : lstKhuyenMai) {
            dcbcKhuyenMai.addElement(khuyenMai.getGiaTri());
        }
    }

    private void ShowDataComboBoxSanPham(List<SanPham> lstSanPhamm) {
        for (SanPham sanPham : lstSanPhamm) {
            dcbcSanPham.addElement(sanPham.getTen());
        }
    }


    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            int index = tblSanPhamm.getSelectedRow();
            if (txtMaSanPham.getText().trim().length() > 10) {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm tối đa 10 kí tự");
                return;
            }
            SanPham sp = new SanPham();
            String messsage = sanPhamService.add(getSanPhamByForm());
            lstSpViewModel = sanPhamService.getAllViewModel();
            LoadToTableSp(lstSpViewModel);
            JOptionPane.showMessageDialog(this, "Thêm mới sản phẩm thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtMaSanPham.setText("");
        txtTenSanPham.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnChiTietSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietSanPhamActionPerformed
        jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_btnChiTietSanPhamActionPerformed

    private void tblSanPhammMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhammMouseClicked
        int index = tblSanPhamm.getSelectedRow();
        SanPhamResponse sanPhamViewModel = sanPhamService.getAllViewModel().get(index);
        if (index >= 0) {
            txtMaSanPham.setText(sanPhamViewModel.getMa());
            txtTenSanPham.setText(sanPhamViewModel.getTen());
        }
    }//GEN-LAST:event_tblSanPhammMouseClicked

    private void tblChiTietSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSPMouseClicked
        int index = tblChiTietSP.getSelectedRow();
        CTSanPhamResponse ctSanPhamViewModel = ctsanPhamService.getAllViewModel().get(index);
        if (index >= 0) {
            cbbSanPham.setSelectedItem(ctSanPhamViewModel.getTensp());
            txtMaSpct.setText(ctSanPhamViewModel.getMactsp());
            txtSoLuongTon.setText(ctSanPhamViewModel.getSoLuongTon().toString());
            txtGiaBan.setText(ctSanPhamViewModel.getGiaBan().toString());
            txaMoTa.setText(ctSanPhamViewModel.getMoTa());
            txtMaVach.setText(ctSanPhamViewModel.getMaVach());
            cbbMauSac.setSelectedItem(ctSanPhamViewModel.getMauSac());
            cbbKichThuocct.setSelectedItem(ctSanPhamViewModel.getKichThuoc());
            cbbHangct.setSelectedItem(ctSanPhamViewModel.getHang());
            cbbKhuyenMaict.setSelectedItem(ctSanPhamViewModel.getKhuyenMai());
        }
        showChiTietSp();
    }//GEN-LAST:event_tblChiTietSPMouseClicked

    private void txtTimKiemSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemSpActionPerformed

    }//GEN-LAST:event_txtTimKiemSpActionPerformed

    private void txtTimKiemSpCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemSpCaretUpdate
        try {
            lstSpViewModel = sanPhamService.findSanPhamByMaOrTen(txtTimKiemSp.getText());
            LoadToTableSp(lstSpViewModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtTimKiemSpCaretUpdate

    private void btnUpdateCTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateCTSPMouseClicked
        
    }//GEN-LAST:event_btnUpdateCTSPMouseClicked

    private void btnClearCtspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearCtspActionPerformed
        cbbSanPham.setSelectedIndex(-1);
        txtMaSpct.setText("");
        txtSoLuongTon.setText("");
        txtMaVach.setText("");
        txaMoTa.setText("");
        cbbMauSacct.setSelectedIndex(-1);
        cbbKichThuocct.setSelectedIndex(-1);
        cbbHangct.setSelectedIndex(-1);
        cbbKhuyenMaict.setSelectedIndex(-1);
    }//GEN-LAST:event_btnClearCtspActionPerformed

    private void btnAddCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCTSPActionPerformed
        try {
            int index = tblChiTietSP.getSelectedRow();
            if (txtMaSpct.getText().trim().length() > 10) {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm chi tiet tối đa 10 kí tự");
                return;
            }
            ChiTietSP spct = new ChiTietSP();
            String messsage = ctsanPhamService.add(getChiTietSp());
            JOptionPane.showMessageDialog(this, "Thêm mới sản phẩm chi tiết thành công");
            LoadToTableCTSanPham(lstctSpViewModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddCTSPActionPerformed

    private void btnUpdateCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCTSPActionPerformed
        
    }//GEN-LAST:event_btnUpdateCTSPActionPerformed

    private void btXuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXuatFileActionPerformed
        String path = "";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(this);

        if (x == JFileChooser.APPROVE_OPTION) {
            path = j.getSelectedFile().getPath();
        }
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(path + "sanpham.txt")));
            //open
            document.open();
            PdfPTable tbl = new PdfPTable(10);
            //Add header
            tbl.addCell("Mã sản phẩm");
            tbl.addCell("Tên sản phẩm");
            tbl.addCell("Màu sắc");
            tbl.addCell("Kích thước");
            tbl.addCell("Hãng");
            tbl.addCell("Khuyến mãi");
            tbl.addCell("Mô tả");;

            for (int i = 0; i < tblChiTietSP.getRowCount(); i++) {
                String maSp = tblChiTietSP.getValueAt(i, 0).toString();
                String tenSp = tblChiTietSP.getValueAt(i, 1).toString();
                String mausac = tblChiTietSP.getValueAt(i, 2).toString();
                String kichThuoc = tblChiTietSP.getValueAt(i, 3).toString();
                String hang = tblChiTietSP.getValueAt(i, 4).toString();
                String khuyenMai = tblChiTietSP.getValueAt(i, 5).toString();
                String moTa = tblChiTietSP.getValueAt(i, 6).toString();

                tbl.addCell(maSp);
                tbl.addCell(tenSp);
                tbl.addCell(mausac);
                tbl.addCell(kichThuoc);
                tbl.addCell(hang);
                tbl.addCell(khuyenMai);
                tbl.addCell(moTa);
            }
            document.add(tbl);

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        document.close();
        System.out.println("Done");
    }//GEN-LAST:event_btXuatFileActionPerformed

    private void txtFindCTSanPhamCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFindCTSanPhamCaretUpdate
        try {
            lstctSpViewModel = ctsanPhamService.findByMaOrTen(txtTimKiemSp.getText());
            LoadToTableCTSanPham(lstctSpViewModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtFindCTSanPhamCaretUpdate

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        try {
            SanPham sanPham = getSanPhamByForm();
            int index = tblSanPhamm.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào");
            } else {
                int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không?");
                if (hoi == 0) {
                    sanPham.setId(lstSpViewModel.get(index).getId());
                    String mess = sanPhamService.update(sanPham);
                    lstSpViewModel = sanPhamService.getAllViewModel();
                    LoadToTableSp(lstSpViewModel);
                    JOptionPane.showMessageDialog(this, "Sửa sản phẩm thành công");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaActionPerformed
    
    private void radiomauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radiomauSacMouseClicked
        try {
            LoadToTableMauSac();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_radiomauSacMouseClicked

    private void radioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioHangMouseClicked
        try {
            LoadToTableHang();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_radioHangMouseClicked

    private void radioKichThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioKichThuocMouseClicked
        try {
            LoadToTableKichThuoc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_radioKichThuocMouseClicked

    private void LoadToTableMauSac(){
        dtmThuocTinh.setRowCount(0);
        for (SPMauSacResponse ms : lstMauSacRespon) { 
            dtmThuocTinh.addRow(ms.toDataRow());
        }
    }
    
    private void LoadToTableKichThuoc(){
        dtmThuocTinh.setRowCount(0);
        for (SPKichThuocResponse kt : lstKichThuocRepon) { 
            dtmThuocTinh.addRow(kt.toDataRow());
        }
    }
    
    private void LoadToTableHang(){
        dtmThuocTinh.setRowCount(0);
        for (SPHangResponse h : lstHangRepon) { 
            dtmThuocTinh.addRow(h.toDataRow());
        }
    }
    
    public SanPham getSanPhamByForm() {
        String masp = txtMaSanPham.getText().trim();
        String tensp = txtTenSanPham.getText().trim();
        SanPham sanPham = new SanPham();
        sanPham.setMa(masp);
        sanPham.setTen(tensp);
        return sanPham;
    }

    public ChiTietSP getChiTietSp() {
        ChiTietSP chiTietSp = new ChiTietSP();
        MauSac mauSac = new MauSac();
        mauSac.setTen(cbbMauSac.getSelectedItem().toString());
        Hang hang = new Hang();
        hang.setTen(cbbHangct.getSelectedItem().toString());
        KhuyenMai khuyenMai = new KhuyenMai();
        khuyenMai.setGiaTri(cbbKhuyenMaict.getSelectedItem().toString());
        SanPham sanPham = new SanPham();
        sanPham.setTen(cbbSanPham.getSelectedItem().toString());
        KichThuoc kichThuoc = new KichThuoc();
        kichThuoc.setTen(cbbKichThuocct.getSelectedItem().toString());
        chiTietSp.setMaChiTietSP(txtMaSpct.getText().trim());
        chiTietSp.setMoTa(txaMoTa.getText().trim());
        chiTietSp.setSoLuongTon(Integer.valueOf(txtSoLuongTon.getText().trim()));
        chiTietSp.setGiaBan(BigDecimal.valueOf(Double.valueOf(txtGiaBan.getText().trim())));
        chiTietSp.setMaVach(txtMaVach.getText().trim());

        chiTietSp.setSanPham(sanPham);
        chiTietSp.setHang(hang);
        chiTietSp.setKhuyenMai(khuyenMai);
        chiTietSp.setKichThuoc(kichThuoc);
        chiTietSp.setMauSac(mauSac);

        return chiTietSp;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btXuatFile;
    private javax.swing.JButton btnAddCTSP;
    private javax.swing.JButton btnChiTietSanPham;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClearCtsp;
    private javax.swing.JButton btnLamMoiThuocTinh;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaThuocTinh;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemThuocTinh;
    private javax.swing.JButton btnUpdateCTSP;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbHangct;
    private javax.swing.JComboBox<String> cbbKhuyenMaict;
    private javax.swing.JComboBox<String> cbbKichThuocct;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbMauSacct;
    private javax.swing.JComboBox<String> cbbSanPham;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JRadioButton radioHang;
    private javax.swing.JRadioButton radioKichThuoc;
    private javax.swing.JRadioButton radiomauSac;
    private javax.swing.JTable tblChiTietSP;
    private javax.swing.JTable tblSanPhamm;
    private javax.swing.JTable tblThuocTinhSP;
    private javax.swing.JTextArea txaMoTa;
    private javax.swing.JTextField txtFindCTSanPham;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtMaSpct;
    private javax.swing.JTextField txtMaThuocTinh;
    private javax.swing.JTextField txtMaVach;
    private javax.swing.JTextField txtSoLuongTon;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTenThuocTinh;
    private javax.swing.JTextField txtTimKiemSp;
    // End of variables declaration//GEN-END:variables

}
