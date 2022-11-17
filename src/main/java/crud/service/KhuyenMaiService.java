/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud.service;

import crud.repository.KhuyenMaiRepository;
import crud.service.impl.KhuyenMaiServiceImpl;
import domainmodels.KhuyenMai;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thangncph26123
 */
public class KhuyenMaiService implements KhuyenMaiServiceImpl{
    KhuyenMaiRepository khuyenMaiRepository = new KhuyenMaiRepository();
    @Override
    public List<KhuyenMai> getList() {
    return khuyenMaiRepository.getList();
    }

    @Override
    public String update(KhuyenMai khuyenMai) {
        if(khuyenMai.getMa().equals("")){
            return "ma khong duoc de trong";
        }
        if(khuyenMai.getTen().equals("")){
            return "ten khong duoc de trong";
        }
        if(khuyenMaiRepository.FindKhuyenMaiByMa(khuyenMai.getMa())!= null){
            return "ma khong duoc trung";
        }
        boolean check = khuyenMaiRepository.SaveOrUpDate(khuyenMai) ;
        if(check){
        return "sua thanh cong";
        }
        return "sua that bai";
        
    }

    @Override
    public String insert(KhuyenMai khuyenMai) {
    if(khuyenMai.getMa().equals("")){
            return "ma khong duoc de trong";
        }
        if(khuyenMai.getTen().equals("")){
            return "ten khong duoc de trong";
        }
        boolean check = khuyenMaiRepository.SaveOrUpDate(khuyenMai) ;
        if(check){
        return "them thanh cong";
        }
        return "them that bai";
    }

    @Override
    public String delete(String ma) {
    KhuyenMai khuyenMai = khuyenMaiRepository.FindKhuyenMaiByMa(ma);
        if (khuyenMai == null) {
            return "khuyen mai nay không tồn tại";
        }
        if (khuyenMaiRepository.Delete(khuyenMai)) {
            return "Xóa thành công";
        } else {
            return "Lỗi. Xóa thất bại";
        }
    }
    public static void main(String[] args) {
        KhuyenMaiServiceImpl khuyenMaiServiceImpl = new KhuyenMaiService();
        List<KhuyenMai> list = new ArrayList<>();
        list = khuyenMaiServiceImpl.getList();
        for (KhuyenMai khuyenMai : list) {
            System.out.println(khuyenMai);
        }
    }
    

    
    
}
