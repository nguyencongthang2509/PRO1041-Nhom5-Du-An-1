/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.viewmodel.CTSanPhamViewModel;
import core.quanly.viewmodel.SanPhamViewModel;
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
public class CTSanPhamRepository extends CrudRepository<String, ChiTietSP, CTSanPhamViewModel> {

    public CTSanPhamRepository() {
        className = ChiTietSP.class.getName();
        res = "new core.quanly.viewmodel.CTSanPhamViewModel(a.id, a.sanPham.ma, a.sanPham.ten,"
                + "a.mauSac.ten, a.kichThuoc.ten,a.hang.ma, a.khuyenMai.giaTri ,a.maChiTietSP, a.soLuongTon, a.giaBan, a.moTa , a.maVach)";
    }

    public static void main(String[] args) {
        List<CTSanPhamViewModel> list = new CTSanPhamRepository().getAllResponse();
        System.out.println(list);
    }
//
//    public List<SanPhamViewModel> findByName(String name) {
//        List<SanPhamViewModel> list = new ArrayList<>();
//        try {
//            session = HibernateUtil.getSession();
//            String hql = "SELECT new viewmodel.CTSanPhamViewModel"
//                    + "(a.id, a.sanPham.ma, a.sanPham.ten"
//                    + ", a.soLuongTon, a.giaBan, a.moTa, a.trangThaiXoa) "
//                    + " FROM ChiTietSP a WHERE a.sanPham.ten LIKE CONCAT('%',:name,'%')";
//            Query query = session.createQuery(hql);
//            query.setParameter("name", name);
//            list = query.getResultList();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }

    public List<CTSanPhamViewModel> findByMaOrTen(String inpput) {
        List<CTSanPhamViewModel> lst = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.CTSanPhamViewMode(a.id, a.sanPham.ma, a.sanPham.ten"
                    + ", a.soLuongTon, a.giaBan, a.moTa, a.trangThaiXoa, a.mauSac.ten, a.kichThuoc.ten,a.hang.ten, a.khuyenMai.ten) "
                    + "FROM ChiTietSP a WHERE a.ma LIKE CONCAT('%',:input,'%','%' ,'%','%','%' ,'%','%','%' ,'%') OR a.ten LIKE CONCAT"
                    + "('%',:input,'%','%' ,'%','%','%' ,'%','%','%' ,'%')";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    public List<CTSanPhamViewModel> getcbbList(String ctSanPham) {
        List<CTSanPhamViewModel> listsv = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.CTSanPhamViewModel(a.id, a.sanPham.ma, a.sanPham.ten,"
                + "a.mauSac.ten, a.kichThuoc.ten,a.hang.ten, a.khuyenMai.giaTri ,a.maChiTietSP, a.soLuongTon, a.giaBan, a.moTa , a.maVach)"
                    + " FROM ChiTietSP a WHERE a.hang.ma LIKE CONCAT('%','%','%','%','%',:ma ,'%','%','%','%','%','%')";
            Query query = session.createQuery(hql);
            query.setParameter("ma", ctSanPham);
            listsv = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listsv;
    }
    
//    public static void main(String[] args) {
//        List<ChiTietSP> list = new CTSanPhamRepository().getAll();
//        for (ChiTietSP c : list) {
//            System.out.println(c.toString());
//        }
//    }
}
