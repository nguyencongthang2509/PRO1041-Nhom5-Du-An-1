/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.quanly.service;

import core.quanly.viewmodel.SPKichThuocResponse;
import domainmodels.KichThuoc;
import java.util.List;

/**
 *
 * @author HP
 */
public interface SPKichThuocService {
    
    List<SPKichThuocResponse> getAllViewModel();

    KichThuoc getOne(String ma);

    String add(KichThuoc kichThuoc);

    String update(KichThuoc kichThuoc);

    List<KichThuoc> getAll();

    int genMaKichThuocTuDong();
}
