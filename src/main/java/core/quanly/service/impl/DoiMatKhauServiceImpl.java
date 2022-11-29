/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.repository.DoiMatKhauRepository;

/**
 *
 * @author thiennvtph26140
 */
public class DoiMatKhauServiceImpl implements core.quanly.service.DoiMatKhauService{
    DoiMatKhauRepository doiMatKhauRepository = new DoiMatKhauRepository();
    @Override
    public String GetIdNhanVien(String email, String matkhau) {
    return doiMatKhauRepository.GetIdNhanVien(email, matkhau);
    }

    @Override
    public String DoiMatKhau(String matkhau, String id) {
    boolean check = doiMatKhauRepository.DoiMatKhau(matkhau, id);
    if(check){
        return "Đổi mật khẩu thành công";
    }else{
        return "Đổi mật khẩu thất bại";
    }
    }
    
}
