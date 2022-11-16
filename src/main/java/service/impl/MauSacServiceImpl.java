/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainmodels.MauSac;
import java.util.List;
import repository.MausacRepository;
import service.MauSacService;

/**
 *
 * @author NgocAnh
 */
public class MauSacServiceImpl implements MauSacService {

    private MausacRepository mauSacRe = new MausacRepository();

    @Override
    public List<MauSac> getAll() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return mauSacRe.getAll();
    }

    @Override
    public String insert(MauSac mausac) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean check = mauSacRe.saveOrUpdate(mausac);
        if (check) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    @Override
    public String update(MauSac mausac) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean check = mauSacRe.saveOrUpdate(mausac);
        if (check) {
            return "Sửa thành công";
        }
        return "Sửa thất bại";
    }

    @Override
    public String delete(MauSac mausac) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean check = mauSacRe.delete(mausac);
        if (check) {
            return "Xóa thành công";
        }
        return "Xóa thất bại";
    }

}
