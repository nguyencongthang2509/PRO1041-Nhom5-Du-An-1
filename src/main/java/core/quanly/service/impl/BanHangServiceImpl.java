package core.quanly.service.impl;

import core.quanly.repository.BanHangRepository;
import core.quanly.service.BanHangService;
import core.quanly.viewmodel.BhChiTietSPResponse;
import core.quanly.viewmodel.BhHoaDonChiTietResponse;
import core.quanly.viewmodel.BhHoaDonResponse;
import core.quanly.viewmodel.BhKhachHangResponse;
import domainmodels.HoaDon;
import domainmodels.KhachHang;
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
        hoaDon = banHangRepository.saveOrUpdate(hoaDon);
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
    public List<BhHoaDonResponse> getAllResponseHD() {
        return banHangRepository.getAllResponseHD();
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

}
