package devops.GraphService.model.implementations;

import java.time.LocalDate;

public class Person implements Comparable<Person> {
	private String nickname;
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;
	private LocalDate dateOfBirth;
	private LocalDate dateOfDeath;
	private String occupation;
	private String description;

	public Person(String nickname,String firstName,String lastName,String address,String phoneNumber,LocalDate dateOfBirth,LocalDate dateOfDeath,String occupation, String description)  {
		this.nickname = nickname;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.dateOfDeath = dateOfDeath;
		this.occupation = occupation;
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

	public String getOccupation() {
		return occupation;
	}

	public LocalDate getDateOfDeath() {
		return dateOfDeath;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getNickname() {
		return nickname;
	}

	@Override
	public int compareTo(Person o) {
		return this.getLastName().compareTo(o.getLastName());
	}
}
