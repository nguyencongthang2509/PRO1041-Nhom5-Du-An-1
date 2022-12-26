/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service;

import domainmodels.NhanVien;


/**
 *
 * @author thangncph26123
 */
public interface DangNhapService {
    
    NhanVien findNhanVienByEmailAndMatKhau(String email, String matKhau);
}
