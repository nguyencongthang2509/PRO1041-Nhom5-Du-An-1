package core.quanly.service.impl;

import core.quanly.repository.BanHangRepository;
import core.quanly.service.BanHangService;
import core.quanly.viewmodel.BhChiTietSPResponse;
import core.quanly.viewmodel.BhHoaDonChiTietResponse;
import core.quanly.viewmodel.BhHoaDonResponse;
import core.quanly.viewmodel.BhKhachHangResponse;
import domainmodels.ChiTietSP;
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
    public List<BhHoaDonResponse> getAllResponseHD(String idNhanVien) {
        return banHangRepository.getAllResponseHD(idNhanVien);
    }

    @Override
    public boolean insertHDCT(String idHoaDon, Map<String, BhHoaDonChiTietResponse> list) {
        boolean check = banHangRepository.insertHDCT(idHoaDon, list);
        return check;
    }

    @Override
    public boolean updateSoLuong(Map<String, BhHoaDonChiTietResponse> list) {
        boolean check = banHangRepository.updateSoLuong(list);
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
        hoaDonChiTiet.setTrangThai(0);
        hoaDonChiTiet.setDonGia(bhHoaDonChiTietResponse.getDonGia());
        hoaDonChiTiet.setMaKhuyenMai(bhHoaDonChiTietResponse.getMaKhuyenMai());
        hoaDonChiTiet.setLoaiKhuyenMai(bhHoaDonChiTietResponse.getLoaiKhuyenMai());
        hoaDonChiTiet.setGiaTriKhuyenMai(bhHoaDonChiTietResponse.getGiamGia());
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
            bhHoaDonChiTietResponse.setGiaBan(xx.getGiaBan());
            bhHoaDonChiTietResponse.setGiamGia(xx.getGiamGia());
            bhHoaDonChiTietResponse.setHang(xx.getHang());
            bhHoaDonChiTietResponse.setIdHDCT(xx.getIdHDCT());
            bhHoaDonChiTietResponse.setIdHoaDon(xx.getIdHoaDon());
            bhHoaDonChiTietResponse.setLoaiKhuyenMai(xx.getLoaiKhuyenMai());
            bhHoaDonChiTietResponse.setMaKhuyenMai(xx.getMaKhuyenMai());
            bhHoaDonChiTietResponse.setMaSP(xx.getMaSP());
            bhHoaDonChiTietResponse.setMauSac(xx.getMauSac());
            bhHoaDonChiTietResponse.setSize(xx.getSize());
            bhHoaDonChiTietResponse.setSoLuong(xx.getSoLuong());
            bhHoaDonChiTietResponse.setSoLuongTon(xx.getSoLuongTon());
            bhHoaDonChiTietResponse.setTenSP(xx.getTenSP());
            bhHoaDonChiTietResponse.setTrangThai(xx.getTrangThai());
            mapHoaDonChiTietResponse.put(bhHoaDonChiTietResponse.getIdChiTietSP(), bhHoaDonChiTietResponse);
        }
        return mapHoaDonChiTietResponse;
    }

    @Override
    public boolean updateKhachHangInHoaDon(String idHoaDon, String idKhachHang) {
        HoaDon hoaDon = banHangRepository.findByIdHoaDon(idHoaDon);
        KhachHang khachHang = new KhachHang();
        khachHang.setId(idKhachHang);
        hoaDon.setKhachHang(khachHang);
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
            if(maVach.equals(xx.getMaVach())){
                return xx;
            }
        }
        return null;
    }

    @Override
    public KhachHang findKhachHangById(String id) {
        return banHangRepository.findKhachHangById(id);
    }

}
