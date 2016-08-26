/*
 * 
 * 
 */
package com.multimedianetwork.gliding.managers;

import com.multimedianetwork.gliding.model.Glider;
import com.multimedianetwork.gliding.dao.GliderDao;

/**
 *
 * @author Camelia Rus
 */
public class GliderManager extends AbstractManager<Glider> {

    public GliderManager() {
        super(Glider.class);
        dao = new GliderDao();
    }
}
