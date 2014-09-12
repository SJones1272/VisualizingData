public class Place{
 String name, state;
 float latitude, longitude;

  Place(String name, String state, float latitude, float longitude){
   this.name = name;
   this.state = state;
   this.latitude = latitude;
   this.longitude = longitude; 
  } 
  
  //Method to convert latitude and longitude
  void convertLanLon(){
    // Albers equal-area conic projection.
    // USGS uses standard parallels at 45.5 ̊N and 29.5 ̊N
    // with a central meridian value of 96 ̊W.
    // Latitude value is phi, longitude is lambda.
    float phi0 = 0;
    float lambda0 = radians(-96);
    float phi1 = radians(29.5f);
    float phi2 = radians(45.5f);
    float phi = radians(this.latitude);
    float lambda = radians(this.longitude);
    float n = 0.5f * (sin(phi1) + sin(phi2));
    float theta = n * (lambda - lambda0); //radians(lon - lambda0);
    float c = sq(cos(phi1)) + 2*n*sin(phi1);
    float rho = sqrt(c - 2*n*sin(phi)) / n;
    float rho0 = sqrt(c - 2*n*sin(phi0)) / n;
    this.latitude = rho * sin(theta);
    this.longitude = rho0 - rho*cos(theta);
  }
  
}
