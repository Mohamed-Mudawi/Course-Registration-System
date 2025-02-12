/*
 * Main class made by Mohamed Mudawi - February 6th, 2025
 * This class is designed to be the class to run the entire course registration system
 * It will use all associated classes and interfaces
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

public class Main 
{
	private static Scanner userInput = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		// Students, courses, and admin array lists that will hold all the students, courses, and admins
		ArrayList<Student> students = new ArrayList<>();
		ArrayList<Course> courses = new ArrayList<>();
		ArrayList<User> admins = new ArrayList<>();
		
		// Adding the one and only admin to the admin list (Can be changed to have more)
		Admin onlyAdmin = new Admin("James","Smith","Admin001","Admin");
		admins.add(onlyAdmin);
		
		// Deserializing the courses, students, and admins array lists
		deserializeCourses(courses);
		deserializeStudents(students);
		
		
		// While loop to allow the user to choose to log into a student or admin account
		while (true)
		{
			int sOrA = studentOrAdmin();
			
			if (sOrA == 0) // Student log in
			{
				try
				{
					Student studentLoggedIn = studentLogIn(students);
					studentLoggedIn.enterStudentMenu(courses);
				}
				catch (Exception e)
				{
					System.out.println("\n\n\n-----------------------------------------------------");
					System.out.println("Student username or password incorrect!");
					System.out.println("-----------------------------------------------------\n\n\n");
				}
			}
			else if (sOrA == 1) // Admin log in
			{
				try
				{
					Admin adminLoggedIn = (Admin) adminLogIn(admins);
					adminLoggedIn.displayAdminMenu(courses, students);
				}
				catch (Exception e)
				{
					System.out.println("\n\n\n-----------------------------------------------------");
					System.out.println("Admin username or password incorrect!");
					System.out.println("-----------------------------------------------------\n\n\n");
				}
			}
			else // End the program
			{
				break;
			}
		}	
		
		System.out.println("\n\n\n-----------------------------------------------------");
		System.out.println("Thank you for using the Course Management System!");
		System.out.println("-----------------------------------------------------\n\n\n");
		
		
		// Serializing the courses and students array lists
		System.out.println("\n\n\n-----------------------------------------------------");
		serializeCourses(courses);
		serializeStudents(students);
		System.out.println("-----------------------------------------------------\n\n\n");
	}
	
	// Method that will allow the user to choose to log into either a student or admin account
	public static int studentOrAdmin()
	{
		System.out.println("\n\n\n-----------------------------------------------------");
		System.out.println("Welcome to the Course Registration System!");
		System.out.println("-----------------------------------------------------\n\n\n");
		
		System.out.print("[TYPE HERE] Log into a STUDENT or ADMIN account (\"Exit\" to exit): ");
		String stuOrAd = userInput.nextLine();
		stuOrAd = stuOrAd.toLowerCase();
		
		if (stuOrAd.equals("student") || stuOrAd.equals("s"))
		{
			return 0;
		}
		else if (stuOrAd.equals("admin") || stuOrAd.equals("a"))
		{
			return 1;
		}
		else
		{
			return 2;
		}
	}
	
	// Method that will allow the user to log into a student account
	public static Student studentLogIn(ArrayList<Student> students)
	{
		System.out.println("\n\n\n-----------------------------------------------------");
		System.out.println("Student Log In");
		System.out.println("-----------------------------------------------------\n\n\n");
		
		System.out.print("[TYPE HERE] Enter your username: ");
		String stuUN = userInput.nextLine();
		System.out.print("[TYPE HERE]  Enter your password: ");
		String stuPW = userInput.nextLine();
		
		// Searching the students list to see if the username and password were correct
		for (int i = 0; i < students.size(); i++)
		{
			Student tempStudent = students.get(i);
			if (tempStudent.getUsername().equals(stuUN) && tempStudent.getPassword().equals(stuPW))
			{
				return tempStudent;
			}
		}
		
		// Username or password was incorrect
		return null;
	}
	
	// Method that will allow the user to log into a admin account
	public static User adminLogIn(ArrayList<User> admins)
	{
		System.out.println("\n\n\n-----------------------------------------------------");
		System.out.println("Admin Log In");
		System.out.println("-----------------------------------------------------\n\n\n");
			
		System.out.print("[TYPE HERE] Enter your username: ");
		String adUN = userInput.nextLine();
		System.out.print("[TYPE HERE] Enter your password: ");
		String adPW = userInput.nextLine();
			
		// Searching the admins list to see if the username and password were correct
		for (int i = 0; i < admins.size(); i++)
		{
			User tempAdmin = admins.get(i);
			if (tempAdmin.getUsername().equals(adUN) && tempAdmin.getPassword().equals(adPW))
			{
				return tempAdmin;
			}
		}
			
		// Username or password were incorrect
		return null;
	}
	
	// Method that will read the courses from a csv file, and add them to the courses array list
	public static void addCoursesFromCSV(ArrayList<Course> courses)
	{
		String fileName = "MyUniversityCourses.csv";
		File coursesFile = new File(fileName);
		
		try
		{
			Scanner coursesReader = new Scanner(coursesFile);
			coursesReader.nextLine(); // Skip the first line since it's just the section names
			while (coursesReader.hasNextLine())
			{
				String[] courseInfo = (coursesReader.nextLine()).split(",");
				String courseName = courseInfo[0];
				String courseID = courseInfo[1];
				int maxStudents = Integer.valueOf(courseInfo[2]);
				String courseInstructor = courseInfo[5];
				int courseSection = Integer.valueOf(courseInfo[6]);
				String courseLocation = courseInfo[7];
				Course currentCourse = new Course(courseName, courseID, maxStudents, courseInstructor, courseSection, courseLocation);
				courses.add(currentCourse);
			}
			coursesReader.close();
		}
		catch (Exception e)
		{
			System.out.println("\n\n\n-----------------------------------------------------");
			System.out.println("An error occurred trying to add courses from \"MyUniversityCourses.csv\"!");
			System.out.println("-----------------------------------------------------\n\n\n");
		}
	}
	
	// Method that will deserialize the courses, and if this is the very firs time the program is running, then it will copy from the "MyUniversityCourses.csv" file
	public static void deserializeCourses(ArrayList<Course> courses)
	{
		String fileName = "courses.ser";
		
		File coursesFile = new File(fileName);
		
		if (!(coursesFile.exists())) // If this is the first time serializing, then add the courses from the .csv file to the courses array list
		{
			addCoursesFromCSV(courses);
		}
		
		else // Otherwise, it's not the first time serializing
		{
			try (FileInputStream fileInput = new FileInputStream(coursesFile); ObjectInputStream objInput = new ObjectInputStream(fileInput))
			{
				courses.clear();
			
				@SuppressWarnings("unchecked")
				ArrayList<Course> serializedCourses = (ArrayList<Course>) objInput.readObject(); 
				courses.addAll(serializedCourses);
			}
			catch (Exception e)
			{
				System.out.println("\n\n\n-----------------------------------------------------");
				System.out.println("An error occurred!");
				System.out.println("-----------------------------------------------------\n\n\n");
			}
		}
		
	}
	
	// Method that will deserialize the students array list
	public static void deserializeStudents(ArrayList<Student> students)
	{
		String fileName = "students.ser";
			
		File studentsFile = new File(fileName);
			
		if (studentsFile.exists()) 
		{
			try (FileInputStream fileInput = new FileInputStream(studentsFile); ObjectInputStream objInput = new ObjectInputStream(fileInput))
			{	
				students.clear();
				
				@SuppressWarnings("unchecked")
				ArrayList<Student> serializedStudents = (ArrayList<Student>) objInput.readObject();
				students.addAll(serializedStudents);
			}
			catch (Exception e)
			{
				System.out.println("\n\n\n-----------------------------------------------------");
				System.out.println("An error occurred!");
				System.out.println("-----------------------------------------------------\n\n\n");
			}
		}
	}
	
	// Method that will serialize the courses array
	public static void serializeCourses(ArrayList<Course> courses)
	{
		String fileName = "courses.ser";
		
		File coursesFile = new File(fileName);
		try (FileOutputStream fileOutput = new FileOutputStream(coursesFile); ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput))
		{	
			objOutput.writeObject(courses);
			System.out.println("Courses serialization successful!");
		}
		catch (Exception e)
		{
			System.out.println("\n\n\n-----------------------------------------------------");
			System.out.println("An error occurred!");
			System.out.println("-----------------------------------------------------\n\n\n");
		}
	}
	
	// Method that will serialize the students array
		public static void serializeStudents(ArrayList<Student> students)
		{
			String fileName = "students.ser";
			
			File studentsFile = new File(fileName);
			try (FileOutputStream fileOutput = new FileOutputStream(studentsFile); ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput))
			{	
				objOutput.writeObject(students);
				System.out.println("Students serialization successful!");
			}
			catch (Exception e)
			{
				System.out.println("\n\n\n-----------------------------------------------------");
				System.out.println("An error occurred!");
				System.out.println("-----------------------------------------------------\n\n\n");
			}
		}
}
