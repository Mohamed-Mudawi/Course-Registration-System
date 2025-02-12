/*
 * AdminInterface interface made by Mohamed Mudawi - February 5th, 2025
 * This interface is designed to be implemented by the Admin class
 * The interface will have the signature methods used that will be used by the Admin
 */

import java.util.ArrayList;

public interface AdminInterface 
{
	void displayAdminMenu(ArrayList<Course> courses, ArrayList<Student> students); // Displays the admin menu
	
	// Course management methods
	void enterCourseManagement(ArrayList<Course> courses, ArrayList<Student> students); // Display the course management to the user
	void createNewCourse(ArrayList<Course> courses); // Create a new course
	void deleteCourse(Course course, ArrayList<Course> courses); // Delete a course
	void displayCourseInfo(Course course); // Displays information about the given course
	void registerStudent(ArrayList<Student> students); // Register student for a course
	
	// Reports methods
	void enterReports(ArrayList<Course> courses, ArrayList<Student> students); // Displays the reports menu to the user
	void viewAllCourses(ArrayList<Course> courses); // View all courses
	void viewAllFullCourses(ArrayList<Course> courses); // View all full courses
	void writeFullCourses(ArrayList<Course> courses); // Writes to a file all the courses that are full
	void viewStudentInCourse(Course course); // Views the students in a course
	void viewCoursesRegistered(Student student); // Views the courses a student in registered in
	void sortCourses(ArrayList<Course> courses); // Sorts the courses based on the number of students registered
}
