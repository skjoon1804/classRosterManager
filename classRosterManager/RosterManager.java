//  Copyright © 2016 Oh Jun Kwon. All rights reserved.

public class RosterManager
{
	private Course[] course;
	private int totalCourse;
	
	public void run()
	// run function runs the main loop of the program
	{
		System.out.println("Welcome to Class Roster Manager!\nSelect an action based on the following menu:");
		ClassRosterUI.printMenu();
		
		String cmd = ClassRosterUI.getCommand();
		while(!cmd.equals("q"))
		// the program would loop on unless user prompts input q for quitting the execution
		{
			if(cmd.equals("ac"))
			// user input "ac" for adding a course
			{
				String courseCode = ClassRosterUI.getCourseCode();
				String courseName = ClassRosterUI.getCourseName();
				Course c = new Course(courseName, courseCode);
				try{ 
					this.addCourse(c);
				} catch (CourseLimitException | DuplicateCourseException e){
					
					if(e instanceof CourseLimitException)
						System.out.println("ERROR: Course number reached maximum");
					else if (e instanceof DuplicateCourseException)
						System.out.println("ERROR: Same course already exists");
				}
			}
			
			else if(cmd.equals("as"))
			// user input "as" for adding a student
			{
				try{
					String courseCode = ClassRosterUI.getCourseCode();
					checkCourse(courseCode);
					int studentID = ClassRosterUI.getStudentID();
					String studentLast = ClassRosterUI.getStudentLast();	
					String studentFirst = ClassRosterUI.getStudentFirst();
					Student s = new Student(studentID,studentFirst,studentLast);
					this.addStudent(courseCode, s);		
					
				} catch (StudentLimitException | DuplicateStudentException | CourseNotFoundException e){
					if (e instanceof StudentLimitException)
						System.out.println("ERROR: Course is full");
					else if (e instanceof DuplicateStudentException)
						System.out.println("ERROR: Student already enrolled");
					else if (e instanceof CourseNotFoundException)
						System.out.println("ERROR: Could not find course");
				}
			}
			
			else if(cmd.equals("dc"))
			// user input "dc" for dropping a course
			{
				String courseCode = ClassRosterUI.getCourseCode();
				try{
					this.deleteCourse(courseCode);
				} catch (CourseNotFoundException | EmptyCourseListException e){
					if (e instanceof CourseNotFoundException)
						System.out.println("ERROR: Could not find course");
					else if (e instanceof EmptyCourseListException)
						System.out.println("ERROR: Course list is empty");
				}
			}
			
			else if(cmd.equals("ds"))
			// user input "ds" for dropping a student
			{
				try{
					String courseCode = ClassRosterUI.getCourseCode();
					checkCourse(courseCode);
					int id = ClassRosterUI.getStudentID();
					checkStudent(id,courseCode);
					this.deleteStudent(id, courseCode);
					
				} catch (EmptyStudentListException | StudentNotFoundException | CourseNotFoundException e){
					if (e instanceof EmptyStudentListException)
						System.out.println("ERROR: No student to drop");
					else if (e instanceof StudentNotFoundException)
						System.out.println("ERROR: Student could not be found");
					else if (e instanceof CourseNotFoundException)
						System.out.println("ERROR: Course does not exist");
					
				}
			}
			else if(cmd.equals("p")){
			// user input "p" for printing the current status of the class roster
				this.printRoster();
			}
			ClassRosterUI.printMenu();
			cmd = ClassRosterUI.getCommand();
		}
		
	}
	
	public RosterManager()
	// initializes RosterManager with the total course number to zero and the course array with the size of 10 
	{
		totalCourse = 0;
		course = new Course[10];
	}
		
	public void addCourse(Course c) throws CourseLimitException, DuplicateCourseException
	// adds a course to the course array and might throw either CourseLimitException or DuplicateCourseException
	{
			if(totalCourse == 10){
			// if there are already 10 courses and the user tries to add on more, it throws CourseLimitException since the maximum is already reached
				throw new CourseLimitException();
			}

		     for(int i=0; i< totalCourse; ++i){
		    // if the course user tries to add already exists in the course list, it throws DuplicateCourseException
		        if(course[i].getCourseCode().equals(c.getCourseCode())){
		           throw new DuplicateCourseException();
		        }
		     }
		     course[totalCourse++] = c;
	}
	
	public void addStudent(String courseCode, Student s) throws StudentLimitException, DuplicateStudentException, CourseNotFoundException
	// add a student to the course and might throw StudentLimitException, DuplicateStudentException, or CourseNotFoundException
	{
		int studentAdded=0;

		for(int i = 0; i < totalCourse; ++i)
		{
			if(courseCode.equals(course[i].getCourseCode()))
			{
				try{
					course[i].addStudent(s);	
					
				} catch (StudentLimitException | DuplicateStudentException e){
					if (e instanceof StudentLimitException)
						throw new StudentLimitException();
					else if (e instanceof DuplicateStudentException)
						throw new DuplicateStudentException();
				}			
				studentAdded=1;
			}
		}
		if (studentAdded != 1)
		// if no course was found that matches student's request, it throws CourseNotFoundException
			throw new CourseNotFoundException();
	}
	
	public void deleteCourse(String courseCode) throws CourseNotFoundException, EmptyCourseListException
	// drops a course from the array and might throw CourseNotFoundException or EmptyCourseListException
	{
		int courseDeleted = 0;
		if (totalCourse == 0)
		// if no course exists in the list, it throws EmptyCourseListException
			throw new EmptyCourseListException();
		
		for(int i = 0; i < totalCourse; ++i){
			if(courseCode.equals(course[i].getCourseCode())){	
				for(int j = i; j < totalCourse; ++j){
					course[j] = course[j+1];
					courseDeleted =1;
				}
				totalCourse--;
				break;
			}
		}
		if (courseDeleted != 1)
		// if no course was found to match dropping of course request, it throws deleteCourseException
			throw new CourseNotFoundException();
	}

	public void deleteStudent(int id,String	courseCode) throws EmptyStudentListException, StudentNotFoundException, CourseNotFoundException
	// drops a student from the course array and might throw EmptyStudentListException, StudentNotFoundException, or CourseNotFoundException
	{
		int studentDeleted = 0;
		
		for(int i = 0; i < totalCourse; ++i){
			if(courseCode.equals(course[i].getCourseCode())){

				try{
					course[i].removeStudent(id);
				} catch (EmptyStudentListException | StudentNotFoundException e){
					if (e instanceof EmptyStudentListException)
						throw new EmptyStudentListException();
					else if (e instanceof StudentNotFoundException)
						throw new StudentNotFoundException();
				}
				studentDeleted =1;
			}
		}
		if (studentDeleted !=1)
		// if none of the courses in the list matches what the student requested to drop for, it throws CourseNotFoundException
			throw new CourseNotFoundException();
	}
	
	public void printRoster()
	// when prompted by the user, print to the UI in text format of the current status of what classes are added and who are enrolled in those courses
	{
		System.out.println("********************");
		for(int i = 0; i < totalCourse; ++i){	
			System.out.println(course[i].getCourseCode() + ": " + course[i].getCourseName());
			System.out.println("Enrolled: " + course[i].getEnrollment());
			for(int j = 0; j < course[i].getEnrollment(); ++j)
				System.out.println("\t" + course[i].getAllStudents()[j].getID() + "  |  " 
										+ course[i].getAllStudents()[j].getLastName() + ", "
										+ course[i].getAllStudents()[j].getFirstName());	}
		System.out.println("********************");
	}
	
	private int checkCourse(String courseCode) throws CourseNotFoundException
	// check if the course was found in the array. If the respective courseCode is never found in the list, CourseNotFoundException is thrown right away.
	{
		int found = -1;
		for(int i = 0; i < totalCourse; ++i)
		{
			if(courseCode.equals(course[i].getCourseCode()))
			{
				found = i;
			}
		}
		if(found == -1)
			throw new CourseNotFoundException();
		return found;
	}
	
	private int checkStudent(int studentID, String courseCode) throws StudentNotFoundException
	// check if the student was found in the array. If the respective studentID is never found within the respective courseCode of course list, StudentNotFoundException is thrown promptly
	{
		int found = -1;
		for(int i = 0; i < totalCourse; ++i)
		{
			if(courseCode.equals(course[i].getCourseCode()))
			{
				for(int j = 0; j < course[i].getEnrollment(); ++j)
				{
					if(course[i].getAllStudents()[j].getID() == studentID)
						found = j;
				}
			}
		}
		if(found == -1)
			throw new StudentNotFoundException();
		return found;
	}
	
	public Course[] getAllCourse(){return this.course;} // course array object getter called from junit test
	public int getTotalCourse(){return this.totalCourse;} // current total number of courses getter called from junit test

}
