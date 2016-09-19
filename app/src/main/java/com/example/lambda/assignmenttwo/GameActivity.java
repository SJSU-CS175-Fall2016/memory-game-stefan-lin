package com.example.lambda.assignmenttwo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.os.Handler;
import android.widget.TextView;
import android.app.AlertDialog;

import com.example.lambda.assignmenttwo.utils.Tile;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by lambda on 9/15/2016.
 */
public class GameActivity extends AppCompatActivity {
  private ArrayList<Tile> _tiles = new ArrayList<>(20);
  private int magicNum = 0;
  private Tile firstSelectedTile = null;
  private TextView _scoreBoard = null;
  private int _score = 0;
  private int _total = 10;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);
    this._scoreBoard = (TextView)findViewById(R.id.text_view_game_score);

    for(int row=0; row<5; row++){
      this._addImageViewsToLinearLayout(row);
    }
  }

  /*
  @Override
  public void onSaveInstanceState(Bundle outState){
    super.onSaveInstanceState(outState);
    // TODO: 9/18/2016  
  }
  */

  private void _addImageViewsToLinearLayout(int rowNumber){
    LinearLayout linearLayout = null;
    switch (rowNumber){
      case 0:
        linearLayout = (LinearLayout)findViewById(R.id.row0);
        break;
      case 1:
        linearLayout = (LinearLayout)findViewById(R.id.row1);
        break;
      case 2:
        linearLayout = (LinearLayout)findViewById(R.id.row2);
        break;
      case 3:
        linearLayout = (LinearLayout)findViewById(R.id.row3);
        break;
      case 4:
        linearLayout = (LinearLayout)findViewById(R.id.row4);
        break;
      default:
        linearLayout = null;
    } // END switch STATEMENT

    if(linearLayout == null){
      Log.e("error", "linearlayout is null");
      return;
    }

    for(int index=0; index<4; index++){
      //ImageView imageView = new ImageView(this);
      final Tile imageView = new Tile(this);
      LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
          0,
          LinearLayout.LayoutParams.MATCH_PARENT
      );
      layoutParams.weight = 1;
      layoutParams.setMargins(4, 4, 4, 4);
      imageView.setBackgroundColor(Color.BLACK);
      imageView.setLayoutParams(layoutParams);
      imageView.setImageResource(App.resourceArray.get(rowNumber * 4 + index));
      if(App.resourceArray != null) {
        int i = rowNumber * 4 + index;
        int resId = App.resourceArray.get(i);
        //Log.e("ImageView", String.valueOf(resId));
        //imageView.setImageResource(resId);
        imageView.setResId(resId);
        imageView.setImageResource(R.mipmap.z);
        //imageView.setBackground(
        //    ContextCompat.getDrawable(this, R.drawable.imageview_border)
        //);
      }
      else{
        imageView.setImageResource(R.mipmap.ic_sentiment_neutral_white_48dp);
      }
      //imageView.setVisibility(View.INVISIBLE);
      linearLayout.addView(imageView);
      imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          imageView.flip();
          new Handler().postDelayed(
              new Runnable() {
                @Override
                public void run() {
                  Log.i("pause", "wait for some milliseconds");
                  checkState(imageView);
                }
              }, 1000
          );
        }
      });
      this._tiles.add(imageView);
    }
  } // END METHOD _insertRows


  private void checkState(Tile selectedTile){
    if(magicNum == 1){
      // validate two tiles
      if(selectedTile.getResId() == firstSelectedTile.getResId()){
        // Correct
        selectedTile.setCorrect();
        firstSelectedTile.setCorrect();
        selectedTile.finish();
        firstSelectedTile.finish();
        // accumulate score
        _score += 100;
        _total--;
        this._scoreBoard.setText(String.valueOf(_score));
      }
      else{
        // incorrect
        _score -= 10;
        // set timeout and reset tiles
        firstSelectedTile.reset();
        selectedTile.reset();
        this._scoreBoard.setText(String.valueOf(_score));
      }
      firstSelectedTile = null;
      magicNum = 0;
      if(_total == 0){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
            this
        );
        alertDialogBuilder
            .setMessage("Your score is " + String.valueOf(_score))
            .setCancelable(false)
            .setPositiveButton("OK",new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog,int id) {
                Intent intent = new Intent(
                    GameActivity.this,
                    MainActivity.class
                );
                GameActivity.this.startActivity(intent);
              }
            });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
      }
      return;
    }
    magicNum++;
    firstSelectedTile = selectedTile;
  }
}
