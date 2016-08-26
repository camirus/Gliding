/*
 * 
 * 
 */
package com.multimedianetwork.gliding.managers;

import com.multimedianetwork.gliding.model.Member;
import com.multimedianetwork.gliding.dao.MemberDao;
import java.util.List;

/**
 *
 * @author Camelia Rus
 */
public class MemberManager extends AbstractManager<Member> {

    public MemberManager() {
        super(Member.class);
        dao = new MemberDao();
    }

    public List<Member> getOneDayMembers() {
        return ((MemberDao) dao).getOneDayMembers();
    }

    public List<Member> getPermanentMembers() {
        return ((MemberDao) dao).getPermanentMembers();
    }
}
