/*
 * CourseInterface interface made by Mohamed Mudawi - February 5th, 2025
 * This interface is designed to be implemented by the Course class
 * The interface will have the signature methods used that will be used by the Course class
 */


public interface CourseInterface 
{
	// Getters and setters for all variables (course name and id cannot be set)
	String getCourseName();
	String getCourseID(); 
	int getMaxStudents();
	int getCurrentStudents();
	String getCourseInstructor();
	int getCourseSection();
	String getCourseLocation();
	
	void setMaxStudents(int newMax);
	void setCourseInstructor(String newInstructor);
	void setCourseLocation(String newLocation);
	
	
	void displayStudentsInCourse(); // Displays the students in the course
	void addStudentToCourse(Student student); // Add a student to the course
	void removeStudent(Student student); // Remove a student from the course
}
