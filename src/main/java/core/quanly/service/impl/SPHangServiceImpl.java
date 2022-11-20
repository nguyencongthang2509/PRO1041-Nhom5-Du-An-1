/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.repository.SPHangRepository;
import core.quanly.service.HangService;
import core.quanly.viewmodel.SPHangViewModel;
import domainmodels.Hang;
import java.util.List;

/**
 *
 * @author HP
 */
public class SPHangServiceImpl implements HangService{
    
    private SPHangRepository hangRepository ;
    
    public SPHangServiceImpl(){
        hangRepository = new SPHangRepository();
    }

    @Override
    public List<SPHangViewModel> getAllViewModel() {
        return hangRepository.getAllResponse();
    }

    @Override
    public Hang getOne(String ma) {
        return hangRepository.findByMa(ma);
    }

    @Override
    public String add(Hang hang) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String update(Hang hang) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Hang> getAll() {
        return hangRepository.getAll();
    }
    
}
