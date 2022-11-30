/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud.service.impl;

import crud.repository.MauSacRepository;
import crud.service.MauSacService;
import domainmodels.ChiTietSP;
import domainmodels.MauSac;
import java.util.List;

/**
 *
 * @author NgocAnh
 */
public class MauSacServiceImpl implements MauSacService {

    private MauSacRepository mausacRe = new MauSacRepository();

    @Override
    public List<MauSac> getAll() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   return  mausacRe.getAll();
    }

    @Override
    public String insert(MauSac mausac) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean check = mausacRe.saveOrUpdate(mausac);
        ChiTietSP chiTietSP = new ChiTietSP();
        if (check) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    @Override
    public String update(MauSac mausac) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean check = mausacRe.saveOrUpdate(mausac);
        if (check) {
            return "Sửa thành công";
        }
        return "Sửa thất bại";
    }

    @Override
    public String delete(MauSac mausac) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean check = mausacRe.delete(mausac);
        if (check) {
            return "Xoa thành công";
        }
        return "Xoa thất bại";

    }

}
