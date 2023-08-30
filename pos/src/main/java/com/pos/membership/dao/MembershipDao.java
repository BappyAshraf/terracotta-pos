package com.pos.membership.dao;

import java.util.List;

import com.pos.membership.model.Membership;

public interface MembershipDao {

	public Membership addMemberSave(Membership membership);
	
	public List<Membership> getMemberList(Membership membership);
	
	public Membership getMemberInfo(Membership membership);
	
	
}