//  Copyright © 2016 Oh Jun Kwon. All rights reserved.

import java.util.Scanner;
public class ClassRosterUI
{
	static void printMenu()
	// print menu that prompts the user to order what function they want
	
	{
		System.out.println("----------");
		System.out.println("ac: Add Course\n"
			+ "dc: Drop Course\n"
			+ "as: Add Student\n"
			+ "ds: Drop Student\n"
			+ " p: Print ClassRoster\n"
			+ " q: Quit Program");
		System.out.println("----------");
	}
	
	static String getCommand()
	// gets input from the user and it either returns error if it is invalid or passes on if it is valid
	{
		String cmd;
		Scanner in = new Scanner(System.in);
		System.out.print("Enter Command: ");
		cmd = in.nextLine();
		while(!cmd.equals("ac") && !cmd.equals("dc") && !cmd.equals("as") && !cmd.equals("ds") && !cmd.equals("p") && !cmd.equals("q"))
		{
			System.out.println("Invalid command!!!");
			System.out.print("Enter Command: ");
			cmd = in.nextLine();
		}
		
		return cmd;
	}
	
	static String getCourseName()
	// accepts course name from the user and returns the value
	{
		String courseName;
		Scanner in = new Scanner(System.in);
		System.out.print("Enter Course Name: ");
		courseName = in.nextLine();
		
		return courseName;
	}
	
	static String getCourseCode()
	// accepts course code from the user and returns the value
	{
		String courseCode;
		Scanner in = new Scanner(System.in);
		System.out.print("Enter Course Code: ");
		courseCode = in.nextLine();
		
		return courseCode;
	}
	
	static int getStudentID()
	// accepts student id number from the user and returns the value
	{
		int studentID;
		Scanner in = new Scanner(System.in);
		System.out.print("Enter StudentID: ");
		studentID = in.nextInt();
		
		return studentID;
	}
	
	static String getStudentFirst()
	// accepts student's first name from the user and returns the value
	{
		String firstName;
		Scanner in = new Scanner(System.in);
		System.out.print("Enter first Name: ");
		firstName = in.nextLine();
		
		return firstName;
	}
	
	static String getStudentLast()
	// accepts student's last name from the user and returns the value
	{
		String lastName;
		Scanner in = new Scanner(System.in);
		System.out.print("Enter last Name: ");
		lastName = in.nextLine();
		
		return lastName;
	}
}
