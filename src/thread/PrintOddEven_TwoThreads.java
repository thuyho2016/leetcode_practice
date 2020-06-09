package thread;

/** 
 * Paypal phone interview
 * Printing odd and even number by two Threads
 * [1,2,3,4,,5,6,7,8,9,10]
 */

// EvenOdd Thread Class 

class PrintOddEven implements Runnable {

    private int max;
    private boolean isEvenNumber;
    private Printer print;

    PrintOddEven(Printer print, int max, boolean isEvenNumber) {
        this.print = print;
        this.max = max;
        this.isEvenNumber = isEvenNumber;
    }

    @Override
    public void run() {

        int number = isEvenNumber == true ? 2 : 1; //if(number %2 == 0) -->print even number
        while (number <= max) {

            if (isEvenNumber) {
               // System.out.println("Even: "+ Thread.currentThread().getName()); 
                print.printEven(number);
            } else {
               // System.out.println("Odd: "+ Thread.currentThread().getName()); //Odd :Thread-0
                print.printOdd(number);
            }
            number += 2;
        }

    }

}

class Printer {

    boolean isOdd = false;
    
    public Printer() {  }

    synchronized void printEven(int number) {

        while (isOdd == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Even:" + number);
        isOdd = false;
        notifyAll();
    }

    synchronized void printOdd(int number) {
        while (isOdd == true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Odd:" + number);
        isOdd = true;
        notifyAll();
    }
}

public class PrintOddEven_TwoThreads {
	
    public static void main(String... args) {
        Printer printer = new Printer();
        Thread t1 = new Thread(new PrintOddEven(printer, 10, false)); //isEvenNumber = false because 1 should be print first
        Thread t2 = new Thread(new PrintOddEven(printer, 10, true));
        t1.start();
        t2.start();
    }
}

