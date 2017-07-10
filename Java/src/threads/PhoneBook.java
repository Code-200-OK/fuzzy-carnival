package threads;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;


public class PhoneBook {
	public static void main(String[] args) {
		Person person1 = new Person("jmk", "8743562734");
		Person person2 = new Person("emmt", "3332322343");
		Person person3 = new Person("eclipse", "654345678");
		Phonebookk pb = new Phonebookk();
		pb.addPerson(person1, person2, person3);

		System.out.println(pb.lookUpByName("jmk"));

	}

}


class Phonebookk {

	private ConcurrentHashMap<String, Person> _db = new ConcurrentHashMap<String, Person>();

	public Phonebookk() {

	}

	public Person lookUpByName(String name) {
		for (Entry<String, Person> e : _db.entrySet()) {
			if (e.getValue().getName().equals(name)) {
				return e.getValue();
			}
		}
		return null;
	}

	public Person lookUpByPhoneNumber(String phoneNumber) {
		return _db.get(phoneNumber);
	}

	public synchronized void addPerson(Person person) {
		_db.put(person.getPhoneNumber(), person);
	}

	public synchronized void addPerson(Person... persons) {
		for (Person person : persons) {
			_db.put(person.getPhoneNumber(), person);
		}
	}
}



class Person {
	private String name;
	private String phoneNumber;

	public Person() {
		super();
	}

	public Person(String name, String phoneNumber) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", phoneNumber=" + phoneNumber + "]";
	}

}
