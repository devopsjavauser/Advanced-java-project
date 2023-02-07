package com.ty.studentapp.util;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Scanner;

import com.ty.studentapp.dao.StudentDatabaseOperation;
import com.ty.studentapp.dto.Student;

public class MainApp {
	static Scanner scan=new Scanner (System.in);
	public static void main(String[] args) {
		StudentDatabaseOperation operation=new StudentDatabaseOperation();
		
		while(true) {
	    System.out.println("\t_____________________");
	    System.out.println("\t|     Welcome       |");
		System.out.println("\t|-------------------|");
		System.out.println("\t|1.Register         |");
		System.out.println("\t|2.login            |");
		System.out.println("\t|3.Forget Password  |");
		System.out.println("\t|4.Exit             |");
		System.out.println("\t|___________________|");
		
		
		System.out.println("\t ENTER YOUR CHOICE");
		 System.out.print("\t\t");
		 
		
		  int choice= scan.nextInt();
		  System.out.println("\t ___________________");
		
		switch (choice) {
		case 1:
			//register
			Student s=new Student();
			System.out.println("\tEnter Student Id");
			System.out.print("\t\t");
			int id=scan.nextInt();
			System.out.println("\tEnter Student Name");
			System.out.print("\t\t");
			String name =scan.next();
			System.out.println("\tEnter Marks");
			System.out.print("\t\t");
			double marks=scan.nextDouble();
			System.out.println("\tEnter Email Id");
			System.out.print("\t\t");
			String mailId=scan.next();
			
		    System.out.println("\tEnter password");
		    System.out.print("\t\t");
			String  password=scan.next();
			s.setId(id);
			s.setName(name);
			s.setMarks(marks);
			s.setEmailId(mailId);
			s.setPassword(password);
			boolean isInserted=operation.insertStudent(s);
			if (isInserted) {
				 System.out.println("Student Registered!!!!");
			}
			else {
			System.out.println("not Registered!!!!");
				
				
			}
			
			break;
      case 2:
    	  //login
    	  System.out.println("\tEnter Email Id");
    	  System.out.print("\t\t");
    	  String email_id=scan.next();
    	  System.out.println("\tEnter your password");
    	  System.out.print("\t\t");
		   String passward=scan.next();
		   Student logedInStudent=operation.loginValidate(email_id,passward);
		   if (logedInStudent==null) {
			   System.out.println("Invalid EmailId/password!!!!");
			
		}
		   else {
			System.out.println("welcome  "+logedInStudent.getName());
			while (true) {
				
			System.out.println(""+(char)3);
			System.out.println("---------------------------------");
			System.out.println("\t1.Display All Student\n\t2.Search_byID \n\t3.update\n\t4.Delete\n\t5.Display based on marks\n\t6.logout");
			System.out.println("-----------------------------------");
			System.out.println("\tEnter your Choice");
			System.out.print("\t\t");
			int subMenuChoice=scan.nextInt();
			if ( subMenuChoice==1) {
				//Display All Student
				ArrayList<Student> allStudents=operation.getAllStudents();
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("ID\t|\tName\t|\tMarks\t|\tEmailId\t\t|");
				System.out.println("-------------------------------------------------------------------------------");
				for (Student s1: allStudents) {
					System.out.println(s1.getId()+"\t|\t"+s1.getName()+"\t|\t"+s1.getMarks()+"\t|\t"+s1.getEmailId());
					
				}
				
			} else if ( subMenuChoice==2){
             //search
				System.out.println("\tEnter student id");
				System.out.print("\t\t");
				int stuid=scan.nextInt();
				Student searchedStudent=operation.getStudent(stuid);
				if (searchedStudent==null) {
					System.out.println("No records"+stuid);
					
				}
				else
					System.out.println("ID\t|\tName\t|\tMarks\t|\tEmailId\t\t|");
				System.out.println(searchedStudent.getId()+"\t|\t"+searchedStudent.getName()+"\t|\t"+searchedStudent.getMarks()+"\t|\t"+searchedStudent.getEmailId());
						
			}
			else if ( subMenuChoice==3){
				
				//updates student 
				System.out.println("\tEnter student id to be updated");
				System.out.print("\t\t");
				int sid=scan.nextInt();
				Student stu=operation.getStudent(sid);
				if(stu==null) {
					System.out.println(" Data cannot--be updated because data not fount for id"+stu);
					
				}
				else {
					//update
					System.out.println("-----------------------------");
					System.out.println("a.Name\nb.Marks\nc.Email_id");
					System.out.println("-----------------------------");
					System.out.println("\tEnter your choice");
					System.out.print("\t\t");
					char updatechoice=scan.next().charAt(0);
					if (updatechoice=='a'){
						System.out.println("Enter update Name");
						String  updatedName=scan.next();
						stu.setName(updatedName);
					}else if (updatechoice=='b') {
						System.out.println("Enter update Marks");
						double updatedMarks=scan.nextDouble();
						stu.setMarks(updatedMarks);
						
					}
                    else if (updatechoice=='c') {
                    	System.out.println("\tEnter update Email_id");
                    	System.out.print("\t\t");
						String  updatedEmail_id=scan.next();
						stu.setEmailId(updatedEmail_id);
						
					}
                    else {
                    	System.out.println("\tPlease Enter Correct Option");
                    }
					boolean isupdated=operation.updateStudentData(stu);
					if (isupdated) {
						System.out.println("\tData is  updated");
						
					} else {
						System.out.println("\tData is Not updated");

					}
				}

			}
			else if( subMenuChoice==4) {
				//Delete
				System.out.println("\tEnter the Student id to be delete");
				System.out.print("\t\t");
				int sid=scan.nextInt();
				Student stu=operation.getStudent(sid);
				if(stu==null) {
					System.out.println("Data Cannot be Updated because data not found for id"+sid);
				}else {
					boolean isDeleted =operation.deleteStudent(sid);
					if (isDeleted) {
						System.out.println("\tdata is deleted");
					} else {
                          System.out.println("\tdata is not deleted");
					}
				}

			}
			else if ( subMenuChoice==5){
				//Display based on marks
				System.out.println("\tEnter  lower your Marks");
				System.out.print("\t\t");
				double lowMarks=scan.nextDouble();
				System.out.println("\tEnter  upper your Marks");
				System.out.print("\t\t");
				double upperMarks=scan.nextDouble();
				ArrayList<Student> studentList=operation.getStudentsBasedOnMarks(lowMarks,upperMarks);
				if (studentList.isEmpty()) {
					System.out.println("no records found marks"+lowMarks);
					
				}
				else {
					System.out.println("ID\t|\tName\t|\tMarks\t|\tEmailId\t\t|");
					System.out.println("-------------------------------------------------------------------------------");
					for (Student s1:studentList ) {
						System.out.println(s1.getId()+"\t|\t"+s1.getName()+"\t|\t"+s1.getMarks()+"\t|\t"+s1.getEmailId());
						
					}
					
				}


			}
			
			else if( subMenuChoice==6) {
				//logout
				System.out.println("\tThank you "+logedInStudent.getName());
				break;
			}
			else {
				System.out.println("\tchoice is invalid");
			}

		}
		   }
			break;
       case 3:
    	   //set and forget the password
    	   System.out.println("\tEnter your mail ID");
    	   System.out.print("\t\t");
    	   String mailid=scan.next();
    	   System.out.println("\tEnter new Password");
    	   System.out.print("\t\t");
    	   String newPassword=scan.next();
    	   System.out.println("\tretype new Password");
    	   System.out.print("\t\t");
    	   String retypePassword=scan.next();
    	   while (!newPassword.equals(retypePassword)) {
    		   System.out.println("new password and retyped password is not matching");
    		   System.out.println("\tEnter new Password");
    		   System.out.print("\t\t");
        	   newPassword=scan.next();
        	   System.out.println("\tretype new Password");
        	   System.out.print("\t\t");
                retypePassword=scan.next();
			
		}
    	   boolean isUpdated=operation.updatepassword(mailid,newPassword);
    	   if (isUpdated) {
			System.out.println("your password is updated");
		}else {
			System.out.println("some thing went wrong while updating  password");
		}
	
	    break;
      case 4:
    	  //exit to the student 
    	  System.out.println("***********THANK YOU*************");
    	  System.exit(0);
	
	   break;

		default:
			System.out.println("Invaild choice enter correct option");
			break;
		}
		
	}

}
}
