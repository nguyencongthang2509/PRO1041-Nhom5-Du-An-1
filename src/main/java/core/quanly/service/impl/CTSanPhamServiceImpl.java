/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.repository.CTSanPhamRepository;
import core.quanly.service.CTSanPhamService;
import core.quanly.viewmodel.CTSanPhamResponse;
import domainmodels.ChiTietSP;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class CTSanPhamServiceImpl implements CTSanPhamService {

    private CTSanPhamRepository CTSanPhamRepository = new CTSanPhamRepository();

    @Override
    public List<CTSanPhamResponse> getAllViewModel() {
        return CTSanPhamRepository.getAllResponseCTSP();
    }

    @Override
    public ChiTietSP getOne(String ma) {
        return CTSanPhamRepository.findByMa(ma);
    }

    @Override
    public String add(ChiTietSP ctsanPham) {
     
        
        ChiTietSP sanPhamFind = CTSanPhamRepository.findByMa(ctsanPham.getMaChiTietSP());
        if (sanPhamFind != null) {
            return "Mã không được trùng";
        }
        ctsanPham = CTSanPhamRepository.saveOrUpdate(ctsanPham);
        if (ctsanPham != null) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(ChiTietSP ctsanPham) {
        ChiTietSP sanPhamFindById = CTSanPhamRepository.findById(ctsanPham.getId());
        if (sanPhamFindById == null) {
            return "Không tìm thấy sản phẩm";
        }
      
        if (!ctsanPham.getMaChiTietSP().equals(sanPhamFindById.getMaChiTietSP())) {
            ChiTietSP sanPhamFindByMa = CTSanPhamRepository.findByMa(ctsanPham.getMaChiTietSP());
            if (sanPhamFindByMa != null) {
                return "Mã không được trùng";
            } else {
                sanPhamFindById.setMaChiTietSP(ctsanPham.getMaChiTietSP());
            }
        }
        sanPhamFindById.setHang(ctsanPham.getHang());
        sanPhamFindById.setMauSac(ctsanPham.getMauSac());
        sanPhamFindById.setKichThuoc(ctsanPham.getKichThuoc());
        sanPhamFindById.setChatLieu(ctsanPham.getChatLieu());
        sanPhamFindById.setMaVach(ctsanPham.getMaVach());
        sanPhamFindById.setSoLuongTon(ctsanPham.getSoLuongTon());
        sanPhamFindById.setGiaBan(ctsanPham.getGiaBan());
        sanPhamFindById.setSanPham(ctsanPham.getSanPham());
        sanPhamFindById.setMaChiTietSP(ctsanPham.getMaChiTietSP());
        sanPhamFindById.setMoTa(ctsanPham.getMoTa());
        sanPhamFindById.setTrangThaiXoa(ctsanPham.getTrangThaiXoa());
        ctsanPham = CTSanPhamRepository.saveOrUpdate(sanPhamFindById);
        if (ctsanPham != null) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public List<ChiTietSP> getAll() {
        return CTSanPhamRepository.getAll();
    }

    @Override
    public List<CTSanPhamResponse> findByMaOrTen(String input, int TrangThai) {
        return CTSanPhamRepository.findByMaOrTen(input, TrangThai);
    }

    @Override
    public List<CTSanPhamResponse> getFormCTSP(String ma) {
        return CTSanPhamRepository.getFormCTSP(ma);
    }

    @Override
    public List<CTSanPhamResponse> getCbbListHang(String hang) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CTSanPhamResponse> findTrangThai(Integer ma) {
        return CTSanPhamRepository.findTrangThai(ma);
    }

    @Override
    public int genMaCTSPTuDong() {
        return CTSanPhamRepository.genMaCTSP();
    }

    @Override
    public boolean updateTrangThai(int trangThai, String id) {
        return CTSanPhamRepository.updateTrangThai(trangThai, id);
    }

}
