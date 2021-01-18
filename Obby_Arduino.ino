#include              //Servo library
Servo servo;        //initialize a servo object for the connected servo
         
int dir = 0;
bool swim = false;
int hunger = 20;

int pinRED = 10;
int pinBLUE = 11;
int pinGREEN = 12;
int pinWATER = 13;

void setup() 
{ 
  servo.attach(9);      // attach the signal pin of servo to pin9 of arduino
  pinMode(pinRED, OUTPUT);
  pinMode(pinBLUE, OUTPUT);
  pinMode(pinGREEN, OUTPUT);
  pinMode(pinWATER, OUTPUT);
} 

void foodLvl(){
  if (hunger >= 13){
    digitalWrite(pinGREEN, HIGH);
  }else if (hunger >= 7){
    digitalWrite(pinGREEN, HIGH);
    digitalWrite(pinBLUE, HIGH);
  }else{
    digitalWrite(pinRED, HIGH);
  }
}

void inWater(){
  if (swim == true)
}

void loop() 
{
  servo.write(dir)
    delay(250);
}
