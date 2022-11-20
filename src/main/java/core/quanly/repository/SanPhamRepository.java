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
        res = "new core.quanly.viewmodel.SanPhamViewModel(a.id, a.ma, a.ten)";
    }
    
    public List<SanPhamViewModel> findByMaOrTen(String inpput){
        List<SanPhamViewModel> lst = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.SanPhamViewModel(a.id, a.ma, a.ten) FROM SanPham a "
                    + "WHERE a.ma LIKE CONCAT('%',:input,'%') OR a.ten LIKE CONCAT('%',:input,'%')";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
    
    public static void main(String[] args) {
        List<SanPhamViewModel> list = new SanPhamRepository().getAllResponse();
        for (SanPhamViewModel sp : list) {
            System.out.println(sp.toString());
        }
    }
}
