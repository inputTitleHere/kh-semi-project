package com.kh.zoomin.supervisor.model.dao;
import static com.kh.mvc.common.JdbcTemplate.close;
import static com.kh.zoomin.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.mvc.member.model.exception.MemberException;
import com.kh.zoomin.supervisor.model.service.ApplicantMember;
import com.kh.zoomin.supervisor.model.service.RecruitMember;


public class SupervisorDao {

	private Properties prop = new Properties();
	
	public SupervisorDao() {
		String filename = SupervisorDao.class.getResource("/sql/zoomin/member/member-query.properties").getPath();
		//로그
		System.out.println("filename@SupervisorDao = " + filename);
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private ApplicantMember handleApplicantMemberRset(ResultSet rset) {
		int uid = rset.getInt("uid");
		String name = rset.getString("name");
		String id = rset.getString("id");
		String password = rset.getString("password");
		String phone = rset.getString("phone");
		String email = rset.getString("email");
		Date regDate = rset.getDate("regDate");
		return new ApplicantMember(uid, name, id, password, phone, email, regDate);
	}
	
	
	private RecruitMember handleRecruitMemberRset(ResultSet rset) {
		int uid = rset.getInt("uid");
		String companyNo = rset.getString("companyNo");
		String name = rset.getString("name");
		String id = rset.getString("id");
		String password = rset.getString("password");
		String email = rset.getString("email");
		Date regDate = rset.getDate("regDate");
		return new RecruitMember(uid, companyNo, name, id, password, email, regDate);
	}
	
	
	public List<ApplicantMember> findApplicantMemberAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ApplicantMember> applicantMemberList = new ArrayList<>();
		String sql = prop.getProperty("findApplicantMemberAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ApplicantMember applicantMember = handleApplicantMemberRset(rset);
				applicantMemberList.add(applicantMember);
			}
		} catch (Exception e) {
			throw new Exception("구인자 전체 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return applicantMemberList;
	}

	public List<RecruitMember> findRecruitMemberAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<RecruitMember> recruitMemberList = new ArrayList<>();
		String sql = prop.getProperty("findRecruitMemberAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				RecruitMember recruitMember = handleRecruitMemberRset(rset);
				recruitMemberList.add(recruitMember);
			}
		} catch (Exception e) {
			throw new Exception("구직자 전체 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return recruitMemberList;
	}

	public List<ApplicantMember> findApplicantMemberLike(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ApplicantMember> applicantMemberList = new ArrayList<>();
		String sql = prop.getProperty("findApplicantMemberLike");
		//findApplicantMemberLike = select * from applicantMember where # like ?
		
		//# 컬럼명 처리하기
		String col = (String) param.get("searchType");
		String val = (String) param.get("searchKeyword");
		sql = sql.replace("#", col);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + val + "%");
			rset = pstmt.executeQuery();
			while(rset.next())
				applicantMemberList.add(handleApplicantMemberRset(rset));			
		} catch (SQLException e) {
			throw new Exception("관리자 회원검색 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}				
		return applicantMemberList;
	}

	public List<RecruitMember> findRecruitMemberLike(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<RecruitMember> recruitMemberList = new ArrayList<>();
		String sql = prop.getProperty("findApplicantMemberLike");
		//findRecruitMemberLike = select * from recruitMember where # like ?
		
		//# 컬럼명 처리하기
		String col = (String) param.get("searchType");
		String val = (String) param.get("searchKeyword");
		sql = sql.replace("#", col);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + val + "%");
			rset = pstmt.executeQuery();
			while(rset.next())
				recruitMemberList.add(handleRecruitMemberRset(rset));			
		} catch (SQLException e) {
			throw new Exception("관리자 회사검색 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}				
		return recruitMemberList;
	}

	

	

}
