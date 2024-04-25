package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.catalina.User;

import com.entity.user;

public class UserDAO {
	private Connection conn;
	
	public UserDAO(Connection conn ) {
		super();
		
		this.conn = conn;
		
	}
	
	public boolean addUser(user u ) {
		boolean f =  false;
		try {
			String sql = "insert into user(name,qualification,email,password,role) values(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setString(2, u.getQualification());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getPassword());
			ps.setString(5, "user");
			
			int i = ps.executeUpdate();
			if(i==1) {
				f=true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	public user login(String em ,String psw) {
		user u = null;
		
		try {
			String sql = "String * from user where email = ? and password =?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, em);
			ps.setString(2, psw);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				u = new user();
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setQualification(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setPassword(rs.getString(5));
				u.setRole(rs.getString(6));
			}
		}catch(Exception e) {
			
		}
		
		return  u;
	}

	public boolean updateuser(user u) {
		boolean f = false;
		
		try {
			String sql = "update user set name= ?,qualification=?,email=?,password=?,where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setString(2, u.getQualification());
			ps.setString(2, u.getEmail());
			ps.setString(2, u.getPassword());
			ps.setInt(2, u.getId());
			
			int i = ps.executeUpdate();
			if(i==1) {
				f = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return  f;
	}

}
