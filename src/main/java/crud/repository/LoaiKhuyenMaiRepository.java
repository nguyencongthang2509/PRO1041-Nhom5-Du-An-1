/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud.repository;

import config.HibernateUtil;
import domainmodels.KhuyenMai;
import domainmodels.LoaiKhuyenMai;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author thiennvtph26140
 */
public class LoaiKhuyenMaiRepository {
    public List<LoaiKhuyenMai> getList(){
        List<LoaiKhuyenMai> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "SELECT a FROM LoaiKhuyenMai a";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean SaveOrUpDate(LoaiKhuyenMai loaiKhuyenMai){
        boolean check = false;
        try {
            Session session = HibernateUtil.getSession();
            Transaction trans = session.beginTransaction();
            session.saveOrUpdate(loaiKhuyenMai);
            trans.commit();
            session.close();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check ;
    }
    
    public boolean Delete(LoaiKhuyenMai loaiKhuyenMai){
        boolean check = false;
        try {
            Session session = HibernateUtil.getSession();
            Transaction trans = session.beginTransaction();
            session.delete(loaiKhuyenMai);
            trans.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check ;
    }
    
    public LoaiKhuyenMai FindLoaiKhuyenMaiByMa(String ma){
        LoaiKhuyenMai loaiKhuyenMai = null;
        try {
           Session session = HibernateUtil.getSession();
           String hql = "SELECT a FROM LoaiKhuyenMai a WHERE a.ma =: ma ";
           Query query = session.createQuery(hql);
           query.setParameter("ma", ma);
           if (query.getSingleResult() != null){
              loaiKhuyenMai = (LoaiKhuyenMai) query.getResultList();
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loaiKhuyenMai;
    }
}
