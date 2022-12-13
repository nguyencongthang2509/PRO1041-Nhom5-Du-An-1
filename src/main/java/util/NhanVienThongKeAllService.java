/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import config.HibernateUtil;
import core.quanly.viewmodel.ThongKeHangHoaResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author quynhncph26201
 */
public class NhanVienThongKeAllService {
    //Lable Thong Ke : Start
     public BigDecimal BaoCaoDT(Date dat1,String id) {
        BigDecimal nv = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select sum(a.thanhTien) from HoaDon a where (a.nhanVien.id =:id)and(a.trangThai=2 or a.trangThai = 5) and (month(a.ngayThanhToan) = month(:dat1) "
                    + "and year(a.ngayThanhToan)= year(:dat1) and day(a.ngayThanhToan)= day(:dat1))";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.setParameter("dat1", dat1);
            nv = (BigDecimal) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (nv == null) {
            nv = BigDecimal.valueOf(0.00);
        }
        return nv;
    }
     public Long BaoCaoHD(Date dat1,String idnv) {
        Long id = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(a.id) from HoaDon a  where (a.nhanVien.id =:id) and (month(a.ngayThanhToan) = month(:dat1) "
                    + "and year(a.ngayThanhToan)= year(:dat1) and day(a.ngayThanhToan)= day(:dat1))";
            Query query = session.createQuery(hql);
            query.setParameter("id", idnv);
            query.setParameter("dat1", dat1);
            id = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }
     public Long BaoCaoHDhuy(Date dat1,String idnv) {
        Long id = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(a.id) from HoaDon a where (a.nhanVien.id =:id)and(a.trangThai=1) and (month(a.ngayThanhToan) = month(:dat1) "
                    + "and year(a.ngayThanhToan)= year(:dat1) and day(a.ngayThanhToan)= day(:dat1))";
            Query query = session.createQuery(hql);
            query.setParameter("id", idnv);
            query.setParameter("dat1", dat1);
            id = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }
     public int BaoCaoKH(Date dat1,String idnv) {
        int id = 0;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(a.id) from khach_hang a inner join hoa_don b on a.id = b.id_khach_hang where(month(b.ngay_thanh_toan) = month(:dat1) \n"
                    + "  and year(b.ngay_thanh_toan)= year(:dat1) and day(b.ngay_thanh_toan)= day(:dat1)) and (b.id_nhan_vien =:id) ";
            Query query = session.createNativeQuery(hql);
            query.setParameter("id", idnv);
            query.setParameter("dat1", dat1);
            id = (int) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
//            return null;

        }
        return id;
    }
//End
     //Table:Start
     public List<ThongKeHangHoaResponse> getListHDCT(String id) {
        List<ThongKeHangHoaResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = """
                         select new core.quanly.viewmodel.ThongKeHangHoaResponse
                         (a.chiTietSPId.id, a.chiTietSPId.maChiTietSP, a.chiTietSPId.sanPham.ten,
                         a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten, 
                        a.chiTietSPId.kichThuoc.ten, SUM(a.soLuong), a.donGia)
                         from HoaDonChiTiet a inner join HoaDon b on a.hoaDonId=b.id where b.id=:id Group by a.chiTietSPId.id, a.chiTietSPId.maChiTietSP, 
                         a.chiTietSPId.sanPham.ten, a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten,
                         a.chiTietSPId.kichThuoc.ten,a.donGia
                         """;
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
     //End
     public static void main(String[] args) throws ParseException {
         String vv ="2022/12/07";
         SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
        Date dd= fm.parse(vv);
        BigDecimal bg = new NhanVienThongKeAllService().BaoCaoDT(dd, "14a4e99f-12b0-401d-b167-769d5e798301");
         System.out.println(bg);
    }
     
}
