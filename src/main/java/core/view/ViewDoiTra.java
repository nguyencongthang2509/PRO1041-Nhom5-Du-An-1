package core.view;

import core.quanly.service.BanHangService;
import core.quanly.service.HoaDonservice;
import core.quanly.service.TraHangService;
import core.quanly.service.impl.BanHangServiceImpl;
import core.quanly.service.impl.HoaDonserviceImpl;
import core.quanly.service.impl.TraHangServiceImpl;
import core.quanly.viewmodel.BhHoaDonChiTietResponse;
import core.quanly.viewmodel.HdHoaDonChiTietResponse1;
import core.quanly.viewmodel.ThHoaDonChiTietResponse;
import core.quanly.viewmodel.ThHoaDonResponse;
import domainmodels.ChiTietSP;
import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import domainmodels.HoaDonTraHang;
import domainmodels.HoaDonTraHangChiTiet;
import domainmodels.NhanVien;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.math3.util.BigReal;
import util.ConvertMoneyToString;

/**
 *
 * @author thangncph26123
 */
public class ViewDoiTra extends javax.swing.JPanel {

    private DefaultTableModel modelHoaDon = new DefaultTableModel();
    private DefaultTableModel modelHoaDonChiTiet = new DefaultTableModel();
    private DefaultTableModel modelSanPham = new DefaultTableModel();
    private DefaultTableModel modelHoaDonTraHang = new DefaultTableModel();
    private DefaultTableModel modelHoaDonTraHangChiTiet = new DefaultTableModel();
    private DefaultTableModel modelHoaDonChiTietCuTaiQuay = new DefaultTableModel();
    private DefaultTableModel modelHoaDonChiTietMoiTaiQuay = new DefaultTableModel();
    private TraHangService traHangService;
    private BanHangService banHangService;
    private List<ThHoaDonResponse> listHoaDon;
    private List<HoaDonTraHang> listHoaDonTraHang;
    private List<HoaDonTraHangChiTiet> listHoaDonTraHangChiTiet;
    private List<BhHoaDonChiTietResponse> listHoaDonChiTiet;
    private Map<String, ThHoaDonChiTietResponse> listSanPhamTra;
    private List<HdHoaDonChiTietResponse1> listHoaDonChiTietCuTaiQuay = new ArrayList<>();
    private NhanVien nhanVien;
    private HoaDonservice hoaDonservice;

    public ViewDoiTra(NhanVien nv) {
        initComponents();
        nhanVien = nv;
        modelHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        modelHoaDonTraHang = (DefaultTableModel) tblHoaDonTraHang.getModel();
        modelHoaDonChiTiet = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        modelHoaDonTraHangChiTiet = (DefaultTableModel) tblHoaDonTraHangChiTiet.getModel();
        modelHoaDonTraHangChiTiet = (DefaultTableModel) tblHoaDonTraHangChiTiet.getModel();
        modelHoaDonChiTietCuTaiQuay = (DefaultTableModel) tblHoaDonChiTietCuTaiQuay.getModel();
        modelHoaDonChiTietMoiTaiQuay = (DefaultTableModel) tblHoaDonChiTietCuTaiQuay1.getModel();
        modelSanPham = (DefaultTableModel) tblSanPham.getModel();
        traHangService = new TraHangServiceImpl();
        hoaDonservice = new HoaDonserviceImpl();
        traHangService = new TraHangServiceImpl();
        banHangService = new BanHangServiceImpl();
        listHoaDon = traHangService.getAllResponseHD();
        listHoaDonTraHang = traHangService.getAllHoaDonTraHang();
        loadHoaDonTraHang(listHoaDonTraHang);
        listHoaDonTraHangChiTiet = new ArrayList<>();
        listSanPhamTra = new HashMap<>();
        loadTableHoaDon(listHoaDon);
    }

    private void loadTableHoaDon(List<ThHoaDonResponse> list) {
        modelHoaDon.setRowCount(0);
        for (ThHoaDonResponse xx : list) {
            modelHoaDon.addRow(xx.toDataRow());
        }
    }

    private void loadTableHoaDonTraHang(List<ThHoaDonResponse> list) {
        modelHoaDonTraHang.setRowCount(0);
        for (ThHoaDonResponse xx : list) {
            modelHoaDonTraHang.addRow(xx.toDataRowTraHang());
        }
    }
    
    private void loadHoaDonChiTiet(List<HdHoaDonChiTietResponse1> list) {
        modelHoaDonChiTietCuTaiQuay.setRowCount(0);
        for (HdHoaDonChiTietResponse1 xx : list) {
            modelHoaDonChiTietCuTaiQuay.addRow(xx.toDataRow());
        }
    }
    
    private void loadHoaDonChiTiet1(List<HdHoaDonChiTietResponse1> list) {
        modelHoaDonChiTietMoiTaiQuay.setRowCount(0);
        for (HdHoaDonChiTietResponse1 xx : list) {
            modelHoaDonChiTietMoiTaiQuay.addRow(xx.toDataRow());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ViewHoaDonChiTiet = new javax.swing.JFrame();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        txtMaHoaDon = new javax.swing.JTextField();
        txtNgayThanhToan = new javax.swing.JTextField();
        txtNhanVien = new javax.swing.JTextField();
        txtKhachHang = new javax.swing.JTextField();
        txtTienDaThanhToan = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtPhanTramGiam = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtCapBac = new javax.swing.JTextField();
        ViewHoaDonTraHangChiTiet = new javax.swing.JFrame();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblHoaDonTraHangChiTiet = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtMaHoaDonCu = new javax.swing.JTextField();
        txtMaHoaDonMoi = new javax.swing.JTextField();
        txtTenKhachHangTraHang = new javax.swing.JTextField();
        txtSdtKhachHangTraHang = new javax.swing.JTextField();
        txtTenNhanVienTraHang = new javax.swing.JTextField();
        txtSdtNhanVienTraHang = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtNgayTraHang = new javax.swing.JTextField();
        txtTongTienHoanTra = new javax.swing.JTextField();
        txtGhiChuTraHang = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        lblTienTraKhachBangChu = new javax.swing.JLabel();
        btnXuatHoaDonTraHang = new javax.swing.JButton();
        btnXemChiTietHoaDonCu = new javax.swing.JButton();
        btnXemChiTietHoaDonMoi = new javax.swing.JButton();
        HoaDonTaiQuayCu = new javax.swing.JFrame();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblHoaDonChiTietCuTaiQuay = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtMaHoaDonCuTaiQuay = new javax.swing.JTextField();
        txtKhachHangCuTaiQuay = new javax.swing.JTextField();
        txtNhanVienCuTaiQuay = new javax.swing.JTextField();
        txtNgayTaoCuTaiQuay = new javax.swing.JTextField();
        txtNgayThanhToanCuTaiQuay = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtTrangThaiCuTaiQuay = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtThanhTienCuTaiQuay = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtTienKhachDuaCuTaiQuay = new javax.swing.JTextField();
        txtTienKhachCkCuTaiQuay = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtMaGiaoDichCuTaiQuay = new javax.swing.JTextField();
        txtTienThuaTaiQuayCu = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        lblThanhTienBangChuCuTaiQuay = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtHTThanhToanCuTaiQuay = new javax.swing.JTextField();
        txtHTGiaoHangCuTaiQuay = new javax.swing.JTextField();
        txtPhanTRamGiamGiaCuTaiQuay = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        HoaDonDatHangCu = new javax.swing.JFrame();
        HoaDonDatHangMoi = new javax.swing.JFrame();
        HoaDonTaiQuayMoi = new javax.swing.JFrame();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblHoaDonChiTietCuTaiQuay1 = new javax.swing.JTable();
        jLabel38 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        txtMaHoaDonCuTaiQuay1 = new javax.swing.JTextField();
        txtKhachHangCuTaiQuay1 = new javax.swing.JTextField();
        txtNhanVienCuTaiQuay1 = new javax.swing.JTextField();
        txtNgayTaoCuTaiQuay1 = new javax.swing.JTextField();
        txtNgayThanhToanCuTaiQuay1 = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        txtTrangThaiCuTaiQuay1 = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        txtThanhTienCuTaiQuay1 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        txtTienKhachDuaCuTaiQuay1 = new javax.swing.JTextField();
        txtTienKhachCkCuTaiQuay1 = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        txtMaGiaoDichCuTaiQuay1 = new javax.swing.JTextField();
        txtTienThuaTaiQuayCu1 = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        lblThanhTienBangChuCuTaiQuay1 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        txtHTThanhToanCuTaiQuay1 = new javax.swing.JTextField();
        txtHTGiaoHangCuTaiQuay1 = new javax.swing.JTextField();
        txtPhanTRamGiamGiaCuTaiQuay1 = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        btnClear = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMaHDNgoai = new javax.swing.JTextField();
        txtTenKHNgoai = new javax.swing.JTextField();
        txtSDTNgoai = new javax.swing.JTextField();
        txtDiaChiNgoai = new javax.swing.JTextField();
        txtThanhTienNgoai = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtTienTraKhach = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTenKhTra = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnTraHang = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnXemChiTiet = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDonTraHang = new javax.swing.JTable();
        jTextField7 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        ViewHoaDonChiTiet.setTitle("Thông tin chi tiết của hóa đơn");
        ViewHoaDonChiTiet.setMinimumSize(new java.awt.Dimension(942, 542));
        ViewHoaDonChiTiet.setResizable(false);
        ViewHoaDonChiTiet.setSize(new java.awt.Dimension(942, 542));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel11.setText("Mã hóa đơn:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel12.setText("Ngày thanh toán:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel13.setText("Nhân viên:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel14.setText("Khách hàng:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel15.setText("Tiền sản phẩm:");

        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã CTSP", "Tên SP", "Hãng", "Màu sắc", "Kích thước", "Chất liệu", "Số lượng", "Giảm giá", "Giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonChiTiet.setRowHeight(25);
        tblHoaDonChiTiet.setSelectionBackground(new java.awt.Color(86, 154, 222));
        tblHoaDonChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChiTietMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblHoaDonChiTiet);

        txtMaHoaDon.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtMaHoaDon.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtNgayThanhToan.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNgayThanhToan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtNhanVien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNhanVien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtKhachHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtKhachHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtTienDaThanhToan.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTienDaThanhToan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jButton3.setBackground(new java.awt.Color(153, 204, 255));
        jButton3.setText("Trả tất cả");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel16.setText("Số phần trăm được giảm:");

        txtPhanTramGiam.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPhanTramGiam.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel17.setText("Cấp bậc khách hàng lúc đó:");

        txtCapBac.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCapBac.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienDaThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgayThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCapBac, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPhanTramGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtPhanTramGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtNgayThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCapBac, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienDaThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ViewHoaDonChiTietLayout = new javax.swing.GroupLayout(ViewHoaDonChiTiet.getContentPane());
        ViewHoaDonChiTiet.getContentPane().setLayout(ViewHoaDonChiTietLayout);
        ViewHoaDonChiTietLayout.setHorizontalGroup(
            ViewHoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ViewHoaDonChiTietLayout.setVerticalGroup(
            ViewHoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ViewHoaDonTraHangChiTiet.setTitle("Hóa đơn trả hàng chi tiết");
        ViewHoaDonTraHangChiTiet.setMaximumSize(new java.awt.Dimension(975, 575));
        ViewHoaDonTraHangChiTiet.setMinimumSize(new java.awt.Dimension(975, 575));
        ViewHoaDonTraHangChiTiet.setResizable(false);
        ViewHoaDonTraHangChiTiet.setSize(new java.awt.Dimension(975, 575));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        tblHoaDonTraHangChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã CTSP", "Tên SP", "Hãng", "Màu sắc", "Kích thước", "Số lượng trả", "Tiền hoàn trả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonTraHangChiTiet.setRowHeight(25);
        tblHoaDonTraHangChiTiet.setSelectionBackground(new java.awt.Color(86, 154, 222));
        tblHoaDonTraHangChiTiet.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(tblHoaDonTraHangChiTiet);

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel18.setText("Hóa đơn trả hàng:");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel19.setText("Mã hóa đơn cũ:");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel20.setText("Mã hóa đơn mới:");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel21.setText("Khách hàng:");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel22.setText("SĐT khách hàng:");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel23.setText("Nhân viên trả hàng:");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel24.setText("SĐT nhân viên trả hàng:");

        txtMaHoaDonCu.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtMaHoaDonCu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtMaHoaDonMoi.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtMaHoaDonMoi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtTenKhachHangTraHang.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTenKhachHangTraHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtSdtKhachHangTraHang.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtSdtKhachHangTraHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtTenNhanVienTraHang.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTenNhanVienTraHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtSdtNhanVienTraHang.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtSdtNhanVienTraHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel25.setText("Danh sách hàng hóa đã trả:");

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel26.setText("Ngày trả hàng:");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel27.setText("Tổng tiền hoàn trả:");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel28.setText("Ghi chú:");

        txtNgayTraHang.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtNgayTraHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtTongTienHoanTra.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTongTienHoanTra.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtGhiChuTraHang.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtGhiChuTraHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel29.setText("Bằng chữ:");

        lblTienTraKhachBangChu.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        lblTienTraKhachBangChu.setText("---");

        btnXuatHoaDonTraHang.setBackground(new java.awt.Color(153, 204, 255));
        btnXuatHoaDonTraHang.setText("Xuất hóa đơn trả hàng");

        btnXemChiTietHoaDonCu.setBackground(new java.awt.Color(153, 204, 255));
        btnXemChiTietHoaDonCu.setIcon(new ImageIcon("src/main/images/eye.png"));
        btnXemChiTietHoaDonCu.setToolTipText("Xem chi tiết hóa đơn cũ");
        btnXemChiTietHoaDonCu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemChiTietHoaDonCuActionPerformed(evt);
            }
        });

        btnXemChiTietHoaDonMoi.setBackground(new java.awt.Color(153, 204, 255));
        btnXemChiTietHoaDonMoi.setIcon(new ImageIcon("src/main/images/eye.png"));
        btnXemChiTietHoaDonMoi.setToolTipText("Xem chi tiết hóa đơn mới");
        btnXemChiTietHoaDonMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemChiTietHoaDonMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSdtNhanVienTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenNhanVienTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSdtKhachHangTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenKhachHangTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMaHoaDonCu, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMaHoaDonMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(5, 5, 5)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnXemChiTietHoaDonMoi)
                                            .addComponent(btnXemChiTietHoaDonCu))))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addGap(47, 47, 47)
                                        .addComponent(txtNgayTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel27)
                                            .addComponent(jLabel28))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtGhiChuTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTongTienHoanTra, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel29)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblTienTraKhachBangChu))))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXuatHoaDonTraHang)))
                .addContainerGap(164, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 939, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(22, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaHoaDonCu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnXemChiTietHoaDonCu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaHoaDonMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnXemChiTietHoaDonMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(txtNgayTraHang)
                                .addGap(39, 39, 39))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel20)
                                    .addComponent(txtTongTienHoanTra, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel26)
                                            .addComponent(jLabel19))
                                        .addGap(39, 39, 39)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTenKhachHangTraHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel29)
                                        .addComponent(lblTienTraKhachBangChu)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSdtKhachHangTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(txtGhiChuTraHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTenNhanVienTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24)
                    .addComponent(txtSdtNhanVienTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(btnXuatHoaDonTraHang))
                .addContainerGap(256, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                    .addContainerGap(324, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(34, 34, 34)))
        );

        jPanel9Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnXemChiTietHoaDonCu, btnXemChiTietHoaDonMoi, txtMaHoaDonCu, txtMaHoaDonMoi});

        javax.swing.GroupLayout ViewHoaDonTraHangChiTietLayout = new javax.swing.GroupLayout(ViewHoaDonTraHangChiTiet.getContentPane());
        ViewHoaDonTraHangChiTiet.getContentPane().setLayout(ViewHoaDonTraHangChiTietLayout);
        ViewHoaDonTraHangChiTietLayout.setHorizontalGroup(
            ViewHoaDonTraHangChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewHoaDonTraHangChiTietLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        ViewHoaDonTraHangChiTietLayout.setVerticalGroup(
            ViewHoaDonTraHangChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        HoaDonTaiQuayCu.setTitle("Hóa đơn trước khi trả hàng");
        HoaDonTaiQuayCu.setMaximumSize(new java.awt.Dimension(794, 661));
        HoaDonTaiQuayCu.setMinimumSize(new java.awt.Dimension(794, 661));
        HoaDonTaiQuayCu.setPreferredSize(new java.awt.Dimension(794, 661));
        HoaDonTaiQuayCu.setResizable(false);
        HoaDonTaiQuayCu.setSize(new java.awt.Dimension(794, 661));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setMaximumSize(new java.awt.Dimension(794, 661));
        jPanel10.setMinimumSize(new java.awt.Dimension(794, 661));

        tblHoaDonChiTietCuTaiQuay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã CTSP", "Tên SP", "Hãng", "Màu", "Size", "Số lượng", "Đơn giá", "Giá bán", "Giảm giá", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonChiTietCuTaiQuay.setRowHeight(25);
        tblHoaDonChiTietCuTaiQuay.setSelectionBackground(new java.awt.Color(86, 154, 222));
        tblHoaDonChiTietCuTaiQuay.setShowGrid(false);
        tblHoaDonChiTietCuTaiQuay.getTableHeader().setReorderingAllowed(false);
        tblHoaDonChiTietCuTaiQuay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChiTietCuTaiQuayMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblHoaDonChiTietCuTaiQuay);

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 28)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 204, 255));
        jLabel30.setText("Hóa đơn trước khi trả hàng");

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel31.setText("Mã hóa đơn:");

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel32.setText("Nhân viên:");

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel33.setText("Khách hàng:");

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel34.setText("Ngày tạo");

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel35.setText("Ngày thanh toán:");

        txtMaHoaDonCuTaiQuay.setEditable(false);
        txtMaHoaDonCuTaiQuay.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtMaHoaDonCuTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtKhachHangCuTaiQuay.setEditable(false);
        txtKhachHangCuTaiQuay.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtKhachHangCuTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtNhanVienCuTaiQuay.setEditable(false);
        txtNhanVienCuTaiQuay.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtNhanVienCuTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtNgayTaoCuTaiQuay.setEditable(false);
        txtNgayTaoCuTaiQuay.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtNgayTaoCuTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtNgayThanhToanCuTaiQuay.setEditable(false);
        txtNgayThanhToanCuTaiQuay.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtNgayThanhToanCuTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel36.setText("Trạng thái:");

        txtTrangThaiCuTaiQuay.setEditable(false);
        txtTrangThaiCuTaiQuay.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTrangThaiCuTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel37.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel37.setText("Thành tiền:");

        txtThanhTienCuTaiQuay.setEditable(false);
        txtThanhTienCuTaiQuay.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtThanhTienCuTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel39.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel39.setText("Tiền khách đưa:");

        jLabel40.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel40.setText("Tiền khách CK:");

        txtTienKhachDuaCuTaiQuay.setEditable(false);
        txtTienKhachDuaCuTaiQuay.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTienKhachDuaCuTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtTienKhachCkCuTaiQuay.setEditable(false);
        txtTienKhachCkCuTaiQuay.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTienKhachCkCuTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel41.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel41.setText("Tiền thừa:");

        jLabel42.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel42.setText("Mã giao dịch:");

        txtMaGiaoDichCuTaiQuay.setEditable(false);
        txtMaGiaoDichCuTaiQuay.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtMaGiaoDichCuTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtTienThuaTaiQuayCu.setEditable(false);
        txtTienThuaTaiQuayCu.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTienThuaTaiQuayCu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel43.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel43.setText("Bằng chữ:");

        lblThanhTienBangChuCuTaiQuay.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        lblThanhTienBangChuCuTaiQuay.setText("---");

        jLabel45.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel45.setText("HT thanh toán:");

        jLabel46.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel46.setText("HT giao hàng:");

        txtHTThanhToanCuTaiQuay.setEditable(false);
        txtHTThanhToanCuTaiQuay.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtHTThanhToanCuTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtHTGiaoHangCuTaiQuay.setEditable(false);
        txtHTGiaoHangCuTaiQuay.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtHTGiaoHangCuTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtPhanTRamGiamGiaCuTaiQuay.setEditable(false);
        txtPhanTRamGiamGiaCuTaiQuay.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtPhanTRamGiamGiaCuTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel48.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel48.setText("Phần trăm giảm giá:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(jLabel30)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtThanhTienCuTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31)
                                    .addComponent(jLabel36))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaHoaDonCuTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNhanVienCuTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtKhachHangCuTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgayTaoCuTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgayThanhToanCuTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTrangThaiCuTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel39)
                                    .addComponent(jLabel40)
                                    .addComponent(jLabel42)
                                    .addComponent(jLabel45)
                                    .addComponent(jLabel46))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHTThanhToanCuTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaGiaoDichCuTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTienThuaTaiQuayCu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHTGiaoHangCuTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTienKhachCkCuTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTienKhachDuaCuTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel48)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPhanTRamGiamGiaCuTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(71, 71, 71))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addGap(253, 253, 253)
                                .addComponent(jLabel41))
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblThanhTienBangChuCuTaiQuay)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaHoaDonCuTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPhanTRamGiamGiaCuTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNhanVienCuTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTienKhachDuaCuTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtKhachHangCuTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienKhachCkCuTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(13, 13, 13)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNgayTaoCuTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaGiaoDichCuTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNgayThanhToanCuTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienThuaTaiQuayCu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTrangThaiCuTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtHTThanhToanCuTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(jLabel37))
                        .addComponent(txtHTGiaoHangCuTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtThanhTienCuTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(lblThanhTienBangChuCuTaiQuay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout HoaDonTaiQuayCuLayout = new javax.swing.GroupLayout(HoaDonTaiQuayCu.getContentPane());
        HoaDonTaiQuayCu.getContentPane().setLayout(HoaDonTaiQuayCuLayout);
        HoaDonTaiQuayCuLayout.setHorizontalGroup(
            HoaDonTaiQuayCuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        HoaDonTaiQuayCuLayout.setVerticalGroup(
            HoaDonTaiQuayCuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout HoaDonDatHangCuLayout = new javax.swing.GroupLayout(HoaDonDatHangCu.getContentPane());
        HoaDonDatHangCu.getContentPane().setLayout(HoaDonDatHangCuLayout);
        HoaDonDatHangCuLayout.setHorizontalGroup(
            HoaDonDatHangCuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        HoaDonDatHangCuLayout.setVerticalGroup(
            HoaDonDatHangCuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout HoaDonDatHangMoiLayout = new javax.swing.GroupLayout(HoaDonDatHangMoi.getContentPane());
        HoaDonDatHangMoi.getContentPane().setLayout(HoaDonDatHangMoiLayout);
        HoaDonDatHangMoiLayout.setHorizontalGroup(
            HoaDonDatHangMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        HoaDonDatHangMoiLayout.setVerticalGroup(
            HoaDonDatHangMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        HoaDonTaiQuayMoi.setTitle("Hóa đơn trước khi trả hàng");
        HoaDonTaiQuayMoi.setMaximumSize(new java.awt.Dimension(794, 661));
        HoaDonTaiQuayMoi.setMinimumSize(new java.awt.Dimension(794, 661));
        HoaDonTaiQuayMoi.setPreferredSize(new java.awt.Dimension(794, 661));
        HoaDonTaiQuayMoi.setResizable(false);
        HoaDonTaiQuayMoi.setSize(new java.awt.Dimension(794, 661));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setMaximumSize(new java.awt.Dimension(794, 661));
        jPanel11.setMinimumSize(new java.awt.Dimension(794, 661));

        tblHoaDonChiTietCuTaiQuay1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã CTSP", "Tên SP", "Hãng", "Màu", "Size", "Số lượng", "Đơn giá", "Giá bán", "Giảm giá", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonChiTietCuTaiQuay1.setRowHeight(25);
        tblHoaDonChiTietCuTaiQuay1.setSelectionBackground(new java.awt.Color(86, 154, 222));
        tblHoaDonChiTietCuTaiQuay1.setShowGrid(false);
        tblHoaDonChiTietCuTaiQuay1.getTableHeader().setReorderingAllowed(false);
        tblHoaDonChiTietCuTaiQuay1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChiTietCuTaiQuay1MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblHoaDonChiTietCuTaiQuay1);

        jLabel38.setFont(new java.awt.Font("Times New Roman", 1, 28)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(102, 204, 255));
        jLabel38.setText("Hóa đơn sau khi trả hàng");

        jLabel44.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel44.setText("Mã hóa đơn:");

        jLabel47.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel47.setText("Nhân viên:");

        jLabel49.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel49.setText("Khách hàng:");

        jLabel50.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel50.setText("Ngày tạo");

        jLabel51.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel51.setText("Ngày thanh toán:");

        txtMaHoaDonCuTaiQuay1.setEditable(false);
        txtMaHoaDonCuTaiQuay1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtMaHoaDonCuTaiQuay1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtKhachHangCuTaiQuay1.setEditable(false);
        txtKhachHangCuTaiQuay1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtKhachHangCuTaiQuay1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtNhanVienCuTaiQuay1.setEditable(false);
        txtNhanVienCuTaiQuay1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtNhanVienCuTaiQuay1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtNgayTaoCuTaiQuay1.setEditable(false);
        txtNgayTaoCuTaiQuay1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtNgayTaoCuTaiQuay1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtNgayThanhToanCuTaiQuay1.setEditable(false);
        txtNgayThanhToanCuTaiQuay1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtNgayThanhToanCuTaiQuay1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel52.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel52.setText("Trạng thái:");

        txtTrangThaiCuTaiQuay1.setEditable(false);
        txtTrangThaiCuTaiQuay1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTrangThaiCuTaiQuay1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel53.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel53.setText("Thành tiền:");

        txtThanhTienCuTaiQuay1.setEditable(false);
        txtThanhTienCuTaiQuay1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtThanhTienCuTaiQuay1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel54.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel54.setText("Tiền khách đưa:");

        jLabel55.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel55.setText("Tiền khách CK:");

        txtTienKhachDuaCuTaiQuay1.setEditable(false);
        txtTienKhachDuaCuTaiQuay1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTienKhachDuaCuTaiQuay1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtTienKhachCkCuTaiQuay1.setEditable(false);
        txtTienKhachCkCuTaiQuay1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTienKhachCkCuTaiQuay1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel56.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel56.setText("Tiền thừa:");

        jLabel57.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel57.setText("Mã giao dịch:");

        txtMaGiaoDichCuTaiQuay1.setEditable(false);
        txtMaGiaoDichCuTaiQuay1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtMaGiaoDichCuTaiQuay1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtTienThuaTaiQuayCu1.setEditable(false);
        txtTienThuaTaiQuayCu1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTienThuaTaiQuayCu1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel58.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel58.setText("Bằng chữ:");

        lblThanhTienBangChuCuTaiQuay1.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        lblThanhTienBangChuCuTaiQuay1.setText("---");

        jLabel59.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel59.setText("HT thanh toán:");

        jLabel60.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel60.setText("HT giao hàng:");

        txtHTThanhToanCuTaiQuay1.setEditable(false);
        txtHTThanhToanCuTaiQuay1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtHTThanhToanCuTaiQuay1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtHTGiaoHangCuTaiQuay1.setEditable(false);
        txtHTGiaoHangCuTaiQuay1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtHTGiaoHangCuTaiQuay1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtPhanTRamGiamGiaCuTaiQuay1.setEditable(false);
        txtPhanTRamGiamGiaCuTaiQuay1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtPhanTRamGiamGiaCuTaiQuay1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel61.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel61.setText("Phần trăm giảm giá:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel53)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtThanhTienCuTaiQuay1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel44)
                                    .addComponent(jLabel52))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaHoaDonCuTaiQuay1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNhanVienCuTaiQuay1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtKhachHangCuTaiQuay1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgayTaoCuTaiQuay1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgayThanhToanCuTaiQuay1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTrangThaiCuTaiQuay1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel54)
                                    .addComponent(jLabel55)
                                    .addComponent(jLabel57)
                                    .addComponent(jLabel59)
                                    .addComponent(jLabel60))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHTThanhToanCuTaiQuay1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaGiaoDichCuTaiQuay1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTienThuaTaiQuayCu1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHTGiaoHangCuTaiQuay1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTienKhachCkCuTaiQuay1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTienKhachDuaCuTaiQuay1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel61)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPhanTRamGiamGiaCuTaiQuay1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(71, 71, 71))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(jLabel49)
                            .addComponent(jLabel50)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addGap(253, 253, 253)
                                .addComponent(jLabel56))
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblThanhTienBangChuCuTaiQuay1)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(jLabel38)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaHoaDonCuTaiQuay1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPhanTRamGiamGiaCuTaiQuay1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNhanVienCuTaiQuay1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTienKhachDuaCuTaiQuay1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtKhachHangCuTaiQuay1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienKhachCkCuTaiQuay1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNgayTaoCuTaiQuay1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaGiaoDichCuTaiQuay1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNgayThanhToanCuTaiQuay1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienThuaTaiQuayCu1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTrangThaiCuTaiQuay1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtHTThanhToanCuTaiQuay1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60)
                            .addComponent(jLabel53))
                        .addComponent(txtHTGiaoHangCuTaiQuay1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtThanhTienCuTaiQuay1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(lblThanhTienBangChuCuTaiQuay1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout HoaDonTaiQuayMoiLayout = new javax.swing.GroupLayout(HoaDonTaiQuayMoi.getContentPane());
        HoaDonTaiQuayMoi.getContentPane().setLayout(HoaDonTaiQuayMoiLayout);
        HoaDonTaiQuayMoiLayout.setHorizontalGroup(
            HoaDonTaiQuayMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        HoaDonTaiQuayMoiLayout.setVerticalGroup(
            HoaDonTaiQuayMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1292, 784));
        setMinimumSize(new java.awt.Dimension(1292, 784));
        setPreferredSize(new java.awt.Dimension(1292, 784));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm trả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 15))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã CTSP", "Tên SP", "Hãng", "Màu sắc", "Kích thước", "Số lượng", "Tiền hoàn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setRowHeight(25);
        tblSanPham.setSelectionBackground(new java.awt.Color(86, 154, 222));
        tblSanPham.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblSanPham);

        btnClear.setBackground(new java.awt.Color(153, 204, 255));
        btnClear.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 15))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Mã HĐ:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Tên KH:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel4.setText("SĐT:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("Địa chỉ:");

        txtMaHDNgoai.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtMaHDNgoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtMaHDNgoai.setMaximumSize(new java.awt.Dimension(7, 30));
        txtMaHDNgoai.setMinimumSize(new java.awt.Dimension(7, 30));
        txtMaHDNgoai.setPreferredSize(new java.awt.Dimension(7, 30));

        txtTenKHNgoai.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTenKHNgoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTenKHNgoai.setMaximumSize(new java.awt.Dimension(7, 30));
        txtTenKHNgoai.setMinimumSize(new java.awt.Dimension(7, 30));
        txtTenKHNgoai.setPreferredSize(new java.awt.Dimension(7, 30));

        txtSDTNgoai.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtSDTNgoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtSDTNgoai.setMaximumSize(new java.awt.Dimension(7, 30));
        txtSDTNgoai.setMinimumSize(new java.awt.Dimension(7, 30));
        txtSDTNgoai.setPreferredSize(new java.awt.Dimension(7, 30));

        txtDiaChiNgoai.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtDiaChiNgoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtDiaChiNgoai.setMaximumSize(new java.awt.Dimension(7, 30));
        txtDiaChiNgoai.setMinimumSize(new java.awt.Dimension(7, 30));
        txtDiaChiNgoai.setPreferredSize(new java.awt.Dimension(7, 30));

        txtThanhTienNgoai.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtThanhTienNgoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtThanhTienNgoai.setMaximumSize(new java.awt.Dimension(7, 30));
        txtThanhTienNgoai.setMinimumSize(new java.awt.Dimension(7, 30));
        txtThanhTienNgoai.setPreferredSize(new java.awt.Dimension(7, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setText("Thành tiền:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtThanhTienNgoai, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChiNgoai, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDTNgoai, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKHNgoai, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaHDNgoai, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(txtMaHDNgoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(36, 36, 36)
                                .addComponent(jLabel3))
                            .addComponent(txtTenKHNgoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(jLabel4))
                    .addComponent(txtSDTNgoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(txtDiaChiNgoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtThanhTienNgoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn trả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 15))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel8.setText("Tiền trả khách:");

        txtTienTraKhach.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTienTraKhach.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTienTraKhach.setMaximumSize(new java.awt.Dimension(7, 30));
        txtTienTraKhach.setMinimumSize(new java.awt.Dimension(7, 30));
        txtTienTraKhach.setPreferredSize(new java.awt.Dimension(7, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel9.setText("Ghi chú:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel10.setText("Tên KH:");

        txtTenKhTra.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTenKhTra.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTenKhTra.setMaximumSize(new java.awt.Dimension(7, 30));
        txtTenKhTra.setMinimumSize(new java.awt.Dimension(7, 30));
        txtTenKhTra.setPreferredSize(new java.awt.Dimension(7, 30));

        txtGhiChu.setColumns(20);
        txtGhiChu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtGhiChu.setRows(5);
        jScrollPane4.setViewportView(txtGhiChu);

        btnTraHang.setBackground(new java.awt.Color(153, 204, 255));
        btnTraHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnTraHang.setText("Trả hàng");
        btnTraHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenKhTra, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTienTraKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btnTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKhTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTienTraKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(btnTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 15))); // NOI18N

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HĐ", "Nhân viên", "Ngày thanh toán", "Khách hàng", "Tiền đã thanh toán", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.setRowHeight(25);
        tblHoaDon.setSelectionBackground(new java.awt.Color(86, 154, 222));
        tblHoaDon.getTableHeader().setReorderingAllowed(false);
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new ImageIcon("src/main/images/search.png"));

        btnXemChiTiet.setBackground(new java.awt.Color(153, 204, 255));
        btnXemChiTiet.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnXemChiTiet.setText("Xem chi tiết");
        btnXemChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemChiTietActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnXemChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addGap(4, 4, 4)
                .addComponent(btnXemChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Danh sách hóa đơn trong thời hạn trả hàng", jPanel2);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblHoaDonTraHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Nhân viên", "Ngày trả hàng", "Khách hàng", "Tiền hoàn trả", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonTraHang.setRowHeight(25);
        tblHoaDonTraHang.setSelectionBackground(new java.awt.Color(86, 154, 222));
        tblHoaDonTraHang.getTableHeader().setReorderingAllowed(false);
        tblHoaDonTraHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonTraHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDonTraHang);
        if (tblHoaDonTraHang.getColumnModel().getColumnCount() > 0) {
            tblHoaDonTraHang.getColumnModel().getColumn(0).setPreferredWidth(7);
        }

        jTextField7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel7.setIcon(new ImageIcon("src/main/images/search.png"));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lịch sử hóa đơn trả hàng", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed

    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        listHoaDon = traHangService.findHoaDon(txtTimKiem.getText().trim());
        loadTableHoaDon(listHoaDon);
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void btnXemChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTietActionPerformed
        int row = tblHoaDon.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Mời chọn hóa đơn");
            return;
        }
        ThHoaDonResponse thHoaDonResponse = listHoaDon.get(row);
        txtMaHoaDon.setText(thHoaDonResponse.getMaHD());
        txtKhachHang.setText(thHoaDonResponse.getTenKhachHang());
        DecimalFormat df = new DecimalFormat("#,###");
        BigDecimal a = null;
        BigDecimal b = null;
        a = thHoaDonResponse.getThanhTien();
        if (thHoaDonResponse.getTienShip() != null) {
            b = thHoaDonResponse.getTienShip();
        } else {
            b = new BigDecimal(0);
        }
        BigDecimal bigDecimal = a.subtract(b);
        txtTienDaThanhToan.setText(df.format(bigDecimal) + " Vnđ");
        txtNgayThanhToan.setText(thHoaDonResponse.getNgayThanhToan() + "");
        txtNhanVien.setText(thHoaDonResponse.getTenNhanVien());
        txtPhanTramGiam.setText(thHoaDonResponse.getPhanTramGiamGia() + " %");
        if (thHoaDonResponse.getCapBac() == 0) {
            txtCapBac.setText("Đồng");
        } else if (thHoaDonResponse.getCapBac() == 1) {
            txtCapBac.setText("Bạc");
        } else if (thHoaDonResponse.getCapBac() == 2) {
            txtCapBac.setText("Vàng");
        } else if (thHoaDonResponse.getCapBac() == 3) {
            txtCapBac.setText("Kim Cương");
        }
        listHoaDonChiTiet = banHangService.getAllHDCTByIdHoaDon(thHoaDonResponse.getId());
        loadTableHoaDonChiTiet(listHoaDonChiTiet);
        ViewHoaDonChiTiet.setVisible(true);
        ViewHoaDonChiTiet.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnXemChiTietActionPerformed

    private void tblHoaDonChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChiTietMouseClicked
        try {
            int rowHoaDon = tblHoaDon.getSelectedRow();
            int row = tblHoaDonChiTiet.getSelectedRow();
            BhHoaDonChiTietResponse bhHoaDonChiTietResponse = listHoaDonChiTiet.get(row);
            ThHoaDonResponse thHoaDonResponse = listHoaDon.get(rowHoaDon);
            if (bhHoaDonChiTietResponse.getGiamGia().compareTo(BigDecimal.ZERO) > 0) {
                JOptionPane.showMessageDialog(this, "Sản phẩm này đã có khuyến mại, không thể trả");
                return;
            }

            ThHoaDonChiTietResponse bhHoaDonChiTietCheck = listSanPhamTra.get(bhHoaDonChiTietResponse.getIdChiTietSP());
            String soLuongTraStr = JOptionPane.showInputDialog(this, "Mời nhập số lượng muốn trả");
            if (soLuongTraStr == null) {
                return;
            }
            int soLuongTra;
            try {
                soLuongTra = Integer.parseInt(soLuongTraStr);
                if (soLuongTra > bhHoaDonChiTietResponse.getSoLuong()) {
                    JOptionPane.showMessageDialog(this, "Số lượng trả không được vượt số lượng");
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số");
                return;
            }
            if (soLuongTra == 0) {
                return;
            }
            if (bhHoaDonChiTietCheck == null) {
                ThHoaDonChiTietResponse thdctr = new ThHoaDonChiTietResponse();
                thdctr.setChatLieu(bhHoaDonChiTietResponse.getChatLieu());
                thdctr.setDonGia(bhHoaDonChiTietResponse.getDonGia());
                BigDecimal giaBan = bhHoaDonChiTietResponse.getGiaBan().subtract(bhHoaDonChiTietResponse.getGiaBan().multiply(new BigDecimal(thHoaDonResponse.getPhanTramGiamGia()).divide(new BigDecimal(100))));
                thdctr.setGiaBan(giaBan);
                thdctr.setGiamGia(bhHoaDonChiTietResponse.getGiamGia());
                thdctr.setHang(bhHoaDonChiTietResponse.getHang());
                thdctr.setIdChiTietSP(bhHoaDonChiTietResponse.getIdChiTietSP());
                thdctr.setIdHDCT(bhHoaDonChiTietResponse.getIdHDCT());
                thdctr.setIdHoaDon(bhHoaDonChiTietResponse.getIdHoaDon());
                thdctr.setMaCTSP(bhHoaDonChiTietResponse.getMaCTSP());
                thdctr.setMaSP(bhHoaDonChiTietResponse.getMaSP());
                thdctr.setMauSac(bhHoaDonChiTietResponse.getMauSac());
                thdctr.setSize(bhHoaDonChiTietResponse.getSize());
                thdctr.setSoLuong(soLuongTra);
                thdctr.setSoLuongTon(bhHoaDonChiTietResponse.getSoLuongTon());
                thdctr.setSoLuongTruocKhiTra(bhHoaDonChiTietResponse.getSoLuong());
                thdctr.setTenSP(bhHoaDonChiTietResponse.getTenSP());
                listSanPhamTra.put(thdctr.getIdChiTietSP(), thdctr);
            } else {
                bhHoaDonChiTietCheck.setSoLuong(soLuongTra);
                listSanPhamTra.replace(bhHoaDonChiTietCheck.getIdChiTietSP(), bhHoaDonChiTietCheck);
            }
            loadTableSanPham(listSanPhamTra);
            tinhTienTraKhach();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblHoaDonChiTietMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        listSanPhamTra.clear();
        loadTableSanPham(listSanPhamTra);
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnTraHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraHangActionPerformed
        try {
            int rowHoaDon = tblHoaDon.getSelectedRow();
            if (rowHoaDon == -1) {
                return;
            }
            if (txtGhiChu.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Hãy nhập lí do khách trả hàng vào phần ghi chú");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn trả hàng không?");
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            ThHoaDonResponse thHoaDonResponse = listHoaDon.get(rowHoaDon);
            HoaDon hoaDonCu = banHangService.findByIdHoaDon(thHoaDonResponse.getId());
            Integer trangThaiCu = hoaDonCu.getTrangThai();
            hoaDonCu.setTrangThai(7);
            banHangService.saveOrUpdate(hoaDonCu);

            HoaDon hoaDonMoi = new HoaDon();
            LocalDateTime time = LocalDateTime.now();
            String maHd = "HD" + String.valueOf(time.getYear()).substring(2) + time.getMonthValue()
                    + time.getDayOfMonth() + time.getHour() + time.getMinute() + time.getSecond();
            hoaDonMoi.setMa(maHd);
            hoaDonMoi.setKhachHang(hoaDonCu.getKhachHang());
            hoaDonMoi.setDiaChi(hoaDonCu.getDiaChi());
            hoaDonMoi.setHinhThucGiaoHang(hoaDonCu.getHinhThucGiaoHang());
            hoaDonMoi.setHinhThucThanhToan(hoaDonCu.getHinhThucThanhToan());
            hoaDonMoi.setLyDo(hoaDonCu.getLyDo());
            hoaDonMoi.setMaGiaoDich(hoaDonCu.getMaGiaoDich());
            hoaDonMoi.setNgayMongMuon(hoaDonCu.getNgayMongMuon());
            hoaDonMoi.setNgayNhan(hoaDonCu.getNgayNhan());
            hoaDonMoi.setNgayShip(hoaDonCu.getNgayShip());
            hoaDonMoi.setNgayTao(new Date());
            hoaDonMoi.setNgayThanhToan(hoaDonCu.getNgayThanhToan());
            hoaDonMoi.setNhanVien(nhanVien);
            hoaDonMoi.setPhamTramGiamGia(hoaDonCu.getPhamTramGiamGia());
            hoaDonMoi.setSdtNguoiNhan(hoaDonCu.getSdtNguoiNhan());
            hoaDonMoi.setSdtNguoiShip(hoaDonCu.getSdtNguoiShip());
            hoaDonMoi.setTenNguoiNhan(hoaDonCu.getTenNguoiNhan());
            hoaDonMoi.setTenNguoiShip(hoaDonCu.getTenNguoiShip());
            String array[] = txtTienTraKhach.getText().trim().split(" ");
            hoaDonMoi.setThanhTien(hoaDonCu.getThanhTien().subtract(new BigDecimal(array[0].replace(",", ""))));
            hoaDonMoi.setTienKhachChuyenKhoan(hoaDonCu.getTienKhachChuyenKhoan());
            hoaDonMoi.setTienKhachTra(hoaDonCu.getTienKhachTra());
            hoaDonMoi.setTienShip(hoaDonCu.getTienShip());
            hoaDonMoi.setTienThua(hoaDonCu.getTienThua());
            hoaDonMoi.setTrangThai(trangThaiCu);
            hoaDonMoi.setTrangThaiThanhToan(hoaDonCu.getTrangThaiThanhToan());
            banHangService.saveOrUpdate(hoaDonMoi);

            HoaDonTraHang hoaDonTraHang = new HoaDonTraHang();
            hoaDonTraHang.setHoaDonCu(hoaDonCu);
            hoaDonTraHang.setHoaDonMoi(hoaDonMoi);
            hoaDonTraHang.setNgayDoiTra(new Date());
            String tienTraKhachStr[] = txtTienTraKhach.getText().trim().split(" ");
            hoaDonTraHang.setTienHoanTraKhach(new BigDecimal(tienTraKhachStr[0].replace(",", "")));
            hoaDonTraHang.setGhiChu(txtGhiChu.getText().trim());
            traHangService.saveOrUpdateHoaDonTraHang(hoaDonTraHang);

            for (Map.Entry<String, ThHoaDonChiTietResponse> entry : listSanPhamTra.entrySet()) {
                ThHoaDonChiTietResponse value = entry.getValue();
                HoaDonTraHangChiTiet hoaDonTraHangChiTiet = new HoaDonTraHangChiTiet();
                hoaDonTraHangChiTiet.setMaChiTietSanPham(value.getMaCTSP());
                hoaDonTraHangChiTiet.setGiaBan(value.getGiaBan());
                hoaDonTraHangChiTiet.setHoaDonDoiTra(hoaDonTraHang);
                hoaDonTraHangChiTiet.setKichThuoc(value.getSize());
                hoaDonTraHangChiTiet.setSoLuongTra(value.getSoLuong());
                hoaDonTraHangChiTiet.setMauSac(value.getMauSac());
                hoaDonTraHangChiTiet.setTenHang(value.getHang());
                hoaDonTraHangChiTiet.setTenSP(value.getTenSP());
                banHangService.updateSoLuong(value.getIdChiTietSP(), -value.getSoLuong());
                traHangService.saveOrUpdateHoaDonTraHangChiTiet(hoaDonTraHangChiTiet);
                if (value.getSoLuongTruocKhiTra() == value.getSoLuong()) {
                    continue;
                }
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setHoaDonId(hoaDonMoi);

                ChiTietSP chiTietSP = new ChiTietSP();
                chiTietSP.setId(value.getIdChiTietSP());
                hoaDonChiTiet.setChiTietSPId(chiTietSP);
                hoaDonChiTiet.setDonGia(value.getDonGia());
                hoaDonChiTiet.setGiaBan(value.getDonGia());
                hoaDonChiTiet.setGiamGiaKhuyenMai(value.getGiamGia());
                hoaDonChiTiet.setSoLuong(value.getSoLuongTruocKhiTra() - value.getSoLuong());

                banHangService.saveOrUpdateHDCT(hoaDonChiTiet);
            }

            for (BhHoaDonChiTietResponse xx : listHoaDonChiTiet) {
                if (listSanPhamTra.get(xx.getIdChiTietSP()) == null) {

                    HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                    hoaDonChiTiet.setHoaDonId(hoaDonMoi);

                    ChiTietSP chiTietSP = new ChiTietSP();
                    chiTietSP.setId(xx.getIdChiTietSP());
                    hoaDonChiTiet.setChiTietSPId(chiTietSP);
                    hoaDonChiTiet.setDonGia(xx.getDonGia());
                    hoaDonChiTiet.setGiaBan(xx.getGiaBan());
                    hoaDonChiTiet.setGiamGiaKhuyenMai(xx.getGiamGia());
                    hoaDonChiTiet.setSoLuong(xx.getSoLuong());

                    banHangService.saveOrUpdateHDCT(hoaDonChiTiet);
                }
            }
            listHoaDon = traHangService.getAllResponseHD();
            loadTableHoaDon(listHoaDon);
            JOptionPane.showMessageDialog(this, "Trả hàng thành công");
            clearForm();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTraHangActionPerformed

    private void clearForm() {
        listSanPhamTra.clear();
        loadTableSanPham(listSanPhamTra);
        txtDiaChiNgoai.setText("");
        txtMaHDNgoai.setText("");
        txtTenKHNgoai.setText("");
        txtSDTNgoai.setText("");
        txtThanhTienNgoai.setText("");
        txtTienTraKhach.setText("");
        txtTenKhTra.setText("");
        txtGhiChu.setText("");
    }

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        try {
            int row = tblHoaDon.getSelectedRow();
            ThHoaDonResponse thHoaDonResponse = listHoaDon.get(row);
            txtMaHDNgoai.setText(thHoaDonResponse.getMaHD());
            txtTenKHNgoai.setText(thHoaDonResponse.getTenKhachHang());
            txtThanhTienNgoai.setText(thHoaDonResponse.getThanhTien() + "");
            txtSDTNgoai.setText(thHoaDonResponse.getSdtKhachHang());
            txtDiaChiNgoai.setText(thHoaDonResponse.getDiaChiKhachHang());
            txtTenKhTra.setText(thHoaDonResponse.getTenKhachHang());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void tblHoaDonTraHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonTraHangMouseClicked
        try {
//            clearForm();
            ViewHoaDonTraHangChiTiet.setVisible(true);
            ViewHoaDonTraHangChiTiet.setLocationRelativeTo(null);
            int row = tblHoaDonTraHang.getSelectedRow();
            HoaDonTraHang hoaDonTraHang = new HoaDonTraHang();
            if(row >= 0){
                hoaDonTraHang = listHoaDonTraHang.get(row);
            }
            txtMaHoaDonCu.setText(hoaDonTraHang.getHoaDonCu().getMa());
            txtMaHoaDonMoi.setText(hoaDonTraHang.getHoaDonMoi().getMa());
            txtTenKhachHangTraHang.setText(hoaDonTraHang.getHoaDonCu().getKhachHang().getHoTen());
            txtSdtKhachHangTraHang.setText(hoaDonTraHang.getHoaDonCu().getKhachHang().getSdt());
            txtTenNhanVienTraHang.setText(hoaDonTraHang.getHoaDonMoi().getNhanVien().getTen());
            txtSdtNhanVienTraHang.setText(hoaDonTraHang.getHoaDonMoi().getNhanVien().getSdt());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
            txtNgayTraHang.setText(simpleDateFormat.format(hoaDonTraHang.getNgayDoiTra()));
            DecimalFormat df = new DecimalFormat("#,###");
            txtTongTienHoanTra.setText(df.format(hoaDonTraHang.getTienHoanTraKhach()) + " Vnđ");
            lblTienTraKhachBangChu.setText(ConvertMoneyToString.convertMoneyToText(String.valueOf(hoaDonTraHang.getTienHoanTraKhach())));
            txtGhiChuTraHang.setText(hoaDonTraHang.getGhiChu());
            listHoaDonTraHangChiTiet = traHangService.getAllHoaDonTraHangChiTiet(hoaDonTraHang.getId());
            loadTableHoaDonTraHangChiTiet(listHoaDonTraHangChiTiet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblHoaDonTraHangMouseClicked

    private void loadTableHoaDonTraHangChiTiet(List<HoaDonTraHangChiTiet> list) {
        modelHoaDonTraHangChiTiet.setRowCount(0);
        for (HoaDonTraHangChiTiet xx : list) {
            modelHoaDonTraHangChiTiet.addRow(new Object[]{xx.getMaChiTietSanPham(), xx.getTenSP(), xx.getTenHang(), xx.getMauSac(), xx.getKichThuoc(), xx.getSoLuongTra(), xx.getGiaBan()});
        }
    }
    
    private void loadHoaDonTraHang(List<HoaDonTraHang> list) {
        modelHoaDonTraHang.setRowCount(0);
        int i = 1;
        for (HoaDonTraHang xx : list) {
            modelHoaDonTraHang.addRow(new Object[]{i,xx.getHoaDonMoi().getNhanVien().getTen(), xx.getNgayDoiTra(), xx.getHoaDonCu().getKhachHang().getHoTen(), xx.getTienHoanTraKhach(), xx.getGhiChu()});
            i++;
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnXemChiTietHoaDonCuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTietHoaDonCuActionPerformed
        try {
            DecimalFormat df = new DecimalFormat("#,###");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
            HoaDonTaiQuayCu.setVisible(true);
            HoaDonTaiQuayCu.setLocationRelativeTo(null);
            HoaDon hoaDon = traHangService.getHoaDonByMa(txtMaHoaDonCu.getText().trim());
            txtMaHoaDonCuTaiQuay.setText(hoaDon.getMa());
            txtNhanVienCuTaiQuay.setText(hoaDon.getNhanVien().getMa() + " - " + hoaDon.getNhanVien().getTen());
            txtKhachHangCuTaiQuay.setText(hoaDon.getKhachHang().getHoTen() + " - " + hoaDon.getKhachHang().getSdt());
            txtNgayTaoCuTaiQuay.setText(sdf.format(hoaDon.getNgayTao()));
            txtNgayThanhToanCuTaiQuay.setText(sdf.format(hoaDon.getNgayThanhToan()));
            txtTrangThaiCuTaiQuay.setText(hoaDon.getTrangThai() == 2 ? "Đã thanh toán" : (hoaDon.getTrangThai() == 5 ? "Đã giao" : "Đã trả hàng"));
            txtThanhTienCuTaiQuay.setText(df.format(hoaDon.getThanhTien()) + " Vnđ");
            lblThanhTienBangChuCuTaiQuay.setText(ConvertMoneyToString.convertMoneyToText(String.valueOf(hoaDon.getThanhTien())));
            txtPhanTRamGiamGiaCuTaiQuay.setText(hoaDon.getPhamTramGiamGia() + " %");
            txtTienThuaTaiQuayCu.setText(hoaDon.getTienThua()== null ? "0 Vnđ" : df.format(hoaDon.getTienThua()) + " Vnđ");
            txtTienKhachDuaCuTaiQuay.setText(hoaDon.getTienKhachTra() == null ? "0 Vnđ" : df.format(hoaDon.getTienKhachTra()) + " Vnđ");
            txtTienKhachCkCuTaiQuay.setText(hoaDon.getTienKhachChuyenKhoan()== null ? "0" : df.format(hoaDon.getTienKhachChuyenKhoan()) + " Vnđ");
            txtMaGiaoDichCuTaiQuay.setText(hoaDon.getMaGiaoDich() == null ? "Không có" : hoaDon.getMaGiaoDich());
            txtHTThanhToanCuTaiQuay.setText(hoaDon.getHinhThucThanhToan() == 0 ? "Tiền mặt" : (hoaDon.getHinhThucThanhToan() == 1 ? "Chuyển khoản" : "Kết hợp"));
            txtHTGiaoHangCuTaiQuay.setText(hoaDon.getHinhThucGiaoHang() == 0 ? "Tại quầy" : "Đặt hàng");
            listHoaDonChiTietCuTaiQuay = hoaDonservice.getListHDCT(hoaDon.getMa());
            loadHoaDonChiTiet(listHoaDonChiTietCuTaiQuay);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXemChiTietHoaDonCuActionPerformed

    private void tblHoaDonChiTietCuTaiQuayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChiTietCuTaiQuayMouseClicked

    }//GEN-LAST:event_tblHoaDonChiTietCuTaiQuayMouseClicked

    private void tblHoaDonChiTietCuTaiQuay1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChiTietCuTaiQuay1MouseClicked
        
    }//GEN-LAST:event_tblHoaDonChiTietCuTaiQuay1MouseClicked

    private void btnXemChiTietHoaDonMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTietHoaDonMoiActionPerformed
        try {
            DecimalFormat df = new DecimalFormat("#,###");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
            HoaDonTaiQuayMoi.setVisible(true);
            HoaDonTaiQuayMoi.setLocationRelativeTo(null);
            HoaDon hoaDon = traHangService.getHoaDonByMa(txtMaHoaDonMoi.getText().trim());
            txtMaHoaDonCuTaiQuay1.setText(hoaDon.getMa());
            txtNhanVienCuTaiQuay1.setText(hoaDon.getNhanVien().getMa() + " - " + hoaDon.getNhanVien().getTen());
            txtKhachHangCuTaiQuay1.setText(hoaDon.getKhachHang().getHoTen() + " - " + hoaDon.getKhachHang().getSdt());
            txtNgayTaoCuTaiQuay1.setText(sdf.format(hoaDon.getNgayTao()));
            txtNgayThanhToanCuTaiQuay1.setText(sdf.format(hoaDon.getNgayThanhToan()));
            txtTrangThaiCuTaiQuay1.setText(hoaDon.getTrangThai() == 2 ? "Đã thanh toán" : (hoaDon.getTrangThai() == 5 ? "Đã giao" : "Đã trả hàng"));
            txtThanhTienCuTaiQuay1.setText(df.format(hoaDon.getThanhTien()) + " Vnđ");
            lblThanhTienBangChuCuTaiQuay1.setText(ConvertMoneyToString.convertMoneyToText(String.valueOf(hoaDon.getThanhTien())));
            txtPhanTRamGiamGiaCuTaiQuay1.setText(hoaDon.getPhamTramGiamGia() + " %");
            txtTienThuaTaiQuayCu1.setText(hoaDon.getTienThua()== null ? "0 Vnđ" : df.format(hoaDon.getTienThua()) + " Vnđ");
            txtTienKhachDuaCuTaiQuay1.setText(hoaDon.getTienKhachTra() == null ? "0 Vnđ" : df.format(hoaDon.getTienKhachTra()) + " Vnđ");
            txtTienKhachCkCuTaiQuay1.setText(hoaDon.getTienKhachChuyenKhoan()== null ? "0" : df.format(hoaDon.getTienKhachChuyenKhoan()) + " Vnđ");
            txtMaGiaoDichCuTaiQuay1.setText(hoaDon.getMaGiaoDich() == null ? "Không có" : hoaDon.getMaGiaoDich());
            txtHTThanhToanCuTaiQuay1.setText(hoaDon.getHinhThucThanhToan() == 0 ? "Tiền mặt" : (hoaDon.getHinhThucThanhToan() == 1 ? "Chuyển khoản" : "Kết hợp"));
            txtHTGiaoHangCuTaiQuay1.setText(hoaDon.getHinhThucGiaoHang() == 0 ? "Tại quầy" : "Đặt hàng");
            listHoaDonChiTietCuTaiQuay = hoaDonservice.getListHDCT(hoaDon.getMa());
            loadHoaDonChiTiet1(listHoaDonChiTietCuTaiQuay);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXemChiTietHoaDonMoiActionPerformed

    private void loadTableHoaDonChiTiet(List<BhHoaDonChiTietResponse> list) {
        modelHoaDonChiTiet.setRowCount(0);
        for (BhHoaDonChiTietResponse xx : list) {
            modelHoaDonChiTiet.addRow(new Object[]{xx.getMaCTSP(), xx.getTenSP(), xx.getHang(), xx.getMauSac(), xx.getSize(), xx.getChatLieu(), xx.getSoLuong(), xx.getGiamGia(), xx.getGiaBan()});
        }
    }

    private void tinhTienTraKhach() {
        try {
            BigDecimal tongTienTra = new BigDecimal(0);
            for (Map.Entry<String, ThHoaDonChiTietResponse> entry : listSanPhamTra.entrySet()) {
                ThHoaDonChiTietResponse value = entry.getValue();
                tongTienTra = tongTienTra.add(value.getGiaBan().multiply(new BigDecimal(value.getSoLuong())));
            }
            DecimalFormat df = new DecimalFormat("#,###");
            txtTienTraKhach.setText(df.format(tongTienTra) + " Vnđ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTableSanPham(Map<String, ThHoaDonChiTietResponse> list) {
        modelSanPham.setRowCount(0);
        for (Map.Entry<String, ThHoaDonChiTietResponse> entry : list.entrySet()) {
            ThHoaDonChiTietResponse xx = entry.getValue();
            modelSanPham.addRow(new Object[]{xx.getMaCTSP(), xx.getTenSP(), xx.getHang(), xx.getMauSac(), xx.getSize(), xx.getSoLuong(), xx.getGiaBan()});
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame HoaDonDatHangCu;
    private javax.swing.JFrame HoaDonDatHangMoi;
    private javax.swing.JFrame HoaDonTaiQuayCu;
    private javax.swing.JFrame HoaDonTaiQuayMoi;
    private javax.swing.JFrame ViewHoaDonChiTiet;
    private javax.swing.JFrame ViewHoaDonTraHangChiTiet;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnTraHang;
    private javax.swing.JButton btnXemChiTiet;
    private javax.swing.JButton btnXemChiTietHoaDonCu;
    private javax.swing.JButton btnXemChiTietHoaDonMoi;
    private javax.swing.JButton btnXuatHoaDonTraHang;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
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
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLabel lblThanhTienBangChuCuTaiQuay;
    private javax.swing.JLabel lblThanhTienBangChuCuTaiQuay1;
    private javax.swing.JLabel lblTienTraKhachBangChu;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTable tblHoaDonChiTietCuTaiQuay;
    private javax.swing.JTable tblHoaDonChiTietCuTaiQuay1;
    private javax.swing.JTable tblHoaDonTraHang;
    private javax.swing.JTable tblHoaDonTraHangChiTiet;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtCapBac;
    private javax.swing.JTextField txtDiaChiNgoai;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtGhiChuTraHang;
    private javax.swing.JTextField txtHTGiaoHangCuTaiQuay;
    private javax.swing.JTextField txtHTGiaoHangCuTaiQuay1;
    private javax.swing.JTextField txtHTThanhToanCuTaiQuay;
    private javax.swing.JTextField txtHTThanhToanCuTaiQuay1;
    private javax.swing.JTextField txtKhachHang;
    private javax.swing.JTextField txtKhachHangCuTaiQuay;
    private javax.swing.JTextField txtKhachHangCuTaiQuay1;
    private javax.swing.JTextField txtMaGiaoDichCuTaiQuay;
    private javax.swing.JTextField txtMaGiaoDichCuTaiQuay1;
    private javax.swing.JTextField txtMaHDNgoai;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaHoaDonCu;
    private javax.swing.JTextField txtMaHoaDonCuTaiQuay;
    private javax.swing.JTextField txtMaHoaDonCuTaiQuay1;
    private javax.swing.JTextField txtMaHoaDonMoi;
    private javax.swing.JTextField txtNgayTaoCuTaiQuay;
    private javax.swing.JTextField txtNgayTaoCuTaiQuay1;
    private javax.swing.JTextField txtNgayThanhToan;
    private javax.swing.JTextField txtNgayThanhToanCuTaiQuay;
    private javax.swing.JTextField txtNgayThanhToanCuTaiQuay1;
    private javax.swing.JTextField txtNgayTraHang;
    private javax.swing.JTextField txtNhanVien;
    private javax.swing.JTextField txtNhanVienCuTaiQuay;
    private javax.swing.JTextField txtNhanVienCuTaiQuay1;
    private javax.swing.JTextField txtPhanTRamGiamGiaCuTaiQuay;
    private javax.swing.JTextField txtPhanTRamGiamGiaCuTaiQuay1;
    private javax.swing.JTextField txtPhanTramGiam;
    private javax.swing.JTextField txtSDTNgoai;
    private javax.swing.JTextField txtSdtKhachHangTraHang;
    private javax.swing.JTextField txtSdtNhanVienTraHang;
    private javax.swing.JTextField txtTenKHNgoai;
    private javax.swing.JTextField txtTenKhTra;
    private javax.swing.JTextField txtTenKhachHangTraHang;
    private javax.swing.JTextField txtTenNhanVienTraHang;
    private javax.swing.JTextField txtThanhTienCuTaiQuay;
    private javax.swing.JTextField txtThanhTienCuTaiQuay1;
    private javax.swing.JTextField txtThanhTienNgoai;
    private javax.swing.JTextField txtTienDaThanhToan;
    private javax.swing.JTextField txtTienKhachCkCuTaiQuay;
    private javax.swing.JTextField txtTienKhachCkCuTaiQuay1;
    private javax.swing.JTextField txtTienKhachDuaCuTaiQuay;
    private javax.swing.JTextField txtTienKhachDuaCuTaiQuay1;
    private javax.swing.JTextField txtTienThuaTaiQuayCu;
    private javax.swing.JTextField txtTienThuaTaiQuayCu1;
    private javax.swing.JTextField txtTienTraKhach;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTienHoanTra;
    private javax.swing.JTextField txtTrangThaiCuTaiQuay;
    private javax.swing.JTextField txtTrangThaiCuTaiQuay1;
    // End of variables declaration//GEN-END:variables
}
