package core.quanly.service;

import core.quanly.viewmodel.ThHoaDonResponse;
import java.util.List;

/**
 *
 * @author thangncph26123
 */
public interface TraHangService {
    
    List<ThHoaDonResponse> getAllResponseHD();
    
    List<ThHoaDonResponse> findHoaDon(String input);
}
