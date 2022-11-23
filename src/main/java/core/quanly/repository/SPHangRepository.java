/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.viewmodel.SPHangViewModel;
import domainmodels.Hang;
import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.CrudRepository;

/**
 *
 * @author HP
 */
public class SPHangRepository extends CrudRepository<String, Hang, SPHangViewModel>{
    
     public SPHangRepository() {
        className = Hang.class.getName();
        res = "new core.quanly.viewmodel.SPHangViewModel(a.id, a.ma, a.ten)";
    }
    
    public static void main(String[] args) {
        List<Hang> list = new SPHangRepository().getAll();
        System.out.println(list);
    }
}
