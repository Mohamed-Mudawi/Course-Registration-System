/*
 * User class made by Mohamed Mudawi - February 5th, 2025
 * This class is designed to be inherited by the Admin and Student classes
 * The class will hold the methods and variables that will be used both by the Admin and Student
 */
import java.io.Serializable;

public abstract class User implements Serializable
{
	private static final long serialVersionUID = 1L;
	// Setting variables
	private String firstName;
	private String lastName;
	private String password;
	private String username;
	
	// Constructor (user must have a first name, last name, username, and password)
	public User(String FN, String LN, String PW, String UN)
	{
		firstName = FN;
		lastName = LN;
		password = PW;
		username = UN;
	}
	
	// Getters (Methods that will return values of variables)
	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public String getPassword()
	{
		return password;
	}
	public String getUsername()
	{
		return username;
	}
	
	// Setters (Methods that will set the value of variables)
	public void setFirstName(String FN)
	{
		firstName = FN;
	}
	public void setLastName(String LN)
	{
		lastName = LN;
	}
	public void setPassword(String PW)
	{
		password = PW;
	}
	public void setUsername(String UN)
	{
		username = UN;
	}
	
	public abstract String toString();
}
