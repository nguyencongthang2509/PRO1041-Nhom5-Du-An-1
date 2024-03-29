package config;


import core.quanly.repository.KhuyenMaiRepository;
import core.quanly.viewmodel.KMSanphamDangKmReponse;
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
import java.util.List;
import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static final SessionFactory FACTORY;
    private static Session SESSION;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=du_an_1");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123456");
        properties.put(Environment.SHOW_SQL, "true");
//        properties.put(Environment.HBM2DDL_AUTO, "create");

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

    public static Session getSession() {
        if (SESSION == null || !SESSION.isConnected()) {
            SESSION = FACTORY.openSession();
        }
        return SESSION;
    }

    public static void main(String[] args) {
        if (getSession() != null) {
            System.out.println("Thành công");
        }
    }

}
