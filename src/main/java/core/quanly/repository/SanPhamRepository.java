/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.viewmodel.SanPhamViewModel;
import domainmodels.SanPham;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transaction;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.CrudRepository;

/**
 *
 * @author HP
 */
public class SanPhamRepository extends CrudRepository<String, SanPham, SanPhamViewModel>{

    public SanPhamRepository(){
        className = SanPham.class.getName();
        res = "new core.quanly.viewmodel.SanPhamViewModel(a.id, a.ma, a.ten, a.hang.ten, a.loai.ten)";
    }
    
    public static void main(String[] args) {
        List<SanPhamViewModel> list = new SanPhamRepository().getAllResponse();
        for (SanPhamViewModel sp : list) {
            System.out.println(sp.toString());
        }
    }
}
