
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package core.view;

import core.quanly.repository.CTSanPhamRepository;
import core.quanly.service.CTSanPhamService;
import core.quanly.service.SPChatLieuService;
import core.quanly.service.SPHangService;
import core.quanly.service.SPKhuyenMaiService;
import core.quanly.service.SPKhuyenMaiService;
import core.quanly.service.SPKichThuocService;
import core.quanly.service.SPMauSacService;
import core.quanly.service.SanPhamService;
import core.quanly.service.impl.CTSanPhamServiceImpl;
import core.quanly.service.impl.SPChatLieuServiceImpl;
import core.quanly.service.impl.SPHangServiceImpl;
import core.quanly.service.impl.SPKhuyenMaiServiceImpl;
import core.quanly.service.impl.SPKichThuocServiceImpl;
import core.quanly.service.impl.SPMauSacServiceImpl;
import core.quanly.service.impl.SanPhamserviceImpl;
import core.quanly.viewmodel.CTSanPhamResponse;
import core.quanly.viewmodel.SPChatLieuResponse;
import core.quanly.viewmodel.SanPhamResponse;
import crud.service.MauSacService;
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
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
import core.quanly.viewmodel.SPHangResponse;
import core.quanly.viewmodel.SPKhuyenMaiResponse;
import core.quanly.viewmodel.SPKichThuocResponse;
import core.quanly.viewmodel.SPMauSacResponse;
import crud.service.HangService;
import crud.service.KichThuocService;
import crud.service.impl.HangServiceImpl;
import crud.service.impl.KichThuocServiceImpl;
import domainmodels.ChatLieu;
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

//    private static final String FILE_NAME = "/tmp/itext.pdf";
    private final SanPhamService sanPhamService;
    private final CTSanPhamService ctsanPhamService;
    private final SPChatLieuService chatLieuService;
    private final SPHangService hangService;
    private final SPMauSacService mauSacService;
    private final SPKichThuocService kichThuocService;
    private final SPKhuyenMaiService khuyenMaiService;
    DefaultTableModel dtm;
    DefaultTableModel dtm1;
    DefaultTableModel dtmshowctsp;
    DefaultTableModel dtmThuocTinh;
    DefaultComboBoxModel dcbcChatLieu;
    DefaultComboBoxModel dcbcHang;
    DefaultComboBoxModel dcbcMauSac;
    DefaultComboBoxModel dcbcKichThuoc;
    DefaultComboBoxModel dcbcKhuyenMai;
    DefaultComboBoxModel dcbcSanPham;
    List<Hang> lstHang = new ArrayList<>();
    List<SPHangResponse> lstHangRepon = new ArrayList<>();
    List<ChatLieu> lstChatLieu = new ArrayList<>();
    List<SPChatLieuResponse> lstChatLieuRespon = new ArrayList<>();
    List<MauSac> lstMauSac = new ArrayList<>();
    List<SPMauSacResponse> lstMauSacRespon = new ArrayList<>();
    List<KichThuoc> lstKichThuoc = new ArrayList<>();
    List<SPKichThuocResponse> lstKichThuocRepon = new ArrayList<>();
    List<SPKhuyenMaiResponse> lstKhuyenMaiRepon = new ArrayList<>();
    List<SanPham> lstSanPham = new ArrayList<>();
    List<SanPhamResponse> lstSpViewModel = new ArrayList<>();
    List<CTSanPhamResponse> lstctSpViewModel = new ArrayList<>();

    public ViewSanPham() {
        initComponents();
        sanPhamService = new SanPhamserviceImpl();
        ctsanPhamService = new CTSanPhamServiceImpl();
        chatLieuService = new SPChatLieuServiceImpl();
        hangService = new SPHangServiceImpl();
        mauSacService = new SPMauSacServiceImpl();
        kichThuocService = new SPKichThuocServiceImpl();
        khuyenMaiService = new SPKhuyenMaiServiceImpl();
        dtm = (DefaultTableModel) tblSanPhamm.getModel();
        dtm1 = (DefaultTableModel) tblCTSanPham.getModel();
        dtmThuocTinh = (DefaultTableModel) tblThuocTinhSP.getModel();
        dcbcChatLieu = (DefaultComboBoxModel) cbbChatLieu.getModel();
        dcbcHang = (DefaultComboBoxModel) cbbHangct.getModel();
        dcbcMauSac = (DefaultComboBoxModel) cbbMauSac.getModel();
        dcbcKichThuoc = (DefaultComboBoxModel) cbbKichThuocct.getModel();
        dcbcSanPham = (DefaultComboBoxModel) cbbSanPham.getModel();
        lstHang = hangService.getAll();
        lstChatLieu = chatLieuService.getAll();
        lstMauSac = mauSacService.getAll();
        lstKichThuoc = kichThuocService.getAll();
        lstSanPham = sanPhamService.getAll();
        lstSpViewModel = sanPhamService.getAllViewModel();
        lstctSpViewModel = ctsanPhamService.getAllViewModel();
        ShowDataComboBoxHang(lstHang);
        ShowDataComboBoxMauSac(lstMauSac);
        ShowDataComboBoxKichThuoc(lstKichThuoc);
        ShowDataComboBoxSanPham(lstSanPham);
        ShowDataComboBoxChatLieu(lstChatLieu);
        LoadToTableSp(lstSpViewModel);
        LoadToTableCTSanPham(lstctSpViewModel);
        int index = 0;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnChiTietSanPham = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        txtMaSanPham = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPhamm = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
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
        txtSoLuongTon = new javax.swing.JTextField();
        txtmaCTSP = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaMoTa = new javax.swing.JTextArea();
        cbbHangct = new javax.swing.JComboBox<>();
        cbbKichThuocct = new javax.swing.JComboBox<>();
        cbbMauSac = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnClearr = new javax.swing.JButton();
        btXuatFileExcel = new javax.swing.JButton();
        cbbSanPham = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtMaVach = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cbbChatLieu = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        rdoDangHoatDong = new javax.swing.JRadioButton();
        rdoDungHoatDong = new javax.swing.JRadioButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txtFindCTSanPham = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCTSanPham = new javax.swing.JTable();
        btnTTKoHoatDong = new javax.swing.JButton();
        btnTTHoatDong = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaThuocTinh = new javax.swing.JTextField();
        txtTenThuocTinh = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        radioHang = new javax.swing.JRadioButton();
        radioKichThuoc = new javax.swing.JRadioButton();
        radioMausac = new javax.swing.JRadioButton();
        radioChatLieu = new javax.swing.JRadioButton();
        jPanel15 = new javax.swing.JPanel();
        btnThemThuocTinh = new javax.swing.JButton();
        btnSuaThuocTinh = new javax.swing.JButton();
        btnLamMoiThuocTinh = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblThuocTinhSP = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1292, 784));
        setMinimumSize(new java.awt.Dimension(1292, 784));
        setPreferredSize(new java.awt.Dimension(1292, 784));

        jTabbedPane2.setMaximumSize(new java.awt.Dimension(1292, 784));
        jTabbedPane2.setMinimumSize(new java.awt.Dimension(1292, 784));
        jTabbedPane2.setPreferredSize(new java.awt.Dimension(1292, 784));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1252, 747));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N
        jPanel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
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
                .addContainerGap(44, Short.MAX_VALUE)
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
                .addGap(18, 18, 18)
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(btnChiTietSanPham)
                .addGap(18, 18, 18)
                .addComponent(btnClear)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel2.setText("Tên sản phẩm:");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel1.setText("Mã sản phẩm:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(59, 59, 59)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                    .addComponent(txtTenSanPham))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(227, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

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

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel4.setText("Tìm kiếm:");

        txtTimKiemSp.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemSpCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(0, 16, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemSp, javax.swing.GroupLayout.PREFERRED_SIZE, 1104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimKiemSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("SẢN PHẨM", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N
        jPanel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel10.setText("Mã sản phẩm:");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel11.setText("Mã CTSP:");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel12.setText("Số lượng tồn:");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel13.setText("Giá bán:");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel14.setText("Mô tả:");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel15.setText("Màu sắc:");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel16.setText("Kích thước:");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel17.setText("Hãng:");

        txaMoTa.setColumns(20);
        txaMoTa.setRows(5);
        jScrollPane2.setViewportView(txaMoTa);

        cbbHangct.setBackground(new java.awt.Color(153, 204, 255));

        cbbKichThuocct.setBackground(new java.awt.Color(153, 204, 255));

        cbbMauSac.setBackground(new java.awt.Color(153, 204, 255));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAdd.setBackground(new java.awt.Color(153, 204, 255));
        btnAdd.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(153, 204, 255));
        btnUpdate.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnClearr.setBackground(new java.awt.Color(153, 204, 255));
        btnClearr.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnClearr.setText("Làm mới");
        btnClearr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearrActionPerformed(evt);
            }
        });

        btXuatFileExcel.setBackground(new java.awt.Color(153, 204, 255));
        btXuatFileExcel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btXuatFileExcel.setText("Xuất File");
        btXuatFileExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXuatFileExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btXuatFileExcel)
                    .addComponent(btnClearr)
                    .addComponent(btnUpdate)
                    .addComponent(btnAdd))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel12Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btXuatFileExcel, btnAdd, btnClearr, btnUpdate});

        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnAdd)
                .addGap(33, 33, 33)
                .addComponent(btnUpdate)
                .addGap(37, 37, 37)
                .addComponent(btnClearr)
                .addGap(39, 39, 39)
                .addComponent(btXuatFileExcel)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        cbbSanPham.setBackground(new java.awt.Color(153, 204, 255));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel5.setText("Mã vạch:");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel18.setText("Chất liệu:");

        cbbChatLieu.setBackground(new java.awt.Color(153, 204, 255));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel8.setText("Trạng thái:");

        buttonGroup3.add(rdoDangHoatDong);
        rdoDangHoatDong.setSelected(true);
        rdoDangHoatDong.setText("Đang hoạt động");

        buttonGroup3.add(rdoDungHoatDong);
        rdoDungHoatDong.setText("Dừng hoạt động");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel13)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11)
                                .addComponent(jLabel12))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtSoLuongTon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtmaCTSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbbSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(76, 76, 76)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))))
                .addGap(88, 88, 88)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5)
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addComponent(jLabel15)
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel8))
                .addGap(54, 54, 54)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbbMauSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbbKichThuocct, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbbHangct, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMaVach, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                        .addComponent(cbbChatLieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(rdoDangHoatDong)
                        .addGap(18, 18, 18)
                        .addComponent(rdoDungHoatDong)))
                .addGap(90, 90, 90)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(cbbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtmaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)
                                    .addComponent(cbbHangct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18)
                                    .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(cbbKichThuocct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtMaVach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(rdoDangHoatDong)
                                .addComponent(rdoDungHoatDong))
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel23.setText("Tìm kiếm");

        txtFindCTSanPham.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFindCTSanPhamCaretUpdate(evt);
            }
        });
        txtFindCTSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindCTSanPhamActionPerformed(evt);
            }
        });

        tblCTSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã CTSP", "Mã sản phẩm", "Màu sắc", "Kích thước", "Hãng", "Chất liệu", "Mô tả", "Số lượng tồn", "Giá bán", "Mã vạch", "Trạng thái"
            }
        ));
        tblCTSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblCTSanPham);

        btnTTKoHoatDong.setBackground(new java.awt.Color(153, 204, 255));
        btnTTKoHoatDong.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnTTKoHoatDong.setText("Không hoạt động");
        btnTTKoHoatDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTTKoHoatDongActionPerformed(evt);
            }
        });

        btnTTHoatDong.setBackground(new java.awt.Color(153, 204, 255));
        btnTTHoatDong.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnTTHoatDong.setText("Hoạt động");
        btnTTHoatDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTTHoatDongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnTTHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btnTTKoHoatDong)
                        .addGap(11, 11, 11))
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(jLabel23)
                            .addGap(43, 43, 43)
                            .addComponent(txtFindCTSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(758, 758, 758))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1231, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnTTHoatDong, btnTTKoHoatDong});

        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtFindCTSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTTHoatDong)
                    .addComponent(btnTTKoHoatDong))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("CHI TIẾT SẢN PHẨM", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập thuộc tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel6.setText("Mã thuộc tính:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel7.setText("Tên thuộc tính:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(60, 60, 60)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaThuocTinh)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
                .addContainerGap(184, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        buttonGroup1.add(radioHang);
        radioHang.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        radioHang.setText("Hãng");
        radioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioHangMouseClicked(evt);
            }
        });
        radioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioHangActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioKichThuoc);
        radioKichThuoc.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        radioKichThuoc.setText("Kích thước");
        radioKichThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioKichThuocMouseClicked(evt);
            }
        });

        buttonGroup1.add(radioMausac);
        radioMausac.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        radioMausac.setText("Màu sắc");
        radioMausac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioMausacMouseClicked(evt);
            }
        });

        buttonGroup1.add(radioChatLieu);
        radioChatLieu.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        radioChatLieu.setText("Chất liệu");
        radioChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioChatLieuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(radioKichThuoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(radioHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioMausac)
                    .addComponent(radioChatLieu))
                .addGap(32, 32, 32))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioKichThuoc)
                    .addComponent(radioMausac))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioHang)
                    .addComponent(radioChatLieu))
                .addGap(60, 60, 60))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnThemThuocTinh.setBackground(new java.awt.Color(153, 204, 255));
        btnThemThuocTinh.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnThemThuocTinh.setText("Thêm");
        btnThemThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThuocTinhActionPerformed(evt);
            }
        });

        btnSuaThuocTinh.setBackground(new java.awt.Color(153, 204, 255));
        btnSuaThuocTinh.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnSuaThuocTinh.setText("Sửa");
        btnSuaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThuocTinhActionPerformed(evt);
            }
        });

        btnLamMoiThuocTinh.setBackground(new java.awt.Color(153, 204, 255));
        btnLamMoiThuocTinh.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnLamMoiThuocTinh.setText("Làm mới");
        btnLamMoiThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiThuocTinhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLamMoiThuocTinh)
                    .addComponent(btnSuaThuocTinh)
                    .addComponent(btnThemThuocTinh))
                .addGap(35, 35, 35))
        );

        jPanel15Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLamMoiThuocTinh, btnSuaThuocTinh, btnThemThuocTinh});

        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnThemThuocTinh)
                .addGap(36, 36, 36)
                .addComponent(btnSuaThuocTinh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(btnLamMoiThuocTinh)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách thuộc tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tblThuocTinhSP.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tblThuocTinhSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã thuộc tính", "Tên thuộc tính"
            }
        ));
        tblThuocTinhSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocTinhSPMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblThuocTinhSP);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 38, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("THUỘC TÍNH SẢN PHẨM", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1292, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loadAll(){
        lstSanPham = sanPhamService.getAll();
        ShowDataComboBoxSanPham(lstSanPham);
        LoadToTableMauSac();
        LoadToTableKichThuoc();
        LoadToTableChatLieu();
        LoadToTableHang();
    }
    
    private void LoadToTableSp(List<SanPhamResponse> lstSpViewModel1) {
        dtm.setRowCount(0);
        int index = 1;
        for (SanPhamResponse ap : lstSpViewModel) {
            dtm.addRow(ap.toDataRow(index));
            index++;
        }
    }

    private void LoadToTableCTSanPham(List<CTSanPhamResponse> lstctSpViewModel1) {
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
        String maSp = this.tblSanPhamm.getValueAt(row, 1).toString();
        System.out.println(maSp);
        dtmshowctsp = (DefaultTableModel) tblCTSanPham.getModel();
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

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
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
            showChiTietSp();
        }
    }//GEN-LAST:event_tblSanPhammMouseClicked

    private void tblCTSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTSanPhamMouseClicked
        int index = tblCTSanPham.getSelectedRow();
        CTSanPhamResponse ctSanPhamViewModel = ctsanPhamService.getAllViewModel().get(index);
        if (index >= 0) {
            cbbSanPham.setSelectedItem(ctSanPhamViewModel.getMasp());
            txtmaCTSP.setText(ctSanPhamViewModel.getMactsp());
            txtSoLuongTon.setText(ctSanPhamViewModel.getSoLuongTon().toString());
            txtGiaBan.setText(ctSanPhamViewModel.getGiaBan().toString());
            txaMoTa.setText(ctSanPhamViewModel.getMoTa());
            txtMaVach.setText(ctSanPhamViewModel.getMaVach());
            cbbMauSac.setSelectedItem(ctSanPhamViewModel.getMauSac());
            cbbKichThuocct.setSelectedItem(ctSanPhamViewModel.getKichThuoc());
            cbbHangct.setSelectedItem(ctSanPhamViewModel.getHang());
            cbbChatLieu.setSelectedItem(ctSanPhamViewModel.getChatLieu());

        }
    }//GEN-LAST:event_tblCTSanPhamMouseClicked
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
    
    private void ShowDataComboBoxChatLieu(List<ChatLieu> lstChatLieu){
        for (ChatLieu chatLieu : lstChatLieu) {
            dcbcChatLieu.addElement(chatLieu.getTen());
        }
    }

    private void ShowDataComboBoxSanPham(List<SanPham> lstSanPham) {
        for (SanPham sanPham : lstSanPham) {
            dcbcSanPham.addElement(sanPham.getMa());
        }
    }

    private void btnTTKoHoatDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTTKoHoatDongActionPerformed
        try {
            dtm1 = (DefaultTableModel) tblCTSanPham.getModel();
            dtm1.setRowCount(0);
            lstctSpViewModel = ctsanPhamService.findTrangThai(1);
            for (CTSanPhamResponse ctsp : lstctSpViewModel) {
                dtm1.addRow(ctsp.toDateRow());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnTTKoHoatDongActionPerformed

    private void btnTTHoatDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTTHoatDongActionPerformed
        try {
            dtm1 = (DefaultTableModel) tblCTSanPham.getModel();
            dtm1.setRowCount(0);
            lstctSpViewModel = ctsanPhamService.findTrangThai(0);
            for (CTSanPhamResponse ctsp : lstctSpViewModel) {
                dtm1.addRow(ctsp.toDateRow());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnTTHoatDongActionPerformed

    private void txtTimKiemSpCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemSpCaretUpdate
        // TODO add your handling code here:
        try {
            lstSpViewModel = sanPhamService.findMaOrTen(txtTimKiemSp.getText());
            LoadToTableSp(lstSpViewModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtTimKiemSpCaretUpdate

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        SanPham sp = getSanPhamByForm();

        int chon = tblSanPhamm.getSelectedRow();
        if (chon < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào");
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không?");
        if (hoi != JOptionPane.YES_OPTION) {
            return;
        }
        sp.setId(lstSpViewModel.get(chon).getId());
        String messsage = sanPhamService.update(sp);
        lstSpViewModel = sanPhamService.getAllViewModel();
        LoadToTableSp(lstSpViewModel);
        JOptionPane.showMessageDialog(this, "Sửa sản phẩm thành công");

    }//GEN-LAST:event_btnSuaActionPerformed

    private void radioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioHangActionPerformed

    private void btnClearrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearrActionPerformed
        cbbSanPham.setSelectedItem(-1);
        txtmaCTSP.setText("");
        txtSoLuongTon.setText("");
        txtGiaBan.setText("");
        txaMoTa.setText("");
        cbbMauSac.setSelectedIndex(-1);
        cbbKichThuocct.setSelectedIndex(-1);
        cbbHangct.setSelectedIndex(-1);
        cbbChatLieu.setSelectedIndex(-1);
        txtMaVach.setText("");
    }//GEN-LAST:event_btnClearrActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            String messsage = ctsanPhamService.add(getChiTietSp());
            lstctSpViewModel = ctsanPhamService.getAllViewModel();
            LoadToTableCTSanPham(lstctSpViewModel);
            JOptionPane.showMessageDialog(this, messsage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        ChiTietSP spct = getChiTietSp();

        int chon = tblCTSanPham.getSelectedRow();
        if (chon < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào");
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không?");
        if (hoi != JOptionPane.YES_OPTION) {
            return;
        }
        JOptionPane.showMessageDialog(this, spct.getTrangThaiXoa());
        spct.setId(lstctSpViewModel.get(chon).getMa());
        String messsage = ctsanPhamService.update(spct);
        lstctSpViewModel = ctsanPhamService.getAllViewModel();
        LoadToTableCTSanPham(lstctSpViewModel);
        JOptionPane.showMessageDialog(this, "Sửa sản phẩm thành công");
    }//GEN-LAST:event_btnUpdateActionPerformed

    
    
    private void LoadToTableHang() {
        dtmThuocTinh.setRowCount(0);
        lstHangRepon = hangService.getAllViewModel();
        for (SPHangResponse h : lstHangRepon) {
            dtmThuocTinh.addRow(h.toDateRow());
        }
    }
    private void LoadToTableChatLieu() {
        dtmThuocTinh.setRowCount(0);
        lstChatLieuRespon = chatLieuService.getAllViewModel();
        for (SPChatLieuResponse ch : lstChatLieuRespon) {
            dtmThuocTinh.addRow(ch.toDateRow());
        }
    }

    private void LoadToTableMauSac() {
        dtmThuocTinh.setRowCount(0);
        lstMauSacRespon = mauSacService.getAllViewModel();
        for (SPMauSacResponse h : lstMauSacRespon) {
            dtmThuocTinh.addRow(h.toDateRow());
        }
    }

    private void LoadToTableKichThuoc() {
        dtmThuocTinh.setRowCount(0);
        lstKichThuocRepon = kichThuocService.getAllViewModel();
        for (SPKichThuocResponse h : lstKichThuocRepon) {
            dtmThuocTinh.addRow(h.toDateRow());
        }
    }

    private void btnThemThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThuocTinhActionPerformed
        try {
            if (radioHang.isSelected()) {
                try {
                    Hang hang = new Hang();
                    String messsage = hangService.add(getHangByForm());
                    lstHangRepon = hangService.getAllViewModel();
                    LoadToTableHang();
                    JOptionPane.showMessageDialog(this, "Thêm mới hãng thành công");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (radioKichThuoc.isSelected()) {
                try {
                    KichThuoc kichThuoc = new KichThuoc();
                    String messsage = kichThuocService.add(getKichThuocByForm());
                    lstKichThuocRepon = kichThuocService.getAllViewModel();
                    LoadToTableKichThuoc();
                    JOptionPane.showMessageDialog(this, "Thêm mới kích thước thành công");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if(radioMausac.isSelected()){
                try {
                    MauSac mauSac = new MauSac();
                    String messsage = mauSacService.add(getMauSacByForm());
                    lstMauSacRespon = mauSacService.getAllViewModel();
                    LoadToTableMauSac();
                    JOptionPane.showMessageDialog(this, "Thêm mới màu sắc thành công");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    ChatLieu chatLieu = new ChatLieu();
                    String messsage = chatLieuService.add(getChatLieuByForm());
                    lstChatLieuRespon = chatLieuService.getAllViewModel();
                    LoadToTableChatLieu();
                    JOptionPane.showMessageDialog(this, "Thêm mới chất liệu thành công");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            loadAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemThuocTinhActionPerformed

    private void radioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioHangMouseClicked
        try {
            LoadToTableHang();
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_radioHangMouseClicked

    private void radioKichThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioKichThuocMouseClicked
        try {
            LoadToTableKichThuoc();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_radioKichThuocMouseClicked

    private void radioMausacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioMausacMouseClicked
        try {
            LoadToTableMauSac();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_radioMausacMouseClicked

    private void btnLamMoiThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiThuocTinhActionPerformed
        txtMaThuocTinh.setText("");
        txtTenThuocTinh.setText("");
    }//GEN-LAST:event_btnLamMoiThuocTinhActionPerformed

    private void btnSuaThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThuocTinhActionPerformed
        try {
            if (radioHang.isSelected()) {
                Hang hang = getHangByForm();

                int chon = tblThuocTinhSP.getSelectedRow();
                if (chon < 0) {
                    JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào");
                    return;
                }
                int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không?");
                if (hoi != JOptionPane.YES_OPTION) {
                    return;
                }
                hang.setId(lstHangRepon.get(chon).getId());
                String messsage = hangService.update(hang);
                lstHangRepon = hangService.getAllViewModel();
                LoadToTableHang();
                JOptionPane.showMessageDialog(this, "Sửa thành công");
            } else if (radioKichThuoc.isSelected()) {
                KichThuoc kichThuoc = getKichThuocByForm();

                int chon = tblThuocTinhSP.getSelectedRow();
                if (chon < 0) {
                    JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào");
                    return;
                }
                int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không?");
                if (hoi != JOptionPane.YES_OPTION) {
                    return;
                }
                kichThuoc.setId(lstKichThuocRepon.get(chon).getId());
                String messsage = kichThuocService.update(kichThuoc);
                lstKichThuocRepon = kichThuocService.getAllViewModel();
                LoadToTableKichThuoc();
                JOptionPane.showMessageDialog(this, "Sửa thành công");
            } else if(radioMausac.isSelected()){
                MauSac mauSac = getMauSacByForm();

                int chon = tblThuocTinhSP.getSelectedRow();
                if (chon < 0) {
                    JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào");
                    return;
                }
                int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không?");
                if (hoi != JOptionPane.YES_OPTION) {
                    return;
                }
                mauSac.setId(lstMauSacRespon.get(chon).getId());
                String messsage = mauSacService.update(mauSac);
                lstMauSacRespon = mauSacService.getAllViewModel();
                LoadToTableMauSac();
                JOptionPane.showMessageDialog(this, "Sửa thành công");
            }else{
                ChatLieu chatLieu = getChatLieuByForm();

                int chon = tblThuocTinhSP.getSelectedRow();
                if (chon < 0) {
                    JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào");
                    return;
                }
                int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không?");
                if (hoi != JOptionPane.YES_OPTION) {
                    return;
                }
                chatLieu.setId(lstChatLieuRespon.get(chon).getId());
                String messsage = chatLieuService.update(chatLieu);
                lstChatLieuRespon = chatLieuService.getAllViewModel();
                LoadToTableChatLieu();
                JOptionPane.showMessageDialog(this, "Sửa thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaThuocTinhActionPerformed

    private void txtFindCTSanPhamCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFindCTSanPhamCaretUpdate
        try {
            lstctSpViewModel = ctsanPhamService.findByMaOrTen(txtFindCTSanPham.getText());
            LoadToTableCTSanPham(lstctSpViewModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtFindCTSanPhamCaretUpdate

    private void tblThuocTinhSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhSPMouseClicked
        int row = tblThuocTinhSP.getSelectedRow();
        txtMaThuocTinh.setText(tblThuocTinhSP.getValueAt(row, 0).toString());
        txtTenThuocTinh.setText(tblThuocTinhSP.getValueAt(row, 1).toString());
    }//GEN-LAST:event_tblThuocTinhSPMouseClicked

    private void txtFindCTSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindCTSanPhamActionPerformed

    }//GEN-LAST:event_txtFindCTSanPhamActionPerformed

    private void radioChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioChatLieuMouseClicked
        try {
            LoadToTableChatLieu();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_radioChatLieuMouseClicked

    private void btXuatFileExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXuatFileExcelActionPerformed
        
    }//GEN-LAST:event_btXuatFileExcelActionPerformed

    public SanPham getSanPhamByForm() {
        String masp = txtMaSanPham.getText().trim();
        String tensp = txtTenSanPham.getText().trim();
        SanPham sanPham = new SanPham();
        sanPham.setMa(masp);
        sanPham.setTen(tensp);
        return sanPham;
    }

    public Hang getHangByForm() {
        String maHang = txtMaThuocTinh.getText().trim();
        String tenHang = txtTenThuocTinh.getText().trim();
        Hang hang = new Hang();
        hang.setMa(maHang);
        hang.setTen(tenHang);
        return hang;
    }

    public MauSac getMauSacByForm() {
        String maMauSac = txtMaThuocTinh.getText().trim();
        String tenMausac = txtTenThuocTinh.getText().trim();
        MauSac mausac = new MauSac();
        mausac.setMa(maMauSac);
        mausac.setTen(tenMausac);
        return mausac;
    }

    public KichThuoc getKichThuocByForm() {
        String maKichThuoc = txtMaThuocTinh.getText().trim();
        String tenKichThuoc = txtTenThuocTinh.getText().trim();
        KichThuoc kichThuoc = new KichThuoc();
        kichThuoc.setMa(maKichThuoc);
        kichThuoc.setTen(tenKichThuoc);
        return kichThuoc;
    }
    
    public ChatLieu getChatLieuByForm() {
        String maChatLieu = txtMaThuocTinh.getText().trim();
        String tenChatLieu = txtTenThuocTinh.getText().trim();
        ChatLieu chatLieu = new ChatLieu();
        chatLieu.setMa(maChatLieu);
        chatLieu.setTen(tenChatLieu);
        return chatLieu;
    }

    public ChiTietSP getChiTietSp() {
        ChiTietSP chiTietSp = new ChiTietSP();
        int indexmau = cbbMauSac.getSelectedIndex();
        int indexkt = cbbKichThuocct.getSelectedIndex();
        int indexhang = cbbHangct.getSelectedIndex();
        int indexsp = cbbSanPham.getSelectedIndex();
        int indexchatlieu = cbbChatLieu.getSelectedIndex();
        chiTietSp.setMaChiTietSP(txtmaCTSP.getText().trim());
        chiTietSp.setMoTa(txaMoTa.getText().trim());
        chiTietSp.setSoLuongTon(Integer.valueOf(txtSoLuongTon.getText().trim()));
        chiTietSp.setGiaBan(BigDecimal.valueOf(Double.valueOf(txtGiaBan.getText().trim())));
        chiTietSp.setMaVach(txtMaVach.getText().trim());

        chiTietSp.setSanPham(sanPhamService.getAll().get(indexsp));
        chiTietSp.setHang(hangService.getAll().get(indexhang));
        chiTietSp.setKichThuoc(kichThuocService.getAll().get(indexkt));
        chiTietSp.setMauSac(mauSacService.getAll().get(indexmau));
        chiTietSp.setChatLieu(chatLieuService.getAll().get(indexchatlieu));
        chiTietSp.setTrangThaiXoa(rdoDangHoatDong.isSelected() ? 0 : 1);
        return chiTietSp;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btXuatFileExcel;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnChiTietSanPham;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClearr;
    private javax.swing.JButton btnLamMoiThuocTinh;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaThuocTinh;
    private javax.swing.JButton btnTTHoatDong;
    private javax.swing.JButton btnTTKoHoatDong;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemThuocTinh;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cbbChatLieu;
    private javax.swing.JComboBox<String> cbbHangct;
    private javax.swing.JComboBox<String> cbbKichThuocct;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbSanPham;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JRadioButton radioChatLieu;
    private javax.swing.JRadioButton radioHang;
    private javax.swing.JRadioButton radioKichThuoc;
    private javax.swing.JRadioButton radioMausac;
    private javax.swing.JRadioButton rdoDangHoatDong;
    private javax.swing.JRadioButton rdoDungHoatDong;
    private javax.swing.JTable tblCTSanPham;
    private javax.swing.JTable tblSanPhamm;
    private javax.swing.JTable tblThuocTinhSP;
    private javax.swing.JTextArea txaMoTa;
    private javax.swing.JTextField txtFindCTSanPham;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtMaThuocTinh;
    private javax.swing.JTextField txtMaVach;
    private javax.swing.JTextField txtSoLuongTon;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTenThuocTinh;
    private javax.swing.JTextField txtTimKiemSp;
    private javax.swing.JTextField txtmaCTSP;
    // End of variables declaration//GEN-END:variables

}
