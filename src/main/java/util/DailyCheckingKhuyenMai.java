package util;

import config.HibernateUtil;
import core.quanly.repository.KhachHangRespository;
import core.quanly.service.KhuyenMaiService;
import core.quanly.service.impl.KhuyenMaiImpl;
import core.quanly.viewmodel.KhachHangRespone;
import core.quanly.viewmodel.KhuyenMaiResponse;
import domainmodels.ChatLieu;
import domainmodels.ChiTietSP;
import domainmodels.ChiTietSPKhuyenMai;
import domainmodels.Hang;
import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import domainmodels.HoaDonTraHang;
import domainmodels.HoaDonTraHangChiTiet;
import domainmodels.KhachHang;
import domainmodels.KhuyenMai;
import domainmodels.KichThuoc;
import domainmodels.MauSac;
import domainmodels.NhanVien;
import domainmodels.SanPham;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;
import lombok.Synchronized;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author thiennvtph26140
 */
public class DailyCheckingKhuyenMai extends Thread {

    private static final SessionFactory FACTORY;
    private static Session SESSION;
    private Session session;
    private Transaction TRANS;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=du_an_1");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123456");
        properties.put(Environment.SHOW_SQL, "true");
//        properties.put(Environment.HBM2DDL_AUTO, "create");//gen DB tự động

        conf.addAnnotatedClass(SanPham.class);
        conf.addAnnotatedClass(KhuyenMai.class);
        conf.addAnnotatedClass(MauSac.class);
        conf.addAnnotatedClass(ChatLieu.class);
        conf.addAnnotatedClass(KichThuoc.class);
        conf.addAnnotatedClass(Hang.class);
        conf.addAnnotatedClass(NhanVien.class);
        conf.addAnnotatedClass(KhachHang.class);
        conf.addAnnotatedClass(HoaDon.class);
        conf.addAnnotatedClass(ChiTietSP.class);
        conf.addAnnotatedClass(ChiTietSPKhuyenMai.class);
        conf.addAnnotatedClass(HoaDonChiTiet.class);
        conf.addAnnotatedClass(HoaDonTraHang.class);
        conf.addAnnotatedClass(HoaDonTraHangChiTiet.class);

        conf.setProperties(properties);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    @Synchronized
    public static Session getSession() {
        if (SESSION == null || !SESSION.isConnected()) {
            SESSION = FACTORY.openSession();
        }
        return SESSION;
    }

    public List<String> GetAllKhuyenMaiDangDienRa() {
        List<String> list = new ArrayList<>();
        try {
            session = getSession();
            String hql = "SELECT id FROM KhuyenMai a where a.ngayBatDau < :currentTime and :currentTime < a.ngayKetThuc order by a.createdDate DESC";
            Query query = session.createQuery(hql);
            query.setParameter("currentTime", new Date());
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<String> GetAllKhuyenMaiKhongDienRa() {
        List<String> list = new ArrayList<>();
        try {
            session = getSession();
            String hql = "SELECT id FROM KhuyenMai a where a.ngayBatDau > :currentTime or :currentTime > a.ngayKetThuc order by a.createdDate DESC";
            Query query = session.createQuery(hql);
            query.setParameter("currentTime", new Date());
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Synchronized
    public boolean updateKhuyenMaiDangDienRa(String idKhuyenMai) {
        boolean check = false;
        try {
            session = getSession();
            String sql = "Update ctsp_khuyen_mai set trang_thai = 0 where id_khuyen_mai = :idKhuyenMai";
            TRANS = session.beginTransaction();
            Query query = session.createNativeQuery(sql);
            query.setParameter("idKhuyenMai", idKhuyenMai);
            query.executeUpdate();
            check = true;
            TRANS.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;

    }

    @Synchronized
    public boolean updateKhuyenMaiKhongDienRa(String idKhuyenMai) {
        boolean check = false;
        try {
            session = getSession();
            String sql = "Update ctsp_khuyen_mai set trang_thai = 1 where id_khuyen_mai = :idKhuyenMai";
            TRANS = session.beginTransaction();
            Query query = session.createNativeQuery(sql);
            query.setParameter("idKhuyenMai", idKhuyenMai);
            query.executeUpdate();
            check = true;
            TRANS.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;

    }

    @Override
    @Synchronized
    public synchronized void run() {
        while (true) {
            List<String> listDangdienRa = GetAllKhuyenMaiDangDienRa();
            if (listDangdienRa != null) {
                for (String xx : listDangdienRa) {
                    updateKhuyenMaiDangDienRa(xx);
                }
            }
            List<String> listKhongDienRa = GetAllKhuyenMaiKhongDienRa();
            if (listKhongDienRa != null) {
                for (String xx : listKhongDienRa) {
                    updateKhuyenMaiKhongDienRa(xx);
                }
            }
            List<KhachHang> listKhachHang = getAllKhacHangDangHoatDong();
            for (KhachHang xx : listKhachHang) {
                BigDecimal tienKhachMua = getTongTienByIdKhachHang(xx.getId());
                if (tienKhachMua == null) {
                    continue;
                }
                if (tienKhachMua != null) {
                    if (tienKhachMua.compareTo(new BigDecimal(3000000)) < 0) {
                        xx.setCapBac(0);
                    }
                    if (tienKhachMua.compareTo(new BigDecimal(3000000)) > 0 && tienKhachMua.compareTo(new BigDecimal(5000000)) < 0) {
                        xx.setCapBac(1);
                    }
                    if (tienKhachMua.compareTo(new BigDecimal(5000000)) > 0 && tienKhachMua.compareTo(new BigDecimal(10000000)) < 0) {
                        xx.setCapBac(2);
                    }
                    if (tienKhachMua.compareTo(new BigDecimal(10000000)) > 0) {
                        xx.setCapBac(3);
                    }
                    saveOrUpdateKH(xx);
                }
            }
            try {
                Thread.sleep(60000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    public static void dailyChecking() {
//        Thread countDownThread = new Thread() {
//            @Override
//            public void run() {
//                do {
//                    List<KhuyenMai> listDangdienRa = new DailyCheckingKhuyenMai().GetAllKhuyenMaiDangDienRa();
//                    if (listDangdienRa != null) {
//                        for (KhuyenMai xx : listDangdienRa) {
//                            new DailyCheckingKhuyenMai().updateKhuyenMaiDangDienRa(xx.getId());
//                        }
//                    }
//                    List<KhuyenMai> listKhongDienRa = new DailyCheckingKhuyenMai().GetAllKhuyenMaiKhongDienRa();
//                    if (listKhongDienRa != null) {
//                        for (KhuyenMai xx : listKhongDienRa) {
//                            new DailyCheckingKhuyenMai().updateKhuyenMaiKhongDienRa(xx.getId());
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
    @Synchronized
    public List<KhachHang> getAllKhacHangDangHoatDong() {
        List<KhachHang> list = new ArrayList<>();
        try {
            session = getSession();
            String hql = "SELECT a FROM KhachHang a where a.trangThaiXoa = 0 AND a.ma <> 'KH000'";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Synchronized
    public BigDecimal getTongTienByIdKhachHang(String id) {
        BigDecimal money = new BigDecimal(0);
        try {
            session = getSession();
            String hql = "SELECT sum(a.thanhTien) FROM HoaDon a where a.khachHang.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            money = (BigDecimal) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return money;
    }

    @Synchronized
    public KhachHang saveOrUpdateKH(KhachHang entity) {
        try {
            session = getSession();
            TRANS = session.beginTransaction();
            session.saveOrUpdate(entity);
            TRANS.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return entity;
    }

//    public static void dailyCheckingRankCustomer() {
//        Thread countDownThread = new Thread() {
//            @Override
//            public void run() {
//                do {
////                    List<KhachHang> listKH = new ArrayList<>();
//                    List<KhachHang> listKhachHang = new DailyCheckingKhuyenMai().getAllKhacHangDangHoatDong();
//                    for (KhachHang xx : listKhachHang) {
//                        BigDecimal tienKhachMua = new DailyCheckingKhuyenMai().getTongTienByIdKhachHang(xx.getId());
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
//                            new DailyCheckingKhuyenMai().saveOrUpdateKH(xx);
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
}
