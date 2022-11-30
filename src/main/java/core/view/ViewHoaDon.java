/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package core.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DateTimePicker;
import core.quanly.service.HoaDonservice;
import core.quanly.service.impl.HoaDonserviceImpl;
import core.quanly.viewmodel.HdHoaDonChiTietResponse1;
import core.quanly.viewmodel.HdHoaDonResponse1;
import core.quanly.viewmodel.HdHoaDonResponse2;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author longnh26222
 */
public class ViewHoaDon extends javax.swing.JPanel {

    private HoaDonservice hoadonservice = new HoaDonserviceImpl();
    private DefaultTableModel modelHoaDon;
    private DefaultTableModel modelHoaDon2;
    private DefaultTableModel modelHoaDonChiTiet;
    private DefaultTableModel modelHoaDonChiTiet2;
    private List<HdHoaDonResponse1> listHoaDon;
    private List<HdHoaDonResponse2> listHoaDon2;
    private List<HdHoaDonChiTietResponse1> listHDCT;

    public ViewHoaDon() {
        initComponents();
        this.modelHoaDon = (DefaultTableModel) tblHoadon.getModel();
        this.modelHoaDon2 = (DefaultTableModel) tblHD2.getModel();
        this.loadTableHoaDon();
        this.loadTableHoaDon2();
    }

    public void loadTableHoaDon() {
        modelHoaDon.setRowCount(0);
        listHoaDon = hoadonservice.getList(0);
        for (HdHoaDonResponse1 h : listHoaDon) {
            modelHoaDon.addRow(h.toDaTaRow());
        }
    }

    public void loadTableHoaDon2() {
        try {
            modelHoaDon2.setRowCount(0);
            listHoaDon2 = hoadonservice.getList2(1);
            for (HdHoaDonResponse2 h : listHoaDon2) {
                modelHoaDon2.addRow(h.toDaTaRow());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTableHDbyText() {
        modelHoaDon.setRowCount(0);
        listHoaDon = hoadonservice.getListbyText(0, txtSearch.getText());
        for (HdHoaDonResponse1 h : listHoaDon) {
            modelHoaDon.addRow(h.toDaTaRow());
        }
    }

    public void loadTableHD2byText() {
        modelHoaDon2.setRowCount(0);
        listHoaDon2 = hoadonservice.getListHD2byText(txtSearch2.getText(), 1);
        for (HdHoaDonResponse2 h : listHoaDon2) {
            modelHoaDon2.addRow(h.toDaTaRow());
        }
    }

    public void loadTblHD1byTime() {
        modelHoaDon.setRowCount(0);
        DatePicker time1 = txtTime1.getDatePicker();
        DatePicker time2 = txtTime2.getDatePicker();
        Date time3 = java.sql.Date.valueOf(time1.getDate());
        Date time4 = java.sql.Date.valueOf(time2.getDate());
        listHoaDon = hoadonservice.getListbyTime(time3, time4, 0);
        for (HdHoaDonResponse1 h : listHoaDon) {
            modelHoaDon.addRow(h.toDaTaRow());
        }
    }

    public void loadTblHD2byTime() {
        modelHoaDon2.setRowCount(0);
        DatePicker time1 = txtTime3.getDatePicker();
        DatePicker time2 = txtTime4.getDatePicker();
        Date time3 = java.sql.Date.valueOf(time1.getDate());
        Date time4 = java.sql.Date.valueOf(time2.getDate());
        listHoaDon2 = hoadonservice.getList2byTime(time3, time4, 1);
        for (HdHoaDonResponse2 h : listHoaDon2) {
            modelHoaDon2.addRow(h.toDaTaRow());
        }
        System.out.println(listHoaDon);
    }

    public void loadTableHoaDonChiTiet() {
        int row = this.tblHoadon.getSelectedRow();
        if (row == -1) {
            return;
        }
        String mahd = this.tblHoadon.getValueAt(row, 0).toString();
        modelHoaDonChiTiet = (DefaultTableModel) tblHoadonchitiet.getModel();
        modelHoaDonChiTiet.setRowCount(0);
        listHDCT = hoadonservice.getListHDCT(mahd);
        if (listHDCT == null) {
            return;
        }
        for (HdHoaDonChiTietResponse1 hd : listHDCT) {
            modelHoaDonChiTiet.addRow(hd.toDataRow());
        }
    }

    public void loadTableHoaDonChiTiet2() {
        int row = this.tblHD2.getSelectedRow();
        if (row == -1) {
            return;
        }
        String mahd = this.tblHD2.getValueAt(row, 0).toString();
        modelHoaDonChiTiet2 = (DefaultTableModel) tblHoaDonChiTiet2.getModel();
        modelHoaDonChiTiet2.setRowCount(0);
        listHDCT = hoadonservice.getListHDCT(mahd);
        if (listHDCT == null) {
            return;
        }
        for (HdHoaDonChiTietResponse1 hd : listHDCT) {
            modelHoaDonChiTiet2.addRow(hd.toDataRow());
        }
    }

    public void resetTableHDCT() {
        if (tblHoadonchitiet.getRowCount() > 0) {
            modelHoaDonChiTiet.setRowCount(0);
        }
    }

    public void resetTableHDCT2() {
        if (tblHoaDonChiTiet2.getRowCount() > 0) {
            modelHoaDonChiTiet2.setRowCount(0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpb1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cboTrangthaithanhtoan = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cboHinhthucthanhtoan = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoadon = new javax.swing.JTable();
        txtTime1 = new com.github.lgooddatepicker.components.DateTimePicker();
        txtTime2 = new com.github.lgooddatepicker.components.DateTimePicker();
        btnLoadByTime = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoadonchitiet = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtSearch2 = new javax.swing.JTextField();
        cboTrangThai2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cboHinhThucTT2 = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHD2 = new javax.swing.JTable();
        txtTime4 = new com.github.lgooddatepicker.components.DateTimePicker();
        txtTime3 = new com.github.lgooddatepicker.components.DateTimePicker();
        btnLoadByTime2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet2 = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1292, 784));
        setMinimumSize(new java.awt.Dimension(1292, 784));
        setPreferredSize(new java.awt.Dimension(1292, 784));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tpb1.setBackground(new java.awt.Color(255, 255, 255));
        tpb1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tpb1MouseClicked(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel1.setToolTipText("");

        jLabel1.setText("Tìm kiếm hóa đơn:");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        txtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchCaretUpdate(evt);
            }
        });

        jLabel2.setText("Trạng thái hóa đơn:");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        cboTrangthaithanhtoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Chờ thanh toán", "Đã hủy", "Đã thanh toán", " " }));
        cboTrangthaithanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangthaithanhtoanActionPerformed(evt);
            }
        });

        jLabel4.setText("Hình thức thanh toán:");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        cboHinhthucthanhtoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Tiền mặt", "Chuyển khoản" }));
        cboHinhthucthanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHinhthucthanhtoanActionPerformed(evt);
            }
        });

        tblHoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Ngày tạo", "Ngày thanh toán", "Hình thức thanh toán", "Tổng tiền", "Tiền khách CK", "Tiền khách trả", "Tiền thừa", "Mã NV", "Tên NV", "Tên KH", "SĐT KH", "Đia chỉ", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoadon.setShowGrid(false);
        tblHoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoadonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoadon);

        btnLoadByTime.setText("Lọc");
        btnLoadByTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadByTimeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cboTrangthaithanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboHinhthucthanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTime1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addComponent(txtTime2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLoadByTime)
                        .addGap(0, 183, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(cboHinhthucthanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboTrangthaithanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTime1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTime2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLoadByTime)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(1268, 350));

        tblHoadonchitiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Hãng", "Màu", "Size", "Số lượng", "Đơn giá", "Giá bán", "Giảm giá KM", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoadonchitiet.setShowGrid(false);
        tblHoadonchitiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoadonchitietMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoadonchitiet);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1271, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        tpb1.addTab("Hóa đơn tại quầy", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(1285, 578));

        txtSearch2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearch2CaretUpdate(evt);
            }
        });
        txtSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearch2ActionPerformed(evt);
            }
        });

        cboTrangThai2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đang giao", "Đã giao" }));
        cboTrangThai2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThai2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Trạng thái hóa đơn:");
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel7.setText("Hình thức thanh toán:");
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel8.setText("Tìm kiếm hóa đơn:");
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        cboHinhThucTT2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Tiền mặt", "Chuyển khoản" }));
        cboHinhThucTT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHinhThucTT2ActionPerformed(evt);
            }
        });

        tblHD2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Ngày tạo", "Ngày thanh toán", "Hình thức thanh toán", "Thành tiền", "Tiền khách CK", "Tiền khách trả", "Tiền thừa", "Mã NV", "Tên NV", "Người nhận", "SĐT NN", "Địa chỉ", "SĐT người ship", "Tên người ship", "Tiền ship", "Ngày ship", "Ngày nhận", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHD2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHD2MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblHD2);

        btnLoadByTime2.setText("Lọc");
        btnLoadByTime2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadByTime2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(144, 144, 144)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cboTrangThai2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboHinhThucTT2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTime3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTime4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLoadByTime2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 213, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboTrangThai2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboHinhThucTT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTime4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTime3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoadByTime2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(488, 350));

        tblHoaDonChiTiet2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Hãng", "Màu", "Size", "Số lượng", "Đơn giá", "Giá bán", "Giảm giá KM", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblHoaDonChiTiet2);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(201, 201, 201))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 1285, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpb1.addTab("Hóa đơn đặt hàng", jPanel5);

        add(tpb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 780));
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoadonchitietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoadonchitietMouseClicked

    }//GEN-LAST:event_tblHoadonchitietMouseClicked

    private void cboTrangthaithanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangthaithanhtoanActionPerformed
        resetTableHDCT();
        if (cboTrangthaithanhtoan.getSelectedIndex() == 0) {
            loadTableHoaDon();
        }
        if (cboTrangthaithanhtoan.getSelectedIndex() > 0) {
            int trangthai = cboTrangthaithanhtoan.getSelectedIndex();
            modelHoaDon.setRowCount(0);
            listHoaDon = hoadonservice.getListbyTrangThai(trangthai - 1, 0);
            for (HdHoaDonResponse1 h : listHoaDon) {
                modelHoaDon.addRow(h.toDaTaRow());
            }
        }
    }//GEN-LAST:event_cboTrangthaithanhtoanActionPerformed

    private void tblHoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoadonMouseClicked
       loadTableHoaDonChiTiet();
    }//GEN-LAST:event_tblHoadonMouseClicked

    private void cboHinhthucthanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHinhthucthanhtoanActionPerformed
        resetTableHDCT();
        if (cboHinhthucthanhtoan.getSelectedIndex() == 0) {
            loadTableHoaDon();
        }
        if (cboHinhthucthanhtoan.getSelectedIndex() > 0) {
            int hinhthuctt = cboHinhthucthanhtoan.getSelectedIndex();
            modelHoaDon.setRowCount(0);
            listHoaDon = hoadonservice.getListbyHinhThucThanhToan(hinhthuctt - 1, 0);
            for (HdHoaDonResponse1 h : listHoaDon) {
                modelHoaDon.addRow(h.toDaTaRow());
            }
        }
    }//GEN-LAST:event_cboHinhthucthanhtoanActionPerformed

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate
        resetTableHDCT();
        try {
            loadTableHDbyText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtSearchCaretUpdate

    private void tpb1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpb1MouseClicked

    }//GEN-LAST:event_tpb1MouseClicked

    private void tblHD2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHD2MouseClicked
       loadTableHoaDonChiTiet2();
    }//GEN-LAST:event_tblHD2MouseClicked

    private void txtSearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearch2ActionPerformed

    }//GEN-LAST:event_txtSearch2ActionPerformed

    private void txtSearch2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearch2CaretUpdate
        resetTableHDCT2();
        try {
            loadTableHD2byText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtSearch2CaretUpdate

    private void cboTrangThai2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThai2ActionPerformed
        resetTableHDCT2();
        if (cboTrangThai2.getSelectedIndex() == 0) {
            loadTableHoaDon2();
        }
        if (cboTrangThai2.getSelectedIndex() > 0) {
            int trangthai = cboTrangThai2.getSelectedIndex();
            modelHoaDon2.setRowCount(0);
            listHoaDon2 = hoadonservice.getList2byTrangThai(trangthai + 2, 1);
            for (HdHoaDonResponse2 h : listHoaDon2) {
                modelHoaDon2.addRow(h.toDaTaRow());
            }
        }
    }//GEN-LAST:event_cboTrangThai2ActionPerformed

    private void cboHinhThucTT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHinhThucTT2ActionPerformed
        resetTableHDCT2();
        if (cboHinhThucTT2.getSelectedIndex() == 0) {
            loadTableHoaDon2();
        }
        if (cboHinhThucTT2.getSelectedIndex() > 0) {
            int hinhthuctt = cboHinhThucTT2.getSelectedIndex();
            modelHoaDon2.setRowCount(0);
            listHoaDon2 = hoadonservice.getList2byHinhThucThanhToan(hinhthuctt - 1, 1);
            for (HdHoaDonResponse2 h : listHoaDon2) {
                modelHoaDon2.addRow(h.toDaTaRow());
            }
        }
    }//GEN-LAST:event_cboHinhThucTT2ActionPerformed

    private void btnLoadByTime2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadByTime2ActionPerformed
        resetTableHDCT2();
        if (txtTime3.getDatePicker().getDate() == null || txtTime4.getDatePicker().getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn mốc thời gian");
            return;
        }
        loadTblHD2byTime();
    }//GEN-LAST:event_btnLoadByTime2ActionPerformed

    private void btnLoadByTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadByTimeActionPerformed
        resetTableHDCT();
        if (txtTime1.getDatePicker().getDate() == null || txtTime2.getDatePicker().getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn mốc thời gian");
            return;
        }
        loadTblHD1byTime();
    }//GEN-LAST:event_btnLoadByTimeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoadByTime;
    private javax.swing.JButton btnLoadByTime2;
    private javax.swing.JComboBox<String> cboHinhThucTT2;
    private javax.swing.JComboBox<String> cboHinhthucthanhtoan;
    private javax.swing.JComboBox<String> cboTrangThai2;
    private javax.swing.JComboBox<String> cboTrangthaithanhtoan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tblHD2;
    private javax.swing.JTable tblHoaDonChiTiet2;
    private javax.swing.JTable tblHoadon;
    private javax.swing.JTable tblHoadonchitiet;
    private javax.swing.JTabbedPane tpb1;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch2;
    private com.github.lgooddatepicker.components.DateTimePicker txtTime1;
    private com.github.lgooddatepicker.components.DateTimePicker txtTime2;
    private com.github.lgooddatepicker.components.DateTimePicker txtTime3;
    private com.github.lgooddatepicker.components.DateTimePicker txtTime4;
    // End of variables declaration//GEN-END:variables
}
