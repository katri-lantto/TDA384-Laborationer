import TSim.*;

public class Lab1{

  public Lab1(Integer speed1, Integer speed2) throws InterruptedException {
    TSimInterface tsi = TSimInterface.getInstance();
    Train t1 = new Train(1, speed1);
    Train t2 = new Train(2, speed2);
    t1.start();
    t2.start();
  }
}
