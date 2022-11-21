/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.viewmodel.KhachHangRespone;
import core.view.ViewKhachHang;
import domainmodels.KhachHang;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import repository.CrudRepository;

/**
 *
 * @author NgocAnh
 */
public class KhachHangRespository extends CrudRepository<String, KhachHang, KhachHangRespone> {

    public KhachHangRespository() {
        className = KhachHang.class.getName();
        res = "new core.quanly.viewmodel.KhachHangRespone(a.id,a.ma,a.hoTen,a.gioiTinh,a.sdt,a.diaChi,a.email,a.ngaySinh)";
    }
    
     public List<KhachHangRespone> findKhachHangByMaOrTen(String input) {
        List<KhachHangRespone> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
//           
            String hql = "SELECT new core.quanly.viewmodel.KhachHangRespone(a.id,a.ma,a.hoTen,a.gioiTinh,a.sdt,a.diaChi,a.email,a.ngaySinh) FROM KhachHang a WHERE a.ma LIKE CONCAT('%',:input,'%') OR a.hoTen LIKE CONCAT('%',:input,'%') ";
            Query query = session.createQuery(hql);
            query.setParameter("input", input);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
