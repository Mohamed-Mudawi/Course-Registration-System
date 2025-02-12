/*
 * Prompts class made by Mohamed Mudawi - February 6th, 2025
 * This class is designed to ask the user a prompt
 * The class is mainly meant to help out the Admin and Student classes by doing much of the 
 * data validation when the methods ask the user to enter something
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Prompts 
{
	private static Scanner userInput = new Scanner(System.in);
	
	// Method that asks the admin to enter the reports or course managment menus
	public static char askForReportsOrCM()
	{
		System.out.print("[TYPE HERE] Enter the reports or course management menu? (\"Exit\" to exit): ");
		String rOrC = userInput.nextLine();
		rOrC = rOrC.toLowerCase();
		if (rOrC.equals("course managment") || rOrC.equals("course") || rOrC.equals("c") || rOrC.equals("managment") || rOrC.equals("m"))
		{
			return 'c';
		}
		else if (rOrC.equals("reports") || rOrC.equals("r"))
		{
			return 'r';
		}
		else
		{
			return 'e';
		}
	}
	
	// Method that will allow the user to choose a number between 1-6 
	public static int choose6Options()
	{
		System.out.print("[TYPE HERE] Choose a number 1-6: ");
		int optionChosen = Integer.valueOf(userInput.nextLine());
		
		if (optionChosen == 1)
		{
			return 1;
		}
		else if (optionChosen == 2)
		{
			return 2;
		}
		else if (optionChosen == 3)
		{
			return 3;
		}
		else if (optionChosen == 4)
		{
			return 4;
		}
		else if (optionChosen == 5)
		{
			return 5;
		}
		else
		{
			return 6;
		}
	}
	
	// Method that will allow the user to choose a number between 1-7 
		public static int choose7Options()
		{
			System.out.print("[TYPE HERE] Choose a number 1-7: ");
			int optionChosen = Integer.valueOf(userInput.nextLine());
			
			if (optionChosen == 1)
			{
				return 1;
			}
			else if (optionChosen == 2)
			{
				return 2;
			}
			else if (optionChosen == 3)
			{
				return 3;
			}
			else if (optionChosen == 4)
			{
				return 4;
			}
			else if (optionChosen == 5)
			{
				return 5;
			}
			else if (optionChosen == 6)
			{
				return 6;
			}
			else
			{
				return 7;
			}
		}
	
	// Method that will allow the user to choose a number between 1-3
	public static int choose3Options()
	{
		System.out.print("[TYPE HERE] Choose a number 1-3: ");
		int optionChosen = Integer.valueOf(userInput.nextLine());
			
		if (optionChosen == 1)
		{
			return 1;
		}
		else if (optionChosen == 2)
		{
			return 2;
		}
		else
		{
			return 3;
		}
	}
	
	// Method that will ask a user to find a course, then return that course
	public static Course findCourse(ArrayList<Course> courses)
	{
		System.out.print("[TYPE HERE] Enter the name of the course: ");
		String targetCourseName = userInput.nextLine();
		System.out.print("[TYPE HERE] Enter the section of the course: ");
		int targetSection = Integer.valueOf(userInput.nextLine());
		
		// Searching for the course name and section from the course list
		for (int i = 0; i < courses.size(); i++)
		{
			Course targetCourse = courses.get(i);
			
			if (targetCourse.getCourseName().equals(targetCourseName) && targetCourse.getCourseSection() == targetSection)
			{
				return targetCourse;
			}
		}
		
		// Course name or section that was entered was not present
		return null;
	}
	
	// Method that will ask a user to find a student, then return that student
	public static Student findStudent(ArrayList<Student> students)
	{
		System.out.print("[TYPE HERE] Enter the first name of the student: ");
		String targetFN = userInput.nextLine();
		System.out.print("[TYPE HERE] Enter the last name of the student: ");
		String targetLN = userInput.nextLine();
		System.out.print("[TYPE HERE] Enter the username of the student: ");
		String targetUN= userInput.nextLine();
			
		// Searching for the student first name, last name, and username from the students list
		for (int i = 0; i < students.size(); i++)
		{
			Student targetStudent = students.get(i);
				
			if (targetStudent.getFirstName().equals(targetFN) && targetStudent.getLastName().equals(targetLN) && targetStudent.getUsername().equals(targetUN))
			{
				return targetStudent;
			}
		}
			
		// First name, last name, or username were incorrect
		return null;
	}
}


