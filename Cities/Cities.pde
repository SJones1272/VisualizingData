import big.data.*;

Place[] listOfCities;
DataSource ds;

float mapX1 = 0;
float mapY1 = 0;
float mapX2 = 640;
float mapY2 = 400;

float minX = 1;
float maxX = -1;
float minY = 1;
float maxY = -1;


void setup(){
 size(640, 400);
 background(255);
 DataSource.initializeProcessing(this);
 ds = DataSource.connectCSV("data/US_Cities_Longitude_Latitude.csv");
 ds.load();
 ds.printUsageString();
 
 listOfCities = ds.fetchArray("Place", "City", "State", "Latitude", "Longitude");
 
 //get max/min and convert all cords
 for(int i = 0; i<listOfCities.length; i++){
    listOfCities[i].convertLanLon();
    float x = listOfCities[i].latitude;
    float y = listOfCities[i].longitude;
    if (x > maxX) maxX = x;
    if (x < minX) minX = x;
    if (y > maxY) maxY = y;
    if (y < minY) minY = y;
 }
 
 
}

float TX(float x){
  return map(x, minX, maxX, mapX1, mapX2);
}

float TY(float y){
  return map(y, minY, maxY, mapY2, mapY1);
}

public void draw(){
  
  //Map doesnt come out right, points seem to be meshed close together. 
  for(int r = 0; r<listOfCities.length; r++){
   int xx = (int) TX(listOfCities[r].latitude);
   int yy = (int) TY(listOfCities[r].longitude);
   set(xx, yy, #72E227); 
 }
  
}
