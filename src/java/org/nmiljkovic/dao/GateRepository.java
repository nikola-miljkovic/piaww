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
import org.nmiljkovic.models.Gate;
import org.nmiljkovic.site.HibernateUtil;

/**
 *
 * @author milja
 */
public class GateRepository {
    Session session = null;
    
    public GateRepository() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public Gate getGate(String airport, int terminal, String gate) {
        Gate gateObj = null;
        try {
            org.hibernate.Transaction tran = session.beginTransaction();
            Query q = session.createQuery("from Gate as gate where gate.airport = '" 
                    + airport + "' and gate.terminal = " 
                    + terminal + " and gate.gate = '" 
                    + gate + "'");
            gateObj = (Gate)q.list().get(0);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gateObj;
    }
}
