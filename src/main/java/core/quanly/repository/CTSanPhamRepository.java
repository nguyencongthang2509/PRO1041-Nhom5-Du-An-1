/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.viewmodel.CTSanPhamResponse;
import core.quanly.viewmodel.SanPhamResponse;
import domainmodels.ChiTietSP;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import repository.CrudRepository;

/**
 *
 * @author HP
 */

public class CTSanPhamRepository extends CrudRepository<String, ChiTietSP, CTSanPhamResponse> {

    public CTSanPhamRepository() { 
        className = ChiTietSP.class.getName();
        res = "";
    }
    
        public List<CTSanPhamResponse> getAllResponseCTSP() {
        List<CTSanPhamResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.CTSanPhamResponse(a.id, a.sanPham.ma, a.sanPham.ten,"
                    + "a.mauSac.ten, a.kichThuoc.ten, a.hang.ten, b.giaTri ,a.maChiTietSP, a.moTa, a.soLuongTon, a.giaBan, a.maVach, a.trangThaiXoa) FROM ChiTietSP a LEFT JOIN a.khuyenMai b";
            org.hibernate.query.Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public static void main(String[] args) {
        List<CTSanPhamResponse> list = new CTSanPhamRepository().findTrangThai(1);
        System.out.println(list);
    }

    public List<CTSanPhamResponse> findByMaOrTen(String input) {
        List<CTSanPhamResponse> lst = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.CTSanPhamResponse(a.id, a.sanPham.ma, a.sanPham.ten,"
                    + "a.mauSac.ten, a.kichThuoc.ten, a.hang.ten, b.giaTri ,a.maChiTietSP, a.moTa, a.soLuongTon, a.giaBan, a.maVach, a.trangThaiXoa) FROM ChiTietSP a LEFT JOIN a.khuyenMai b"
                    + " WHERE a.sanPham.ma LIKE CONCAT('%',:input,'%') OR a.maChiTietSP LIKE CONCAT('%',:input,'%')";
            Query query = session.createQuery(hql);
            query.setParameter("input", input);
            lst = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
    
    public List<CTSanPhamResponse> findTrangThai(Integer input) {
        List<CTSanPhamResponse> lst = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.CTSanPhamResponse(a.id, a.sanPham.ma, a.sanPham.ten,"
                    + "a.mauSac.ten, a.kichThuoc.ten, a.hang.ten, b.giaTri ,a.maChiTietSP, a.moTa, a.soLuongTon, a.giaBan, a.maVach, a.trangThaiXoa) FROM ChiTietSP a LEFT JOIN a.khuyenMai b"
                    + " WHERE a.trangThaiXoa = :input";
            Query query = session.createQuery(hql);
            query.setParameter("input", input);
            lst = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    
    public List<CTSanPhamResponse> getFormCTSP(String ma){
        List<CTSanPhamResponse> lst = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.CTSanPhamResponse(a.id, a.sanPham.ma, a.sanPham.ten,"
                + "a.mauSac.ten, a.kichThuoc.ten, a.hang.ma, b.giaTri ,a.maChiTietSP, a.moTa, a.soLuongTon, a.giaBan, a.maVach, a.trangThaiXoa) "
                    + "FROM ChiTietSP a left join a.khuyenMai b WHERE a.sanPham.ma LIKE CONCAT('%',:ma,'%')";
            Query query = session.createQuery(hql);
            query.setParameter("ma", ma);
            lst = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
}
