package core.quanly.service.impl;


import core.quanly.repository.BanHangRepository;
import core.quanly.service.BanHangService;
import core.quanly.viewmodel.BhChiTietSPResponse;
import core.quanly.viewmodel.BhHoaDonChiTietResponse;
import core.quanly.viewmodel.BhHoaDonResponse;
import core.quanly.viewmodel.BhKhachHangResponse;
import core.quanly.viewmodel.BhNhanVienResponse;
import core.quanly.viewmodel.CTSanPhamResponse;
import domainmodels.ChiTietSP;
import domainmodels.ChiTietSPKhuyenMai;
import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import domainmodels.KhachHang;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author thangncph26123
 */
public class BanHangServiceImpl implements BanHangService {

    private BanHangRepository banHangRepository;

    public BanHangServiceImpl() {
        banHangRepository = new BanHangRepository();
    }

    @Override
    public List<BhChiTietSPResponse> getAllChiTietSP() {
        return banHangRepository.getAllResponseCTSP();
    }

    @Override
    public List<BhKhachHangResponse> getAllKhachHangResponse() {
        return banHangRepository.getAllResponseKhachHang();
    }

    @Override
    public int genMaHoaDonTuDong() {
        return banHangRepository.genMaHoaDon();
    }

    @Override
    public String saveOrUpdate(HoaDon hoaDon) {
        hoaDon = banHangRepository.saveOrUpdateHD(hoaDon);
        if (hoaDon != null) {
            return "Tạo hóa đơn thành công";
        } else {
            return "Tạo hóa đơn thất bại";
        }
    }

    @Override
    public KhachHang findByMaKhachHang(String ma) {
        return banHangRepository.findByMaKhachHang(ma);
    }

    @Override
    public List<BhHoaDonResponse> getAllResponseHD(String idNhanVien, Integer hinhThucGiaoHang, Integer trangThai) {
        return banHangRepository.getAllResponseHD(idNhanVien, hinhThucGiaoHang, trangThai);
    }

    @Override
    public boolean insertHDCT(String idHoaDon, Map<String, BhHoaDonChiTietResponse> list) {
        boolean check = banHangRepository.insertHDCT(idHoaDon, list);
        return check;
    }

    @Override
    public boolean updateSoLuong(String id, Integer soLuong) {
        boolean check = banHangRepository.updateSoLuong(id, soLuong);
        return check;
    }

    @Override
    public boolean updateTrangThaiHoaDon(String id) {
        boolean check = banHangRepository.updateTrangThaiHoaDon(id);
        return check;
    }

    @Override
    public boolean saveOrUpdateHDCT(HoaDonChiTiet entity) {
        HoaDonChiTiet hoaDonChiTiet = banHangRepository.saveOrUpdateHDCT(entity);
        if (hoaDonChiTiet != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteHDCT(HoaDonChiTiet hoaDonChiTiet) {
        boolean check = banHangRepository.deleteHDCT(hoaDonChiTiet);
        return check;
    }

    @Override
    public HoaDonChiTiet convertHoaDonChiTiet(BhHoaDonChiTietResponse bhHoaDonChiTietResponse) {
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();

        ChiTietSP chiTietSP = new ChiTietSP();
        chiTietSP.setId(bhHoaDonChiTietResponse.getIdChiTietSP());
        HoaDon hoaDon = new HoaDon();
        hoaDon.setId(bhHoaDonChiTietResponse.getIdHoaDon());

        hoaDonChiTiet.setChiTietSPId(chiTietSP);
        hoaDonChiTiet.setHoaDonId(hoaDon);
        hoaDonChiTiet.setSoLuong(bhHoaDonChiTietResponse.getSoLuong());
        hoaDonChiTiet.setGiamGiaKhuyenMai(bhHoaDonChiTietResponse.getGiamGia());
        hoaDonChiTiet.setDonGia(bhHoaDonChiTietResponse.getDonGia());
        hoaDonChiTiet.setGiaBan(bhHoaDonChiTietResponse.getGiaBan());
        return hoaDonChiTiet;
    }

    @Override
    public List<BhHoaDonChiTietResponse> getAllHDCTByIdHoaDon(String idHoaDon) {
        return banHangRepository.getAllHDCTByIdHoaDon(idHoaDon);
    }

    @Override
    public Map<String, BhHoaDonChiTietResponse> convertToHoaDonChiTietResponse(List<BhHoaDonChiTietResponse> list) {
        Map<String, BhHoaDonChiTietResponse> mapHoaDonChiTietResponse = new HashMap<>();
        for (BhHoaDonChiTietResponse xx : list) {
            BhHoaDonChiTietResponse bhHoaDonChiTietResponse = new BhHoaDonChiTietResponse();
            bhHoaDonChiTietResponse.setIdChiTietSP(xx.getIdChiTietSP());
            bhHoaDonChiTietResponse.setDonGia(xx.getDonGia());
            bhHoaDonChiTietResponse.setMaCTSP(xx.getMaCTSP());
            bhHoaDonChiTietResponse.setGiaBan(xx.getGiaBan());
            bhHoaDonChiTietResponse.setGiamGia(xx.getGiamGia());
            bhHoaDonChiTietResponse.setHang(xx.getHang());
            bhHoaDonChiTietResponse.setIdHDCT(xx.getIdHDCT());
            bhHoaDonChiTietResponse.setIdHoaDon(xx.getIdHoaDon());
            bhHoaDonChiTietResponse.setMaSP(xx.getMaSP());
            bhHoaDonChiTietResponse.setMauSac(xx.getMauSac());
            bhHoaDonChiTietResponse.setSize(xx.getSize());
            bhHoaDonChiTietResponse.setSoLuong(xx.getSoLuong());
            bhHoaDonChiTietResponse.setSoLuongTon(xx.getSoLuongTon());
            bhHoaDonChiTietResponse.setTenSP(xx.getTenSP());
            mapHoaDonChiTietResponse.put(bhHoaDonChiTietResponse.getIdChiTietSP(), bhHoaDonChiTietResponse);
        }
        return mapHoaDonChiTietResponse;
    }

    @Override
    public boolean updateKhachHangInHoaDon(String idHoaDon, String idKhachHang) {
        HoaDon hoaDon = banHangRepository.findByIdHoaDon(idHoaDon);
        KhachHang khachHang = banHangRepository.findByIdKhachHang(idKhachHang);
        hoaDon.setKhachHang(khachHang);
        if (khachHang.getCapBac() != null) {
            if (khachHang.getCapBac() == 0) {
                hoaDon.setPhamTramGiamGia(0.0);
            } else if (khachHang.getCapBac() == 1) {
                hoaDon.setPhamTramGiamGia(3.0);
            } else if (khachHang.getCapBac() == 2) {
                hoaDon.setPhamTramGiamGia(5.0);
            } else if (khachHang.getCapBac() == 3) {
                hoaDon.setPhamTramGiamGia(10.0);
            }
        } else {
            hoaDon.setPhamTramGiamGia(0.0);
        }
        banHangRepository.saveOrUpdateHD(hoaDon);
        return true;
    }

    @Override
    public String findChiTietSPByMaVach(String maVach) {
        return banHangRepository.findChiTietSPByMaVach(maVach);
    }

    @Override
    public BhChiTietSPResponse findCTSPByMaVach(List<BhChiTietSPResponse> list, String maVach) {
        for (BhChiTietSPResponse xx : list) {
            if (maVach.equals(xx.getMaVach())) {
                return xx;
            }
        }
        return null;
    }

    @Override
    public KhachHang findKhachHangById(String id) {
        return banHangRepository.findKhachHangById(id);
    }

    @Override
    public HoaDon findByIdHoaDon(String id) {
        return banHangRepository.findByIdHoaDon(id);
    }

    @Override
    public ChiTietSPKhuyenMai getCTSPKhuyenMai(String idChiTietSP) {
        return banHangRepository.getCTSPKhuyenMai(idChiTietSP);
    }

    @Override
    public HoaDonChiTiet findByIdHoaDonChiTiet(String id) {
        return banHangRepository.findByIdHoaDonChiTiet(id);
    }

    @Override
    public List<BhNhanVienResponse> getAllNhanVienResponse() {
        return banHangRepository.getAllNhanVienResponse();
    }

    @Override
    public int genMaKH() {
        return banHangRepository.genMaKH();
    }

    @Override
    public boolean saveOrUpdateKH(KhachHang entity) {
        KhachHang khachHang = banHangRepository.saveOrUpdateKH(entity);
        if (khachHang != null) {
            return true;
        }
        return false;
    }

    @Override
    public ChiTietSP findChiTietSPById(String id) {
        return banHangRepository.findChiTietSPById(id);
    }

    @Override
    public List<BhKhachHangResponse> findKhachHang(String input) {
        return banHangRepository.findKhachHang(input);
    }

    @Override
    public List<BhNhanVienResponse> findNhanVien(String input) {
        return banHangRepository.findNhanVien(input);
    }

    @Override
    public List<BhHoaDonResponse> getAllResponseHDByTrangThai(String idNhanVien, int trangThaiThanhToan) {
        return banHangRepository.getAllResponseHDByTrangThai(idNhanVien, trangThaiThanhToan);
    }

    @Override
    public List<BhChiTietSPResponse> findCTSP(String hang, String mauSac, String kichThuoc) {
        return banHangRepository.findCTSP(hang, mauSac, kichThuoc);
    }

    @Override
    public List<BhChiTietSPResponse> findCTSPByMa(String input) {
        return banHangRepository.findCTSPByMa(input);
    }

    @Override
    public List<CTSanPhamResponse> findCTSPCTSP(String sanPham, String hang, String mauSac, String kichThuoc, String chatLieu, Integer trangThai) {
        return banHangRepository.findCTSPCTSP(sanPham, hang, mauSac, kichThuoc, chatLieu, trangThai);
    }

    @Override
    public Long countHoaDonChoGiaoHang() {
        return banHangRepository.countHoaDonChoGiaoHang();
    }

}
