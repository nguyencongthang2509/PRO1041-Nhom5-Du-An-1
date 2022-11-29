/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package core.view;

import com.github.lgooddatepicker.components.DatePicker;
import core.quanly.service.KhachHangService;
import java.util.List;
import core.quanly.viewmodel.KhachHangRespone;
import core.quanly.service.impl.KhachHangServiceImpl;
import core.quanly.viewmodel.KhachHangLichSuRespone;
import javax.swing.table.DefaultTableModel;
import domainmodels.KhachHang;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JOptionPane;

/**
 *
 * @author thangncph26123
 */
public class ViewKhachHang extends javax.swing.JPanel {

    KhachHangService khachhang;
    DefaultTableModel mol = new DefaultTableModel();
    List<KhachHangRespone> listKhachHang;
    List<KhachHangLichSuRespone> listLS;
    DatePicker date;
    DefaultComboBoxModel cbom = new DefaultComboBoxModel();

    public ViewKhachHang() {
        initComponents();
        khachhang = new KhachHangServiceImpl();
        listKhachHang = khachhang.getAllResponse();
        fillTable(listKhachHang);
    }

    public void fillTable(List<KhachHangRespone> list) {
        mol = (DefaultTableModel) tbl_khachhang.getModel();
        mol.setRowCount(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int stt = 1;
        for (KhachHangRespone x : list) {
            mol.addRow(new Object[]{stt++, x.getMa(), x.getHoTen(), x.getGioiTinh() == 0 ? "Nam" : "Nữ", x.getSdt(), x.getDiaChi(),
                x.getEmail(), dateFormat.format(x.getNgaySinh())});
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        btn_clear = new javax.swing.JButton();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_makh = new javax.swing.JTextField();
        txt_hoten = new javax.swing.JTextField();
        txt_sdt = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        txt_diachi = new javax.swing.JTextField();
        btn_them = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        btn_clear1 = new javax.swing.JButton();
        txt_ngaysinh = new com.github.lgooddatepicker.components.DatePicker();
        rdo_nam = new javax.swing.JRadioButton();
        rdo_nu = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        txt_timkiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_khachhang = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        cbo_gioitinh = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_lichsugiaodich = new javax.swing.JTable();

        btn_clear.setBackground(new java.awt.Color(255, 102, 102));
        btn_clear.setText("CLEAR");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1292, 784));
        setMinimumSize(new java.awt.Dimension(1292, 784));
        setPreferredSize(new java.awt.Dimension(1292, 784));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel1.setText("Mã KH:");
        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel2.setText("Họ và tên:");
        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel3.setText("SĐT:");
        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel4.setText("Địa chỉ:");
        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel5.setText("Email:");
        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel6.setText("Giới tính:");
        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel8.setText("Ngày sinh:");
        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        txt_makh.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_makh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(51, 51, 51)));

        txt_hoten.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_hoten.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));

        txt_sdt.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_sdt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(51, 51, 51)));
        txt_sdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sdtActionPerformed(evt);
            }
        });

        txt_email.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_email.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(51, 51, 51)));

        txt_diachi.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_diachi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(51, 51, 51)));

        btn_them.setText("THÊM");
        btn_them.setBackground(new java.awt.Color(153, 204, 255));
        btn_them.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_sua.setText("SỬA");
        btn_sua.setBackground(new java.awt.Color(153, 204, 255));
        btn_sua.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        btn_clear1.setText("CLEAR");
        btn_clear1.setBackground(new java.awt.Color(153, 204, 255));
        btn_clear1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btn_clear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear1ActionPerformed(evt);
            }
        });

        txt_ngaysinh.setBackground(new java.awt.Color(255, 255, 255));
        txt_ngaysinh.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        buttonGroup1.add(rdo_nam);
        rdo_nam.setText("Nam");
        rdo_nam.setBackground(new java.awt.Color(255, 255, 255));
        rdo_nam.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        buttonGroup1.add(rdo_nu);
        rdo_nu.setText("Nữ");
        rdo_nu.setBackground(new java.awt.Color(255, 255, 255));
        rdo_nu.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(146, 146, 146)
                        .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(131, 131, 131)
                        .addComponent(btn_clear1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(223, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_makh, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(9, 9, 9))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(66, 66, 66)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_hoten, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdo_nam)
                                        .addGap(62, 62, 62)
                                        .addComponent(rdo_nu))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(65, 65, 65)
                                .addComponent(txt_ngaysinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(73, 73, 73)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_diachi)
                            .addComponent(txt_email)
                            .addComponent(txt_sdt))
                        .addGap(58, 58, 58))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(44, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(85, 85, 85))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txt_makh, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel2)))
                                    .addComponent(txt_hoten))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(rdo_nam)
                            .addComponent(rdo_nu))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(txt_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(87, 87, 87)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_them)
                    .addComponent(btn_sua)
                    .addComponent(btn_clear1))
                .addGap(33, 33, 33))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jTabbedPane3.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTabbedPane3.setInheritsPopupMenu(true);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        txt_timkiem.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_timkiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(51, 51, 51)));
        txt_timkiem.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        txt_timkiem.setForeground(new java.awt.Color(204, 204, 204));
        txt_timkiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_timkiemCaretUpdate(evt);
            }
        });
        txt_timkiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_timkiemFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_timkiemFocusLost(evt);
            }
        });
        txt_timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timkiemActionPerformed(evt);
            }
        });

        tbl_khachhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã KH", "Họ và tên", "Giới tính", "SĐT", "Địa chỉ", "Email", "Ngày sinh"
            }
        ));
        tbl_khachhang.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        tbl_khachhang.setGridColor(new java.awt.Color(255, 255, 255));
        tbl_khachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_khachhangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_khachhang);

        jLabel12.setText("Giới tính:");
        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        cbo_gioitinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        cbo_gioitinh.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cbo_gioitinh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_gioitinhItemStateChanged(evt);
            }
        });

        jLabel7.setText("Tìm kiếm:");
        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel7))
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_timkiem)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(cbo_gioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 963, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_timkiem, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_gioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Thông tin khách hàng", jPanel6);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tbl_lichsugiaodich.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên KH", "SĐT", "Giới tính", "Mã HĐ", "Ngày giao dịch", "Tổng tiền", "Trạng thái"
            }
        ));
        tbl_lichsugiaodich.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        tbl_lichsugiaodich.setGridColor(new java.awt.Color(255, 255, 255));
        tbl_lichsugiaodich.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_lichsugiaodichMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_lichsugiaodich);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1192, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Lịch sử giao dịch", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_sdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sdtActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sdtActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        KhachHang khachhangg = getKhachHangByForm();
        int cf = JOptionPane.showConfirmDialog(this, "Ban muon them?");
        if (cf == 0) {
            String mess = khachhang.insert(khachhangg);
            listKhachHang = khachhang.getAllResponse();
            fillTable(listKhachHang);
            JOptionPane.showMessageDialog(this, mess);
        }
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
        KhachHang khachhangg = getKhachHangByForm();
        int chon = tbl_khachhang.getSelectedRow();
        if (chon < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào");
        } else {
            int cf = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa?");
            if (cf == 0) {
                khachhangg.setId(listKhachHang.get(chon).getId());
                String mess = khachhang.update(khachhangg);
                listKhachHang = khachhang.getAllResponse();
                fillTable(listKhachHang);
                JOptionPane.showMessageDialog(this, mess);
            }
        }
    }//GEN-LAST:event_btn_suaActionPerformed

    private void txt_timkiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_timkiemCaretUpdate
        // TODO add your handling code here:
        listKhachHang = khachhang.findKhachHangByMaOrTen(txt_timkiem.getText());
        fillTable(listKhachHang);
    }//GEN-LAST:event_txt_timkiemCaretUpdate

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        // TODO add your handling code here: 
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_clear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear1ActionPerformed
        // TODO add your handling code here:
        txt_diachi.setText(null);
        txt_email.setText(null);
        txt_hoten.setText(null);
        txt_makh.setText(null);
        txt_sdt.setText(null);
    }//GEN-LAST:event_btn_clear1ActionPerformed

    private void tbl_khachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_khachhangMouseClicked
        // TODO add your handling code here:
        int index = tbl_khachhang.getSelectedRow();
        txt_makh.setText(tbl_khachhang.getValueAt(index, 1).toString());
        txt_hoten.setText(tbl_khachhang.getValueAt(index, 2).toString());
        String gioitinh = tbl_khachhang.getValueAt(index, 3).toString();
        if (gioitinh.equalsIgnoreCase("Nam")) {
            rdo_nam.setSelected(true);
            rdo_nu.setSelected(false);
        } else {
            rdo_nu.setSelected(true);
            rdo_nam.setSelected(false);
        }
        txt_sdt.setText(tbl_khachhang.getValueAt(index, 4).toString());
        txt_diachi.setText(tbl_khachhang.getValueAt(index, 5).toString());
        txt_email.setText(tbl_khachhang.getValueAt(index, 6).toString());
        txt_ngaysinh.setDate(LocalDate.parse(tbl_khachhang.getValueAt(index, 7).toString()));

        String id = tbl_khachhang.getValueAt(index, 1).toString();
        listLS = khachhang.getKhachHangByLichSu(id);
        mol = (DefaultTableModel) tbl_lichsugiaodich.getModel();
        mol.setRowCount(0);
        for (KhachHangLichSuRespone x : listLS) {
            mol.addRow(x.toDaTaRow());
        }
        System.out.println(listLS);
    }//GEN-LAST:event_tbl_khachhangMouseClicked

    private void tbl_lichsugiaodichMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_lichsugiaodichMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_lichsugiaodichMouseClicked

    private void txt_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timkiemActionPerformed

    private void txt_timkiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_timkiemFocusGained

    }//GEN-LAST:event_txt_timkiemFocusGained

    private void txt_timkiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_timkiemFocusLost

    }//GEN-LAST:event_txt_timkiemFocusLost

    private void cbo_gioitinhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_gioitinhItemStateChanged
        int gt = cbo_gioitinh.getSelectedIndex();
        mol.setRowCount(0);
        List<KhachHangRespone> list = khachhang.getLoadCbbGioiTinh(gt);
        if (list.isEmpty()) {
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int stt = 1;
        for (KhachHangRespone x : list) {
            mol.addRow(new Object[]{stt++, x.getMa(), x.getHoTen(), x.getGioiTinh() == 0 ? "Nam" : "Nữ", x.getSdt(), x.getDiaChi(),
                x.getEmail(), dateFormat.format(x.getNgaySinh())});
        }
    }//GEN-LAST:event_cbo_gioitinhItemStateChanged

    public KhachHang getKhachHangByForm() {
        KhachHang kh = new KhachHang();
        kh.setMa(txt_makh.getText());
        kh.setHoTen(txt_hoten.getText());
        int tt;
        if (rdo_nam.isSelected()) {
            tt = 0;
        } else {
            tt = 1;
        }
        kh.setGioiTinh(tt);
        kh.setEmail(txt_email.getText());
        kh.setDiaChi(txt_diachi.getText());
        kh.setSdt(txt_sdt.getText());
        kh.setNgaySinh(java.sql.Date.valueOf(txt_ngaysinh.getDate()));
        return kh;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_clear1;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbo_gioitinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JRadioButton rdo_nam;
    private javax.swing.JRadioButton rdo_nu;
    private javax.swing.JTable tbl_khachhang;
    private javax.swing.JTable tbl_lichsugiaodich;
    private javax.swing.JTextField txt_diachi;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_hoten;
    private javax.swing.JTextField txt_makh;
    private com.github.lgooddatepicker.components.DatePicker txt_ngaysinh;
    private javax.swing.JTextField txt_sdt;
    private javax.swing.JTextField txt_timkiem;
    // End of variables declaration//GEN-END:variables
}
