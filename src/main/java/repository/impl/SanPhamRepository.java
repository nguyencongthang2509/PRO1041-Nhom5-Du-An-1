/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import core.quanly.viewmodel.SanPhamViewModel;
import domainmodels.SanPham;
import java.util.List;
import java.util.UUID;
import repository.CrudRepository;

/**
 *
 * @author HP
 */
public class SanPhamRepository extends CrudRepository<UUID, SanPham, SanPhamViewModel>{
    
    public SanPhamRepository(){
        className = SanPham.class.getName();
        res = "new viewmodel.SanPhamViewModel(a.id, a.ma, a.ten)";
    }
    
    public static void main(String[] args) {
        List<SanPham> list = new SanPhamRepository().getAll();
        for (SanPham sp : list) {
            System.out.println(sp.toString());
        }
    }
}
