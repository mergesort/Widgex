/*Joseph Fabisevich
 *Michael Knower
 */

import java.util.Arrays;

/**
 *Class to store person arrays for the Organizer class.
 *@author Joseph Fabisevich
 */
class Person
{
	//Fields
	private String lastName, firstName;
	private long phoneNumber;

	/**
	 *Person constructor used to initialize the parameters.
	 *@param lastName the lastName
	 *@param firstName the firstName
	 *@param phoneNumber the phoneNumber
	 */
	public Person(String lastName, String firstName, long phoneNumber)
	{
		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
	}

	//Testy method
	/**
	 *A method to display the contents of a person.
	 */
	public void displayPerson()
	{
		System.out.println(lastName + ", " + firstName + ", " + phoneNumber);
	}


	/**
	 *@return lastName returns lastName.
	 */
	public String getLast()									 {return lastName;}
	/**
	 *@return firstName returns firstName.
	 */
	public String getFirst()								 {return firstName;}
	/**
	 *@return phoneNumber returns phoneNumber.
	 */
	public long getPhone()								   {return phoneNumber;}
}

public class PersonArray
{

	private Person[] a;
	private int nElems;

	/**
	 *Sets the person created to have a certain amount of elements.
	 *@param max Number of elements for the PersonArray to have.
	 */
	public PersonArray(int max)
	{
		a = new Person[max];
		nElems = 0;
	}

	/**
	 *@return nElems returns the number of elements currently in the array.
	 */
	public int getNElems()		 								{return nElems;}

	/**
	 *Adds the element to the Person [].  Adds fields, and then increments the
	 *nElems.
	 *@return a returns the new Person [] with the added element.
	 */
	public Person[] merge(String last, String first, long phoneNumber)
	{
		a[nElems] = new Person(last, first, phoneNumber);
		nElems++;
		return a;
	}

	/**
	 *Changes the elements of the Person [] into a string.
	 */
	public String display()
	{
		String str = "";
		for(int j=0; j<nElems; j++)
		{
			str += a[j].getLast() + ", " + a[j].getFirst() + ", " + a[j].getPhone() + "\n";
		}
		return str;
	}

}

class Test
{
	public static void main(String[] args)
	{
		PersonArray arr;
		arr = new PersonArray(100);

		String [] string = new String [] {"People", "See", "To", "Person", "Request", "Android", "Bot"};


		System.out.println("Original");

		for(int i = 0; i < string.length; i++)
			System.out.println(string [i]);

		System.out.println("\n\n");
		System.out.println("Sorted java way\n");
   		Arrays.sort(string);

   		for(int i = 0; i < string.length; i++)
   			System.out.println(string [i]);

	}
}