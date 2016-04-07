package max.phonebook.Ben;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Person implements Serializable{

	private String id;
	private String name;
	private String phonenumber;
	private String email;
	private String address;
	private String sortLetters;  //显示数据拼音的首字母
		

	public Person ()
	{
		
	}
		
	public String getID() {
		return id;
	}
	public void setID(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getSortLetters() {
		return sortLetters;
	}

	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
	
	/*@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id="+id+",name="+name+",phonenumber=";
	}*/
}
