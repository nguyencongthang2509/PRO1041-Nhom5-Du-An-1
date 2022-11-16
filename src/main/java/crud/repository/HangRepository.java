/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud.repository;

import config.HibernateUtil;
import domainmodels.Hang;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class HangRepository {

    private String fromTable = "FROM Hang";
    private Session session = HibernateUtil.getSession();

    public List<Hang> getAll() {
        Query query = session.createQuery(fromTable, Hang.class);
        List<Hang> listHang = query.getResultList();
        return listHang;
    }

    public Hang getOne(String ma) {
        String hql = fromTable + "WHERE ma =: ma";
        Query query = session.createSQLQuery(hql);
        query.setParameter("ma", ma);
        Hang hang = (Hang) query.getSingleResult();
        return hang;
    }

    public Boolean add(Hang hang) {
        Transaction trans = null;
        try ( Session session = HibernateUtil.getSession()) {
            trans = session.beginTransaction();
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
            trans = session.beginTransaction();
            session.saveOrUpdate(hang);
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
            trans = session.beginTransaction();
            session.delete(hang);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
