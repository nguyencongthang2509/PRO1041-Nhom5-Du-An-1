/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.repository.SPKhuyenMaiRepository;
import core.quanly.service.SPKhuyenMaiService;
import core.quanly.viewmodel.SPKhuyenMaiResponse;
import domainmodels.KhuyenMai;
import java.util.List;

/**
 *
 * @author HP
 */
public class SPKhuyenMaiServiceImpl implements SPKhuyenMaiService{
    
    private SPKhuyenMaiRepository spKhuyenMaiRepo = new SPKhuyenMaiRepository();

    @Override
    public List<SPKhuyenMaiResponse> getAllViewModel() {
        return spKhuyenMaiRepo.getAllResponse();
    }

    @Override
    public KhuyenMai getOne(String ma) {
        return spKhuyenMaiRepo.findById(ma);
    }

    @Override
    public String add(KhuyenMai khuyenMai) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String update(KhuyenMai khuyenMai) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<KhuyenMai> getAll() {
        return spKhuyenMaiRepo.getAll();
    }
    
    
}
