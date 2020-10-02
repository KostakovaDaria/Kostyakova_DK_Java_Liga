package ru.philosophyit;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {
 
  static class Friend {
    private final String name;
        
    public Friend(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }
    
    public void bow(Friend bower) {
    	
    			synchronized (this)
    			{
        		 System.out.format("%s: %s подстрелил меня!\n", this.name, bower.getName());
        	     System.out.format("%s: стреляю в ответ!\n", this.name);
        	  
    		 }	
   
    			bower.bowBack(this);
    }
             
    public synchronized void bowBack(Friend bower) 
    {
    		System.out.format("%s: %s подстрелил меня!\n", this.name, bower.getName());
    }
  }
 
  public static void main(String[] args) {
  Friend alphonse = new Friend("Alphonse");
  Friend gaston = new Friend("Gaston");
   
    new Thread(() -> alphonse.bow(gaston)).start();
           
    new Thread(() -> gaston.bow(alphonse)).start(); 
  }   
}
