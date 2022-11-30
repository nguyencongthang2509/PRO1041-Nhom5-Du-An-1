/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud.repository;

import config.HibernateUtil;
import domainmodels.NhanVien;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author longnhph26222
 */
public class NhanVienRepository {

    public List<NhanVien> getList() {
        List<NhanVien> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select a from NhanVien a";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean saveorUpdate(NhanVien nv) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getSession();
            Transaction trans = session.beginTransaction();
            session.saveOrUpdate(nv);
            trans.commit();
            session.close();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean delete(NhanVien nv) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getSession();
            Transaction trans = session.beginTransaction();
            session.delete(nv);
            trans.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public NhanVien findbyMa(String ma) {
        NhanVien nv = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select a from NhanVien a where a.ma = :ma";
            Query query = session.createQuery(hql);
            query.setParameter("ma", ma);
            if (query.getSingleResult() != null) {
                nv = (NhanVien) query.getSingleResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nv;
    }

    public NhanVien findbyID(String id){
        NhanVien nv = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select a from NhanVien a where a.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            if (query.getSingleResult() != null) {
                nv = (NhanVien) query.getSingleResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nv;
    }
}
