package edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import edu.dto.CustomerDto;
import edu.dto.EmployeeDto;
import edu.util.DButil;

public class CustomerDao {
	public boolean insertCustomer(CustomerDto cusDto){
		boolean status= false;
		try {
		Connection con = DButil.connect();
		String q ="insert into Customer(accno,cname,email,balance) Values(?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(q); 
		ps.setInt(1,cusDto.getAccno());
		ps.setString(2,cusDto.getCname());
		ps.setString(3,cusDto.getEmail());
		ps.setInt(4,cusDto.getBalance());
		ps.executeUpdate();
		status =true;
		}catch (Exception e)
		{
			System.out.println("CustomerDao.insertCustomer():" + e);
		}
		return status;
	}
	public boolean updateCustomer(CustomerDto cusd) {
		boolean flag = false;
		try {
		Connection con = DButil.connect();
      String q = "update Customer set cname = ?, email =?,balance = ? where accno = ?";
      PreparedStatement ps = con.prepareStatement(q);
      ps.setString(1,cusd.getCname());
      ps.setString(2,cusd.getEmail());
      ps.setInt(3,cusd.getBalance());
      ps.setInt(4,cusd.getAccno());
      if(ps.executeUpdate()>0) {
    	  flag=true;
      }
		}
		catch(Exception exe)
		{
			System.out.println("CustomerDao.updateCustomer():" + exe);
		}
		return flag;
	}
	public boolean deleteCustomer(int accno){
			boolean status= false;
			try {
			Connection con = DButil.connect();
			String q ="Delete from Customer where accno = ? ";
			PreparedStatement ps = con.prepareStatement(q); 
			ps.setInt(1,accno);
			ps.executeUpdate();
			status =true;
			}catch (Exception e)
			{
				System.out.println("CustomerDao.DeleteCustomer():" + e);
			}
			return status;
	}
	public CustomerDto selectCustomer(int accno){
		CustomerDto c = null;
		try {
			Connection con = DButil.connect();
			String q ="Select * from Customer where accno = ?";
			PreparedStatement ps = con.prepareStatement(q);
			ps.setInt(1,accno);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				c = new CustomerDto();
				c.setAccno(rs.getInt(1));
				c.setCname(rs.getString(2));
				c.setEmail(rs.getString(3));
				c.setBalance(rs.getInt(4));
			}
		}
		catch(Exception e){
			System.out.println("CustomerDao.SelectCustomer() : "+ e);
		}
		return c;
	}
	public ArrayList<CustomerDto> selectallCustomer(){
		ArrayList<CustomerDto> cuslist = new ArrayList<CustomerDto>();	
		CustomerDto c = null;
		try {
		Connection con = DButil.connect();
		String q ="Select accno,cname,email,balance from Customer";
		PreparedStatement ps = con.prepareStatement(q);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			c = new CustomerDto();
			c.setAccno(rs.getInt(1));
			c.setCname(rs.getString(2));
			c.setEmail(rs.getString(3));
			c.setBalance(rs.getInt(4));
			cuslist.add(c);
		}
	}
	catch(Exception exe){
		System.out.println("CustomerDao.SelectAllEmployee() : "+ exe);
	}
	return cuslist;
	}
}
