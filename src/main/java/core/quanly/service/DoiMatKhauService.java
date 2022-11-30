/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.quanly.service;

/**
 *
 * @author thiennvtph26140
 */
public interface DoiMatKhauService {
    String GetIdNhanVien(String email, String matkhau);
    
    String DoiMatKhau(String matkhau, String id);
}
