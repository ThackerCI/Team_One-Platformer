package com.test.platformer;//  Olutayo Elelu
//  Last Edited: 02.29.16
//  Record Breaker - Records Class
//  Iteration 2
  
//- Location (int x, int y): Defined by our game mechanics. For now, a pair of ints serving as x and y coordinate. Functions include getLocation and setLocation.
//- Sprite (Picture File reference): Color coordinated for different effects/Goal. Functions include getSprite.
//- Track (MP3/Music File reference): Information relating to the actual MP3 of the object. Can be pulled by the game engine to play it. Functions include getTrack, playTrack
//- Goal(Boolean): is the record a goal record or not? Used mainly by the main game engine to know when to end the level. Functions include isGoal and setGoal
//- Collect(Boolean): Has the record been collected? Functions include isCollected


public class Record
{  
   int[] loc = new int[2];
   boolean goal = false;
   boolean collect = true;
   
   //Location
   //Location (int x, int y): Defined by our game mechanics. For now, a pair of ints serving as x and y coordinate.
   //Functions include getLocation and setLocation.
   //represented as an array w/ 2 values
   public void setLocation(int x, int y)
   {
     loc[0] = x;
     loc[1] = y;
   }
   
   public int[] getLocation()
   {
     return loc;
   }
   
   //Goal(Boolean): is the record a goal record or not?
   //Collect(Boolean): Has the record been collected? Functions include isCollected
   //Used mainly by the main game engine to know when to end the level. Functions include isGoal and setGoal
   
   public boolean isGoal()
   {
      return goal;
   }
   
   public void setGoal()
   {
      goal = true;
   }
   
   public boolean isCollected()
   {
      return collect;
   }
   
   public void collectRecord()
   {
      collect = true;
   }
   /*
   //- Sprite (Picture File reference): Color coordinated for different effects/Goal. Functions include getSprite.
   
   public void getSprite()
   {
   
   }
   
   
   
   
   //Track-related functions will be completed next iteration
   */
   
   public void printRecord()
   {
      System.out.println("Location is " + getLocation());
      System.out.println("Is this a goal record: " + isGoal());
      System.out.println("Has it been collected: " + isCollected());
   }

   public static void main (String [] args)
   { 
      System.out.println("Record Class!");
      
      Record yeezus = new Record();
      yeezus.setLocation(5, 68);
      yeezus.collectRecord();
      
      yeezus.printRecord();
      
      return;
   } 
}