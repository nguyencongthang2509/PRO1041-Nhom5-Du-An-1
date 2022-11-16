/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainmodels.KichThuoc;
import java.util.List;
import repository.KichthuocRepository;
import service.KichThuocService;

/**
 *
 * @author NgocAnh
 */
public class KichThuocServiceImpl implements KichThuocService{
private KichthuocRepository kichThuocRe = new KichthuocRepository();
    @Override
    public List<KichThuoc> getAll() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return kichThuocRe.getAll();
    }

    @Override
    public String insert(KichThuoc kichthuoc) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   boolean check = kichThuocRe.saveOrUpdate(kichthuoc);
        if (check) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    @Override
    public String update(KichThuoc kichthuoc) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   boolean check = kichThuocRe.saveOrUpdate(kichthuoc);
        if (check) {
            return "Sửa thành công";
        }
        return "Sửa thất bại";
    }

    @Override
    public String delete(KichThuoc kichthuoc) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    boolean check = kichThuocRe.delete(kichthuoc);
        if (check) {
            return "Xóa thành công";
        }
        return "Xóa thất bại";
    }
    
}
