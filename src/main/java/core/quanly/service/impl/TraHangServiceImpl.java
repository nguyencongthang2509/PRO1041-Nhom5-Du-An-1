package core.quanly.service.impl;

import core.quanly.repository.TraHangRepository;
import core.quanly.service.TraHangService;
import core.quanly.viewmodel.ThHoaDonResponse;
import java.util.List;

/**
 *
 * @author thangncph26123
 */
public class TraHangServiceImpl implements TraHangService{

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
    
}
