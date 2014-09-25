import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;


public class ApiKeyManager {
 
  /*
   * Tasks
   * Add a key x
   * Remove a key x
   * get a key x
   * list all keys x
   */
 
 Preferences root;
 List<ApiKey> keys = new ArrayList<ApiKey>();
 ApiKey tempA;
 
 
 public ApiKeyManager() throws BackingStoreException{
  //when called it makes a new node ApiKeys in the user node.
  Preferences.userRoot().node("ApiKeys"); 
  readFile();
 }
 
 public static void main(String[] args) throws BackingStoreException{
  ApiKeyManager apiM = new ApiKeyManager();
  System.out.println(apiM.listKeys());
 }
 
 //reads the preferences file and stores everything in the keys array. 
 public void readFile() throws BackingStoreException{
  keys.removeAll(keys);
  root = Preferences.userRoot().node("ApiKey");
  String[] temp = root.childrenNames();
  System.out.println(temp.length);
  if(temp.length != 0){
   for(int i = 0; i<temp.length; i++){
    System.out.println(temp[i]);
    root = Preferences.userRoot().node("ApiKey/"+temp[i]);
    System.out.println(root.absolutePath());
    String s = root.get("name", "!NAME");
    String a = root.get("method", "!METHOD");
    String y = root.get("key", "!KEY");
    keys.add(new ApiKey(s, a, y));
    }
  }
 }
 
 //adds a given key to a node
 public void addKey(ApiKey key){
  String s = key.name;
  String a = key.method;
  String y = key.key;
  
  //directs us to the given node, if its not there it is created for us.
  root = Preferences.userRoot().node("ApiKey/"+s);
  root.put("name", s);
  root.put("method", a);
  root.put("key", y);
 }
 
 //gets a key and all of its values given its name.
 public String getKey(String name) throws Exception{
  //Determines if the node exists
  if(root.nodeExists("/ApiKey/"+name)){
   root = Preferences.userRoot().node("ApiKey/"+name);
   String s = root.get("name", "!NAME");
   String a = root.get("method", "!METHOD");
   String y = root.get("key", "!KEY");
   return "Name: " + s + "\nMethod: " + a + "\nKey: " + y;
  }
  else{
   throw new Exception("The " + name + " node was not found.");
  }
 }
 
 //returns all the keys in a string. 
 public String listKeys(){
  String r = "";
  for(int i = 0; i<keys.size(); i++){
   String s = keys.get(i).name;
   String a = keys.get(i).method;
   String y = keys.get(i).key;
   r = r + s + " " + a + " " + y +"\n";
  }
  return r;
 }
 
 //removes the key and all of its information.
 public void removeKey(String name) throws BackingStoreException{
  root = Preferences.userRoot().node("ApiKey/"+name);
  root.removeNode();
 }
}