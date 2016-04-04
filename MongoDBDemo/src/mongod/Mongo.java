package mongod;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


public class Mongo {

	public  MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	private DB db = mongoClient.getDB( "maxiao" );
	private  DBCollection coll = db.getCollection("maxiao");
	DBCursor myDoc,myDoc1;
	public  Mongo(){
		
	}
  public boolean mongod(){
	  
	  Set<String> colls = db.getCollectionNames();
	  for (String s:colls) {
	     System.out.println("1--"+s);
	  }
	  
	  
	 myDoc = coll.find();
	while (myDoc.hasNext()) {

		System.out.println(myDoc.next());

		}
	 myDoc1 = coll.find();
	while (myDoc1.hasNext()) {

		DBObject obj=myDoc1.next();
		System.out.println("2====="+obj.get("name"));
		if(obj.get("name").equals("asd"))
		{
			System.out.println("∂¡»°≥…π¶£°");
			return true;
		}

		}
	
	
	return false;
	
  }
}
