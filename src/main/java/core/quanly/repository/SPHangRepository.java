
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;


import config.HibernateUtil;
import core.quanly.viewmodel.SPHangResponse;
import domainmodels.Hang;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import repository.CrudRepository;

/**
 *
 * @author HP
 */
public class SPHangRepository extends CrudRepository<String, Hang, SPHangResponse> {

    public SPHangRepository() {
        className = Hang.class.getName();
        res = "new core.quanly.viewmodel.SPHangResponse(a.id, a.ma, a.ten)";
    }

    public List<SPHangResponse> getAllResponseHang() {
        List<SPHangResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.SPHangResponse(a.id, a.ma, a.ten) FROM Hang a Where a.trangThaiXoa = 0 order by a.createdDate desc";
            org.hibernate.query.Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public int genMaHang() {
        String maStr = "";
        session = HibernateUtil.getSession();
        try {
            String nativeQuery = "SELECT MAX(CONVERT(INT, SUBSTRING(ma,2,10))) from hang";
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
        List<Hang> list = new SPHangRepository().getAll();
        System.out.println(list);
    }
}
