package com.ty.studentapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ty.studentapp.dto.Student;

public class StudentDatabaseOperation {
	private static final String DRIVERCLASS="com.mysql.cj.jdbc.Driver";
	private static final String DBURL="jdbc:mysql://localhost:3306/yuvaraj_db?user=root&password=root";
	
	public boolean insertStudent(Student stu) {
		String query="INSERT INTO student_table VALUES(?,?,?,?,?)";
		Connection con=null;
		PreparedStatement pstmt=null;
	    int rowAffected=0;
	 try {
		Class.forName(DRIVERCLASS);
		con=DriverManager.getConnection( DBURL);
	    pstmt=con.prepareStatement(query);
		pstmt.setInt(1,stu.getId());
		pstmt.setString(2,stu.getName());
		pstmt.setDouble(3,stu.getMarks());
        pstmt.setString(4,stu.getEmailId());
        pstmt.setString(5,stu.getPassword());
         rowAffected=pstmt.executeUpdate();
	 } catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 finally {
		 if (con!=null) {
			 try {
				 con.close();
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		 if (pstmt!=null) {
			 try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
	}
	 if (rowAffected!=0) {
		 return true;
		
	} else {
		return false;
	}
		

}
	public Student loginValidate(String email_id, String passward) {
		// TODO Auto-generated method stub
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Student s=new Student();
		String query="SELECT * FROM student_table WHERE email_Id=? AND passward=?";
		
		
		try {
			Class.forName(DRIVERCLASS);
			con=DriverManager.getConnection(DBURL);
			
			pstmt=con.prepareStatement(query);
			
			pstmt.setString(1, email_id);
			pstmt.setString(2, passward);
			rs=pstmt.executeQuery();
			if (rs.next()) {
				
				
				int id=rs.getInt(1);
				String name=rs.getString(2);
				double marks=rs.getDouble(3);
				String email_Id=rs.getString(4);
				String pswd=rs.getString(5);
				s.setId(id);
				s.setName(name);
				s.setMarks(marks);
				s.setEmailId(email_Id);
				s.setPassword(pswd);
				return s;
				
			}else {
				return null;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
					
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			if (pstmt!=null) {
				try {
					pstmt.close();
					
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				
			}
			if (rs!=null) {
				try {
					rs.close();
					
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				
			}
			
			
		}
		return null;
		
		
			
		}
	public ArrayList<Student> getAllStudents() {
		//SELECT *FROM student_table
	ArrayList<Student>allStudentslist=new ArrayList<>();
Connection con=null;
Statement stmt=null;
ResultSet rs=null;
try {
	Class.forName(DRIVERCLASS);
	con=DriverManager.getConnection(DBURL);
	String query="SELECT*FROM student_table";
	stmt=con.createStatement();
	rs=stmt.executeQuery(query);
	while (rs.next()) {
		int id=rs.getInt(1);
		String name=rs.getString(2);
		double marks=rs.getDouble(3);
		String email_Id=rs.getString(4);
		String pswd=rs.getString(5);
		Student s=new Student();
		s.setId(id);
		s.setName(name);
		s.setMarks(marks);
		s.setEmailId(email_Id);
		s.setPassword(pswd);
		allStudentslist.add(s);
		
		
	}
} catch (ClassNotFoundException | SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} finally {
	 if (con!=null) {
		 try {
			 con.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	 if (stmt!=null) {
		 try {
			stmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
}

	return allStudentslist;
	}
	public Student getStudent(int stuid) {
		// TODO Auto-generated method stub
		//select *from student_table WHERE sid=?
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Student s=new Student();
		String query="SELECT * FROM student_table WHERE sid=?";
		
		
		try {
			Class.forName(DRIVERCLASS);
			con=DriverManager.getConnection(DBURL);
			
			pstmt=con.prepareStatement(query);
			
			pstmt.setInt(1, stuid);
		
			rs=pstmt.executeQuery();
			if (rs.next()) {
				
				
				int id=rs.getInt(1);
				String name=rs.getString(2);
				double marks=rs.getDouble(3);
				String email_Id=rs.getString(4);
				String pswd=rs.getString(5);
				s.setId(id);
				s.setName(name);
				s.setMarks(marks);
				s.setEmailId(email_Id);
				s.setPassword(pswd);
				return s;
				
			}else {
				return null;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Student> getStudentsBasedOnMarks(double lowMarks, double upperMarks) {
		ArrayList<Student> Studentsbymarks=new ArrayList<Student>();
		 // TODO Auto-generated method stub
		//SELECT *FROM student_table Where marksBETWEEN? AND ?
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		
		
		try {
			Class.forName(DRIVERCLASS);
			con=DriverManager.getConnection(DBURL);
			String query="SELECT * FROM student_table WHERE marks BETWEEN ? AND ?";
			
			pstmt=con.prepareStatement(query);
			
			pstmt.setDouble(1, lowMarks);
			pstmt.setDouble(2,upperMarks);;
		
			rs=pstmt.executeQuery();
			while (rs.next()) {
				Student s=new Student();
				
				
				int id=rs.getInt(1);
				String name=rs.getString(2);
				double marks=rs.getDouble(3);
				String email_Id=rs.getString(4);
				
				s.setId(id);
				s.setName(name);
				s.setMarks(marks);
				s.setEmailId(email_Id);
				
				
				Studentsbymarks.add(s);
			
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
					
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			if (pstmt!=null) {
				try {
					pstmt.close();
					
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				
			}
			if (rs!=null) {
				try {
					rs.close();
					
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				
			}
			
			
		}
		return Studentsbymarks ;
		
		

		
	}
	public boolean updateStudentData(Student stu) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			Class.forName(DRIVERCLASS);
			con=DriverManager.getConnection(DBURL);
			String query="UPDATE student_table  SET name=?,marks=?,email_id=? WHERE sid=?";
		     pstmt=con.prepareStatement(query);
		     pstmt.setString(1, stu.getName());
		     pstmt.setDouble(2, stu.getMarks());
		     pstmt.setString(3, stu.getEmailId());
		     pstmt.setInt(4, stu.getId());
		     int rowsAffected=pstmt.executeUpdate();
		    //  if(rowsAffected!=0)
		    //	  return true;
		   //   else
		    //	  return false;
		     return rowsAffected!=0;
		      
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return false;
	}
	public boolean updatepassword(String mailid, String newPassword) {
		
		//UPDATE student_table set PASSWARD=? where  email_Id=?;
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			Class.forName(DRIVERCLASS);
			con=DriverManager.getConnection(DBURL);
			String query="UPDATE student_table set Passward=? where  email_Id=?";
		     pstmt=con.prepareStatement(query);
		     pstmt.setString(1,  newPassword);
		     pstmt.setString(2, mailid);
		   
		     int rowsAffected=pstmt.executeUpdate();
		    //  if(rowsAffected!=0)
		    //	  return true;
		   //   else
		    //	  return false;
		     return rowsAffected!=0;
		      
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			 if (con!=null) {
				 try {
					 con.close();
					
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
			 if (pstmt!=null) {
				 try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
			
		}
		
		
		
		
		return false;
	}
	public boolean deleteStudent(int sid) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		
		
		
		
		try {
			Class.forName(DRIVERCLASS);
			con=DriverManager.getConnection(DBURL);
			String query="DELETE FROM student_table WHERE sid=?";
			
			
			pstmt=con.prepareStatement(query);
			
			pstmt.setInt(1, sid);
			
			int rowsAffected=pstmt.executeUpdate();
					
				
		
				return rowsAffected!=0;
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
					
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			if (pstmt!=null) {
				try {
					pstmt.close();
					
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				
			}
			
			
			
		}
	
		
		
			
		
		return false;
	}
}
		
			
		
		
	

