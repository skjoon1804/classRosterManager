//  Copyright © 2016 Oh Jun Kwon. All rights reserved.

public class Student
{
	private int studentID;
	private String firstName;
	private String lastName;
	
	public Student()
	// student constructor without any input
	{
		studentID = 0;
		firstName = "default";
		lastName = "default";
	}
	
	public Student(int ID, String first, String last)
	// student constructor with three inputs; studentId, student first name, and student last name
	{
		this.studentID = ID;
		this.firstName = first;
		this.lastName = last;
	}
	
	public Student(Student s)
	// student constructor with only student object itself
	{
		this.studentID = s.studentID;
		this.firstName = s.firstName;
		this.lastName = s.lastName;
	}
	
	public int getID(){return this.studentID;} // student ID getter 
	public String getFirstName() {return this.firstName;} // student first name getter
	public String getLastName() {return this.lastName;} // student last name getter
	
	public void setID(int ID) {this.studentID = ID;} // student ID setter 
	public void setFirstName(String first){this.firstName = first;} // student first name setter
	public void setLastName(String last) {this.lastName = last;} // student last name setter
}
