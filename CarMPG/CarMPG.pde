import big.data.*;

DataSource ds;
Car[] cars;
int minMPG = 0;
int maxMPG = 45;
int minYear, maxYear;
float plotx1, ploty1, plotx2, ploty2;
int i = 0;

void setup(){
 size(400, 400);
 smooth();
 ds.initializeProcessing(this);
 ds = ds.connectXML("/Users/Stephen/Desktop/vehicles.xml"); 
 ds.load();
 ds.printUsageString();
 
 //combinged MPG for conventional fuel type.
 //this setup works here for some reason but not in 
 //nationalparks or cities applications.
 cars = ds.fetchArray("Car", "comb08", "year");
 //println(cars[10].mpg + " " + cars[10].year);
 
 //setting up min and max years
 minYear = cars[0].year;
 maxYear = cars[cars.length - 1].year;
 
 //setting up corners of the plotted area.
 plotx1 = 50;
 plotx2 = width - plotx1;
 ploty1 = 60;
 ploty2 = height - ploty1;
 
 
 
}

//Eventually turn into some graph.
void draw(){
  background(150);
  fill(255);
  strokeWeight(5);
  stroke(#72E227);
  text("MPG: " + cars[i].mpg + "  Year: " + cars[i].year, width/2, height/2);
  i = i + 1;
}
 

