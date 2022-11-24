/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.repository.SPKichThuocRepository;
import core.quanly.service.SPKichThuocService;
import core.quanly.viewmodel.SPKichThuocResponse;
import domainmodels.KichThuoc;
import java.util.List;

/**
 *
 * @author HP
 */
public class SPKichThuocServiceImpl implements SPKichThuocService{
    
    private SPKichThuocRepository spKichThuocRepo = new SPKichThuocRepository();

    @Override
    public List<SPKichThuocResponse> getAllViewModel() {
        return spKichThuocRepo.getAllResponse();
    }

    @Override
    public KichThuoc getOne(String ma) {
        return spKichThuocRepo.findById(ma);
    }

    @Override
    public String add(KichThuoc kichThuoc) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String update(KichThuoc kichThuoc) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<KichThuoc> getAll() {
        return spKichThuocRepo.getAll();
    }
}
