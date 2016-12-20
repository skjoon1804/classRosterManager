//  Copyright © 2016 Oh Jun Kwon. All rights reserved.

public class Course
{
	private String courseName;
	private String courseCode;
	private int enrollment;
	private Student[] studentEnrolled;
	
	public Course()
	// course constructor without any input
	{
		courseName = "default";
		courseCode = "D0000";
		enrollment = 0;
		studentEnrolled = new Student[50];
	}
	public Course(String courseName, String courseCode)
	// course constructor with courseName and courseCode as the inputs
	{
		this.courseName = courseName;
		this.courseCode = courseCode;
		enrollment = 0;
		studentEnrolled = new Student[50];
	}
	public Course(Course c)
	// course constructor with Course object as the only input
	{
		this.courseName = c.courseName;
		this.courseCode = c.courseCode;
		this.enrollment = c.enrollment;
		this.studentEnrolled = c.studentEnrolled;
	}
	
	public void addStudent(Student s) throws StudentLimitException, DuplicateStudentException
	// add student object to the course array and it throws StudentLimiteException or DuplicateStudentException when either error occurs
	{
		if (this.enrollment == 50)
		// if the maximum number of students (50) is reached per course, it throws error
			throw new StudentLimitException();
		
		for (int i=0; i<enrollment; i++){
		// if the student id input is the same as the one already in the course, it throws error
			if (getAllStudents()[i].getID() == s.getID())
				throw new DuplicateStudentException();				
		}
		boolean added = false;
		for(int i = 0; i < enrollment; ++i)
		{
			// compare information of students that are already in the course and one that is just added
			int comp = s.getLastName().compareTo(studentEnrolled[i].getLastName());
			// if the last name of students are the same
			if(comp == 0)
			{
				if(s.getID() < studentEnrolled[i].getID())
				// compare student ID number and set the smaller one to come first in the array
				{
					for(int j = enrollment; j > i; --j)
					{
						studentEnrolled[j] = studentEnrolled[j-1];
					}
					studentEnrolled[i] = s;
					added = true;
				}
				else
				{
					for(int j = enrollment; j > i+1; --j)
						studentEnrolled[j] = studentEnrolled[j-1];
					studentEnrolled[i+1] = s;
				}
				break;
			}
			else if(comp < 0)
			// if the last name of student that was just added comes before the student's in alphabetical order, the student is added before in the array
			{
				for(int j = enrollment; j > i; --j)
				{
					studentEnrolled[j] = studentEnrolled[j-1];
				}
				studentEnrolled[i] = s;
				added = true;
				break;
			}
		}
		// in all other cases, student that was recently added comes in the last order of the array
		if(!added)
			studentEnrolled[enrollment] = s;
		enrollment++;
	}
	
	
	public void removeStudent(int studentId) throws	EmptyStudentListException, StudentNotFoundException
	// remove student object from the course array and it throws EmptyStudentListException or StudentNotFoundException when either error occurs
	{
		
		
		if (this.enrollment == 0)
		// if there is no student in the class and the function is called to remove a student, it throws EmptyStudentListException
			throw new EmptyStudentListException();
		boolean removed = false;
		for(int i = 0; i < this.enrollment; ++i){
		// go through a loop to look through all students in the class. If a student Id number matches the input, the student is removed.
			if(studentId == studentEnrolled[i].getID()){
				
				for(int j = i; j < this.enrollment;++j){
					studentEnrolled[j] = studentEnrolled[j+1];
				}
				removed = true;
					
				enrollment--;
				break;
			}
		}
		
		if (!removed)
		// if no student matches with the record of students in the class, it throws StudentNotFoundException
			throw new StudentNotFoundException();
		
	}

	public String getCourseName(){return this.courseName;} // CourseName getter called from RosterManager
	public String getCourseCode(){return this.courseCode;} // CourseCode getter called from RosterManager
	public int getEnrollment(){return this.enrollment;} // Enrollment number getter called from RosterManager
	public Student[] getAllStudents(){return this.studentEnrolled;} // Student array object getter called from RosterManager
	
}
