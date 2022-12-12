package view;

import core.view.ViewBanHang;
import core.view.ViewDoiTra;
import core.view.ViewHoaDon;
import core.view.ViewKhachHang;
import core.view.ViewKhuyenMai;
import core.view.ViewNhanVien;
import core.view.ViewSanPham;
import core.view.ViewThongKe;
import domainmodels.NhanVien;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import util.DailyCheckingKhuyenMai;
import util.DailyCheckingRankCustomer;

/**
 *
 * @author thangncph26123
 */
public class JFrameQuanLy extends javax.swing.JFrame {

    private NhanVien nhanVien = new NhanVien();
    

    public JFrameQuanLy(NhanVien nv) {
        initComponents();
        setLocationRelativeTo(null);
        nhanVien = nv;
        pnlBanHang.setBackground(Color.WHITE);
        menuBanHang.setForeground(new Color(153, 204, 255));
        ViewBanHang viewBanHang = new ViewBanHang(nhanVien);
        btnCardSon.removeAll();
        btnCardSon.add(viewBanHang);
        btnCardSon.setLayout(new FlowLayout());
        this.pack();
        btnCardSon.setVisible(true);

        ImageIcon img = new ImageIcon("src/main/images/sneaker.jpg");
        
        lblAvartar.setIcon(img);

        this.setIconImage(img.getImage());
        
        DailyCheckingKhuyenMai.dailyChecking();
        DailyCheckingKhuyenMai.dailyCheckingRankCustomer();

<<<<<<< HEAD
        Thread countDownThread = new Thread() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
                do {
                    Date now = new Date();

                    String st = sdf.format(now);

                    lblNgayGio.setText(st);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (true);
            }
        };
        countDownThread.start();
        txtMaNV.setText(nhanVien.getMa());
        txtTenNV.setText(nhanVien.getTen());
        txtChucVu.setText(nhanVien.getVaiTro() == 0 ? "Quản lý" : "Nhân viên");
=======
>>>>>>> develop_phase_2
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCards = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        pnlBanHang = new javax.swing.JPanel();
        menuBanHang = new javax.swing.JLabel();
        pnlHoaDon = new javax.swing.JPanel();
        menuHoaDon = new javax.swing.JLabel();
        pnlSanPham = new javax.swing.JPanel();
        menuSanPham = new javax.swing.JLabel();
        pnlKhuyenMai = new javax.swing.JPanel();
        menuKhuyenMai = new javax.swing.JLabel();
        pnlThongKe = new javax.swing.JPanel();
        menuThongKe = new javax.swing.JLabel();
        pnlNhanVien = new javax.swing.JPanel();
        menuNhanVien = new javax.swing.JLabel();
        pnlKhachHang = new javax.swing.JPanel();
        menuKhachHang = new javax.swing.JLabel();
        pnlDangXuat = new javax.swing.JPanel();
        menuDangXuat = new javax.swing.JLabel();
        pnlThoat = new javax.swing.JPanel();
        menuThoat = new javax.swing.JLabel();
        pnlDoiHang = new javax.swing.JPanel();
        menuDoiHang = new javax.swing.JLabel();
        lblAvartar = new util.ImageAvatar();
        btnCardSon = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Phần mềm bán giày thời trang Sneaker");
        setBackground(new java.awt.Color(153, 204, 255));
        setResizable(false);

        pnlCards.setBackground(new java.awt.Color(255, 255, 255));
        pnlCards.setMaximumSize(new java.awt.Dimension(204, 784));

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(204, 784));
        jPanel1.setPreferredSize(new java.awt.Dimension(206, 784));

        pnlBanHang.setBackground(new java.awt.Color(153, 204, 255));
        pnlBanHang.setPreferredSize(new java.awt.Dimension(84, 50));

        menuBanHang.setBackground(new java.awt.Color(51, 255, 51));
        menuBanHang.setFont(new java.awt.Font("Source Sans Pro Black", 0, 20)); // NOI18N
        menuBanHang.setForeground(new java.awt.Color(255, 255, 255));
        menuBanHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuBanHang.setIcon(new ImageIcon("src/main/images/cart.png"));
        menuBanHang.setText("Bán hàng");
        menuBanHang.setToolTipText("");
        menuBanHang.setInheritsPopupMenu(false);
        menuBanHang.setMaximumSize(new java.awt.Dimension(84, 20));
        menuBanHang.setMinimumSize(new java.awt.Dimension(84, 20));
        menuBanHang.setPreferredSize(new java.awt.Dimension(84, 20));
        menuBanHang.setVerifyInputWhenFocusTarget(false);
        menuBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuBanHangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlBanHangLayout = new javax.swing.GroupLayout(pnlBanHang);
        pnlBanHang.setLayout(pnlBanHangLayout);
        pnlBanHangLayout.setHorizontalGroup(
            pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBanHangLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(menuBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlBanHangLayout.setVerticalGroup(
            pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuBanHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlHoaDon.setBackground(new java.awt.Color(153, 204, 255));
        pnlHoaDon.setPreferredSize(new java.awt.Dimension(74, 50));

        menuHoaDon.setBackground(new java.awt.Color(51, 255, 51));
        menuHoaDon.setFont(new java.awt.Font("Source Sans Pro Black", 0, 20)); // NOI18N
        menuHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        menuHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuHoaDon.setIcon(new ImageIcon("src/main/images/invoice.png"));
        menuHoaDon.setText("Hóa đơn");
        menuHoaDon.setToolTipText("");
        menuHoaDon.setInheritsPopupMenu(false);
        menuHoaDon.setVerifyInputWhenFocusTarget(false);
        menuHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuHoaDonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlHoaDonLayout = new javax.swing.GroupLayout(pnlHoaDon);
        pnlHoaDon.setLayout(pnlHoaDonLayout);
        pnlHoaDonLayout.setHorizontalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHoaDonLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(menuHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlHoaDonLayout.setVerticalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlSanPham.setBackground(new java.awt.Color(153, 204, 255));
        pnlSanPham.setPreferredSize(new java.awt.Dimension(90, 50));

        menuSanPham.setBackground(new java.awt.Color(51, 255, 51));
        menuSanPham.setFont(new java.awt.Font("Source Sans Pro Black", 0, 20)); // NOI18N
        menuSanPham.setForeground(new java.awt.Color(255, 255, 255));
        menuSanPham.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuSanPham.setIcon(new ImageIcon("src/main/images/cubes.png"));
        menuSanPham.setText("Sản phẩm");
        menuSanPham.setToolTipText("");
        menuSanPham.setInheritsPopupMenu(false);
        menuSanPham.setVerifyInputWhenFocusTarget(false);
        menuSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSanPhamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlSanPhamLayout = new javax.swing.GroupLayout(pnlSanPham);
        pnlSanPham.setLayout(pnlSanPhamLayout);
        pnlSanPhamLayout.setHorizontalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSanPhamLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(menuSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlSanPhamLayout.setVerticalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlKhuyenMai.setBackground(new java.awt.Color(153, 204, 255));
        pnlKhuyenMai.setPreferredSize(new java.awt.Dimension(107, 50));

        menuKhuyenMai.setBackground(new java.awt.Color(51, 255, 51));
        menuKhuyenMai.setFont(new java.awt.Font("Source Sans Pro Black", 0, 20)); // NOI18N
        menuKhuyenMai.setForeground(new java.awt.Color(255, 255, 255));
        menuKhuyenMai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuKhuyenMai.setIcon(new ImageIcon("src/main/images/coupon.png"));
        menuKhuyenMai.setText("Khuyến mại");
        menuKhuyenMai.setToolTipText("");
        menuKhuyenMai.setInheritsPopupMenu(false);
        menuKhuyenMai.setPreferredSize(new java.awt.Dimension(90, 26));
        menuKhuyenMai.setVerifyInputWhenFocusTarget(false);
        menuKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuKhuyenMaiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlKhuyenMaiLayout = new javax.swing.GroupLayout(pnlKhuyenMai);
        pnlKhuyenMai.setLayout(pnlKhuyenMaiLayout);
        pnlKhuyenMaiLayout.setHorizontalGroup(
            pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKhuyenMaiLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(menuKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlKhuyenMaiLayout.setVerticalGroup(
            pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuKhuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlThongKe.setBackground(new java.awt.Color(153, 204, 255));
        pnlThongKe.setPreferredSize(new java.awt.Dimension(83, 50));

        menuThongKe.setBackground(new java.awt.Color(51, 255, 51));
        menuThongKe.setFont(new java.awt.Font("Source Sans Pro Black", 0, 20)); // NOI18N
        menuThongKe.setForeground(new java.awt.Color(255, 255, 255));
        menuThongKe.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuThongKe.setIcon(new ImageIcon("src/main/images/trend.png"));
        menuThongKe.setText("Thống kê");
        menuThongKe.setToolTipText("");
        menuThongKe.setInheritsPopupMenu(false);
        menuThongKe.setVerifyInputWhenFocusTarget(false);
        menuThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuThongKeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlThongKeLayout = new javax.swing.GroupLayout(pnlThongKe);
        pnlThongKe.setLayout(pnlThongKeLayout);
        pnlThongKeLayout.setHorizontalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongKeLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(menuThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlThongKeLayout.setVerticalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlNhanVien.setBackground(new java.awt.Color(153, 204, 255));
        pnlNhanVien.setPreferredSize(new java.awt.Dimension(91, 50));

        menuNhanVien.setBackground(new java.awt.Color(51, 255, 51));
        menuNhanVien.setFont(new java.awt.Font("Source Sans Pro Black", 0, 20)); // NOI18N
        menuNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        menuNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuNhanVien.setIcon(new ImageIcon("src/main/images/nhanvien.png"));
        menuNhanVien.setText("Nhân viên");
        menuNhanVien.setToolTipText("");
        menuNhanVien.setInheritsPopupMenu(false);
        menuNhanVien.setVerifyInputWhenFocusTarget(false);
        menuNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuNhanVienMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlNhanVienLayout = new javax.swing.GroupLayout(pnlNhanVien);
        pnlNhanVien.setLayout(pnlNhanVienLayout);
        pnlNhanVienLayout.setHorizontalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNhanVienLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(menuNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlNhanVienLayout.setVerticalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlKhachHang.setBackground(new java.awt.Color(153, 204, 255));
        pnlKhachHang.setPreferredSize(new java.awt.Dimension(106, 50));

        menuKhachHang.setBackground(new java.awt.Color(51, 255, 51));
        menuKhachHang.setFont(new java.awt.Font("Source Sans Pro Black", 0, 20)); // NOI18N
        menuKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        menuKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuKhachHang.setIcon(new ImageIcon("src/main/images/rating.png"));
        menuKhachHang.setText("Khách hàng");
        menuKhachHang.setToolTipText("");
        menuKhachHang.setInheritsPopupMenu(false);
        menuKhachHang.setVerifyInputWhenFocusTarget(false);
        menuKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuKhachHangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlKhachHangLayout = new javax.swing.GroupLayout(pnlKhachHang);
        pnlKhachHang.setLayout(pnlKhachHangLayout);
        pnlKhachHangLayout.setHorizontalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKhachHangLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(menuKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlKhachHangLayout.setVerticalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlDangXuat.setBackground(new java.awt.Color(153, 204, 255));
        pnlDangXuat.setPreferredSize(new java.awt.Dimension(92, 50));

        menuDangXuat.setBackground(new java.awt.Color(51, 255, 51));
        menuDangXuat.setFont(new java.awt.Font("Source Sans Pro Black", 0, 20)); // NOI18N
        menuDangXuat.setForeground(new java.awt.Color(255, 255, 255));
        menuDangXuat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuDangXuat.setIcon(new ImageIcon("src/main/images/logout.png"));
        menuDangXuat.setText("Đăng xuất");
        menuDangXuat.setToolTipText("");
        menuDangXuat.setInheritsPopupMenu(false);
        menuDangXuat.setVerifyInputWhenFocusTarget(false);
        menuDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuDangXuatMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlDangXuatLayout = new javax.swing.GroupLayout(pnlDangXuat);
        pnlDangXuat.setLayout(pnlDangXuatLayout);
        pnlDangXuatLayout.setHorizontalGroup(
            pnlDangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDangXuatLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(menuDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlDangXuatLayout.setVerticalGroup(
            pnlDangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlThoat.setBackground(new java.awt.Color(153, 204, 255));
        pnlThoat.setPreferredSize(new java.awt.Dimension(92, 50));

        menuThoat.setBackground(new java.awt.Color(51, 255, 51));
        menuThoat.setFont(new java.awt.Font("Source Sans Pro Black", 0, 20)); // NOI18N
        menuThoat.setForeground(new java.awt.Color(255, 255, 255));
        menuThoat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuThoat.setIcon(new ImageIcon("src/main/images/exit.png"));
        menuThoat.setText("Thoát");
        menuThoat.setToolTipText("");
        menuThoat.setInheritsPopupMenu(false);
        menuThoat.setVerifyInputWhenFocusTarget(false);
        menuThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuThoatMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlThoatLayout = new javax.swing.GroupLayout(pnlThoat);
        pnlThoat.setLayout(pnlThoatLayout);
        pnlThoatLayout.setHorizontalGroup(
            pnlThoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThoatLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(menuThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlThoatLayout.setVerticalGroup(
            pnlThoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuThoat, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlDoiHang.setBackground(new java.awt.Color(153, 204, 255));
        pnlDoiHang.setPreferredSize(new java.awt.Dimension(84, 50));

        menuDoiHang.setBackground(new java.awt.Color(51, 255, 51));
        menuDoiHang.setFont(new java.awt.Font("Source Sans Pro Black", 0, 20)); // NOI18N
        menuDoiHang.setForeground(new java.awt.Color(255, 255, 255));
        menuDoiHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuDoiHang.setIcon(new ImageIcon("src/main/images/cart.png"));
        menuDoiHang.setText("Đổi hàng");
        menuDoiHang.setToolTipText("");
        menuDoiHang.setInheritsPopupMenu(false);
        menuDoiHang.setMaximumSize(new java.awt.Dimension(84, 20));
        menuDoiHang.setMinimumSize(new java.awt.Dimension(84, 20));
        menuDoiHang.setPreferredSize(new java.awt.Dimension(84, 20));
        menuDoiHang.setVerifyInputWhenFocusTarget(false);
        menuDoiHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuDoiHangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlDoiHangLayout = new javax.swing.GroupLayout(pnlDoiHang);
        pnlDoiHang.setLayout(pnlDoiHangLayout);
        pnlDoiHangLayout.setHorizontalGroup(
            pnlDoiHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoiHangLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(menuDoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlDoiHangLayout.setVerticalGroup(
            pnlDoiHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuDoiHang, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        lblAvartar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAvartarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
            .addComponent(pnlSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
            .addComponent(pnlKhuyenMai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
            .addComponent(pnlThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
            .addComponent(pnlNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
            .addComponent(pnlKhachHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
            .addComponent(pnlDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
            .addComponent(pnlThoat, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
            .addComponent(pnlBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
            .addComponent(pnlDoiHang, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAvartar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblAvartar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlDoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlThoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        btnCardSon.setBackground(new java.awt.Color(255, 255, 255));
        btnCardSon.setAutoscrolls(true);
        btnCardSon.setMaximumSize(new java.awt.Dimension(1292, 784));
        btnCardSon.setMinimumSize(new java.awt.Dimension(1292, 784));
        btnCardSon.setPreferredSize(new java.awt.Dimension(1292, 784));

        javax.swing.GroupLayout btnCardSonLayout = new javax.swing.GroupLayout(btnCardSon);
        btnCardSon.setLayout(btnCardSonLayout);
        btnCardSonLayout.setHorizontalGroup(
            btnCardSonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1292, Short.MAX_VALUE)
        );
        btnCardSonLayout.setVerticalGroup(
            btnCardSonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlCardsLayout = new javax.swing.GroupLayout(pnlCards);
        pnlCards.setLayout(pnlCardsLayout);
        pnlCardsLayout.setHorizontalGroup(
            pnlCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCardsLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnCardSon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pnlCardsLayout.setVerticalGroup(
            pnlCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCardSon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCards, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBanHangMouseClicked
        pnlBanHang.setBackground(Color.WHITE);
        menuBanHang.setForeground(new Color(153, 204, 255));
        menuDoiHang.setForeground(Color.WHITE);
        menuHoaDon.setForeground(Color.WHITE);
        menuKhachHang.setForeground(Color.WHITE);
        menuKhuyenMai.setForeground(Color.WHITE);
        menuNhanVien.setForeground(Color.WHITE);
        menuSanPham.setForeground(Color.WHITE);
        menuThongKe.setForeground(Color.WHITE);
        menuDangXuat.setForeground(Color.WHITE);
        menuThoat.setForeground(Color.WHITE);
        pnlHoaDon.setBackground(null);
        pnlDoiHang.setBackground(null);
        pnlKhachHang.setBackground(null);
        pnlKhuyenMai.setBackground(null);
        pnlNhanVien.setBackground(null);
        pnlSanPham.setBackground(null);
        pnlThongKe.setBackground(null);
        pnlThoat.setBackground(null);
        pnlDangXuat.setBackground(null);
        ViewBanHang viewBanHang = new ViewBanHang(nhanVien);
        btnCardSon.removeAll();
        btnCardSon.add(viewBanHang);
        btnCardSon.setLayout(new FlowLayout());
        this.pack();
        btnCardSon.setVisible(true);
    }//GEN-LAST:event_menuBanHangMouseClicked

    private void menuHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuHoaDonMouseClicked
        pnlHoaDon.setBackground(Color.WHITE);
        menuHoaDon.setForeground(new Color(153, 204, 255));
        menuDoiHang.setForeground(Color.WHITE);
        menuBanHang.setForeground(Color.WHITE);
        menuKhachHang.setForeground(Color.WHITE);
        menuKhuyenMai.setForeground(Color.WHITE);
        menuNhanVien.setForeground(Color.WHITE);
        menuSanPham.setForeground(Color.WHITE);
        menuThongKe.setForeground(Color.WHITE);
        menuDangXuat.setForeground(Color.WHITE);
        menuThoat.setForeground(Color.WHITE);
        pnlBanHang.setBackground(null);
        pnlDoiHang.setBackground(null);
        pnlKhachHang.setBackground(null);
        pnlKhuyenMai.setBackground(null);
        pnlNhanVien.setBackground(null);
        pnlSanPham.setBackground(null);
        pnlThongKe.setBackground(null);
        pnlThoat.setBackground(null);
        pnlDangXuat.setBackground(null);
        ViewHoaDon viewHoaDon = new ViewHoaDon();
        btnCardSon.removeAll();
        btnCardSon.add(viewHoaDon);
        btnCardSon.setLayout(new FlowLayout());
        this.pack();
        ViewBanHang.webcam.close();
        btnCardSon.setVisible(true);
    }//GEN-LAST:event_menuHoaDonMouseClicked

    private void menuSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSanPhamMouseClicked
        pnlSanPham.setBackground(Color.WHITE);
        menuSanPham.setForeground(new Color(153, 204, 255));
        menuDoiHang.setForeground(Color.WHITE);
        menuBanHang.setForeground(Color.WHITE);
        menuKhachHang.setForeground(Color.WHITE);
        menuKhuyenMai.setForeground(Color.WHITE);
        menuNhanVien.setForeground(Color.WHITE);
        menuHoaDon.setForeground(Color.WHITE);
        menuThongKe.setForeground(Color.WHITE);
        menuDangXuat.setForeground(Color.WHITE);
        menuThoat.setForeground(Color.WHITE);
        pnlHoaDon.setBackground(null);
        pnlDoiHang.setBackground(null);
        pnlKhachHang.setBackground(null);
        pnlKhuyenMai.setBackground(null);
        pnlNhanVien.setBackground(null);
        pnlBanHang.setBackground(null);
        pnlThongKe.setBackground(null);
        pnlThoat.setBackground(null);
        pnlDangXuat.setBackground(null);
        ViewSanPham viewSanPham = new ViewSanPham();
        btnCardSon.removeAll();
        btnCardSon.add(viewSanPham);
        btnCardSon.setLayout(new FlowLayout());
        this.pack();
        ViewBanHang.webcam.close();
        btnCardSon.setVisible(true);
    }//GEN-LAST:event_menuSanPhamMouseClicked

    private void menuThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuThongKeMouseClicked
        pnlThongKe.setBackground(Color.WHITE);
        menuThongKe.setForeground(new Color(153, 204, 255));
        menuDoiHang.setForeground(Color.WHITE);
        menuBanHang.setForeground(Color.WHITE);
        menuKhachHang.setForeground(Color.WHITE);
        menuKhuyenMai.setForeground(Color.WHITE);
        menuNhanVien.setForeground(Color.WHITE);
        menuHoaDon.setForeground(Color.WHITE);
        menuSanPham.setForeground(Color.WHITE);
        menuDangXuat.setForeground(Color.WHITE);
        menuThoat.setForeground(Color.WHITE);
        pnlHoaDon.setBackground(null);
        pnlDoiHang.setBackground(null);
        pnlKhachHang.setBackground(null);
        pnlKhuyenMai.setBackground(null);
        pnlNhanVien.setBackground(null);
        pnlBanHang.setBackground(null);
        pnlSanPham.setBackground(null);
        pnlThoat.setBackground(null);
        pnlDangXuat.setBackground(null);
        ViewThongKe viewThongKe = new ViewThongKe();
        btnCardSon.removeAll();
        btnCardSon.add(viewThongKe);
        btnCardSon.setLayout(new FlowLayout());
        this.pack();
        ViewBanHang.webcam.close();
        btnCardSon.setVisible(true);
    }//GEN-LAST:event_menuThongKeMouseClicked

    private void menuNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuNhanVienMouseClicked
        pnlNhanVien.setBackground(Color.WHITE);
        menuNhanVien.setForeground(new Color(153, 204, 255));
        menuDoiHang.setForeground(Color.WHITE);
        menuBanHang.setForeground(Color.WHITE);
        menuKhachHang.setForeground(Color.WHITE);
        menuKhuyenMai.setForeground(Color.WHITE);
        menuThongKe.setForeground(Color.WHITE);
        menuHoaDon.setForeground(Color.WHITE);
        menuSanPham.setForeground(Color.WHITE);
        menuDangXuat.setForeground(Color.WHITE);
        menuThoat.setForeground(Color.WHITE);
        pnlHoaDon.setBackground(null);
        pnlDoiHang.setBackground(null);
        pnlKhachHang.setBackground(null);
        pnlKhuyenMai.setBackground(null);
        pnlThongKe.setBackground(null);
        pnlBanHang.setBackground(null);
        pnlSanPham.setBackground(null);
        pnlThoat.setBackground(null);
        pnlDangXuat.setBackground(null);
        ViewNhanVien viewNhanVien = new ViewNhanVien();
        btnCardSon.removeAll();
        btnCardSon.add(viewNhanVien);
        btnCardSon.setLayout(new FlowLayout());
        this.pack();
        ViewBanHang.webcam.close();
        btnCardSon.setVisible(true);
    }//GEN-LAST:event_menuNhanVienMouseClicked

    private void menuKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuKhachHangMouseClicked
        pnlKhachHang.setBackground(Color.WHITE);
        menuKhachHang.setForeground(new Color(153, 204, 255));
        menuDoiHang.setForeground(Color.WHITE);
        menuBanHang.setForeground(Color.WHITE);
        menuNhanVien.setForeground(Color.WHITE);
        menuKhuyenMai.setForeground(Color.WHITE);
        menuThongKe.setForeground(Color.WHITE);
        menuHoaDon.setForeground(Color.WHITE);
        menuSanPham.setForeground(Color.WHITE);
        menuDangXuat.setForeground(Color.WHITE);
        menuThoat.setForeground(Color.WHITE);
        pnlHoaDon.setBackground(null);
        pnlDoiHang.setBackground(null);
        pnlNhanVien.setBackground(null);
        pnlKhuyenMai.setBackground(null);
        pnlThongKe.setBackground(null);
        pnlBanHang.setBackground(null);
        pnlThoat.setBackground(null);
        pnlDangXuat.setBackground(null);
        pnlSanPham.setBackground(null);
        ViewKhachHang viewKhachHang = new ViewKhachHang();
        btnCardSon.removeAll();
        btnCardSon.add(viewKhachHang);
        btnCardSon.setLayout(new FlowLayout());
        this.pack();
        ViewBanHang.webcam.close();
        btnCardSon.setVisible(true);
    }//GEN-LAST:event_menuKhachHangMouseClicked

    private void menuKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuKhuyenMaiMouseClicked
        pnlKhuyenMai.setBackground(Color.WHITE);
        menuKhuyenMai.setForeground(new Color(153, 204, 255));
        menuDoiHang.setForeground(Color.WHITE);
        menuBanHang.setForeground(Color.WHITE);
        menuNhanVien.setForeground(Color.WHITE);
        menuKhachHang.setForeground(Color.WHITE);
        menuThongKe.setForeground(Color.WHITE);
        menuHoaDon.setForeground(Color.WHITE);
        menuSanPham.setForeground(Color.WHITE);
        menuDangXuat.setForeground(Color.WHITE);
        menuThoat.setForeground(Color.WHITE);
        pnlHoaDon.setBackground(null);
        pnlDoiHang.setBackground(null);
        pnlNhanVien.setBackground(null);
        pnlKhachHang.setBackground(null);
        pnlThoat.setBackground(null);
        pnlDangXuat.setBackground(null);
        pnlThongKe.setBackground(null);
        pnlBanHang.setBackground(null);
        pnlSanPham.setBackground(null);
        ViewKhuyenMai viewKhuyenMai = new ViewKhuyenMai();
        btnCardSon.removeAll();
        btnCardSon.add(viewKhuyenMai);
        btnCardSon.setLayout(new FlowLayout());
        this.pack();
        ViewBanHang.webcam.close();
        btnCardSon.setVisible(true);
    }//GEN-LAST:event_menuKhuyenMaiMouseClicked

    private void menuDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDangXuatMouseClicked
        pnlDangXuat.setBackground(Color.WHITE);
        menuDangXuat.setForeground(new Color(153, 204, 255));
        menuKhuyenMai.setForeground(Color.WHITE);
        menuBanHang.setForeground(Color.WHITE);
        menuNhanVien.setForeground(Color.WHITE);
        menuKhachHang.setForeground(Color.WHITE);
        menuThongKe.setForeground(Color.WHITE);
        menuHoaDon.setForeground(Color.WHITE);
        menuSanPham.setForeground(Color.WHITE);
        menuDoiHang.setForeground(Color.WHITE);
        menuThoat.setForeground(Color.WHITE);
        pnlHoaDon.setBackground(null);
        pnlThoat.setBackground(null);
        pnlDoiHang.setBackground(null);
        pnlKhuyenMai.setBackground(null);
        pnlNhanVien.setBackground(null);
        pnlKhachHang.setBackground(null);
        pnlThongKe.setBackground(null);
        pnlBanHang.setBackground(null);
        pnlSanPham.setBackground(null);
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn đăng xuất không?", "Xác nhận", JOptionPane.YES_NO_CANCEL_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        new ViewDangNhap().setVisible(true);
        ViewBanHang.webcam.close();
        this.dispose();
    }//GEN-LAST:event_menuDangXuatMouseClicked

    private void menuThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuThoatMouseClicked
        pnlThoat.setBackground(Color.WHITE);
        menuThoat.setForeground(new Color(153, 204, 255));
        menuKhuyenMai.setForeground(Color.WHITE);
        menuBanHang.setForeground(Color.WHITE);
        menuNhanVien.setForeground(Color.WHITE);
        menuKhachHang.setForeground(Color.WHITE);
        menuThongKe.setForeground(Color.WHITE);
        menuHoaDon.setForeground(Color.WHITE);
        menuSanPham.setForeground(Color.WHITE);
        menuDoiHang.setForeground(Color.WHITE);
        menuDangXuat.setForeground(Color.WHITE);
        pnlHoaDon.setBackground(null);
        pnlDangXuat.setBackground(null);
        pnlDoiHang.setBackground(null);
        pnlKhuyenMai.setBackground(null);
        pnlNhanVien.setBackground(null);
        pnlKhachHang.setBackground(null);
        pnlThongKe.setBackground(null);
        pnlBanHang.setBackground(null);
        pnlSanPham.setBackground(null);
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát chương trình không?", "Xác nhận", JOptionPane.YES_NO_CANCEL_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        ViewBanHang.webcam.close();
        System.exit(0);
    }//GEN-LAST:event_menuThoatMouseClicked

    private void menuDoiHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDoiHangMouseClicked
        pnlDoiHang.setBackground(Color.WHITE);
        menuDoiHang.setForeground(new Color(153, 204, 255));
        menuKhuyenMai.setForeground(Color.WHITE);
        menuBanHang.setForeground(Color.WHITE);
        menuNhanVien.setForeground(Color.WHITE);
        menuKhachHang.setForeground(Color.WHITE);
        menuThongKe.setForeground(Color.WHITE);
        menuHoaDon.setForeground(Color.WHITE);
        menuSanPham.setForeground(Color.WHITE);
        menuDangXuat.setForeground(Color.WHITE);
        menuThoat.setForeground(Color.WHITE);
        pnlHoaDon.setBackground(null);
        pnlThoat.setBackground(null);
        pnlDangXuat.setBackground(null);
        pnlKhuyenMai.setBackground(null);
        pnlNhanVien.setBackground(null);
        pnlKhachHang.setBackground(null);
        pnlThongKe.setBackground(null);
        pnlBanHang.setBackground(null);
        pnlSanPham.setBackground(null);
        ViewDoiTra viewDoiTra = new ViewDoiTra();
        btnCardSon.removeAll();
        btnCardSon.add(viewDoiTra);
        btnCardSon.setLayout(new FlowLayout());
        this.pack();
        ViewBanHang.webcam.close();
        btnCardSon.setVisible(true);
    }//GEN-LAST:event_menuDoiHangMouseClicked

    private void lblAvartarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAvartarMouseClicked
        
    }//GEN-LAST:event_lblAvartarMouseClicked

//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(JFrameQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JFrameQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JFrameQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JFrameQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new JFrameQuanLy().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnCardSon;
    private javax.swing.JPanel jPanel1;
    private util.ImageAvatar lblAvartar;
    private javax.swing.JLabel menuBanHang;
    private javax.swing.JLabel menuDangXuat;
    private javax.swing.JLabel menuDoiHang;
    private javax.swing.JLabel menuHoaDon;
    private javax.swing.JLabel menuKhachHang;
    private javax.swing.JLabel menuKhuyenMai;
    private javax.swing.JLabel menuNhanVien;
    private javax.swing.JLabel menuSanPham;
    private javax.swing.JLabel menuThoat;
    private javax.swing.JLabel menuThongKe;
    private javax.swing.JPanel pnlBanHang;
    private javax.swing.JPanel pnlCards;
    private javax.swing.JPanel pnlDangXuat;
    private javax.swing.JPanel pnlDoiHang;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlKhachHang;
    private javax.swing.JPanel pnlKhuyenMai;
    private javax.swing.JPanel pnlNhanVien;
    private javax.swing.JPanel pnlSanPham;
    private javax.swing.JPanel pnlThoat;
    private javax.swing.JPanel pnlThongKe;
    // End of variables declaration//GEN-END:variables
}
