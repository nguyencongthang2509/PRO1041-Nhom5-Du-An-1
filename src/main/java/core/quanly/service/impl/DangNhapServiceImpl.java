package core.quanly.service.impl;

import core.quanly.repository.DangNhapRepository;
import core.quanly.service.DangNhapService;
import domainmodels.NhanVien;



/**
 *
 * @author thangncph26123
 */
public class DangNhapServiceImpl implements DangNhapService{
    
    private DangNhapRepository dangNhapRepository;

    public DangNhapServiceImpl() {
        dangNhapRepository = new DangNhapRepository();
    }
    
    @Override
    public NhanVien findNhanVienByEmailAndMatKhau(String email, String matKhau) {
        return dangNhapRepository.findNhanVienByEmailAndMatKhau(email, matKhau);
    }
    
}
