/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.service.ChiTietSanPhamService;
import core.quanly.service.impl.ChiTietSanPhamImpl;
import core.quanly.viewmodel.KMChiTietSanPhamResponse;
import core.quanly.viewmodel.KhuyenMaiResponse;
import domainmodels.ChiTietSP;
import domainmodels.KhuyenMai;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


/**
 *
 * @author thiennvtph26140
 */
public class ChiTietSanPhamRepository extends repository.CrudRepository<String, ChiTietSP, KMChiTietSanPhamResponse >{
    public ChiTietSanPhamRepository(){
    className = ChiTietSP.class.getName();
    res = "NEW core.quanly.viewmodel.KMChiTietSanPhamResponse( a.sanPham.ma, a.sanPham.ten)";
    }
    
    
}
