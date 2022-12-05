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
import core.quanly.viewmodel.BhNhanVienResponse;
import domainmodels.ChiTietSP;
import domainmodels.ChiTietSPKhuyenMai;
import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import domainmodels.KhachHang;
import domainmodels.NhanVien;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
import util.ExportFilePdfByITextDatHang;
import util.ExportFilePdfByITextTaiQuay;

/**
 *
 * @author thangncph26123
 */
public class ViewBanHang extends javax.swing.JPanel implements Runnable, ThreadFactory {

    private final DefaultTableModel modelSanPham;
    private final DefaultTableModel modelGioHang;
    private final DefaultTableModel modelHoaDon;
    private final DefaultTableModel modelKhachHang;
    private final DefaultTableModel modelNhanVien;
    private List<BhChiTietSPResponse> listSanPham;
    private List<BhHoaDonResponse> listHoaDon;
    private List<BhKhachHangResponse> listKhachHang;
    private List<BhNhanVienResponse> listNhanVien;
    private final BanHangService banHangService;
    private Map<String, BhHoaDonChiTietResponse> mapGioHang;
    private NhanVien nhanVien;
    private int soLuongCu;
    private boolean selected;
    private int countTbpTaiQuay = 0;
    private int countTbpDatHang = 0;
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
        modelNhanVien = (DefaultTableModel) tblNhanVienShip.getModel();
        banHangService = new BanHangServiceImpl();
        listSanPham = new ArrayList<>();
        listKhachHang = new ArrayList<>();
        listNhanVien = new ArrayList<>();
        listHoaDon = new ArrayList<>();
        mapGioHang = new HashMap<>();
        listSanPham = banHangService.getAllChiTietSP();
        listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 0, 0);
        setSelected(0, 0);
        loadDataToTableSP(listSanPham);
        loadDataToHoaDon(listHoaDon);
        cboHinhThucThanhToan.setVisible(false);
    }

    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam
        webcam.setViewSize(size);
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        pnlWebcam.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 231, 173));

        executor.execute(this);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(1000);
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
//                    int rowHoaDon = tblHoaDonCho.getSelectedRow();
//                    if (rowHoaDon == -1) {
//                        JOptionPane.showMessageDialog(this, "Mời chọn hóa đơn");
//                        continue;
//                    }
                    int rowHoaDon = tblHoaDonCho.getSelectedRow();
                    if (rowHoaDon >= 0) {
                        BhHoaDonResponse bhHoaDonClick = listHoaDon.get(rowHoaDon);
                        if (bhHoaDonClick.getTrangThai() != 0) {
                            clearForm();
                        }
                    }
                    rowHoaDon = tblHoaDonCho.getSelectedRow();
                    if (rowHoaDon == -1) {
                        if (tbpDonHang.getSelectedIndex() == 0) {
                            taoHoaDonTaiQuay();
                        } else {
                            taoHoaDonDatHang();
                        }
                    }
                    rowHoaDon = tblHoaDonCho.getSelectedRow();
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
                                    HoaDonChiTiet hoaDonChiTiet = banHangService.findByIdHoaDonChiTiet(hoaDonChiTietCheck.getIdHDCT());
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
                        bhHoaDonChiTietResponse.setMaCTSP(bhChiTietSPCheck.getMaCTSP());
                        bhHoaDonChiTietResponse.setMaSP(bhChiTietSPCheck.getMaSP());
                        bhHoaDonChiTietResponse.setTenSP(bhChiTietSPCheck.getTenSP());
                        bhHoaDonChiTietResponse.setSoLuong(soLuong);
                        bhHoaDonChiTietResponse.setHang(bhChiTietSPCheck.getHang());
                        bhHoaDonChiTietResponse.setMauSac(bhChiTietSPCheck.getMauSac());
                        bhHoaDonChiTietResponse.setSize(bhChiTietSPCheck.getSize());
                        bhHoaDonChiTietResponse.setSoLuongTon(bhChiTietSPCheck.getSoLuongTon());
                        bhHoaDonChiTietResponse.setDonGia(bhChiTietSPCheck.getDonGia());
                        ChiTietSPKhuyenMai chiTietSPKhuyenMai = banHangService.getCTSPKhuyenMai(bhChiTietSPCheck.getId());
                        if (chiTietSPKhuyenMai != null) {
                            bhHoaDonChiTietResponse.setGiaBan(chiTietSPKhuyenMai.getDonGiaConLai());
                            bhHoaDonChiTietResponse.setGiamGia(bhChiTietSPCheck.getDonGia().subtract(chiTietSPKhuyenMai.getDonGiaConLai()));
                        } else {
                            bhHoaDonChiTietResponse.setGiaBan(bhChiTietSPCheck.getDonGia());
                            bhHoaDonChiTietResponse.setGiamGia(new BigDecimal(0));
                        }
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
            BigDecimal giaBan = new BigDecimal(0);
            ChiTietSPKhuyenMai chiTietSPKhuyenMai = banHangService.getCTSPKhuyenMai(xx.getId());
            if (chiTietSPKhuyenMai != null) {
                giaBan = chiTietSPKhuyenMai.getDonGiaConLai();
            } else {
                giaBan = xx.getDonGia();
            }
            modelSanPham.addRow(new Object[]{xx.getMaCTSP(), xx.getTenSP(), xx.getMauSac(), xx.getSize(), xx.getHang(), xx.getSoLuongTon(), xx.getDonGia() + " Vnđ", giaBan + " Vnđ"});
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

    private void loadDataToNhanVien(List<BhNhanVienResponse> list) {
        modelNhanVien.setRowCount(0);
        for (BhNhanVienResponse xx : list) {
            modelNhanVien.addRow(xx.toDataRow());
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
        jTextField1 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        txtTenKHAdd = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        txtSdtAdd = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jTextField11 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtNgaySinhAdd = new com.github.lgooddatepicker.components.DatePicker();
        txtEmailKh = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        NhanVienView = new javax.swing.JFrame();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblNhanVienShip = new javax.swing.JTable();
        jLabel35 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cboMauSac = new javax.swing.JComboBox<>();
        cboSize = new javax.swing.JComboBox<>();
        cboHang = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnXoa = new javax.swing.JButton();
        btnXoa1 = new javax.swing.JButton();
        chkTatCa = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDonCho = new javax.swing.JTable();
        cboTrangThaiHoaDon = new javax.swing.JComboBox<>();
        cboHinhThucThanhToan = new javax.swing.JComboBox<>();
        btnClear = new javax.swing.JButton();
        cboHinhThucGiaoHang = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        tbpDonHang = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaKHTaiQuay = new javax.swing.JTextField();
        txtTenKHTaiQuay = new javax.swing.JTextField();
        btnChonTaiQuay = new javax.swing.JButton();
        lblCapBac = new javax.swing.JLabel();
        lblTenCapBac = new javax.swing.JLabel();
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
        jLabel19 = new javax.swing.JLabel();
        txtTienKhachCKTaiQuay = new javax.swing.JTextField();
        btnThanhToanTaiQuay = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtHoTenKHDatHang = new javax.swing.JTextField();
        txtSDTKHDatHang = new javax.swing.JTextField();
        txtDiaChiDatHang = new javax.swing.JTextField();
        btnChonKhachHangDatHang = new javax.swing.JButton();
        txtTenNguoiShipDatHang = new javax.swing.JTextField();
        txtSDTNguoiShipDatHang = new javax.swing.JTextField();
        btnChonNhanVienShip = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
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
        btnDangGiaoDatHang = new javax.swing.JButton();
        btnDaGiaoDatHang = new javax.swing.JButton();
        txtTienShip = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        btnThanhToanDatHang = new javax.swing.JButton();
        btnChoGiaoHang = new javax.swing.JButton();
        txtNgayMongMuon = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel34 = new javax.swing.JLabel();
        cboTrangThaiThanhToan = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
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
        btnChonKhachHang.setBackground(new java.awt.Color(153, 204, 255));
        btnChonKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKhachHangActionPerformed(evt);
            }
        });

        jTextField1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel31.setText("Tìm kiếm:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(38, 38, 38)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 105, Short.MAX_VALUE))
                    .addComponent(jScrollPane5))
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(btnChonKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnChonKhachHang)
                .addContainerGap())
        );

        jTabbedPane4.addTab("Danh sách khách hàng", jPanel9);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        txtTenKHAdd.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jTextField5.setText("Tên KH:");
        jTextField5.setBorder(null);

        txtSdtAdd.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jTextField7.setText("SĐT:");
        jTextField7.setBorder(null);

        jTextField8.setText("Ngày sinh:");
        jTextField8.setBorder(null);

        jTextField10.setText("Giới tính:");
        jTextField10.setBorder(null);

        buttonGroup2.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");
        rdoNam.setBackground(new java.awt.Color(255, 255, 255));

        buttonGroup2.add(rdoNu);
        rdoNu.setText("Nữ");
        rdoNu.setBackground(new java.awt.Color(255, 255, 255));

        jTextField11.setText("Địa chỉ:");
        jTextField11.setBorder(null);

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane6.setViewportView(txtDiaChi);

        jButton2.setText("Thêm");
        jButton2.setBackground(new java.awt.Color(153, 204, 255));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Sửa");
        jButton3.setBackground(new java.awt.Color(153, 204, 255));

        txtEmailKh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jTextField6.setText("Email: ");
        jTextField6.setBorder(null);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(txtTenKHAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jButton2)
                                .addGap(97, 97, 97)
                                .addComponent(jButton3))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtNgaySinhAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmailKh, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSdtAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTenKHAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEmailKh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSdtAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgaySinhAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addGap(28, 28, 28)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Thiết lập thông tin khách hàng", jPanel13);

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

        NhanVienView.setTitle("Nhân viên ship hàng");
        NhanVienView.setBackground(new java.awt.Color(255, 255, 255));
        NhanVienView.setMinimumSize(new java.awt.Dimension(522, 519));
        NhanVienView.setSize(new java.awt.Dimension(522, 519));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setMaximumSize(new java.awt.Dimension(522, 519));
        jPanel7.setMinimumSize(new java.awt.Dimension(522, 519));

        jLabel18.setText("Chọn nhân viên ship hàng");
        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 204, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel6.setMaximumSize(new java.awt.Dimension(494, 444));
        jPanel6.setMinimumSize(new java.awt.Dimension(494, 444));

        tblNhanVienShip.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Tên NV", "Số điện thoại", "Email", "Địa chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tblNhanVienShip);
        if (tblNhanVienShip.getColumnModel().getColumnCount() > 0) {
            tblNhanVienShip.getColumnModel().getColumn(0).setPreferredWidth(45);
            tblNhanVienShip.getColumnModel().getColumn(1).setPreferredWidth(60);
            tblNhanVienShip.getColumnModel().getColumn(2).setHeaderValue("Giới tính");
        }

        jLabel35.setText("Tìm kiếm:");

        jTextField9.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jButton6.setText("Chọn");
        jButton6.setBackground(new java.awt.Color(153, 204, 255));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
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
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel35)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout NhanVienViewLayout = new javax.swing.GroupLayout(NhanVienView.getContentPane());
        NhanVienView.getContentPane().setLayout(NhanVienViewLayout);
        NhanVienViewLayout.setHorizontalGroup(
            NhanVienViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhanVienViewLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        NhanVienViewLayout.setVerticalGroup(
            NhanVienViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhanVienViewLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1292, 784));
        setMinimumSize(new java.awt.Dimension(1292, 784));
        setPreferredSize(new java.awt.Dimension(1292, 784));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(849, 324));
        jPanel1.setMinimumSize(new java.awt.Dimension(849, 324));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Màu sắc", "Size", "Hãng", "SL tồn", "Đơn giá", "Giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setRowHeight(25);
        tblSanPham.setSelectionBackground(new java.awt.Color(86, 154, 222));
        tblSanPham.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblSanPham.getTableHeader().setReorderingAllowed(false);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);
        if (tblSanPham.getColumnModel().getColumnCount() > 0) {
            tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(70);
            tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblSanPham.getColumnModel().getColumn(2).setPreferredWidth(40);
            tblSanPham.getColumnModel().getColumn(3).setPreferredWidth(20);
            tblSanPham.getColumnModel().getColumn(4).setPreferredWidth(30);
            tblSanPham.getColumnModel().getColumn(5).setPreferredWidth(30);
            tblSanPham.getColumnModel().getColumn(6).setPreferredWidth(100);
        }

        txtTimKiem.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel14.setIcon(new ImageIcon("src/main/images/search.png"));

        cboMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Black", "White" }));
        cboMauSac.setBackground(new java.awt.Color(153, 204, 255));

        cboSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "39", "40", "41" }));
        cboSize.setBackground(new java.awt.Color(153, 204, 255));

        cboHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nike", "Adidas", "Vans" }));
        cboHang.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setText("Màu sắc:");

        jLabel2.setText("Size:");

        jLabel30.setText("Hãng:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTimKiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboHang, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addGap(7, 7, 7))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel30)
                        .addComponent(cboHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Đơn giá", "Số lượng", "Giảm giá", "Giá bán", "Thành tiền", "Chọn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGioHang.setRowHeight(25);
        tblGioHang.setSelectionBackground(new java.awt.Color(86, 154, 222));
        tblGioHang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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
            tblGioHang.getColumnModel().getColumn(0).setPreferredWidth(35);
            tblGioHang.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblGioHang.getColumnModel().getColumn(2).setPreferredWidth(45);
            tblGioHang.getColumnModel().getColumn(3).setPreferredWidth(25);
            tblGioHang.getColumnModel().getColumn(5).setPreferredWidth(45);
            tblGioHang.getColumnModel().getColumn(7).setPreferredWidth(25);
        }

        btnXoa.setIcon(new ImageIcon("src/main/images/trash.png"));
        btnXoa.setBackground(new java.awt.Color(153, 204, 255));
        btnXoa.setToolTipText("Xóa tất cả");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnXoa1.setIcon(new ImageIcon("src/main/images/trash.png"));
        btnXoa1.setBackground(new java.awt.Color(153, 204, 255));
        btnXoa1.setToolTipText("Xóa sản phẩm");
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });

        chkTatCa.setText("Tất cả");
        chkTatCa.setBackground(new java.awt.Color(255, 255, 255));
        chkTatCa.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTatCaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(chkTatCa)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(chkTatCa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(btnXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

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
        tblHoaDonCho.setRowHeight(25);
        tblHoaDonCho.setSelectionBackground(new java.awt.Color(86, 154, 222));
        tblHoaDonCho.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblHoaDonCho.getTableHeader().setReorderingAllowed(false);
        tblHoaDonCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDonCho);

        cboTrangThaiHoaDon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chờ thanh toán", "Đã thanh toán", "Tất cả" }));
        cboTrangThaiHoaDon.setBackground(new java.awt.Color(153, 204, 255));
        cboTrangThaiHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiHoaDonActionPerformed(evt);
            }
        });

        cboHinhThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thanh toán trước", "Thanh toán khi nhận", "Tất cả" }));
        cboHinhThucThanhToan.setBackground(new java.awt.Color(153, 204, 255));

        btnClear.setIcon(new ImageIcon("src/main/images/clear.png"));
        btnClear.setBackground(new java.awt.Color(153, 204, 255));
        btnClear.setToolTipText("Clear hóa đơn");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        cboHinhThucGiaoHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tại quầy", "Đặt hàng", "Tất cả" }));
        cboHinhThucGiaoHang.setBackground(new java.awt.Color(153, 204, 255));
        cboHinhThucGiaoHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHinhThucGiaoHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cboHinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTrangThaiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboHinhThucGiaoHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTrangThaiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboHinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboHinhThucGiaoHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N
        jPanel4.setMaximumSize(new java.awt.Dimension(415, 28));
        jPanel4.setMinimumSize(new java.awt.Dimension(415, 28));

        tbpDonHang.setMaximumSize(new java.awt.Dimension(407, 728));
        tbpDonHang.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tbpDonHangStateChanged(evt);
            }
        });
        tbpDonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbpDonHangMouseClicked(evt);
            }
        });

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 15))); // NOI18N

        jLabel3.setText("Mã KH:");
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        jLabel4.setText("Tên KH:");
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        txtMaKHTaiQuay.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtMaKHTaiQuay.setText("KH000");
        txtMaKHTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtTenKHTaiQuay.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTenKHTaiQuay.setText("Khách bán lẻ");
        txtTenKHTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        btnChonTaiQuay.setIcon(new ImageIcon("src/main/images/choice.png"));
        btnChonTaiQuay.setBackground(new java.awt.Color(153, 204, 255));
        btnChonTaiQuay.setToolTipText("Chọn khách hàng");
        btnChonTaiQuay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonTaiQuayActionPerformed(evt);
            }
        });

        lblCapBac.setIcon(new ImageIcon("src/main/images/kimcuong.png"));
        lblCapBac.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblCapBac.setToolTipText("Cấp bậc hiện tại của khách hàng");

        lblTenCapBac.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        lblTenCapBac.setForeground(new java.awt.Color(51, 153, 255));

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
                        .addComponent(txtMaKHTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCapBac, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTenCapBac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtTenKHTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChonTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel3))
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMaKHTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblCapBac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblTenCapBac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTenKHTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(btnChonTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 15))); // NOI18N

        jLabel5.setText("Mã hóa đơn:");
        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        jLabel6.setText("Tên nhân viên:");
        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        jLabel7.setText("Tổng tiền:");
        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        jLabel8.setText("Giảm giá KM:");
        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        jLabel9.setText("Thanh toán:");
        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        jLabel10.setText("Tiền khách đưa:");
        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        jLabel11.setText("Tiền thừa:");
        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        jLabel12.setText("HT thanh toán:");
        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

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

        cboHTThanhToanTaiQuay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản", "Kết hợp" }));
        cboHTThanhToanTaiQuay.setBackground(new java.awt.Color(153, 204, 255));
        cboHTThanhToanTaiQuay.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cboHTThanhToanTaiQuay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHTThanhToanTaiQuayActionPerformed(evt);
            }
        });

        jLabel19.setText("Tiền khách CK:");
        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        txtTienKhachCKTaiQuay.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTienKhachCKTaiQuay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTienKhachCKTaiQuay.setEnabled(false);

        btnThanhToanTaiQuay.setIcon(new ImageIcon("src/main/images/credit-card.png"));
        btnThanhToanTaiQuay.setBackground(new java.awt.Color(153, 204, 255));
        btnThanhToanTaiQuay.setToolTipText("Thanh toán");
        btnThanhToanTaiQuay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanTaiQuayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtTienKhachCKTaiQuay)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cboHTThanhToanTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTienThuaTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(txtTienKhachDuaTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtThanhToanTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtGiamGiaTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTenNVTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMaHDTaiQuay, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTongTienTaiQuay))))
                .addGap(34, 34, 34))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThanhToanTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148))
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
                .addComponent(btnThanhToanTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        tbpDonHang.addTab("Tại quầy", jPanel17);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setMaximumSize(new java.awt.Dimension(402, 697));
        jPanel18.setMinimumSize(new java.awt.Dimension(402, 697));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin người nhận và người ship", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 15))); // NOI18N

        jLabel13.setText("Ng/nhận:");
        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        jLabel15.setText("SDT:");
        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        jLabel16.setText("Địa chỉ:");
        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        txtHoTenKHDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtHoTenKHDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtSDTKHDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtSDTKHDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtDiaChiDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtDiaChiDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        btnChonKhachHangDatHang.setIcon(new ImageIcon("src/main/images/choice.png"));
        btnChonKhachHangDatHang.setBackground(new java.awt.Color(153, 204, 255));
        btnChonKhachHangDatHang.setToolTipText("Chọn khách hàng");
        btnChonKhachHangDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKhachHangDatHangActionPerformed(evt);
            }
        });

        txtTenNguoiShipDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTenNguoiShipDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtSDTNguoiShipDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtSDTNguoiShipDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        btnChonNhanVienShip.setIcon(new ImageIcon("src/main/images/choice.png"));
        btnChonNhanVienShip.setBackground(new java.awt.Color(153, 204, 255));
        btnChonNhanVienShip.setMargin(new java.awt.Insets(2, 7, 2, 7));
        btnChonNhanVienShip.setMaximumSize(new java.awt.Dimension(44, 42));
        btnChonNhanVienShip.setMinimumSize(new java.awt.Dimension(44, 42));
        btnChonNhanVienShip.setPreferredSize(new java.awt.Dimension(44, 42));
        btnChonNhanVienShip.setToolTipText("Chọn nhân viên ship hàng");
        btnChonNhanVienShip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonNhanVienShipActionPerformed(evt);
            }
        });

        jLabel32.setText("Ng/ship:");
        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        jLabel33.setText("SDT:");
        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiaChiDatHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(txtSDTKHDatHang, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtHoTenKHDatHang, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTenNguoiShipDatHang)
                    .addComponent(txtSDTNguoiShipDatHang))
                .addGap(28, 28, 28)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnChonNhanVienShip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonKhachHangDatHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnChonKhachHangDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(btnChonNhanVienShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addComponent(jLabel13)
                            .addGap(12, 12, 12)
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel33))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel19Layout.createSequentialGroup()
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel32)
                                .addGroup(jPanel19Layout.createSequentialGroup()
                                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel19Layout.createSequentialGroup()
                                            .addComponent(txtHoTenKHDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(30, 30, 30))
                                        .addComponent(txtSDTKHDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtDiaChiDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(txtTenNguoiShipDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, 0)
                            .addComponent(txtSDTNguoiShipDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 4, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 15))); // NOI18N
        jPanel21.setMaximumSize(new java.awt.Dimension(374, 497));
        jPanel21.setMinimumSize(new java.awt.Dimension(374, 497));

        jLabel20.setText("Tổng tiền:");
        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        jLabel21.setText("Tên nhân viên:");
        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

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

        jLabel22.setText("Mã HĐ:");
        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        txtMaHDDatHang.setEditable(false);
        txtMaHDDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtMaHDDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel23.setText("Giảm giá:");
        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        jLabel24.setText("Thanh toán:");
        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        jLabel25.setText("HT thanh toán:");
        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        cboHTThanhToanDatHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản", "Kết hợp" }));
        cboHTThanhToanDatHang.setSelectedIndex(1);
        cboHTThanhToanDatHang.setBackground(new java.awt.Color(153, 204, 255));
        cboHTThanhToanDatHang.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        cboHTThanhToanDatHang.setToolTipText("");
        cboHTThanhToanDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHTThanhToanDatHangActionPerformed(evt);
            }
        });

        jLabel26.setText("Tiền khách đưa:");
        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        jLabel27.setText("Tiền khách CK:");
        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        jLabel28.setText("Tiền thừa:");
        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        txtGiamGiaDatHang.setEditable(false);
        txtGiamGiaDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtGiamGiaDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtTienKhachDuaDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTienKhachDuaDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTienKhachDuaDatHang.setEnabled(false);
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

        txtTienThuaDatHang.setEditable(false);
        txtTienThuaDatHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTienThuaDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        btnDangGiaoDatHang.setIcon(new ImageIcon("src/main/images/danggiao.png"));
        btnDangGiaoDatHang.setBackground(new java.awt.Color(153, 204, 255));
        btnDangGiaoDatHang.setToolTipText("Đang giao");
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

        btnDaGiaoDatHang.setIcon(new ImageIcon("src/main/images/dagiao.png"));
        btnDaGiaoDatHang.setBackground(new java.awt.Color(153, 204, 255));
        btnDaGiaoDatHang.setToolTipText("Đã giao");
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

        jLabel29.setText("Tiền ship:");
        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        btnThanhToanDatHang.setIcon(new ImageIcon("src/main/images/credit-card.png"));
        btnThanhToanDatHang.setBackground(new java.awt.Color(153, 204, 255));
        btnThanhToanDatHang.setToolTipText("Thanh toán");
        btnThanhToanDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanDatHangActionPerformed(evt);
            }
        });

        btnChoGiaoHang.setIcon(new ImageIcon("src/main/images/wait.png"));
        btnChoGiaoHang.setBackground(new java.awt.Color(153, 204, 255));
        btnChoGiaoHang.setToolTipText("Chờ giao hàng");
        btnChoGiaoHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChoGiaoHangActionPerformed(evt);
            }
        });

        txtNgayMongMuon.setBackground(new java.awt.Color(255, 255, 255));

        jLabel34.setText("Trạng thái thanh toán:");
        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        cboTrangThaiThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thanh toán trước", "Thanh toán khi nhận" }));
        cboTrangThaiThanhToan.setBackground(new java.awt.Color(153, 204, 255));
        cboTrangThaiThanhToan.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        cboTrangThaiThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiThanhToanActionPerformed(evt);
            }
        });

        jLabel17.setText("Mong muốn:");
        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addComponent(cboTrangThaiThanhToan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnThanhToanDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnChoGiaoHang, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)
                        .addComponent(btnDangGiaoDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDaGiaoDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel21)
                            .addComponent(jLabel20)
                            .addComponent(jLabel23)
                            .addComponent(jLabel26)
                            .addComponent(jLabel25)
                            .addComponent(jLabel24)
                            .addComponent(jLabel29)
                            .addComponent(jLabel28)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtThanhToanDatHang)
                            .addComponent(txtTienShip)
                            .addComponent(txtGiamGiaDatHang)
                            .addComponent(txtTongTienDatHang)
                            .addComponent(txtTenNVDatHang)
                            .addComponent(txtMaHDDatHang)
                            .addComponent(cboHTThanhToanDatHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTienKhachDuaDatHang)
                            .addComponent(txtTienKhachCKDatHang)
                            .addComponent(txtTienThuaDatHang))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtNgayMongMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23)))
                .addGap(3, 3, 3)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTienShip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(3, 3, 3)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtThanhToanDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtNgayMongMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboTrangThaiThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34)))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel17)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThanhToanDatHang, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(btnDangGiaoDatHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDaGiaoDatHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChoGiaoHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbpDonHang.addTab("Đặt hàng", jPanel18);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
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
            .addComponent(pnlWebcam, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
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
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        try {
            int rowHoaDon = tblHoaDonCho.getSelectedRow();
            if (rowHoaDon >= 0) {
                BhHoaDonResponse bhHoaDonClick = listHoaDon.get(rowHoaDon);
                if (bhHoaDonClick.getTrangThai() != 0) {
                    clearForm();
                }
            }
            rowHoaDon = tblHoaDonCho.getSelectedRow();
            if (rowHoaDon == -1) {
                if (tbpDonHang.getSelectedIndex() == 0) {
                    taoHoaDonTaiQuay();
                } else {
                    taoHoaDonDatHang();
                }
            }
            int rowSanPham = tblSanPham.getSelectedRow();
            rowHoaDon = tblHoaDonCho.getSelectedRow();
            
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
                            HoaDonChiTiet hoaDonChiTiet = banHangService.findByIdHoaDonChiTiet(hoaDonChiTietCheck.getIdHDCT());
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
                bhHoaDonChiTietResponse.setMaCTSP(bhChiTietSPCheck.getMaCTSP());
                bhHoaDonChiTietResponse.setMaSP(bhChiTietSPCheck.getMaSP());
                bhHoaDonChiTietResponse.setTenSP(bhChiTietSPCheck.getTenSP());
                bhHoaDonChiTietResponse.setSoLuong(soLuong);
                bhHoaDonChiTietResponse.setHang(bhChiTietSPCheck.getHang());
                bhHoaDonChiTietResponse.setMauSac(bhChiTietSPCheck.getMauSac());
                bhHoaDonChiTietResponse.setSize(bhChiTietSPCheck.getSize());
                bhHoaDonChiTietResponse.setSoLuongTon(bhChiTietSPCheck.getSoLuongTon());
                bhHoaDonChiTietResponse.setDonGia(bhChiTietSPCheck.getDonGia());
                ChiTietSPKhuyenMai chiTietSPKhuyenMai = banHangService.getCTSPKhuyenMai(bhChiTietSPCheck.getId());
                if (chiTietSPKhuyenMai != null) {
                    bhHoaDonChiTietResponse.setGiaBan(chiTietSPKhuyenMai.getDonGiaConLai());
                    bhHoaDonChiTietResponse.setGiamGia(bhChiTietSPCheck.getDonGia().subtract(chiTietSPKhuyenMai.getDonGiaConLai()));
                } else {
                    bhHoaDonChiTietResponse.setGiaBan(bhChiTietSPCheck.getDonGia());
                    bhHoaDonChiTietResponse.setGiamGia(new BigDecimal(0));
                }
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
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa tất cả sản phẩm khỏi giỏ hàng không?");
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            for (Map.Entry<String, BhHoaDonChiTietResponse> entry : mapGioHang.entrySet()) {
                BhHoaDonChiTietResponse value = entry.getValue();
                HoaDonChiTiet hoaDonChiTiet = banHangService.findByIdHoaDonChiTiet(value.getIdHDCT());
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
        if (tbpDonHang.getSelectedIndex() == 0) {
            txtMaKHTaiQuay.setText(bhKhachHangResponse.getMa());
            txtTenKHTaiQuay.setText(bhKhachHangResponse.getHoTen());
            if (bhKhachHangResponse.getCapBac() == 0) {
                lblCapBac.setIcon(new ImageIcon("src/main/images/dong.png"));
                lblCapBac.setText("");
                lblTenCapBac.setText("Đồng");
            }
            if (bhKhachHangResponse.getCapBac() == 1) {
                lblCapBac.setIcon(new ImageIcon("src/main/images/bac.png"));
                lblCapBac.setText("");
                lblTenCapBac.setText("Bạc");
            }
            if (bhKhachHangResponse.getCapBac() == 2) {
                lblCapBac.setIcon(new ImageIcon("src/main/images/vang.png"));
                lblCapBac.setText("");
                lblTenCapBac.setText("Vàng");
            }
            if (bhKhachHangResponse.getCapBac() == 3) {
                lblCapBac.setIcon(new ImageIcon("src/main/images/kimcuong.png"));
                lblCapBac.setText("");
                lblTenCapBac.setText("Kim cương");
            }
            tinhTongTien();
        } else {
            txtHoTenKHDatHang.setText(bhKhachHangResponse.getHoTen());
            txtSDTKHDatHang.setText(bhKhachHangResponse.getSdt());
            txtDiaChiDatHang.setText(bhKhachHangResponse.getDiaChi());
        }
        int rowHoaDon = tblHoaDonCho.getSelectedRow();
        if (rowHoaDon >= 0) {
            BhHoaDonResponse bhHoaDonResponse = listHoaDon.get(rowHoaDon);
            banHangService.updateKhachHangInHoaDon(bhHoaDonResponse.getId(), bhKhachHangResponse.getId());
            if (bhHoaDonResponse.getHinhThucGiaoHang() == 0) {
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 0, 0);
            } else {
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 1, 0);
            }
            loadDataToHoaDon(listHoaDon);
            tblHoaDonCho.setRowSelectionInterval(0, 0);
        }
        KhachHangView.setVisible(false);
    }//GEN-LAST:event_btnChonKhachHangActionPerformed

    private void tblHoaDonChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChoMouseClicked
        try {
            int row = tblHoaDonCho.getSelectedRow();
            BhHoaDonResponse bhHoaDonResponse = listHoaDon.get(row);
            if (bhHoaDonResponse.getHinhThucGiaoHang() == 0) {
                if (bhHoaDonResponse.getTrangThai() != 0) {
                    btnXoa.setEnabled(false);
                    btnXoa1.setEnabled(false);
                    btnChonTaiQuay.setEnabled(false);
                    btnThanhToanTaiQuay.setEnabled(false);
                    chkTatCa.setEnabled(false);
                } else {
                    btnXoa.setEnabled(true);
                    btnXoa1.setEnabled(true);
                    btnChonTaiQuay.setEnabled(true);
                    btnThanhToanTaiQuay.setEnabled(true);
                    chkTatCa.setEnabled(true);
                }
            }
            if (bhHoaDonResponse.getHinhThucGiaoHang() == 0) {
                tbpDonHang.setSelectedIndex(0);
                if (bhHoaDonResponse.getTrangThai() == 0) {
                    txtMaHDTaiQuay.setText(bhHoaDonResponse.getMaHD());
                    txtTenNVTaiQuay.setText(bhHoaDonResponse.getTenNhanVien());
                    txtMaKHTaiQuay.setText(bhHoaDonResponse.getMaKhachHang());
                    txtTenKHTaiQuay.setText(bhHoaDonResponse.getTenKhachHang());
                } else {
                    txtMaHDTaiQuay.setText(bhHoaDonResponse.getMaHD());
                    txtTenNVTaiQuay.setText(bhHoaDonResponse.getTenNhanVien());
                    txtMaKHTaiQuay.setText(bhHoaDonResponse.getMaKhachHang());
                    txtTenKHTaiQuay.setText(bhHoaDonResponse.getTenKhachHang());
                    cboHTThanhToanTaiQuay.setSelectedIndex(bhHoaDonResponse.getHinhThucThanhToan());
                    if (bhHoaDonResponse.getHinhThucThanhToan() == 0) {
                        txtTienKhachDuaTaiQuay.setText(bhHoaDonResponse.getTienKhachDua() + "");
                    } else if (bhHoaDonResponse.getHinhThucThanhToan() == 1) {
                        txtTienKhachCKTaiQuay.setText(bhHoaDonResponse.getTienKhachCk() + "");
                    } else {
                        txtTienKhachDuaTaiQuay.setText(bhHoaDonResponse.getTienKhachDua() + "");
                        txtTienKhachCKTaiQuay.setText(bhHoaDonResponse.getTienKhachCk() + "");
                    }
                    txtTienThuaTaiQuay.setText(bhHoaDonResponse.getTienThua() + "");
                }
            } else {
                tbpDonHang.setSelectedIndex(1);
                txtMaHDDatHang.setText(bhHoaDonResponse.getMaHD());
                txtTenNVDatHang.setText(bhHoaDonResponse.getTenNhanVien());
                txtHoTenKHDatHang.setText(bhHoaDonResponse.getTenNguoiNhan());
                txtSDTKHDatHang.setText(bhHoaDonResponse.getSdtNguoiNhan());
                txtDiaChiDatHang.setText(bhHoaDonResponse.getDiaChi());
                txtTenNguoiShipDatHang.setText(bhHoaDonResponse.getTenNguoiShip());
                txtSDTNguoiShipDatHang.setText(bhHoaDonResponse.getSdtNguoiShip());
                Date ngayMongMuon = bhHoaDonResponse.getNgayMongMuon();
                if (ngayMongMuon != null) {
                    Instant instantNgayMongMuon = ngayMongMuon.toInstant();
                    LocalDateTime localDateTime = LocalDateTime.ofInstant(instantNgayMongMuon, ZoneId.systemDefault());
                    txtNgayMongMuon.setDateTimePermissive(localDateTime);
                }
                if (bhHoaDonResponse.getTrangThaiThanhToan() != null) {
                    cboTrangThaiThanhToan.setSelectedIndex(bhHoaDonResponse.getTrangThaiThanhToan());
                }
                if (bhHoaDonResponse.getTrangThaiThanhToan() != null) {
                    if (bhHoaDonResponse.getTrangThaiThanhToan() == 0) {
                        if (bhHoaDonResponse.getTrangThai() == 0) {

                        } else {
                            if (bhHoaDonResponse.getHinhThucThanhToan() == 0) {
                                txtTienKhachDuaDatHang.setEnabled(true);
                                txtTienKhachCKDatHang.setEnabled(false);
                                txtTienKhachDuaDatHang.setText(bhHoaDonResponse.getTienKhachDua() + "");
                                txtTienThuaDatHang.setText(bhHoaDonResponse.getTienThua() + "");
                            } else if (bhHoaDonResponse.getHinhThucThanhToan() == 1) {
                                txtTienKhachDuaDatHang.setEnabled(false);
                                txtTienKhachCKDatHang.setEnabled(true);
                                txtTienKhachCKDatHang.setText(bhHoaDonResponse.getTienKhachCk() + "");
                                txtTienThuaDatHang.setText(bhHoaDonResponse.getTienThua() + "");
                            } else {
                                txtTienKhachDuaDatHang.setEnabled(true);
                                txtTienKhachCKDatHang.setEnabled(true);
                                txtTienKhachDuaDatHang.setText(bhHoaDonResponse.getTienKhachDua() + "");
                                txtTienKhachCKDatHang.setText(bhHoaDonResponse.getTienKhachCk() + "");
                                txtTienThuaDatHang.setText(bhHoaDonResponse.getTienThua() + "");
                            }
                        }
                    } else {
                        if (bhHoaDonResponse.getTrangThai() != 5) {
                            txtTienKhachDuaDatHang.setText("");
                            txtTienKhachCKDatHang.setText("");
                            txtTienThuaDatHang.setText("");
                            cboHinhThucThanhToan.setSelectedIndex(0);
                            txtTienKhachCKDatHang.setEnabled(false);
                        } else {
                            if (bhHoaDonResponse.getHinhThucThanhToan() == 0) {
                                txtTienKhachDuaDatHang.setEnabled(true);
                                txtTienKhachCKDatHang.setEnabled(false);
                                txtTienKhachDuaDatHang.setText(bhHoaDonResponse.getTienKhachDua() + "");
                                txtTienThuaDatHang.setText(bhHoaDonResponse.getTienThua() + "");
                            } else if (bhHoaDonResponse.getHinhThucThanhToan() == 1) {
                                txtTienKhachDuaDatHang.setEnabled(false);
                                txtTienKhachCKDatHang.setEnabled(true);
                                txtTienKhachCKDatHang.setText(bhHoaDonResponse.getTienKhachCk() + "");
                                txtTienThuaDatHang.setText(bhHoaDonResponse.getTienThua() + "");
                            } else {
                                txtTienKhachDuaDatHang.setEnabled(true);
                                txtTienKhachCKDatHang.setEnabled(true);
                                txtTienKhachDuaDatHang.setText(bhHoaDonResponse.getTienKhachDua() + "");
                                txtTienKhachCKDatHang.setText(bhHoaDonResponse.getTienKhachCk() + "");
                                txtTienThuaDatHang.setText(bhHoaDonResponse.getTienThua() + "");
                            }
                        }
                    }
                }
                if (bhHoaDonResponse.getHinhThucThanhToan() != null) {
                    cboHTThanhToanDatHang.setSelectedIndex(bhHoaDonResponse.getHinhThucThanhToan());
                    if (cboHTThanhToanDatHang.getSelectedIndex() == 0) {
                        if (bhHoaDonResponse.getTienKhachDua() != null) {
                            txtTienKhachDuaDatHang.setText(bhHoaDonResponse.getTienKhachDua() + "");
                        }
                    } else if (cboHTThanhToanDatHang.getSelectedIndex() == 1) {
                        if (bhHoaDonResponse.getTienKhachCk() != null) {
                            txtTienKhachCKDatHang.setText(bhHoaDonResponse.getTienKhachCk() + "");
                        }
                    } else {
                        if (bhHoaDonResponse.getTienKhachDua() != null && bhHoaDonResponse.getTienKhachCk() != null) {
                            txtTienKhachDuaDatHang.setText(bhHoaDonResponse.getTienKhachDua() + "");
                            txtTienKhachCKDatHang.setText(bhHoaDonResponse.getTienKhachCk() + "");
                        }
                    }
                }
                if (bhHoaDonResponse.getTrangThai() == 0) {
                    btnThanhToanDatHang.setEnabled(true);
                    btnChoGiaoHang.setEnabled(false);
                    btnDangGiaoDatHang.setEnabled(false);
                    btnDaGiaoDatHang.setEnabled(false);
                    btnChonKhachHangDatHang.setEnabled(true);
                    btnChonNhanVienShip.setEnabled(true);
                    btnXoa.setEnabled(true);
                    btnXoa1.setEnabled(true);
                }
                if (bhHoaDonResponse.getTrangThai() == 2) {
                    btnThanhToanDatHang.setEnabled(false);
                    btnChoGiaoHang.setEnabled(true);
                    btnDangGiaoDatHang.setEnabled(false);
                    btnDaGiaoDatHang.setEnabled(false);
                    btnChonKhachHangDatHang.setEnabled(true);
                    btnChonNhanVienShip.setEnabled(true);
                    btnXoa.setEnabled(true);
                    btnXoa1.setEnabled(true);
                }
                if (bhHoaDonResponse.getTrangThai() == 3) {
                    btnThanhToanDatHang.setEnabled(false);
                    btnChoGiaoHang.setEnabled(false);
                    btnDangGiaoDatHang.setEnabled(true);
                    btnDaGiaoDatHang.setEnabled(false);
                    btnChonKhachHangDatHang.setEnabled(true);
                    btnChonNhanVienShip.setEnabled(true);
                    btnXoa.setEnabled(true);
                    btnXoa1.setEnabled(true);
                }
                if (bhHoaDonResponse.getTrangThai() == 4) {
                    btnThanhToanDatHang.setEnabled(false);
                    btnChoGiaoHang.setEnabled(false);
                    btnDangGiaoDatHang.setEnabled(false);
                    btnDaGiaoDatHang.setEnabled(true);
                    btnChonKhachHangDatHang.setEnabled(false);
                    btnChonNhanVienShip.setEnabled(false);
                    btnXoa.setEnabled(false);
                    btnXoa1.setEnabled(false);
                }
                if (bhHoaDonResponse.getTrangThai() == 5) {
                    btnThanhToanDatHang.setEnabled(false);
                    btnChoGiaoHang.setEnabled(false);
                    btnDangGiaoDatHang.setEnabled(false);
                    btnDaGiaoDatHang.setEnabled(false);
                    btnChonKhachHangDatHang.setEnabled(false);
                    btnChonNhanVienShip.setEnabled(false);
                    btnXoa.setEnabled(false);
                    btnXoa1.setEnabled(false);
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
            try {
                row = tblGioHang.getSelectedRow();
                if (row >= 0) {
                    soLuongCurrent = Integer.parseInt(tblGioHang.getValueAt(row, 3).toString());
                    if (soLuongCurrent < 0) {
                        tblGioHang.setValueAt(soLuongCu, row, 3);
                        JOptionPane.showMessageDialog(this, "Số lượng không được âm");
                        return;
                    }
                }
            } catch (Exception e) {
                tblGioHang.setValueAt(soLuongCu, row, 3);
                JOptionPane.showMessageDialog(this, "Số lượng phải là số");
                return;
            }
            BhHoaDonChiTietResponse bhHoaDonChiTietResponse = new BhHoaDonChiTietResponse();
            if (row >= 0) {
                bhHoaDonChiTietResponse = new ArrayList<>(mapGioHang.values()).get(row);
            }
            if (soLuongCurrent > bhHoaDonChiTietResponse.getSoLuongTon()) {
                tblGioHang.setValueAt(soLuongCu, row, 3);
                JOptionPane.showMessageDialog(this, "Số lượng tồn không đủ");
                return;
            }
            if (soLuongCurrent < 0) {
                tblGioHang.setValueAt(soLuongCu, row, 3);
                JOptionPane.showMessageDialog(this, "Số lượng không được âm");
                return;
            }
            if (soLuongCurrent == 0) {
                mapGioHang.remove(bhHoaDonChiTietResponse.getIdChiTietSP());
                loadDataToTableHDCT(mapGioHang);
                HoaDonChiTiet hoaDonChiTiet = banHangService.findByIdHoaDonChiTiet(bhHoaDonChiTietResponse.getIdHDCT());
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
        }
    }//GEN-LAST:event_tblGioHangKeyReleased

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed
        try {
            Map<String, BhHoaDonChiTietResponse> mapSelected = getMapSelected();
            if (getMapSelected().size() == 0) {
                JOptionPane.showMessageDialog(this, "Hãy chọn sản phẩm muốn xóa");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa các sản phẩm đã lựa chọn không?");
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            for (Map.Entry<String, BhHoaDonChiTietResponse> entry : mapSelected.entrySet()) {
                BhHoaDonChiTietResponse value = entry.getValue();
                mapGioHang.remove(value.getIdChiTietSP());
                HoaDonChiTiet hoaDonChiTiet = banHangService.findByIdHoaDonChiTiet(value.getIdHDCT());
                banHangService.deleteHDCT(hoaDonChiTiet);
            }
            loadDataToTableHDCT(mapGioHang);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoa1ActionPerformed

    private void txtTienKhachDuaDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachDuaDatHangActionPerformed

    }//GEN-LAST:event_txtTienKhachDuaDatHangActionPerformed

    private void btnChonTaiQuayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonTaiQuayActionPerformed
        try {
            KhachHangView.setVisible(true);
            KhachHangView.setLocationRelativeTo(null);
            listKhachHang = banHangService.getAllKhachHangResponse();
            loadDataToKhachHang(listKhachHang);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnChonTaiQuayActionPerformed

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
            soLuongCu = Integer.parseInt(tblGioHang.getValueAt(row, 3).toString());
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
        loadDataToHoaDon(listHoaDon);
        mapGioHang.clear();
        loadDataToTableHDCT(mapGioHang);
        cboHTThanhToanTaiQuay.setSelectedIndex(0);
        txtMaKHTaiQuay.setText("KH000");
        txtTenKHTaiQuay.setText("Khách bán lẻ");
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
        txtTienShip.setText("");
        txtThanhToanDatHang.setText("");
        txtTienKhachCKDatHang.setText("");
        txtTienKhachDuaDatHang.setText("");
        txtTienThuaDatHang.setText("");
    }

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

            int rowHoaDon = tblHoaDonCho.getSelectedRow();
            BhHoaDonResponse bhHoaDonResponse = listHoaDon.get(rowHoaDon);
            if (bhHoaDonResponse.getHinhThucGiaoHang() == 1) {
                HoaDon hoaDon = banHangService.findByIdHoaDon(bhHoaDonResponse.getId());
                hoaDon.setTrangThai(4);
                hoaDon.setTenNguoiNhan(tenNguoiNhan);
                hoaDon.setSdtNguoiNhan(sdtNguoiNhan);
                hoaDon.setDiaChi(diaChi);
                hoaDon.setTenNguoiShip(tenNguoiShip);
                hoaDon.setSdtNguoiShip(sdtNguoiShip);
                String array[] = txtThanhToanDatHang.getText().trim().split(" ");
                hoaDon.setThanhTien(new BigDecimal(array[0].replace(",", "")));
                hoaDon.setNhanVien(nhanVien);
                hoaDon.setNgayShip(new Date());
                hoaDon.setTienShip(new BigDecimal(txtTienShip.getText().trim()));

                banHangService.saveOrUpdate(hoaDon);
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 1, 4);
                setSelected(1, 3);
                loadDataToHoaDon(listHoaDon);
                tblHoaDonCho.setRowSelectionInterval(0, 0);
//                JOptionPane.showMessageDialog(this, "Thanh toán thành công");
//                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn không?");
//                if (confirm == JOptionPane.YES_OPTION) {
//                    try {
//                        ExportFilePdfByITextDatHang export = new ExportFilePdfByITextDatHang();
//                        export.exportBill(hoaDon, new ArrayList<>(mapGioHang.values()));
//                        JOptionPane.showMessageDialog(this, "In hóa đơn thành công");
//                    } catch (Exception e) {
//                        JOptionPane.showMessageDialog(this, "In hóa đơn thất bại");
//                    }
//                    clearFormDatHang();
//                } else {
//                    clearFormDatHang();
//                }
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDangGiaoDatHangActionPerformed

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
            if (bhHoaDonResponse.getHinhThucGiaoHang() == 1 && bhHoaDonResponse.getTrangThai() == 4) {
                HoaDon hoaDon = banHangService.findByIdHoaDon(bhHoaDonResponse.getId());
                if (hoaDon.getTrangThaiThanhToan() == 1) {
                    if (cboHTThanhToanDatHang.getSelectedIndex() == 0 && txtTienKhachDuaDatHang.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Hãy nhập tiền khách đưa");
                        return;
                    }
                    String arrayCheck[] = txtTienThuaDatHang.getText().trim().split(" ");
                    if (new BigDecimal(arrayCheck[0].replace(",", "")).compareTo(BigDecimal.ZERO) == -1) {
                        JOptionPane.showMessageDialog(this, "Tiền khách đưa không đủ");
                        return;
                    }
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
                    hoaDon.setHinhThucThanhToan(cboHTThanhToanDatHang.getSelectedIndex());
                    hoaDon.setNgayThanhToan(new Date());
                }
                hoaDon.setNgayNhan(new Date());
                hoaDon.setTrangThai(5);
                banHangService.saveOrUpdate(hoaDon);
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 1, 5);
                setSelected(1, 4);
                tblHoaDonCho.setRowSelectionInterval(0, 0);
                loadDataToHoaDon(listHoaDon);
                clearFormDatHang();
                JOptionPane.showMessageDialog(this, "Cập nhật hóa đơn thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Trạng thái hóa đơn không hợp lệ");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDaGiaoDatHangActionPerformed

    private void btnThanhToanDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanDatHangActionPerformed
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
//            if(txtNgayMongMuon.datePicker.getSettings().getAllowKeyboardEditing() || txtNgayMongMuon.timePicker.getSettings().getAllowKeyboardEditing()){
//                JOptionPane.showMessageDialog(this, "Hãy nhập thời gian khách mong muốn nhận hàng");
//                return;
//            }
            LocalDateTime timeLocal = txtNgayMongMuon.getDateTimePermissive();
            String t1 = String.valueOf(timeLocal);
            String array1[] = t1.split("T");
            String arrayDaoChuoi[] = array1[0].split("-");
            String ngayDao = arrayDaoChuoi[2] + "-" + arrayDaoChuoi[1] + "-" + arrayDaoChuoi[0];
            String timeMongMuonStr = ngayDao + " " + array1[1];
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
            Date timeMongMuon = sdf.parse(timeMongMuonStr);

            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thanh toán không?");
            if (hoi != JOptionPane.YES_OPTION) {
                return;
            }
            int rowHoaDon = tblHoaDonCho.getSelectedRow();
            BhHoaDonResponse bhHoaDonResponse = listHoaDon.get(rowHoaDon);
            if (bhHoaDonResponse.getHinhThucGiaoHang() == 1) {
                HoaDon hoaDon = banHangService.findByIdHoaDon(bhHoaDonResponse.getId());
                hoaDon.setHinhThucGiaoHang(1);
                hoaDon.setHinhThucThanhToan(cboHTThanhToanDatHang.getSelectedIndex());
                hoaDon.setTrangThai(2);
                hoaDon.setTenNguoiNhan(tenNguoiNhan);
                hoaDon.setSdtNguoiNhan(sdtNguoiNhan);
                hoaDon.setDiaChi(diaChi);
                hoaDon.setNgayMongMuon(timeMongMuon);
                hoaDon.setTrangThaiThanhToan(0);
                hoaDon.setTenNguoiShip(tenNguoiShip);
                hoaDon.setSdtNguoiShip(sdtNguoiShip);
                hoaDon.setNhanVien(nhanVien);
                hoaDon.setNgayThanhToan(new Date());
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

                JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn không?");
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        ExportFilePdfByITextDatHang export = new ExportFilePdfByITextDatHang();
                        export.exportBill(hoaDon, new ArrayList<>(mapGioHang.values()));
                        JOptionPane.showMessageDialog(this, "In hóa đơn thành công");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "In hóa đơn thất bại");
                    }
                    clearFormDatHang();
                } else {
                    clearFormDatHang();
                }
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 1, 2);
                setSelected(1, 1);
                loadDataToHoaDon(listHoaDon);
                tblHoaDonCho.setRowSelectionInterval(0, 0);
                btnThanhToanDatHang.setEnabled(false);
                btnChoGiaoHang.setEnabled(true);
                btnDangGiaoDatHang.setEnabled(false);
                btnDaGiaoDatHang.setEnabled(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThanhToanDatHangActionPerformed

    private void btnChoGiaoHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChoGiaoHangActionPerformed
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
            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật hóa đơn thành trạng thái chờ giao hàng hay không?");
            if (hoi != JOptionPane.YES_OPTION) {
                return;
            }
            LocalDateTime timeLocal = txtNgayMongMuon.getDateTimePermissive();
            String t1 = String.valueOf(timeLocal);
            String array1[] = t1.split("T");
            String arrayDaoChuoi[] = array1[0].split("-");
            String ngayDao = arrayDaoChuoi[2] + "-" + arrayDaoChuoi[1] + "-" + arrayDaoChuoi[0];
            String timeMongMuonStr = ngayDao + " " + array1[1];
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
            Date timeMongMuon = sdf.parse(timeMongMuonStr);

            int rowHoaDon = tblHoaDonCho.getSelectedRow();
            BhHoaDonResponse bhHoaDonResponse = listHoaDon.get(rowHoaDon);
            if (bhHoaDonResponse.getHinhThucGiaoHang() == 1) {
                if (bhHoaDonResponse.getTrangThai() == 2) {
                    HoaDon hoaDon = banHangService.findByIdHoaDon(bhHoaDonResponse.getId());
                    hoaDon.setHinhThucGiaoHang(1);
                    hoaDon.setTrangThai(3);
                    hoaDon.setNgayMongMuon(timeMongMuon);
                    hoaDon.setTenNguoiNhan(tenNguoiNhan);
                    hoaDon.setSdtNguoiNhan(sdtNguoiNhan);
                    hoaDon.setDiaChi(diaChi);
                    hoaDon.setTenNguoiShip(tenNguoiShip);
                    hoaDon.setSdtNguoiShip(sdtNguoiShip);
                    hoaDon.setTienShip(new BigDecimal(txtTienShip.getText().trim()));
                    String array[] = txtThanhToanDatHang.getText().trim().split(" ");
                    hoaDon.setThanhTien(new BigDecimal(array[0].replace(",", "")));
                    banHangService.saveOrUpdate(hoaDon);
                }
                if (bhHoaDonResponse.getTrangThai() == 0) {
                    HoaDon hoaDon = banHangService.findByIdHoaDon(bhHoaDonResponse.getId());
                    hoaDon.setTrangThai(3);
                    hoaDon.setTrangThaiThanhToan(1);
                    hoaDon.setTenNguoiNhan(tenNguoiNhan);
                    hoaDon.setSdtNguoiNhan(sdtNguoiNhan);
                    hoaDon.setDiaChi(diaChi);
                    hoaDon.setNgayMongMuon(timeMongMuon);
                    String array[] = txtThanhToanDatHang.getText().trim().split(" ");
                    hoaDon.setThanhTien(new BigDecimal(array[0].replace(",", "")));
                    hoaDon.setTenNguoiShip(tenNguoiShip);
                    hoaDon.setSdtNguoiShip(sdtNguoiShip);
                    hoaDon.setNhanVien(nhanVien);
                    hoaDon.setTienShip(new BigDecimal(txtTienShip.getText().trim()));
                    banHangService.saveOrUpdate(hoaDon);
                    banHangService.updateSoLuong(mapGioHang);
                    listSanPham = banHangService.getAllChiTietSP();
                    loadDataToTableSP(listSanPham);
                }
                JOptionPane.showMessageDialog(this, "Đã cập nhật thành trạng thái chờ giao hàng");
//                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn không?");
//                if (confirm == JOptionPane.YES_OPTION) {
//                    try {
//                        ExportFilePdfByITextDatHang export = new ExportFilePdfByITextDatHang();
//                        export.exportBill(hoaDon, new ArrayList<>(mapGioHang.values()));
//                        JOptionPane.showMessageDialog(this, "In hóa đơn thành công");
//                    } catch (Exception e) {
//                        JOptionPane.showMessageDialog(this, "In hóa đơn thất bại");
//                    }
//                    clearFormDatHang();
//                } else {
//                    clearFormDatHang();
//                }
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 1, 3);
                setSelected(1, 2);
                loadDataToHoaDon(listHoaDon);
                tblHoaDonCho.setRowSelectionInterval(0, 0);
                btnThanhToanDatHang.setEnabled(false);
                btnChoGiaoHang.setEnabled(false);
                btnDangGiaoDatHang.setEnabled(true);
                btnDaGiaoDatHang.setEnabled(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnChoGiaoHangActionPerformed

    private void btnChonKhachHangDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKhachHangDatHangActionPerformed
        KhachHangView.setVisible(true);
        KhachHangView.setLocationRelativeTo(null);
        listKhachHang = banHangService.getAllKhachHangResponse();
        loadDataToKhachHang(listKhachHang);
    }//GEN-LAST:event_btnChonKhachHangDatHangActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnThanhToanTaiQuayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanTaiQuayActionPerformed
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
                HoaDon hoaDon = banHangService.findByIdHoaDon(bhHoaDonResponse.getId());
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
                if (khachHang.getCapBac() == 0) {
                    hoaDon.setPhamTramGiamGia(0.0);
                }
                if (khachHang.getCapBac() == 1) {
                    hoaDon.setPhamTramGiamGia(3.0);
                }
                if (khachHang.getCapBac() == 2) {
                    hoaDon.setPhamTramGiamGia(5.0);
                }
                if (khachHang.getCapBac() == 3) {
                    hoaDon.setPhamTramGiamGia(10.0);
                }
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
                JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn không?");
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        ExportFilePdfByITextTaiQuay export = new ExportFilePdfByITextTaiQuay();
                        export.exportBill(hoaDon, new ArrayList<>(mapGioHang.values()));
                        JOptionPane.showMessageDialog(this, "In hóa đơn thành công");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "In hóa đơn thất bại");
                    }
                    clearForm();
                } else {
                    clearForm();
                }
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 0, 2);
                setSelected(0, 1);
                loadDataToHoaDon(listHoaDon);
                tblHoaDonCho.setRowSelectionInterval(0, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThanhToanTaiQuayActionPerformed

    private void setSelected(Integer hinhThucGiaoHang, Integer trangThaiHoaDon) {
        cboHinhThucGiaoHang.setSelectedIndex(hinhThucGiaoHang);
        cboTrangThaiHoaDon.setSelectedIndex(trangThaiHoaDon);
    }

    private void cboHinhThucGiaoHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHinhThucGiaoHangActionPerformed
        clearForm();
        clearFormDatHang();
        if (cboHinhThucGiaoHang.getSelectedIndex() == 0) {
            tbpDonHang.setSelectedIndex(0);
            btnThanhToanTaiQuay.setEnabled(false);
            cboTrangThaiHoaDon.removeAllItems();
            cboHinhThucThanhToan.setVisible(false);
            cboTrangThaiHoaDon.addItem("Chờ thanh toán");
            cboTrangThaiHoaDon.addItem("Đã thanh toán");
            cboTrangThaiHoaDon.addItem("Tất cả");
            if (cboTrangThaiHoaDon.getSelectedIndex() == 1) {
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 0, 2);
            } else if (cboTrangThaiHoaDon.getSelectedIndex() == 1) {
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 0, 0);
            }
            loadDataToHoaDon(listHoaDon);
            countTbpDatHang = 1;
        } else if (cboHinhThucGiaoHang.getSelectedIndex() == 1) {
            tbpDonHang.setSelectedIndex(1);
            btnThanhToanDatHang.setEnabled(false);
            btnChoGiaoHang.setEnabled(false);
            btnDangGiaoDatHang.setEnabled(false);
            btnDaGiaoDatHang.setEnabled(false);
            cboHinhThucThanhToan.setVisible(true);
            cboTrangThaiHoaDon.removeAllItems();
            cboTrangThaiHoaDon.addItem("Chờ thanh toán");
            cboTrangThaiHoaDon.addItem("Đã thanh toán");
            cboTrangThaiHoaDon.addItem("Chờ giao hàng");
            cboTrangThaiHoaDon.addItem("Đang giao");
            cboTrangThaiHoaDon.addItem("Đã giao");
            cboTrangThaiHoaDon.addItem("Tất cả");
            if (cboTrangThaiHoaDon.getSelectedIndex() == 1) {
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 1, 2);
            } else if (cboTrangThaiHoaDon.getSelectedIndex() == 2) {
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 1, 3);
            } else if (cboTrangThaiHoaDon.getSelectedIndex() == 3) {
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 1, 4);
            } else if (cboTrangThaiHoaDon.getSelectedIndex() == 4) {
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 1, 5);
            } else {
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 1, 0);
            }
            loadDataToHoaDon(listHoaDon);
            countTbpTaiQuay = 1;
        } else {
            if (cboTrangThaiHoaDon.getSelectedIndex() == 1) {
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), -1, 2);
            }
            if (cboTrangThaiHoaDon.getSelectedIndex() == 2) {
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), -1, -1);
            }
            if (cboTrangThaiHoaDon.getSelectedIndex() == 0) {
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), -1, 0);
            }
            loadDataToHoaDon(listHoaDon);
            countTbpDatHang = 1;
            countTbpTaiQuay = 1;
        }
    }//GEN-LAST:event_cboHinhThucGiaoHangActionPerformed

    private void cboTrangThaiHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiHoaDonActionPerformed
        clearForm();
        clearFormDatHang();
        if (cboTrangThaiHoaDon.getSelectedIndex() == 0) {
            if (cboHinhThucGiaoHang.getSelectedIndex() == 0) {
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 0, 0);
            } else {
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 1, 0);
            }
        } else if (cboTrangThaiHoaDon.getSelectedIndex() == 1) {
            if (cboHinhThucGiaoHang.getSelectedIndex() == 0) {
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 0, 2);
            } else {
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 1, 2);
            }
        } else if (cboTrangThaiHoaDon.getSelectedIndex() == 2) {
            listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 1, 3);
        } else if (cboTrangThaiHoaDon.getSelectedIndex() == 3) {
            listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 1, 4);
        } else if (cboTrangThaiHoaDon.getSelectedIndex() == 4) {
            listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 1, 5);
        }
//        if (cboTrangThaiHoaDon.getSelectedIndex() == 5) {
//            if (cboHinhThucGiaoHang.getSelectedIndex() == 0) {
//                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 0, -1);
//            } else if (cboHinhThucGiaoHang.getSelectedIndex() == 1) {
//                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 1, -1);
//            } else {
//                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), -1, -1);
//            }
//        }
        if (cboHinhThucGiaoHang.getSelectedIndex() == 0 && cboTrangThaiHoaDon.getSelectedIndex() == 2) {
            listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 0, -1);
        }
        if (cboHinhThucGiaoHang.getSelectedIndex() == 2 && cboTrangThaiHoaDon.getSelectedIndex() == 2) {
            listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), -1, -1);
        }
        if (cboHinhThucGiaoHang.getSelectedIndex() == 1 && cboTrangThaiHoaDon.getSelectedIndex() == 5) {
            listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 1, -1);
        }
        loadDataToHoaDon(listHoaDon);
    }//GEN-LAST:event_cboTrangThaiHoaDonActionPerformed

    private void txtTenNVDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNVDatHangActionPerformed

    }//GEN-LAST:event_txtTenNVDatHangActionPerformed

    private void tbpDonHangStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbpDonHangStateChanged

    }//GEN-LAST:event_tbpDonHangStateChanged

    private void tbpDonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpDonHangMouseClicked
        if (tbpDonHang.getSelectedIndex() == 0) {
            if (countTbpTaiQuay == 0) {
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 0, 0);
                setSelected(0, 0);
                loadDataToHoaDon(listHoaDon);
                btnThanhToanTaiQuay.setEnabled(false);
            }
            countTbpTaiQuay++;
            countTbpDatHang = 0;
        } else {
            if (countTbpDatHang == 0) {
                listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 1, 0);
                setSelected(1, 0);
                loadDataToHoaDon(listHoaDon);
                btnThanhToanDatHang.setEnabled(false);
                btnChoGiaoHang.setEnabled(false);
                btnDangGiaoDatHang.setEnabled(false);
                btnDaGiaoDatHang.setEnabled(false);
            }
            countTbpDatHang++;
            countTbpTaiQuay = 0;
        }
    }//GEN-LAST:event_tbpDonHangMouseClicked

    private void cboTrangThaiThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiThanhToanActionPerformed
        if (cboTrangThaiThanhToan.getSelectedIndex() == 0) {
            btnThanhToanDatHang.setEnabled(true);
            btnChoGiaoHang.setEnabled(false);
            btnDangGiaoDatHang.setEnabled(false);
            btnDaGiaoDatHang.setEnabled(false);
        } else {
            btnChoGiaoHang.setEnabled(true);
            btnDangGiaoDatHang.setEnabled(false);
            btnDaGiaoDatHang.setEnabled(false);
            btnThanhToanDatHang.setEnabled(false);
            txtTienKhachCKDatHang.setEnabled(true);
            txtTienKhachCKDatHang.setText("");
            cboHTThanhToanDatHang.setSelectedIndex(0);
            txtTienKhachDuaDatHang.setText("");
            txtTienThuaDatHang.setText("");
        }
    }//GEN-LAST:event_cboTrangThaiThanhToanActionPerformed

    private void chkTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTatCaActionPerformed
        if (!selected) {
            for (int i = 0; i < mapGioHang.size(); i++) {
                tblGioHang.setValueAt(true, i, 7);
            }
            selected = true;
        } else {
            for (int i = 0; i < mapGioHang.size(); i++) {
                tblGioHang.setValueAt(false, i, 7);
            }
            selected = false;
        }
    }//GEN-LAST:event_chkTatCaActionPerformed

    private void btnChonNhanVienShipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonNhanVienShipActionPerformed
        NhanVienView.setVisible(true);
        NhanVienView.setLocationRelativeTo(null);
        listNhanVien = banHangService.getAllNhanVienResponse();
        loadDataToNhanVien(listNhanVien);
    }//GEN-LAST:event_btnChonNhanVienShipActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            int rowNhanVien = tblNhanVienShip.getSelectedRow();
            if(rowNhanVien >= 0){
                BhNhanVienResponse bhNhanVienResponse = listNhanVien.get(rowNhanVien);
                txtTenNguoiShipDatHang.setText(bhNhanVienResponse.getTen());
                txtSDTNguoiShipDatHang.setText(bhNhanVienResponse.getSdt());
            }
            NhanVienView.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            String maKH = "KH" + banHangService.genMaKH();
            String tenKh = txtTenKHAdd.getText().trim();
            String sdtKh = txtSdtAdd.getText().trim();
            Date ngaySinh = java.sql.Date.valueOf(txtNgaySinhAdd.getDate());
            int gioiTinh = rdoNam.isSelected() ? 0 : 1;
            String diaChi = txtDiaChi.getText().trim();
            String email = txtEmailKh.getText().trim();
            if(maKH.isEmpty() || tenKh.isEmpty() || sdtKh.isEmpty() || diaChi.isEmpty() || email.isEmpty()){
                JOptionPane.showMessageDialog(this, "Hãy nhập đầy đủ các thông tin");
                return;
            }
            KhachHang khachHang = new KhachHang();
            khachHang.setMa(maKH);
            khachHang.setHoTen(tenKh);
            khachHang.setSdt(sdtKh);
            khachHang.setNgaySinh(ngaySinh);
            khachHang.setGioiTinh(gioiTinh);
            khachHang.setDiaChi(diaChi);
            khachHang.setEmail(email);
            khachHang.setCapBac(0);
            banHangService.saveOrUpdateKH(khachHang);
            listKhachHang = banHangService.getAllKhachHangResponse();
            loadDataToKhachHang(listKhachHang);
            JOptionPane.showMessageDialog(this, "Tạo khách hàng thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame KhachHangView;
    private javax.swing.JFrame NhanVienView;
    private javax.swing.JButton btnChoGiaoHang;
    private javax.swing.JFrame btnChonKH;
    private javax.swing.JButton btnChonKhachHang;
    private javax.swing.JButton btnChonKhachHangDatHang;
    private javax.swing.JButton btnChonNhanVienShip;
    private javax.swing.JButton btnChonTaiQuay;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDaGiaoDatHang;
    private javax.swing.JButton btnDangGiaoDatHang;
    private javax.swing.JButton btnThanhToanDatHang;
    private javax.swing.JButton btnThanhToanTaiQuay;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoa1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboHTThanhToanDatHang;
    private javax.swing.JComboBox<String> cboHTThanhToanTaiQuay;
    private javax.swing.JComboBox<String> cboHang;
    private javax.swing.JComboBox<String> cboHinhThucGiaoHang;
    private javax.swing.JComboBox<String> cboHinhThucThanhToan;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboSize;
    private javax.swing.JComboBox<String> cboTrangThaiHoaDon;
    private javax.swing.JComboBox<String> cboTrangThaiThanhToan;
    private javax.swing.JCheckBox chkTatCa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
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
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblCapBac;
    private javax.swing.JLabel lblTenCapBac;
    private javax.swing.JPanel pnlWebcam;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDonCho;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblKhachHangView;
    private javax.swing.JTable tblNhanVienShip;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTabbedPane tbpDonHang;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtDiaChiDatHang;
    private javax.swing.JTextField txtEmailKh;
    private javax.swing.JTextField txtGiamGiaDatHang;
    private javax.swing.JTextField txtGiamGiaTaiQuay;
    private javax.swing.JTextField txtHoTenKHDatHang;
    private javax.swing.JTextField txtMaHDDatHang;
    private javax.swing.JTextField txtMaHDTaiQuay;
    private javax.swing.JTextField txtMaKHTaiQuay;
    private com.github.lgooddatepicker.components.DateTimePicker txtNgayMongMuon;
    private com.github.lgooddatepicker.components.DatePicker txtNgaySinhAdd;
    private javax.swing.JTextField txtSDTKHDatHang;
    private javax.swing.JTextField txtSDTNguoiShipDatHang;
    private javax.swing.JTextField txtSdtAdd;
    private javax.swing.JTextField txtTenKHAdd;
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
                BigDecimal money = new BigDecimal(tblGioHang.getValueAt(i, 2).toString()).multiply(new BigDecimal(tblGioHang.getValueAt(i, 3).toString()));
                moneySale = new BigDecimal(tblGioHang.getValueAt(i, 4).toString()).multiply(new BigDecimal(tblGioHang.getValueAt(i, 3).toString()));
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
                KhachHang khachHang = banHangService.findByMaKhachHang(txtMaKHTaiQuay.getText().trim());
                BigDecimal thanhTienConLai = null;
                if (khachHang != null) {
                    String array[] = txtThanhToanTaiQuay.getText().trim().split(" ");
                    if (khachHang.getCapBac() == 0) {
                        thanhTienConLai = new BigDecimal(array[0].replace(",", "")).subtract(new BigDecimal(array[0].replace(",", "")).multiply(new BigDecimal(0.0).divide(new BigDecimal(100))));
                    }
                    if (khachHang.getCapBac() == 1) {
                        thanhTienConLai = new BigDecimal(array[0].replace(",", "")).subtract(new BigDecimal(array[0].replace(",", "")).multiply(new BigDecimal(3.0).divide(new BigDecimal(100))));
                    }
                    if (khachHang.getCapBac() == 2) {
                        thanhTienConLai = new BigDecimal(array[0].replace(",", "")).subtract(new BigDecimal(array[0].replace(",", "")).multiply(new BigDecimal(5.0).divide(new BigDecimal(100))));
                    }
                    if (khachHang.getCapBac() == 3) {
                        thanhTienConLai = new BigDecimal(array[0].replace(",", "")).subtract(new BigDecimal(array[0].replace(",", "")).multiply(new BigDecimal(10.0).divide(new BigDecimal(100))));
                    }
                }
                txtThanhToanTaiQuay.setText(df.format(thanhTienConLai) + " Vnđ");
                if (cboHTThanhToanTaiQuay.getSelectedIndex() == 1) {
                    txtTienKhachCKTaiQuay.setText(txtThanhToanTaiQuay.getText());
                    return;
                }
                if (cboHTThanhToanTaiQuay.getSelectedIndex() == 2) {
                    BigDecimal tienCKTaiQuay = thanhTienConLai.subtract(new BigDecimal(txtTienKhachDuaTaiQuay.getText().trim().toString()));
                    txtTienKhachCKTaiQuay.setText(tienCKTaiQuay + "");
                    txtTienThuaTaiQuay.setText("0 Vnđ");
                    return;
                }
                if (txtTienKhachDuaTaiQuay.getText().trim().isEmpty()) {
                    return;
                }
                BigDecimal tienKhachDua = new BigDecimal(txtTienKhachDuaTaiQuay.getText().trim());
                BigDecimal tienThua = null;
                tienThua = tienKhachDua.subtract(thanhTienConLai);
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
                BigDecimal tienCanTra = tong.subtract(giamGia).add(tienShip);
                txtThanhToanDatHang.setText(df.format(tienCanTra) + " Vnđ");
                if (cboHTThanhToanDatHang.getSelectedIndex() == 1) {
                    txtTienKhachCKDatHang.setText(txtThanhToanDatHang.getText());
                    txtTienThuaDatHang.setText("0 Vnđ");
                    return;
                }
                if (cboHTThanhToanDatHang.getSelectedIndex() == 2) {
                    BigDecimal tienCKDatHang = tienCanTra.subtract(new BigDecimal(txtTienKhachDuaDatHang.getText().trim().toString()));
                    txtTienKhachCKDatHang.setText(tienCKDatHang + "");
                    txtTienThuaDatHang.setText("0 Vnđ");
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

    public void taoHoaDonDatHang() {
        try {
            clearForm();
            clearFormDatHang();
            HoaDon hoaDon = new HoaDon();
            LocalDateTime time = LocalDateTime.now();
            String maHd = "HD" + String.valueOf(time.getYear()).substring(2) + time.getMonthValue()
                    + time.getDayOfMonth() + time.getHour() + time.getMinute() + time.getSecond();
            hoaDon.setMa(maHd);
            hoaDon.setNgayTao(new Date());
            hoaDon.setNhanVien(nhanVien);
            hoaDon.setHinhThucGiaoHang(1);
            hoaDon.setTrangThai(0);
            banHangService.saveOrUpdate(hoaDon);
            listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 1, 0);
            setSelected(1, 0);
            loadDataToHoaDon(listHoaDon);
            JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công");
            tblHoaDonCho.setRowSelectionInterval(0, 0);
            txtMaHDDatHang.setText(hoaDon.getMa());
            txtTenNVDatHang.setText(nhanVien.getTen());
            btnThanhToanDatHang.setEnabled(true);
            btnChoGiaoHang.setEnabled(false);
            btnDangGiaoDatHang.setEnabled(false);
            btnDaGiaoDatHang.setEnabled(false);
            btnXoa.setEnabled(true);
            btnXoa1.setEnabled(true);
            btnChonKhachHangDatHang.setEnabled(true);
            btnChonNhanVienShip.setEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void taoHoaDonTaiQuay() {
        try {
            clearForm();
            clearFormDatHang();
            HoaDon hoaDon = new HoaDon();
            LocalDateTime time = LocalDateTime.now();
            String maHd = "HD" + String.valueOf(time.getYear()).substring(2) + time.getMonthValue()
                    + time.getDayOfMonth() + time.getHour() + time.getMinute() + time.getSecond();
            hoaDon.setMa(maHd);
            hoaDon.setNgayTao(new Date());
            hoaDon.setNhanVien(nhanVien);
            hoaDon.setHinhThucGiaoHang(0);
            hoaDon.setTrangThai(0);
            banHangService.saveOrUpdate(hoaDon);
            listHoaDon = banHangService.getAllResponseHD(nhanVien.getId(), 0, 0);
            setSelected(0, 0);
            loadDataToHoaDon(listHoaDon);
            JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công");
            tblHoaDonCho.setRowSelectionInterval(0, 0);
            btnXoa.setEnabled(true);
            btnXoa1.setEnabled(true);
            btnClear.setEnabled(true);
            btnChonTaiQuay.setEnabled(true);
            btnThanhToanTaiQuay.setEnabled(true);
            chkTatCa.setEnabled(true);
            txtMaHDTaiQuay.setText(hoaDon.getMa());
            txtTenNVTaiQuay.setText(nhanVien.getTen());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, BhHoaDonChiTietResponse> getMapSelected() {
        Map<String, BhHoaDonChiTietResponse> mapSelected = new HashMap<>();
        for (int i = 0; i < mapGioHang.size(); i++) {
            boolean check = Boolean.valueOf(tblGioHang.getValueAt(i, 7).toString());
            if (check) {
                mapSelected.put(new ArrayList<>(mapGioHang.values()).get(i).getIdChiTietSP(), new ArrayList<>(mapGioHang.values()).get(i));
            }
        }
        return mapSelected;
    }
}
