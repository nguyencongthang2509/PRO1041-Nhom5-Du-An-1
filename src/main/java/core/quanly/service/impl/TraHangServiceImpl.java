package core.quanly.service.impl;

import core.quanly.repository.TraHangRepository;
import core.quanly.service.TraHangService;
import core.quanly.viewmodel.ThHoaDonResponse;
import domainmodels.HoaDon;
import domainmodels.HoaDonTraHang;
import domainmodels.HoaDonTraHangChiTiet;
import java.util.List;

/**
 *
 * @author thangncph26123
 */
public class TraHangServiceImpl implements TraHangService {

    private TraHangRepository traHangRepository;

    public TraHangServiceImpl() {
        traHangRepository = new TraHangRepository();
    }

    @Override
    public List<ThHoaDonResponse> getAllResponseHD() {
        return traHangRepository.getAllResponseHD();
    }

    @Override
    public List<ThHoaDonResponse> findHoaDon(String input) {
        return traHangRepository.findHoaDon(input);
    }

    @Override
    public boolean saveOrUpdateHoaDonTraHang(HoaDonTraHang entity) {
        HoaDonTraHang hoaDonTraHang = traHangRepository.saveOrUpdateHoaDonTraHang(entity);
        if (hoaDonTraHang != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean saveOrUpdateHoaDonTraHangChiTiet(HoaDonTraHangChiTiet entity) {
        HoaDonTraHangChiTiet hoaDonTraHang = traHangRepository.saveOrUpdateHoaDonTraHang(entity);
        if (hoaDonTraHang != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ThHoaDonResponse> getAllResponseHDTraHang() {
        return traHangRepository.getAllResponseHDTraHang();
    }

    @Override
    public HoaDonTraHang findHoaDonTraHang(String idMoi, String idCu) {
        return traHangRepository.findHoaDonTraHang(idMoi, idCu);
    }

    @Override
    public List<HoaDonTraHangChiTiet> getAllHoaDonTraHangChiTiet(String id) {
        return traHangRepository.getAllHoaDonTraHangChiTiet(id);
    }

    @Override
    public List<HoaDon> getAllHoaDonTraHang() {
        return traHangRepository.getAllHoaDonTraHang();
    }

    @Override
    public HoaDon getHoaDonByMa(String ma) {
        return traHangRepository.getHoaDonByMa(ma);
    }

    @Override
    public boolean updateSoLuong(String idHDCT, Integer soLuong) {
        return traHangRepository.updateSoLuong(idHDCT, soLuong);    
    }

    @Override
    public List<HoaDonTraHang> getAllHoaDonTraHangByIdHoaDon(String id) {
        return traHangRepository.getAllHoaDonTraHangByIdHoaDon(id);
    }

    @Override
    public List<HoaDon> findHoaDonById(String input) {
        return traHangRepository.findHoaDonById(input);
    }

}
