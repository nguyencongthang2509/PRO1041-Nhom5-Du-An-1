/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud.repository;

import config.HibernateUtil;
import domainmodels.KhuyenMai;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author thangncph26123
 */
public class KhuyenMaiRepository {
    public List<KhuyenMai> getList(){
        List<KhuyenMai> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "SELECT a FROM KhuyenMai a";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean SaveOrUpDate(KhuyenMai khuyenMai){
        boolean check = false;
        try {
            Session session = HibernateUtil.getSession();
            Transaction trans = session.beginTransaction();
            session.saveOrUpdate(khuyenMai);
            trans.commit();
            session.close();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check ;
    }
    
    public boolean Delete(KhuyenMai khuyenMai){
        boolean check = false;
        try {
            Session session = HibernateUtil.getSession();
            Transaction trans = session.beginTransaction();
            session.delete(khuyenMai);
            trans.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check ;
    }
    
    public KhuyenMai FindKhuyenMaiByMa(String ma){
        KhuyenMai khuyenMai = null;
        try {
           Session session = HibernateUtil.getSession();
           String hql = "SELECT a FROM KhuyenMai a WHERE a.ma =: ma ";
           Query query = session.createQuery(hql);
           query.setParameter("ma", ma);
           if (query.getSingleResult() != null){
               khuyenMai = (KhuyenMai) query.getResultList();
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return khuyenMai;
    }
    
    
}
