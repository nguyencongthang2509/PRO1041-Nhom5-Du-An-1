package core.view;

//import core.quanly.repository.NhanVienRepository;
import com.github.lgooddatepicker.components.DatePicker;
import core.quanly.repository.NhanVienRepository;
import core.quanly.service.NhanVienService;
import core.quanly.service.impl.NhanVienServiceImpl;
import core.quanly.viewmodel.NhanVienNghiResponse;
import core.quanly.viewmodel.NhanVienResponse;
import domainmodels.NhanVien;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
//import infrastructure.constant.VaiTro;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.bind.DatatypeConverter;
import util.EmailSender;
import util.ExportFileExcelNhanVien;
import util.ImportExcelNhanVien;
import util.MauExcelFileNhanVien;

/**
 *
 * @author thangncph26123
 */
public class ViewNhanVien extends javax.swing.JPanel {

    DefaultTableModel model;
    DefaultTableModel modelDaNghi;

    List<NhanVienResponse> listNhanVien;
    List<NhanVienNghiResponse> listNhanVien1;
    List<NhanVienResponse> listNhanVienoooo = new ArrayList<>();
    NhanVienService nhanVienService;
    NhanVienRepository nvnnv;
    NhanVienServiceImpl nvImpl = new NhanVienServiceImpl();
    ImportExcelNhanVien mEx = new ImportExcelNhanVien();
    int index = 0;
    int index1 = 0;

    public ViewNhanVien() {
        initComponents();
        model = (DefaultTableModel) tblNhanVien.getModel();
        modelDaNghi = (DefaultTableModel) tblNhanVien1.getModel();
        listNhanVien = new ArrayList<>();
        listNhanVien1 = new ArrayList<>();
        nhanVienService = new NhanVienServiceImpl();
        nvnnv = new NhanVienRepository();
        loadTable();
        loadTableDaNghi();
        txtMaNhanVien6.setEnabled(false);

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
        btnExport = new javax.swing.JButton();
        btnMauImport = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();
        datePickerNgaysinh = new com.github.lgooddatepicker.components.DatePicker();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        cboFindGioiTinh = new javax.swing.JComboBox<>();
        cboVaiTro = new javax.swing.JComboBox<>();
        jPanel18 = new javax.swing.JPanel();
        txtTimKiembyMa = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhanVien1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        cboFindGioiTinh1 = new javax.swing.JComboBox<>();
        cboVaiTro1 = new javax.swing.JComboBox<>();
        jPanel20 = new javax.swing.JPanel();
        txtTimKiembyMa2 = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1292, 784));
        setMinimumSize(new java.awt.Dimension(1292, 784));
        setPreferredSize(new java.awt.Dimension(1292, 784));

        jLabel10.setText("Tim Kiem");
        jLabel10.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(1292, 784));
        jPanel2.setMinimumSize(new java.awt.Dimension(1292, 784));
        jPanel2.setPreferredSize(new java.awt.Dimension(1292, 784));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập  thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 16))); // NOI18N

        jLabel60.setText("Mã nhân viên:");
        jLabel60.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        jLabel61.setText("Tên nhân viên:");
        jLabel61.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        jLabel62.setText("SDT:");
        jLabel62.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        jLabel63.setText("Địa chỉ:");
        jLabel63.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        jLabel64.setText("Email:");
        jLabel64.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        txtMaNhanVien6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtMaNhanVien6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNhanVienActionPerformed(evt);
            }
        });

        txtTenNhanVien6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtSdtNhanVien6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtDiaChiNhanVien6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtEmail6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel65.setText("Ngày sinh:");
        jLabel65.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        btnSua7.setText("Sửa");
        btnSua7.setBackground(new java.awt.Color(153, 204, 255));
        btnSua7.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        btnSua7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThem6.setText("Thêm");
        btnThem6.setBackground(new java.awt.Color(153, 204, 255));
        btnThem6.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        btnThem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnNew6.setText("Clear");
        btnNew6.setBackground(new java.awt.Color(153, 204, 255));
        btnNew6.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        btnNew6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel11.setText("Trạng Thái");
        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

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
        rdoNhanVien6.setText("Nhân viên");

        rdoQuanLy6.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoQuanLy6);
        rdoQuanLy6.setText("Quản lý");

        jLabel67.setText("Vai trò");
        jLabel67.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        rdoNu6.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNu6);
        rdoNu6.setText("Nữ");

        rdoNam6.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNam6);
        rdoNam6.setSelected(true);
        rdoNam6.setText("Nam");

        jLabel66.setText("Giới tính");
        jLabel66.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel67)
                    .addComponent(jLabel66))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addComponent(rdoQuanLy6)
                    .addComponent(rdoNam6))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton2)
                    .addComponent(rdoNhanVien6)
                    .addComponent(rdoNu6))
                .addGap(20, 20, 20))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoNam6)
                    .addComponent(rdoNu6)
                    .addComponent(jLabel66))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(rdoQuanLy6)
                    .addComponent(rdoNhanVien6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton1)
                        .addComponent(jRadioButton2))
                    .addComponent(jLabel11))
                .addGap(47, 47, 47))
        );

        btnExport.setText("Export");
        btnExport.setBackground(new java.awt.Color(153, 204, 255));
        btnExport.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportbtnSuaActionPerformed(evt);
            }
        });

        btnMauImport.setText("Tải Mẫu");
        btnMauImport.setBackground(new java.awt.Color(153, 204, 255));
        btnMauImport.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        btnMauImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMauImportbtnSuaActionPerformed(evt);
            }
        });

        btnImport.setText("Import");
        btnImport.setBackground(new java.awt.Color(153, 204, 255));
        btnImport.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportbtnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel61)
                    .addComponent(jLabel60)
                    .addComponent(jLabel62))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtTenNhanVien6)
                    .addComponent(txtMaNhanVien6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                    .addComponent(txtSdtNhanVien6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(55, 55, 55)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel65)
                    .addComponent(jLabel64)
                    .addComponent(jLabel63))
                .addGap(48, 48, 48)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiaChiNhanVien6, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addComponent(txtEmail6)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(btnThem6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnNew6, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                            .addComponent(btnMauImport))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(btnExport)
                                .addGap(18, 18, 18)
                                .addComponent(btnSua7))))
                    .addComponent(datePickerNgaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnNew6, btnThem6});

        jPanel16Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnExport, btnSua7});

        jPanel16Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnImport, btnMauImport});

        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(txtDiaChiNhanVien6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEmail6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(datePickerNgaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel60)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel61)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel62)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem6)
                            .addComponent(btnNew6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSua7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMauImport, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel63)
                            .addComponent(txtMaNhanVien6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTenNhanVien6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSdtNhanVien6)
                                    .addComponent(jLabel65)))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel64)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 115, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel16Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnNew6, btnSua7, btnThem6});

        jPanel16Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtDiaChiNhanVien6, txtEmail6, txtMaNhanVien6, txtSdtNhanVien6, txtTenNhanVien6});

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMaximumSize(new java.awt.Dimension(1292, 784));
        jPanel4.setMinimumSize(new java.awt.Dimension(1292, 784));
        jPanel4.setPreferredSize(new java.awt.Dimension(1292, 784));

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã NV", "Tên NV", "Giới Tính", "Ngày Sinh", "Địa Chỉ", "SDT", "Email", "Vai Trò", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.setRowHeight(25);
        tblNhanVien.setSelectionBackground(new java.awt.Color(86, 154, 222));
        tblNhanVien.getTableHeader().setReorderingAllowed(false);
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        cboFindGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "Nam", "Nữ" }));
        cboFindGioiTinh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboFindGioiTinhItemStateChanged(evt);
            }
        });
        cboFindGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFindGioiTinhActionPerformed(evt);
            }
        });

        cboVaiTro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "Quản Lý", "Nhân Viên" }));
        cboVaiTro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboVaiTroItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(cboFindGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(cboVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboFindGioiTinh, cboVaiTro});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboFindGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm Kiếm"));

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
                .addComponent(txtTimKiembyMa, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiembyMa)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(414, Short.MAX_VALUE))
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

        tblNhanVien1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã NV", "Tên NV", "Giới Tình", "Ngày Sinh", "Địa Chỉ", "SDT", "Email", "Vai Trò", "Trạng Thái"
            }
        ));
        tblNhanVien1.setRowHeight(25);
        tblNhanVien1.setSelectionBackground(new java.awt.Color(86, 154, 222));
        tblNhanVien1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVien1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNhanVien1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        cboFindGioiTinh1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "Nam", "Nữ" }));
        cboFindGioiTinh1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboFindGioiTinh1ItemStateChanged(evt);
            }
        });
        cboFindGioiTinh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFindGioiTinh1ActionPerformed(evt);
            }
        });

        cboVaiTro1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "Quản Lý", "Nhân Viên" }));
        cboVaiTro1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboVaiTro1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(cboFindGioiTinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(cboVaiTro1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboFindGioiTinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboVaiTro1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm Kiếm"));

        txtTimKiembyMa2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiembyMa2CaretUpdate(evt);
            }
        });
        txtTimKiembyMa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiembyMa2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiembyMa2, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiembyMa2)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Nghỉ việc", jPanel5);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1369, 1369, 1369)
                .addComponent(jLabel10))
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1289, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187)
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
        try {
            index = tblNhanVien.getSelectedRow();
            NhanVienResponse nvr = nhanVienService.getAllResponse().get(index);
            if (index >= 0) {
                txtMaNhanVien6.setText(tblNhanVien.getValueAt(index, 1).toString());
                txtEmail6.setText(tblNhanVien.getValueAt(index, 7).toString());
                txtDiaChiNhanVien6.setText(tblNhanVien.getValueAt(index, 5).toString());
                txtSdtNhanVien6.setText(tblNhanVien.getValueAt(index, 6).toString());
                txtTenNhanVien6.setText(tblNhanVien.getValueAt(index, 2).toString());
                String date = tblNhanVien.getValueAt(index, 4).toString();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date ngaySinh = simpleDateFormat.parse(date);
                String ngaySinhStr = simpleDateFormat.format(ngaySinh);
                datePickerNgaysinh.setDate(LocalDate.parse(ngaySinhStr));
                String gioitinh = tblNhanVien.getValueAt(index, 3).toString();
                if (gioitinh.equalsIgnoreCase("Nam")) {
                    rdoNam6.setSelected(true);
                    rdoNu6.setSelected(false);
                } else {
                    rdoNu6.setSelected(true);
                    rdoNam6.setSelected(false);
                }
                String vaitro = tblNhanVien.getValueAt(index, 8).toString();
                if (vaitro.equalsIgnoreCase("Nhân Viên")) {
                    rdoNhanVien6.setSelected(true);
                    rdoQuanLy6.setSelected(false);
                } else {
                    rdoQuanLy6.setSelected(true);
                    rdoNhanVien6.setSelected(false);
                }
                String trangThaiXoa = tblNhanVien.getValueAt(index, 9).toString();
                if (trangThaiXoa.equalsIgnoreCase("Đang Làm")) {
                    jRadioButton1.setSelected(true);
                    jRadioButton2.setSelected(false);
                } else {
                    jRadioButton2.setSelected(true);
                    jRadioButton1.setSelected(false);
                }

            }
            loadTableDaNghi();
        } catch (Exception e) {
            e.printStackTrace();
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
            if (datePickerNgaysinh.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng không để trống ngày sinh");
                return;
            }

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
            JOptionPane.showMessageDialog(this, message);

            loadTable();

            loadTableDaNghi();
            NewForm();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed

        try {
            NhanVien nv = getformData();

//            if (tblNhanVien.getSelectedRow() < 0||tblNhanVien1.getSelectedRow()<0) {
//                JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào");
//                return;
//            } 
            if (tblNhanVien.getSelectedRow() >= 0) {
                nv.setId(listNhanVien.get(tblNhanVien.getSelectedRow()).getId());
                String message = nhanVienService.update(nv);
                JOptionPane.showMessageDialog(this, message);

            }
            if (tblNhanVien1.getSelectedRow() >= 0) {
                nv.setId(listNhanVien1.get(tblNhanVien1.getSelectedRow()).getId());
                String message = nhanVienService.update(nv);
                JOptionPane.showMessageDialog(this, message);

            }
            loadTable();
            loadTableDaNghi();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtMaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNhanVienActionPerformed

    private void tblNhanVien1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVien1MouseClicked
        try {
            index1 = tblNhanVien1.getSelectedRow();
            NhanVienResponse nvr = nhanVienService.getAllResponse().get(index1);
            if (index1 >= 0) {
                txtMaNhanVien6.setText(tblNhanVien1.getValueAt(index1, 1).toString());
                txtEmail6.setText(tblNhanVien1.getValueAt(index1, 7).toString());
                txtDiaChiNhanVien6.setText(tblNhanVien1.getValueAt(index1, 5).toString());
                txtSdtNhanVien6.setText(tblNhanVien1.getValueAt(index1, 6).toString());
                txtTenNhanVien6.setText(tblNhanVien1.getValueAt(index1, 2).toString());
                String date = tblNhanVien1.getValueAt(index1, 4).toString();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date ngaySinh = simpleDateFormat.parse(date);
                String ngaySinhStr = simpleDateFormat.format(ngaySinh);
                datePickerNgaysinh.setDate(LocalDate.parse(ngaySinhStr));
                String gioitinh = tblNhanVien1.getValueAt(index1, 3).toString();
                if (gioitinh.equalsIgnoreCase("Nam")) {
                    rdoNam6.setSelected(true);
                    rdoNu6.setSelected(false);
                } else {
                    rdoNu6.setSelected(true);
                    rdoNam6.setSelected(false);
                }
                String vaitro = tblNhanVien1.getValueAt(index1, 8).toString();
                if (vaitro.equalsIgnoreCase("Nhân Viên")) {
                    rdoNhanVien6.setSelected(true);
                    rdoQuanLy6.setSelected(false);
                } else {
                    rdoQuanLy6.setSelected(true);
                    rdoNhanVien6.setSelected(false);
                }
                String trangThaiXoa = tblNhanVien1.getValueAt(index1, 9).toString();
                if (trangThaiXoa.equalsIgnoreCase("Đang Làm")) {
                    jRadioButton1.setSelected(true);
                    jRadioButton2.setSelected(false);
                } else {
                    jRadioButton2.setSelected(true);
                    jRadioButton1.setSelected(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_tblNhanVien1MouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked

    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked

    }//GEN-LAST:event_jPanel5MouseClicked

    private void btnExportbtnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportbtnSuaActionPerformed
        try {
            ExportFileExcelNhanVien export = new ExportFileExcelNhanVien();
            listNhanVienoooo = nhanVienService.getAllResponse();
            boolean check = export.ExportExcel(listNhanVienoooo);

            if (check) {
                JOptionPane.showMessageDialog(this, "Export File Excel thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Export File Excel thất bại");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnExportbtnSuaActionPerformed

    private void btnMauImportbtnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMauImportbtnSuaActionPerformed
        try {
            MauExcelFileNhanVien importFile = new MauExcelFileNhanVien();
            boolean check = importFile.ImportExcel();
            if (check) {
                JOptionPane.showMessageDialog(this, "Tải mẫu Excel thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Tải mẫu Excel thất bại");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnMauImportbtnSuaActionPerformed

    private void btnImportbtnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportbtnSuaActionPerformed
        try {

            JFileChooser fc = new JFileChooser();
            int check = fc.showOpenDialog(null);
            File file = null;
            if (check == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
            }
            ImportExcelNhanVien excelNV = new ImportExcelNhanVien();
            try {
                excelNV.ImportFile(file.getAbsolutePath());
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            listNhanVienoooo = nvnnv.getAllResponseNhanVienLam();
            loadTable();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }//GEN-LAST:event_btnImportbtnSuaActionPerformed

    private void cboFindGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFindGioiTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboFindGioiTinhActionPerformed

    private void cboFindGioiTinh1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboFindGioiTinh1ItemStateChanged

    }//GEN-LAST:event_cboFindGioiTinh1ItemStateChanged

    private void cboFindGioiTinh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFindGioiTinh1ActionPerformed
        String query = cboFindGioiTinh1.getSelectedItem().toString();
        Filter1(query);
    }//GEN-LAST:event_cboFindGioiTinh1ActionPerformed

    private void cboVaiTro1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboVaiTro1ItemStateChanged
        String query = cboVaiTro1.getSelectedItem().toString();
        Filter1(query);
    }//GEN-LAST:event_cboVaiTro1ItemStateChanged

    private void txtTimKiembyMa2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiembyMa2CaretUpdate
        try {
            LoadFindNhanVienNghi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtTimKiembyMa2CaretUpdate

    private void txtTimKiembyMa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiembyMa2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiembyMa2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnMauImport;
    private javax.swing.JButton btnNew6;
    private javax.swing.JButton btnSua7;
    private javax.swing.JButton btnThem6;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cboFindGioiTinh;
    private javax.swing.JComboBox<String> cboFindGioiTinh1;
    private javax.swing.JComboBox<String> cboVaiTro;
    private javax.swing.JComboBox<String> cboVaiTro1;
    private com.github.lgooddatepicker.components.DatePicker datePickerNgaysinh;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdoNam6;
    private javax.swing.JRadioButton rdoNhanVien6;
    private javax.swing.JRadioButton rdoNu6;
    private javax.swing.JRadioButton rdoQuanLy6;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTable tblNhanVien1;
    private javax.swing.JTextField txtDiaChiNhanVien6;
    private javax.swing.JTextField txtEmail6;
    private javax.swing.JTextField txtMaNhanVien6;
    private javax.swing.JTextField txtSdtNhanVien6;
    private javax.swing.JTextField txtTenNhanVien6;
    private javax.swing.JTextField txtTimKiembyMa;
    private javax.swing.JTextField txtTimKiembyMa2;
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
        nv.setMa("NV" + nvnnv.genMaNhanVien());

        nv.setTen(txtTenNhanVien6.getText().trim());
        nv.setGioiTinh(rdoNam6.isSelected() ? 0 : 1);
        nv.setNgaySinh(java.sql.Date.valueOf(datePickerNgaysinh.getDate()));

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
        datePickerNgaysinh.setText("");
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
        if (query != "Tất Cả") {
            tr.setRowFilter(RowFilter.regexFilter(query));
        } else {
            tblNhanVien.setRowSorter(tr);
        }
    }

    private void Filter1(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modelDaNghi);
        tblNhanVien1.setRowSorter(tr);

        //Check filter query
        if (query != "Tất Cả") {
            tr.setRowFilter(RowFilter.regexFilter(query));
        } else {
            tblNhanVien1.setRowSorter(tr);
        }
    }

    private void loadTableDaNghi() {
        modelDaNghi.setRowCount(0);
        int index = 1;
        listNhanVien1 = nvnnv.getAllResponseNhanVienNghi();
        for (NhanVienNghiResponse xx : listNhanVien1) {
            modelDaNghi.addRow(xx.toDataRow(index));
            index++;
        }
    }

    private void LoadFindNhanVienNghi() {
        modelDaNghi = (DefaultTableModel) tblNhanVien1.getModel();
        modelDaNghi.setRowCount(0);
        int index = 1;
        List<NhanVienNghiResponse> nhanVienNghiRespon = nvImpl.getListbyAll(txtTimKiembyMa2.getText());
        for (NhanVienNghiResponse xx : nhanVienNghiRespon) {
            modelDaNghi.addRow(xx.toDataRow(index));
            index++;

        }
    }

}
