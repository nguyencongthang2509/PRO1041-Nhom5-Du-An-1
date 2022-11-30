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
import org.hibernate.query.NativeQuery;
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
                    + "a.mauSac.ten, a.kichThuoc.ten, a.hang.ten, a.chatLieu.ten ,a.maChiTietSP, a.moTa, a.soLuongTon, a.giaBan, a.maVach, a.trangThaiXoa) FROM ChiTietSP a Where a.trangThaiXoa = 0 order by a.createdDate desc";
            org.hibernate.query.Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public static void main(String[] args) {
        List<CTSanPhamResponse> list = new CTSanPhamRepository().getAllResponseCTSP();
        System.out.println(list);
    }

    public List<CTSanPhamResponse> findByMaOrTen(String input) {
        List<CTSanPhamResponse> lst = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.CTSanPhamResponse(a.id, a.sanPham.ma, a.sanPham.ten,"
                    + "a.mauSac.ten, a.kichThuoc.ten, a.hang.ten, a.chatLieu.ten ,a.maChiTietSP, a.moTa, a.soLuongTon, a.giaBan, a.maVach, a.trangThaiXoa) FROM ChiTietSP a"
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
                    + "a.mauSac.ten, a.kichThuoc.ten, a.hang.ten, a.chatLieu.ten, a.maChiTietSP, a.moTa, a.soLuongTon, a.giaBan, a.maVach, a.trangThaiXoa) FROM ChiTietSP a"
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
                + "a.mauSac.ten, a.kichThuoc.ten, a.hang.ma, a.chatLieu.ten ,a.maChiTietSP, a.moTa, a.soLuongTon, a.giaBan, a.maVach, a.trangThaiXoa) "
                    + "FROM ChiTietSP a WHERE a.sanPham.ma LIKE CONCAT('%',:ma,'%')";
            Query query = session.createQuery(hql);
            query.setParameter("ma", ma);
            lst = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
    
    public int genMaCTSP() {
        String maStr = "";
        session = HibernateUtil.getSession();
        try {
            String nativeQuery = "SELECT MAX(CONVERT(INT, SUBSTRING(a.ma_ctsp,5,10))) from chi_tiet_san_pham a";
            NativeQuery query = session.createNativeQuery(nativeQuery);
            if (query.getSingleResult() != null) {
                maStr = query.getSingleResult().toString();
            } else {
                maStr = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (maStr == null) {
            maStr = "0";
            int ma = Integer.parseInt(maStr);
            return ++ma;
        }
        int ma = Integer.parseInt(maStr);
        return ++ma;
    }
}
