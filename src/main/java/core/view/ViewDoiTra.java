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
import java.io.File;
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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.math3.util.BigReal;
import util.ConvertMoneyToString;
import util.ExportPhieuTraHang;

/**
 *
 * @author thangncph26123
 */
public class ViewDoiTra extends javax.swing.JPanel {

    private DefaultTableModel modelHoaDon = new DefaultTableModel();
    private DefaultTableModel modelHoaDonChiTiet = new DefaultTableModel();
    private DefaultTableModel modelSanPham = new DefaultTableModel();
    private DefaultTableModel modelHoaDonTraHang = new DefaultTableModel();
    private DefaultTableModel modelHDTraHang = new DefaultTableModel();
    private DefaultTableModel modelHDTraHangCT = new DefaultTableModel();
    private DefaultTableModel modelHDCT = new DefaultTableModel();
    private TraHangService traHangService;
    private BanHangService banHangService;
    private List<ThHoaDonResponse> listHoaDon;
    private List<HoaDon> listHoaDonTraHang;
    private List<HoaDonTraHang> listHDTraHang;
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
        modelHDTraHang = (DefaultTableModel) tblHDTraHang.getModel();
        modelHDTraHangCT = (DefaultTableModel) tblHDTHCT.getModel();
        modelSanPham = (DefaultTableModel) tblSanPham.getModel();
        modelHDCT = (DefaultTableModel) tblHDCT.getModel();
        traHangService = new TraHangServiceImpl();
        hoaDonservice = new HoaDonserviceImpl();
        traHangService = new TraHangServiceImpl();
        banHangService = new BanHangServiceImpl();
        listHoaDon = traHangService.getAllResponseHD();
        listHoaDonTraHang = traHangService.getAllHoaDonTraHang();
        loadTableHoaDonTraHang(listHoaDonTraHang);
        listHoaDonTraHangChiTiet = new ArrayList<>();
        listHDTraHang = new ArrayList<>();
        listSanPhamTra = new HashMap<>();
        loadTableHoaDon(listHoaDon);
    }

    private void loadTableHoaDon(List<ThHoaDonResponse> list) {
        modelHoaDon.setRowCount(0);
        for (ThHoaDonResponse xx : list) {
            modelHoaDon.addRow(xx.toDataRow());
        }
    }

    private void loadTableHDTraHang(List<HoaDonTraHang> list) {
        modelHDTraHang.setRowCount(0);
        int i = 1;
        for (HoaDonTraHang xx : list) {
            modelHDTraHang.addRow(new Object[]{i, xx.getNhanVien().getTen(), xx.getNgayDoiTra(), xx.getTienHoanTraKhach(), xx.getGhiChu()});
            i++;
        }
    }

    private void loadTableHoaDonTraHang(List<HoaDon> list) {
        modelHoaDonTraHang.setRowCount(0);
        int i = 1;
        for (HoaDon xx : list) {
            modelHoaDonTraHang.addRow(new Object[]{i, xx.getMa(), xx.getNhanVien().getTen(), xx.getKhachHang().getHoTen(), xx.getNgayThanhToan(), xx.getTrangThai() == 2 ? "Đã thanh toán" : "Đã giao"});
            i++;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DiaLogHoaDonTraHangChiTiet = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblHDTraHang = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        txttenKH = new javax.swing.JTextField();
        txtSDTKH = new javax.swing.JTextField();
        txtNhanVienBan = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        btnXuatHoaDonTraHang = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lblSoLanTraHang = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtHinhThucGH = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtTenNgNhan = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        txtTenNgShip = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtSDTNgShip = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblHDCT = new javax.swing.JTable();
        lblTraHetHang = new javax.swing.JLabel();
        DiaLogViewHoaDonChiTiet = new javax.swing.JDialog();
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
        DiaLogHdTraHangChiTiet = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblHDTHCT = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtNVTH = new javax.swing.JTextField();
        txtNTH = new javax.swing.JTextField();
        txtTTHT = new javax.swing.JTextField();
        txtGCHT = new javax.swing.JTextField();
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

        DiaLogHoaDonTraHangChiTiet.setTitle("Hóa đơn trả hàng");
        DiaLogHoaDonTraHangChiTiet.setMinimumSize(new java.awt.Dimension(717, 743));
        DiaLogHoaDonTraHangChiTiet.setSize(new java.awt.Dimension(717, 743));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setMaximumSize(new java.awt.Dimension(717, 567));
        jPanel9.setMinimumSize(new java.awt.Dimension(717, 567));
        jPanel9.setPreferredSize(new java.awt.Dimension(717, 743));

        tblHDTraHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Nhân viên trả hàng", "Ngày trả", "Số tiền hoàn trả", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHDTraHang.setRowHeight(25);
        tblHDTraHang.setSelectionBackground(new java.awt.Color(86, 154, 222));
        tblHDTraHang.getTableHeader().setReorderingAllowed(false);
        tblHDTraHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDTraHangMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblHDTraHang);
        if (tblHDTraHang.getColumnModel().getColumnCount() > 0) {
            tblHDTraHang.getColumnModel().getColumn(0).setPreferredWidth(45);
        }

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel19.setText("Mã hóa đơn:");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel21.setText("Khách hàng:");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel22.setText("SĐT khách hàng:");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel23.setText("Nhân viên:");

        txtMaHD.setEditable(false);
        txtMaHD.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtMaHD.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txttenKH.setEditable(false);
        txttenKH.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txttenKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtSDTKH.setEditable(false);
        txtSDTKH.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtSDTKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtNhanVienBan.setEditable(false);
        txtNhanVienBan.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtNhanVienBan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel25.setText("Lịch sử trả hàng:");

        btnXuatHoaDonTraHang.setBackground(new java.awt.Color(153, 204, 255));
        btnXuatHoaDonTraHang.setText("In phiếu trả hàng");
        btnXuatHoaDonTraHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatHoaDonTraHangActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel24.setText("Thành tiền:");

        txtThanhTien.setEditable(false);
        txtThanhTien.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtThanhTien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel26.setText("Bằng chữ:");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel27.setText("---");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel28.setText("Số lần trả hàng:");

        lblSoLanTraHang.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        lblSoLanTraHang.setText("---");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel20.setText("Hình thức:");

        txtHinhThucGH.setEditable(false);
        txtHinhThucGH.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtHinhThucGH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel29.setText("Tên ng/nhận:");

        txtTenNgNhan.setEditable(false);
        txtTenNgNhan.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTenNgNhan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel30.setText("Địa chỉ:");

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel31.setText("Tên ng/ship:");

        txtDiaChi.setEditable(false);
        txtDiaChi.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtTenNgShip.setEditable(false);
        txtTenNgShip.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTenNgShip.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel32.setText("SĐT ng/ship:");

        txtSDTNgShip.setEditable(false);
        txtSDTNgShip.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtSDTNgShip.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jButton1.setBackground(new java.awt.Color(153, 204, 255));
        jButton1.setText("Xem chi tiết");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tblHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã CTSP", "Tên SP", "Hãng", "Màu sắc", "Kích thước", "Chất liệu", "SL", "Giảm giá", "Giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHDCT.setRowHeight(25);
        tblHDCT.setSelectionBackground(new java.awt.Color(86, 154, 222));
        tblHDCT.getTableHeader().setReorderingAllowed(false);
        tblHDCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDCTMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblHDCT);
        if (tblHDCT.getColumnModel().getColumnCount() > 0) {
            tblHDCT.getColumnModel().getColumn(0).setPreferredWidth(45);
            tblHDCT.getColumnModel().getColumn(6).setPreferredWidth(11);
        }

        lblTraHetHang.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        lblTraHetHang.setForeground(new java.awt.Color(255, 0, 51));
        lblTraHetHang.setText("jLabel37");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(jLabel26)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNhanVienBan, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addComponent(jLabel20)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                    .addComponent(txtHinhThucGH, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel29)
                                        .addComponent(jLabel30))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtTenNgShip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtTenNgNhan, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtSDTNgShip)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXuatHoaDonTraHang))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(lblSoLanTraHang)
                                .addGap(26, 26, 26)
                                .addComponent(lblTraHetHang))
                            .addComponent(jLabel27)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(txtMaHD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(txtHinhThucGH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txttenKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING))
                                                    .addComponent(txtTenNgNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(txtSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel22))
                                                .addGap(16, 16, 16))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtNhanVienBan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtTenNgShip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel24)
                                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSDTNgShip, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(lblSoLanTraHang)
                    .addComponent(lblTraHetHang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnXuatHoaDonTraHang)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout DiaLogHoaDonTraHangChiTietLayout = new javax.swing.GroupLayout(DiaLogHoaDonTraHangChiTiet.getContentPane());
        DiaLogHoaDonTraHangChiTiet.getContentPane().setLayout(DiaLogHoaDonTraHangChiTietLayout);
        DiaLogHoaDonTraHangChiTietLayout.setHorizontalGroup(
            DiaLogHoaDonTraHangChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DiaLogHoaDonTraHangChiTietLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        DiaLogHoaDonTraHangChiTietLayout.setVerticalGroup(
            DiaLogHoaDonTraHangChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DiaLogHoaDonTraHangChiTietLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        DiaLogViewHoaDonChiTiet.setTitle("Chi tiết hóa đơn");
        DiaLogViewHoaDonChiTiet.setMinimumSize(new java.awt.Dimension(942, 527));
        DiaLogViewHoaDonChiTiet.setSize(new java.awt.Dimension(942, 527));

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

        txtMaHoaDon.setEditable(false);
        txtMaHoaDon.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtMaHoaDon.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtNgayThanhToan.setEditable(false);
        txtNgayThanhToan.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNgayThanhToan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtNhanVien.setEditable(false);
        txtNhanVien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNhanVien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtKhachHang.setEditable(false);
        txtKhachHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtKhachHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtTienDaThanhToan.setEditable(false);
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

        txtPhanTramGiam.setEditable(false);
        txtPhanTramGiam.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPhanTramGiam.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel17.setText("Cấp bậc KH tại thời điểm giao dịch:");

        txtCapBac.setEditable(false);
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

        javax.swing.GroupLayout DiaLogViewHoaDonChiTietLayout = new javax.swing.GroupLayout(DiaLogViewHoaDonChiTiet.getContentPane());
        DiaLogViewHoaDonChiTiet.getContentPane().setLayout(DiaLogViewHoaDonChiTietLayout);
        DiaLogViewHoaDonChiTietLayout.setHorizontalGroup(
            DiaLogViewHoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DiaLogViewHoaDonChiTietLayout.setVerticalGroup(
            DiaLogViewHoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        DiaLogHdTraHangChiTiet.setTitle("Hóa đơn trả hàng chi tiết");
        DiaLogHdTraHangChiTiet.setMinimumSize(new java.awt.Dimension(736, 467));
        DiaLogHdTraHangChiTiet.setSize(new java.awt.Dimension(736, 467));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel18.setText("Nhân viên trả hàng:");

        tblHDTHCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã CTSP", "Tên SP", "Hãng", "Size", "Màu sắc", "Số lượng trả", "Tiền hoàn trả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHDTHCT.setRowHeight(25);
        tblHDTHCT.setSelectionBackground(new java.awt.Color(86, 154, 222));
        jScrollPane6.setViewportView(tblHDTHCT);

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel33.setText("Ngày trả hàng:");

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel34.setText("Tổng tiền hoàn trả:");

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel35.setText("Ghi chú:");

        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel36.setText("Danh sách sản phẩm đã trả:");

        txtNVTH.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtNVTH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtNTH.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtNTH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtTTHT.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTTHT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtGCHT.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtGCHT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNVTH, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNTH, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTTHT, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGCHT, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel18)
                                    .addComponent(txtNVTH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addComponent(jLabel33))
                            .addComponent(txtNTH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(jLabel34))
                    .addComponent(txtTTHT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel35)
                    .addComponent(txtGCHT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout DiaLogHdTraHangChiTietLayout = new javax.swing.GroupLayout(DiaLogHdTraHangChiTiet.getContentPane());
        DiaLogHdTraHangChiTiet.getContentPane().setLayout(DiaLogHdTraHangChiTietLayout);
        DiaLogHdTraHangChiTietLayout.setHorizontalGroup(
            DiaLogHdTraHangChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DiaLogHdTraHangChiTietLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        DiaLogHdTraHangChiTietLayout.setVerticalGroup(
            DiaLogHdTraHangChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DiaLogHdTraHangChiTietLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

        txtMaHDNgoai.setEditable(false);
        txtMaHDNgoai.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtMaHDNgoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtMaHDNgoai.setMaximumSize(new java.awt.Dimension(7, 30));
        txtMaHDNgoai.setMinimumSize(new java.awt.Dimension(7, 30));
        txtMaHDNgoai.setPreferredSize(new java.awt.Dimension(7, 30));

        txtTenKHNgoai.setEditable(false);
        txtTenKHNgoai.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTenKHNgoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTenKHNgoai.setMaximumSize(new java.awt.Dimension(7, 30));
        txtTenKHNgoai.setMinimumSize(new java.awt.Dimension(7, 30));
        txtTenKHNgoai.setPreferredSize(new java.awt.Dimension(7, 30));

        txtSDTNgoai.setEditable(false);
        txtSDTNgoai.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtSDTNgoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtSDTNgoai.setMaximumSize(new java.awt.Dimension(7, 30));
        txtSDTNgoai.setMinimumSize(new java.awt.Dimension(7, 30));
        txtSDTNgoai.setPreferredSize(new java.awt.Dimension(7, 30));

        txtDiaChiNgoai.setEditable(false);
        txtDiaChiNgoai.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtDiaChiNgoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtDiaChiNgoai.setMaximumSize(new java.awt.Dimension(7, 30));
        txtDiaChiNgoai.setMinimumSize(new java.awt.Dimension(7, 30));
        txtDiaChiNgoai.setPreferredSize(new java.awt.Dimension(7, 30));

        txtThanhTienNgoai.setEditable(false);
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

        txtTienTraKhach.setEditable(false);
        txtTienTraKhach.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTienTraKhach.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTienTraKhach.setMaximumSize(new java.awt.Dimension(7, 30));
        txtTienTraKhach.setMinimumSize(new java.awt.Dimension(7, 30));
        txtTienTraKhach.setPreferredSize(new java.awt.Dimension(7, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel9.setText("Ghi chú:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel10.setText("Tên KH:");

        txtTenKhTra.setEditable(false);
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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N

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
                "STT", "Mã HĐ", "Nhân viên", "Khách hàng", "Ngày thanh toán", "Trạng thái"
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
        jTextField7.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField7CaretUpdate(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N

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
        if (thHoaDonResponse.getPhanTramGiamGia() == 0) {
            txtCapBac.setText("Đồng");
        } else if (thHoaDonResponse.getPhanTramGiamGia() == 3) {
            txtCapBac.setText("Bạc");
        } else if (thHoaDonResponse.getPhanTramGiamGia() == 5) {
            txtCapBac.setText("Vàng");
        } else if (thHoaDonResponse.getPhanTramGiamGia() == 10) {
            txtCapBac.setText("Kim Cương");
        }
        listHoaDonChiTiet = banHangService.getAllHDCTByIdHoaDon(thHoaDonResponse.getId());
        loadTableHoaDonChiTiet(listHoaDonChiTiet);
        DiaLogViewHoaDonChiTiet.setVisible(true);
        DiaLogViewHoaDonChiTiet.setLocationRelativeTo(null);
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
            String array[] = txtTienTraKhach.getText().trim().split(" ");
            hoaDonCu.setThanhTien(hoaDonCu.getThanhTien().subtract(new BigDecimal(array[0].replace(",", ""))));
            banHangService.saveOrUpdate(hoaDonCu);
            HoaDonTraHang hoaDonTraHang = new HoaDonTraHang();
            hoaDonTraHang.setNhanVien(nhanVien);
            hoaDonTraHang.setHoaDon(hoaDonCu);
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
                traHangService.updateSoLuong(value.getIdHDCT(), value.getSoLuong());
                if (value.getSoLuongTruocKhiTra() == value.getSoLuong()) {
                    HoaDonChiTiet hoaDonChiTiet = banHangService.findByIdHoaDonChiTiet(value.getIdHDCT());
                    banHangService.deleteHDCT(hoaDonChiTiet);
                }
            }

            int confirm1 = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn trả hàng không?");
            if (confirm1 == JOptionPane.YES_OPTION) {
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
                try {
                    ExportPhieuTraHang export = new ExportPhieuTraHang();
                    export.exportBill(hoaDonCu, hoaDonTraHang, traHangService.getAllHoaDonTraHangChiTiet(hoaDonTraHang.getId()), path);
                    JOptionPane.showMessageDialog(this, "In hóa đơn thành công");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "In hóa đơn thất bại");
                }
            }

            listHoaDon = traHangService.getAllResponseHD();
            loadTableHoaDon(listHoaDon);
            listHoaDonTraHang = traHangService.getAllHoaDonTraHang();
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
            DecimalFormat df = new DecimalFormat("#,###");
            int row = tblHoaDon.getSelectedRow();
            ThHoaDonResponse thHoaDonResponse = listHoaDon.get(row);
            txtMaHDNgoai.setText(thHoaDonResponse.getMaHD());
            txtTenKHNgoai.setText(thHoaDonResponse.getTenKhachHang());
            txtThanhTienNgoai.setText(df.format(thHoaDonResponse.getThanhTien()) + " Vnđ");
            txtSDTNgoai.setText(thHoaDonResponse.getSdtKhachHang());
            txtDiaChiNgoai.setText(thHoaDonResponse.getDiaChiKhachHang());
            txtTenKhTra.setText(thHoaDonResponse.getTenKhachHang());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void tblHoaDonTraHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonTraHangMouseClicked
        try {
            DecimalFormat df = new DecimalFormat("#,###");
            DiaLogHoaDonTraHangChiTiet.setVisible(true);
            DiaLogHoaDonTraHangChiTiet.setLocationRelativeTo(null);
            int row = tblHoaDonTraHang.getSelectedRow();
            HoaDon hoaDon = listHoaDonTraHang.get(row);
            txtMaHD.setText(hoaDon.getMa());
            txttenKH.setText(hoaDon.getKhachHang().getHoTen());
            txtSDTKH.setText(hoaDon.getKhachHang().getSdt());
            txtNhanVienBan.setText(hoaDon.getNhanVien().getMa() + " - " + hoaDon.getNhanVien().getTen());
            txtThanhTien.setText(df.format(hoaDon.getThanhTien()) + " Vnđ");
            jLabel27.setText(ConvertMoneyToString.convertMoneyToText(String.valueOf(hoaDon.getThanhTien())));
            txtHinhThucGH.setText(hoaDon.getHinhThucGiaoHang() == 0 ? "Tại quầy" : "Đặt hàng");
            if (hoaDon.getHinhThucGiaoHang() == 0) {
                jLabel29.setVisible(false);
                jLabel30.setVisible(false);
                jLabel31.setVisible(false);
                jLabel32.setVisible(false);
                txtTenNgNhan.setVisible(false);
                txtDiaChi.setVisible(false);
                txtTenNgShip.setVisible(false);
                txtSDTNgShip.setVisible(false);
            } else {
                jLabel29.setVisible(true);
                jLabel30.setVisible(true);
                jLabel31.setVisible(true);
                jLabel32.setVisible(true);
                txtTenNgNhan.setVisible(true);
                txtDiaChi.setVisible(true);
                txtTenNgShip.setVisible(true);
                txtSDTNgShip.setVisible(true);
                txtTenNgNhan.setText(hoaDon.getTenNguoiNhan() + " - " + hoaDon.getSdtNguoiNhan());
                txtDiaChi.setText(hoaDon.getDiaChi());
                txtTenNgShip.setText(hoaDon.getTenNguoiShip());
                txtSDTNgShip.setText(hoaDon.getSdtNguoiShip());
            }
            listHDTraHang = traHangService.getAllHoaDonTraHangByIdHoaDon(hoaDon.getId());
            loadTableHDTraHang(listHDTraHang);
            listHoaDonChiTiet = banHangService.getAllHDCTByIdHoaDon(hoaDon.getId());
            if (listHoaDonChiTiet.size() > 0) {
                lblTraHetHang.setVisible(false);
            } else {
                lblTraHetHang.setVisible(true);
                lblTraHetHang.setText("Hóa đơn này đã trả hết hàng");
            }
            loadTableHDCT(listHoaDonChiTiet);
            lblSoLanTraHang.setText(listHDTraHang.size() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblHoaDonTraHangMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            int rowHoaDon = tblHoaDon.getSelectedRow();
            ThHoaDonResponse thHoaDonResponse = listHoaDon.get(rowHoaDon);
            listSanPhamTra.clear();
            for (BhHoaDonChiTietResponse xx : listHoaDonChiTiet) {
                if (xx.getGiamGia().compareTo(BigDecimal.ZERO) > 0) {
                    JOptionPane.showMessageDialog(this, "Trong danh sách đã có sản phẩm được giảm giá khuyến mại");
                    return;
                }
                ThHoaDonChiTietResponse thdctr = new ThHoaDonChiTietResponse();
                thdctr.setChatLieu(xx.getChatLieu());
                thdctr.setDonGia(xx.getDonGia());
                BigDecimal giaBan = xx.getGiaBan().subtract(xx.getGiaBan().multiply(new BigDecimal(thHoaDonResponse.getPhanTramGiamGia()).divide(new BigDecimal(100))));
                thdctr.setGiaBan(giaBan);
                thdctr.setGiamGia(xx.getGiamGia());
                thdctr.setHang(xx.getHang());
                thdctr.setIdChiTietSP(xx.getIdChiTietSP());
                thdctr.setIdHDCT(xx.getIdHDCT());
                thdctr.setIdHoaDon(xx.getIdHoaDon());
                thdctr.setMaCTSP(xx.getMaCTSP());
                thdctr.setMaSP(xx.getMaSP());
                thdctr.setMauSac(xx.getMauSac());
                thdctr.setSize(xx.getSize());
                thdctr.setSoLuong(xx.getSoLuong());
                thdctr.setSoLuongTon(xx.getSoLuongTon());
                thdctr.setSoLuongTruocKhiTra(xx.getSoLuong());
                thdctr.setTenSP(xx.getTenSP());
                listSanPhamTra.put(thdctr.getIdChiTietSP(), thdctr);
            }
            loadTableSanPham(listSanPhamTra);
            tinhTienTraKhach();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tblHDTraHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDTraHangMouseClicked

    }//GEN-LAST:event_tblHDTraHangMouseClicked

    private void jTextField7CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField7CaretUpdate
        listHoaDonTraHang = traHangService.findHoaDonById(jTextField7.getText());
        loadTableHoaDonTraHang(listHoaDonTraHang);
    }//GEN-LAST:event_jTextField7CaretUpdate

    private void btnXuatHoaDonTraHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatHoaDonTraHangActionPerformed
        int rowhdth = tblHDTraHang.getSelectedRow();
        if (rowhdth == -1) {
            JOptionPane.showMessageDialog(this, "Hãy chọn một hóa đơn trả hàng");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn trả hàng không?");
        if (confirm == JOptionPane.YES_OPTION) {
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
            int rowHoaDon = tblHoaDonTraHang.getSelectedRow();
            HoaDon hoaDon = listHoaDonTraHang.get(rowHoaDon);

            HoaDonTraHang hoaDonTraHang = listHDTraHang.get(rowhdth);
            try {
                ExportPhieuTraHang export = new ExportPhieuTraHang();
                export.exportBill(hoaDon, hoaDonTraHang, listHoaDonTraHangChiTiet, path);
                JOptionPane.showMessageDialog(this, "In hóa đơn thành công");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "In hóa đơn thất bại");
            }
        }
    }//GEN-LAST:event_btnXuatHoaDonTraHangActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            int row = tblHDTraHang.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Hãy chọn hóa đơn trả hàng");
                return;
            }
            DecimalFormat df = new DecimalFormat("#,###");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
            DiaLogHdTraHangChiTiet.setVisible(true);
            DiaLogHdTraHangChiTiet.setLocationRelativeTo(null);
            HoaDonTraHang hoaDonTraHang = listHDTraHang.get(row);
            txtNVTH.setText(hoaDonTraHang.getNhanVien().getMa() + " - " + hoaDonTraHang.getNhanVien().getTen());
            txtNTH.setText(sdf.format(hoaDonTraHang.getNgayDoiTra()));
            txtTTHT.setText(df.format(hoaDonTraHang.getTienHoanTraKhach()) + " Vnđ");
            txtGCHT.setText(hoaDonTraHang.getGhiChu());
            listHoaDonTraHangChiTiet = traHangService.getAllHoaDonTraHangChiTiet(hoaDonTraHang.getId());
            loadTableHoaDonTHCT(listHoaDonTraHangChiTiet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblHDCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDCTMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHDCTMouseClicked

    private void loadTableHoaDonChiTiet(List<BhHoaDonChiTietResponse> list) {
        modelHoaDonChiTiet.setRowCount(0);
        for (BhHoaDonChiTietResponse xx : list) {
            modelHoaDonChiTiet.addRow(new Object[]{xx.getMaCTSP(), xx.getTenSP(), xx.getHang(), xx.getMauSac(), xx.getSize(), xx.getChatLieu(), xx.getSoLuong(), xx.getGiamGia(), xx.getGiaBan()});
        }
    }

    private void loadTableHDCT(List<BhHoaDonChiTietResponse> list) {
        modelHDCT.setRowCount(0);
        for (BhHoaDonChiTietResponse xx : list) {
            modelHDCT.addRow(new Object[]{xx.getMaCTSP(), xx.getTenSP(), xx.getHang(), xx.getMauSac(), xx.getSize(), xx.getChatLieu(), xx.getSoLuong(), xx.getGiamGia(), xx.getGiaBan()});
        }
    }

    private void loadTableHoaDonTHCT(List<HoaDonTraHangChiTiet> list) {
        modelHDTraHangCT.setRowCount(0);
        for (HoaDonTraHangChiTiet xx : list) {
            modelHDTraHangCT.addRow(new Object[]{xx.getMaChiTietSanPham(), xx.getTenSP(), xx.getTenHang(), xx.getKichThuoc(), xx.getMauSac(), xx.getSoLuongTra(), xx.getGiaBan()});
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
    private javax.swing.JDialog DiaLogHdTraHangChiTiet;
    private javax.swing.JDialog DiaLogHoaDonTraHangChiTiet;
    private javax.swing.JDialog DiaLogViewHoaDonChiTiet;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnTraHang;
    private javax.swing.JButton btnXemChiTiet;
    private javax.swing.JButton btnXuatHoaDonTraHang;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
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
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLabel lblSoLanTraHang;
    private javax.swing.JLabel lblTraHetHang;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTable tblHDTHCT;
    private javax.swing.JTable tblHDTraHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTable tblHoaDonTraHang;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtCapBac;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDiaChiNgoai;
    private javax.swing.JTextField txtGCHT;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtHinhThucGH;
    private javax.swing.JTextField txtKhachHang;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaHDNgoai;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtNTH;
    private javax.swing.JTextField txtNVTH;
    private javax.swing.JTextField txtNgayThanhToan;
    private javax.swing.JTextField txtNhanVien;
    private javax.swing.JTextField txtNhanVienBan;
    private javax.swing.JTextField txtPhanTramGiam;
    private javax.swing.JTextField txtSDTKH;
    private javax.swing.JTextField txtSDTNgShip;
    private javax.swing.JTextField txtSDTNgoai;
    private javax.swing.JTextField txtTTHT;
    private javax.swing.JTextField txtTenKHNgoai;
    private javax.swing.JTextField txtTenKhTra;
    private javax.swing.JTextField txtTenNgNhan;
    private javax.swing.JTextField txtTenNgShip;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtThanhTienNgoai;
    private javax.swing.JTextField txtTienDaThanhToan;
    private javax.swing.JTextField txtTienTraKhach;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txttenKH;
    // End of variables declaration//GEN-END:variables
}
