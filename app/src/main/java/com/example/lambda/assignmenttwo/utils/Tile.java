package com.example.lambda.assignmenttwo.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.example.lambda.assignmenttwo.R;

/**
 * Created by lambda on 9/15/2016.
 */
public class Tile extends ImageView{
  private int _resId = -1;
  private boolean state = false;
  private boolean done = false;
  private Context ctx;

  public Tile(Context context) {
    super(context);
    ctx = context;
  }

  public void setResId(int id){
    this._resId = id;
  }

  public boolean isVisible(){
    return this.state;
  }

  public void flip(){
    /*
    if(state){ // IF IMAGE IS SHOWN
      this.setImageResource(R.mipmap.z);
      state = false;
    }
    else{
      this.setImageResource(_resId);
      state = true;
    }
    */
    if(!state){
      this.setImageResource(_resId);
      state = true;
    }
  }

  public int getResId(){
    return _resId;
  }

  public void reset(){
    if(state){
      this.setImageResource(R.mipmap.z);
      state = false;
    }
  }

  public void setCorrect(){
    this.setImageResource(R.mipmap.z_done);
    this.setBackgroundColor(Color.GREEN);
  }

  public void finish(){
    this.done = true;
  }
}
