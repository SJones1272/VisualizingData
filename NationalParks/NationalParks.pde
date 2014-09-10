import big.data.*;

Park[] listOfParks;
DataSource ds;

void setup(){
  ds.initializeProcessing(this);
  ds = ds.connectCSV("data/US_National_Parks_Coordinates.csv");
  ds.load();
  ds.printUsageString();
  
  //Turning CSV into an array of parks.
  //Think I have a syntax issue. Seems to work with XML but not csv. 
  listOfParks = ds.fetchArray("Park", "Name", "Latitude", "Longitude");
}
