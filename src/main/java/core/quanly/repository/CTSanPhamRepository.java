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
import repository.CrudRepository;

/**
 *
 * @author HP
 */
public class CTSanPhamRepository extends CrudRepository<String, ChiTietSP, CTSanPhamViewModel>{
     public CTSanPhamRepository() {
        className = ChiTietSP.class.getName();
        res = "new viewmodel.CTSanPhamViewModel (a.id, a.sanPham.ma, a.sanPham.ten"
                + ", a.soLuongTon, a.giaBan, a.moTa, a.trangThaiXoa, a.mauSac.ten, a.kichThuoc.ten,a.hang.ten, a.khuyenMai.ten)";
    }

    public List<SanPhamViewModel> findByName(String name) {
        List<SanPhamViewModel> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT new viewmodel.CTSanPhamViewModel"
                    + "(a.id, a.sanPham.ma, a.sanPham.ten"
                    + ", a.soLuongTon, a.giaBan, a.moTa, a.trangThaiXoa) "
                    + " FROM ChiTietSP a WHERE a.sanPham.ten LIKE CONCAT('%',:name,'%')";
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static void main(String[] args) {
        List<ChiTietSP> list = new CTSanPhamRepository().getAll();
        for (ChiTietSP c : list) {
            System.out.println(c.toString());
        }
    }
}
