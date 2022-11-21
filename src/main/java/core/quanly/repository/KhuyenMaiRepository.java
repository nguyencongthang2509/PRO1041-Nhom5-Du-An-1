/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.viewmodel.KhuyenMaiResponse;
import domainmodels.KhuyenMai;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author thiennvtph26140
 */
public class KhuyenMaiRepository extends repository.CrudRepository<String, KhuyenMai, KhuyenMaiResponse >{

   public KhuyenMaiRepository(){
    className = KhuyenMai.class.getName();
    res = "new core.quanly.viewmodel.KhuyenMaiResponse( a.id, a.ma, a.ten, a.loaiKhuyenMai , a.giaTri, a.ngayBatDau, a.ngayKetThuc)";
    }
   
//    public boolean saveOrUpdate(KhuyenMaiResponse khuyenMaiResponse) {
//    boolean check = false;
//        try {
////            Session session = HibernateUtil.getSession(); 
////            Transaction trans = session.beginTransaction(); 
////            session.saveOrUpdate(khuyenMaiResponse);
////            trans.commit(); 
//            check = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return check;
    
//    }
   
    public List<KhuyenMaiResponse> FindMaOrTenByInput (String input){
        List<KhuyenMaiResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "SELECT NEW core.quanly.viewmodel.KhuyenMaiResponse(a.id, a.ma, a.ten, a.loaiKhuyenMai , a.giaTri, a.ngayBatDau, a.ngayKetThuc) FROM KhuyenMai a WHERE a.ma LIKE CONCAT('%',:input,'%') OR a.ten LIKE CONCAT('%',:input,'%')";
            Query query = session.createQuery(hql);
            query.setParameter("input", input);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
}
