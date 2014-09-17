import big.data.*;
import java.util.Scanner;


public class HealthTopics {
 
 static String[] topics;

 public static void main(String[] args) {
  
  //Another "../null.." problem
  DataSource ds = DataSource.connect("http://healthfinder.gov/developer/MyHFSearch.xml?");
  ds.set("api_Key", "ytrkhlethwpvgrjb").set("gender", "male").set("age", "20").load();
  
  ds.printUsageString();
  //topics = ds.fetchStringArray("Topics/Categories");

 }

}
