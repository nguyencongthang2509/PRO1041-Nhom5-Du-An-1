package crud.repository;

import config.HibernateUtil;
import domainmodels.TrangThaiHoaDon;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author quynhncph26201
 */
public class RepositoryTrangThaiHoaDon {

    private Session session = HibernateUtil.getSession();
    private String toTable = "from TrangThaiHoaDon";

//Lấy toàn bộ thông tin trong bảng TrangThaiHoaDon
    public List<TrangThaiHoaDon> getAll() {
        Query query = session.createQuery(toTable, TrangThaiHoaDon.class);
        List<TrangThaiHoaDon> getList = query.getResultList();
        return getList;
    }

//Thêm hoạc Sửa
    public boolean updateOrinsert(TrangThaiHoaDon tthd) {
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
    public boolean delete(TrangThaiHoaDon tthd) {
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

    public TrangThaiHoaDon getbyID(String ma) {
        TrangThaiHoaDon tt = null;
        String sql = toTable + "where ma := ma";
        Query query = session.createQuery(sql, TrangThaiHoaDon.class);
        query.setParameter("ma", ma);
        if (query.getSingleResult() != null) {
            tt = (TrangThaiHoaDon) query.getSingleResult();
        }

        return tt;
    }
    

    public static void main(String[] args) {
        List<TrangThaiHoaDon> list = new RepositoryTrangThaiHoaDon().getAll();
        for (TrangThaiHoaDon x : list) {
            System.out.println(x.toString());
        }

    }
}
