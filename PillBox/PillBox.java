import java.util.Scanner;

import big.data.*;

public class PillBox {

 //api info can be found here https://github.com/HHS/pillbox_docs/wiki/Pillbox-API-documentation
 //api key is: 5WJQ4X4PR9
 
 public static void main(String[] args){
  String name;
  String id;
  String[] drugs;
  
  //Gets name of drug
  Scanner scan = new Scanner(System.in);
  System.out.print("Enter the name of the drug: ");
  name = scan.next();
  scan.close();
  
  //Gets the data for the drug name.
  DataSource ds1 = DataSource.connect("http://rxnav.nlm.nih.gov/REST/rxcui?").set("name", name);
  ds1.load();
  //ds1.printUsageString();
  
  //gets id of the drug.
  id = ds1.fetchString("idGroup/rxnormId");
  //System.out.println(id);
  
  //gets the data of all brands associated with drug id. 
  DataSource ds = DataSource.connect("http://rxnav.nlm.nih.gov/REST/brands?").set("ingredientids", id);
  ds.load();
  ds.printUsageString();
  
  //puts all the brand names into an array.
  drugs = ds.fetchStringArray("brandGroup/conceptProperties/name");
  
  System.out.println(drugs.length);
  
  //Prints out all drug brands with ingredient included. Does not work -- only pulls the first thing.
  for(int i = 0; i<drugs.length; i++){
   System.out.println(drugs[i]);
  }
  
  
 }
}
