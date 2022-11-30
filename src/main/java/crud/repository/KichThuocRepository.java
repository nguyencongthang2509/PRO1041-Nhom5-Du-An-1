/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud.repository;

import config.HibernateUtil;
import domainmodels.KichThuoc;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author NgocAnh
 */
public class KichThuocRepository {
    private String fromTable = "FROM KichThuoc";
    private Session session = HibernateUtil.getSession();

    public List<KichThuoc> getAll() {
        Query query = session.createQuery(fromTable, KichThuoc.class);
        List<KichThuoc> listKichThuoc = query.getResultList();
        return listKichThuoc;
    }

    public boolean saveOrUpdate(KichThuoc kichthuoc) {
        boolean check = false;
        try {
            Transaction trans = session.beginTransaction();
            session.saveOrUpdate(kichthuoc);
            trans.commit();
            session.close();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean delete(KichThuoc kichthuoc) {
        boolean check = false;
        try {
            Transaction trans = session.beginTransaction();
            session.delete(kichthuoc);
            trans.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public KichThuoc getOne(Long id) {
        String sql = fromTable + "Where id := id";
        Query query = session.createQuery(sql, KichThuoc.class);
        query.setParameter("id", id);
        KichThuoc kichthuoc = (KichThuoc) query.getSingleResult();
        return kichthuoc;
    }

    public static void main(String[] args) {
        List<KichThuoc> list = new KichThuocRepository().getAll();
        for (KichThuoc x : list) {
            System.out.println(x.toString());
        }
    }
}
