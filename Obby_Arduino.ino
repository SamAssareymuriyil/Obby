#include <Servo.h> // include the servo control library
const int buzz = 8; // set buzzer pin
const int green = 10; //set green rgb pin
const int blue = 11; //set blue rgb pin
const int red = 12; // set red rgb pin
const int water = 13; // set water pin
Servo servo; // setup servo object in Servo library

void setup() {
  // open serial port for input
  Serial.begin(9600);
  Serial.setTimeout(50);

  // initialize output pins
  pinMode(buzz, OUTPUT);
  pinMode(green, OUTPUT);
  pinMode(blue, OUTPUT);
  pinMode(red, OUTPUT);
  pinMode(water, OUTPUT);

  // attach servo data pin to object
  servo.attach(9);
  servo.write(0);
}

void loop() {
  // get input number
  String text = Serial.readString();
  // convert to integer
  int num = text.toInt();
  
  //servo - rotates head
  if (num >= 4000){
    int dir = num - 4000;
    if (dir >= 180){
      dir = dir - 180;
    }
    servo.write(dir);
    delay(250);
  }
  
  //rgb - health disp
  else if (num >= 3000){
    digitalWrite(red, LOW);
    digitalWrite(green, LOW);
    digitalWrite(blue, LOW);
    if (num >= 3016){
      digitalWrite(green, HIGH);
      delay(10);
      digitalWrite(green, LOW);
    }
    else if (num <= 3015 && num >= 3007){
      digitalWrite(green, HIGH);
      digitalWrite(red, HIGH);
      delay(10);
      digitalWrite(green, LOW);
      digitalWrite(red, LOW);
    }
    else if (num <= 3006){
      digitalWrite(red, HIGH);
      delay(10);
      digitalWrite(red, LOW);
    }
  }
  
  //blue - water disp
  else if (num >= 2000){
    if(num == 2001){
      digitalWrite(water, HIGH);
      delay(10);
      digitalWrite(water, LOW);
    }
  }
  
  //buzz - hunger warning
  else if (num >= 1000){
    if(num <= 1006){ 
    tone(buzz, 400);
    delay(100);
    noTone(buzz);
    }
    noTone(buzz);
  }
}
