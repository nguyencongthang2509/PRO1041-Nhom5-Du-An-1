/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.viewmodel.CTSanPhamResponse;
import core.quanly.viewmodel.SPMauSacResponse;
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
        res = "new core.quanly.viewmodel.CTSanPhamResponse(a.id, a.sanPham.ma, a.sanPham.ten,"
                + "a.mauSac.ten, a.kichThuoc.ten,a.hang.ten, a.khuyenMai.giaTri ,a.maChiTietSP, a.moTa,"
                + " a.soLuongTon, a.giaBan , a.maVach)";
    }

    public static void main(String[] args) {
        List<CTSanPhamResponse> list = new CTSanPhamRepository().getAllResponse();
        System.out.println(list);
    } 

    public List<CTSanPhamResponse> getListHang(String maHang) {
        List<CTSanPhamResponse> listsv = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.CTSanPhamResponse(a.sanPham.ma, a.sanPham.ten,"
                + "a.mauSac.ten, a.kichThuoc.ten,a.hang.ten, a.khuyenMai.giaTri ,a.maChiTietSP,a.moTa , a.soLuongTon, a.giaBan,  a.maVach)"
                    + " FROM ChiTietSP a WHERE a.hang.ma LIKE CONCAT('%',:ma ,'%')";
            Query query = session.createQuery(hql);
            query.setParameter("ma", maHang);
            listsv = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listsv;
    }
    
    public List<CTSanPhamResponse> getListMauSac(String maMauSac) {
        List<CTSanPhamResponse> listms = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.CTSanPhamResponse(a.sanPham.ma, a.sanPham.ten,"
                + "a.mauSac.ten, a.kichThuoc.ten,a.hang.ten, a.khuyenMai.giaTri ,a.maChiTietSP,a.moTa , a.soLuongTon, a.giaBan,  a.maVach)"
                    + " FROM ChiTietSP a WHERE a.mauSac.ma LIKE CONCAT('%',:ma ,'%')";
            Query query = session.createQuery(hql);
            query.setParameter("ma", maMauSac);
            listms = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listms;
    }
    
    public List<CTSanPhamResponse> findctSanPham(String input) {
        List<CTSanPhamResponse> lst = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.CTSanPhamResponse(a.id, a.sanPham.ma, a.sanPham.ten,"
                + "a.mauSac.ten, a.kichThuoc.ten,a.hang.ten, a.khuyenMai.giaTri ,a.maChiTietSP, a.moTa, a.soLuongTon, a.giaBan ,a.maVach) "
                    + "FROM ChiTietSP a WHERE a.sanPham.ten LIKE CONCAT('%',:input, '%') "
                    + "OR a.maChiTietSP LIKE CONCAT('%',:input, '%')";
            Query query = session.createQuery(hql);
            query.setParameter("input", input);
            lst = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
    
        
    public List<CTSanPhamResponse> getListCTSP(String masp) {
        List<CTSanPhamResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.CTSanPhamResponse"
                + "(a.id, a.sanPham.ma, a.sanPham.ten,"
                + "a.mauSac.ten, a.kichThuoc.ten,a.hang.ten, a.khuyenMai.giaTri ,"
                + "a.maChiTietSP, a.moTa, a.soLuongTon, a.giaBan , a.maVach) from ChiTietSP a where a.sanPham.ma = :masp";
            Query query = session.createQuery(hql);
            query.setParameter("masp", masp);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
