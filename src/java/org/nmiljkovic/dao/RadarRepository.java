/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmiljkovic.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.nmiljkovic.models.Aircraft;
import org.nmiljkovic.models.Airways;
import org.nmiljkovic.models.Gate;
import org.nmiljkovic.models.Radar;
import org.nmiljkovic.site.HibernateUtil;

/**
 *
 * @author milja
 */
public class RadarRepository {
    Session session = null;
    
    public RadarRepository() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public List<Radar> getRadarList(String[] radarNames) {
        List<Radar> radarList = null;
        try {
            org.hibernate.Transaction tran = session.beginTransaction();
            List<String> radarEntries = new ArrayList<>();
            for (String radarName : radarNames) {
                radarEntries.add("radar.name = '" + radarName + "'");
            }
            String queryString = "from Radar as radar where " + String.join(" or ", radarEntries);
            Query q = session.createQuery(queryString);
            radarList = (List<Radar>)q.list();
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return radarList;
    }
}
