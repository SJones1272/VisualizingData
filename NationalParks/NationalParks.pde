import big.data.*;

Park[] listOfParks;
DataSource ds;
float mapX1 = 0;
float mapY1 = 0;
float mapX2 = 600;
float mapY2 = 380;

float minX = 1;
float maxX = -1;
float minY = 1;
float maxY = -1;

void setup(){
  size(640, 400);
  background(255);
  ds.initializeProcessing(this);
  ds = ds.connectCSV("data/US_National_Parks_Coordinates.csv");
  ds.load();
  //ds.printUsageString();
  
  //Turning CSV into an array of parks.
  //Think I have a syntax issue. Seems to work with XML but not csv. 
  listOfParks = ds.fetchArray("Park", "Name", "Latitude", "Longitude");
  println(listOfParks.length);
  
   for(int i = 0; i<listOfParks.length; i++){
    listOfParks[i].convertLanLon();
    float x = listOfParks[i].latitude;
    float y = listOfParks[i].longitude;
    if (x > maxX) maxX = x;
    if (x < minX) minX = x;
    if (y > maxY) maxY = y;
    if (y < minY) minY = y;
 }
}

public void draw(){
  
  for(int r = 0; r<listOfParks.length; r++){
   int xx = (int) TX(listOfParks[r].latitude);
   int yy = (int) TY(listOfParks[r].longitude);
   set(xx, yy, #72E227); 
  }
  
}

float TX(float x){
  return map(x, minX, maxX, mapX1, mapX2);
}

float TY(float y){
  return map(y, minY, maxY, mapY2, mapY1);
}

