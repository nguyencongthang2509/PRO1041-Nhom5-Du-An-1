package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.viewmodel.BhChiTietSPResponse;
import core.quanly.viewmodel.BhHoaDonChiTietResponse;
import core.quanly.viewmodel.BhHoaDonResponse;
import core.quanly.viewmodel.BhKhachHangResponse;
import core.quanly.viewmodel.BhNhanVienResponse;
import domainmodels.ChiTietSP;
import domainmodels.ChiTietSPKhuyenMai;
import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import domainmodels.KhachHang;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import repository.CrudRepository;

/**
 *
 * @author thangncph26123
 */
public class BanHangRepository extends CrudRepository<String, ChiTietSP, BhChiTietSPResponse> {

    public BanHangRepository() {
        className = ChiTietSP.class.getName();
        res = "new core.quanly.viewmodel.BhChiTietSPResponse(a.id, a.sanPham.ma, a.sanPham.ten, a.mauSac.ten, a.kichThuoc.ten, a.hang.ten, a.soLuongTon, a.khuyenMai.loaiKhuyenMai, a.khuyenMai.giaTri, a.giaBan, a.moTa, a.maVach)";
    }

    public List<BhChiTietSPResponse> getAllResponseCTSP() {
        List<BhChiTietSPResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT " + "new core.quanly.viewmodel.BhChiTietSPResponse"
                    + "(a.id, a.maChiTietSP,a.sanPham.ma, a.sanPham.ten, "
                    + "a.mauSac.ten, a.kichThuoc.ten, a.hang.ten, a.soLuongTon, a.giaBan, a.moTa, a.maVach)"
                    + " FROM ChiTietSP a WHERE a.trangThaiXoa = 0 order by a.createdDate DESC";
            Query query = session.createQuery(hql);
//            query.setFirstResult(1);
//            query.setMaxResults(8);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public ChiTietSPKhuyenMai getCTSPKhuyenMai(String idChiTietSP) {
        ChiTietSPKhuyenMai chiTietSPKhuyenMai = new ChiTietSPKhuyenMai();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM ChiTietSPKhuyenMai a WHERE a.chiTietSPId.id = :idChiTietSP AND a.trangThai = 0";
            Query query = session.createQuery(hql);
            query.setParameter("idChiTietSP", idChiTietSP);
            chiTietSPKhuyenMai = (ChiTietSPKhuyenMai) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return chiTietSPKhuyenMai;
    }

    public static void main(String[] args) {
        List<BhHoaDonChiTietResponse> list = new BanHangRepository().getAllHDCTByIdHoaDon("7b207910-96b6-4859-ba86-00b01a6f0f37");
        System.out.println(list);
    }

    public List<BhHoaDonResponse> getAllResponseHD(String idNhanVien, Integer hinhThucGiaoHang, Integer trangThai) {
        List<BhHoaDonResponse> list = new ArrayList<>();
        try {
            if (hinhThucGiaoHang >= 0 && trangThai >= 0) {
                session = HibernateUtil.getSession();
                String hql = "SELECT " + "new core.quanly.viewmodel.BhHoaDonResponse"
                        + "(a.id, a.ma, a.ngayTao, a.hinhThucGiaoHang, a.hinhThucThanhToan ,b.ten, c.ma, c.hoTen, a.trangThaiThanhToan, a.ngayMongMuon, a.trangThai,"
                        + "a.tenNguoiNhan, a.sdtNguoiNhan, a.diaChi, a.tenNguoiShip, a.sdtNguoiShip,"
                        + "a.tienShip, a.tienKhachTra, a.tienKhachChuyenKhoan, a.tienThua)"
                        + " FROM HoaDon a LEFT JOIN a.nhanVien b LEFT JOIN a.khachHang c "
                        + "WHERE a.trangThai <> 1 AND b.id = :idNhanVien AND a.hinhThucGiaoHang LIKE CONCAT('%', CONVERT(VARCHAR, :hinhThucGiaoHang),'%') AND a.trangThai LIKE CONCAT('%',CONVERT(VARCHAR, :trangThai),'%')"
                        + " ORDER BY a.lastModifiedDate DESC";
                Query query = session.createQuery(hql);
                query.setParameter("idNhanVien", idNhanVien);
                query.setParameter("hinhThucGiaoHang", hinhThucGiaoHang);
                query.setParameter("trangThai", trangThai);
                list = query.getResultList();
            }
            if (hinhThucGiaoHang == -1) {
                session = HibernateUtil.getSession();
                String hql = "SELECT " + "new core.quanly.viewmodel.BhHoaDonResponse"
                        + "(a.id, a.ma, a.ngayTao, a.hinhThucGiaoHang, a.hinhThucThanhToan ,b.ten, c.ma, c.hoTen, a.trangThaiThanhToan , a.ngayMongMuon,a.trangThai,"
                        + "a.tenNguoiNhan, a.sdtNguoiNhan, a.diaChi, a.tenNguoiShip, a.sdtNguoiShip,"
                        + "a.tienShip, a.tienKhachTra, a.tienKhachChuyenKhoan, a.tienThua)"
                        + " FROM HoaDon a LEFT JOIN a.nhanVien b LEFT JOIN a.khachHang c "
                        + "WHERE a.trangThai <> 1 AND b.id = :idNhanVien AND a.hinhThucGiaoHang LIKE CONCAT('%','','%') AND a.trangThai LIKE CONCAT('%',CONVERT(VARCHAR, :trangThai),'%')"
                        + " ORDER BY a.lastModifiedDate DESC";
                Query query = session.createQuery(hql);
                query.setParameter("idNhanVien", idNhanVien);
                query.setParameter("trangThai", trangThai);
                list = query.getResultList();
            }
            if (trangThai == -1) {
                session = HibernateUtil.getSession();
                String hql = "SELECT " + "new core.quanly.viewmodel.BhHoaDonResponse"
                        + "(a.id, a.ma, a.ngayTao, a.hinhThucGiaoHang, a.hinhThucThanhToan ,b.ten, c.ma, c.hoTen,  a.trangThaiThanhToan ,a.ngayMongMuon,a.trangThai,"
                        + "a.tenNguoiNhan, a.sdtNguoiNhan, a.diaChi, a.tenNguoiShip, a.sdtNguoiShip,"
                        + "a.tienShip, a.tienKhachTra, a.tienKhachChuyenKhoan, a.tienThua)"
                        + " FROM HoaDon a LEFT JOIN a.nhanVien b LEFT JOIN a.khachHang c "
                        + "WHERE a.trangThai <> 1 AND b.id = :idNhanVien AND a.hinhThucGiaoHang LIKE CONCAT('%', CONVERT(VARCHAR, :hinhThucGiaoHang),'%') AND a.trangThai LIKE CONCAT('%','','%')"
                        + " ORDER BY a.lastModifiedDate DESC";
                Query query = session.createQuery(hql);
                query.setParameter("idNhanVien", idNhanVien);
                query.setParameter("hinhThucGiaoHang", hinhThucGiaoHang);
                list = query.getResultList();
            }
            if (hinhThucGiaoHang == -1 && trangThai == -1) {
                session = HibernateUtil.getSession();
                String hql = "SELECT " + "new core.quanly.viewmodel.BhHoaDonResponse"
                        + "(a.id, a.ma, a.ngayTao, a.hinhThucGiaoHang, a.hinhThucThanhToan ,b.ten, c.ma, c.hoTen, a.trangThaiThanhToan ,a.ngayMongMuon, a.trangThai,"
                        + "a.tenNguoiNhan, a.sdtNguoiNhan, a.diaChi, a.tenNguoiShip, a.sdtNguoiShip,"
                        + "a.tienShip, a.tienKhachTra, a.tienKhachChuyenKhoan, a.tienThua)"
                        + " FROM HoaDon a LEFT JOIN a.nhanVien b LEFT JOIN a.khachHang c "
                        + "WHERE a.trangThai <> 1 AND b.id = :idNhanVien"
                        + " ORDER BY a.lastModifiedDate DESC";
                Query query = session.createQuery(hql);
                query.setParameter("idNhanVien", idNhanVien);
                list = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<BhHoaDonChiTietResponse> getAllHDCTByIdHoaDon(String idHoaDon) {
        List<BhHoaDonChiTietResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = """
                         SELECT new core.quanly.viewmodel.BhHoaDonChiTietResponse
                         (b.id, a.id, c.id, b.maChiTietSP, b.sanPham.ma,
                         b.sanPham.ten, b.hang.ten, b.chatLieu.ten,
                         b.mauSac.ten, b.kichThuoc.ten, b.giaBan, a.soLuong, b.soLuongTon,
                         a.giamGiaKhuyenMai, a.giaBan)
                         FROM HoaDonChiTiet a LEFT JOIN a.chiTietSPId b 
                         LEFT JOIN a.hoaDonId c
                         WHERE c.id = :idHoaDon
                         """;
            Query query = session.createQuery(hql);
            query.setParameter("idHoaDon", idHoaDon);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

//    public boolean updateKhachHangInHoaDon(String idHoaDon, String idKhachHang) {
//        boolean check = false;
//        session = HibernateUtil.getSession();
//        trans = session.beginTransaction();
//        try {
//            Query query = session.createNativeQuery("UPDATE hoa_don SET id_khach_hang = :idKhachHang WHERE id = :idHoaDon");
//            query.setParameter("idKhachHang", idKhachHang);
//            query.setParameter("idHoaDon", idHoaDon);
//            query.executeUpdate();
//            check = true;
//            trans.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            trans.rollback();
//        }
//        return check;
//    }
    public HoaDon findByIdHoaDon(String id) {
        HoaDon entity = null;
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM HoaDon a WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            entity = (HoaDon) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    public HoaDonChiTiet findByIdHoaDonChiTiet(String id) {
        HoaDonChiTiet entity = null;
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM HoaDonChiTiet a WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            entity = (HoaDonChiTiet) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    public String findChiTietSPByMaVach(String maVach) {
        String id = null;
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT a.id FROM ChiTietSP a WHERE a.maVach = :maVach";
            Query query = session.createQuery(hql);
            query.setParameter("maVach", maVach);
            id = (String) query.getSingleResult();
        } catch (Exception e) {
        }
        return id;
    }

    public KhachHang findKhachHangById(String id) {
        KhachHang khachHang = null;
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM KhachHang a WHERE a.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            khachHang = (KhachHang) query.getSingleResult();
        } catch (Exception e) {
        }
        return khachHang;
    }

//    public static void main(String[] args) {
//        List<BhHoaDonChiTietResponse> list = new BanHangRepository().getAllHDCTByIdHoaDon("3b278840-82a0-4af1-92b4-a23ed60d0e00");
//        System.out.println(list);
//    }
    public boolean updateSoLuong(Map<String, BhHoaDonChiTietResponse> list) {
        boolean check = false;
        String sql = "UPDATE ChiTietSP SET soLuongTon = soLuongTon - :soLuongMua WHERE id = :id";
        session = HibernateUtil.getSession();
        trans = session.beginTransaction();
        try {
            for (Map.Entry<String, BhHoaDonChiTietResponse> entry : list.entrySet()) {
                BhHoaDonChiTietResponse value = entry.getValue();
                Query query = session.createQuery(sql);
                query.setParameter("soLuongMua", value.getSoLuong());
                query.setParameter("id", value.getIdChiTietSP());
                query.executeUpdate();
            }
            check = true;
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        return check;
    }

    public HoaDon saveOrUpdateHD(HoaDon entity) {
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

    public HoaDonChiTiet saveOrUpdateHDCT(HoaDonChiTiet entity) {
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
    
    public KhachHang saveOrUpdateKH(KhachHang entity) {
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

    public boolean deleteHDCT(HoaDonChiTiet hoaDonChiTiet) {
        boolean check = false;
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            session.delete(hoaDonChiTiet);
            trans.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        return check;
    }

    public KhachHang findByMaKhachHang(String ma) {
        KhachHang khachHang = null;
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM KhachHang a WHERE a.ma = :ma";
            Query query = session.createQuery(hql);
            query.setParameter("ma", ma);
            if (query.getSingleResult() != null) {
                khachHang = (KhachHang) query.getSingleResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return khachHang;
    }

    public List<BhKhachHangResponse> getAllResponseKhachHang() {
        List<BhKhachHangResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT " + "new core.quanly.viewmodel.BhKhachHangResponse"
                    + "(a.id, a.ma, a.hoTen, a.sdt, a.email, a.gioiTinh, a.diaChi, a.capBac)"
                    + " FROM KhachHang a";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<BhNhanVienResponse> getAllNhanVienResponse() {
        List<BhNhanVienResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT " + "new core.quanly.viewmodel.BhNhanVienResponse"
                    + "(a.id, a.ma, a.ten, a.sdt, a.email, a.diaChi)"
                    + " FROM NhanVien a where a.vaiTro = 1";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public int genMaHoaDon() {
        String maStr = "";
        session = HibernateUtil.getSession();
        try {
            String nativeQuery = "SELECT MAX(CONVERT(INT, SUBSTRING(ma,3,10))) from hoa_don";
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
    
    public int genMaKH() {
        String maStr = "";
        session = HibernateUtil.getSession();
        try {
            String nativeQuery = "SELECT MAX(CONVERT(INT, SUBSTRING(ma,3,10))) from khach_hang";
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
    
    

    public boolean insertHDCT(String idHoaDon, Map<String, BhHoaDonChiTietResponse> list) {
        boolean check = false;
        session = HibernateUtil.getSession();
        trans = session.beginTransaction();
        try {
            for (Map.Entry<String, BhHoaDonChiTietResponse> entry : list.entrySet()) {
                BhHoaDonChiTietResponse value = entry.getValue();

                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();

                ChiTietSP chiTietSP = new ChiTietSP();
                chiTietSP.setId(value.getIdChiTietSP());
                HoaDon hoaDon = new HoaDon();
                hoaDon.setId(idHoaDon);

                hoaDonChiTiet.setChiTietSPId(chiTietSP);
                hoaDonChiTiet.setHoaDonId(hoaDon);
                hoaDonChiTiet.setDonGia(value.getDonGia());
                hoaDonChiTiet.setGiaBan(value.getGiaBan());
                hoaDonChiTiet.setSoLuong(value.getSoLuong());

                session.saveOrUpdate(hoaDonChiTiet);
            }
            trans.commit();
            check = true;
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
        return check;
    }

    public boolean updateTrangThaiHoaDon(String id) {
        boolean check = false;
        session = HibernateUtil.getSession();
        trans = session.beginTransaction();
        try {
            Query query = session.createQuery("UPDATE HoaDon SET trang_thai = 2 WHERE id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            check = true;
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        return check;
    }

}
