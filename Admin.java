/*
 * Admin class made by Mohamed Mudawi - February 5th, 2025
 * This class is designed to represent an admin in the course registration system
 * This class will override the methods in the AdminInterface interface
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.io.PrintWriter;
import java.io.File;
import java.io.Serializable;

public class Admin extends User implements AdminInterface, Serializable
{
	private static final long serialVersionUID = 1L;
	private Scanner userInput = new Scanner(System.in);
	
	// Constructor (uses the User class constructor)
	public Admin(String FN, String LN, String PW, String UN)
	{
		super(FN, LN, PW, UN);
	}
	
	// Displays the admin menu, and allows the user to navigate between course management or reports
	public void displayAdminMenu(ArrayList<Course> courses, ArrayList<Student> students)
	{
		System.out.println("\n\n\n-----------------------------------------------------");
		System.out.println("Admin Menu");
		System.out.println("-----------------------------------------------------\n\n\n");
		
		// Asking if the user wants to enter the reports or course management menus
		while (true)
		{
			char reportOrCM = Prompts.askForReportsOrCM(); // MAKE THIS
			if (reportOrCM == 'c') { enterCourseManagement(courses, students); }
			else if (reportOrCM == 'r') { enterReports(courses, students); }
			else if (reportOrCM == 'e') { break; }
		}
	}
	
	// Course management methods
	
	// Display the course management to the user
	public void enterCourseManagement(ArrayList<Course> courses, ArrayList<Student> students)
	{
		System.out.println("\n\n\n-----------------------------------------------------");
		System.out.println("Course Management Menu");
		System.out.println("-----------------------------------------------------\n\n\n");
		
		// Asking the admin which option from 1-6 he wants to do
		while (true)
		{
			System.out.println("\n\n\n-----------------------------------------------------");
			System.out.println("Choose options from below (1-6)");
			System.out.println("1. Create new course");
			System.out.println("2. Delete course");
			System.out.println("3. Edit course");
			System.out.println("4. Display course info");
			System.out.println("5. Register student");
			System.out.println("6. Exit course managment menu");
			System.out.println("-----------------------------------------------------\n\n\n");
			
			int optionChose = Prompts.choose6Options();
			
			if (optionChose == 1) // Create a new course
			{
				createNewCourse(courses);
			}
			else if (optionChose == 2) // Delete a course
			{
				try
				{
					Course courseToDelete = Prompts.findCourse(courses); // MAKE THIS
					deleteCourse(courseToDelete, courses);
				}
				catch (Exception e)
				{
					System.out.println("\n\n\n-----------------------------------------------------");
					System.out.println("Error occurred!");
					System.out.println("-----------------------------------------------------\n\n\n");
				}
			}
			else if (optionChose == 3) // Edit something about a course
			{
				try
				{
					Course courseToEdit = Prompts.findCourse(courses);
					System.out.println("\n\n\n-----------------------------------------------------");
					System.out.println("Choose options from below (1-6)");
					System.out.println("1. Edit maximum amount of students allowed in the course");
					System.out.println("2. Edit the instructor of a course");
					System.out.println("3. Edit the location of a course");
					System.out.println("-----------------------------------------------------\n\n\n");
					int editOptionChose = Prompts.choose3Options(); 
					
					if (editOptionChose == 1) // Edit the maximum amount of students in a course
					{
						System.out.print("[TYPE HERE] Enter a new maximum amount of students: ");
						int newMaxStu = Integer.valueOf(userInput.nextLine());
						courseToEdit.setMaxStudents(newMaxStu);
					}
					else if (editOptionChose == 2) // Edit the instructor of a course
					{
						System.out.print("[TYPE HERE] Enter a new course instructor: ");
						String newCourseIns = userInput.nextLine();
						courseToEdit.setCourseInstructor(newCourseIns);
					}
					else if (editOptionChose == 3) // Edit the location of a course
					{
						System.out.print("[TYPE HERE] Enter a new course location: ");
						String newCourseLoc = userInput.nextLine();
						courseToEdit.setCourseLocation(newCourseLoc);
					}
				}
				catch (Exception e)
				{
					System.out.println("\n\n\n-----------------------------------------------------");
					System.out.println("Error occurred!");
					System.out.println("-----------------------------------------------------\n\n\n");
				}
			}
			else if (optionChose == 4) // Display information about a course
			{
				try
				{
					Course courseToDisplay = Prompts.findCourse(courses);
					displayCourseInfo(courseToDisplay);
				}
				catch (Exception e)
				{
					System.out.println("\n\n\n-----------------------------------------------------");
					System.out.println("Error occurred!");
					System.out.println("-----------------------------------------------------\n\n\n");
				}
			}
			else if (optionChose == 5) // Register a student to the school
			{
				registerStudent(students);
			}
			else if (optionChose == 6) // Return to the admin menu
			{
				break;
			}
		}
	}
	
	// Create a new course
	public void createNewCourse(ArrayList<Course> courses)
	{
		// Asking the user for all the information needed to create a new course
		System.out.print("[TYPE HERE] Enter new course name: ");
		String newCourseName = userInput.nextLine();
		System.out.print("[TYPE HERE] Enter new course ID: ");
		String newCourseID = userInput.nextLine();
		System.out.print("[TYPE HERE] Enter maximum students: ");
		int newMaximumStudents = Integer.valueOf(userInput.nextLine());
		System.out.print("[TYPE HERE] Enter new course instructor: ");
		String newCourseInstructor = userInput.nextLine();
		System.out.print("[TYPE HERE] Enter course section: ");
		int newCourseSection = Integer.valueOf(userInput.nextLine());
		System.out.print("[TYPE HERE] Enter new course location: ");
		String newCourseLocation = userInput.nextLine();
		
		
		// Creating a new object of the course, then adding to the courses list
		courses.add(new Course(newCourseName, newCourseID, newMaximumStudents, newCourseInstructor, newCourseSection, newCourseLocation));
		System.out.println("\n\n\n-----------------------------------------------------");
		System.out.println("New course successfully created!");
		System.out.println("-----------------------------------------------------\n\n\n");
	}
	
	// Delete a course
	public void deleteCourse(Course course, ArrayList<Course> courses)
	{
		System.out.println("\n\n\n-----------------------------------------------------");
		courses.remove(course);
		System.out.println("Course removed successfully!");
		System.out.println("-----------------------------------------------------\n\n\n");
	}
	
	// Displays information about the given course
	public void displayCourseInfo(Course course)
	{
		System.out.println("\n\n\n-----------------------------------------------------");
		System.out.println("Course Name - " + course.getCourseName());
		System.out.println("Course ID - " + course.getCourseID());
		System.out.println("Course Instructor - " + course.getCourseInstructor());
		System.out.println("Course Location - " + course.getCourseLocation());
		System.out.println("Course Section - " + course.getCourseSection());
		System.out.println("Current Students in Course - " + course.getCurrentStudents());
		System.out.println("Maximum Student Allow in Course - " + course.getMaxStudents());
		System.out.println("-----------------------------------------------------\n\n\n");
	}
	
	// Register a student to the school
	public void registerStudent(ArrayList<Student> students)
	{
		System.out.print("[TYPE HERE] Enter the first name of the student: ");
		String tempFirstName = userInput.nextLine();
		System.out.print("[TYPE HERE] Enter the last name of the student: ");
		String tempLastName = userInput.nextLine();
		System.out.print("[TYPE HERE] Enter the username of the student: ");
		String tempUsername = userInput.nextLine();
		System.out.print("[TYPE HERE] Enter the password: ");
		String tempPassword = userInput.nextLine();
		Student newStudent = new Student(tempFirstName, tempLastName, tempPassword, tempUsername);
		
		students.add(newStudent);
		
		
		System.out.println("\n\n\n-----------------------------------------------------");
		System.out.println("Successfully registered new student!");
		System.out.println("-----------------------------------------------------\n\n\n");
	}
	
	// Reports methods
	
	// Displays the reports menu to the user
	public void enterReports(ArrayList<Course> courses, ArrayList<Student> students)
	{
		System.out.println("\n\n\n-----------------------------------------------------");
		System.out.println("Admin Reports Menu");
		System.out.println("-----------------------------------------------------\n\n\n");
		
		// Asking the admin which option from 1-5 he wants to do
		while (true)
		{
			System.out.println("\n\n\n-----------------------------------------------------");
			System.out.println("Choose options from below (1-6)");
			System.out.println("1. View all courses");
			System.out.println("2. View all full courses");
			System.out.println("3. Write to a file all the full courses");
			System.out.println("4. View students in a course");
			System.out.println("5. View courses a student is registered in");
			System.out.println("6. Sort the courses");
			System.out.println("7. Exit reports menu");
			System.out.println("-----------------------------------------------------\n\n\n");
			
			int optionChose = Prompts.choose7Options(); // MAKE THIS
			
			if (optionChose == 1) // View all available courses
			{
				viewAllCourses(courses);
			}
			else if (optionChose == 2) // View all full courses
			{
				viewAllFullCourses(courses);
			}
			else if (optionChose == 3) // Write to a file all the courses that are full
			{
				writeFullCourses(courses);
			}
			else if (optionChose == 4) // View students in a course
			{
				try
				{
					Course chosenCourse = Prompts.findCourse(courses); // MAKE THIS
					viewStudentInCourse(chosenCourse);
				}
				catch (Exception e)
				{
					System.out.println("\n\n\n-----------------------------------------------------");
					System.out.println("Error occurred!");
					System.out.println("-----------------------------------------------------\n\n\n");
				}
			}
			else if (optionChose == 5) // View courses a student is registered in
			{
				try
				{
					Student chosenStudent = Prompts.findStudent(students); // MAKE THIS
					viewCoursesRegistered(chosenStudent);
				}
				catch (Exception e)
				{
					System.out.println("\n\n\n-----------------------------------------------------");
					System.out.println("Error occurred!");
					System.out.println("-----------------------------------------------------\n\n\n");
				}
			}
			else if (optionChose == 6) // Sort the courses
			{
				sortCourses(courses);
			}
			else if (optionChose == 7) // Return to main menu
			{
				break;
			}
		}
	}
	
	// View all courses
	public void viewAllCourses(ArrayList<Course> courses)
	{
		System.out.println("\n\n\n-----------------------------------------------------");
		System.out.println("All available courses - ");
		System.out.println(courses);
		System.out.println("-----------------------------------------------------\n\n\n");
	}
	
	// View all full courses
	public void viewAllFullCourses(ArrayList<Course> courses)
	{
		System.out.println("\n\n\n-----------------------------------------------------");
		System.out.println("All full courses - ");
		
		// Using a for loop to iterate through all the courses in the course list and determine if they're full or not
		System.out.print("[");
		for (int i = 0; i < courses.size(); i++)
		{
			Course currentCourse = courses.get(i);
			if (currentCourse.getMaxStudents() == currentCourse.getCurrentStudents())
			{
				System.out.print(currentCourse);
				if (i != courses.size() - 1)
				{
					System.out.print(", ");
				}
			}
		}
		System.out.println("]");
		
		System.out.println("-----------------------------------------------------\n\n\n");
	}
	
	// Writes to a file all the courses that are full
	public void writeFullCourses(ArrayList<Course> courses)
	{
		try 
		{
			File fullCourseFile = new File("FullCoursesFile.txt");
			PrintWriter writeFile = new PrintWriter(fullCourseFile);
			
			for (int i = 0; i < courses.size(); i++)
			{
				Course currentCourse = courses.get(i);
				if (currentCourse.getCurrentStudents() == currentCourse.getMaxStudents())
				{
					String courseInfo = "Course ID: " + currentCourse.getCourseID() + ", " + "Course name: " + currentCourse.getCourseName() + ", "  + 
					"Course section: " + currentCourse.getCourseSection() + ", " + "Course instructor: " + currentCourse.getCourseInstructor() + ", "
					 + "Course location: " + currentCourse.getCourseLocation();
					writeFile.write(courseInfo);
					writeFile.write("\n");
				}
			}
			
			writeFile.close();
			
			System.out.println("\n\n\n-----------------------------------------------------");
			System.out.println("Successfully wrote to file \"FullCoursesFile.txt\" all the courses that are full!");
			System.out.println("-----------------------------------------------------\n\n\n");
		}
		catch (Exception e)
		{
			System.out.println("\n\n\n-----------------------------------------------------");
			System.out.println("An error occurred!");
			System.out.println("-----------------------------------------------------\n\n\n");
		}
	}
	
	// Views the students in a course
	public void viewStudentInCourse(Course course)
	{
		course.displayStudentsInCourse();
	}
	
	// Views the courses a student in registered in
	public void viewCoursesRegistered(Student student)
	{
		student.viewAllRegisteredCourses();
	}
	
	// Sorts the courses based on the number of students registered
	public void sortCourses(ArrayList<Course> courses)
	{
		Collections.sort(courses);
		
		System.out.println("\n\n\n-----------------------------------------------------");
		System.out.println("Courses sorted successfully!");
		System.out.println("-----------------------------------------------------\n\n\n");
	}
	
	public String toString()
	{
		return getUsername();
	}
}
