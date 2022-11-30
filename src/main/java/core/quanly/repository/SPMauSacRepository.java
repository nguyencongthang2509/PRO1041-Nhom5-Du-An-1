/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import core.quanly.viewmodel.SPMauSacResponse;
import domainmodels.MauSac;
import java.util.List;
import repository.CrudRepository;

/**
 *
 * @author HP
 */
public class SPMauSacRepository extends CrudRepository<String, MauSac, SPMauSacResponse>{
    
    public SPMauSacRepository() {
        className = MauSac.class.getName();
        res = "new core.quanly.viewmodel.SPMauSacResponse(a.id, a.ma, a.ten)";
    }
    
    public static void main(String[] args) {
        List<MauSac> list = new SPMauSacRepository().getAll();
        System.out.println(list);
    }
}
