/*
 * Course class made by Mohamed Mudawi - February 5th, 2025
 * This class is designed to represent a course in the course registration system
 * This class will hold information about a course, and the students enrolled in that course
 * It implements the CourseInterface interface
 */

import java.util.ArrayList;
import java.io.Serializable;

public class Course implements CourseInterface, Comparable<Course>, Serializable
{
	private static final long serialVersionUID = 1L;

	// Student list for students enrolled
	ArrayList<Student> studentsIn = new ArrayList<>();
		
	// Setting variables
	private String courseName;
	private String courseID;
	private int maxStudents;
	private int currentStudents;
	private String courseInstructor;
	private int courseSection;
	private String courseLocation;
	
	// Constructor
	public Course(String CN, String ID, int MS, String CI, int CSection, String CL)
	{
		courseName = CN;
		courseID = ID;
		maxStudents = MS;
		courseInstructor = CI;
		courseSection = CSection;
		courseLocation = CL;
	}
	
	// Getters (Methods that return the value of a variable)
	public String getCourseName()
	{
		return courseName;
	}
	public String getCourseID()
	{
		return courseID;
	}
	public int getMaxStudents()
	{
		return maxStudents;
	}
	public int getCurrentStudents()
	{
		return currentStudents;
	}
	public String getCourseInstructor()
	{
		return courseInstructor;
	}
	public int getCourseSection()
	{
		return courseSection;
	}
	public String getCourseLocation()
	{
		return courseLocation;
	}
	
	// Setters (Methods that set values of variables, course name, id, and section cannot be set)
	public void setMaxStudents(int newMax)
	{
		maxStudents = newMax;
	}
	public void setCourseInstructor(String newInstructor)
	{
		courseInstructor = newInstructor;
	}
	
	public void setCourseLocation(String newLocation)
	{
		courseLocation = newLocation;
	}
	
	// Displays the students in the course
	public void displayStudentsInCourse()
	{
		System.out.println("\n\n\n-----------------------------------------------------");
		System.out.println("Students in " + getCourseName() + " section " + getCourseSection() + " - ");
		System.out.println(studentsIn);
		System.out.println("-----------------------------------------------------\n\n\n");
	}
	
	// Add a student to the course
	public void addStudentToCourse(Student student)
	{
		if (maxStudents > currentStudents)
		{
			studentsIn.add(student);
			currentStudents++;
			student.studentCourses.add(this);
				
			System.out.println("\n\n\n-----------------------------------------------------");
			System.out.println("Student added successfully!");
			System.out.println("-----------------------------------------------------\n\n\n");
		}
		else
		{
			System.out.println("\n\n\n-----------------------------------------------------");
			System.out.println("Maximum class capacity!");
			System.out.println("-----------------------------------------------------\n\n\n");
		}
	}
	
	// Remove a student from the course
	public void removeStudent(Student student)
	{
		studentsIn.remove(student);
		currentStudents--;
			
		System.out.println("\n\n\n-----------------------------------------------------");
		System.out.println("Student removed successfully!");
		System.out.println("-----------------------------------------------------\n\n\n");
		
	}
	
	// Tostring method
	public String toString()
	{
		return getCourseName();
	}
	
	// Compare to method
	public int compareTo(Course other)
	{
		if (this.getCurrentStudents() > other.getCurrentStudents())
		{
			return 1;
		}
		else if (this.getCurrentStudents() < other.getCurrentStudents())
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
}




