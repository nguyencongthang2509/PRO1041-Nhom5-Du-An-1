/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.quanly.service;


import core.quanly.viewmodel.SPHangResponse;
import core.quanly.viewmodel.SanPhamResponse;
import domainmodels.Hang;
import domainmodels.SanPham;
import java.util.List;

/**
 *
 * @author HP
 */
public interface SPHangService {

    List<SPHangResponse> getAllViewModel();
    
    List<SPHangResponse> getAllResponse();

    Hang getOne(String ma);

    String add(Hang hang);

    String update(Hang hang);

    List<Hang> getAll();
    
    int genMaHangTuDong();

}
