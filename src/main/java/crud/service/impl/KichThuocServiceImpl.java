/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud.service.impl;

import crud.repository.KichThuocRepository;
import crud.service.KichThuocService;
import domainmodels.KichThuoc;
import java.util.List;

/**
 *
 * @author NgocAnh
 */
public class KichThuocServiceImpl implements KichThuocService {

    private KichThuocRepository kichthuocRe = new KichThuocRepository();

    @Override
    public List<KichThuoc> getAll() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return kichthuocRe.getAll();
    }

    @Override
    public String insert(KichThuoc kichthuoc) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean check = kichthuocRe.saveOrUpdate(kichthuoc);
        if (check) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    @Override
    public String update(KichThuoc kichthuoc) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean check = kichthuocRe.saveOrUpdate(kichthuoc);
        if (check) {
            return "Sửa thành công";
        }
        return "Sửa thất bại";
    }

    @Override
    public String delete(KichThuoc kichthuoc) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean check = kichthuocRe.delete(kichthuoc);
        if (check) {
            return "Xoa thành công";
        }
        return "Xoa thất bại";
    }

}
