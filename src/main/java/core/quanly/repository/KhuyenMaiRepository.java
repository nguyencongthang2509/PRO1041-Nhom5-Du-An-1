/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.viewmodel.KMSanphamDangKmReponse;
import core.quanly.viewmodel.KhuyenMaiResponse;
import domainmodels.ChiTietSP;
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
public class KhuyenMaiRepository extends repository.CrudRepository<String, ChiTietSP, KhuyenMaiResponse> {

    public KhuyenMaiRepository() {
        className = ChiTietSP.class.getName();
        res = "new core.quanly.viewmodel.KhuyenMaiResponse( a.khuyenMai.id, a.khuyenMai.ma, a.khuyenMai.ten, a.khuyenMai.loaiKhuyenMai, "
                + " a.khuyenMai.giaTri, a.khuyenMai.ngayBatDau, a.khuyenMai.ngayKetThuc)";
    }
    public List<KMSanphamDangKmReponse> GetAllSanPhamDangApDung(String idkhuyenmai){
    List<KMSanphamDangKmReponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.KMSanphamDangKmReponse( a.sanPham.ma, a.sanPham.ten, a.hang.ten, a.mauSac.ten, a.kichThuoc.ten) FROM ChiTietSP a where a.khuyenMai.id =: idkhuyenmai";
            org.hibernate.query.Query query = session.createQuery(hql);
            query.setParameter("idkhuyenmai", idkhuyenmai);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    
    public List<KhuyenMaiResponse> FindMaOrTenByInput(String input) {
        List<KhuyenMaiResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "SELECT NEW core.quanly.viewmodel.KhuyenMaiResponse(a.id, a.khuyenMai.ma,a.khuyenMai.ten, a.khuyenMai.loaiKhuyenMai,"
                    + " a.khuyenMai.giaTri, a.sanPham.ten, a.khuyenMai.ngayBatDau, a.khuyenMai.ngayKetThuc ) FROM ChiTietSP a WHERE a.khuyenMai.ma LIKE CONCAT('%',:input,'%') OR a.khuyenMai.ten LIKE CONCAT('%',:input,'%')";
            javax.persistence.Query query = session.createQuery(hql);
            query.setParameter("input", input);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public KhuyenMai saveOrUpdateKM(KhuyenMai entity) {
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            session.saveOrUpdate(entity);
            trans.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return entity;
    }

    public boolean updateKhuyenMaiChoSP(String idKhuyenMai, String idChiTietSP) {
        boolean check = false;
        try {
            String sql = "Update chi_tiet_san_pham set id_khuyen_mai = :idKhuyenMai where id = :idChiTietSP";
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            org.hibernate.query.Query query = session.createNativeQuery(sql);
            query.setParameter("idKhuyenMai", idKhuyenMai);
            query.setParameter("idChiTietSP", idChiTietSP);
            query.executeUpdate();
            check = true;
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        return check;
        
        
    }
    
    public List<KhuyenMaiResponse> getAllResponseKhuyenMai() {
        List<KhuyenMaiResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT  NEW core.quanly.viewmodel.KhuyenMaiResponse(a.id, a.ma,a.ten, a.loaiKhuyenMai,  a.giaTri, a.ngayBatDau, a.ngayKetThuc ) FROM KhuyenMai a";
            org.hibernate.query.Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
}
