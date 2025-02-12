/*
 * Student class made by Mohamed Mudawi - February 5th, 2025
 * This class is designed to represent a student in the course registration system
 * This class will override the methods in the StudentInterface interface
 */
import java.util.ArrayList;
import java.io.Serializable;

public class Student extends User implements StudentInterface, Serializable
{
	private static final long serialVersionUID = 1L;
	// Course list the student's enrolled in
	ArrayList<Course> studentCourses = new ArrayList<>();
	
	// Constructor (uses the User class constructor)
	public Student(String FN, String LN, String PW, String UN)
	{
		super(FN, LN, PW, UN);
	}
	
	// Enter the student menu
	public void enterStudentMenu(ArrayList<Course> courses)
	{
		System.out.println("\n\n\n-----------------------------------------------------");
		System.out.println("Student Menu");
		System.out.println("-----------------------------------------------------\n\n\n");
		
		// Asking the student which option from 1-6 he wants to do
		while (true)
		{
			System.out.println("\n\n\n-----------------------------------------------------");
			System.out.println("Choose option from below (1-6)");
			System.out.println("1. View all available courses");
			System.out.println("2. View courses that not full");
			System.out.println("3. Register in a course");
			System.out.println("4. Withdraw from a course");
			System.out.println("5. Withdraw from recently registered course");
			System.out.println("6. View all courses registered in");
			System.out.println("7. Return to main menu");
			System.out.println("-----------------------------------------------------\n\n\n");
			
			int optionChose = Prompts.choose7Options();
			
			if (optionChose == 1) // View all available courses
			{
				viewAllCourses(courses);
			}
			else if (optionChose == 2) // View courses that not full
			{
				viewNotFullCourses(courses);
			}
			else if (optionChose == 3) // Register in a course
			{
				try
				{
					Course chosenCourse = Prompts.findCourse(courses); 
					if (chosenCourse == null)
					{
						throw new Exception();
					}
					registerInCourse(chosenCourse);
				}
				catch (Exception e)
				{
					System.out.println("\n\n\n-----------------------------------------------------");
					System.out.println("An error occurred!");
					System.out.println("-----------------------------------------------------\n\n\n");
				}
			}
			else if (optionChose == 4) // Withdraw from a course
			{
				try
				{
					Course chosenCourse = Prompts.findCourse(courses); 
					if (chosenCourse == null)
					{
						throw new Exception();
					}
					withdrawCourse(chosenCourse);
				}
				catch (Exception e)
				{
					System.out.println("\n\n\n-----------------------------------------------------");
					System.out.println("An error occurred!");
					System.out.println("-----------------------------------------------------\n\n\n");
				}
			}
			else if (optionChose == 5) // Withdraw from a course
			{
				try
				{
					withdrawCourse();
				}
				catch (Exception e)
				{
					System.out.println("\n\n\n-----------------------------------------------------");
					System.out.println("An error occurred!");
					System.out.println("-----------------------------------------------------\n\n\n");
				}
			}
			else if (optionChose == 6) // View all courses registered in
			{
				viewAllRegisteredCourses();
			}
			else if (optionChose == 7) // Return to the main menu
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
	
	// View all courses that are not full
	public void viewNotFullCourses(ArrayList<Course> courses)
	{
		System.out.println("\n\n\n-----------------------------------------------------");
		System.out.println("All courses not full - ");
		
		// Using a for loop to iterate through all the courses in the course list and determine if they're full or not
		System.out.print("[");
		for (int i = 0; i < courses.size(); i++)
		{
			Course currentCourse = courses.get(i);
			if (currentCourse.getMaxStudents() != currentCourse.getCurrentStudents())
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
	
	// Register in a course
	public void registerInCourse(Course course)
	{
		course.addStudentToCourse(this);
	}
	
	// Withdraw from a course the student is registered in
	public void withdrawCourse(Course course)
	{
		course.removeStudent(this);
		studentCourses.remove(course);
	}
	
	// Withdraw from the most recent course the student registered in
	public void withdrawCourse()
	{
		try
		{
			Course courseToRemove = studentCourses.get(studentCourses.size() - 1);
			courseToRemove.removeStudent(this);
			studentCourses.remove(courseToRemove);
			
			System.out.println("\n\n\n-----------------------------------------------------");
			System.out.println("Successfully removed from course! ");
			System.out.println("-----------------------------------------------------\n\n\n");
		}		
		catch (Exception e)
		{
			System.out.println("\n\n\n-----------------------------------------------------");
			System.out.println("An error occurred!");
			System.out.println("-----------------------------------------------------\n\n\n");
		}
	}
	
	// View all courses the student is registered in
	public void viewAllRegisteredCourses()
	{
		System.out.println("\n\n\n-----------------------------------------------------");
		System.out.println("Courses " + this.getFirstName() + " " + this.getLastName() + " registered in - ");
		System.out.println(studentCourses);
		System.out.println("-----------------------------------------------------\n\n\n");
	}
	
	// Tostring method
	public String toString()
	{
		return getUsername();
	}
}
