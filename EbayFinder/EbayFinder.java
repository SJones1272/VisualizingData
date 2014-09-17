import big.data.*;

public class EbayFinder {

 public static void main(String[] args) {
  DataSource ds = DataSource.connect("http://svcs.ebay.com/services/search/FindingService/v1");
  ds.set("OPERATION-NAME", "findItemsByKeywords").set("SERVICE-VERSION", "1.0.0");
  ds.set("SECURITY-APPNAME", "StephenJ-4d75-4f49-b701-c21f0c089826").set("GLOBAL-ID", "EBAY-US");
  ds.set("format", "xml").set("keywords", "silver");
  ds.load();
  ds.printUsageString();
  
  
 }

}
