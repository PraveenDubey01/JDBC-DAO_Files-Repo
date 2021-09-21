package edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.dto.EmployeeDto;
import edu.util.DButil;

public class EmployeeDao {
	public boolean insertEmployee(EmployeeDto empdto){
		boolean status= false;
		try {
		Connection con = DButil.connect();
		String q ="insert into Employee(eid,ename,salary,deptno) Values(?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(q); 
		ps.setInt(1,empdto.getEid());
		ps.setString(2,empdto.getEname());
		ps.setDouble(3,empdto.getSalary());
		ps.setString(4,empdto.getDeptno());
		ps.executeUpdate();
		status =true;
		}catch (Exception exe)
		{
			System.out.println("EmployeeDao.insertEmployee():" + exe);
		}
		return status;
	}
	public boolean updateEmployee(EmployeeDto emp) {
		boolean flag = false;
		try {
		Connection con = DButil.connect();
      String q = "update Employee set ename = ?, salary =?,deptno = ? where eid= ?";
      PreparedStatement ps = con.prepareStatement(q);
      ps.setString(1,emp.getEname());
      ps.setDouble(2,emp.getSalary());
      ps.setString(3,emp.getDeptno());
      ps.setInt(4,emp.getEid());
      if(ps.executeUpdate()>0) {
    	  flag=true;
      }
		}
		catch(Exception exe)
		{
			System.out.println("EmployeeDao.updateEmployee():" + exe);
		}
		return flag;
	}
	public boolean deleteEmployee(int eid){
		boolean status= false;
		try {
		Connection con = DButil.connect();
		String q ="Delete from Employee where eid =? ";
		PreparedStatement ps = con.prepareStatement(q); 
		ps.setInt(1,eid);
		ps.executeUpdate();
		status =true;
		}catch (Exception exe)
		{
			System.out.println("EmployeeDao.DeleteEmployee():" + exe);
		}
		return status;
}
	public EmployeeDto selectEmployee(int eid){
		EmployeeDto e = null;
		try {
			Connection con = DButil.connect();
			String q ="Select * from Employee where eid = ?";
			PreparedStatement ps = con.prepareStatement(q);
			ps.setInt(1,eid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				e = new EmployeeDto();
				e.setEid(rs.getInt(1));
				e.setEname(rs.getString(2));
				e.setSalary(rs.getDouble(3));
				e.setDeptno(rs.getString(4));
			}
		}
		catch(Exception exe){
			System.out.println("EmployeeDao.SelectEmployee() : "+ exe);
		}
		return e;
	}
public ArrayList<EmployeeDto> selectallEmployee(){
	ArrayList<EmployeeDto> list = new ArrayList<EmployeeDto>();	
	EmployeeDto e = null;
	try {
	Connection con = DButil.connect();
	String q ="Select eid,ename,salary,deptno from Employee ";
	PreparedStatement ps = con.prepareStatement(q);
	ResultSet rs = ps.executeQuery();
	while(rs.next()){
		e = new EmployeeDto();
		e.setEid(rs.getInt(1));
		e.setEname(rs.getString(2));
		e.setSalary(rs.getDouble(3));
		e.setDeptno(rs.getString(4));
		list.add(e);
	}
}
catch(Exception exe){
	System.out.println("EmployeeDao.SelectEmployee() : "+ exe);
}
return list;
}
}