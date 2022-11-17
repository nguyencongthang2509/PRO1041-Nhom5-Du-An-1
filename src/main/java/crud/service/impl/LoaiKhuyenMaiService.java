/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud.service.impl;

import crud.repository.LoaiKhuyenMaiRepository;
import domainmodels.LoaiKhuyenMai;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiennvtph26140
 */
public class LoaiKhuyenMaiService implements LoaiKhuyenMaiServiceImpl{
    LoaiKhuyenMaiRepository loaiKhuyenMaiRepository= new LoaiKhuyenMaiRepository();
    @Override
    public List<LoaiKhuyenMai> getlist() {
    return loaiKhuyenMaiRepository.getList();
    }

    @Override
    public String insert(LoaiKhuyenMai loaiKhuyenMai) {
    if(loaiKhuyenMai.getMa().equals("")){
            return "ma khong duoc de trong";
        }
        if(loaiKhuyenMai.getTen().equals("")){
            return "ten khong duoc de trong";
        }
        boolean check = loaiKhuyenMaiRepository.SaveOrUpDate(loaiKhuyenMai) ;
        if(check){
        return "them thanh cong";
        }
        return "them that bai";
    }
    

    @Override
    public String update(LoaiKhuyenMai loaiKhuyenMai) {
    if(loaiKhuyenMai.getMa().equals("")){
            return "ma khong duoc de trong";
        }
        if(loaiKhuyenMai.getTen().equals("")){
            return "ten khong duoc de trong";
        }
        if(loaiKhuyenMaiRepository.FindLoaiKhuyenMaiByMa(loaiKhuyenMai.getMa())!= null){
            return "ma khong duoc trung";
        }
        boolean check = loaiKhuyenMaiRepository.SaveOrUpDate(loaiKhuyenMai) ;
        if(check){
        return "them thanh cong";
        }
        return "them that bai";
    
    }

    @Override
    public String delete(String ma) {
    LoaiKhuyenMai loaiKhuyenMai = loaiKhuyenMaiRepository.FindLoaiKhuyenMaiByMa(ma);
    if(loaiKhuyenMai == null){
        return "loai khuyen mai nay khong ton tai";
    }
    if(loaiKhuyenMaiRepository.Delete(loaiKhuyenMai)){
        return "xoa thanh cong";
    }else{
        return "xoa that bai";
    }
    }
    
    public static void main(String[] args) {
        LoaiKhuyenMaiServiceImpl loaiKhuyenMaiServiceImpl = new LoaiKhuyenMaiService();
        List<LoaiKhuyenMai> list = new ArrayList<>();
        list = loaiKhuyenMaiServiceImpl.getlist();
        for (LoaiKhuyenMai loaiKhuyenMai : list) {
            System.out.println(loaiKhuyenMai);
        }
    }
    
    
}
