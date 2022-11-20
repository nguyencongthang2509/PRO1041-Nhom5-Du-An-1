package core.quanly.service.impl;

import core.quanly.repository.BanHangRepository;
import core.quanly.service.BanHangService;
import core.quanly.viewmodel.BhChiTietSPResponse;
import java.util.List;

/**
 *
 * @author thangncph26123
 */
public class BanHangServiceImpl implements BanHangService{
    
    private BanHangRepository banHangRepository;

    public BanHangServiceImpl() {
        banHangRepository = new BanHangRepository();
    }
    
    @Override
    public List<BhChiTietSPResponse> getAllChiTietSP() {
        return banHangRepository.getAllResponseCTSP();
    }
    
}
