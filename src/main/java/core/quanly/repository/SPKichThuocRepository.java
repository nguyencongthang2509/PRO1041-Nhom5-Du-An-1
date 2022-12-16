/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.viewmodel.SPKichThuocResponse;
import domainmodels.KichThuoc;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.query.NativeQuery;
import repository.CrudRepository;

/**
 *
 * @author HP
 */
public class SPKichThuocRepository extends CrudRepository<String, KichThuoc, SPKichThuocResponse>{
    
    public SPKichThuocRepository() {
        className = KichThuoc.class.getName();
        res = "new core.quanly.viewmodel.SPKichThuocResponse(a.id, a.ma, a.ten)";
    }
    
    public List<SPKichThuocResponse> getAllResponseKichThuoc() {
        List<SPKichThuocResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.SPKichThuocResponse(a.id, a.ma, a.ten) FROM KichThuoc a Where a.trangThaiXoa = 0 order by a.createdDate desc";
            org.hibernate.query.Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    
    public int genMaKichThuoc() {
        String maStr = "";
        session = HibernateUtil.getSession();
        try {
            String nativeQuery = "SELECT MAX(CONVERT(INT, SUBSTRING(ma,3,10))) from kich_thuoc";
            NativeQuery query = session.createNativeQuery(nativeQuery);
            if (query.getSingleResult() != null) {
                maStr = query.getSingleResult().toString();
            } else {
                maStr = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (maStr == null) {
            maStr = "0";
            int ma = Integer.parseInt(maStr);
            return ++ma;
        }
        int ma = Integer.parseInt(maStr);
        return ++ma;
    }
    
    public static void main(String[] args) {
        List<KichThuoc> list = new SPKichThuocRepository().getAll();
        System.out.println(list);
    }
    
}
