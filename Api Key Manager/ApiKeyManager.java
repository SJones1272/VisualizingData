import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

 /*
  * ==TASKS==
  * Add a key x
  * Remove a key
  * get a key 
  * list all keys
  */

public class ApiKeyManager {


 //various variables
 static String temp;
 static String[] parts;
 static List<ApiKey> keys = new ArrayList<ApiKey>();
 static int i = 0;
 static ApiKey Atemp;
 
 public static void main(String[] args) throws IOException{
  readfile();
  addKey(new ApiKey("test", "test", "test"));
 }
 
 //read in all keys and store them in a list
 public static void readfile() throws FileNotFoundException{
  Scanner scan = new Scanner(new FileReader("keys.txt"));
  while(scan.hasNextLine()){
   temp = scan.nextLine();
   parts = temp.split(",");
   keys.add(new ApiKey(parts[0], parts[1], parts[2]));
   i++;
   System.out.println(temp);
  }
  scan.close();
 }
 
 //adds a given key to the file
 public static void addKey(ApiKey key) throws IOException{
  FileOutputStream fos = new FileOutputStream("keys.txt");
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
  for(int i = 0; i<keys.size(); i++){
   Atemp = keys.get(i);
   bw.write(Atemp.name + "," + Atemp.method + "," + Atemp.key);
   bw.newLine();
  }
  bw.write(key.name + "," + key.method + "," + key.key);
  bw.close();
  
 }
 
}