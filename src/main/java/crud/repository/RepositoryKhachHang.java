package crud.repository;

import config.HibernateUtil;
import domainmodels.KhachHang;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author quynhncph26201
 */
public class RepositoryKhachHang {

    private Session session = HibernateUtil.getSession();
    private String toTable = "from KhachHang";

//Lấy toàn bộ thông tin trong bảng KhachHang
    public List<KhachHang> getAll() {
        Query query = session.createQuery(toTable, KhachHang.class);
        List<KhachHang> getList = query.getResultList();
        return getList;
    }

//Thêm hoạc Sửa
    public boolean updateOrinsert(KhachHang tthd) {
        boolean check = false;
        try {
            Transaction trans = session.beginTransaction();
            session.saveOrUpdate(tthd);
            trans.commit();
            session.close();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;

    }

//Xóa
    public boolean delete(KhachHang tthd) {
        boolean check = false;
        try {
            Transaction trans = session.beginTransaction();
            session.delete(tthd);
            trans.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
//Tim theo ma

    public KhachHang getbyID(String ma) {
        KhachHang kh = null;
        String sql = toTable + "where ma := ma";
        Query query = session.createQuery(sql, KhachHang.class);
        query.setParameter("ma", ma);
        if (query.getSingleResult() != null) {
            kh = (KhachHang) query.getSingleResult();
        }

        return kh;
    }

}
