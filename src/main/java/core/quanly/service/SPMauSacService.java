/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.quanly.service;

import core.quanly.viewmodel.SPMauSacResponse;
import domainmodels.MauSac;
import java.util.List;

/**
 *
 * @author HP
 */
public interface SPMauSacService {
    
    List<SPMauSacResponse> getAllViewModel();
    
    List<SPMauSacResponse> getAllResponse();

    MauSac getOne(String ma);

    String add(MauSac mauSac);

    String update(MauSac mauSac);

    List<MauSac> getAll();

    int genMaMauSacTuDong();
}
