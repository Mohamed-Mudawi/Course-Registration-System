/*
 * StudentInterface interface made by Mohamed Mudawi - February 5th, 2025
 * This interface is designed to be implemented by the Student class
 * The interface will have the signature methods used that will be used by the student
 */
import java.util.ArrayList;

public interface StudentInterface 
{
	void enterStudentMenu(ArrayList<Course> courses); // Enter the student menu
	
	void viewAllCourses(ArrayList<Course> courses); // View all courses
	void viewNotFullCourses(ArrayList<Course> courses); // View all courses that are not full
	void registerInCourse(Course course); // Register in a course
	void withdrawCourse(Course course); // Withdraw from a course the student is registered in
	void viewAllRegisteredCourses(); // View all courses the student is registered in
}
