/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author thiennvtph26140
 */
public class DoiMatKhauRepository {
    public String GetIdNhanVien(String email, String matkhau){
        String id = "";
    try {
            Session session = HibernateUtil.getSession();
            String hql = "select a.id from NhanVien a where a.email = :email and a.matKhau = :matkhau";
            Query query = session.createQuery(hql);
            query.setParameter("matkhau", matkhau);
            query.setParameter("email", email);
            id = (String) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
    return  id;
    }
    public boolean DoiMatKhau(String matkhau, String id){
    int check = -1;
    
        try {
            Session session = HibernateUtil.getSession();
            Transaction trans = session.beginTransaction();
            String hql = "UPDATE nhan_vien SET mat_khau = :matKhau where id = :id";
            Query query = session.createNativeQuery(hql);
            query.setParameter("matKhau", matkhau);
            query.setParameter("id", id);
            check = query.executeUpdate();
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return check > 0;
    }
}
