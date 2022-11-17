package view;

import antlr.Utils;
import javax.swing.ImageIcon;

/**
 *
 * @author thangncph26123
 */
public class JFrameQuanLy extends javax.swing.JFrame {

    public JFrameQuanLy() {
        initComponents();
        setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCards = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnBanHang = new com.k33ptoo.components.KButton();
        btnHoaDon = new com.k33ptoo.components.KButton();
        btnSanPham = new com.k33ptoo.components.KButton();
        btnKhuyenMai = new com.k33ptoo.components.KButton();
        btnDoiHang = new com.k33ptoo.components.KButton();
        btnThongKe = new com.k33ptoo.components.KButton();
        btnNhanVien = new com.k33ptoo.components.KButton();
        btnKhachHang = new com.k33ptoo.components.KButton();
        btnDangXuat = new com.k33ptoo.components.KButton();
        jLabel1 = new javax.swing.JLabel();
        btnCardSon = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý bán giày thời trang Sneaker Store");
        setBackground(new java.awt.Color(153, 204, 255));

        pnlCards.setBackground(new java.awt.Color(153, 204, 255));

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        btnBanHang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnBanHang.setText("Bán hàng");
        btnBanHang.setToolTipText("");
        btnBanHang.setFont(new java.awt.Font("Roboto Slab Black", 0, 24)); // NOI18N
        btnBanHang.setkBackGroundColor(new java.awt.Color(255, 204, 204));
        btnBanHang.setkEndColor(new java.awt.Color(153, 153, 255));
        btnBanHang.setkHoverEndColor(new java.awt.Color(255, 102, 102));
        btnBanHang.setkHoverForeGround(new java.awt.Color(204, 204, 255));
        btnBanHang.setkHoverStartColor(new java.awt.Color(204, 204, 255));
        btnBanHang.setkSelectedColor(new java.awt.Color(255, 153, 153));
        btnBanHang.setkStartColor(new java.awt.Color(255, 153, 153));

        btnHoaDon.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnHoaDon.setText("Hóa đơn");
        btnHoaDon.setToolTipText("");
        btnHoaDon.setFont(new java.awt.Font("Roboto Slab Black", 0, 24)); // NOI18N
        btnHoaDon.setkBackGroundColor(new java.awt.Color(255, 204, 204));
        btnHoaDon.setkEndColor(new java.awt.Color(153, 153, 255));
        btnHoaDon.setkHoverEndColor(new java.awt.Color(255, 102, 102));
        btnHoaDon.setkHoverForeGround(new java.awt.Color(204, 204, 255));
        btnHoaDon.setkHoverStartColor(new java.awt.Color(204, 204, 255));
        btnHoaDon.setkSelectedColor(new java.awt.Color(255, 153, 153));
        btnHoaDon.setkStartColor(new java.awt.Color(255, 153, 153));

        btnSanPham.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnSanPham.setText("Sản phẩm");
        btnSanPham.setToolTipText("");
        btnSanPham.setFont(new java.awt.Font("Roboto Slab Black", 0, 24)); // NOI18N
        btnSanPham.setkBackGroundColor(new java.awt.Color(255, 204, 204));
        btnSanPham.setkEndColor(new java.awt.Color(153, 153, 255));
        btnSanPham.setkHoverEndColor(new java.awt.Color(255, 102, 102));
        btnSanPham.setkHoverForeGround(new java.awt.Color(204, 204, 255));
        btnSanPham.setkHoverStartColor(new java.awt.Color(204, 204, 255));
        btnSanPham.setkSelectedColor(new java.awt.Color(255, 153, 153));
        btnSanPham.setkStartColor(new java.awt.Color(255, 153, 153));

        btnKhuyenMai.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnKhuyenMai.setText("Khuyến mại");
        btnKhuyenMai.setToolTipText("");
        btnKhuyenMai.setFont(new java.awt.Font("Roboto Slab Black", 0, 24)); // NOI18N
        btnKhuyenMai.setkBackGroundColor(new java.awt.Color(255, 204, 204));
        btnKhuyenMai.setkEndColor(new java.awt.Color(153, 153, 255));
        btnKhuyenMai.setkHoverEndColor(new java.awt.Color(255, 102, 102));
        btnKhuyenMai.setkHoverForeGround(new java.awt.Color(204, 204, 255));
        btnKhuyenMai.setkHoverStartColor(new java.awt.Color(204, 204, 255));
        btnKhuyenMai.setkSelectedColor(new java.awt.Color(255, 153, 153));
        btnKhuyenMai.setkStartColor(new java.awt.Color(255, 153, 153));

        btnDoiHang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDoiHang.setText("Đổi hàng");
        btnDoiHang.setToolTipText("");
        btnDoiHang.setFont(new java.awt.Font("Roboto Slab Black", 0, 24)); // NOI18N
        btnDoiHang.setkBackGroundColor(new java.awt.Color(255, 204, 204));
        btnDoiHang.setkEndColor(new java.awt.Color(153, 153, 255));
        btnDoiHang.setkHoverEndColor(new java.awt.Color(255, 102, 102));
        btnDoiHang.setkHoverForeGround(new java.awt.Color(204, 204, 255));
        btnDoiHang.setkHoverStartColor(new java.awt.Color(204, 204, 255));
        btnDoiHang.setkSelectedColor(new java.awt.Color(255, 153, 153));
        btnDoiHang.setkStartColor(new java.awt.Color(255, 153, 153));

        btnThongKe.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnThongKe.setText("Thống kê");
        btnThongKe.setToolTipText("");
        btnThongKe.setFont(new java.awt.Font("Roboto Slab Black", 0, 24)); // NOI18N
        btnThongKe.setkBackGroundColor(new java.awt.Color(255, 204, 204));
        btnThongKe.setkEndColor(new java.awt.Color(153, 153, 255));
        btnThongKe.setkHoverEndColor(new java.awt.Color(255, 102, 102));
        btnThongKe.setkHoverForeGround(new java.awt.Color(204, 204, 255));
        btnThongKe.setkHoverStartColor(new java.awt.Color(204, 204, 255));
        btnThongKe.setkSelectedColor(new java.awt.Color(255, 153, 153));
        btnThongKe.setkStartColor(new java.awt.Color(255, 153, 153));

        btnNhanVien.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnNhanVien.setText("Nhân viên");
        btnNhanVien.setToolTipText("");
        btnNhanVien.setFont(new java.awt.Font("Roboto Slab Black", 0, 24)); // NOI18N
        btnNhanVien.setkBackGroundColor(new java.awt.Color(255, 204, 204));
        btnNhanVien.setkEndColor(new java.awt.Color(153, 153, 255));
        btnNhanVien.setkHoverEndColor(new java.awt.Color(255, 102, 102));
        btnNhanVien.setkHoverForeGround(new java.awt.Color(204, 204, 255));
        btnNhanVien.setkHoverStartColor(new java.awt.Color(204, 204, 255));
        btnNhanVien.setkSelectedColor(new java.awt.Color(255, 153, 153));
        btnNhanVien.setkStartColor(new java.awt.Color(255, 153, 153));

        btnKhachHang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnKhachHang.setText("Khách hàng");
        btnKhachHang.setToolTipText("");
        btnKhachHang.setFont(new java.awt.Font("Roboto Slab Black", 0, 24)); // NOI18N
        btnKhachHang.setkBackGroundColor(new java.awt.Color(255, 204, 204));
        btnKhachHang.setkEndColor(new java.awt.Color(153, 153, 255));
        btnKhachHang.setkHoverEndColor(new java.awt.Color(255, 102, 102));
        btnKhachHang.setkHoverForeGround(new java.awt.Color(204, 204, 255));
        btnKhachHang.setkHoverStartColor(new java.awt.Color(204, 204, 255));
        btnKhachHang.setkSelectedColor(new java.awt.Color(255, 153, 153));
        btnKhachHang.setkStartColor(new java.awt.Color(255, 153, 153));

        btnDangXuat.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.setToolTipText("");
        btnDangXuat.setFont(new java.awt.Font("Roboto Slab Black", 0, 24)); // NOI18N
        btnDangXuat.setkBackGroundColor(new java.awt.Color(255, 204, 204));
        btnDangXuat.setkEndColor(new java.awt.Color(153, 153, 255));
        btnDangXuat.setkHoverEndColor(new java.awt.Color(255, 102, 102));
        btnDangXuat.setkHoverForeGround(new java.awt.Color(204, 204, 255));
        btnDangXuat.setkHoverStartColor(new java.awt.Color(204, 204, 255));
        btnDangXuat.setkSelectedColor(new java.awt.Color(255, 153, 153));
        btnDangXuat.setkStartColor(new java.awt.Color(255, 153, 153));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
            .addComponent(btnSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnKhuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnDoiHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnKhachHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnDangXuat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout btnCardSonLayout = new javax.swing.GroupLayout(btnCardSon);
        btnCardSon.setLayout(btnCardSonLayout);
        btnCardSonLayout.setHorizontalGroup(
            btnCardSonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1292, Short.MAX_VALUE)
        );
        btnCardSonLayout.setVerticalGroup(
            btnCardSonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 784, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlCardsLayout = new javax.swing.GroupLayout(pnlCards);
        pnlCards.setLayout(pnlCardsLayout);
        pnlCardsLayout.setHorizontalGroup(
            pnlCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCardsLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCardSon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCardsLayout.setVerticalGroup(
            pnlCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCardSon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCards, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private com.k33ptoo.components.KButton btnBanHang;
    private javax.swing.JPanel btnCardSon;
    private com.k33ptoo.components.KButton btnDangXuat;
    private com.k33ptoo.components.KButton btnDoiHang;
    private com.k33ptoo.components.KButton btnHoaDon;
    private com.k33ptoo.components.KButton btnKhachHang;
    private com.k33ptoo.components.KButton btnKhuyenMai;
    private com.k33ptoo.components.KButton btnNhanVien;
    private com.k33ptoo.components.KButton btnSanPham;
    private com.k33ptoo.components.KButton btnThongKe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnlCards;
    // End of variables declaration//GEN-END:variables
}
