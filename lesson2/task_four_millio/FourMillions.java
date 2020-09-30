//package pr1;

package ru.philosophyit;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class FourMillions {


  /**
   * Êëàññ-ñ÷¸ò÷èê.
   */
  static class Counter {

    /**
     * Áóôåð ñ÷¸òà
     */
    private long count = 0;

    /**
     * Ñ÷èòàåì +1
     */
    public void increment() {
      count++;
    }

    /**
     * Ïîëó÷èòü òåêóùåå çíà÷åíèå ñ÷¸ò÷èêà
     */
    public long getCount() {
      return count;
    }
  }

  private final static int N_THREADS = 4;

  /**
   * Òî÷êà âõîäà â ïðîãðàììó
   *
   * @param args àðã-òû êîìàíäíîé ñòðîêè
   */
  public static void main(String[] args) {
    Counter counter = new Counter();

    ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);

    // ñîçäà¸ì java.Util.Stream äëÿ èíòîâ ùò 0 äî 4 (èñêä.)
    // * íå ïóòàòü Stream è Thread
    CompletableFuture<?>[] futures = IntStream.range(0, N_THREADS)
        // âìåñòî êàæäîé öèôðû çàïóñêàåì èíêðåìåíòû ñ÷¸ò÷èêà
       
    		 .mapToObj(ignored -> runCounting(counter, executorService))
        // ñîáèðàåì CompletableFuture'û â ìàññè
        .toArray(CompletableFuture[]::new);

    ///System.out.println("Total count: " + counter.getCount());
    
    // êîãäà âñå ïîòîêè çàâåðøàò ñâîþ ðàáîòó
    CompletableFuture.allOf(futures).thenRun(() -> {
      // èìååì øàíñ íå ïîëó÷èòü 4 ìëí
    	System.out.println("Total count: " + counter.getCount());
        executorService.shutdown();
    });
  }

  /**
   * Çàïóñêàåò ìèëëèîí èíêðåìåíòîâ ñ÷¸ò÷èêà â îòäåëüíîì ïîòîêå
   *
   * @param counter         ñ÷¸ò÷èê äëÿ èíêðåìåíòîâ
   * @param executorService ïóë ïîòîêîâ äëÿ ðàáîòû
   *
   * @return CompletableFuture áåç ðåçóëüòàòà, ðàçðåøàåìûé ïîñëå çàâåðøåíèÿ èíêðåìåíòàöèé
   */
  
  
  private static CompletableFuture<?> runCounting(Counter counter, ExecutorService executorService) {
    
	  try {  
		  Thread.sleep(50);
    
    } catch (InterruptedException e) {}
	  
	  return CompletableFuture.runAsync(
		        () -> {
		          for (int j = 0; j < 1000000; j++) {
		            counter.increment();
		          
		          }
		        },
		        executorService
		    );

       
  }
}

