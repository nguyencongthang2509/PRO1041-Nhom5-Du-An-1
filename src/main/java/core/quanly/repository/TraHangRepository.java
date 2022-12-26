
package core.quanly.repository;


import config.HibernateUtil;
import core.quanly.viewmodel.ThHoaDonResponse;
import domainmodels.HoaDon;
import domainmodels.HoaDonTraHang;
import domainmodels.HoaDonTraHangChiTiet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.query.Query;
import repository.CrudRepository;

/**
 *
 * @author thangncph26123
 */
public class TraHangRepository extends CrudRepository<String, HoaDon, Object> {

    public List<ThHoaDonResponse> getAllResponseHD() {
        List<ThHoaDonResponse> list = new ArrayList<>();
        Date now = new Date();
        Long ngay = now.getTime() - 259200000;
        Date ngayTra = new Date(ngay);
        try {
                session = HibernateUtil.getSession();
                String hql = "SELECT " + "new core.quanly.viewmodel.ThHoaDonResponse"
                        + "(a.id, a.ma, a.ngayTao, a.hinhThucGiaoHang, a.hinhThucThanhToan ,b.ten, c.ma, c.hoTen, c.sdt,c.diaChi,c.capBac,a.trangThaiThanhToan, a.ngayMongMuon,a.ngayThanhToan ,a.trangThai,"
                        + "a.tenNguoiNhan, a.sdtNguoiNhan, a.diaChi, a.tenNguoiShip, a.sdtNguoiShip,"
                        + "a.tienShip, a.tienKhachTra, a.tienKhachChuyenKhoan, a.thanhTien,a.tienThua, a.phamTramGiamGia)"
                        + " FROM HoaDon a LEFT JOIN a.nhanVien b LEFT JOIN a.khachHang c "
                        + "WHERE (a.trangThai = 2 or a.trangThai = 5) AND (select count(g.id) FROM HoaDonChiTiet g where g.hoaDonId.id = a.id) > 0 AND c.ma <> 'KH000' AND a.ngayThanhToan > :ngayTra"
                        + " ORDER BY a.createdDate DESC";
                Query query = session.createQuery(hql);
                query.setParameter("ngayTra", ngayTra);
                list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    
    public List<HoaDon> getAllHoaDonTraHang() {
        List<HoaDon> list = new ArrayList<>();
        try {
                session = HibernateUtil.getSession();
                String hql = "SELECT a FROM HoaDon a where (select count(b.id) FROM HoaDonTraHang b where b.hoaDon.id = a.id) > 0 Order by a.ngayThanhToan DESC";
                Query query = session.createQuery(hql);
                list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    
    public List<HoaDon> findHoaDonById(String input) {
        List<HoaDon> list = new ArrayList<>();
        try {
                session = HibernateUtil.getSession();
                String hql = "SELECT a FROM HoaDon a where (select count(b.id) FROM HoaDonTraHang b where b.hoaDon.id = a.id) > 0 AND (a.ma LIKE CONCAT('%',:input,'%') OR a.khachHang.sdt LIKE CONCAT('%',:input,'%') OR a.khachHang.hoTen LIKE CONCAT('%',:input,'%')) Order by a.ngayThanhToan DESC";
                Query query = session.createQuery(hql);
                query.setParameter("input", input);
                list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    
    public HoaDon getHoaDonByMa(String ma) {
        HoaDon hoaDon = new HoaDon();
        try {
                session = HibernateUtil.getSession();
                String hql = "SELECT a FROM HoaDon a where a.ma = :ma";
                Query query = session.createQuery(hql);
                query.setParameter("ma", ma);
                hoaDon = (HoaDon) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return hoaDon;
    }
    
    public boolean updateSoLuong(String idHDCT, Integer soLuong) {
        boolean check = false;
        String sql = "UPDATE HoaDonChiTiet SET soLuong = soLuong - :soLuongTra WHERE id = :id";
        session = HibernateUtil.getSession();
        trans = session.beginTransaction();
        try {
            Query query = session.createQuery(sql);
            query.setParameter("soLuongTra", soLuong);
            query.setParameter("id", idHDCT);
            query.executeUpdate();
            check = true;
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        return check;
    }
    
    public List<ThHoaDonResponse> getAllResponseHDTraHang() {
        List<ThHoaDonResponse> list = new ArrayList<>();
        try {
                session = HibernateUtil.getSession();
                String hql = "SELECT " + "new core.quanly.viewmodel.ThHoaDonResponse"
                        + "(a.id, a.ma, a.ngayTao, a.hinhThucGiaoHang, a.hinhThucThanhToan ,b.ten, c.ma, c.hoTen, c.sdt,c.diaChi,c.capBac,a.trangThaiThanhToan, a.ngayMongMuon,a.ngayThanhToan ,a.trangThai,"
                        + "a.tenNguoiNhan, a.sdtNguoiNhan, a.diaChi, a.tenNguoiShip, a.sdtNguoiShip,"
                        + "a.tienShip, a.tienKhachTra, a.tienKhachChuyenKhoan, a.thanhTien,a.tienThua,a.phamTramGiamGia)"
                        + " FROM HoaDon a LEFT JOIN a.nhanVien b LEFT JOIN a.khachHang c "
                        + "WHERE a.trangThai = 7 AND c.ma <> 'KH000'"
                        + " ORDER BY a.createdDate";
                Query query = session.createQuery(hql);
                list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    
    public List<ThHoaDonResponse> findHoaDon(String input) {
        List<ThHoaDonResponse> list = new ArrayList<>();
        try {
                session = HibernateUtil.getSession();
                String hql = "SELECT " + "new core.quanly.viewmodel.ThHoaDonResponse"
                        + "(a.id, a.ma, a.ngayTao, a.hinhThucGiaoHang, a.hinhThucThanhToan ,b.ten, c.ma, c.hoTen, c.sdt,c.diaChi,c.capBac,a.trangThaiThanhToan, a.ngayMongMuon, a.ngayThanhToan,a.trangThai,"
                        + "a.tenNguoiNhan, a.sdtNguoiNhan, a.diaChi, a.tenNguoiShip, a.sdtNguoiShip,"
                        + "a.tienShip, a.tienKhachTra, a.tienKhachChuyenKhoan, a.thanhTien,a.tienThua, a.phamTramGiamGia)"
                        + " FROM HoaDon a LEFT JOIN a.nhanVien b LEFT JOIN a.khachHang c "
                        + "WHERE (a.trangThai = 2 or a.trangThai = 5) AND c.ma <> 'KH000' AND (a.ma LIKE CONCAT('%',:input,'%') OR c.sdt LIKE CONCAT('%',:input,'%') OR c.hoTen LIKE CONCAT('%',:input,'%'))"
                        + " ORDER BY a.createdDate DESC";
                Query query = session.createQuery(hql);
                query.setParameter("input", input);
                list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    
    public HoaDonTraHang findHoaDonTraHang(String idMoi, String idCu) {
        HoaDonTraHang list = new HoaDonTraHang();
        try {
                session = HibernateUtil.getSession();
                String hql = "SELECT a FROM HoaDonTraHang a WHERE a.hoaDonMoi.id = :idMoi AND a.hoaDonCu.id = :idCu";
                Query query = session.createQuery(hql);
                query.setParameter("idMoi", idMoi);
                query.setParameter("idCu", idCu);
                list = (HoaDonTraHang) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    
    public List<HoaDonTraHang> getAllHoaDonTraHangByIdHoaDon(String id){
        List<HoaDonTraHang> list = new ArrayList<>();
        try {
                session = HibernateUtil.getSession();
                String hql = "SELECT a FROM HoaDonTraHang a WHERE a.hoaDon.id = :id ORder by a.createdDate desc";
                Query query = session.createQuery(hql);
                query.setParameter("id", id);
                list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    
//    public static void main(String[] args) {
//        HoaDonTraHang hang = new TraHangRepository().findHoaDonTraHang("4459dc5d-0666-480d-8f7f-35d493359a80");
//        System.out.println(hang);
//    }
    
    public List<HoaDonTraHangChiTiet> getAllHoaDonTraHangChiTiet(String id) {
        List<HoaDonTraHangChiTiet> list = new ArrayList<>();
        try {
                session = HibernateUtil.getSession();
                String hql = "SELECT a FROM HoaDonTraHangChiTiet a WHERE a.hoaDonDoiTra.id = :id";
                Query query = session.createQuery(hql);
                query.setParameter("id", id);
                list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    
    public HoaDonTraHang saveOrUpdateHoaDonTraHang(HoaDonTraHang entity) {
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
    
    public HoaDonTraHangChiTiet saveOrUpdateHoaDonTraHang(HoaDonTraHangChiTiet entity) {
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
    
}
