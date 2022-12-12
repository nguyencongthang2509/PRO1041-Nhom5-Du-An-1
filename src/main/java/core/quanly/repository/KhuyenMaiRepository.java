/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.viewmodel.KMChiTietSPResponse;
import core.quanly.viewmodel.KMSanphamDangKmReponse;
import core.quanly.viewmodel.KhuyenMaiResponse;
import core.quanly.viewmodel.SanPhamResponse;
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
import org.hibernate.query.NativeQuery;
import repository.CrudRepository;

/**
 *
 * @author thiennvtph26140
 */
public class KhuyenMaiRepository extends CrudRepository<String, ChiTietSP, KhuyenMaiResponse> {

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
                         AND a.trangThaiXoa = 0
                         """;
            org.hibernate.query.Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<KMChiTietSPResponse> getAllChiTietSPCoTheApDung(Date ngayBatDau, Date ngayKetThuc) {
        List<KMChiTietSPResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = """
                         SELECT new core.quanly.viewmodel.KMChiTietSPResponse
                         (a.id, a.maChiTietSP, a.sanPham.ten, a.hang.ten, a.mauSac.ten
                         , a.kichThuoc.ten, a.chatLieu.ten) FROM ChiTietSP a
                         where (SELECT b.id FROM ChiTietSPKhuyenMai b 
                         where :ngayBatDau < b.khuyenMaiId.ngayKetThuc AND :ngayKetThuc > b.khuyenMaiId.ngayBatDau
                         AND b.chiTietSPId.id = a.id AND b.trangThai <> 1) IS NULL
                         AND a.trangThaiXoa = 0
                         """;
            org.hibernate.query.Query query = session.createQuery(hql);
            query.setParameter("ngayBatDau", ngayBatDau);
            query.setParameter("ngayKetThuc", ngayKetThuc);
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
            String hql = "SELECT new core.quanly.viewmodel.KMSanphamDangKmReponse(a.id ,a.chiTietSPId.id, a.chiTietSPId.maChiTietSP, a.chiTietSPId.sanPham.ten, a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten, a.chiTietSPId.kichThuoc.ten, a.chiTietSPId.chatLieu.ten) FROM ChiTietSPKhuyenMai a where a.khuyenMaiId.id =: idkhuyenmai";
            Query query = session.createQuery(hql);
            query.setParameter("idkhuyenmai", idkhuyenmai);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<KMSanphamDangKmReponse> aaaaaa(String idkhuyenmai) {
        List<KMSanphamDangKmReponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = """
                         SELECT new core.quanly.viewmodel.KMSanphamDangKmReponse
                         (a.id ,c.maChiTietSP, d.ten, e.ten, f.ten, g.ten, h.ten)
                         FROM ChiTietSPKhuyenMai a LEFT JOIN a.khuyenMaiId b
                         LEFT JOIN a.chiTietSPId c LEFT JOIN a.chiTietSPId.sanPham d 
                         LEFT JOIN a.chiTietSPId.hang e LEFT JOIN a.chiTietSPId.mauSac
                         f LEFT JOIN a.chiTietSPId.kichThuoc g LEFT JOIN 
                         a.chiTietSPId.chatLieu h where b.id = :idkhuyenmai
                         """;
            org.hibernate.query.Query query = session.createQuery(hql);
            query.setParameter("idkhuyenmai", idkhuyenmai);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<KMSanphamDangKmReponse> GetAllSanPhamDangApDung1111(String idkhuyenmai) {
        List<KMSanphamDangKmReponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.KMSanphamDangKmReponse(a.id ,c.maChiTietSP, d.ten, e.ten, f.ten, g.ten, h.ten) FROM ChiTietSPKhuyenMai a LEFT JOIN a.khuyenMaiId b LEFT JOIN a.chiTietSPId c LEFT JOIN a.chiTietSPId.sanPham d LEFT JOIN a.chiTietSPId.hang e LEFT JOIN a.chiTietSPId.mauSac f LEFT JOIN a.chiTietSPId.kichThuoc g LEFT JOIN a.chiTietSPId.chatLieu h where b.id = :idkhuyenmai";
            org.hibernate.query.Query query = session.createQuery(hql);
            query.setParameter("idkhuyenmai", idkhuyenmai);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        List<KMSanphamDangKmReponse> List = new KhuyenMaiRepository().aaaaaa("4773ac4c-dd4b-41de-b845-6be8ed07a0b9");
        System.out.println(List);
    }

    public List<KhuyenMaiResponse> FindMaOrTenByInputDangDienRa(String input) {
        List<KhuyenMaiResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.KhuyenMaiResponse(a.id, a.ma,a.ten, a.loaiKhuyenMai,"
                    + " a.giaTri, a.ngayBatDau, a.ngayKetThuc, a.moTa) FROM KhuyenMai a WHERE (a.ma LIKE CONCAT('%',:input,'%') or a.ten LIKE CONCAT('%',:input,'%')) and  a.ngayBatDau < :currentTime and :currentTime <a.ngayKetThuc order by a.createdDate DESC";
            Query query = session.createQuery(hql);
            query.setParameter("input", input);
            query.setParameter("currentTime", new Date());
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<KhuyenMaiResponse> FindMaOrTenByInputDaDienRa(String input) {
        List<KhuyenMaiResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT NEW core.quanly.viewmodel.KhuyenMaiResponse(a.id, a.ma,a.ten, a.loaiKhuyenMai,"
                    + " a.giaTri, a.ngayBatDau, a.ngayKetThuc, a.moTa ) FROM KhuyenMai a WHERE (a.ma LIKE CONCAT('%',:input,'%') or a.ten LIKE CONCAT('%',:input,'%')) and  :currentTime > a.ngayKetThuc order by a.createdDate DESC";
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
            session = HibernateUtil.getSession();
            String hql = "SELECT NEW core.quanly.viewmodel.KhuyenMaiResponse(a.id, a.ma,a.ten, a.loaiKhuyenMai,"
                    + " a.giaTri, a.ngayBatDau, a.ngayKetThuc, a.moTa ) FROM KhuyenMai a WHERE (a.ma LIKE CONCAT('%',:input,'%') or a.ten LIKE CONCAT('%',:input,'%')) and  :currentTime < a.ngayBatDau order by a.createdDate DESC";
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

    public boolean updateKhuyenMaiDangDienRa(String idKhuyenMai) {
        boolean check = false;
        try {
            String sql = "Update ctsp_khuyen_mai set trang_thai = 0 where id_khuyen_mai = :idKhuyenMai";
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            org.hibernate.query.Query query = session.createNativeQuery(sql);
            query.setParameter("idKhuyenMai", idKhuyenMai);
            query.executeUpdate();
            check = true;
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return check;

    }

    public boolean updateKhuyenMaiKhongDienRa(String idKhuyenMai) {
        boolean check = false;
        try {
            String sql = "Update ctsp_khuyen_mai set trang_thai = 1 where id_khuyen_mai = :idKhuyenMai";
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            org.hibernate.query.Query query = session.createNativeQuery(sql);
            query.setParameter("idKhuyenMai", idKhuyenMai);
            query.executeUpdate();
            check = true;
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return check;

    }

    public boolean HuyKhuyenMai(String idctspkm) {
        boolean check = false;
        try {
            String sql = "delete from ctsp_khuyen_mai where id = :idctspkm";
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            org.hibernate.query.Query query = session.createNativeQuery(sql);
            query.setParameter("idctspkm", idctspkm);
            query.executeUpdate();
            check = true;
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return check;

    }

    public List<KhuyenMaiResponse> getAllResponseKhuyenMai() {
        List<KhuyenMaiResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT  NEW core.quanly.viewmodel.KhuyenMaiResponse(a.id, a.ma,a.ten, a.loaiKhuyenMai,  a.giaTri, a.ngayBatDau, a.ngayKetThuc, a.moTa) FROM KhuyenMai a";
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
            String hql = "SELECT new core.quanly.viewmodel.KhuyenMaiResponse(a.id, a.ma,a.ten, a.loaiKhuyenMai,  a.giaTri, a.ngayBatDau, a.ngayKetThuc, a.moTa ) FROM KhuyenMai a where a.ngayBatDau < :currentTime and :currentTime < a.ngayKetThuc order by a.createdDate DESC";
            org.hibernate.query.Query query = session.createQuery(hql);
            query.setParameter("currentTime", new Date());
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<KhuyenMai> GetAllKhuyenMaiDangDienRa() {
        List<KhuyenMai> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM KhuyenMai a where a.ngayBatDau < :currentTime and :currentTime < a.ngayKetThuc order by a.createdDate DESC";
            org.hibernate.query.Query query = session.createQuery(hql);
            query.setParameter("currentTime", new Date());
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<KhuyenMaiResponse> GetKhuyenMaiKhongDienRa() {
        List<KhuyenMaiResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT  NEW core.quanly.viewmodel.KhuyenMaiResponse(a.id, a.ma,a.ten, a.loaiKhuyenMai,  a.giaTri, a.ngayBatDau, a.ngayKetThuc, a.moTa ) FROM KhuyenMai a where a.ngayBatDau > :currentTime or :currentTime > a.ngayKetThuc order by a.createdDate DESC";
            org.hibernate.query.Query query = session.createQuery(hql);
            query.setParameter("currentTime", new Date());
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<KhuyenMai> GetAllKhuyenMaiKhongDienRa() {
        List<KhuyenMai> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM KhuyenMai a where a.ngayBatDau > :currentTime or :currentTime > a.ngayKetThuc order by a.createdDate DESC";
            org.hibernate.query.Query query = session.createQuery(hql);
            query.setParameter("currentTime", new Date());
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

    public List<KhuyenMaiResponse> GetKhuyenMaiSapDienRa() {
        List<KhuyenMaiResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT  NEW core.quanly.viewmodel.KhuyenMaiResponse(a.id, a.ma,a.ten, a.loaiKhuyenMai,  a.giaTri, a.ngayBatDau, a.ngayKetThuc, a.moTa ) FROM KhuyenMai a where  :currentTime < a.ngayBatDau order by a.createdDate DESC";
            org.hibernate.query.Query query = session.createQuery(hql);
            query.setParameter("currentTime", new Date());
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

    public List<KhuyenMaiResponse> GetKhuyenMaiDaDienRa() {
        List<KhuyenMaiResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT  NEW core.quanly.viewmodel.KhuyenMaiResponse(a.id, a.ma,a.ten, a.loaiKhuyenMai,"
                    + "  a.giaTri, a.ngayBatDau, a.ngayKetThuc, a.moTa ) FROM KhuyenMai a where  :currentTime > a.ngayKetThuc order by a.createdDate DESC";
            org.hibernate.query.Query query = session.createQuery(hql);
            query.setParameter("currentTime", new Date());
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

    public int GenMaKhuyenMai() {
        session = HibernateUtil.getSession();
        String maStr = "";
        try {
            String nativequery = "SELECT MAX(CONVERT(INT, SUBSTRING(ma,3,15))) from khuyen_mai";
            Query query = session.createNativeQuery(nativequery);
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

    public List<KMChiTietSPResponse> FindSanPhamByTen(Object input) {
        List<KMChiTietSPResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT NEW core.quanly.viewmodel.KMChiTietSPResponse(a.id, a.maChiTietSP, a.sanPham.ten, a.hang.ten, a.mauSac.ten\n"
                    + "                         , a.kichThuoc.ten, a.chatLieu.ten) FROM ChiTietSP a WHERE (a.sanPham.ten LIKE CONCAT('%',:input,'%') OR a.maChiTietSP LIKE CONCAT('%',:input,'%')) AND (SELECT b.id FROM ChiTietSPKhuyenMai b where b.trangThai = 0 AND b.chiTietSPId.id = a.id) IS NULL";
            javax.persistence.Query query = session.createQuery(hql);
            query.setParameter("input", input);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> SelectTenSanPham() {
        List<String> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT  a.ten FROM SanPham a ";
            javax.persistence.Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
//    

}
