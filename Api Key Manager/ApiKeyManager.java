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
  * Tasks
  * Add a key x
  * Remove a key
  * get a key x
  * list all keys x
  */

public class ApiKeyManager {


 //Array of all ApiKeys
 static String temp;
 static String[] parts;
 static List<ApiKey> keys = new ArrayList<ApiKey>();
 static int i = 0;
 static ApiKey Atemp;
 
 public static void main(String[] args) throws IOException{
  readfile();
  addKey(new ApiKey("test", "test", "test"));
  System.out.println(getKey("Ebay"));
 }
 
 //read in all keys and store in an array
 public static void readfile() throws FileNotFoundException{
  Scanner scan = new Scanner(new FileReader("keys.txt"));
  while(scan.hasNextLine()){
   temp = scan.nextLine();
   parts = temp.split(",");
   keys.add(new ApiKey(parts[0], parts[1], parts[2]));
   i++;
  }
  scan.close();
 }
 
 //adds a given key to the currently stored
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
 
 //gets a key given its name
 public static String getKey(String name){
  String temp = "";
  for(int i = 0; i<keys.size(); i++){
   if(keys.get(i).name.equalsIgnoreCase(name)){
    temp= keys.get(i).key;
   }
  }
  return temp;
 }
 
 //list all the keys currently stored
 public static void listKeys(){
  for(int i = 0; i<keys.size(); i++){
   Atemp = keys.get(i);
   System.out.println(Atemp.name + "," + Atemp.method + "," + Atemp.key);
  }
 }
 
}