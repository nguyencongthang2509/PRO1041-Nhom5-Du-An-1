/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import core.quanly.viewmodel.SPKhuyenMaiResponse;
import domainmodels.KhuyenMai;
import java.util.List;
import repository.CrudRepository;

/**
 *
 * @author HP
 */
public class SPKhuyenMaiRepository extends CrudRepository<String, KhuyenMai, SPKhuyenMaiResponse>{
    public SPKhuyenMaiRepository() {
        className = KhuyenMai.class.getName();
        res = "new core.quanly.viewmodel.SPKhuyenMaiResponse(a.id, a.ma, a.ten)";
    }
    
    public static void main(String[] args) {
        List<KhuyenMai> list = new SPKhuyenMaiRepository().getAll();
        System.out.println(list);
    }
}
