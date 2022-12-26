/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.quanly.service;


import core.quanly.viewmodel.SPKhuyenMaiResponse;
import domainmodels.KhuyenMai;
import java.util.List;

/**
 *
 * @author HP
 */
public interface SPKhuyenMaiService {
    
    List<SPKhuyenMaiResponse> getAllViewModel();

    KhuyenMai getOne(String ma);

    String add(KhuyenMai khuyenMai);

    String update(KhuyenMai khuyenMai);

    List<KhuyenMai> getAll();

}
