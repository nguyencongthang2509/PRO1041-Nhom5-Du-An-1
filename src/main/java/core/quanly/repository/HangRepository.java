/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import domainmodels.Hang;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author HP
 */
public class HangRepository {
    private String fromTable = "FROM hang";
    private Session session = HibernateUtil.getSession();

    public List<Hang> getAll() {
        Query query = session.createQuery(fromTable, Hang.class);
        List<Hang> lstH = query.getResultList();
        return lstH;
    }

    public Hang getOne(String ma) {
        String sql = fromTable + "WHERE ma=: ma";
        Query query = session.createQuery(sql);
        query.setParameter("ma", ma);
        Hang hang = (Hang) query.getSingleResult();
        return hang;
    }

    public Boolean add(Hang hang) {
        Transaction trans = null;
        try ( Session session = HibernateUtil.getSession()) {
            trans = (Transaction) session.beginTransaction();
            session.save(hang);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean update(Hang hang) {
        Transaction trans = null;
        try ( Session session = HibernateUtil.getSession()) {
            trans = (Transaction) session.beginTransaction();
            session.update(hang);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean delete(Hang hang) {
        Transaction trans = null;
        try ( Session session = HibernateUtil.getSession()) {
            trans = (Transaction) session.beginTransaction();
            session.delete(hang);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
