/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import core.quanly.viewmodel.SPKichThuocResponse;
import domainmodels.KichThuoc;
import java.util.List;
import repository.CrudRepository;

/**
 *
 * @author HP
 */
public class SPKichThuocRepository extends CrudRepository<String, KichThuoc, SPKichThuocResponse>{
    
    public SPKichThuocRepository(){
        className = KichThuoc.class.getName();
        res = "new core.quanly.viewmodel.SPKichThuocRepository(a.id, a.maKichThuoc, a.tenKichThuoc)";
    }
    
    public static void main(String[] args) {
        List<KichThuoc> lst = new SPKichThuocRepository().getAll();
        System.out.println(lst);   
    }
}
