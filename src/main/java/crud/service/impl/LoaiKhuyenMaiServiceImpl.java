/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package crud.service.impl;

import domainmodels.LoaiKhuyenMai;
import java.util.List;

/**
 *
 * @author thiennvtph26140
 */
public interface LoaiKhuyenMaiServiceImpl {
    List<LoaiKhuyenMai> getlist();
    
    String insert(LoaiKhuyenMai loaiKhuyenMai);
    
    String update(LoaiKhuyenMai loaiKhuyenMai);
    
    String delete(String ma);
    
}
