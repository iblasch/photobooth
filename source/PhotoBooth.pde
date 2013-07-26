import hypermedia.video.*;
import processing.pdf.*;
OpenCV opencv;
int counter = 0;
PImage img;
PImage img1;
PImage img2;
Button capture;
Button finish;
Button done;
Boolean image_print;
Boolean flip;
Boolean image1, image2, image3, image4;
float lasttime;
Blob[] blobs;

void setup(){
   size(480,360, P2D);
   //open video stream
   opencv = new OpenCV(this);
   opencv.capture(160, 120);
   opencv.threshold(80);    // set black & white threshold 
   image_print = false;
   flip = true;
   image1 = false;
   image2 = false;
   image3 = false;
   image4 = false;
 //  capture = new Button("Press to Capture", 180, 330, 100, 20);
  // finish = new Button("Press to Print", 180, 330, 100, 20);
 //  done = new Button("Done", 180, 330, 100, 20);
   lasttime = millis();

   
   img = loadImage("abstract_bubbles.jpg");
   //img1 = loadImage("abstract_water.jpg");
   //img2 = loadImage("straws.jpg");
   image(img, 0, 0);
   textSize(25);

}

void draw() {
    
  if (counter == 1){
    if(image1 == false){
      image(opencv.image(OpenCV.MEMORY), 70, 50);
      image1 = true;
    }
  }
  else if (counter == 2){
    if(image2 == false){
      image(opencv.image(OpenCV.MEMORY), 250, 50);
      image2 = true;
    }
  }
  else if (counter == 3){
    if(image3 == false){
      image(opencv.image(OpenCV.MEMORY), 250, 190);
      image3 = true;
    }
  }
  else if (counter == 4){
    if(image4 == false){
      image(opencv.image(OpenCV.MEMORY), 70, 190); 
      flip = false;
      image4 = true;
    }
    if (image_print == false){
      saveFrame("PhotoBooth-######.png"); 
      image_print = true;}
  }
  
  if(flip == true){
    opencv.read();                //grab frame from camera
    //capture.display();
    if (counter==0){
      image(opencv.image(),70, 50);  //show current image
      opencv.remember();
      }
    else if (counter == 1){
      image(opencv.image(),250, 50);  //show current image
      opencv.remember();
      }
    else if (counter == 2){
      image(opencv.image(), 250, 190);  //show current image
      opencv.remember();
      }
    else if (counter == 3){    
      image(opencv.image(),70, 190);  //show current image
      opencv.remember();
    }  
      
      
  }  // close flipe bracket




  if(millis()-lasttime > 4000){
    counter = counter + 1;
    if (counter == 5){
      // saveFrame("PhotoBooth-######.png"); 
    }
    lasttime = millis();
  }  // close bracket for timer
  


  
}  // close draw bracket


  // If you want to employ a button - use the code below
  //void mousePressed(){
   // counter = counter + 1;
   // if(capture.mousePressed()){
    //    opencv.remember();   //store the actual image in memory
   // }
    
  //  if (counter == 5){
    //   saveFrame("PhotoBooth-######.png"); 
   // }
  //}
