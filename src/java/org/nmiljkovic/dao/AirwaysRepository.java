/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmiljkovic.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.nmiljkovic.models.Airways;
import org.nmiljkovic.site.HibernateUtil;

/**
 *
 * @author milja
 */
public class AirwaysRepository {
    Session session = null;
    
    public AirwaysRepository() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
    
    public List<Airways> getAirways() {
        List<Airways> airwaysList = null;
        try {
            org.hibernate.Transaction tran = session.beginTransaction();
            Query q = session.createQuery("from Airways");
            airwaysList = (List<Airways>)q.list();
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airwaysList;
    }
}
