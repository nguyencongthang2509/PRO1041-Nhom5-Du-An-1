
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package core.view;

import core.quanly.repository.CTSanPhamRepository;
import core.quanly.service.BanHangService;
import core.quanly.service.CTSanPhamService;
import core.quanly.service.SPChatLieuService;
import core.quanly.service.SPHangService;
import core.quanly.service.SPKhuyenMaiService;
import core.quanly.service.SPKhuyenMaiService;
import core.quanly.service.SPKichThuocService;
import core.quanly.service.SPMauSacService;
import core.quanly.service.SanPhamService;
import core.quanly.service.impl.BanHangServiceImpl;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import util.ExportFileCTSP;
import util.ImportExcelCTSP;
import util.TaiMauExcelCTSP;

/**
 *
 * @author thangncph26123
 */
public class ViewSanPham extends javax.swing.JPanel {

    private final SanPhamService sanPhamService;
    private final CTSanPhamService ctsanPhamService;
    private final SPChatLieuService chatLieuService;
    private final SPHangService hangService;
    private final SPMauSacService mauSacService;
    private final SPKichThuocService kichThuocService;
    private final SPKhuyenMaiService khuyenMaiService;
    private final BanHangService banHangService;
    DefaultTableModel dtm;
    DefaultTableModel dtm1;
    DefaultTableModel dtmshowctsp;
    DefaultTableModel dtmThuocTinh;
    DefaultTableModel modelSanPham;
    DefaultComboBoxModel dcbcChatLieu;
    DefaultComboBoxModel dcbcHang;
    DefaultComboBoxModel dcbcMauSac;
    DefaultComboBoxModel dcbcKichThuoc;
    DefaultComboBoxModel dcbcKhuyenMai;
    DefaultComboBoxModel dcbcSanPham;
    DefaultComboBoxModel modelHang;
    DefaultComboBoxModel modelSize;
    DefaultComboBoxModel modelMau;
    DefaultComboBoxModel modelChatLieu;
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
    private int tranghientai = 0;
    private int tongsoTrang = 1;
    private int count = 1;

    public ViewSanPham() {
        initComponents();
        sanPhamService = new SanPhamserviceImpl();
        banHangService = new BanHangServiceImpl();
        ctsanPhamService = new CTSanPhamServiceImpl();
        modelSanPham = (DefaultTableModel) tblSanPhamCTSP.getModel();
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
        modelHang = (DefaultComboBoxModel) cboHang.getModel();
        modelMau = (DefaultComboBoxModel) cboMau.getModel();
        modelSize = (DefaultComboBoxModel) cboSize.getModel();
        modelChatLieu = (DefaultComboBoxModel) cboChatLieu.getModel();
        lstHang = hangService.getAll();
        lstChatLieu = chatLieuService.getAll();
        lstMauSac = mauSacService.getAll();
        lstKichThuoc = kichThuocService.getAll();
        lstSanPham = sanPhamService.getAll();
        lstSpViewModel = sanPhamService.getAllResponse();
        ShowDataComboBoxHang(lstHang);
        ShowDataComboBoxMauSac(lstMauSac);
        ShowDataComboBoxKichThuoc(lstKichThuoc);
        ShowDataComboBoxSanPham(lstSanPham);
        ShowDataComboBoxChatLieu(lstChatLieu);
        LoadToTableSp(lstSpViewModel);
        btnKhoiPhuc.setEnabled(false);
        lblRecord.setText(tranghientai + 1 + " of " + tongsoTrang);
        int index = 0;
        radioHang.setSelected(true);
        LoadToTableHang();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        JFrameMauSac = new javax.swing.JFrame();
        jPanel16 = new javax.swing.JPanel();
        txtAddTenMauSac = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        btnAddMausac = new javax.swing.JButton();
        JframeKichThuoc = new javax.swing.JFrame();
        jPanel17 = new javax.swing.JPanel();
        txtJraTenKichThuoc = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        btnAddJraKichThuoc = new javax.swing.JButton();
        JframeHang = new javax.swing.JFrame();
        jPanel18 = new javax.swing.JPanel();
        txtJfraTenHang = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        btnJfraAddHang = new javax.swing.JButton();
        JframeChatLieu = new javax.swing.JFrame();
        jPanel19 = new javax.swing.JPanel();
        txtJfraTenChatLieu = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        btnJfraAddChatLieu = new javax.swing.JButton();
        DiaLogChiTietSP = new javax.swing.JDialog();
        jPanel20 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txtFindCTSanPham = new javax.swing.JTextField();
        btnDau = new javax.swing.JButton();
        btnTruoc = new javax.swing.JButton();
        btnSau = new javax.swing.JButton();
        lblRecord = new javax.swing.JLabel();
        btnCuoi = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCTSanPham = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        btnKhoiPhuc = new javax.swing.JButton();
        cbbTrangThai = new javax.swing.JComboBox<>();
        cboHang = new javax.swing.JComboBox<>();
        cboMau = new javax.swing.JComboBox<>();
        cboSize = new javax.swing.JComboBox<>();
        cboChatLieu = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        lblTongSoSanPham = new javax.swing.JLabel();
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
        btnTaiMauExcel = new javax.swing.JButton();
        btnImportExcel = new javax.swing.JButton();
        cbbSanPham = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtMaVach = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cbbChatLieu = new javax.swing.JComboBox<>();
        btnAddMs = new javax.swing.JButton();
        btnAddKichThuoc = new javax.swing.JButton();
        btnAddHang = new javax.swing.JButton();
        btnAddChatLieu = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSanPhamCTSP = new javax.swing.JTable();
        txtTimKiemSp1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
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

        JFrameMauSac.setTitle("Thiết lập màu sắc");
        JFrameMauSac.setBackground(new java.awt.Color(255, 255, 255));
        JFrameMauSac.setMinimumSize(null);
        JFrameMauSac.setResizable(false);
        JFrameMauSac.setSize(new java.awt.Dimension(402, 151));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setMaximumSize(new java.awt.Dimension(402, 151));
        jPanel16.setMinimumSize(new java.awt.Dimension(402, 151));

        txtAddTenMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddTenMauSacActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel19.setText("Tên màu sắc:");

        btnAddMausac.setBackground(new java.awt.Color(153, 204, 255));
        btnAddMausac.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnAddMausac.setText("Thêm");
        btnAddMausac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMausacActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtAddTenMauSac)
                .addGap(26, 26, 26))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(157, Short.MAX_VALUE)
                .addComponent(btnAddMausac, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(155, 155, 155))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtAddTenMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAddMausac)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JFrameMauSacLayout = new javax.swing.GroupLayout(JFrameMauSac.getContentPane());
        JFrameMauSac.getContentPane().setLayout(JFrameMauSacLayout);
        JFrameMauSacLayout.setHorizontalGroup(
            JFrameMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        JFrameMauSacLayout.setVerticalGroup(
            JFrameMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        JframeKichThuoc.setTitle("Thiết lập kích thước");
        JframeKichThuoc.setBackground(new java.awt.Color(255, 255, 255));
        JframeKichThuoc.setMinimumSize(new java.awt.Dimension(400, 141));
        JframeKichThuoc.setSize(new java.awt.Dimension(400, 141));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel21.setText("Tên kích thước:");

        btnAddJraKichThuoc.setBackground(new java.awt.Color(153, 204, 255));
        btnAddJraKichThuoc.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnAddJraKichThuoc.setText("Thêm");
        btnAddJraKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddJraKichThuocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtJraTenKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(btnAddJraKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJraTenKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddJraKichThuoc)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JframeKichThuocLayout = new javax.swing.GroupLayout(JframeKichThuoc.getContentPane());
        JframeKichThuoc.getContentPane().setLayout(JframeKichThuocLayout);
        JframeKichThuocLayout.setHorizontalGroup(
            JframeKichThuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        JframeKichThuocLayout.setVerticalGroup(
            JframeKichThuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        JframeHang.setTitle("Thiết lập hãng");
        JframeHang.setBackground(new java.awt.Color(255, 255, 255));
        JframeHang.setMinimumSize(new java.awt.Dimension(399, 130));
        JframeHang.setSize(new java.awt.Dimension(399, 130));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        jLabel24.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel24.setText("Tên hãng:");

        btnJfraAddHang.setBackground(new java.awt.Color(153, 204, 255));
        btnJfraAddHang.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnJfraAddHang.setText("Thêm");
        btnJfraAddHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJfraAddHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel24)
                .addGap(18, 18, 18)
                .addComponent(txtJfraTenHang)
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(153, Short.MAX_VALUE)
                .addComponent(btnJfraAddHang, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJfraTenHang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnJfraAddHang)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JframeHangLayout = new javax.swing.GroupLayout(JframeHang.getContentPane());
        JframeHang.getContentPane().setLayout(JframeHangLayout);
        JframeHangLayout.setHorizontalGroup(
            JframeHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        JframeHangLayout.setVerticalGroup(
            JframeHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        JframeChatLieu.setTitle("Thiết lập chất liệu");
        JframeChatLieu.setBackground(new java.awt.Color(255, 255, 255));
        JframeChatLieu.setMinimumSize(new java.awt.Dimension(392, 146));
        JframeChatLieu.setSize(new java.awt.Dimension(392, 146));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        jLabel26.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel26.setText("Tên chất liệu:");

        btnJfraAddChatLieu.setBackground(new java.awt.Color(153, 204, 255));
        btnJfraAddChatLieu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnJfraAddChatLieu.setText("Thêm");
        btnJfraAddChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJfraAddChatLieuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addComponent(txtJfraTenChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnJfraAddChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtJfraTenChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(18, 18, 18)
                .addComponent(btnJfraAddChatLieu)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JframeChatLieuLayout = new javax.swing.GroupLayout(JframeChatLieu.getContentPane());
        JframeChatLieu.getContentPane().setLayout(JframeChatLieuLayout);
        JframeChatLieuLayout.setHorizontalGroup(
            JframeChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        JframeChatLieuLayout.setVerticalGroup(
            JframeChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        DiaLogChiTietSP.setMaximumSize(new java.awt.Dimension(1043, 436));
        DiaLogChiTietSP.setMinimumSize(new java.awt.Dimension(1043, 436));
        DiaLogChiTietSP.setPreferredSize(new java.awt.Dimension(1043, 436));
        DiaLogChiTietSP.setSize(new java.awt.Dimension(1043, 436));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
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

        btnDau.setBackground(new java.awt.Color(153, 204, 255));
        btnDau.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDau.setText("|<");
        btnDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDauActionPerformed(evt);
            }
        });

        btnTruoc.setBackground(new java.awt.Color(153, 204, 255));
        btnTruoc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTruoc.setText("|<<");
        btnTruoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTruocActionPerformed(evt);
            }
        });

        btnSau.setBackground(new java.awt.Color(153, 204, 255));
        btnSau.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSau.setText(">>|");
        btnSau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSauActionPerformed(evt);
            }
        });

        lblRecord.setText("0 of 0 ");

        btnCuoi.setBackground(new java.awt.Color(153, 204, 255));
        btnCuoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCuoi.setText(">|");
        btnCuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuoiActionPerformed(evt);
            }
        });

        tblCTSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã CTSP", "Tên sản phẩm", "Màu sắc", "Kích thước", "Hãng", "Chất liệu", "Mô tả", "Số lượng tồn", "Giá bán", "Mã vạch", "Select"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCTSanPham.setRowHeight(30);
        tblCTSanPham.setSelectionBackground(new java.awt.Color(86, 154, 222));
        tblCTSanPham.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCTSanPham.getTableHeader().setReorderingAllowed(false);
        tblCTSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblCTSanPham);
        if (tblCTSanPham.getColumnModel().getColumnCount() > 0) {
            tblCTSanPham.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblCTSanPham.getColumnModel().getColumn(11).setPreferredWidth(35);
        }

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel8.setText("Trạng thái:");

        btnXoa.setBackground(new java.awt.Color(153, 204, 255));
        btnXoa.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnKhoiPhuc.setBackground(new java.awt.Color(153, 204, 255));
        btnKhoiPhuc.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnKhoiPhuc.setText("Khôi phục");
        btnKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucActionPerformed(evt);
            }
        });

        cbbTrangThai.setBackground(new java.awt.Color(153, 204, 255));
        cbbTrangThai.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Không hoạt động" }));
        cbbTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiActionPerformed(evt);
            }
        });

        cboHang.setBackground(new java.awt.Color(153, 204, 255));
        cboHang.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cboHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Không hoạt động" }));
        cboHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboHangItemStateChanged(evt);
            }
        });
        cboHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHangActionPerformed(evt);
            }
        });

        cboMau.setBackground(new java.awt.Color(153, 204, 255));
        cboMau.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cboMau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Không hoạt động" }));
        cboMau.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMauItemStateChanged(evt);
            }
        });
        cboMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMauActionPerformed(evt);
            }
        });

        cboSize.setBackground(new java.awt.Color(153, 204, 255));
        cboSize.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cboSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Không hoạt động" }));
        cboSize.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSizeItemStateChanged(evt);
            }
        });
        cboSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSizeActionPerformed(evt);
            }
        });

        cboChatLieu.setBackground(new java.awt.Color(153, 204, 255));
        cboChatLieu.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cboChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Không hoạt động" }));
        cboChatLieu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboChatLieuItemStateChanged(evt);
            }
        });
        cboChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChatLieuActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel20.setText("Tổng số sản phẩm:");

        lblTongSoSanPham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblTongSoSanPham.setForeground(new java.awt.Color(255, 0, 51));
        lblTongSoSanPham.setText("jLabel22");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFindCTSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboHang, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(cboMau, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 5, Short.MAX_VALUE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(btnDau, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTruoc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRecord)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSau)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCuoi)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTongSoSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel20Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCuoi, btnDau, btnSau, btnTruoc});

        jPanel20Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnKhoiPhuc, btnXoa});

        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtFindCTSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDau)
                    .addComponent(btnTruoc)
                    .addComponent(btnSau)
                    .addComponent(lblRecord)
                    .addComponent(btnCuoi)
                    .addComponent(btnKhoiPhuc)
                    .addComponent(btnXoa)
                    .addComponent(jLabel20)
                    .addComponent(lblTongSoSanPham))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout DiaLogChiTietSPLayout = new javax.swing.GroupLayout(DiaLogChiTietSP.getContentPane());
        DiaLogChiTietSP.getContentPane().setLayout(DiaLogChiTietSPLayout);
        DiaLogChiTietSPLayout.setHorizontalGroup(
            DiaLogChiTietSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DiaLogChiTietSPLayout.setVerticalGroup(
            DiaLogChiTietSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

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
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N
        jPanel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThem.setBackground(new java.awt.Color(153, 204, 255));
        btnThem.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(153, 204, 255));
        btnSua.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnChiTietSanPham.setBackground(new java.awt.Color(153, 204, 255));
        btnChiTietSanPham.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnChiTietSanPham.setText("Chi tiết sản phẩm");
        btnChiTietSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietSanPhamActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(153, 204, 255));
        btnClear.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
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

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel2.setText("Tên sản phẩm:");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel1.setText("Mã sản phẩm:");

        txtMaSanPham.setEnabled(false);

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        tblSanPhamm.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        tblSanPhamm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPhamm.setRowHeight(30);
        tblSanPhamm.setSelectionBackground(new java.awt.Color(86, 154, 222));
        tblSanPhamm.getTableHeader().setReorderingAllowed(false);
        tblSanPhamm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhammMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPhamm);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
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
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemSp, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(836, 836, 836))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

        jTabbedPane2.addTab("Sản phẩm", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(1292, 784));
        jPanel2.setMinimumSize(new java.awt.Dimension(1292, 784));
        jPanel2.setPreferredSize(new java.awt.Dimension(1292, 784));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N
        jPanel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel10.setText("Tên sản phẩm:");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel11.setText("Mã CTSP:");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel12.setText("Số lượng tồn:");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel13.setText("Giá bán:");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel14.setText("Mô tả:");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel15.setText("Màu sắc:");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel16.setText("Kích thước:");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel17.setText("Hãng:");

        txtSoLuongTon.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        txtmaCTSP.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        txtmaCTSP.setEnabled(false);

        txtGiaBan.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        txaMoTa.setColumns(20);
        txaMoTa.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        txaMoTa.setRows(5);
        jScrollPane2.setViewportView(txaMoTa);

        cbbHangct.setBackground(new java.awt.Color(153, 204, 255));
        cbbHangct.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        cbbKichThuocct.setBackground(new java.awt.Color(153, 204, 255));
        cbbKichThuocct.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        cbbMauSac.setBackground(new java.awt.Color(153, 204, 255));
        cbbMauSac.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAdd.setBackground(new java.awt.Color(153, 204, 255));
        btnAdd.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnAdd.setText("Thêm sản phẩm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(153, 204, 255));
        btnUpdate.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnUpdate.setText("Cập nhật sản phẩm");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnClearr.setBackground(new java.awt.Color(153, 204, 255));
        btnClearr.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnClearr.setText("Làm mới");
        btnClearr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearrActionPerformed(evt);
            }
        });

        btXuatFileExcel.setBackground(new java.awt.Color(153, 204, 255));
        btXuatFileExcel.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btXuatFileExcel.setText("Xuất File Excel");
        btXuatFileExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXuatFileExcelActionPerformed(evt);
            }
        });

        btnTaiMauExcel.setBackground(new java.awt.Color(153, 204, 255));
        btnTaiMauExcel.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnTaiMauExcel.setText("Tải mẫu Excel");
        btnTaiMauExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiMauExcelActionPerformed(evt);
            }
        });

        btnImportExcel.setBackground(new java.awt.Color(153, 204, 255));
        btnImportExcel.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnImportExcel.setText("Import File Excel");
        btnImportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImportExcel)
                    .addComponent(btnUpdate)
                    .addComponent(btnAdd)
                    .addComponent(btnClearr)
                    .addComponent(btXuatFileExcel)
                    .addComponent(btnTaiMauExcel))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel12Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btXuatFileExcel, btnAdd, btnClearr, btnImportExcel, btnTaiMauExcel, btnUpdate});

        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate)
                .addGap(18, 18, 18)
                .addComponent(btnClearr)
                .addGap(18, 18, 18)
                .addComponent(btXuatFileExcel)
                .addGap(18, 18, 18)
                .addComponent(btnTaiMauExcel)
                .addGap(18, 18, 18)
                .addComponent(btnImportExcel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cbbSanPham.setBackground(new java.awt.Color(153, 204, 255));
        cbbSanPham.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel5.setText("Mã vạch:");

        txtMaVach.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel18.setText("Chất liệu:");

        cbbChatLieu.setBackground(new java.awt.Color(153, 204, 255));
        cbbChatLieu.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        btnAddMs.setBackground(new java.awt.Color(153, 204, 255));
        btnAddMs.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAddMs.setText("+");
        btnAddMs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMsActionPerformed(evt);
            }
        });

        btnAddKichThuoc.setBackground(new java.awt.Color(153, 204, 255));
        btnAddKichThuoc.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAddKichThuoc.setText("+");
        btnAddKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddKichThuocActionPerformed(evt);
            }
        });

        btnAddHang.setBackground(new java.awt.Color(153, 204, 255));
        btnAddHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAddHang.setText("+");
        btnAddHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddHangActionPerformed(evt);
            }
        });

        btnAddChatLieu.setBackground(new java.awt.Color(153, 204, 255));
        btnAddChatLieu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAddChatLieu.setText("+");
        btnAddChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddChatLieuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addGap(28, 28, 28))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addGap(79, 79, 79)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtSoLuongTon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                        .addComponent(txtmaCTSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                        .addComponent(cbbSanPham, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(88, 88, 88)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(50, 50, 50)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbChatLieu, 0, 239, Short.MAX_VALUE)
                    .addComponent(cbbHangct, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbMauSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbKichThuocct, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaVach))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddKichThuoc)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnAddHang)
                        .addComponent(btnAddChatLieu)
                        .addComponent(btnAddMs)))
                .addGap(20, 20, 20)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddMs, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbKichThuocct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddHang, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbHangct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel5)
                    .addComponent(txtMaVach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel15))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(cbbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel10))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(jLabel17))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(jLabel12))
            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(jLabel13))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(jLabel18))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel11))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(txtmaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel16))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAddKichThuoc, cbbKichThuocct});

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAddHang, cbbHangct});

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAddMs, cbbMauSac});

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAddChatLieu, cbbChatLieu});

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        tblSanPhamCTSP.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        tblSanPhamCTSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPhamCTSP.setRowHeight(30);
        tblSanPhamCTSP.setSelectionBackground(new java.awt.Color(86, 154, 222));
        tblSanPhamCTSP.getTableHeader().setReorderingAllowed(false);
        tblSanPhamCTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamCTSPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblSanPhamCTSPMouseEntered(evt);
            }
        });
        jScrollPane5.setViewportView(tblSanPhamCTSP);

        txtTimKiemSp1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemSp1CaretUpdate(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel9.setText("Tìm kiếm:");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiemSp1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(844, 844, 844))
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1198, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemSp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(304, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                    .addContainerGap(51, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Chi tiết sản phẩm", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập thuộc tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel6.setText("Mã thuộc tính:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel7.setText("Tên thuộc tính:");

        txtMaThuocTinh.setEnabled(false);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(71, 71, 71)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                    .addComponent(txtTenThuocTinh))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        buttonGroup1.add(radioHang);
        radioHang.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
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
        radioKichThuoc.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        radioKichThuoc.setText("Kích thước");
        radioKichThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioKichThuocMouseClicked(evt);
            }
        });

        buttonGroup1.add(radioMausac);
        radioMausac.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        radioMausac.setText("Màu sắc");
        radioMausac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioMausacMouseClicked(evt);
            }
        });

        buttonGroup1.add(radioChatLieu);
        radioChatLieu.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
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
        btnThemThuocTinh.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnThemThuocTinh.setText("Thêm");
        btnThemThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThuocTinhActionPerformed(evt);
            }
        });

        btnSuaThuocTinh.setBackground(new java.awt.Color(153, 204, 255));
        btnSuaThuocTinh.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnSuaThuocTinh.setText("Sửa");
        btnSuaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThuocTinhActionPerformed(evt);
            }
        });

        btnLamMoiThuocTinh.setBackground(new java.awt.Color(153, 204, 255));
        btnLamMoiThuocTinh.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách thuộc tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        tblThuocTinhSP.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        tblThuocTinhSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã thuộc tính", "Tên thuộc tính"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThuocTinhSP.setRowHeight(30);
        tblThuocTinhSP.setSelectionBackground(new java.awt.Color(86, 154, 222));
        tblThuocTinhSP.getTableHeader().setReorderingAllowed(false);
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
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1229, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
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
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jTabbedPane2.addTab("Thuộc tính sản phẩm", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1271, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loadAll() {
        lstSanPham = sanPhamService.getAll();
        ShowDataComboBoxSanPham(lstSanPham);
        lstHang = hangService.getAll();
        ShowDataComboBoxHang(lstHang);
        lstChatLieu = chatLieuService.getAll();
        ShowDataComboBoxChatLieu(lstChatLieu);
        lstMauSac = mauSacService.getAll();
        ShowDataComboBoxMauSac(lstMauSac);
        lstKichThuoc = kichThuocService.getAll();
        ShowDataComboBoxKichThuoc(lstKichThuoc);
    }

    private void LoadToTableSp(List<SanPhamResponse> lstSpViewModel1) {
        dtm.setRowCount(0);
        modelSanPham.setRowCount(0);
        int index = 1;
        for (SanPhamResponse ap : lstSpViewModel) {
            dtm.addRow(ap.toDataRow(index));
            index++;
        }
        int index1 = 1;
        for (SanPhamResponse ap : lstSpViewModel) {
            modelSanPham.addRow(ap.toDataRow(index1));
            index1++;
        }
    }

    private void showChiTietSp() {
        int row = tblSanPhamm.getSelectedRow();
        if (row == -1) {
            return;
        }
        String maSp = tblSanPhamm.getValueAt(row, 1).toString();
        System.out.println(maSp);
        dtmshowctsp = (DefaultTableModel) tblCTSanPham.getModel();
        dtmshowctsp.setRowCount(0);
        lstctSpViewModel = ctsanPhamService.getFormCTSP(maSp);
        loadTableChiTietSP(lstctSpViewModel);
    }

    private void ShowDataComboBoxHang(List<Hang> lstHang) {
        dcbcHang.removeAllElements();
        for (Hang hang : lstHang) {
            dcbcHang.addElement(hang.getTen());
        }
        modelHang.removeAllElements();
        modelHang.addElement("Tất cả");
        for (Hang hang : lstHang) {
            modelHang.addElement(hang.getTen());
        }
    }

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            SanPham sp = getSanPhamByForm();
            if (sp == null) {
                return;
            }
            sp.setMa("Sp" + sanPhamService.genMaSanPhamTuDong());
            String messsage = sanPhamService.add(sp);
            lstSpViewModel = sanPhamService.getAllResponse();
            LoadToTableSp(lstSpViewModel);
            JOptionPane.showMessageDialog(this, "Thêm mới sản phẩm thành công");
            txtMaSanPham.setText("");
            txtTenSanPham.setText("");
            loadAll();
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
        SanPhamResponse sanPhamViewModel = lstSpViewModel.get(index);
        if (index >= 0) {
            txtMaSanPham.setText(sanPhamViewModel.getMa());
            txtTenSanPham.setText(sanPhamViewModel.getTen());
            showChiTietSp();
        }
    }//GEN-LAST:event_tblSanPhammMouseClicked

    private void tblCTSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTSanPhamMouseClicked
        int index = tblCTSanPham.getSelectedRow();
        CTSanPhamResponse ctSanPhamViewModel = lstctSpViewModel.get(index + tranghientai * 10);
        if (index >= 0) {
            cbbSanPham.setSelectedItem(ctSanPhamViewModel.getTensp());
            txtmaCTSP.setText(ctSanPhamViewModel.getMactsp());
            txtSoLuongTon.setText(ctSanPhamViewModel.getSoLuongTon().toString());
            txtGiaBan.setText(ctSanPhamViewModel.getGiaBan().toString());
            txaMoTa.setText(ctSanPhamViewModel.getMoTa());
            txtMaVach.setText(ctSanPhamViewModel.getMaVach());
            cbbMauSac.setSelectedItem(ctSanPhamViewModel.getMauSac());
            cbbKichThuocct.setSelectedItem(ctSanPhamViewModel.getKichThuoc());
            cbbHangct.setSelectedItem(ctSanPhamViewModel.getHang());
            cbbChatLieu.setSelectedItem(ctSanPhamViewModel.getChatLieu());
            cbbSanPham.setSelectedItem(ctSanPhamViewModel.getTensp());
//            if (ctSanPhamViewModel.getTrangThai() == 0) {
//                rdoDangHoatDong.setSelected(true);
//            } else {
//                rdoDungHoatDong.setSelected(true);
//            }
        }
    }//GEN-LAST:event_tblCTSanPhamMouseClicked
    private void ShowDataComboBoxMauSac(List<MauSac> lstMauSac) {
        dcbcMauSac.removeAllElements();
        for (MauSac mauSac : lstMauSac) {
            dcbcMauSac.addElement(mauSac.getTen());
        }
        modelMau.removeAllElements();
        modelMau.addElement("Tất cả");
        for (MauSac mauSac : lstMauSac) {
            modelMau.addElement(mauSac.getTen());
        }
    }

    private void ShowDataComboBoxKichThuoc(List<KichThuoc> lstKichThuoc) {
        dcbcKichThuoc.removeAllElements();
        for (KichThuoc kichThuoc : lstKichThuoc) {
            dcbcKichThuoc.addElement(kichThuoc.getTen());
        }
        modelSize.removeAllElements();
        modelSize.addElement("Tất cả");
        for (KichThuoc kichThuoc : lstKichThuoc) {
            modelSize.addElement(kichThuoc.getTen());
        }
    }

    private void ShowDataComboBoxChatLieu(List<ChatLieu> lstChatLieu) {
        dcbcChatLieu.removeAllElements();
        for (ChatLieu chatLieu : lstChatLieu) {
            dcbcChatLieu.addElement(chatLieu.getTen());
        }
        modelChatLieu.removeAllElements();
        modelChatLieu.addElement("Tất cả");
        for (ChatLieu chatLieu : lstChatLieu) {
            modelChatLieu.addElement(chatLieu.getTen());
        }
    }

    private void ShowDataComboBoxSanPham(List<SanPham> lstSanPham) {
        dcbcSanPham.removeAllElements();
        for (SanPham sanPham : lstSanPham) {
            dcbcSanPham.addElement(sanPham.getTen());
        }
    }

    private void txtTimKiemSpCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemSpCaretUpdate
        try {
            lstSpViewModel = sanPhamService.findMaOrTen(txtTimKiemSp.getText());
            LoadToTableSp(lstSpViewModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtTimKiemSpCaretUpdate

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        SanPham sp = getSanPhamByForm();
        if (sp == null) {
            return;
        }
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
        lstSpViewModel = sanPhamService.getAllResponse();
        LoadToTableSp(lstSpViewModel);
        loadAll();
        JOptionPane.showMessageDialog(this, "Sửa sản phẩm thành công");

    }//GEN-LAST:event_btnSuaActionPerformed

    private void radioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioHangActionPerformed
    }//GEN-LAST:event_radioHangActionPerformed

    private void btnClearrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearrActionPerformed

        txtmaCTSP.setText("");
        txtSoLuongTon.setText("");
        txtGiaBan.setText("");
        txaMoTa.setText("");
        txtMaVach.setText("");
    }//GEN-LAST:event_btnClearrActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
//            if (rdoDungHoatDong.isSelected()) {
//                JOptionPane.showMessageDialog(this, "Không thể thêm sản phẩm với trạng thái dừng hoạt động");
//                return; 
//            }
            ChiTietSP chiTietSp = getChiTietSp();
            if (chiTietSp == null) {
                return;
            }
            chiTietSp.setMaChiTietSP("CTSP" + ctsanPhamService.genMaCTSPTuDong());
            String messsage = ctsanPhamService.add(chiTietSp);
            lstctSpViewModel = ctsanPhamService.getAllViewModel();
            loadTableChiTietSP(lstctSpViewModel);
            JOptionPane.showMessageDialog(this, messsage);
            txtmaCTSP.setText("");
            txtSoLuongTon.setText("");
            txtGiaBan.setText("");
            txaMoTa.setText("");
            txtMaVach.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        ChiTietSP spct = getChiTietSp();
        if (spct == null) {
            return;
        }
        int chon = tblCTSanPham.getSelectedRow();
        if (chon < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào");
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không?");
        if (hoi != JOptionPane.YES_OPTION) {
            return;
        }
        spct.setId(lstctSpViewModel.get(chon).getMa());
        String messsage = ctsanPhamService.update(spct);
        lstctSpViewModel = ctsanPhamService.getAllViewModel();
        loadTableChiTietSP(lstctSpViewModel);
        JOptionPane.showMessageDialog(this, "Sửa sản phẩm thành công");
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void LoadToTableHang() {
        dtmThuocTinh.setRowCount(0);
        lstHangRepon = hangService.getAllResponse();
        for (SPHangResponse h : lstHangRepon) {
            dtmThuocTinh.addRow(h.toDateRow());
        }
    }

    private void LoadToTableChatLieu() {
        dtmThuocTinh.setRowCount(0);
        lstChatLieuRespon = chatLieuService.getAllResponse();
        for (SPChatLieuResponse ch : lstChatLieuRespon) {
            dtmThuocTinh.addRow(ch.toDateRow());
        }
    }

    private void LoadToTableMauSac() {
        dtmThuocTinh.setRowCount(0);
        lstMauSacRespon = mauSacService.getAllResponse();
        for (SPMauSacResponse h : lstMauSacRespon) {
            dtmThuocTinh.addRow(h.toDateRow());
        }
    }

    private void LoadToTableKichThuoc() {
        dtmThuocTinh.setRowCount(0);
        lstKichThuocRepon = kichThuocService.getAllResponse();
        for (SPKichThuocResponse h : lstKichThuocRepon) {
            dtmThuocTinh.addRow(h.toDateRow());
        }
    }

    private void btnThemThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThuocTinhActionPerformed
        try {
            if (radioHang.isSelected()) {
                try {
                    if (txtTenThuocTinh.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Không được để trống");
                        return;
                    }
                    try {
                        txtTenThuocTinh.getText().trim();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Hãng phải là chữ");
                        return;
                    }
                    if (txtTenThuocTinh.getText().trim().length() > 50) {
                        JOptionPane.showMessageDialog(this, "không thể vượt quá 50 kí tự");
                        return;
                    }
                    for (SPHangResponse xx : lstHangRepon) {
                        if (txtTenThuocTinh.getText().equals(xx.getTen())) {
                            JOptionPane.showMessageDialog(this, "Đã có hãng này ");
                            return;
                        }
                    }
                    Hang hang = getHangByForm();
                    hang.setMa("H" + hangService.genMaHangTuDong());
                    String messsage = hangService.add(hang);
                    lstHangRepon = hangService.getAllResponse();
                    LoadToTableHang();
                    JOptionPane.showMessageDialog(this, "Thêm mới hãng thành công");
                    txtMaThuocTinh.setText("");
                    txtTenThuocTinh.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (radioKichThuoc.isSelected()) {
                try {
                    if (txtTenThuocTinh.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Không được để trống");
                        return;
                    }
                    try {
                        Double.parseDouble(txtTenThuocTinh.getText().trim());
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Kích thước phải là số");
                        return;
                    }
                    if (Double.parseDouble(txtTenThuocTinh.getText().trim()) < 30
                            || Double.parseDouble(txtTenThuocTinh.getText().trim()) > 50) {
                        JOptionPane.showMessageDialog(this, "Kích thước phải từ 30 đến 50");
                        return;
                    }
                    for (SPKichThuocResponse xx : lstKichThuocRepon) {
                        if (txtTenThuocTinh.getText().equals(xx.getTen())) {
                            JOptionPane.showMessageDialog(this, "Đã có kích thước này ");
                            return;
                        }
                    }

                    KichThuoc kichThuoc = getKichThuocByForm();
                    kichThuoc.setMa("KT" + kichThuocService.genMaKichThuocTuDong());
                    String messsage = kichThuocService.add(kichThuoc);
                    lstKichThuocRepon = kichThuocService.getAllResponse();
                    LoadToTableKichThuoc();
                    JOptionPane.showMessageDialog(this, "Thêm mới kích thước thành công");
                    txtMaThuocTinh.setText("");
                    txtTenThuocTinh.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (radioMausac.isSelected()) {
                try {
                    if (txtTenThuocTinh.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Không được để trống");
                        return;
                    }
                    try {
                        txtTenThuocTinh.getText().trim();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Màu sắc phải là chữ");
                        return;
                    }
                    if (txtTenThuocTinh.getText().trim().length() > 50) {
                        JOptionPane.showMessageDialog(this, "không thể vượt quá 50 kí tự");
                        return;
                    }
                    for (SPMauSacResponse xx : lstMauSacRespon) {
                        if (txtTenThuocTinh.getText().equals(xx.getTen())) {
                            JOptionPane.showMessageDialog(this, "Đã có Màu sắc này ");
                            return;
                        }
                    }
                    MauSac mauSac = getMauSacByForm();
                    mauSac.setMa("MS" + mauSacService.genMaMauSacTuDong());
                    String messsage = mauSacService.add(mauSac);
                    lstMauSacRespon = mauSacService.getAllResponse();
                    LoadToTableMauSac();
                    JOptionPane.showMessageDialog(this, "Thêm mới màu sắc thành công");
                    txtMaThuocTinh.setText("");
                    txtTenThuocTinh.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    if (txtTenThuocTinh.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Không được để trống");
                        return;
                    }
                    try {
                        txtTenThuocTinh.getText().trim();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Chất liệu phải là chữ");
                        return;
                    }
                    if (txtTenThuocTinh.getText().trim().length() > 50) {
                        JOptionPane.showMessageDialog(this, "không thể vượt quá 50 kí tự");
                        return;
                    }
                    for (SPChatLieuResponse xx : lstChatLieuRespon) {
                        if (txtTenThuocTinh.getText().equals(xx.getTen())) {
                            JOptionPane.showMessageDialog(this, "Đã có chất liệu này ");
                            return;
                        }
                    }
                    ChatLieu chatLieu = getChatLieuByForm();
                    chatLieu.setMa("CL" + chatLieuService.genChatLieuTuDong());
                    String messsage = chatLieuService.add(chatLieu);
                    lstChatLieuRespon = chatLieuService.getAllResponse();
                    LoadToTableChatLieu();
                    JOptionPane.showMessageDialog(this, "Thêm mới chất liệu thành công");
                    txtMaThuocTinh.setText("");
                    txtTenThuocTinh.setText("");
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

    private void radioMausacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioMausacMouseClicked
        try {
            LoadToTableMauSac();
        } catch (Exception e) {
            e.printStackTrace();
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
                if (txtTenThuocTinh.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Không được để trống");
                    return;
                }
                try {
                    txtTenThuocTinh.getText().trim();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Hãng phải là chữ");
                    return;
                }
                if (txtTenThuocTinh.getText().trim().length() > 50) {
                    JOptionPane.showMessageDialog(this, "không thể vượt quá 50 kí tự");
                    return;
                }
                for (SPHangResponse xx : lstHangRepon) {
                    if (txtTenThuocTinh.getText().equals(xx.getTen())) {
                        JOptionPane.showMessageDialog(this, "Đã có hãng này ");
                        return;
                    }
                }
                int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không?");
                if (hoi != JOptionPane.YES_OPTION) {
                    return;
                }
                hang.setId(lstHangRepon.get(chon).getId());
                String messsage = hangService.update(hang);
                lstHangRepon = hangService.getAllResponse();
                LoadToTableHang();

                JOptionPane.showMessageDialog(this, "Sửa thành công");
            } else if (radioKichThuoc.isSelected()) {
                KichThuoc kichThuoc = getKichThuocByForm();

                int chon = tblThuocTinhSP.getSelectedRow();
                if (chon < 0) {
                    JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào");
                    return;
                }
                if (txtTenThuocTinh.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Không được để trống");
                    return;
                }
                try {
                    Double.parseDouble(txtTenThuocTinh.getText().trim());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Kích thước phải là số");
                    return;
                }
                if (Double.parseDouble(txtTenThuocTinh.getText().trim()) < 30
                        || Double.parseDouble(txtTenThuocTinh.getText().trim()) > 50) {
                    JOptionPane.showMessageDialog(this, "Kích thước phải từ 30 đến 50");
                    return;
                }
                for (SPKichThuocResponse xx : lstKichThuocRepon) {
                    if (txtTenThuocTinh.getText().equals(xx.getTen())) {
                        JOptionPane.showMessageDialog(this, "Đã có kích thước này ");
                        return;
                    }
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
            } else if (radioMausac.isSelected()) {
                MauSac mauSac = getMauSacByForm();

                int chon = tblThuocTinhSP.getSelectedRow();
                if (chon < 0) {
                    JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào");
                    return;
                }
                if (txtTenThuocTinh.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Không được để trống");
                    return;
                }
                try {
                    txtTenThuocTinh.getText().trim();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Màu sắc phải là chữ");
                    return;
                }
                if (txtTenThuocTinh.getText().trim().length() > 50) {
                    JOptionPane.showMessageDialog(this, "không thể vượt quá 50 kí tự");
                    return;
                }
                for (SPMauSacResponse xx : lstMauSacRespon) {
                    if (txtTenThuocTinh.getText().equals(xx.getTen())) {
                        JOptionPane.showMessageDialog(this, "Đã có Màu sắc này ");
                        return;
                    }
                }
                int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không?");
                if (hoi != JOptionPane.YES_OPTION) {
                    return;
                }
                mauSac.setId(lstMauSacRespon.get(chon).getId());
                String messsage = mauSacService.update(mauSac);
                lstMauSacRespon = mauSacService.getAllResponse();
                LoadToTableMauSac();
                JOptionPane.showMessageDialog(this, "Sửa thành công");
            } else {
                ChatLieu chatLieu = getChatLieuByForm();

                int chon = tblThuocTinhSP.getSelectedRow();
                if (chon < 0) {
                    JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào");
                    return;
                }
                if (txtTenThuocTinh.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Không được để trống");
                    return;
                }
                try {
                    txtTenThuocTinh.getText().trim();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Chất liệu phải là chữ");
                    return;
                }
                if (txtTenThuocTinh.getText().trim().length() > 50) {
                    JOptionPane.showMessageDialog(this, "không thể vượt quá 50 kí tự");
                    return;
                }
                for (SPChatLieuResponse xx : lstChatLieuRespon) {
                    if (txtTenThuocTinh.getText().equals(xx.getTen())) {
                        JOptionPane.showMessageDialog(this, "Đã có chất liệu này ");
                        return;
                    }
                }
                int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không?");
                if (hoi != JOptionPane.YES_OPTION) {
                    return;
                }
                chatLieu.setId(lstChatLieuRespon.get(chon).getId());
                String messsage = chatLieuService.update(chatLieu);
                lstChatLieuRespon = chatLieuService.getAllResponse();
                LoadToTableChatLieu();

                JOptionPane.showMessageDialog(this, "Sửa thành công");

            }
            loadAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaThuocTinhActionPerformed

    private void txtFindCTSanPhamCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFindCTSanPhamCaretUpdate
        try {
            int row = tblSanPhamCTSP.getSelectedRow();
            SanPhamResponse sanPhamResponse = lstSpViewModel.get(row);
            if (cbbTrangThai.getSelectedIndex() == 0) {
                lstctSpViewModel = ctsanPhamService.findByMaOrTen(txtFindCTSanPham.getText(), sanPhamResponse.getTen(), 0);
            } else {
                lstctSpViewModel = ctsanPhamService.findByMaOrTen(txtFindCTSanPham.getText(), sanPhamResponse.getTen(), 1);
            }

            loadTableChiTietSP(lstctSpViewModel);
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
        try {
            JFileChooser avatarChooser = new JFileChooser("D:\\");
            avatarChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //Giới hạn chỉ chọn đc thư mục
            FileNameExtensionFilter avatarFilter = new FileNameExtensionFilter("Exel File", "xlsx");
            avatarChooser.setFileFilter(avatarFilter);
            avatarChooser.setAcceptAllFileFilterUsed(false);
            int selectFileCheck = avatarChooser.showOpenDialog(this);
            File selectedFile = avatarChooser.getSelectedFile();
            if (!(selectFileCheck == JFileChooser.APPROVE_OPTION)) {
                return;
            }
            //Muốn lấy đường dẫn và để vào export PDF thì 
            String path = selectedFile.getAbsolutePath();
            ExportFileCTSP export = new ExportFileCTSP();
            boolean check = export.ExportFileExcel(lstctSpViewModel, path);
            if (check) {
                JOptionPane.showMessageDialog(this, "Xuất file excel thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Xuất file excel thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btXuatFileExcelActionPerformed

    private void btnTaiMauExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiMauExcelActionPerformed
        try {
            JFileChooser avatarChooser = new JFileChooser("D:\\");
            avatarChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //Giới hạn chỉ chọn đc thư mục
            FileNameExtensionFilter avatarFilter = new FileNameExtensionFilter("Exel File", "xlsx");
            avatarChooser.setFileFilter(avatarFilter);
            avatarChooser.setAcceptAllFileFilterUsed(false);
            int selectFileCheck = avatarChooser.showOpenDialog(this);
            File selectedFile = avatarChooser.getSelectedFile();
            if (!(selectFileCheck == JFileChooser.APPROVE_OPTION)) {
                return;
            }
            //Muốn lấy đường dẫn và để vào export PDF thì 
            String path = selectedFile.getAbsolutePath();
            TaiMauExcelCTSP importEX = new TaiMauExcelCTSP();
            boolean check = importEX.ImportExcel(path);
            if (check) {
                JOptionPane.showMessageDialog(this, "Tải mẫu Excel thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Tải mẫu Excel không thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTaiMauExcelActionPerformed

    private void btnImportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportExcelActionPerformed
        try {
            JFileChooser fc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Exel File", "xlsx");
            fc.setFileFilter(filter);
            int check = fc.showOpenDialog(null);
            File file = null;
            if (check == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
            }
            if (file == null) {
                return;
            }
            ImportExcelCTSP excelCTSP = new ImportExcelCTSP();
            excelCTSP.ImportFile(file.getAbsolutePath());
            lstctSpViewModel = ctsanPhamService.getAllViewModel();
            loadTableChiTietSP(lstctSpViewModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnImportExcelActionPerformed

    private void btnDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDauActionPerformed

        tranghientai = 0;
        lblRecord.setText(tranghientai + 1 + " of " + tongsoTrang);
        loadTableChiTietSP(lstctSpViewModel);
    }//GEN-LAST:event_btnDauActionPerformed

    private void btnTruocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTruocActionPerformed
        tranghientai--;
        if (tranghientai < 0) {
            tranghientai = 0;
        }
        lblRecord.setText(tranghientai + 1 + " of " + tongsoTrang);
        loadTableChiTietSP(lstctSpViewModel);
    }//GEN-LAST:event_btnTruocActionPerformed

    private void btnSauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSauActionPerformed
        tranghientai++;
        if (tranghientai > tongsoTrang - 1) {
            tranghientai = tongsoTrang - 1;
        }
        lblRecord.setText(tranghientai + 1 + " of " + tongsoTrang);
        loadTableChiTietSP(lstctSpViewModel);
    }//GEN-LAST:event_btnSauActionPerformed

    private void btnCuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuoiActionPerformed
        tranghientai = tongsoTrang - 1;
        lblRecord.setText(tranghientai + 1 + " of " + tongsoTrang);
        loadTableChiTietSP(lstctSpViewModel);
    }//GEN-LAST:event_btnCuoiActionPerformed

    private void btnAddMsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMsActionPerformed
        JFrameMauSac.setVisible(true);
        JFrameMauSac.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnAddMsActionPerformed

    private void btnAddKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddKichThuocActionPerformed
        JframeKichThuoc.setVisible(true);
        JframeKichThuoc.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnAddKichThuocActionPerformed

    private void btnAddHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddHangActionPerformed
        JframeHang.setVisible(true);
        JframeHang.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnAddHangActionPerformed

    private void btnAddChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddChatLieuActionPerformed
        JframeChatLieu.setVisible(true);
        JframeChatLieu.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnAddChatLieuActionPerformed

    private void btnJfraAddChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJfraAddChatLieuActionPerformed
        try {
            if (txtJfraTenChatLieu.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống tên");
                return;
            }
            for (ChatLieu xx : lstChatLieu) {
                if (txtJfraTenChatLieu.getText().equals(xx.getTen())) {
                    JOptionPane.showMessageDialog(this, "Đã có chất liệu này ");
                    JframeChatLieu.setVisible(false);
                    return;
                }
            }
            String tenChatLieu = txtJfraTenChatLieu.getText().trim();
            ChatLieu chatLieu = new ChatLieu();
            chatLieu.setMa("CL" + chatLieuService.genChatLieuTuDong());
            chatLieu.setTen(tenChatLieu);
            String messsage = chatLieuService.add(chatLieu);
            lstChatLieuRespon = chatLieuService.getAllViewModel();
            LoadToTableChatLieu();
            loadAll();
            JOptionPane.showMessageDialog(this, "Thêm mới chất liệu thành công");
            JframeChatLieu.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnJfraAddChatLieuActionPerformed

    private void btnJfraAddHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJfraAddHangActionPerformed
        try {
            if (txtJfraTenHang.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống tên");
                return;
            }
            for (Hang xx : lstHang) {
                if (txtJfraTenHang.getText().equals(xx.getTen())) {
                    JOptionPane.showMessageDialog(this, "Đã có hãng này ");
                    JframeHang.setVisible(false);
                    return;
                }
            }
            String tenHang = txtJfraTenHang.getText().trim();
            Hang hang = new Hang();
            hang.setMa("H" + hangService.genMaHangTuDong());
            hang.setTen(tenHang);
            String messsage = hangService.add(hang);
            lstHangRepon = hangService.getAllViewModel();
            LoadToTableHang();
            loadAll();
            JOptionPane.showMessageDialog(this, "Thêm mới hãng thành công");
            JframeHang.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnJfraAddHangActionPerformed

    private void btnAddJraKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddJraKichThuocActionPerformed
        try {
            if (txtJraTenKichThuoc.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống tên");
                return;
            }

            for (KichThuoc xx : lstKichThuoc) {
                if (txtJraTenKichThuoc.getText().equals(xx.getTen())) {
                    JOptionPane.showMessageDialog(this, "Đã có kích thước này ");
                    JframeKichThuoc.setVisible(false);
                    return;
                }
            }
            try {
                Double.parseDouble(txtJraTenKichThuoc.getText().trim());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Kích thước phải là số");
                return;
            }
            if (Double.parseDouble(txtJraTenKichThuoc.getText().trim()) < 30
                    || Double.parseDouble(txtJraTenKichThuoc.getText().trim()) > 50) {
                JOptionPane.showMessageDialog(this, "Kích thước phải từ 30 đến 50");
                JframeKichThuoc.setVisible(false);
                return;
            }
            String tenKichThuoc = txtJraTenKichThuoc.getText().trim();
            KichThuoc kichThuoc = new KichThuoc();
            kichThuoc.setMa("KT" + kichThuocService.genMaKichThuocTuDong());
            kichThuoc.setTen(tenKichThuoc);
            String messsage = kichThuocService.add(kichThuoc);
            lstKichThuocRepon = kichThuocService.getAllViewModel();
            LoadToTableKichThuoc();
            loadAll();
            JOptionPane.showMessageDialog(this, "Thêm mới kích thước thành công");
            JframeKichThuoc.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddJraKichThuocActionPerformed

    private void btnAddMausacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMausacActionPerformed
        try {
            if (txtAddTenMauSac.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống tên");
                return;
            }
            for (MauSac xx : lstMauSac) {
                if (txtAddTenMauSac.getText().equals(xx.getTen())) {
                    JOptionPane.showMessageDialog(this, "Đã có màu sắc này ");
                    JframeKichThuoc.setVisible(false);
                    return;
                }
            }
            String tenMauSac = txtAddTenMauSac.getText().trim();
            MauSac mauSac = new MauSac();
            mauSac.setMa("MS" + mauSacService.genMaMauSacTuDong());
            mauSac.setTen(tenMauSac);
            String messsage = mauSacService.add(mauSac);
            lstMauSacRespon = mauSacService.getAllViewModel();
            LoadToTableMauSac();
            loadAll();
            JOptionPane.showMessageDialog(this, "Thêm mới màu sắc thành công");
            JFrameMauSac.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddMausacActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        try {
            ChiTietSP chiTietSanPham = new ChiTietSP();
            int chon = tblCTSanPham.getSelectedRow();
            if (chon < 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào");
                return;
            }
            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn cập nhật trạng thái không?");
            if (hoi != JOptionPane.YES_OPTION) {
                return;
            }
            List<CTSanPhamResponse> listSelected = GetSelected();
            for (CTSanPhamResponse xx : listSelected) {
                ctsanPhamService.updateTrangThai(1, xx.getMa());
            }
            cbbTrangThai.setSelectedIndex(1);
            loadTableChiTietSP(lstctSpViewModel);
            JOptionPane.showMessageDialog(this, "Cập nhật thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoaActionPerformed


    private void btnKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucActionPerformed
        try {
            ChiTietSP chiTietSanPham = new ChiTietSP();
            int chon = tblCTSanPham.getRowCount();
            if (chon < 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào");
                return;
            }
            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn cập nhật trạng thái không?");
            if (hoi != JOptionPane.YES_OPTION) {
                return;
            }
            List<CTSanPhamResponse> listSelected = GetSelected();
            for (CTSanPhamResponse xx : listSelected) {
                ctsanPhamService.updateTrangThai(0, xx.getMa());
                System.out.println(xx.getMa());
            }
//                lstctSpViewModel = ctsanPhamService.getAllViewModel();
            cbbTrangThai.setSelectedIndex(0);
            loadTableChiTietSP(lstctSpViewModel);
            JOptionPane.showMessageDialog(this, "Cập nhật thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnKhoiPhucActionPerformed

    private void cbbTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangThaiActionPerformed
        try {
            String hang = "";
            String mauSac = "";
            String kichThuoc = "";
            String chatLieu = "";
            int row = tblSanPhamCTSP.getSelectedRow();
            if (row >= 0) {
                SanPhamResponse sanPhamResponse = lstSpViewModel.get(row);
                if (cboHang.getSelectedItem() != null && cboMau.getSelectedItem() != null && cboSize.getSelectedItem() != null && cboChatLieu.getSelectedItem() != null) {
                    hang = cboHang.getSelectedItem().toString();
                    mauSac = cboMau.getSelectedItem().toString();
                    kichThuoc = cboSize.getSelectedItem().toString();
                    chatLieu = cboChatLieu.getSelectedItem().toString();
                    if (cboHang.getSelectedItem().toString().equals("Tất cả")) {
                        hang = "";
                    }
                    if (cboMau.getSelectedItem().toString().equals("Tất cả")) {
                        mauSac = "";
                    }
                    if (cboSize.getSelectedItem().toString().equals("Tất cả")) {
                        kichThuoc = "";
                    }
                    if (cboChatLieu.getSelectedItem().toString().equals("Tất cả")) {
                        chatLieu = "";
                    }
                    lstctSpViewModel = banHangService.findCTSPCTSP(sanPhamResponse.getTen(), hang, mauSac, kichThuoc, chatLieu, cbbTrangThai.getSelectedIndex());
                    loadTableChiTietSP(lstctSpViewModel);
                }
            }
            if(cbbTrangThai.getSelectedIndex() == 0){
                btnXoa.setEnabled(true);
                btnKhoiPhuc.setEnabled(false);
            }else{
                btnXoa.setEnabled(false);
                btnKhoiPhuc.setEnabled(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cbbTrangThaiActionPerformed

    
    
    private void txtAddTenMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddTenMauSacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddTenMauSacActionPerformed

    private void tblSanPhamCTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamCTSPMouseClicked
        try {
            int index = tblSanPhamCTSP.getSelectedRow();
            SanPhamResponse sanPhamResponse = lstSpViewModel.get(index);
            lstctSpViewModel.clear();
            lstctSpViewModel = ctsanPhamService.findTrangThai(sanPhamResponse.getId(), 0);
            loadTableChiTietSP(lstctSpViewModel);
            DiaLogChiTietSP.setVisible(true);
            DiaLogChiTietSP.setLocationRelativeTo(null);
            DiaLogChiTietSP.setTitle("Chi tiết sản phẩm: " + sanPhamResponse.getTen());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblSanPhamCTSPMouseClicked

    private void cboHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboHangActionPerformed

    private void cboMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMauActionPerformed

    private void cboSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSizeActionPerformed

    private void cboChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChatLieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboChatLieuActionPerformed

    private void tblSanPhamCTSPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamCTSPMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSanPhamCTSPMouseEntered

    private void txtTimKiemSp1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemSp1CaretUpdate
        try {
            lstSpViewModel = sanPhamService.findMaOrTen(txtTimKiemSp1.getText());
            LoadToTableSp(lstSpViewModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtTimKiemSp1CaretUpdate

    private void cboHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboHangItemStateChanged
        try {
            String hang = "";
            String mauSac = "";
            String kichThuoc = "";
            String chatLieu = "";
            int row = tblSanPhamCTSP.getSelectedRow();
            if (row >= 0) {
                SanPhamResponse sanPhamResponse = lstSpViewModel.get(row);
                if (cboHang.getSelectedItem() != null && cboMau.getSelectedItem() != null && cboSize.getSelectedItem() != null && cboChatLieu.getSelectedItem() != null) {
                    hang = cboHang.getSelectedItem().toString();
                    mauSac = cboMau.getSelectedItem().toString();
                    kichThuoc = cboSize.getSelectedItem().toString();
                    chatLieu = cboChatLieu.getSelectedItem().toString();
                    if (cboHang.getSelectedItem().toString().equals("Tất cả")) {
                        hang = "";
                    }
                    if (cboMau.getSelectedItem().toString().equals("Tất cả")) {
                        mauSac = "";
                    }
                    if (cboSize.getSelectedItem().toString().equals("Tất cả")) {
                        kichThuoc = "";
                    }
                    if (cboChatLieu.getSelectedItem().toString().equals("Tất cả")) {
                        chatLieu = "";
                    }
                    lstctSpViewModel = banHangService.findCTSPCTSP(sanPhamResponse.getTen(), hang, mauSac, kichThuoc, chatLieu, cbbTrangThai.getSelectedIndex());
                    loadTableChiTietSP(lstctSpViewModel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cboHangItemStateChanged

    private void cboMauItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMauItemStateChanged
        String hang = "";
        String mauSac = "";
        String kichThuoc = "";
        String chatLieu = "";
        int row = tblSanPhamCTSP.getSelectedRow();
        if (row >= 0) {
            SanPhamResponse sanPhamResponse = lstSpViewModel.get(row);
            if (cboHang.getSelectedItem() != null && cboMau.getSelectedItem() != null && cboSize.getSelectedItem() != null && cboChatLieu.getSelectedItem() != null) {
                hang = cboHang.getSelectedItem().toString();
                mauSac = cboMau.getSelectedItem().toString();
                kichThuoc = cboSize.getSelectedItem().toString();
                chatLieu = cboChatLieu.getSelectedItem().toString();
                if (cboHang.getSelectedItem().toString().equals("Tất cả")) {
                    hang = "";
                }
                if (cboMau.getSelectedItem().toString().equals("Tất cả")) {
                    mauSac = "";
                }
                if (cboSize.getSelectedItem().toString().equals("Tất cả")) {
                    kichThuoc = "";
                }
                if (cboChatLieu.getSelectedItem().toString().equals("Tất cả")) {
                    chatLieu = "";
                }
                lstctSpViewModel = banHangService.findCTSPCTSP(sanPhamResponse.getTen(), hang, mauSac, kichThuoc, chatLieu, cbbTrangThai.getSelectedIndex());
                loadTableChiTietSP(lstctSpViewModel);
            }
        }
    }//GEN-LAST:event_cboMauItemStateChanged

    private void cboSizeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSizeItemStateChanged
        String hang = "";
        String mauSac = "";
        String kichThuoc = "";
        String chatLieu = "";
        int row = tblSanPhamCTSP.getSelectedRow();
        if (row >= 0) {
            SanPhamResponse sanPhamResponse = lstSpViewModel.get(row);
            if (cboHang.getSelectedItem() != null && cboMau.getSelectedItem() != null && cboSize.getSelectedItem() != null && cboChatLieu.getSelectedItem() != null) {
                hang = cboHang.getSelectedItem().toString();
                mauSac = cboMau.getSelectedItem().toString();
                kichThuoc = cboSize.getSelectedItem().toString();
                chatLieu = cboChatLieu.getSelectedItem().toString();
                if (cboHang.getSelectedItem().toString().equals("Tất cả")) {
                    hang = "";
                }
                if (cboMau.getSelectedItem().toString().equals("Tất cả")) {
                    mauSac = "";
                }
                if (cboSize.getSelectedItem().toString().equals("Tất cả")) {
                    kichThuoc = "";
                }
                if (cboChatLieu.getSelectedItem().toString().equals("Tất cả")) {
                    chatLieu = "";
                }
                lstctSpViewModel = banHangService.findCTSPCTSP(sanPhamResponse.getTen(), hang, mauSac, kichThuoc, chatLieu, cbbTrangThai.getSelectedIndex());
                loadTableChiTietSP(lstctSpViewModel);
            }
        }
    }//GEN-LAST:event_cboSizeItemStateChanged

    private void cboChatLieuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboChatLieuItemStateChanged
        try {
            String hang = "";
            String mauSac = "";
            String kichThuoc = "";
            String chatLieu = "";
            int row = tblSanPhamCTSP.getSelectedRow();
            if (row >= 0) {
                SanPhamResponse sanPhamResponse = lstSpViewModel.get(row);
                if (cboHang.getSelectedItem() != null && cboMau.getSelectedItem() != null && cboSize.getSelectedItem() != null && cboChatLieu.getSelectedItem() != null) {
                    hang = cboHang.getSelectedItem().toString();
                    mauSac = cboMau.getSelectedItem().toString();
                    kichThuoc = cboSize.getSelectedItem().toString();
                    chatLieu = cboChatLieu.getSelectedItem().toString();
                    if (cboHang.getSelectedItem().toString().equals("Tất cả")) {
                        hang = "";
                    }
                    if (cboMau.getSelectedItem().toString().equals("Tất cả")) {
                        mauSac = "";
                    }
                    if (cboSize.getSelectedItem().toString().equals("Tất cả")) {
                        kichThuoc = "";
                    }
                    if (cboChatLieu.getSelectedItem().toString().equals("Tất cả")) {
                        chatLieu = "";
                    }
                    lstctSpViewModel = banHangService.findCTSPCTSP(sanPhamResponse.getTen(), hang, mauSac, kichThuoc, chatLieu, cbbTrangThai.getSelectedIndex());
                    loadTableChiTietSP(lstctSpViewModel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cboChatLieuItemStateChanged

    private List<CTSanPhamResponse> GetSelected() {
        List<CTSanPhamResponse> listselected = new ArrayList<>();
        for (int i = 0; i < tblCTSanPham.getRowCount(); i++) {
            boolean check = Boolean.valueOf(tblCTSanPham.getValueAt(i, 11).toString());
            System.out.println(check);
            if (check) {
                listselected.add(lstctSpViewModel.get(i));
            }
        }
        return listselected;
    }

    public SanPham getSanPhamByForm() {
        if (txtTenSanPham.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, " Tên sản phẩm không được rỗng");
            return null;
        }
        for (SanPhamResponse xx : lstSpViewModel) {
            if (txtTenSanPham.getText().equals(xx.getTen())) {
                JOptionPane.showMessageDialog(this, "Đã có sản phẩm này này ");
                return null;
            }
        }
        if (txtTenSanPham.getText().length() > 100) {
            JOptionPane.showMessageDialog(this, " Tên sản phẩm tối đa 100 ký tự");
            return null;
        }
        String tensp = txtTenSanPham.getText().trim();
        String maSp = txtMaSanPham.getText().trim();
        SanPham sanPham = new SanPham();
        sanPham.setMa(maSp);
        sanPham.setTen(tensp);
        return sanPham;
    }

    public Hang getHangByForm() {
        String tenHang = txtTenThuocTinh.getText().trim();
        String maHang = txtMaThuocTinh.getText().trim();
        Hang hang = new Hang();
        hang.setMa(maHang);
        hang.setTen(tenHang);
        return hang;
    }

    public MauSac getMauSacByForm() {
        String tenMausac = txtTenThuocTinh.getText().trim();
        String maMauSac = txtMaThuocTinh.getText().trim();
        MauSac mausac = new MauSac();
        mausac.setMa(maMauSac);
        mausac.setTen(tenMausac);
        return mausac;
    }

    public KichThuoc getKichThuocByForm() {
        String tenKichThuoc = txtTenThuocTinh.getText().trim();
        String maKichThuoc = txtMaThuocTinh.getText().trim();
        KichThuoc kichThuoc = new KichThuoc();
        kichThuoc.setMa(maKichThuoc);
        kichThuoc.setTen(tenKichThuoc);
        return kichThuoc;
    }

    public ChatLieu getChatLieuByForm() {

        String tenChatLieu = txtTenThuocTinh.getText().trim();
        String maChatLieu = txtMaThuocTinh.getText().trim();
        ChatLieu chatLieu = new ChatLieu();
        chatLieu.setMa(maChatLieu);
        chatLieu.setTen(tenChatLieu);
        return chatLieu;
    }

    public ChiTietSP getChiTietSp() {
        if (txtMaVach.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã vạch không được rỗng");
            return null;
        }
        for (CTSanPhamResponse xx : lstctSpViewModel) {
            if (txtMaVach.getText().equals(xx.getMaVach())) {
                JOptionPane.showMessageDialog(this, "Đã có mã vạch này ");
                return null;
            }
        }
        try {
            int mavach = 0;
            mavach = Integer.parseInt(txtMaVach.getText().trim());
            if (mavach <= 0) {
                JOptionPane.showMessageDialog(this, "Giá bán lớn hơn 0");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Mã vạch phải là số");
            return null;
        }
        for (CTSanPhamResponse xx : lstctSpViewModel) {
            if (txtMaVach.getText().equals(xx.getMaVach())) {
                JOptionPane.showMessageDialog(this, "Đã có mã vạch này ");
                return null;
            }
        }
        if (txtMaVach.getText().length() > 36) {
            JOptionPane.showMessageDialog(this, "Mã vạch tối đa 36 ký tự");
            return null;
        }
        if (txaMoTa.getText().length() > 255) {
            JOptionPane.showMessageDialog(this, " Mô tả tối đa 255 ký tự");
            return null;
        }
        if (txtGiaBan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá bán không được rỗng");
            return null;
        }
        if (txtSoLuongTon.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số lượng tồn không được rỗng");
            return null;
        }

        try {
            int soLuongTon = 0;
            soLuongTon = Integer.parseInt(txtSoLuongTon.getText().trim());
            if (soLuongTon <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng tồn lớn hơn 0");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng tồn phải là số");
            return null;
        }

        try {
            int giaBan = 0;
            giaBan = Integer.parseInt(txtGiaBan.getText().trim());
            if (giaBan <= 0) {
                JOptionPane.showMessageDialog(this, "Giá bán lớn hơn 0");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giá bán phải là số");
            return null;
        }
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
        chiTietSp.setTrangThaiXoa(0);
        return chiTietSp;
    }

    public void loadTableChiTietSP(List<CTSanPhamResponse> lstChiTietSp) {
//        btnXoa.setEnabled(true);
//        btnKhoiPhuc.setEnabled(false);
        dtm1.setRowCount(0);
        lblTongSoSanPham.setText(lstChiTietSp.size() + "");
        if (lstChiTietSp.size() == 0) {
            return;
        }
        count = lstChiTietSp.size();
        if (count % 10 == 0) {
            tongsoTrang = count / 10;
        } else {
            tongsoTrang = count / 10 + 1;
        }
        int n = 10 * (tranghientai);
        int m = n + 10;
        if (count % 10 == 0) {
            for (int i = n; i < m; i++) {
                dtm1.addRow(new Object[]{i + 1, lstChiTietSp.get(i).getMactsp(), lstChiTietSp.get(i).getTensp(), lstChiTietSp.get(i).getMauSac(),
                    lstChiTietSp.get(i).getKichThuoc(), lstChiTietSp.get(i).getHang(), lstChiTietSp.get(i).getChatLieu(), lstChiTietSp.get(i).getMoTa(),
                    lstChiTietSp.get(i).getSoLuongTon(), lstChiTietSp.get(i).getGiaBan(), lstChiTietSp.get(i).getMaVach(), false});
            }
        } else {
            if (tranghientai == tongsoTrang - 1) {
                int k = n + count % 10;
                for (int i = n; i < k; i++) {
                    dtm1.addRow(new Object[]{i + 1, lstChiTietSp.get(i).getMactsp(), lstChiTietSp.get(i).getTensp(), lstChiTietSp.get(i).getMauSac(),
                        lstChiTietSp.get(i).getKichThuoc(), lstChiTietSp.get(i).getHang(), lstChiTietSp.get(i).getChatLieu(), lstChiTietSp.get(i).getMoTa(),
                        lstChiTietSp.get(i).getSoLuongTon(), lstChiTietSp.get(i).getGiaBan(), lstChiTietSp.get(i).getMaVach(), false});
                }
            } else {
                for (int i = n; i < m; i++) {
                    dtm1.addRow(new Object[]{i + 1, lstChiTietSp.get(i).getMactsp(), lstChiTietSp.get(i).getTensp(), lstChiTietSp.get(i).getMauSac(),
                        lstChiTietSp.get(i).getKichThuoc(), lstChiTietSp.get(i).getHang(), lstChiTietSp.get(i).getChatLieu(), lstChiTietSp.get(i).getMoTa(),
                        lstChiTietSp.get(i).getSoLuongTon(), lstChiTietSp.get(i).getGiaBan(), lstChiTietSp.get(i).getMaVach(), false});
                }
            }
        }
        lblRecord.setText(tranghientai + 1 + " of " + tongsoTrang);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DiaLogChiTietSP;
    private javax.swing.JFrame JFrameMauSac;
    private javax.swing.JFrame JframeChatLieu;
    private javax.swing.JFrame JframeHang;
    private javax.swing.JFrame JframeKichThuoc;
    private javax.swing.JButton btXuatFileExcel;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddChatLieu;
    private javax.swing.JButton btnAddHang;
    private javax.swing.JButton btnAddJraKichThuoc;
    private javax.swing.JButton btnAddKichThuoc;
    private javax.swing.JButton btnAddMausac;
    private javax.swing.JButton btnAddMs;
    private javax.swing.JButton btnChiTietSanPham;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClearr;
    private javax.swing.JButton btnCuoi;
    private javax.swing.JButton btnDau;
    private javax.swing.JButton btnImportExcel;
    private javax.swing.JButton btnJfraAddChatLieu;
    private javax.swing.JButton btnJfraAddHang;
    private javax.swing.JButton btnKhoiPhuc;
    private javax.swing.JButton btnLamMoiThuocTinh;
    private javax.swing.JButton btnSau;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaThuocTinh;
    private javax.swing.JButton btnTaiMauExcel;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemThuocTinh;
    private javax.swing.JButton btnTruoc;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cbbChatLieu;
    private javax.swing.JComboBox<String> cbbHangct;
    private javax.swing.JComboBox<String> cbbKichThuocct;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbSanPham;
    private javax.swing.JComboBox<String> cbbTrangThai;
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboHang;
    private javax.swing.JComboBox<String> cboMau;
    private javax.swing.JComboBox<String> cboSize;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
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
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblRecord;
    private javax.swing.JLabel lblTongSoSanPham;
    private javax.swing.JRadioButton radioChatLieu;
    private javax.swing.JRadioButton radioHang;
    private javax.swing.JRadioButton radioKichThuoc;
    private javax.swing.JRadioButton radioMausac;
    private javax.swing.JTable tblCTSanPham;
    private javax.swing.JTable tblSanPhamCTSP;
    private javax.swing.JTable tblSanPhamm;
    private javax.swing.JTable tblThuocTinhSP;
    private javax.swing.JTextArea txaMoTa;
    private javax.swing.JTextField txtAddTenMauSac;
    private javax.swing.JTextField txtFindCTSanPham;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtJfraTenChatLieu;
    private javax.swing.JTextField txtJfraTenHang;
    private javax.swing.JTextField txtJraTenKichThuoc;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtMaThuocTinh;
    private javax.swing.JTextField txtMaVach;
    private javax.swing.JTextField txtSoLuongTon;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTenThuocTinh;
    private javax.swing.JTextField txtTimKiemSp;
    private javax.swing.JTextField txtTimKiemSp1;
    private javax.swing.JTextField txtmaCTSP;
    // End of variables declaration//GEN-END:variables

}
