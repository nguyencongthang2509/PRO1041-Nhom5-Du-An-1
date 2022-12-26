
package core.quanly.repository;

import config.HibernateUtil;
import domainmodels.NhanVien;
import org.hibernate.Session;
import org.hibernate.query.Query;


/**
 *
 * @author thangncph26123
 */
public class DangNhapRepository {
    
    public NhanVien findNhanVienByEmailAndMatKhau(String email, String matKhau) {
        NhanVien nhanVien = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "SELECT a FROM NhanVien a WHERE a.email = :email AND a.matKhau = :matKhau AND a.trangThaiXoa = 0";
            Query query = session.createQuery(hql);
            query.setParameter("email", email);
            query.setParameter("matKhau", matKhau);
            nhanVien = (NhanVien) query.getSingleResult();
        } catch (Exception e) {
        }
        return nhanVien;
    }
}
