/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package crud.service;

import domainmodels.SanPham;
import java.util.List;

/**
 *
 * @author HP
 */
public interface SanPhamService {
    
    List<SanPham> getAll();

    SanPham getOne(String ma);

    String add(SanPham sanPham);

    String update(SanPham sanPham);

    String delete(SanPham sanPham);
}
