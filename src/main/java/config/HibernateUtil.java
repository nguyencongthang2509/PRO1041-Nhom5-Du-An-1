package config;

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
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=FINALASS_FPOLYSHOP_FA22_SOF205_SOF2041");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123456");
        properties.put(Environment.SHOW_SQL, "false");
//        properties.put(Environment.HBM2DDL_AUTO, "create");//gen DB tự động
//
//        conf.addAnnotatedClass(SanPham.class);
//        conf.addAnnotatedClass(MauSac.class);
//        conf.addAnnotatedClass(NSX.class);
//        conf.addAnnotatedClass(DongSP.class);
//        conf.addAnnotatedClass(ChucVu.class);
//        conf.addAnnotatedClass(CuaHang.class);
//        conf.addAnnotatedClass(NhanVien.class);
//        conf.addAnnotatedClass(KhachHang.class);
//        conf.addAnnotatedClass(GioHang.class);
//        conf.addAnnotatedClass(HoaDon.class);
//        conf.addAnnotatedClass(ChiTietSP.class);
//        conf.addAnnotatedClass(HoaDonChiTiet.class);
//        conf.addAnnotatedClass(GioHangChiTiet.class);

        conf.setProperties(properties);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static Session getSession() {
        if(SESSION == null || !SESSION.isConnected()){
            SESSION = FACTORY.openSession();
        }
        return SESSION;
    }

}
