public class PrimesImprovement
{
  public static void main(String[] args)
  {
 
    //This code should print out the prime numbers.
    //task: Fix and improve the algorithm
    
    System.out.println("Printing primes from 1 to 100");
    int i;
    int j;
    boolean prime;
    for(i = 1; i <= 100; i++)
    {
      prime = true;
      for(j = 1; j < i; j++) 
        if(i%j == 0) prime = false; 
      if(prime) System.out.print(i+" ");
    }
    System.out.println();
  }
}

