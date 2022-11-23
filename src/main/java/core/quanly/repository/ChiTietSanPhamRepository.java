/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.service.ChiTietSanPhamService;
import core.quanly.service.impl.ChiTietSanPhamImpl;
import core.quanly.viewmodel.KMChiTietSanPhamResponse;
import domainmodels.ChiTietSP;
import domainmodels.KhuyenMai;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


/**
 *
 * @author thiennvtph26140
 */
public class ChiTietSanPhamRepository extends repository.CrudRepository<String, ChiTietSP, KMChiTietSanPhamResponse >{
    public ChiTietSanPhamRepository(){
    className = ChiTietSP.class.getName();
    res = "NEW core.quanly.viewmodel.KMChiTietSanPhamResponse(a.id, a.khuyenMai.ma, "
            + "a.khuyenMai.ten, a.khuyenMai.loaiKhuyenMai, a.khuyenMai.giaTri, a.sanPham.ten, a.khuyenMai.ngayBatDau, a.khuyenMai.ngayKetThuc)";
    }
    public List<KMChiTietSanPhamResponse> FindMaOrTenByInput (String input){
        List<KMChiTietSanPhamResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "SELECT NEW core.quanly.viewmodel.KMChiTietSanPhamResponse(a.id, a.khuyenMai.ma,a.khuyenMai.ten, a.khuyenMai.loaiKhuyenMai,"
                    + " a.khuyenMai.giaTri, a.sanPham.ten, a.khuyenMai.ngayBatDau, a.khuyenMai.ngayKetThuc ) FROM ChiTietSP a WHERE a.khuyenMai.ma LIKE CONCAT('%',:input,'%') OR a.khuyenMai.ten LIKE CONCAT('%',:input,'%')";
            javax.persistence.Query query = session.createQuery(hql);
            query.setParameter("input", input);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<KMChiTietSanPhamResponse> GetSanPham() {
        List<KMChiTietSanPhamResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT NEW core.quanly.viewmodel.KMChiTietSanPhamResponse(a.sanPham.ma, a.sanPham.ten) FROM ChiTietSP a";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    
}
