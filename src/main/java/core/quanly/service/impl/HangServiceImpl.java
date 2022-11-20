/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.service.HangService;
import core.quanly.viewmodel.HangViewModel;
import domainmodels.Hang;
import java.util.List;
import repository.impl.HangRepository;

/**
 *
 * @author HP
 */
public class HangServiceImpl implements HangService{
    
    private HangRepository hangRepository ;
    
    public HangServiceImpl(){
        hangRepository = new HangRepository();
    }

    @Override
    public List<HangViewModel> getAllViewModel() {
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
