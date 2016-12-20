//  Copyright © 2016 Oh Jun Kwon. All rights reserved.

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RosterManagerTest
{
	// initialize course array object and rostermanager object
	private Course[] course = new Course[10];
	RosterManager rm = new RosterManager();
	@Before
	public void executeBefore(){
		course = new Course[10];
	}

	
	@Test
	// Test for addCourse method and see if the course object was properly added
	public void addCourseTest() throws DuplicateCourseException, CourseLimitException
	{
		course[0] = new Course("Programming in Java", "ICS45J");
		course[1] = new Course("Programming in C++", "ICS45C");
		course[2] = new Course("Programming in Python", "ICS33");
		rm.addCourse(course[0]);
		rm.addCourse(course[1]);
		rm.addCourse(course[2]);
		Assert.assertArrayEquals(course,rm.getAllCourse());
	}
	
	@Test
	// Test for addCourse method and see if the course object was properly added in order
	public void addCourseOrderedTest() throws DuplicateCourseException, CourseLimitException
	{
		course[0] = new Course("Programming in Java", "ICS45J");
		course[1] = new Course("Programming in C++", "ICS45C");
		course[2] = new Course("Programming in Python", "ICS33");
		rm.addCourse(course[0]);
		rm.addCourse(course[1]);
		rm.addCourse(course[2]);
		Assert.assertEquals(rm.getAllCourse()[0],course[0]);
		Assert.assertEquals(rm.getAllCourse()[1],course[1]);
		Assert.assertEquals(rm.getAllCourse()[2],course[2]);
	}
	
	@Test
	// Test for removeCourse method and see if the course object was properly dropped from the array
	public void removeCourseTest() throws DuplicateCourseException, CourseLimitException, CourseNotFoundException, EmptyCourseListException
	{
		course[0] = new Course("Programming in Java", "ICS45J");
		course[1] = new Course("Programming in C++", "ICS45C");
		course[2] = new Course("Programming in Python", "ICS33");
		rm.addCourse(course[0]);
		rm.addCourse(course[1]);
		rm.addCourse(course[2]);
		rm.deleteCourse("ICS45J");
		Assert.assertEquals(2, rm.getTotalCourse());
	}

	@Test(expected=DuplicateCourseException.class)
	// Test for addCourse method and expect for DuplicateCourseException when addCourse is requested twice for the same course
	public void DuplicateCourseExceptionTest() throws CourseLimitException, DuplicateCourseException
	{
		course[0] = new Course("Programming in Java", "ICS45J");
		course[1] = new Course("Programming in C++", "ICS45C");
		course[2] = new Course("Programming in Python", "ICS33");
		rm.addCourse(course[0]);
		rm.addCourse(course[1]);
		rm.addCourse(course[1]);
	}
	
	@Test(expected=CourseLimitException.class)
	// Test for addCourse method and expect for CourseLimitException when addCourse is requested more than 10 times which exceeds the limit of number of courses allowed
	public void CourseLimitExceptionTest() throws CourseLimitException, DuplicateCourseException
	{
		course[0] = new Course("Programming in Java", "ICS45J");
		course[1] = new Course("Programming in C++", "ICS45C");
		course[2] = new Course("Programming in Python", "ICS33");
		course[3] = new Course("Programming in Java", "ICS31");
		course[4] = new Course("Programming in C++", "ICS32");
		course[5] = new Course("Programming in Python", "ICS46");
		course[6] = new Course("Programming in Java", "CS161");
		course[7] = new Course("Programming in C++", "CS171");
		course[8] = new Course("Programming in Python", "CS162");
		course[9] = new Course("Programming in Python", "CS163");
		Course c = new Course("Programming in Python", "CS164");
		rm.addCourse(course[0]);
		rm.addCourse(course[1]);
		rm.addCourse(course[2]);
		rm.addCourse(course[3]);
		rm.addCourse(course[4]);
		rm.addCourse(course[5]);
		rm.addCourse(course[6]);
		rm.addCourse(course[7]);
		rm.addCourse(course[8]);
		rm.addCourse(course[9]);
		rm.addCourse(c);
	}
	
	@Test(expected = EmptyCourseListException.class)
	// Test for deleteCourse method and expect for EmptyCourseListException when deleteCourse is requested when the course list is empty
	public void EmptyCourseListExceptionTest() throws CourseNotFoundException, EmptyCourseListException
	{
		rm.deleteCourse("ics");
	}
	
	@Test(expected = CourseNotFoundException.class)
	// Test for deleteCourse method and expect for CourseNotFoundException when deleteCourse is requested for a course that does not exist
	public void CourseNotFoundExceptionTest() throws CourseNotFoundException, DuplicateCourseException, CourseLimitException, EmptyCourseListException
	{
		Course c = new Course("Programming in Python", "CS164");
		rm.addCourse(c);
		rm.deleteCourse("ICS161");
	}
	
	@Test
	// Test for addStudent method and see if the method properly adds number of students to the course object
	public void addStudentTest() throws StudentLimitException, DuplicateStudentException
	{
		Student s = new Student(80441261,"Quan","Le");
		Student s2 = new Student(12345678,"Quynh","Le");
		Student s3 = new Student(98765432,"Long","Le");
		Course c = new Course("Java","ics45j");
		c.addStudent(s);
		c.addStudent(s2);
		c.addStudent(s3);
	}
	
	@Test
	// Test for removeStudent method and see if the method properly drops respective students that were added before
	public void removeStudentTest() throws StudentLimitException, DuplicateStudentException, EmptyStudentListException, StudentNotFoundException
	{

		Student s1 = new Student(12345678,"Quynh","Le");
		Student s2 = new Student(98765432,"Long","Le");
		
		Course c = new Course("Java","ics45j");
		c.addStudent(s1);
		c.addStudent(s2);
		
		c.removeStudent(12345678);
		c.removeStudent(98765432);
		
		Student[] arrayS = new Student[50];
		Assert.assertArrayEquals(arrayS,c.getAllStudents());
	}
	
	@Test(expected = EmptyStudentListException.class)
	// Test for removeStudent method and expect for EmptyStudentListException when removeStudent is requested for a course that does not exist
	public void EmptyStudentListExceptionTest() throws EmptyStudentListException, StudentNotFoundException
	{
		Course c = new Course("Java","ics45j");
		c.removeStudent(80441261);
	}
	
	@Test(expected = StudentNotFoundException.class)
	// Test for removeStudent method and expect for StudentNotFoundException when removeStudent is requested for a student that has never been added to the course
	public void StudentNotFoundExceptionTest() throws StudentLimitException, DuplicateStudentException, EmptyStudentListException, StudentNotFoundException
	{
		Student s = new Student(80441261,"Quan","Le");
		Student s2 = new Student(12345678,"Quynh","Le");
		Student s3 = new Student(98765432,"Long","Le");
		Course c = new Course("Java","ics45j");
		c.addStudent(s);
		c.addStudent(s2);
		c.addStudent(s3);
		c.removeStudent(80441262);
		c.removeStudent(12345678);
		c.removeStudent(98765432);
	}
	
	@Test(expected = DuplicateStudentException.class)
	// Test for addStudent method and expect for DuplicateStudentException when addStudent is requested twice for the same student to the same course
	public void DuplicateStudentExceptionTest() throws StudentLimitException, DuplicateStudentException, EmptyStudentListException, StudentNotFoundException
	{
		Student s = new Student(80441261,"Quan","Le");
		Student s2 = new Student(12345678,"Quynh","Le");
		
		Course c = new Course("Java","ics45j");
		
		c.addStudent(s);
		c.addStudent(s2);
		c.addStudent(s);
	}
	
	@Test(expected = StudentLimitException.class)
	// Test for addStudent method and expect for StudentLimitException when addStudent is requested more than 50 times which is the maximum number of students that can be added per couse
	public void StudentLimitExceptionTest() throws StudentLimitException, DuplicateStudentException, EmptyStudentListException, StudentNotFoundException
	{
		Course c = new Course("Java", "ICS45J");
		for (int i=0; i<50; ++i){
			Student s = new Student(i+10000000, "Smith", "John");
			c.addStudent(s);
		}
		// course is now full. if one more student is added, it should throw an error
		
		Student s1 = new Student(99999999, "Adam", "Smith");
		c.addStudent(s1);
		
		
		
	}
	
}

