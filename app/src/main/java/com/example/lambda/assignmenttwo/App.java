package com.example.lambda.assignmenttwo;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by lambda on 9/15/2016.
 */
public class App extends Application {
  public static int BOARD_SIZE = 20;
  private static Random rand = new Random();
  private LruCache<String, Bitmap> _bitmapCache;
  public static int[] _resArray = null;
  public static ArrayList<Integer> resourceArray = null;

  @Override
  public void onCreate(){
    super.onCreate();
    this._init();
    this.prepareRandomResourceArray();

  }

  private void _init(){
    Field[] idFields = R.mipmap.class.getFields();
    this._resArray = new int[idFields.length];
    for(int index=1; index <idFields.length; index++){
      try{
        this._resArray[index] = idFields[index].getInt(null);
      }
      catch (IllegalAccessException iae){
        iae.printStackTrace();
      }
    } // END FOR LOOP
  } // END METHOD _init

  public static int getRandom(){
    return rand.nextInt(BOARD_SIZE/2) + 1;
    //return Constants.FORTUNES[rand.nextInt(Constants.FORTUNES.length) + 1];
    //return fortunes[rand.nextInt(fortunes.length) + 1];
  } // END PUBLIC STATIC METHOD getfortune

  private void prepareRandomResourceArray(){
    resourceArray = new ArrayList<>();
    for(int index=1; index<11; index++){
      resourceArray.add(new Integer(_resArray[index]));
      resourceArray.add(new Integer(_resArray[index]));
    } // END for LOOP
    Collections.shuffle(resourceArray);
    Log.d("App", "after shuffle");
  }

  public void shuffle(){
    if(resourceArray != null){
      Collections.shuffle(resourceArray);
    }
  }
}
