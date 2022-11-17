/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud.repository;

import config.HibernateUtil;
import crud.viewmodel.ChucVuViewModel;
import domainmodels.ChucVu;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author longnhph26222
 */
public class ChucVuRepository {

    public List<ChucVuViewModel> getList() {
        List<ChucVuViewModel> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select new crud.viewmodel.ChucVuViewModel(a.id, a.ma, a.ten) from ChucVu a";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean saveorUpdate(ChucVu c) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getSession();
            Transaction trans = session.beginTransaction();
            session.saveOrUpdate(c);
            trans.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean delete(ChucVu c) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getSession();
            Transaction trans = session.beginTransaction();
            session.delete(c);
            trans.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
    
    public ChucVu findbyMa(String ma){
        ChucVu c = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select a from ChucVu a where a.ma = :ma";
            Query query = session.createQuery(hql);
            query.setParameter("ma", ma);
            if(query.getSingleResult() != null){
                c = (ChucVu) query.getSingleResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
}
