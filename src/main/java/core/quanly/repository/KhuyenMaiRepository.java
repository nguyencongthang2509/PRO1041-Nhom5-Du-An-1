/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.viewmodel.KMChiTietSPResponse;
import core.quanly.viewmodel.KMSanphamDangKmReponse;
import core.quanly.viewmodel.KhuyenMaiResponse;
import domainmodels.ChiTietSP;
import domainmodels.ChiTietSPKhuyenMai;
import domainmodels.KhuyenMai;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.common.reflection.XClass;

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

    public ChiTietSPKhuyenMai saveOrUpdate(ChiTietSPKhuyenMai entity) {
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

    public List<KMChiTietSPResponse> getAllChiTietSP() {
        List<KMChiTietSPResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = """
                         SELECT new core.quanly.viewmodel.KMChiTietSPResponse
                         (a.id, a.maChiTietSP, a.sanPham.ten, a.hang.ten, a.mauSac.ten
                         , a.kichThuoc.ten, a.chatLieu.ten) FROM ChiTietSP a
                         where (SELECT b.id FROM ChiTietSPKhuyenMai b where b.trangThai = 0 AND b.chiTietSPId.id = a.id) IS NULL
                         """;
            org.hibernate.query.Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public ChiTietSPKhuyenMai getChiTietSPKM(String idChiTietSP) {
        ChiTietSPKhuyenMai chiTietSPKhuyenMai = new ChiTietSPKhuyenMai();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM ChiTietSPKhuyenMai a WHERE a.chiTietSPId.id = :idChiTietSP AND a.trangThai = 0";
            org.hibernate.query.Query query = session.createQuery(hql);
            query.setParameter("idChiTietSP", idChiTietSP);
            chiTietSPKhuyenMai = (ChiTietSPKhuyenMai) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return chiTietSPKhuyenMai;
    }

    public ChiTietSP findChiTietSpById(String id) {
        ChiTietSP chiTietSP = null;
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM ChiTietSP a WHERE a.id = :id";
            org.hibernate.query.Query query = session.createQuery(hql);
            query.setParameter("id", id);
            chiTietSP = (ChiTietSP) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return chiTietSP;
    }

    public List<KMSanphamDangKmReponse> GetAllSanPhamDangApDung(String idkhuyenmai) {
        List<KMSanphamDangKmReponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.KMSanphamDangKmReponse( a.chiTietSPId.maChiTietSP, a.chiTietSPId.sanPham.ten, a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten, a.chiTietSPId.kichThuoc.ten, a.chiTietSPId.chatLieu.ten) FROM ChiTietSPKhuyenMai a where a.khuyenMaiId.id =: idkhuyenmai";
            org.hibernate.query.Query query = session.createQuery(hql);
            query.setParameter("idkhuyenmai", idkhuyenmai);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<KhuyenMaiResponse> FindMaOrTenByInputDangDienRa(String input) {
        List<KhuyenMaiResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "SELECT NEW core.quanly.viewmodel.KhuyenMaiResponse(a.id, a.ma,a.ten, a.loaiKhuyenMai,"
                    + " a.giaTri, a.ngayBatDau, a.ngayKetThuc ) FROM KhuyenMai a WHERE (a.ma LIKE CONCAT('%',:input,'%') OR a.ten LIKE CONCAT('%',:input,'%')) and  a.ngayBatDau < :currentTime and :currentTime <a.ngayKetThuc";
            javax.persistence.Query query = session.createQuery(hql);
            query.setParameter("input", input);
            query.setParameter("currentTime", new Date());
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        List<KhuyenMaiResponse> list = new KhuyenMaiRepository().FindMaOrTenByInputDangDienRa("");
        System.out.println(list.size());
    }

    public List<KhuyenMaiResponse> FindMaOrTenByInputDaDienRa(String input) {
        List<KhuyenMaiResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "SELECT NEW core.quanly.viewmodel.KhuyenMaiResponse(a.id, a.ma,a.ten, a.loaiKhuyenMai,"
                    + " a.giaTri, a.ngayBatDau, a.ngayKetThuc ) FROM KhuyenMai a WHERE (a.ma LIKE CONCAT('%',:input,'%') OR a.ten LIKE CONCAT('%',:input,'%')) and  :currentTime > a.ngayKetThuc";
            javax.persistence.Query query = session.createQuery(hql);
            query.setParameter("input", input);
            query.setParameter("currentTime", new Date());
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<KhuyenMaiResponse> FindMaOrTenByInputSapDienRa(String input) {
        List<KhuyenMaiResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "SELECT NEW core.quanly.viewmodel.KhuyenMaiResponse(a.id, a.ma,a.ten, a.loaiKhuyenMai,"
                    + " a.giaTri, a.ngayBatDau, a.ngayKetThuc ) FROM KhuyenMai a WHERE (a.ma LIKE CONCAT('%',:input,'%') OR a.ten LIKE CONCAT('%',:input,'%')) and  :currentTime < a.ngayBatDau";
            javax.persistence.Query query = session.createQuery(hql);
            query.setParameter("input", input);
            query.setParameter("currentTime", new Date());
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

    public List<KhuyenMaiResponse> GetKhuyenMaiDangDienRa() {
        List<KhuyenMaiResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT  NEW core.quanly.viewmodel.KhuyenMaiResponse(a.id, a.ma,a.ten, a.loaiKhuyenMai,  a.giaTri, a.ngayBatDau, a.ngayKetThuc ) FROM KhuyenMai a where a.ngayBatDau < :currentTime and :currentTime <a.ngayKetThuc";
            org.hibernate.query.Query query = session.createQuery(hql);
            query.setParameter("currentTime", new Date());
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<KhuyenMaiResponse> GetKhuyenMaiSapDienRa() {
        List<KhuyenMaiResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT  NEW core.quanly.viewmodel.KhuyenMaiResponse(a.id, a.ma,a.ten, a.loaiKhuyenMai,  a.giaTri, a.ngayBatDau, a.ngayKetThuc ) FROM KhuyenMai a where  :currentTime < a.ngayBatDau";
            org.hibernate.query.Query query = session.createQuery(hql);
            query.setParameter("currentTime", new Date());
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<KhuyenMaiResponse> GetKhuyenMaiDaDienRa() {
        List<KhuyenMaiResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT  NEW core.quanly.viewmodel.KhuyenMaiResponse(a.id, a.ma,a.ten, a.loaiKhuyenMai,  a.giaTri, a.ngayBatDau, a.ngayKetThuc ) FROM KhuyenMai a where  :currentTime > a.ngayKetThuc";
            org.hibernate.query.Query query = session.createQuery(hql);
            query.setParameter("currentTime", new Date());
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
}
