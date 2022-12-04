/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.repository.ThongKeHangHoaRepository;
import core.quanly.service.ThongKeHangHoaService;
import core.quanly.viewmodel.ThongKeHangHoaResponse;
import java.util.List;

/**
 *
 * @author quynhncph26201
 */
public class ThongKeHangHoaServiceImpl implements ThongKeHangHoaService{
    ThongKeHangHoaRepository tkhh = new ThongKeHangHoaRepository();
    @Override
    public List<ThongKeHangHoaResponse> getAllResponse() {
        return tkhh.getListHDCT();
    }
    
    
}
