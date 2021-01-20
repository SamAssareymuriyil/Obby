#include <Servo.h> 
int servoPin = 9; 

int hunger = 0;

int swim = 0;

int counter = 0;

Servo Servo1; 

void setup()
{
  Serial.begin(9600);

  pinMode(13, OUTPUT);
  pinMode(3, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(6, OUTPUT);
  pinMode(8, OUTPUT);
  
  Servo1.attach(servoPin); 
}

void loop()
{
  //hunger = Serial.read();
for (int i = 20; i >= 0; i--) {
  Servo1.write(i*18); 
  
  hunger = i;
  
  swim = random(0,1+1);
  
  if (swim == 1) {
      digitalWrite(13, HIGH);
    counter+=1;
                 } else {
    digitalWrite(13, LOW);
                 }               
  //digitalWrite(13, HIGH);
  delay(1000); // Wait for 1000 millisecond(s)
  //digitalWrite(13, LOW);
  //delay(1000); // Wait for 1000 millisecond(s)

  if (hunger >= 13) {
    analogWrite(12, 51);
    analogWrite(10, 204);
    analogWrite(11, 0);
  } else if (hunger < 13 && hunger >= 7) {
      analogWrite(12, 51);
      analogWrite(10, 51);
      analogWrite(11, 255);
  } else if (hunger < 7 && hunger >= 1) {
      analogWrite(12, 255);
      analogWrite(10, 0);
      analogWrite(11, 0);
    
  } else if (hunger == 0){
    tone(8, 523, 1000); // play tone 60 (C5 = 523 Hz)
  }
  Serial.print(hunger);
  Serial.print(" \n ");
}
  Serial.print(counter);
}
