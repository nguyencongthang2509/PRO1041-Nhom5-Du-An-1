package core.quanly.service;

import core.quanly.viewmodel.ThHoaDonResponse;
import domainmodels.HoaDonTraHang;
import domainmodels.HoaDonTraHangChiTiet;
import java.util.List;

/**
 *
 * @author thangncph26123
 */
public interface TraHangService {
    
    List<ThHoaDonResponse> getAllResponseHD();
    
    HoaDonTraHang findHoaDonTraHang(String id);
    
    List<HoaDonTraHangChiTiet> getAllHoaDonTraHangChiTiet(String id);
    
    List<ThHoaDonResponse> getAllResponseHDTraHang();
    
    List<ThHoaDonResponse> findHoaDon(String input);
    
    boolean saveOrUpdateHoaDonTraHang(HoaDonTraHang entity);
    
    boolean saveOrUpdateHoaDonTraHangChiTiet(HoaDonTraHangChiTiet entity);
}
