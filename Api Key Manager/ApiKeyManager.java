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
  * Remove a key x
  * get a key x
  * list all keys x
  */

public class ApiKeyManager {


 //List of all ApiKeys
  String temp;
  String[] parts;
  List<ApiKey> keys = new ArrayList<ApiKey>();
  int i = 0;
  ApiKey Atemp;
 
 //automatically reads in the file when new key manager is created.
 ApiKeyManager() throws FileNotFoundException{
  readfile();
 }
 
 //read in all keys and store in an array
 public void readfile() throws FileNotFoundException{
  Scanner scan = new Scanner(new FileReader("keys.txt"));
  while(scan.hasNextLine()){
   temp = scan.nextLine();
   parts = temp.split(",");
   keys.add(new ApiKey(parts[0], parts[1], parts[2]));
   i++;
  }
  scan.close();
 }
 
 //writes to the file keys.txt
 private void writefile() throws IOException{
  FileOutputStream fos = new FileOutputStream("keys.txt");
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
  for(int i = 0; i<keys.size(); i++){
   Atemp = keys.get(i);
   bw.write(Atemp.name + "," + Atemp.method + "," + Atemp.key);
   bw.newLine();
  }
  bw.close();
 }
 
 //adds a given key to the currently stored
 public void addKey(ApiKey key) throws IOException{
  keys.add(key);
  writefile();
 }
 
 //gets a key given its name
 public String getKey(String name){
  String temp = "";
  for(int i = 0; i<keys.size(); i++){
   if(keys.get(i).name.equalsIgnoreCase(name)){
    temp= keys.get(i).key;
   }
  }
  return temp;
 }
 
 //list all the keys currently stored
 public ApiKey[] listKeys(){
  ApiKey[] s = new ApiKey[keys.size()];
  for(int i = 0; i<keys.size(); i++){
   Atemp = keys.get(i);
   s[i] = Atemp;
  }
  return s;
 }
 
 //removes a given key
 public void removeKey(String name) throws IOException{
  for(int i = 0; i<keys.size(); i++){
   Atemp = keys.get(i);
   if(Atemp.name.equalsIgnoreCase(name)){
    keys.remove(i);
    writefile();
    break;
   }
  }
 }
 
}
