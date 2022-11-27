package core.view;

//import core.quanly.repository.NhanVienRepository;
import core.quanly.repository.NhanVienRepository;
import core.quanly.service.NhanVienService;
import core.quanly.service.impl.NhanVienServiceImpl;
import core.quanly.viewmodel.NhanVienResponse;
import domainmodels.NhanVien;
import java.security.MessageDigest;
//import infrastructure.constant.VaiTro;
import java.text.SimpleDateFormat;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.bind.DatatypeConverter;
import util.EmailSender;

/**
 *
 * @author thangncph26123
 */
public class ViewNhanVien extends javax.swing.JPanel {

    DefaultTableModel model;
    DefaultTableModel modelDaNghi;
    
    List<NhanVienResponse> listNhanVien;
    NhanVienService nhanVienService;
    NhanVienRepository nvnnv;
    int index = 0;

    public ViewNhanVien() {
        initComponents();
        model = (DefaultTableModel) tblNhanVien.getModel();
        modelDaNghi = (DefaultTableModel) tblNhanVien1.getModel();
        listNhanVien = new ArrayList<>();
        nhanVienService = new NhanVienServiceImpl();
        loadTable();
        loadTableDaNghi();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        txtMaNhanVien6 = new javax.swing.JTextField();
        txtTenNhanVien6 = new javax.swing.JTextField();
        txtSdtNhanVien6 = new javax.swing.JTextField();
        txtDiaChiNhanVien6 = new javax.swing.JTextField();
        txtEmail6 = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        btnSua7 = new javax.swing.JButton();
        btnThem6 = new javax.swing.JButton();
        btnNew6 = new javax.swing.JButton();
        txtNgaySinh6 = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        rdoNhanVien6 = new javax.swing.JRadioButton();
        rdoQuanLy6 = new javax.swing.JRadioButton();
        jLabel67 = new javax.swing.JLabel();
        rdoNu6 = new javax.swing.JRadioButton();
        rdoNam6 = new javax.swing.JRadioButton();
        jLabel66 = new javax.swing.JLabel();
        cboVaiTro = new javax.swing.JComboBox<>();
        cboFindGioiTinh = new javax.swing.JComboBox<>();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhanVien1 = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        txtTimKiembyMa = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1292, 784));
        setMinimumSize(new java.awt.Dimension(1292, 784));
        setPreferredSize(new java.awt.Dimension(1292, 784));

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel10.setText("Tim Kiem");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(1292, 784));
        jPanel2.setMinimumSize(new java.awt.Dimension(1292, 784));
        jPanel2.setPreferredSize(new java.awt.Dimension(1292, 784));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập  thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 16))); // NOI18N

        jLabel60.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel60.setText("Ma");

        jLabel61.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel61.setText("Ten");

        jLabel62.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel62.setText("SDT");

        jLabel63.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel63.setText("Dia Chi");

        jLabel64.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel64.setText("Email");

        txtMaNhanVien6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNhanVienActionPerformed(evt);
            }
        });

        jLabel65.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel65.setText("Ngay Sinh");

        btnSua7.setBackground(new java.awt.Color(153, 204, 255));
        btnSua7.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        btnSua7.setText("Update");
        btnSua7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThem6.setBackground(new java.awt.Color(153, 204, 255));
        btnThem6.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        btnThem6.setText("Create");
        btnThem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnNew6.setBackground(new java.awt.Color(153, 204, 255));
        btnNew6.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        btnNew6.setText("New");
        btnNew6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel11.setText("Trạng Thái");

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Đang Làm");

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(jRadioButton2);
        jRadioButton2.setText("Đã Nghỉ");

        rdoNhanVien6.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoNhanVien6);
        rdoNhanVien6.setSelected(true);
        rdoNhanVien6.setText("Nhan Vien");

        rdoQuanLy6.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoQuanLy6);
        rdoQuanLy6.setText("Quan Ly");

        jLabel67.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel67.setText("Vai Tro");

        rdoNu6.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNu6);
        rdoNu6.setText("Female");

        rdoNam6.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNam6);
        rdoNam6.setSelected(true);
        rdoNam6.setText("Male");

        jLabel66.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel66.setText("Gioi Tinh");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel66)
                        .addGap(21, 21, 21)
                        .addComponent(rdoNam6)
                        .addGap(19, 19, 19)
                        .addComponent(rdoNu6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdoQuanLy6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoNhanVien6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton1)
                        .addGap(29, 29, 29)
                        .addComponent(jRadioButton2)
                        .addGap(20, 20, 20))))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoNam6)
                    .addComponent(rdoNu6)
                    .addComponent(jLabel66))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(rdoQuanLy6)
                    .addComponent(rdoNhanVien6))
                .addGap(41, 41, 41)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jLabel11))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(192, 192, 192)
                                .addComponent(jLabel62))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(208, 208, 208)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel61)
                                    .addComponent(jLabel60))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenNhanVien6, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaNhanVien6, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSdtNhanVien6, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel64, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel65, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel63, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(30, 30, 30))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnThem6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNew6)
                            .addComponent(btnSua7, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(78, 78, 78)))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiaChiNhanVien6, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addComponent(txtEmail6, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addComponent(txtNgaySinh6, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(155, 155, 155))
        );

        jPanel16Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnNew6, btnSua7, btnThem6});

        jPanel16Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtDiaChiNhanVien6, txtEmail6, txtMaNhanVien6, txtNgaySinh6, txtSdtNhanVien6, txtTenNhanVien6});

        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel60)
                        .addComponent(txtDiaChiNhanVien6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel63))
                    .addComponent(txtMaNhanVien6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel61)
                        .addComponent(txtEmail6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel64))
                    .addComponent(txtTenNhanVien6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNgaySinh6, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                        .addComponent(jLabel65))
                    .addComponent(jLabel62)
                    .addComponent(txtSdtNhanVien6))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnNew6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btnThem6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(32, Short.MAX_VALUE))))
        );

        jPanel16Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnNew6, btnSua7, btnThem6});

        jPanel16Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtDiaChiNhanVien6, txtEmail6, txtMaNhanVien6, txtNgaySinh6, txtSdtNhanVien6, txtTenNhanVien6});

        cboVaiTro.setBackground(new java.awt.Color(153, 153, 255));
        cboVaiTro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Quan Ly", "Nhan Vien" }));
        cboVaiTro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboVaiTroItemStateChanged(evt);
            }
        });

        cboFindGioiTinh.setBackground(new java.awt.Color(255, 255, 255));
        cboFindGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Male", "Female" }));
        cboFindGioiTinh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboFindGioiTinhItemStateChanged(evt);
            }
        });

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        tblNhanVien.setBackground(new java.awt.Color(204, 255, 255));
        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Ma", "Ten", "Gioi Tinh", "Ngay Sinh", "Diachi", "SDT", "Email", "Vai Tro", "Trang Thái"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1275, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Đang làm", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        tblNhanVien1.setBackground(new java.awt.Color(204, 255, 255));
        tblNhanVien1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Ma", "Ten", "Gioi Tinh", "Ngay Sinh", "Diachi", "SDT", "Email", "Vai Tro"
            }
        ));
        tblNhanVien1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVien1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNhanVien1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1275, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Nghỉ việc", jPanel5);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm Kiếm"));

        txtTimKiembyMa.setBackground(new java.awt.Color(204, 204, 255));
        txtTimKiembyMa.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiembyMaCaretUpdate(evt);
            }
        });
        txtTimKiembyMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiembyMaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiembyMa, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiembyMa)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(397, 397, 397)
                        .addComponent(cboVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(cboFindGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(188, 188, 188)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1)))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboFindGioiTinh, cboVaiTro});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboFindGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboFindGioiTinh, cboVaiTro});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1369, 1369, 1369)
                .addComponent(jLabel10))
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(184, 184, 184)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiembyMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiembyMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiembyMaActionPerformed

    private void txtTimKiembyMaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiembyMaCaretUpdate
        try {
            LoadFindNhanVien();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtTimKiembyMaCaretUpdate

    private void cboVaiTroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboVaiTroItemStateChanged
        //combobox selection
        String query = cboVaiTro.getSelectedItem().toString();
        Filter(query);
    }//GEN-LAST:event_cboVaiTroItemStateChanged

    private void cboFindGioiTinhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboFindGioiTinhItemStateChanged
        //combobox selection
        String query = cboFindGioiTinh.getSelectedItem().toString();
        Filter(query);
    }//GEN-LAST:event_cboFindGioiTinhItemStateChanged

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        index = tblNhanVien.getSelectedRow();
        NhanVienResponse nvr = nhanVienService.getAllResponse().get(index);
        if (index >= 0) {
            txtMaNhanVien6.setText(nvr.getMa());
            txtEmail6.setText(nvr.getEmail());
            txtDiaChiNhanVien6.setText(nvr.getDiachi());
            txtSdtNhanVien6.setText(nvr.getSdt());
            txtTenNhanVien6.setText(nvr.getTen());
            txtNgaySinh6.setText(nvr.getNgaysinh().toString());
            if (nvr.getGioitinh() == 0) {
                rdoNam6.setSelected(true);
            } else if (nvr.getGioitinh() == 1) {
                rdoNu6.setSelected(true);
            }
            if (nvr.getVaitro() == 0) {
                rdoQuanLy6.setSelected(true);
            } else if (nvr.getVaitro() == 1) {
                rdoNhanVien6.setSelected(true);
            }
            if (nvr.getTrangthaixoa()== 0) {
                jRadioButton1.setSelected(true);
            } else if (nvr.getTrangthaixoa() == 1) {
                jRadioButton2.setSelected(true);
            }

        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        try {
            NewForm();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            NhanVien nv = new NhanVien();
            nv = getformData();
            String password = String.valueOf((int) (Math.random() * 100000000));
            //Send to Email
            EmailSender.emailSender(nv.getEmail(), "Thông Báo Mật Khẩu Nhân Viên Mới", "Password: " + password);

            //Mã Hóa Mật Khẩu
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter
            .printHexBinary(digest).toUpperCase();
            System.out.println(myHash);
            nv.setMatKhau(myHash);

            String message = nhanVienService.insert(nv);

            loadTable();
            JOptionPane.showMessageDialog(this, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
            NhanVien nv = getformData();
            System.out.println(nv.getTrangThaiXoa());
            nv.setId(listNhanVien.get(tblNhanVien.getSelectedRow()).getId());
            String message = nhanVienService.update(nv);
            JOptionPane.showMessageDialog(this, message);
            loadTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtMaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNhanVienActionPerformed

    private void tblNhanVien1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVien1MouseClicked
        
    }//GEN-LAST:event_tblNhanVien1MouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
       
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        
    }//GEN-LAST:event_jPanel5MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNew1;
    private javax.swing.JButton btnNew2;
    private javax.swing.JButton btnNew3;
    private javax.swing.JButton btnNew4;
    private javax.swing.JButton btnNew5;
    private javax.swing.JButton btnNew6;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua2;
    private javax.swing.JButton btnSua3;
    private javax.swing.JButton btnSua4;
    private javax.swing.JButton btnSua5;
    private javax.swing.JButton btnSua6;
    private javax.swing.JButton btnSua7;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnThem2;
    private javax.swing.JButton btnThem3;
    private javax.swing.JButton btnThem4;
    private javax.swing.JButton btnThem5;
    private javax.swing.JButton btnThem6;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cboFindGioiTinh;
    private javax.swing.JComboBox<String> cboVaiTro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNam1;
    private javax.swing.JRadioButton rdoNam2;
    private javax.swing.JRadioButton rdoNam3;
    private javax.swing.JRadioButton rdoNam4;
    private javax.swing.JRadioButton rdoNam5;
    private javax.swing.JRadioButton rdoNam6;
    private javax.swing.JRadioButton rdoNhanVien;
    private javax.swing.JRadioButton rdoNhanVien1;
    private javax.swing.JRadioButton rdoNhanVien2;
    private javax.swing.JRadioButton rdoNhanVien3;
    private javax.swing.JRadioButton rdoNhanVien4;
    private javax.swing.JRadioButton rdoNhanVien5;
    private javax.swing.JRadioButton rdoNhanVien6;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdoNu1;
    private javax.swing.JRadioButton rdoNu2;
    private javax.swing.JRadioButton rdoNu3;
    private javax.swing.JRadioButton rdoNu4;
    private javax.swing.JRadioButton rdoNu5;
    private javax.swing.JRadioButton rdoNu6;
    private javax.swing.JRadioButton rdoQuanLy;
    private javax.swing.JRadioButton rdoQuanLy1;
    private javax.swing.JRadioButton rdoQuanLy2;
    private javax.swing.JRadioButton rdoQuanLy3;
    private javax.swing.JRadioButton rdoQuanLy4;
    private javax.swing.JRadioButton rdoQuanLy5;
    private javax.swing.JRadioButton rdoQuanLy6;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTable tblNhanVien1;
    private javax.swing.JTextField txtDiaChiNhanVien;
    private javax.swing.JTextField txtDiaChiNhanVien1;
    private javax.swing.JTextField txtDiaChiNhanVien2;
    private javax.swing.JTextField txtDiaChiNhanVien3;
    private javax.swing.JTextField txtDiaChiNhanVien4;
    private javax.swing.JTextField txtDiaChiNhanVien5;
    private javax.swing.JTextField txtDiaChiNhanVien6;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JTextField txtEmail2;
    private javax.swing.JTextField txtEmail3;
    private javax.swing.JTextField txtEmail4;
    private javax.swing.JTextField txtEmail5;
    private javax.swing.JTextField txtEmail6;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtMaNhanVien1;
    private javax.swing.JTextField txtMaNhanVien2;
    private javax.swing.JTextField txtMaNhanVien3;
    private javax.swing.JTextField txtMaNhanVien4;
    private javax.swing.JTextField txtMaNhanVien5;
    private javax.swing.JTextField txtMaNhanVien6;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtNgaySinh1;
    private javax.swing.JTextField txtNgaySinh2;
    private javax.swing.JTextField txtNgaySinh3;
    private javax.swing.JTextField txtNgaySinh4;
    private javax.swing.JTextField txtNgaySinh5;
    private javax.swing.JTextField txtNgaySinh6;
    private javax.swing.JTextField txtSdtNhanVien;
    private javax.swing.JTextField txtSdtNhanVien1;
    private javax.swing.JTextField txtSdtNhanVien2;
    private javax.swing.JTextField txtSdtNhanVien3;
    private javax.swing.JTextField txtSdtNhanVien4;
    private javax.swing.JTextField txtSdtNhanVien5;
    private javax.swing.JTextField txtSdtNhanVien6;
    private javax.swing.JTextField txtTenNhanVien;
    private javax.swing.JTextField txtTenNhanVien1;
    private javax.swing.JTextField txtTenNhanVien2;
    private javax.swing.JTextField txtTenNhanVien3;
    private javax.swing.JTextField txtTenNhanVien4;
    private javax.swing.JTextField txtTenNhanVien5;
    private javax.swing.JTextField txtTenNhanVien6;
    private javax.swing.JTextField txtTimKiembyMa;
    // End of variables declaration//GEN-END:variables

    private void loadTable() {
        model.setRowCount(0);
        int index = 1;
        listNhanVien = nhanVienService.getAllResponseLam();
        for (NhanVienResponse xx : listNhanVien) {
            model.addRow(xx.toDataRow(index));
            index++;
        }
    }

    private NhanVien getformData() {
        NhanVien nv = new NhanVien();
        nv.setMa(txtMaNhanVien6.getText().trim());
        nv.setTen(txtTenNhanVien6.getText().trim());
        nv.setGioiTinh(rdoNam6.isSelected() ? 0 : 1);
        SimpleDateFormat dateF = new SimpleDateFormat("dd-mm-yyyy");
        String ll = txtNgaySinh6.getText().trim();
        try {
            Date date = dateF.parse(ll);
            nv.setNgaySinh(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        nv.setDiaChi(txtDiaChiNhanVien6.getText().trim());
        nv.setSdt(txtSdtNhanVien6.getText().trim());
        nv.setEmail(txtEmail6.getText().trim());

        nv.setVaiTro(rdoQuanLy6.isSelected() ? 0 : 1);
        nv.setTrangThaiXoa(jRadioButton1.isSelected() ? 0 : 1);

        return nv;
    }

    private void NewForm() {
        txtMaNhanVien6.setText("");
        txtTenNhanVien6.setText("");
        txtDiaChiNhanVien6.setText("");
        txtEmail6.setText("");
        txtSdtNhanVien6.setText("");
        txtNgaySinh6.setText("");
        txtTimKiembyMa.setText("");
    }

    private void LoadFindNhanVien() {//Tim Kiem Theo Ma Nhan Vien
        model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        int index = 1;
        List<NhanVienResponse> nhanVienRespon = this.nhanVienService.GetAllByMa(txtTimKiembyMa.getText());
        for (NhanVienResponse xx : nhanVienRespon) {
            model.addRow(xx.toDataRow(index));
            index++;

        }

    }

    private void Filter(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        tblNhanVien.setRowSorter(tr);
        //Check filter query
        if (query != "None") {
            tr.setRowFilter(RowFilter.regexFilter(query));
        } else {
            tblNhanVien.setRowSorter(tr);
        }
    }

    private void loadTableDaNghi() {
        modelDaNghi.setRowCount(0);
        int index = 1;
        listNhanVien = nhanVienService.getAllResponseNghi();
        for (NhanVienResponse xx : listNhanVien) {
            modelDaNghi.addRow(xx.toDataRow(index));
            index++;
        }
    }

}
