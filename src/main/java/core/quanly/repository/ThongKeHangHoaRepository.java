/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.viewmodel.ThongKeHangHoaResponse;
import domainmodels.HoaDon;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import repository.CrudRepository;

/**
 *
 * @author quynhncph26201
 */
public class ThongKeHangHoaRepository {

    public List<ThongKeHangHoaResponse> getListHDCT() {
        List<ThongKeHangHoaResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.ThongKeHangHoaResponse"
                    + "(a.id, a.chiTietSPId.sanPham.ma, a.chiTietSPId.sanPham.ten, a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten, "
                    + "a.chiTietSPId.kichThuoc.ten, a.soLuong, a.donGia)"
                    + " from HoaDonChiTiet a ";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public static void main(String[] args) {
//        int vv = new ThongKeHangHoaRepository().getSL();
//        System.out.println(vv);
    }



  
    

}
