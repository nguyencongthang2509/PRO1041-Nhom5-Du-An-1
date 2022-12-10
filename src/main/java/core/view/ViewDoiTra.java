package core.view;

import core.quanly.service.BanHangService;
import core.quanly.service.TraHangService;
import core.quanly.service.impl.BanHangServiceImpl;
import core.quanly.service.impl.TraHangServiceImpl;
import core.quanly.viewmodel.BhHoaDonChiTietResponse;
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
    private TraHangService traHangService;
    private BanHangService banHangService;
    private List<ThHoaDonResponse> listHoaDon;
    private List<ThHoaDonResponse> listHoaDonTraHang;
    private List<HoaDonTraHangChiTiet> listHoaDonTraHangChiTiet;
    private List<BhHoaDonChiTietResponse> listHoaDonChiTiet;
    private Map<String, ThHoaDonChiTietResponse> listSanPhamTra;
    private NhanVien nhanVien;

    public ViewDoiTra(NhanVien nv) {
        initComponents();
        nhanVien = nv;
        modelHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        modelHoaDonTraHang = (DefaultTableModel) tblHoaDonTraHang.getModel();
        modelHoaDonChiTiet = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        modelHoaDonTraHangChiTiet = (DefaultTableModel) tblHoaDonTraHangChiTiet.getModel();
        modelSanPham = (DefaultTableModel) tblSanPham.getModel();
        traHangService = new TraHangServiceImpl();
        banHangService = new BanHangServiceImpl();
        listHoaDon = traHangService.getAllResponseHD();
        listHoaDonTraHangChiTiet = new ArrayList<>();
        listSanPhamTra = new HashMap<>();
        loadTableHoaDon(listHoaDon);
        listHoaDonTraHang = traHangService.getAllResponseHDTraHang();
        loadTableHoaDonTraHang(listHoaDonTraHang);
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
            modelHoaDonTraHang.addRow(xx.toDataRow());
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
        jLabel18 = new javax.swing.JLabel();
        txtMaHDTraHang = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtNgayTraHang = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtNhanVienTraHang = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtTienTraKhachTraHang = new javax.swing.JTextField();
        txtKhachHangTraHang = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtGhiChuTraHang = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblHoaDonTraHangChiTiet = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
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
        txtMaHoaDon.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtNgayThanhToan.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNgayThanhToan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtNhanVien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNhanVien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtKhachHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtKhachHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtTienDaThanhToan.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTienDaThanhToan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

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
        txtPhanTramGiam.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel17.setText("Cấp bậc khách hàng:");

        txtCapBac.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCapBac.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

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
        ViewHoaDonTraHangChiTiet.setMaximumSize(new java.awt.Dimension(790, 433));
        ViewHoaDonTraHangChiTiet.setMinimumSize(new java.awt.Dimension(790, 433));
        ViewHoaDonTraHangChiTiet.setResizable(false);
        ViewHoaDonTraHangChiTiet.setSize(new java.awt.Dimension(790, 433));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel18.setText("Mã hóa đơn:");

        txtMaHDTraHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtMaHDTraHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel19.setText("Ngày trả hàng:");

        txtNgayTraHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNgayTraHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel20.setText("Nhân viên trả hàng:");

        txtNhanVienTraHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNhanVienTraHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel21.setText("Tiền trả lại khách:");

        txtTienTraKhachTraHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTienTraKhachTraHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtKhachHangTraHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtKhachHangTraHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtKhachHangTraHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKhachHangTraHangActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel22.setText("Khách hàng:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel23.setText("Ghi chú:");

        txtGhiChuTraHang.setColumns(20);
        txtGhiChuTraHang.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtGhiChuTraHang.setRows(5);
        jScrollPane6.setViewportView(txtGhiChuTraHang);

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

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel24.setText("Danh sách sản phẩm đã trả:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18)
                            .addComponent(jLabel20)
                            .addComponent(jLabel22))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaHDTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNhanVienTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKhachHangTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(txtTienTraKhachTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMaHDTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTienTraKhachTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKhachHangTraHang))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNhanVienTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel9Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtKhachHangTraHang, txtMaHDTraHang, txtNgayTraHang, txtNhanVienTraHang, txtTienTraKhachTraHang});

        javax.swing.GroupLayout ViewHoaDonTraHangChiTietLayout = new javax.swing.GroupLayout(ViewHoaDonTraHangChiTiet.getContentPane());
        ViewHoaDonTraHangChiTiet.getContentPane().setLayout(ViewHoaDonTraHangChiTietLayout);
        ViewHoaDonTraHangChiTietLayout.setHorizontalGroup(
            ViewHoaDonTraHangChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ViewHoaDonTraHangChiTietLayout.setVerticalGroup(
            ViewHoaDonTraHangChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap(37, Short.MAX_VALUE))
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
                        .addGap(125, 125, 125))))
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
                "Mã HĐ", "Nhân viên", "Ngày thanh toán", "Khách hàng", "Tiền hoàn trả", "Trang thái"
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

        jTextField7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jTabbedPane1.addTab("Lịch sử các hóa đơn trả hàng", jPanel6);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
            hoaDonTraHang.setHoaDon(hoaDonMoi);
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
                banHangService.updateSoLuong(value.getIdChiTietSP(), value.getSoLuong());
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
            listHoaDonTraHang = traHangService.getAllResponseHDTraHang();
            loadTableHoaDonTraHang(listHoaDonTraHang);
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
            ViewHoaDonTraHangChiTiet.setVisible(true);
            ViewHoaDonTraHangChiTiet.setLocationRelativeTo(null);
            int row = tblHoaDonTraHang.getSelectedRow();
            ThHoaDonResponse thHoaDonResponse = listHoaDonTraHang.get(row);
            HoaDonTraHang hoaDonTraHang = traHangService.findHoaDonTraHang(thHoaDonResponse.getId().trim());
            txtMaHDTraHang.setText(thHoaDonResponse.getMaHD());
            txtNhanVienTraHang.setText(thHoaDonResponse.getTenNhanVien());
            txtNgayTraHang.setText(hoaDonTraHang.getNgayDoiTra() + "");
            txtKhachHangTraHang.setText(thHoaDonResponse.getTenKhachHang());
            DecimalFormat df = new DecimalFormat("#,###");
            txtTienTraKhachTraHang.setText(df.format(hoaDonTraHang.getTienHoanTraKhach()) + " Vnđ");
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

    private void txtKhachHangTraHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKhachHangTraHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKhachHangTraHangActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
    }//GEN-LAST:event_jButton3ActionPerformed

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
    private javax.swing.JFrame ViewHoaDonChiTiet;
    private javax.swing.JFrame ViewHoaDonTraHangChiTiet;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnTraHang;
    private javax.swing.JButton btnXemChiTiet;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTable tblHoaDonTraHang;
    private javax.swing.JTable tblHoaDonTraHangChiTiet;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtCapBac;
    private javax.swing.JTextField txtDiaChiNgoai;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextArea txtGhiChuTraHang;
    private javax.swing.JTextField txtKhachHang;
    private javax.swing.JTextField txtKhachHangTraHang;
    private javax.swing.JTextField txtMaHDNgoai;
    private javax.swing.JTextField txtMaHDTraHang;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtNgayThanhToan;
    private javax.swing.JTextField txtNgayTraHang;
    private javax.swing.JTextField txtNhanVien;
    private javax.swing.JTextField txtNhanVienTraHang;
    private javax.swing.JTextField txtPhanTramGiam;
    private javax.swing.JTextField txtSDTNgoai;
    private javax.swing.JTextField txtTenKHNgoai;
    private javax.swing.JTextField txtTenKhTra;
    private javax.swing.JTextField txtThanhTienNgoai;
    private javax.swing.JTextField txtTienDaThanhToan;
    private javax.swing.JTextField txtTienTraKhach;
    private javax.swing.JTextField txtTienTraKhachTraHang;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
