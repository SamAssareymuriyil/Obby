import arduino.Arduino;
class JavaArduinoComm {
    public static void main(String[] args) {
        Arduino obj = new Arduino('4', BAUD_RATE);
        obj.openConnection();
    }
}