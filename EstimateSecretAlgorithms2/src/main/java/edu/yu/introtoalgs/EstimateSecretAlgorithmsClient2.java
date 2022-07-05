package edu.yu.introtoalgs;

public class EstimateSecretAlgorithmsClient2 {
    public static void main(String[] args) {
        //Test all of the algorithms, starting with 500 pieces of data, constantly doubling until it gets to the highest amount of data it can take without taking forever a certain number of times (depending on the speed of the algorithm)
        goThroughAlg(new SecretAlgorithm1(), 33000, 3);
        goThroughAlg(new SecretAlgorithm2(), 524288001, 1000);
        goThroughAlg(new SecretAlgorithm3(), 2049000, 5);
        goThroughAlg(new SecretAlgorithm4(), 524288001, 10000);
    }
    //Go through the given algorithm, starting at 500 and constantly doubling until it reaches the maximum input before it takes forever
    //Make multiple attempts (faster the algorithm, more attempts) on each amount of data and find the average for precision, and print it out
    private static void goThroughAlg(BigOMeasurable alg, int highestNumOfInputs, int numTimesToRunTest) {
        //Print out that we are starting a new algorithm
        System.out.println("Starting algorithm " + alg.getClass());
        //starting with 500, consistently doubling until we get to the highest amount of data it can take (so the algorithm doesn't take forever), calculate the time it takes to run each algorithm with that many pieces of data multiple times
        for (int i=500; i<highestNumOfInputs; i*=2) {
            //Set a variable to hold all of the time it took from the attempts, which we will divide at the end to get the average
            long time=0;
            //run the algorithm that many times with that amount of data, adding the time it took to the variable "time"
            for (int j=0; j<numTimesToRunTest; j++) {
                time+=runAndClockAlg(alg, i);
            }
            //find the average of the iterations by dividing "time" by amount of times it ran
            time/=numTimesToRunTest;
            //print out the average time (in nanoseconds) that it took to run this algorithm with this amount of data
            System.out.println("Average it took to run "+ alg.getClass() + " with " + i + " pieces of data is " + time + " nanoseconds");
        }
    }
    //Run the given algorithm on that amount of data
    //Returns the amount of time it took to run (in nanoseconds)
    private static long runAndClockAlg(BigOMeasurable alg, int n) {
        //setup the alg with that amount of data
        alg.setup(n);
        //find time before the algorithm was run
        long timeBefore = System.nanoTime();
        //run the algorithm
        alg.execute();
        //the time it took to run the algorithm = current time (right after algorithm executed)-time before algorithm
        long timeItTook = System.nanoTime()-timeBefore;
        return timeItTook;
    }
}