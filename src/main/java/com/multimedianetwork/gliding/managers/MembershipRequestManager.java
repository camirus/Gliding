/*
 * 
 * 
 */
package com.multimedianetwork.gliding.managers;

import com.multimedianetwork.gliding.model.MembershipRequest;
import com.multimedianetwork.gliding.dao.MembershipRequestDao;

/**
 *
 * @author Camelia Rus
 */
public class MembershipRequestManager extends AbstractManager<MembershipRequest> {

    public MembershipRequestManager() {
        super(MembershipRequest.class);
        dao = new MembershipRequestDao();
    }
}
