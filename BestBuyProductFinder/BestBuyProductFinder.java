import java.util.Scanner;
import big.data.*;

public class BestBuyProductFinder {

 public static void main(String[] args){
  //apikey = 9mm3ncj8r2a3cjq826m8rkac
  //api documentation at https://developer.bestbuy.com/documentation#responseFormat-response-format
  //http://api.remix.bestbuy.com/v1/products(search=samsung)?show=name,salePrice&apiKey=9mm3ncj8r2a3cjq826m8rkac&pageSize=100
  //Not the most accurate search...Kind of interesting though. 
   
  String product;
  Scanner scan = new Scanner(System.in);
  System.out.println("Enter product name/type: ");
  product = scan.next();
  scan.close();
  
  DataSource ds = DataSource.connect("http://api.remix.bestbuy.com/v1/products(search="+ product + ")");
  ds.set("show", "name,salePrice").set("apiKey", "9mm3ncj8r2a3cjq826m8rkac").set("pageSize", "100");
  ds.load();
  //ds.printUsageString();
  
  Product[] listOfProducts = ds.fetchArray("Product", "name", "salePrice");
  System.out.println(listOfProducts.length);
  
  System.out.println("The following is the results from the search for " + product);
  for(int i = 0; i<listOfProducts.length; i++){
    System.out.println("Name: " + listOfProducts[i].name + " | Price: " + listOfProducts[i].price);
  }
  
 }
 
 
}

class Product{
  String name;
  double price;
  
  Product(String name, double price){
    this.name = name;
    this.price = price;
  }
}
