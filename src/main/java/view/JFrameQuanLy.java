package view;

import core.view.ViewBanHang;
import core.view.ViewDoiTra;
import core.view.ViewHoaDon;
import core.view.ViewKhachHang;
import core.view.ViewKhuyenMai;
import core.view.ViewNhanVien;
import core.view.ViewSanPham;
import core.view.ViewThongKe;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author thangncph26123
 */
public class JFrameQuanLy extends javax.swing.JFrame {

    public JFrameQuanLy() {
        initComponents();
        setLocationRelativeTo(null);
        lblLogo.setIcon(new ImageIcon("src/main/images/sneaker.jpg"));

        ImageIcon originalIcon = new ImageIcon("src/main/images/sneaker.jpg");

        int width = 190;
        int height = 177;

        Image scaled = scaleImage(originalIcon.getImage(), width, height);

        ImageIcon scaledIcon = new ImageIcon(scaled);

        lblLogo.setIcon(scaledIcon);

        pnlBanHang.setBackground(Color.WHITE);
        menuBanHang.setForeground(new Color(153, 204, 255));
        ViewBanHang viewBanHang = new ViewBanHang();
        btnCardSon.removeAll();
        btnCardSon.add(viewBanHang);
        btnCardSon.setLayout(new FlowLayout());
        this.pack();
        btnCardSon.setVisible(true);

        ImageIcon img = new ImageIcon("src/main/images/sneaker.jpg");

        this.setIconImage(img.getImage());
    }

    private Image scaleImage(Image image, int w, int h) {
        Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return scaled;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCards = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
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
        pnlDoiHang = new javax.swing.JPanel();
        menuDoiHang = new javax.swing.JLabel();
        btnCardSon = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý bán giày thời trang Sneaker Store");
        setBackground(new java.awt.Color(153, 204, 255));

        pnlCards.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        pnlBanHang.setBackground(new java.awt.Color(153, 204, 255));

        menuBanHang.setBackground(new java.awt.Color(51, 255, 51));
        menuBanHang.setFont(new java.awt.Font("Source Sans Pro Black", 0, 20)); // NOI18N
        menuBanHang.setForeground(new java.awt.Color(255, 255, 255));
        menuBanHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuBanHang.setIcon(new ImageIcon("src/main/images/cart.png"));
        menuBanHang.setText("Bán hàng");
        menuBanHang.setToolTipText("");
        menuBanHang.setInheritsPopupMenu(false);
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
            .addComponent(menuBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlBanHangLayout.setVerticalGroup(
            pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        pnlHoaDon.setBackground(new java.awt.Color(153, 204, 255));

        menuHoaDon.setBackground(new java.awt.Color(51, 255, 51));
        menuHoaDon.setFont(new java.awt.Font("Source Sans Pro Black", 0, 20)); // NOI18N
        menuHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        menuHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuHoaDon.setIcon(new ImageIcon("src/main/images/hoadon.png"));
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
            .addComponent(menuHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlHoaDonLayout.setVerticalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        pnlSanPham.setBackground(new java.awt.Color(153, 204, 255));

        menuSanPham.setBackground(new java.awt.Color(51, 255, 51));
        menuSanPham.setFont(new java.awt.Font("Source Sans Pro Black", 0, 20)); // NOI18N
        menuSanPham.setForeground(new java.awt.Color(255, 255, 255));
        menuSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuSanPham.setIcon(new ImageIcon("src/main/images/product.png"));
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
            .addComponent(menuSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlSanPhamLayout.setVerticalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        pnlKhuyenMai.setBackground(new java.awt.Color(153, 204, 255));

        menuKhuyenMai.setBackground(new java.awt.Color(51, 255, 51));
        menuKhuyenMai.setFont(new java.awt.Font("Source Sans Pro Black", 0, 20)); // NOI18N
        menuKhuyenMai.setForeground(new java.awt.Color(255, 255, 255));
        menuKhuyenMai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuKhuyenMai.setIcon(new ImageIcon("src/main/images/khuyenmai.png"));
        menuKhuyenMai.setText("Khuyến mại");
        menuKhuyenMai.setToolTipText("");
        menuKhuyenMai.setInheritsPopupMenu(false);
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
            .addComponent(menuKhuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
        );
        pnlKhuyenMaiLayout.setVerticalGroup(
            pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuKhuyenMai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        pnlThongKe.setBackground(new java.awt.Color(153, 204, 255));

        menuThongKe.setBackground(new java.awt.Color(51, 255, 51));
        menuThongKe.setFont(new java.awt.Font("Source Sans Pro Black", 0, 20)); // NOI18N
        menuThongKe.setForeground(new java.awt.Color(255, 255, 255));
        menuThongKe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuThongKe.setIcon(new ImageIcon("src/main/images/thongke.png"));
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
            .addComponent(menuThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlThongKeLayout.setVerticalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuThongKe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        pnlNhanVien.setBackground(new java.awt.Color(153, 204, 255));

        menuNhanVien.setBackground(new java.awt.Color(51, 255, 51));
        menuNhanVien.setFont(new java.awt.Font("Source Sans Pro Black", 0, 20)); // NOI18N
        menuNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        menuNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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
            .addComponent(menuNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlNhanVienLayout.setVerticalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        pnlKhachHang.setBackground(new java.awt.Color(153, 204, 255));

        menuKhachHang.setBackground(new java.awt.Color(51, 255, 51));
        menuKhachHang.setFont(new java.awt.Font("Source Sans Pro Black", 0, 20)); // NOI18N
        menuKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        menuKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuKhachHang.setIcon(new ImageIcon("src/main/images/khachhang.png"));
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
            .addComponent(menuKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlKhachHangLayout.setVerticalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuKhachHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        pnlDoiHang.setBackground(new java.awt.Color(153, 204, 255));

        menuDoiHang.setBackground(new java.awt.Color(51, 255, 51));
        menuDoiHang.setFont(new java.awt.Font("Source Sans Pro Black", 0, 20)); // NOI18N
        menuDoiHang.setForeground(new java.awt.Color(255, 255, 255));
        menuDoiHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuDoiHang.setIcon(new ImageIcon("src/main/images/product.png"));
        menuDoiHang.setText("Đổi hàng");
        menuDoiHang.setToolTipText("");
        menuDoiHang.setInheritsPopupMenu(false);
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
            .addComponent(menuDoiHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlDoiHangLayout.setVerticalGroup(
            pnlDoiHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuDoiHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlKhuyenMai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlKhachHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(pnlDoiHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(pnlKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        btnCardSon.setBackground(new java.awt.Color(255, 255, 255));
        btnCardSon.setPreferredSize(new java.awt.Dimension(1292, 784));

        javax.swing.GroupLayout btnCardSonLayout = new javax.swing.GroupLayout(btnCardSon);
        btnCardSon.setLayout(btnCardSonLayout);
        btnCardSonLayout.setHorizontalGroup(
            btnCardSonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1252, Short.MAX_VALUE)
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
                .addComponent(btnCardSon, javax.swing.GroupLayout.DEFAULT_SIZE, 1252, Short.MAX_VALUE))
        );
        pnlCardsLayout.setVerticalGroup(
            pnlCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCardSon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        pnlHoaDon.setBackground(null);
        pnlDoiHang.setBackground(null);
        pnlKhachHang.setBackground(null);
        pnlKhuyenMai.setBackground(null);
        pnlNhanVien.setBackground(null);
        pnlSanPham.setBackground(null);
        pnlThongKe.setBackground(null);
        ViewBanHang viewBanHang = new ViewBanHang();
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
        pnlBanHang.setBackground(null);
        pnlDoiHang.setBackground(null);
        pnlKhachHang.setBackground(null);
        pnlKhuyenMai.setBackground(null);
        pnlNhanVien.setBackground(null);
        pnlSanPham.setBackground(null);
        pnlThongKe.setBackground(null);
        ViewHoaDon viewHoaDon = new ViewHoaDon();
        btnCardSon.removeAll();
        btnCardSon.add(viewHoaDon);
        btnCardSon.setLayout(new FlowLayout());
        this.pack();
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
        pnlHoaDon.setBackground(null);
        pnlDoiHang.setBackground(null);
        pnlKhachHang.setBackground(null);
        pnlKhuyenMai.setBackground(null);
        pnlNhanVien.setBackground(null);
        pnlBanHang.setBackground(null);
        pnlThongKe.setBackground(null);
        ViewSanPham viewSanPham = new ViewSanPham();
        btnCardSon.removeAll();
        btnCardSon.add(viewSanPham);
        btnCardSon.setLayout(new FlowLayout());
        this.pack();
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
        pnlHoaDon.setBackground(null);
        pnlDoiHang.setBackground(null);
        pnlKhachHang.setBackground(null);
        pnlKhuyenMai.setBackground(null);
        pnlNhanVien.setBackground(null);
        pnlBanHang.setBackground(null);
        pnlSanPham.setBackground(null);
        ViewThongKe viewThongKe = new ViewThongKe();
        btnCardSon.removeAll();
        btnCardSon.add(viewThongKe);
        btnCardSon.setLayout(new FlowLayout());
        this.pack();
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
        pnlHoaDon.setBackground(null);
        pnlDoiHang.setBackground(null);
        pnlKhachHang.setBackground(null);
        pnlKhuyenMai.setBackground(null);
        pnlThongKe.setBackground(null);
        pnlBanHang.setBackground(null);
        pnlSanPham.setBackground(null);
        ViewNhanVien viewNhanVien = new ViewNhanVien();
        btnCardSon.removeAll();
        btnCardSon.add(viewNhanVien);
        btnCardSon.setLayout(new FlowLayout());
        this.pack();
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
        pnlHoaDon.setBackground(null);
        pnlDoiHang.setBackground(null);
        pnlNhanVien.setBackground(null);
        pnlKhuyenMai.setBackground(null);
        pnlThongKe.setBackground(null);
        pnlBanHang.setBackground(null);
        pnlSanPham.setBackground(null);
        ViewKhachHang viewKhachHang = new ViewKhachHang();
        btnCardSon.removeAll();
        btnCardSon.add(viewKhachHang);
        btnCardSon.setLayout(new FlowLayout());
        this.pack();
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
        pnlHoaDon.setBackground(null);
        pnlDoiHang.setBackground(null);
        pnlNhanVien.setBackground(null);
        pnlKhachHang.setBackground(null);
        pnlThongKe.setBackground(null);
        pnlBanHang.setBackground(null);
        pnlSanPham.setBackground(null);
        ViewKhuyenMai viewKhuyenMai = new ViewKhuyenMai();
        btnCardSon.removeAll();
        btnCardSon.add(viewKhuyenMai);
        btnCardSon.setLayout(new FlowLayout());
        this.pack();
        btnCardSon.setVisible(true);
    }//GEN-LAST:event_menuKhuyenMaiMouseClicked

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
        pnlHoaDon.setBackground(null);
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
        btnCardSon.setVisible(true);
    }//GEN-LAST:event_menuDoiHangMouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameQuanLy().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnCardSon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel menuBanHang;
    private javax.swing.JLabel menuDoiHang;
    private javax.swing.JLabel menuHoaDon;
    private javax.swing.JLabel menuKhachHang;
    private javax.swing.JLabel menuKhuyenMai;
    private javax.swing.JLabel menuNhanVien;
    private javax.swing.JLabel menuSanPham;
    private javax.swing.JLabel menuThongKe;
    private javax.swing.JPanel pnlBanHang;
    private javax.swing.JPanel pnlCards;
    private javax.swing.JPanel pnlDoiHang;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlKhachHang;
    private javax.swing.JPanel pnlKhuyenMai;
    private javax.swing.JPanel pnlNhanVien;
    private javax.swing.JPanel pnlSanPham;
    private javax.swing.JPanel pnlThongKe;
    // End of variables declaration//GEN-END:variables
}
