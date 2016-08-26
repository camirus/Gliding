/*
 * 
 * 
 */
package com.multimedianetwork.gliding.managers;

import com.multimedianetwork.gliding.dao.MemberTypeDao;
import com.multimedianetwork.gliding.model.MemberType;

/**
 *
 * @author Camelia Rus
 */
public class MemberTypeManager extends AbstractManager<MemberType> {

    public MemberTypeManager() {
        super(MemberType.class);
        dao = new MemberTypeDao();
    }
}
