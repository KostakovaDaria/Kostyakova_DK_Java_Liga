package pr1;

//package ru.philosophyit;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class FourMillions {


  /**
   * �����-�������.
   */
  static class Counter {

    /**
     * ����� �����
     */
    private long count = 0;

    /**
     * ������� +1
     */
    public void increment() {
      count++;
    }

    /**
     * �������� ������� �������� ��������
     */
    public long getCount() {
      return count;
    }
  }

  private final static int N_THREADS = 4;

  /**
   * ����� ����� � ���������
   *
   * @param args ���-�� ��������� ������
   */
  public static void main(String[] args) {
    Counter counter = new Counter();

    ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);

    // ������ java.Util.Stream ��� ����� �� 0 �� 4 (����.)
    // * �� ������ Stream � Thread
    CompletableFuture<?>[] futures = IntStream.range(0, N_THREADS)
        // ������ ������ ����� ��������� ���������� ��������
       
    		 .mapToObj(ignored -> runCounting(counter, executorService))
        // �������� CompletableFuture'� � �����
        .toArray(CompletableFuture[]::new);

    ///System.out.println("Total count: " + counter.getCount());
    
    // ����� ��� ������ �������� ���� ������
    CompletableFuture.allOf(futures).thenRun(() -> {
      // ����� ���� �� �������� 4 ���
    	System.out.println("Total count: " + counter.getCount());
        executorService.shutdown();
    });
  }

  /**
   * ��������� ������� ����������� �������� � ��������� ������
   *
   * @param counter         ������� ��� �����������
   * @param executorService ��� ������� ��� ������
   *
   * @return CompletableFuture ��� ����������, ����������� ����� ���������� �������������
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

