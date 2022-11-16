/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.HibernateUtil;
import domainmodels.MauSac;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author NgocAnh
 */
public class MausacRepository {
    private String fromTable = "FROM MauSac";
    private Session session = HibernateUtil.getSession();

    public List<MauSac> getAll() {
        Query query = session.createQuery(fromTable, MauSac.class);
        List<MauSac> listMauSac = query.getResultList();
        return listMauSac;
    }

    public boolean saveOrUpdate(MauSac mausac) {
        boolean check = false;
        try {
            Transaction trans = session.beginTransaction();
            session.saveOrUpdate(mausac);
            trans.commit();
            session.close();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean delete(MauSac mausac) {
        boolean check = false;
        try {
            Transaction trans = session.beginTransaction();
            session.delete(mausac);
            trans.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public MauSac getOne(Long id) {
        String sql = fromTable + "Where id := id";
        Query query = session.createQuery(sql, MauSac.class);
        query.setParameter("id", id);
        MauSac mausac = (MauSac) query.getSingleResult();
        return mausac;
    }

    public static void main(String[] args) {
        List<MauSac> list = new MausacRepository().getAll();
        for (MauSac x : list) {
            System.out.println(x.toString());
        }
    }
}
