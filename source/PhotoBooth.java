import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import hypermedia.video.*; 
import processing.pdf.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class PhotoBooth extends PApplet {



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

public void setup(){
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

public void draw() {
    
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

int HORIZONTAL = 0;
int VERTICAL   = 1;
int UPWARDS    = 2;
int DOWNWARDS  = 3;

class Widget
{

  
  PVector pos;
  PVector extents;
  String name;

  int inactiveColor = color(60, 60, 100);
  int activeColor = color(100, 100, 160);
  int bgColor = inactiveColor;
  int lineColor = color(255);
  
  
  
  public void setInactiveColor(int c)
  {
    inactiveColor = c;
    bgColor = inactiveColor;
  }
  
  public int getInactiveColor()
  {
    return inactiveColor;
  }
  
  public void setActiveColor(int c)
  {
    activeColor = c;
  }
  
  public int getActiveColor()
  {
    return activeColor;
  }
  
  public void setLineColor(int c)
  {
    lineColor = c;
  }
  
  public int getLineColor()
  {
    return lineColor;
  }
  
  public String getName()
  {
    return name;
  }
  
  public void setName(String nm)
  {
    name = nm;
  }


  Widget(String t, int x, int y, int w, int h)
  {
    pos = new PVector(x, y);
    extents = new PVector (w, h);
    name = t;
    //registerMethod("mouseEvent", this);
  }

  public void display()
  {
  }

  public boolean isClicked()
  {
    if (mouseX > pos.x && mouseX < pos.x+extents.x 
      && mouseY > pos.y && mouseY < pos.y+extents.y)
    {
      //println(mouseX + " " + mouseY);
      return true;
    }
    else
    {
      return false;
    }
  }
  
  public void mouseEvent(MouseEvent event)
  {
    //if (event.getFlavor() == MouseEvent.PRESS)
    //{
    //  mousePressed();
    //}
  }
  
  
  public boolean mousePressed()
  {
    return isClicked();
  }
  
  public boolean mouseDragged()
  {
    return isClicked();
  }
  
  
  public boolean mouseReleased()
  {
    return isClicked();
  }
}

class Button extends Widget
{
  PImage activeImage = null;
  PImage inactiveImage = null;
  PImage currentImage = null;
  int imageTint = color(255);
  
  Button(String nm, int x, int y, int w, int h)
  {
    super(nm, x, y, w, h);
  }
  
  public void setImage(PImage img)
  {
    setInactiveImage(img);
    setActiveImage(img);
  }
  
  public void setInactiveImage(PImage img)
  {
    if(currentImage == inactiveImage || currentImage == null)
    {
      inactiveImage = img;
      currentImage = inactiveImage;
    }
    else
    {
      inactiveImage = img;
    }
  }
  
  public void setActiveImage(PImage img)
  {
    if(currentImage == activeImage || currentImage == null)
    {
      activeImage = img;
      currentImage = activeImage;
    }
    else
    {
      activeImage = img;
    }
  }
  
  public void setImageTint(int c)
  {
    imageTint = c;
  }

  public void display()
  {
    if(currentImage != null)
    {
      //float imgHeight = (extents.x*currentImage.height)/currentImage.width;
      float imgWidth = (extents.y*currentImage.width)/currentImage.height;
      
      
      pushStyle();
      imageMode(CORNER);
      //tint(imageTint);
      image(currentImage, pos.x, pos.y, imgWidth, extents.y);
      stroke(bgColor);
      noFill();
      rect(pos.x, pos.y, imgWidth,  extents.y);
      //noTint();
      popStyle();
    }
    else
    {
      pushStyle();
      stroke(lineColor);
      fill(bgColor);
      rect(pos.x, pos.y, extents.x, extents.y);
  
      fill(lineColor);
      textAlign(CENTER, CENTER);
      text(name, pos.x + 0.5f*extents.x, pos.y + 0.5f* extents.y);
      popStyle();
    }
  }
  
  public boolean mousePressed()
  {
    if (super.mousePressed())
    {
      bgColor = activeColor;
      if(activeImage != null)
        currentImage = activeImage;
      return true;
    }
    return false;
  }
  
  public boolean mouseReleased()
  {
    if (super.mouseReleased())
    {
      bgColor = inactiveColor;
      if(inactiveImage != null)
        currentImage = inactiveImage;
      return true;
    }
    return false;
  }
}

class Toggle extends Button
{
  boolean on = false;

  Toggle(String nm, int x, int y, int w, int h)
  {
    super(nm, x, y, w, h);
  }


  public boolean get()
  {
    return on;
  }

  public void set(boolean val)
  {
    on = val;
    if (on)
    {
      bgColor = activeColor;
      if(activeImage != null)
        currentImage = activeImage;
    }
    else
    {
      bgColor = inactiveColor;
      if(inactiveImage != null)
        currentImage = inactiveImage;
    }
  }

  public void toggle()
  {
    set(!on);
  }

  
  public boolean mousePressed()
  {
    return super.isClicked();
  }

  public boolean mouseReleased()
  {
    if (super.mouseReleased())
    {
      toggle();
      return true;
    }
    return false;
  }
}

class RadioButtons extends Widget
{
  public Toggle [] buttons;
  
  RadioButtons (int numButtons, int x, int y, int w, int h, int orientation)
  {
    super("", x, y, w*numButtons, h);
    buttons = new Toggle[numButtons];
    for (int i = 0; i < buttons.length; i++)
    {
      int bx, by;
      if(orientation == HORIZONTAL)
      {
        bx = x+i*(w+5);
        by = y;
      }
      else
      {
        bx = x;
        by = y+i*(h+5);
      }
      buttons[i] = new Toggle("", bx, by, w, h);
    }
  }
  
  public void setNames(String [] names)
  {
    for (int i = 0; i < buttons.length; i++)
    {
      if(i >= names.length)
        break;
      buttons[i].setName(names[i]);
    }
  }
  
  public void setImage(int i, PImage img)
  {
    setInactiveImage(i, img);
    setActiveImage(i, img);
  }
  
  public void setAllImages(PImage img)
  {
    setAllInactiveImages(img);
    setAllActiveImages(img);
  }
  
  public void setInactiveImage(int i, PImage img)
  {
    buttons[i].setInactiveImage(img);
  }

  
  public void setAllInactiveImages(PImage img)
  {
    for (int i = 0; i < buttons.length; i++)
    {
      buttons[i].setInactiveImage(img);
    }
  }
  
  public void setActiveImage(int i, PImage img)
  {
    buttons[i].setActiveImage(img);
  }
  
  
  
  public void setAllActiveImages(PImage img)
  {
    for (int i = 0; i < buttons.length; i++)
    {
      buttons[i].setActiveImage(img);
    }
  }

  public void set(String buttonName)
  {
    for (int i = 0; i < buttons.length; i++)
    {
      if(buttons[i].getName().equals(buttonName))
      {
        buttons[i].set(true);
      }
      else
      {
        buttons[i].set(false);
      }
    }
  }
  
  public int get()
  {
    for (int i = 0; i < buttons.length; i++)
    {
      if(buttons[i].get())
      {
        return i;
      }
    }
    return -1;
  }
  
  public String getString()
  {
    for (int i = 0; i < buttons.length; i++)
    {
      if(buttons[i].get())
      {
        return buttons[i].getName();
      }
    }
    return "";
  }

  public void display()
  {
    for (int i = 0; i < buttons.length; i++)
    {
      buttons[i].display();
    }
  }

  public boolean mousePressed()
  {
    for (int i = 0; i < buttons.length; i++)
    {
      if(buttons[i].mousePressed())
      {
        return true;
      }
    }
    return false;
  }
  
  public boolean mouseDragged()
  {
    for (int i = 0; i < buttons.length; i++)
    {
      if(buttons[i].mouseDragged())
      {
        return true;
      }
    }
    return false;
  }

  public boolean mouseReleased()
  {
    for (int i = 0; i < buttons.length; i++)
    {
      if(buttons[i].mouseReleased())
      {
        for(int j = 0; j < buttons.length; j++)
        {
          if(i != j)
            buttons[j].set(false);
        }
        //buttons[i].set(true);
        return true;
      }
    }
    return false;
  }
}

class Slider extends Widget
{
  float minimum;
  float maximum;
  float val;
  int textWidth = 60;
  int orientation = HORIZONTAL;

  Slider(String nm, float v, float min, float max, int x, int y, int w, int h, int ori)
  {
    super(nm, x, y, w, h);
    val = v;
    minimum = min;
    maximum = max;
    orientation = ori;
    if(orientation == HORIZONTAL)
      textWidth = 60;
    else
      textWidth = 20;
  }

  public float get()
  {
    return val;
  }

  public void set(float v)
  {
    val = v;
    val = constrain(val, minimum, maximum);
  }

  public void display()
  {
    pushStyle();
    textAlign(LEFT, TOP);
    fill(lineColor);
    text(name, pos.x, pos.y);
    stroke(lineColor);
    noFill();
    if(orientation ==  HORIZONTAL){
      rect(pos.x+textWidth, pos.y, extents.x-textWidth, extents.y);
    } else {
      rect(pos.x, pos.y+textWidth, extents.x, extents.y-textWidth);
    }
    noStroke();
    fill(bgColor);
    float sliderPos; 
    if(orientation ==  HORIZONTAL){
        sliderPos = map(val, minimum, maximum, 0, extents.x-textWidth-4); 
        rect(pos.x+textWidth+2, pos.y+2, sliderPos, extents.y-4);
    } else if(orientation ==  VERTICAL || orientation == DOWNWARDS){
        sliderPos = map(val, minimum, maximum, 0, extents.y-textWidth-4); 
        rect(pos.x+2, pos.y+textWidth+2, extents.x-4, sliderPos);
    } else if(orientation == UPWARDS){
        sliderPos = map(val, minimum, maximum, 0, extents.y-textWidth-4); 
        rect(pos.x+2, pos.y+textWidth+2 + (extents.y-textWidth-4-sliderPos), extents.x-4, sliderPos);
    };
    popStyle();
  }

  
  public boolean mouseDragged()
  {
    if (super.mouseDragged())
    {
      if(orientation ==  HORIZONTAL){
        set(map(mouseX, pos.x+textWidth, pos.x+extents.x-4, minimum, maximum));
      } else if(orientation ==  VERTICAL || orientation == DOWNWARDS){
        set(map(mouseY, pos.y+textWidth, pos.y+extents.y-4, minimum, maximum));
      } else if(orientation == UPWARDS){
        set(map(mouseY, pos.y+textWidth, pos.y+extents.y-4, maximum, minimum));
      };
      return true;
    }
    return false;
  }

  public boolean mouseReleased()
  {
    if (super.mouseReleased())
    {
      if(orientation ==  HORIZONTAL){
        set(map(mouseX, pos.x+textWidth, pos.x+extents.x-10, minimum, maximum));
      } else if(orientation ==  VERTICAL || orientation == DOWNWARDS){
        set(map(mouseY, pos.y+textWidth, pos.y+extents.y-10, minimum, maximum));
      } else if(orientation == UPWARDS){
        set(map(mouseY, pos.y+textWidth, pos.y+extents.y-10, maximum, minimum));
      };
      return true;
    }
    return false;
  }
}

class MultiSlider extends Widget
{
  Slider [] sliders;

  MultiSlider(String [] nm, float min, float max, int x, int y, int w, int h, int orientation)
  {
    super(nm[0], x, y, w, h*nm.length);
    sliders = new Slider[nm.length];
    for (int i = 0; i < sliders.length; i++)
    {
      int bx, by;
      if(orientation == HORIZONTAL)
      {
        bx = x;
        by = y+i*h;
      }
      else
      {
        bx = x+i*w;
        by = y;
      }
      sliders[i] = new Slider(nm[i], 0, min, max, bx, by, w, h, orientation);
    }
  }

  public void set(int i, float v)
  {
    if(i >= 0 && i < sliders.length)
    {
      sliders[i].set(v);
    }
  }
  
  public float get(int i)
  {
    if(i >= 0 && i < sliders.length)
    {
      return sliders[i].get();
    }
    else
    {
      return -1;
    }
    
  }

  public void display()
  {
    for (int i = 0; i < sliders.length; i++)
    {
      sliders[i].display();
    }
  }

  
  public boolean mouseDragged()
  {
    for (int i = 0; i < sliders.length; i++)
    {
      if(sliders[i].mouseDragged())
      {
        return true;
      }
    }
    return false;
  }

  public boolean mouseReleased()
  {
    for (int i = 0; i < sliders.length; i++)
    {
      if(sliders[i].mouseReleased())
      {
        return true;
      }
    }
    return false;
  }
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "PhotoBooth" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
