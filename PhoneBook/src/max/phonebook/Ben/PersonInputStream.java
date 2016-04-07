package max.phonebook.Ben;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PersonInputStream {

	
	public PersonInputStream() {
		// TODO Auto-generated constructor stub
	}
	 public  void ser(Object object[])throws Exception{   
	        File f=new File("d:"+File.separator+"person.ser");   
	        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(f));   
	        oos.writeObject(object);   
	        oos.close();   
	    } 
	 
	 
	 public  Object[] dser()throws Exception{   
         
	        File f=new File("d:"+File.separator+"person.ser");   
	        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(f));   
	        Object obj[]=(Object []) ois.readObject();   
	        ois.close();
	        return obj;   
	    }  
}
