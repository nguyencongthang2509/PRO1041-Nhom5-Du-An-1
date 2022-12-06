///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package util;
//
//import core.quanly.repository.KhachHangRespository;
//import core.quanly.viewmodel.KhuyenMaiResponse;
//import domainmodels.ChatLieu;
//import domainmodels.ChiTietSP;
//import domainmodels.ChiTietSPKhuyenMai;
//import domainmodels.Hang;
//import domainmodels.HoaDon;
//import domainmodels.HoaDonChiTiet;
//import domainmodels.HoaDonTraHang;
//import domainmodels.HoaDonTraHangChiTiet;
//import domainmodels.KhachHang;
//import domainmodels.KhuyenMai;
//import domainmodels.KichThuoc;
//import domainmodels.MauSac;
//import domainmodels.NhanVien;
//import domainmodels.SanPham;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//import javax.swing.JOptionPane;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.cfg.Environment;
//import org.hibernate.query.Query;
//import org.hibernate.service.ServiceRegistry;
//import static util.DailyCheckingKhuyenMai.getSession;
//
///**
// *
// * @author thangncph26123
// */
//public class DailyCheckingRankCustomer extends Thread {
//
//    private static final SessionFactory FACTORY;
//    private static Session SESSION;
//    private Session session;
//    private Transaction TRANS;
//
//    static {
//        Configuration conf = new Configuration();
//
//        Properties properties = new Properties();
//        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
//        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=du_an_1");
//        properties.put(Environment.USER, "sa");
//        properties.put(Environment.PASS, "123456");
//        properties.put(Environment.SHOW_SQL, "true");
////        properties.put(Environment.HBM2DDL_AUTO, "create");//gen DB tự động
//
//        conf.addAnnotatedClass(SanPham.class);
//        conf.addAnnotatedClass(KhuyenMai.class);
//        conf.addAnnotatedClass(MauSac.class);
//        conf.addAnnotatedClass(ChatLieu.class);
//        conf.addAnnotatedClass(KichThuoc.class);
//        conf.addAnnotatedClass(Hang.class);
//        conf.addAnnotatedClass(NhanVien.class);
//        conf.addAnnotatedClass(KhachHang.class);
//        conf.addAnnotatedClass(HoaDon.class);
//        conf.addAnnotatedClass(ChiTietSP.class);
//        conf.addAnnotatedClass(ChiTietSPKhuyenMai.class);
//        conf.addAnnotatedClass(HoaDonChiTiet.class);
//        conf.addAnnotatedClass(HoaDonTraHang.class);
//        conf.addAnnotatedClass(HoaDonTraHangChiTiet.class);
//
//        conf.setProperties(properties);
//
//        ServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .applySettings(conf.getProperties()).build();
//        FACTORY = conf.buildSessionFactory(registry);
//
//    }
//
//    public static Session getSession() {
//        if (SESSION == null || !SESSION.isConnected()) {
//            SESSION = FACTORY.openSession();
//        }
//        return SESSION;
//    }
//
//    public List<KhachHang> getAllKhacHangDangHoatDong() {
//        List<KhachHang> list = new ArrayList<>();
//        try {
//            session = new DailyCheckingRankCustomer().getSession();
//            String hql = "SELECT a FROM KhachHang a where a.trangThaiXoa = 0 AND a.ma <> 'KH000'";
//            Query query = session.createQuery(hql);
//            list = query.getResultList();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public BigDecimal getTongTienByIdKhachHang(String id) {
//        BigDecimal money = new BigDecimal(0);
//        try {
//            session = new DailyCheckingRankCustomer().getSession();
//            String hql = "SELECT sum(a.thanhTien) FROM HoaDon a where a.khachHang.id = :id";
//            Query query = session.createQuery(hql);
//            query.setParameter("id", id);
//            money = (BigDecimal) query.getSingleResult();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return money;
//    }
//
//    public KhachHang saveOrUpdateKH(KhachHang entity) {
//        try {
//            session = new DailyCheckingRankCustomer().getSession();
//            TRANS = session.beginTransaction();
//            session.saveOrUpdate(entity);
//            TRANS.commit();
//            session.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return entity;
//    }
//
//    public static void dailyCheckingRankCustomer() {
//        Thread countDownThread = new Thread() {
//            @Override
//            public void run() {
//                do {
////                    List<KhachHang> listKH = new ArrayList<>();
//                    List<KhachHang> listKhachHang = new DailyCheckingRankCustomer().getAllKhacHangDangHoatDong();
//                    for (KhachHang xx : listKhachHang) {
//                        BigDecimal tienKhachMua = new DailyCheckingRankCustomer().getTongTienByIdKhachHang(xx.getId());
//                        if (tienKhachMua == null) {
//                            continue;
//                        }
//                        if (tienKhachMua != null) {
//                            if (tienKhachMua.compareTo(new BigDecimal(3000000)) < 0) {
//                                xx.setCapBac(0);
//                            }
//                            if (tienKhachMua.compareTo(new BigDecimal(3000000)) > 0 && tienKhachMua.compareTo(new BigDecimal(5000000)) < 0) {
//                                xx.setCapBac(1);
//                            }
//                            if (tienKhachMua.compareTo(new BigDecimal(5000000)) > 0 && tienKhachMua.compareTo(new BigDecimal(10000000)) < 0) {
//                                xx.setCapBac(2);
//                            }
//                            if (tienKhachMua.compareTo(new BigDecimal(10000000)) > 0) {
//                                xx.setCapBac(3);
//                            }
//                            new DailyCheckingRankCustomer().saveOrUpdateKH(xx);
//                        }
//                    }
//                    try {
//                        Thread.sleep(60000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                } while (true);
//            }
//        };
//        countDownThread.start();
//    }
//}
