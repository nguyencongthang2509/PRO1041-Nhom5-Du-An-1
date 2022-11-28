package core.view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import core.quanly.service.BanHangService;
import core.quanly.service.impl.BanHangServiceImpl;
import core.quanly.viewmodel.BhChiTietSPResponse;
import core.quanly.viewmodel.BhHoaDonChiTietResponse;
import core.quanly.viewmodel.BhHoaDonResponse;
import core.quanly.viewmodel.BhKhachHangResponse;
import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import domainmodels.KhachHang;
import domainmodels.NhanVien;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.ExportFilePdf;

/**
 *
 * @author thangncph26123
 */
public class ViewBanHang extends javax.swing.JPanel implements Runnable, ThreadFactory {

    private final DefaultTableModel modelSanPham;
    private final DefaultTableModel modelGioHang;
    private final DefaultTableModel modelHoaDon;
    private final DefaultTableModel modelKhachHang;
    private List<BhChiTietSPResponse> listSanPham;
    private List<BhHoaDonResponse> listHoaDon;
    private List<BhKhachHangResponse> listKhachHang;
    private final BanHangService banHangService;
    private Map<String, BhHoaDonChiTietResponse> mapGioHang;
    private NhanVien nhanVien;
    private int soLuongCu;
    public static WebcamPanel panel = null;
    public static Webcam webcam = null;

    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    public ViewBanHang(NhanVien nv) {
        initComponents();
        nhanVien = nv;
        initWebcam();
        modelSanPham = (DefaultTableModel) tblSanPham.getModel();
        modelGioHang = (DefaultTableModel) tblGioHang.getModel();
        modelHoaDon = (DefaultTableModel) tblHoaDonCho.getModel();
        modelKhachHang = (DefaultTableModel) tblKhachHangView.getModel();
        banHangService = new BanHangServiceImpl();
        listSanPham = new ArrayList<>();
        listKhachHang = new ArrayList<>();
        listHoaDon = new ArrayList<>();
        mapGioHang = new HashMap<>();
        listSanPham = banHangService.getAllChiTietSP();
        listHoaDon = banHangService.getAllResponseHD(nhanVien.getId());
        loadDataToTableSP(listSanPham);
        loadDataToHoaDon(listHoaDon);
    }

    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam
        webcam.setViewSize(size);
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        pnlWebcam.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 158));

        executor.execute(this);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException e) {
                //No result...
            }
            if (result != null) {
                try {
                    String maVachScan = result.getText();
                    String idChiTietSP = banHangService.findChiTietSPByMaVach(maVachScan);
                    int rowHoaDon = tblHoaDonCho.getSelectedRow();
                    if (rowHoaDon == -1) {
                        JOptionPane.showMessageDialog(this, "Mời chọn hóa đơn");
                        continue;
                    }
                    BhHoaDonResponse bhHoaDonResponse = listHoaDon.get(rowHoaDon);
                    BhChiTietSPResponse bhChiTietSPCheck = banHangService.findCTSPByMaVach(listSanPham, maVachScan);
                    BhHoaDonChiTietResponse hoaDonChiTietCheck = mapGioHang.get(idChiTietSP);
                    int soLuongThayDoi = 0;
                    if (hoaDonChiTietCheck != null) {
                        Object[] options = {"Thêm số lượng", "Giảm số lượng", "Hủy"};
                        int resultOption = JOptionPane.showOptionDialog(this,
                                "Sản phẩm này đã có trong giỏ hàng, bạn muốn làm gì?",
                                "Xác nhận",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[2]);
                        if (resultOption == 0 || resultOption == 1) {
                            String soLuongThayDoiStr = JOptionPane.showInputDialog(this, "Nhập số lượng: ");
                            if (soLuongThayDoiStr == null) {
                                continue;
                            }
                            try {
                                soLuongThayDoi = Integer.parseInt(soLuongThayDoiStr);
                                if (soLuongThayDoi < 0) {
                                    JOptionPane.showMessageDialog(this, "Số lượng không thể âm");
                                    continue;
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, "Số lượng phải là số");
                                continue;
                            }
                            if (resultOption == 0) {
                                if (soLuongThayDoi > bhChiTietSPCheck.getSoLuongTon() - hoaDonChiTietCheck.getSoLuong()) {
                                    JOptionPane.showMessageDialog(this, "Không được vượt số lượng tồn");
                                    continue;
                                }
                                soLuongThayDoi = soLuongThayDoi;
                            } else {
                                if (soLuongThayDoi > hoaDonChiTietCheck.getSoLuong()) {
                                    JOptionPane.showMessageDialog(this, "Số lượng sau khi giảm không được âm");
                                    continue;
                                }
                                if (soLuongThayDoi == hoaDonChiTietCheck.getSoLuong()) {
                                    mapGioHang.remove(hoaDonChiTietCheck.getIdChiTietSP());
                                    loadDataToTableHDCT(mapGioHang);
                                    HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                                    hoaDonChiTiet.setId(hoaDonChiTietCheck.getIdHDCT());
                                    banHangService.deleteHDCT(hoaDonChiTiet);
                                }
                                soLuongThayDoi = -soLuongThayDoi;
                            }
                        }
                    }
                    if (hoaDonChiTietCheck == null) {
                        String inputSoLuong = JOptionPane.showInputDialog(this, "Nhập số lượng", "0");
                        if (inputSoLuong == null) {
                            continue;
                        }
                        int soLuong;
                        try {
                            soLuong = Integer.parseInt(inputSoLuong);
                            if (soLuong < 0 || soLuong > bhChiTietSPCheck.getSoLuongTon()) {
                                JOptionPane.showMessageDialog(this, "Số lượng không được âm, không lớn hơn số lượng tồn");
                                continue;
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "Số lượng phải là số");
                            continue;
                        }
                        if (soLuong == 0) {
                            continue;
                        }
                        BhHoaDonChiTietResponse bhHoaDonChiTietResponse = new BhHoaDonChiTietResponse();
                        bhHoaDonChiTietResponse.setIdChiTietSP(bhChiTietSPCheck.getId());
                        bhHoaDonChiTietResponse.setIdHoaDon(bhHoaDonResponse.getId());
                        bhHoaDonChiTietResponse.setMaSP(bhChiTietSPCheck.getMaSP());
                        bhHoaDonChiTietResponse.setTenSP(bhChiTietSPCheck.getTenSP());
                        bhHoaDonChiTietResponse.setSoLuong(soLuong);
                        bhHoaDonChiTietResponse.setHang(bhChiTietSPCheck.getHang());
                        bhHoaDonChiTietResponse.setMauSac(bhChiTietSPCheck.getMauSac());
                        bhHoaDonChiTietResponse.setSize(bhChiTietSPCheck.getSize());
                        bhHoaDonChiTietResponse.setSoLuongTon(bhChiTietSPCheck.getSoLuongTon());
                        bhHoaDonChiTietResponse.setMaKhuyenMai(bhChiTietSPCheck.getMaKhuyeMai());
                        bhHoaDonChiTietResponse.setLoaiKhuyenMai(bhChiTietSPCheck.getLoaiKhuyenMai());
                        bhHoaDonChiTietResponse.setDonGia(bhChiTietSPCheck.getDonGia());
                        bhHoaDonChiTietResponse.setGiamGia(bhChiTietSPCheck.getKhuyenMai());
                        BigDecimal giaBan = new BigDecimal(0);
                        BigDecimal giamGia = new BigDecimal(0);
                        if (bhChiTietSPCheck.getMaKhuyeMai() == null) {
                            giaBan = bhChiTietSPCheck.getDonGia();
                            giamGia = new BigDecimal(0);
                        } else {
                            if (bhChiTietSPCheck.getLoaiKhuyenMai() == 0) {
                                giamGia = bhChiTietSPCheck.getDonGia().multiply(new BigDecimal(bhChiTietSPCheck.getKhuyenMai()).divide(new BigDecimal(100)));
                                giaBan = bhChiTietSPCheck.getDonGia().subtract(giamGia);
                            } else {
                                giamGia = new BigDecimal(bhChiTietSPCheck.getKhuyenMai());
                                giaBan = bhChiTietSPCheck.getDonGia().subtract(giamGia);
                            }
                        }
                        bhHoaDonChiTietResponse.setGiaBan(giaBan);
                        mapGioHang.put(bhHoaDonChiTietResponse.getIdChiTietSP(), bhHoaDonChiTietResponse);
                        HoaDonChiTiet hoaDonChiTiet = banHangService.convertHoaDonChiTiet(bhHoaDonChiTietResponse);
                        banHangService.saveOrUpdateHDCT(hoaDonChiTiet);
                        bhHoaDonChiTietResponse.setIdHDCT(hoaDonChiTiet.getId());
                        loadDataToTableHDCT(mapGioHang);
                    } else {
                        hoaDonChiTietCheck.setSoLuong(hoaDonChiTietCheck.getSoLuong() + soLuongThayDoi);
                        if (hoaDonChiTietCheck.getSoLuong() != 0) {
                            mapGioHang.replace(hoaDonChiTietCheck.getIdChiTietSP(), hoaDonChiTietCheck);
                            loadDataToTableHDCT(mapGioHang);
                            HoaDonChiTiet hoaDonChiTiet = banHangService.convertHoaDonChiTiet(hoaDonChiTietCheck);
                            hoaDonChiTiet.setId(hoaDonChiTietCheck.getIdHDCT());
                            banHangService.saveOrUpdateHDCT(hoaDonChiTiet);
                        }
                    }
                    tinhTongTien();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    private void loadDataToTableSP(List<BhChiTietSPResponse> list) {
        modelSanPham.setRowCount(0);
        for (BhChiTietSPResponse xx : list) {
            modelSanPham.addRow(xx.toDataRow());
        }
    }

    private void loadDataToTableHDCT(Map<String, BhHoaDonChiTietResponse> map) {
        modelGioHang.setRowCount(0);
        for (Map.Entry<String, BhHoaDonChiTietResponse> entry : map.entrySet()) {
            BhHoaDonChiTietResponse value = entry.getValue();
            modelGioHang.addRow(value.toDataRow());
        }
    }

    private void loadDataToKhachHang(List<BhKhachHangResponse> list) {
        modelKhachHang.setRowCount(0);
        for (BhKhachHangResponse xx : list) {
            modelKhachHang.addRow(xx.toDataRow());
        }
    }

    private void loadDataToHoaDon(List<BhHoaDonResponse> list) {
        modelHoaDon.setRowCount(0);
        for (BhHoaDonResponse xx : list) {
            modelHoaDon.addRow(xx.toDataRow());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnChonKH = new javax.swing.JFrame();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        KhachHangView = new javax.swing.JFrame();
        jPanel16 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblKhachHangView = new javax.swing.JTable();
        btnChonKhachHang = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnXoa = new javax.swing.JButton();
        btnXoa1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDonCho = new javax.swing.JTable();
        btnHuy = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        tbpDonHang = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaKHTaiQuay = new javax.swing.JTextField();
        txtTenKHTaiQuay = new javax.swing.JTextField();
        btnChonTaiQuay = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtMaHDTaiQuay = new javax.swing.JTextField();
        txtTenNVTaiQuay = new javax.swing.JTextField();
        txtTongTienTaiQuay = new javax.swing.JTextField();
        txtGiamGiaTaiQuay = new javax.swing.JTextField();
        txtThanhToanTaiQuay = new javax.swing.JTextField();
        txtTienKhachDuaTaiQuay = new javax.swing.JTextField();
        txtTienThuaTaiQuay = new javax.swing.JTextField();
        cboHTThanhToanTaiQuay = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        lblTaoHoaDonTaiQuay = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblThanhToanTaiQuay = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtTienKhachCKTaiQuay = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtHoTenKHDatHang = new javax.swing.JTextField();
        txtSDTKHDatHang = new javax.swing.JTextField();
        txtDiaChiDatHang = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtTenNguoiShipDatHang = new javax.swing.JTextField();
        txtSDTNguoiShipDatHang = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtTongTienDatHang = new javax.swing.JTextField();
        txtTenNVDatHang = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtMaHDDatHang = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        cboHTThanhToanDatHang = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtGiamGiaDatHang = new javax.swing.JTextField();
        txtTienKhachDuaDatHang = new javax.swing.JTextField();
        txtThanhToanDatHang = new javax.swing.JTextField();
        txtTienKhachCKDatHang = new javax.swing.JTextField();
        txtTienThuaDatHang = new javax.swing.JTextField();
        btnTaoHoaDonDatHang = new javax.swing.JButton();
        btnDangGiaoDatHang = new javax.swing.JButton();
        btnDaGiaoDatHang = new javax.swing.JButton();
        txtTienShip = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        pnlWebcam = new javax.swing.JPanel();

        btnChonKH.setBackground(new java.awt.Color(255, 255, 255));
        btnChonKH.setSize(new java.awt.Dimension(800, 600));

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Tên KH", "SDT", "Email", "Giới tính", "Địa chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblKhachHang);
        if (tblKhachHang.getColumnModel().getColumnCount() > 0) {
            tblKhachHang.getColumnModel().getColumn(0).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(1).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(2).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Danh sách khách hàng", jPanel10);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Chỉnh sửa ttin khách hàng", jPanel11);

        jButton1.setText("Chọn");

        javax.swing.GroupLayout btnChonKHLayout = new javax.swing.GroupLayout(btnChonKH.getContentPane());
        btnChonKH.getContentPane().setLayout(btnChonKHLayout);
        btnChonKHLayout.setHorizontalGroup(
            btnChonKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnChonKHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addContainerGap())
            .addGroup(btnChonKHLayout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnChonKHLayout.setVerticalGroup(
            btnChonKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnChonKHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        KhachHangView.setTitle("Danh sách khách hàng");
        KhachHangView.setBackground(new java.awt.Color(255, 255, 255));
        KhachHangView.setSize(new java.awt.Dimension(527, 524));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        tblKhachHangView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Tên KH", "SDT", "Email", "Giới tính", "Địa chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHangView.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tblKhachHangView);
        if (tblKhachHangView.getColumnModel().getColumnCount() > 0) {
            tblKhachHangView.getColumnModel().getColumn(0).setResizable(false);
            tblKhachHangView.getColumnModel().getColumn(1).setResizable(false);
            tblKhachHangView.getColumnModel().getColumn(2).setResizable(false);
            tblKhachHangView.getColumnModel().getColumn(3).setResizable(false);
            tblKhachHangView.getColumnModel().getColumn(4).setResizable(false);
            tblKhachHangView.getColumnModel().getColumn(5).setResizable(false);
        }

        btnChonKhachHang.setText("Chọn");
        btnChonKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addComponent(btnChonKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnChonKhachHang)
                .addGap(13, 13, 13))
        );

        jTabbedPane4.addTab("Danh sách khách hàng", jPanel9);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 465, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("Chỉnh sửa thông tin khách hàng", jPanel13);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );

        javax.swing.GroupLayout KhachHangViewLayout = new javax.swing.GroupLayout(KhachHangView.getContentPane());
        KhachHangView.getContentPane().setLayout(KhachHangViewLayout);
        KhachHangViewLayout.setHorizontalGroup(
            KhachHangViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        KhachHangViewLayout.setVerticalGroup(
            KhachHangViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jToolBar1.setRollover(true);

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1292, 784));
        setMinimumSize(new java.awt.Dimension(1292, 784));
        setPreferredSize(new java.awt.Dimension(1292, 784));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Màu sắc", "Size", "Hãng", "SL tồn", "Khuyến mại", "Đơn giá", "Mô tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.getTableHeader().setReorderingAllowed(false);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);
        if (tblSanPham.getColumnModel().getColumnCount() > 0) {
            tblSanPham.getColumnModel().getColumn(0).setResizable(false);
            tblSanPham.getColumnModel().getColumn(1).setResizable(false);
            tblSanPham.getColumnModel().getColumn(2).setResizable(false);
            tblSanPham.getColumnModel().getColumn(3).setResizable(false);
            tblSanPham.getColumnModel().getColumn(4).setResizable(false);
            tblSanPham.getColumnModel().getColumn(5).setResizable(false);
            tblSanPham.getColumnModel().getColumn(6).setResizable(false);
        }

        txtTimKiem.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel14.setIcon(new ImageIcon("src/main/images/search.png"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Hãng", "Màu", "Size", "Đơn giá", "Số lượng", "Giảm giá", "Giá bán", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGioHang.getTableHeader().setReorderingAllowed(false);
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        tblGioHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblGioHangKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblGioHangKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblGioHang);
        if (tblGioHang.getColumnModel().getColumnCount() > 0) {
            tblGioHang.getColumnModel().getColumn(0).setResizable(false);
            tblGioHang.getColumnModel().getColumn(1).setResizable(false);
            tblGioHang.getColumnModel().getColumn(2).setResizable(false);
            tblGioHang.getColumnModel().getColumn(3).setResizable(false);
        }

        btnXoa.setBackground(new java.awt.Color(153, 204, 255));
        btnXoa.setIcon(new ImageIcon("src/main/images/trash.png"));
        btnXoa.setText("Xóa tất cả");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnXoa1.setBackground(new java.awt.Color(153, 204, 255));
        btnXoa1.setIcon(new ImageIcon("src/main/images/trash.png"));
        btnXoa1.setText("Xóa SP");
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(btnXoa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chờ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        tblHoaDonCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HĐ", "Ngày tạo", "Nhân viên", "Khách hàng", "Hình thức", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonCho.getTableHeader().setReorderingAllowed(false);
        tblHoaDonCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDonCho);
        if (tblHoaDonCho.getColumnModel().getColumnCount() > 0) {
            tblHoaDonCho.getColumnModel().getColumn(0).setResizable(false);
            tblHoaDonCho.getColumnModel().getColumn(1).setResizable(false);
            tblHoaDonCho.getColumnModel().getColumn(2).setResizable(false);
            tblHoaDonCho.getColumnModel().getColumn(3).setResizable(false);
        }

        btnHuy.setBackground(new java.awt.Color(255, 153, 153));
        btnHuy.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnHuy.setIcon(new ImageIcon("src/main/images/close.png"));
        btnHuy.setText("Hủy");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 15))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel3.setText("Mã KH:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel4.setText("Tên KH:");

        txtMaKHTaiQuay.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtMaKHTaiQuay.setForeground(new java.awt.Color(255, 0, 0));
        txtMaKHTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtTenKHTaiQuay.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTenKHTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        btnChonTaiQuay.setBackground(new java.awt.Color(153, 204, 255));
        btnChonTaiQuay.setIcon(new ImageIcon("src/main/images/choice.png"));
        btnChonTaiQuay.setText("Chọn");
        btnChonTaiQuay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonTaiQuayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(7, 7, 7)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(txtTenKHTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChonTaiQuay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(txtMaKHTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(txtMaKHTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTenKHTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnChonTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 15))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel5.setText("Mã hóa đơn:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel6.setText("Tên nhân viên:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel7.setText("Tổng tiền:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel8.setText("Giảm giá:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel9.setText("Thanh toán:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel10.setText("Tiền khách đưa:");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel11.setText("Tiền thừa:");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel12.setText("HT thanh toán:");

        txtMaHDTaiQuay.setEditable(false);
        txtMaHDTaiQuay.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtMaHDTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtTenNVTaiQuay.setEditable(false);
        txtTenNVTaiQuay.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTenNVTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtTongTienTaiQuay.setEditable(false);
        txtTongTienTaiQuay.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTongTienTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtGiamGiaTaiQuay.setEditable(false);
        txtGiamGiaTaiQuay.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtGiamGiaTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtThanhToanTaiQuay.setEditable(false);
        txtThanhToanTaiQuay.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtThanhToanTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtTienKhachDuaTaiQuay.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTienKhachDuaTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTienKhachDuaTaiQuay.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienKhachDuaTaiQuayCaretUpdate(evt);
            }
        });
        txtTienKhachDuaTaiQuay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTienKhachDuaTaiQuayMouseClicked(evt);
            }
        });

        txtTienThuaTaiQuay.setEditable(false);
        txtTienThuaTaiQuay.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTienThuaTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        cboHTThanhToanTaiQuay.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cboHTThanhToanTaiQuay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản", "Kết hợp" }));
        cboHTThanhToanTaiQuay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHTThanhToanTaiQuayActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(153, 204, 255));

        lblTaoHoaDonTaiQuay.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        lblTaoHoaDonTaiQuay.setForeground(new java.awt.Color(255, 255, 255));
        lblTaoHoaDonTaiQuay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTaoHoaDonTaiQuay.setIcon(new ImageIcon("src/main/images/page.png"));
        lblTaoHoaDonTaiQuay.setText("Tạo hóa đơn");
        lblTaoHoaDonTaiQuay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTaoHoaDonTaiQuayMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTaoHoaDonTaiQuay, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTaoHoaDonTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(153, 204, 255));

        lblThanhToanTaiQuay.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        lblThanhToanTaiQuay.setForeground(new java.awt.Color(255, 255, 255));
        lblThanhToanTaiQuay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThanhToanTaiQuay.setIcon(new ImageIcon("src/main/images/credit-card.png"));
        lblThanhToanTaiQuay.setText("Thanh toán");
        lblThanhToanTaiQuay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThanhToanTaiQuayMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblThanhToanTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblThanhToanTaiQuay, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel19.setText("Tiền khách CK:");

        txtTienKhachCKTaiQuay.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTienKhachCKTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTienKhachCKTaiQuay.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTienKhachCKTaiQuay)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cboHTThanhToanTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTienThuaTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                    .addComponent(txtTienKhachDuaTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtThanhToanTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtGiamGiaTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTongTienTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTenNVTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtMaHDTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(34, 34, 34))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(txtMaHDTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel5))
                                                .addGap(26, 26, 26)
                                                .addComponent(jLabel6))
                                            .addComponent(txtTenNVTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(25, 25, 25)
                                        .addComponent(jLabel7))
                                    .addComponent(txtTongTienTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addComponent(jLabel8))
                            .addComponent(txtGiamGiaTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addComponent(jLabel9))
                    .addComponent(txtThanhToanTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboHTThanhToanTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTienKhachDuaTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(txtTienKhachCKTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTienThuaTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbpDonHang.addTab("Tại quầy", jPanel17);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setMaximumSize(new java.awt.Dimension(402, 697));
        jPanel18.setMinimumSize(new java.awt.Dimension(402, 697));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin người nhận", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 15))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel13.setText("Họ tên:");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel15.setText("SDT:");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel16.setText("Địa chỉ:");

        txtHoTenKHDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtHoTenKHDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtSDTKHDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtSDTKHDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtDiaChiDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtDiaChiDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtHoTenKHDatHang, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                        .addComponent(txtSDTKHDatHang))
                    .addComponent(txtDiaChiDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtHoTenKHDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel15))
                    .addComponent(txtSDTKHDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16))
                    .addComponent(txtDiaChiDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 4, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin người ship", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 15))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel17.setText("Họ tên:");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel18.setText("SDT:");

        txtTenNguoiShipDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTenNguoiShipDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtSDTNguoiShipDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtSDTNguoiShipDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSDTNguoiShipDatHang, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addComponent(txtTenNguoiShipDatHang))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtTenNguoiShipDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(txtSDTNguoiShipDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 15))); // NOI18N
        jPanel21.setMaximumSize(new java.awt.Dimension(369, 435));
        jPanel21.setMinimumSize(new java.awt.Dimension(369, 435));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel20.setText("Tổng tiền:");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel21.setText("Tên nhân viên:");

        txtTongTienDatHang.setEditable(false);
        txtTongTienDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTongTienDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtTenNVDatHang.setEditable(false);
        txtTenNVDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTenNVDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTenNVDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNVDatHangActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel22.setText("Mã HĐ:");

        txtMaHDDatHang.setEditable(false);
        txtMaHDDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtMaHDDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel23.setText("Giảm giá:");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel24.setText("Thanh toán:");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel25.setText("HT thanh toán:");

        cboHTThanhToanDatHang.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        cboHTThanhToanDatHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản", "Kết hợp" }));
        cboHTThanhToanDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHTThanhToanDatHangActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel26.setText("Tiền khách đưa:");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel27.setText("Tiền khách CK:");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel28.setText("Tiền thừa:");

        txtGiamGiaDatHang.setEditable(false);
        txtGiamGiaDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtGiamGiaDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtTienKhachDuaDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTienKhachDuaDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTienKhachDuaDatHang.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienKhachDuaDatHangCaretUpdate(evt);
            }
        });
        txtTienKhachDuaDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachDuaDatHangActionPerformed(evt);
            }
        });

        txtThanhToanDatHang.setEditable(false);
        txtThanhToanDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtThanhToanDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtTienKhachCKDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTienKhachCKDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTienKhachCKDatHang.setEnabled(false);

        txtTienThuaDatHang.setEditable(false);
        txtTienThuaDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTienThuaDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        btnTaoHoaDonDatHang.setBackground(new java.awt.Color(153, 204, 255));
        btnTaoHoaDonDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnTaoHoaDonDatHang.setText("Tạo hóa đơn");
        btnTaoHoaDonDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonDatHangActionPerformed(evt);
            }
        });

        btnDangGiaoDatHang.setBackground(new java.awt.Color(153, 204, 255));
        btnDangGiaoDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnDangGiaoDatHang.setText("Đang giao");
        btnDangGiaoDatHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDangGiaoDatHangMouseClicked(evt);
            }
        });
        btnDangGiaoDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangGiaoDatHangActionPerformed(evt);
            }
        });

        btnDaGiaoDatHang.setBackground(new java.awt.Color(153, 204, 255));
        btnDaGiaoDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnDaGiaoDatHang.setText("Đã giao");
        btnDaGiaoDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaGiaoDatHangActionPerformed(evt);
            }
        });

        txtTienShip.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTienShip.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTienShip.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienShipCaretUpdate(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel29.setText("Tiền ship:");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel28))
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                                .addComponent(btnTaoHoaDonDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenNVDatHang, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMaHDDatHang)
                            .addComponent(txtTongTienDatHang)
                            .addComponent(txtGiamGiaDatHang, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTienThuaDatHang)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                                .addComponent(btnDangGiaoDatHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDaGiaoDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(7, 7, 7))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(18, 18, 18)
                        .addComponent(txtTienKhachCKDatHang)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel25)
                            .addComponent(jLabel24)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtThanhToanDatHang)
                            .addComponent(cboHTThanhToanDatHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTienKhachDuaDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienShip))
                        .addGap(8, 8, 8))))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaHDDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtTenNVDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21)))
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(txtTongTienDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(txtGiamGiaDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)))
                .addGap(3, 3, 3)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTienShip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(3, 3, 3)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtThanhToanDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(5, 5, 5)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboHTThanhToanDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(5, 5, 5)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTienKhachDuaDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(5, 5, 5)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTienKhachCKDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGap(5, 5, 5)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTienThuaDatHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(8, 8, 8)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDaGiaoDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDangGiaoDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaoHoaDonDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbpDonHang.addTab("Đặt hàng", jPanel18);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(tbpDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 398, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quét mã vạch sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        pnlWebcam.setBackground(new java.awt.Color(255, 255, 255));
        pnlWebcam.setMaximumSize(new java.awt.Dimension(50, 50));
        pnlWebcam.setMinimumSize(new java.awt.Dimension(50, 50));
        pnlWebcam.setPreferredSize(new java.awt.Dimension(50, 50));
        pnlWebcam.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlWebcam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlWebcam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(14, 14, 14))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        try {
            int rowSanPham = tblSanPham.getSelectedRow();
            int rowHoaDon = tblHoaDonCho.getSelectedRow();
            if (rowHoaDon == -1) {
                JOptionPane.showMessageDialog(this, "Mời chọn hóa đơn");
                return;
            }
            BhHoaDonResponse bhHoaDonResponse = listHoaDon.get(rowHoaDon);
            BhChiTietSPResponse bhChiTietSPCheck = listSanPham.get(rowSanPham);
            BhHoaDonChiTietResponse hoaDonChiTietCheck = mapGioHang.get(bhChiTietSPCheck.getId());
            int soLuongThayDoi = 0;
            if (hoaDonChiTietCheck != null) {
                Object[] options = {"Thêm số lượng", "Giảm số lượng", "Hủy"};
                int result = JOptionPane.showOptionDialog(this,
                        "Sản phẩm này đã có trong giỏ hàng, bạn muốn làm gì?",
                        "Xác nhận",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[2]);
                if (result == 0 || result == 1) {
                    String soLuongThayDoiStr = JOptionPane.showInputDialog(this, "Nhập số lượng: ");
                    if (soLuongThayDoiStr == null) {
                        return;
                    }
                    try {
                        soLuongThayDoi = Integer.parseInt(soLuongThayDoiStr);
                        if (soLuongThayDoi < 0) {
                            JOptionPane.showMessageDialog(this, "Số lượng không thể âm");
                            return;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Số lượng phải là số");
                        return;
                    }
                    if (result == 0) {
                        if (soLuongThayDoi > bhChiTietSPCheck.getSoLuongTon() - hoaDonChiTietCheck.getSoLuong()) {
                            JOptionPane.showMessageDialog(this, "Không được vượt số lượng tồn");
                            return;
                        }
                        soLuongThayDoi = soLuongThayDoi;
                    } else {
                        if (soLuongThayDoi > hoaDonChiTietCheck.getSoLuong()) {
                            JOptionPane.showMessageDialog(this, "Số lượng sau khi giảm không được âm");
                            return;
                        }
                        if (soLuongThayDoi == hoaDonChiTietCheck.getSoLuong()) {
                            mapGioHang.remove(hoaDonChiTietCheck.getIdChiTietSP());
                            loadDataToTableHDCT(mapGioHang);
                            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                            hoaDonChiTiet.setId(hoaDonChiTietCheck.getIdHDCT());
                            banHangService.deleteHDCT(hoaDonChiTiet);
                        }
                        soLuongThayDoi = -soLuongThayDoi;
                    }
                }
            }
            if (hoaDonChiTietCheck == null) {
                String inputSoLuong = JOptionPane.showInputDialog(this, "Nhập số lượng", "0");
                if (inputSoLuong == null) {
                    return;
                }
                int soLuong;
                try {
                    soLuong = Integer.parseInt(inputSoLuong);
                    if (soLuong < 0 || soLuong > bhChiTietSPCheck.getSoLuongTon()) {
                        JOptionPane.showMessageDialog(this, "Số lượng không được âm, không lớn hơn số lượng tồn");
                        return;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải là số");
                    return;
                }
                if (soLuong == 0) {
                    return;
                }
                BhHoaDonChiTietResponse bhHoaDonChiTietResponse = new BhHoaDonChiTietResponse();
                bhHoaDonChiTietResponse.setIdChiTietSP(bhChiTietSPCheck.getId());
                bhHoaDonChiTietResponse.setIdHoaDon(bhHoaDonResponse.getId());
                bhHoaDonChiTietResponse.setMaSP(bhChiTietSPCheck.getMaSP());
                bhHoaDonChiTietResponse.setTenSP(bhChiTietSPCheck.getTenSP());
                bhHoaDonChiTietResponse.setSoLuong(soLuong);
                bhHoaDonChiTietResponse.setHang(bhChiTietSPCheck.getHang());
                bhHoaDonChiTietResponse.setMauSac(bhChiTietSPCheck.getMauSac());
                bhHoaDonChiTietResponse.setSize(bhChiTietSPCheck.getSize());
                bhHoaDonChiTietResponse.setSoLuongTon(bhChiTietSPCheck.getSoLuongTon());
                bhHoaDonChiTietResponse.setMaKhuyenMai(bhChiTietSPCheck.getMaKhuyeMai());
                bhHoaDonChiTietResponse.setLoaiKhuyenMai(bhChiTietSPCheck.getLoaiKhuyenMai());
                bhHoaDonChiTietResponse.setDonGia(bhChiTietSPCheck.getDonGia());
                bhHoaDonChiTietResponse.setGiamGia(bhChiTietSPCheck.getKhuyenMai());
                BigDecimal giaBan = new BigDecimal(0);
                BigDecimal giamGia = new BigDecimal(0);
                if (bhChiTietSPCheck.getMaKhuyeMai() == null) {
                    giaBan = bhChiTietSPCheck.getDonGia();
                    giamGia = new BigDecimal(0);
                } else {
                    if (bhChiTietSPCheck.getLoaiKhuyenMai() == 0) {
                        giamGia = bhChiTietSPCheck.getDonGia().multiply(new BigDecimal(bhChiTietSPCheck.getKhuyenMai()).divide(new BigDecimal(100)));
                        giaBan = bhChiTietSPCheck.getDonGia().subtract(giamGia);
                    } else {
                        giamGia = new BigDecimal(bhChiTietSPCheck.getKhuyenMai());
                        giaBan = bhChiTietSPCheck.getDonGia().subtract(giamGia);
                    }
                }
                bhHoaDonChiTietResponse.setGiaBan(giaBan);
                mapGioHang.put(bhHoaDonChiTietResponse.getIdChiTietSP(), bhHoaDonChiTietResponse);
                HoaDonChiTiet hoaDonChiTiet = banHangService.convertHoaDonChiTiet(bhHoaDonChiTietResponse);
                banHangService.saveOrUpdateHDCT(hoaDonChiTiet);
                bhHoaDonChiTietResponse.setIdHDCT(hoaDonChiTiet.getId());
                loadDataToTableHDCT(mapGioHang);
            } else {
                hoaDonChiTietCheck.setSoLuong(hoaDonChiTietCheck.getSoLuong() + soLuongThayDoi);
                if (hoaDonChiTietCheck.getSoLuong() != 0) {
                    mapGioHang.replace(hoaDonChiTietCheck.getIdChiTietSP(), hoaDonChiTietCheck);
                    loadDataToTableHDCT(mapGioHang);
                    HoaDonChiTiet hoaDonChiTiet = banHangService.convertHoaDonChiTiet(hoaDonChiTietCheck);
                    hoaDonChiTiet.setId(hoaDonChiTietCheck.getIdHDCT());
                    banHangService.saveOrUpdateHDCT(hoaDonChiTiet);
                }
            }
            tinhTongTien();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        try {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa hết sản phẩm khỏi giỏ hàng không?");
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            for (Map.Entry<String, BhHoaDonChiTietResponse> entry : mapGioHang.entrySet()) {
                BhHoaDonChiTietResponse value = entry.getValue();
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setId(value.getIdHDCT());
                mapGioHang.remove(value.getIdChiTietSP());
                loadDataToTableHDCT(mapGioHang);
                banHangService.deleteHDCT(hoaDonChiTiet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnChonKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKhachHangActionPerformed
        int index = tblKhachHangView.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Hãy chọn khách hàng");
            return;
        }
        BhKhachHangResponse bhKhachHangResponse = listKhachHang.get(index);
        txtMaKHTaiQuay.setText(bhKhachHangResponse.getMa());
        txtTenKHTaiQuay.setText(bhKhachHangResponse.getHoTen());
        int rowHoaDon = tblHoaDonCho.getSelectedRow();
        BhHoaDonResponse bhHoaDonResponse = listHoaDon.get(rowHoaDon);
        banHangService.updateKhachHangInHoaDon(bhHoaDonResponse.getId(), bhKhachHangResponse.getId());
        listHoaDon = banHangService.getAllResponseHD(nhanVien.getId());
        loadDataToHoaDon(listHoaDon);
        tblHoaDonCho.setRowSelectionInterval(0, 0);
        KhachHangView.setVisible(false);
    }//GEN-LAST:event_btnChonKhachHangActionPerformed

    private void tblHoaDonChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChoMouseClicked
        try {
            int row = tblHoaDonCho.getSelectedRow();
            BhHoaDonResponse bhHoaDonResponse = listHoaDon.get(row);
            if (bhHoaDonResponse.getHinhThucGiaoHang() == 0) {
                tbpDonHang.setSelectedIndex(0);
                txtMaHDTaiQuay.setText(bhHoaDonResponse.getMaHD());
                txtTenNVTaiQuay.setText(bhHoaDonResponse.getTenNhanVien());
                txtMaKHTaiQuay.setText(bhHoaDonResponse.getMaKhachHang());
                txtTenKHTaiQuay.setText(bhHoaDonResponse.getTenKhachHang());
            } else {
                tbpDonHang.setSelectedIndex(1);
                txtMaHDDatHang.setText(bhHoaDonResponse.getMaHD());
                txtTenNVDatHang.setText(bhHoaDonResponse.getTenNhanVien());
                txtHoTenKHDatHang.setText(bhHoaDonResponse.getTenNguoiNhan());
                txtSDTKHDatHang.setText(bhHoaDonResponse.getSdtNguoiNhan());
                txtDiaChiDatHang.setText(bhHoaDonResponse.getDiaChi());
                txtTenNguoiShipDatHang.setText(bhHoaDonResponse.getTenNguoiShip());
                txtSDTNguoiShipDatHang.setText(bhHoaDonResponse.getSdtNguoiShip());
                if (bhHoaDonResponse.getHinhThucThanhToan() != null) {
                    cboHTThanhToanDatHang.setSelectedIndex(bhHoaDonResponse.getHinhThucThanhToan());
                    if (cboHTThanhToanDatHang.getSelectedIndex() == 0) {
                        txtTienKhachDuaDatHang.setText(bhHoaDonResponse.getTienKhachDua() + "");
                    } else if (cboHTThanhToanDatHang.getSelectedIndex() == 1) {
                        txtTienKhachCKDatHang.setText(bhHoaDonResponse.getTienKhachCk() + "");
                    } else {
                        txtTienKhachDuaDatHang.setText(bhHoaDonResponse.getTienKhachDua() + "");
                        txtTienKhachCKDatHang.setText(bhHoaDonResponse.getTienKhachCk() + "");
                    }
                }
                if (bhHoaDonResponse.getTienShip() != null) {
                    txtTienShip.setText(bhHoaDonResponse.getTienShip() + "");
                }
            }
            List<BhHoaDonChiTietResponse> listHoaDonChiTietResponse = banHangService.getAllHDCTByIdHoaDon(bhHoaDonResponse.getId());
            if (listHoaDonChiTietResponse != null) {
                mapGioHang = banHangService.convertToHoaDonChiTietResponse(listHoaDonChiTietResponse);
                loadDataToTableHDCT(mapGioHang);
            }
            tinhTongTien();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblHoaDonChoMouseClicked

    private void tblGioHangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblGioHangKeyPressed

    }//GEN-LAST:event_tblGioHangKeyPressed

    private void tblGioHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblGioHangKeyReleased
        try {
            int row = tblGioHang.getSelectedRow();
            Integer soLuongCurrent = null;
            if (row >= 0) {
                soLuongCurrent = Integer.parseInt(tblGioHang.getValueAt(row, 6).toString());
            }
            BhHoaDonChiTietResponse bhHoaDonChiTietResponse = new ArrayList<>(mapGioHang.values()).get(row);
            if (soLuongCurrent > bhHoaDonChiTietResponse.getSoLuongTon()) {
                tblGioHang.setValueAt(soLuongCu, row, 6);
                JOptionPane.showMessageDialog(this, "Số lượng tồn không đủ");
                return;
            }
            if (soLuongCurrent < 0) {
                tblGioHang.setValueAt(soLuongCu, row, 6);
                JOptionPane.showMessageDialog(this, "Số lượng không được âm");
                return;
            }
            if (soLuongCurrent == 0) {
                mapGioHang.remove(bhHoaDonChiTietResponse.getIdChiTietSP());
                loadDataToTableHDCT(mapGioHang);
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setId(bhHoaDonChiTietResponse.getIdHDCT());
                banHangService.deleteHDCT(hoaDonChiTiet);
                return;
            }
            bhHoaDonChiTietResponse.setSoLuong(soLuongCurrent);
            HoaDonChiTiet hoaDonChiTiet = banHangService.convertHoaDonChiTiet(bhHoaDonChiTietResponse);
            hoaDonChiTiet.setId(bhHoaDonChiTietResponse.getIdHDCT());
            banHangService.saveOrUpdateHDCT(hoaDonChiTiet);
            mapGioHang.replace(bhHoaDonChiTietResponse.getIdChiTietSP(), bhHoaDonChiTietResponse);
            loadDataToTableHDCT(mapGioHang);
            tinhTongTien();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Số lượng phải là số");
        }
    }//GEN-LAST:event_tblGioHangKeyReleased

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed
        try {
            int rowHDCT = tblGioHang.getSelectedRow();
            if(rowHDCT == -1){
                JOptionPane.showMessageDialog(this, "Hãy chọn sản phẩm muốn xóa");
                return;
            }
            BhHoaDonChiTietResponse bhHoaDonChiTietResponse = new ArrayList<>(mapGioHang.values()).get(rowHDCT);
            mapGioHang.remove(bhHoaDonChiTietResponse.getIdChiTietSP());
            loadDataToTableHDCT(mapGioHang);
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setId(bhHoaDonChiTietResponse.getIdHDCT());
            banHangService.deleteHDCT(hoaDonChiTiet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoa1ActionPerformed

    private void btnTaoHoaDonDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonDatHangActionPerformed
        try {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMa("HD" + banHangService.genMaHoaDonTuDong());
            hoaDon.setNgayTao(new Date());
            hoaDon.setNhanVien(nhanVien);
            hoaDon.setHinhThucGiaoHang(1);
            hoaDon.setHinhThucThanhToan(1);
            hoaDon.setTrangThai(0);
            banHangService.saveOrUpdate(hoaDon);
            listHoaDon = banHangService.getAllResponseHD(nhanVien.getId());
            loadDataToHoaDon(listHoaDon);
            JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công");
            tblHoaDonCho.setRowSelectionInterval(0, 0);
            txtMaHDDatHang.setText(hoaDon.getMa());
            txtTenNVDatHang.setText(nhanVien.getTen());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTaoHoaDonDatHangActionPerformed

    private void txtTienKhachDuaDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachDuaDatHangActionPerformed

    }//GEN-LAST:event_txtTienKhachDuaDatHangActionPerformed

    private void btnChonTaiQuayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonTaiQuayActionPerformed
        KhachHangView.setVisible(true);
        KhachHangView.setLocationRelativeTo(null);
        listKhachHang = banHangService.getAllKhachHangResponse();
        loadDataToKhachHang(listKhachHang);
    }//GEN-LAST:event_btnChonTaiQuayActionPerformed

    private void lblTaoHoaDonTaiQuayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTaoHoaDonTaiQuayMouseClicked
        try {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMa("HD" + banHangService.genMaHoaDonTuDong());
            hoaDon.setNgayTao(new Date());
            hoaDon.setNhanVien(nhanVien);
            hoaDon.setHinhThucGiaoHang(0);
            hoaDon.setHinhThucThanhToan(0);
            hoaDon.setTrangThai(0);
            banHangService.saveOrUpdate(hoaDon);
            listHoaDon = banHangService.getAllResponseHD(nhanVien.getId());
            loadDataToHoaDon(listHoaDon);
            JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công");
            tblHoaDonCho.setRowSelectionInterval(0, 0);
            txtMaHDTaiQuay.setText(hoaDon.getMa());
            txtTenNVTaiQuay.setText(nhanVien.getTen());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lblTaoHoaDonTaiQuayMouseClicked

    private void cboHTThanhToanTaiQuayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHTThanhToanTaiQuayActionPerformed
        if (cboHTThanhToanTaiQuay.getSelectedIndex() == 0) {
            txtTienKhachDuaTaiQuay.setEnabled(true);
            txtTienKhachCKTaiQuay.setEnabled(false);
            txtTienKhachCKTaiQuay.setText("");
            txtTienThuaTaiQuay.setText("");
        }
        if (cboHTThanhToanTaiQuay.getSelectedIndex() == 1) {
            txtTienKhachDuaTaiQuay.setEnabled(false);
            txtTienKhachCKTaiQuay.setEnabled(true);
            txtTienKhachDuaTaiQuay.setText("");
            txtTienKhachCKTaiQuay.setText(txtThanhToanTaiQuay.getText());
            txtTienThuaTaiQuay.setText("0 Vnđ");
        }
        if (cboHTThanhToanTaiQuay.getSelectedIndex() == 2) {
            txtTienKhachDuaTaiQuay.setEnabled(true);
            txtTienKhachCKTaiQuay.setEnabled(true);
            txtTienKhachCKTaiQuay.setText("");
            txtTienThuaTaiQuay.setText("");
        }
    }//GEN-LAST:event_cboHTThanhToanTaiQuayActionPerformed

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        try {
            int row = tblGioHang.getSelectedRow();
            soLuongCu = Integer.parseInt(tblGioHang.getValueAt(row, 6).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void txtTienKhachDuaTaiQuayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTienKhachDuaTaiQuayMouseClicked

    }//GEN-LAST:event_txtTienKhachDuaTaiQuayMouseClicked

    private void txtTienKhachDuaTaiQuayCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienKhachDuaTaiQuayCaretUpdate
        try {
            if (txtTienKhachDuaTaiQuay.getText().trim().isEmpty()) {
                return;
            }
            BigDecimal tienKhachDua = new BigDecimal(txtTienKhachDuaTaiQuay.getText().trim());
            if (tienKhachDua.compareTo(BigDecimal.ZERO) == -1) {
                JOptionPane.showMessageDialog(this, "Tiền khách đưa phải > 0");
                return;
            }
            tinhTongTien();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Tiền khách đưa phải là số");
        }
    }//GEN-LAST:event_txtTienKhachDuaTaiQuayCaretUpdate

    private void clearForm() {
        mapGioHang.clear();
        loadDataToTableHDCT(mapGioHang);
        txtMaKHTaiQuay.setText("");
        txtTenKHTaiQuay.setText("");
        txtMaHDTaiQuay.setText("");
        txtTongTienTaiQuay.setText("");
        txtTienKhachDuaTaiQuay.setText("");
        txtTienKhachCKTaiQuay.setText("");
        txtGiamGiaTaiQuay.setText("");
        txtTienThuaTaiQuay.setText("");
        txtTenNVTaiQuay.setText("");
        txtThanhToanTaiQuay.setText("");
    }

    private void clearFormDatHang() {
        mapGioHang.clear();
        loadDataToTableHDCT(mapGioHang);
        txtHoTenKHDatHang.setText("");
        txtSDTKHDatHang.setText("");
        txtDiaChiDatHang.setText("");
        txtTenNguoiShipDatHang.setText("");
        txtSDTNguoiShipDatHang.setText("");
        txtTongTienDatHang.setText("");
        txtMaHDDatHang.setText("");
        txtTenNVDatHang.setText("");
        txtGiamGiaDatHang.setText("");
        txtThanhToanDatHang.setText("");
        txtTienKhachCKDatHang.setText("");
        txtTienKhachDuaDatHang.setText("");
        txtTienThuaDatHang.setText("");
    }

    private void lblThanhToanTaiQuayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThanhToanTaiQuayMouseClicked
        try {
            if (cboHTThanhToanTaiQuay.getSelectedIndex() == 0 && txtTienKhachDuaTaiQuay.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Hãy nhập tiền khách đưa");
                return;
            }
            String arrayCheck[] = txtTienThuaTaiQuay.getText().trim().split(" ");
            if (new BigDecimal(arrayCheck[0].replace(",", "")).compareTo(BigDecimal.ZERO) == -1) {
                JOptionPane.showMessageDialog(this, "Tiền khách đưa không đủ");
                return;
            }
            int rowHoaDon = tblHoaDonCho.getSelectedRow();
            BhHoaDonResponse bhHoaDonResponse = listHoaDon.get(rowHoaDon);
            if (bhHoaDonResponse.getHinhThucGiaoHang() == 0) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setId(bhHoaDonResponse.getId());
                hoaDon.setMa(bhHoaDonResponse.getMaHD());
                hoaDon.setNgayTao(bhHoaDonResponse.getNgayTao());
                hoaDon.setHinhThucGiaoHang(0);
                hoaDon.setHinhThucThanhToan(cboHTThanhToanTaiQuay.getSelectedIndex());
                KhachHang khachHang = null;
                if (!txtMaKHTaiQuay.getText().trim().isEmpty()) {
                    khachHang = banHangService.findByMaKhachHang(txtMaKHTaiQuay.getText().trim());
                }
                hoaDon.setKhachHang(khachHang);
                hoaDon.setTrangThai(2);
                hoaDon.setNhanVien(nhanVien);
                hoaDon.setNgayThanhToan(new Date());
                String array[] = txtThanhToanTaiQuay.getText().trim().split(" ");
                hoaDon.setThanhTien(new BigDecimal(array[0].replace(",", "")));
                if (cboHTThanhToanTaiQuay.getSelectedIndex() == 0) {
                    String arrayTKT[] = txtTienKhachDuaTaiQuay.getText().trim().split(" ");
                    hoaDon.setTienKhachTra(new BigDecimal(arrayTKT[0].replace(",", "")));
                } else if (cboHTThanhToanTaiQuay.getSelectedIndex() == 1) {
                    String arrayTKCK[] = txtTienKhachCKTaiQuay.getText().trim().split(" ");
                    hoaDon.setTienKhachChuyenKhoan(new BigDecimal(arrayTKCK[0].replace(",", "")));
                } else {
                    String arrayTKT[] = txtTienKhachDuaTaiQuay.getText().trim().split(" ");
                    hoaDon.setTienKhachTra(new BigDecimal(arrayTKT[0].replace(",", "")));
                    String arrayTKCK[] = txtTienKhachCKTaiQuay.getText().trim().split(" ");
                    hoaDon.setTienKhachChuyenKhoan(new BigDecimal(arrayTKCK[0].replace(",", "")));
                }
                String arrayTT[] = txtTienThuaTaiQuay.getText().trim().split(" ");
                hoaDon.setTienThua(new BigDecimal(arrayTT[0].replace(",", "")));
                banHangService.saveOrUpdate(hoaDon);
                banHangService.updateSoLuong(mapGioHang);
                listSanPham = banHangService.getAllChiTietSP();
                loadDataToTableSP(listSanPham);
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId());
                loadDataToHoaDon(listHoaDon);
                JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn không?");
                if(confirm != JOptionPane.YES_OPTION){
                    return;
                }
                ExportFilePdf exportFilePdf = new ExportFilePdf();
                exportFilePdf.WriteInvoice(hoaDon, new ArrayList<>(mapGioHang.values()));
                JOptionPane.showMessageDialog(this, "In hóa đơn thành công");
                clearForm();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lblThanhToanTaiQuayMouseClicked

    private void cboHTThanhToanDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHTThanhToanDatHangActionPerformed
        if (cboHTThanhToanDatHang.getSelectedIndex() == 0) {
            txtTienKhachDuaDatHang.setEnabled(true);
            txtTienKhachCKDatHang.setEnabled(false);
            txtTienKhachCKDatHang.setText("");
            txtTienThuaDatHang.setText("");
        }
        if (cboHTThanhToanDatHang.getSelectedIndex() == 1) {
            txtTienKhachDuaDatHang.setEnabled(false);
            txtTienKhachCKDatHang.setEnabled(true);
            txtTienKhachDuaDatHang.setText("");
            txtTienKhachCKDatHang.setText(txtThanhToanDatHang.getText());
            txtTienThuaDatHang.setText("0 Vnđ");
        }
        if (cboHTThanhToanDatHang.getSelectedIndex() == 2) {
            txtTienKhachDuaDatHang.setEnabled(true);
            txtTienKhachCKDatHang.setEnabled(true);
            txtTienKhachCKDatHang.setText("");
        }
    }//GEN-LAST:event_cboHTThanhToanDatHangActionPerformed

    private void btnDangGiaoDatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangGiaoDatHangMouseClicked

    }//GEN-LAST:event_btnDangGiaoDatHangMouseClicked

    private void btnDangGiaoDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangGiaoDatHangActionPerformed
        try {
            String tenNguoiNhan = txtHoTenKHDatHang.getText().trim();
            String sdtNguoiNhan = txtSDTKHDatHang.getText().trim();
            String diaChi = txtDiaChiDatHang.getText().trim();
            String tenNguoiShip = txtTenNguoiShipDatHang.getText().trim();
            String sdtNguoiShip = txtSDTNguoiShipDatHang.getText().trim();
            if (tenNguoiNhan.isEmpty() || sdtNguoiNhan.isEmpty() || diaChi.isEmpty()
                    || tenNguoiShip.isEmpty() || sdtNguoiShip.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Hãy nhập đầy đủ thông tin của người nhận và người ship");
                return;
            }
            if (txtTienShip.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Hãy nhập tiền ship");
                return;
            }
            if (cboHTThanhToanDatHang.getSelectedIndex() == 0 && txtTienKhachDuaDatHang.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Hãy nhập tiền khách đưa");
                return;
            }
            String arrayCheck[] = txtTienThuaDatHang.getText().trim().split(" ");
            if (new BigDecimal(arrayCheck[0].replace(",", "")).compareTo(BigDecimal.ZERO) == -1) {
                JOptionPane.showMessageDialog(this, "Tiền khách đưa không đủ");
                return;
            }
            int rowHoaDon = tblHoaDonCho.getSelectedRow();
            BhHoaDonResponse bhHoaDonResponse = listHoaDon.get(rowHoaDon);
            if (bhHoaDonResponse.getHinhThucGiaoHang() == 1) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setId(bhHoaDonResponse.getId());
                hoaDon.setMa(bhHoaDonResponse.getMaHD());
                hoaDon.setNgayTao(bhHoaDonResponse.getNgayTao());
                hoaDon.setHinhThucGiaoHang(1);
                hoaDon.setHinhThucThanhToan(cboHTThanhToanDatHang.getSelectedIndex());
                hoaDon.setTrangThai(3);
                hoaDon.setTenNguoiNhan(tenNguoiNhan);
                hoaDon.setSdtNguoiNhan(sdtNguoiNhan);
                hoaDon.setDiaChi(diaChi);
                hoaDon.setTenNguoiShip(tenNguoiShip);
                hoaDon.setSdtNguoiShip(sdtNguoiShip);
                hoaDon.setNhanVien(nhanVien);
                hoaDon.setNgayThanhToan(new Date());
                hoaDon.setNgayShip(new Date());
                hoaDon.setTienShip(new BigDecimal(txtTienShip.getText().trim()));
                String array[] = txtThanhToanDatHang.getText().trim().split(" ");
                hoaDon.setThanhTien(new BigDecimal(array[0].replace(",", "")));
                if (cboHTThanhToanDatHang.getSelectedIndex() == 0) {
                    String arrayTKT[] = txtTienKhachDuaDatHang.getText().trim().split(" ");
                    hoaDon.setTienKhachTra(new BigDecimal(arrayTKT[0].replace(",", "")));
                } else if (cboHTThanhToanDatHang.getSelectedIndex() == 1) {
                    String arrayTKCK[] = txtTienKhachCKDatHang.getText().trim().split(" ");
                    hoaDon.setTienKhachChuyenKhoan(new BigDecimal(arrayTKCK[0].replace(",", "")));
                } else {
                    String arrayTKT[] = txtTienKhachDuaDatHang.getText().trim().split(" ");
                    hoaDon.setTienKhachTra(new BigDecimal(arrayTKT[0].replace(",", "")));
                    String arrayTKCK[] = txtTienKhachCKDatHang.getText().trim().split(" ");
                    hoaDon.setTienKhachChuyenKhoan(new BigDecimal(arrayTKCK[0].replace(",", "")));
                }
                String arrayTT[] = txtTienThuaDatHang.getText().trim().split(" ");
                hoaDon.setTienThua(new BigDecimal(arrayTT[0].replace(",", "")));
                banHangService.saveOrUpdate(hoaDon);
                banHangService.updateSoLuong(mapGioHang);
                listSanPham = banHangService.getAllChiTietSP();
                loadDataToTableSP(listSanPham);
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId());
                loadDataToHoaDon(listHoaDon);
                clearFormDatHang();
                JOptionPane.showMessageDialog(this, "Thanh toán thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDangGiaoDatHangActionPerformed

    private void txtTenNVDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNVDatHangActionPerformed

    }//GEN-LAST:event_txtTenNVDatHangActionPerformed

    private void txtTienShipCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienShipCaretUpdate
        try {
            tinhTongTien();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtTienShipCaretUpdate

    private void txtTienKhachDuaDatHangCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienKhachDuaDatHangCaretUpdate
        try {
            if (txtTienKhachDuaDatHang.getText().trim().isEmpty()) {
                return;
            }
            BigDecimal tienKhachDua = new BigDecimal(txtTienKhachDuaDatHang.getText().trim());
            if (tienKhachDua.compareTo(BigDecimal.ZERO) == -1) {
                JOptionPane.showMessageDialog(this, "Tiền khách đưa phải > 0");
                return;
            }
            tinhTongTien();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Tiền khách đưa phải là số");
        }
    }//GEN-LAST:event_txtTienKhachDuaDatHangCaretUpdate

    private void btnDaGiaoDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaGiaoDatHangActionPerformed
        try {
            int rowHoaDon = tblHoaDonCho.getSelectedRow();
            BhHoaDonResponse bhHoaDonResponse = listHoaDon.get(rowHoaDon);
            if (bhHoaDonResponse.getHinhThucGiaoHang() == 1 && bhHoaDonResponse.getTrangThai() == 3) {
                HoaDon hoaDon = banHangService.findByIdHoaDon(bhHoaDonResponse.getId());
                hoaDon.setId(bhHoaDonResponse.getId());
                hoaDon.setNgayNhan(new Date());
                hoaDon.setTrangThai(4);
                banHangService.saveOrUpdate(hoaDon);
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId());
                loadDataToHoaDon(listHoaDon);
                clearFormDatHang();
                JOptionPane.showMessageDialog(this, "Cập nhật hóa đơn thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Hóa đơn không phù hợp để sử dụng chức năng này");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDaGiaoDatHangActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame KhachHangView;
    private javax.swing.JFrame btnChonKH;
    private javax.swing.JButton btnChonKhachHang;
    private javax.swing.JButton btnChonTaiQuay;
    private javax.swing.JButton btnDaGiaoDatHang;
    private javax.swing.JButton btnDangGiaoDatHang;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnTaoHoaDonDatHang;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoa1;
    private javax.swing.JComboBox<String> cboHTThanhToanDatHang;
    private javax.swing.JComboBox<String> cboHTThanhToanTaiQuay;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JPanel jPanel21;
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
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblTaoHoaDonTaiQuay;
    private javax.swing.JLabel lblThanhToanTaiQuay;
    private javax.swing.JPanel pnlWebcam;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDonCho;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblKhachHangView;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTabbedPane tbpDonHang;
    private javax.swing.JTextField txtDiaChiDatHang;
    private javax.swing.JTextField txtGiamGiaDatHang;
    private javax.swing.JTextField txtGiamGiaTaiQuay;
    private javax.swing.JTextField txtHoTenKHDatHang;
    private javax.swing.JTextField txtMaHDDatHang;
    private javax.swing.JTextField txtMaHDTaiQuay;
    private javax.swing.JTextField txtMaKHTaiQuay;
    private javax.swing.JTextField txtSDTKHDatHang;
    private javax.swing.JTextField txtSDTNguoiShipDatHang;
    private javax.swing.JTextField txtTenKHTaiQuay;
    private javax.swing.JTextField txtTenNVDatHang;
    private javax.swing.JTextField txtTenNVTaiQuay;
    private javax.swing.JTextField txtTenNguoiShipDatHang;
    private javax.swing.JTextField txtThanhToanDatHang;
    private javax.swing.JTextField txtThanhToanTaiQuay;
    private javax.swing.JTextField txtTienKhachCKDatHang;
    private javax.swing.JTextField txtTienKhachCKTaiQuay;
    private javax.swing.JTextField txtTienKhachDuaDatHang;
    private javax.swing.JTextField txtTienKhachDuaTaiQuay;
    private javax.swing.JTextField txtTienShip;
    private javax.swing.JTextField txtTienThuaDatHang;
    private javax.swing.JTextField txtTienThuaTaiQuay;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTienDatHang;
    private javax.swing.JTextField txtTongTienTaiQuay;
    // End of variables declaration//GEN-END:variables

    private void tinhTongTien() {
        try {
            int size = tblGioHang.getRowCount();
            BigDecimal tong = new BigDecimal(0);
            BigDecimal giamGia = new BigDecimal(0);
            for (int i = 0; i < size; i++) {
                BigDecimal moneySale = new BigDecimal(0);
                BigDecimal money = new BigDecimal(tblGioHang.getValueAt(i, 5).toString()).multiply(new BigDecimal(tblGioHang.getValueAt(i, 6).toString()));
                if (!tblGioHang.getValueAt(i, 7).toString().contains(" ")) {
                    if (tblGioHang.getValueAt(i, 7).equals("0") || Integer.parseInt(tblGioHang.getValueAt(i, 7).toString()) == 0) {
                        moneySale = new BigDecimal(0);
                    }
                } else {
                    String array[] = tblGioHang.getValueAt(i, 7).toString().split(" ");
                    if (array[1].equals("%")) {
                        moneySale = (new BigDecimal(tblGioHang.getValueAt(i, 5).toString()).multiply(new BigDecimal(tblGioHang.getValueAt(i, 6).toString()))).multiply(new BigDecimal(array[0]).divide(new BigDecimal(100)));
                    } else {
                        moneySale = new BigDecimal(array[0]).multiply(new BigDecimal(tblGioHang.getValueAt(i, 6).toString()));
                    }
                }
                tong = tong.add(money);
                giamGia = giamGia.add(moneySale);
            }
            int rowHoaDon = tblHoaDonCho.getSelectedRow();
            if (rowHoaDon == -1) {
                return;
            }
            BhHoaDonResponse bhHoaDonResponse = listHoaDon.get(rowHoaDon);
            if (bhHoaDonResponse.getHinhThucGiaoHang() == 0) {
                DecimalFormat df = new DecimalFormat("#,###");
                txtTongTienTaiQuay.setText(df.format(tong) + " Vnđ");
                txtGiamGiaTaiQuay.setText(df.format(giamGia) + " Vnđ");
                BigDecimal tienCanTra = tong.subtract(giamGia);
                txtThanhToanTaiQuay.setText(df.format(tienCanTra) + " Vnđ");
                if (cboHTThanhToanTaiQuay.getSelectedIndex() == 1) {
                    txtTienKhachCKTaiQuay.setText(txtThanhToanTaiQuay.getText());
                    return;
                }
                if (txtTienKhachDuaTaiQuay.getText().trim().isEmpty()) {
                    return;
                }
                BigDecimal tienKhachDua = new BigDecimal(txtTienKhachDuaTaiQuay.getText().trim());
                BigDecimal tienThua = null;
                tienThua = tienKhachDua.subtract(tienCanTra);
                if (tienThua.compareTo(BigDecimal.ZERO) == -1) {
                    txtTienThuaTaiQuay.setText(tienThua + " Vnđ");
                } else {
                    txtTienThuaTaiQuay.setText(df.format(tienThua) + " Vnđ");
                }
            } else {
                try {
                    if (!txtTienShip.getText().trim().isEmpty()) {
                        Double.parseDouble(txtTienShip.getText().trim());
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Tiền ship phải là số");
                    txtTienShip.setText("0");
                    return;
                }
                BigDecimal tienShip = new BigDecimal(0);
                if (!txtTienShip.getText().trim().isEmpty()) {
                    tienShip = new BigDecimal(txtTienShip.getText().trim());
                }
                DecimalFormat df = new DecimalFormat("#,###");
                txtTongTienDatHang.setText(df.format(tong) + " Vnđ");
                txtGiamGiaDatHang.setText(df.format(giamGia) + " Vnđ");
                BigDecimal tienCanTra = tong.subtract(giamGia).subtract(tienShip);
                txtThanhToanDatHang.setText(df.format(tienCanTra) + " Vnđ");
                if (cboHTThanhToanDatHang.getSelectedIndex() == 1) {
                    txtTienKhachCKDatHang.setText(txtThanhToanDatHang.getText());
                    return;
                }
                if (txtTienKhachDuaDatHang.getText().trim().isEmpty()) {
                    return;
                }
                BigDecimal tienKhachDua = new BigDecimal(txtTienKhachDuaDatHang.getText().trim());
                BigDecimal tienThua = null;
                tienThua = tienKhachDua.subtract(tienCanTra);
                if (tienThua.compareTo(BigDecimal.ZERO) == -1) {
                    txtTienThuaDatHang.setText(tienThua + " Vnđ");
                } else {
                    txtTienThuaDatHang.setText(df.format(tienThua) + " Vnđ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
