/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmiljkovic.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.nmiljkovic.models.Aircraft;
import org.nmiljkovic.models.Airways;
import org.nmiljkovic.models.Manufacturer;
import org.nmiljkovic.site.HibernateUtil;

/**
 *
 * @author milja
 */
public class ManufactureRepository {
    Session session = null;
    
    public ManufactureRepository() {
        
    }

    public List<Manufacturer> getManufacturers() {
        this.session = HibernateUtil.getSessionFactory().openSession();
        List<Manufacturer> manufacturers = null;
        try {
            org.hibernate.Transaction tran = session.beginTransaction();
            Query q = session.createQuery("from Manufacturer");
            manufacturers = (List<Manufacturer>)q.list();
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return manufacturers;
    }
}
