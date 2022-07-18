package com.kh.zoomin.recruit.member;

import java.sql.Date;

import com.kh.zoomin.member.dto.Member;

public class RecruitMember extends Member{

	private int uid;
	private String companyNo;
	private String id;
	private String password;
	private String email;
	private boolean supervisor;
	private Date regDate;
	

	public RecruitMember(int uid, String companyNo, String id, String password, String phone, String email, Boolean supervisor, Date regDate) {
		this(1, uid, companyNo, id, password, email, supervisor, regDate);
	}

	public RecruitMember(int memberType, int uid, String companyNo, String id, String password, String email,
			boolean supervisor, Date regDate) {
		super(1);
		this.uid = uid;
		this.companyNo = companyNo;
		this.id = id;
		this.password = password;
		this.email = email;
		this.supervisor = supervisor;
		this.regDate = regDate;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getid() {
		return id;
	}

	public void setMemberId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isSupervisor() {
		return supervisor;
	}

	public void setSupervisor(boolean supervisor) {
		if(supervisor)
		this.supervisor = supervisor;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "RecruitMember [uid=" + uid + ", companyNo=" + companyNo + ", memberId=" + id + ", password="
				+ password + ", email=" + email + ", supervisor=" + supervisor + ", regDate=" + regDate + "]";
	}


}

