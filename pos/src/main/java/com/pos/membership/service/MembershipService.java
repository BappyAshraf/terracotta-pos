package com.pos.membership.service;

import java.util.List;

import com.pos.membership.model.Membership;

public interface MembershipService {

	public Membership addMemberSave(Membership membership);
	
	public List<Membership> getMemberList(Membership membership);
	
	public Membership getMemberInfo(Membership membership);
	
}