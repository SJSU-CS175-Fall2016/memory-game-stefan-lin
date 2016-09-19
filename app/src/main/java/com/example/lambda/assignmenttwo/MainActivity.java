package com.example.lambda.assignmenttwo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    _initButtons();
  }

  private void _initButtons(){
    Button playButton = (Button)findViewById(R.id.main_play_button);
    Button rulesButton = (Button)findViewById(R.id.main_rules_button);

    if(playButton != null){
      playButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          // TODO: 9/15/2016 start activity - game
          Intent intent = new Intent(MainActivity.this, GameActivity.class);
          MainActivity.this.startActivity(intent);
        }
      });
    }
    else{
      // TODO: 9/15/2016 create button and inject into R.id.main_linearlayout
    }

    if(rulesButton == null){
      // TODO: 9/15/2016 create button and inject into R.id.main_linearlayout
    }
    else{
      rulesButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent intent = new Intent(MainActivity.this, RulesActivity.class);
          MainActivity.this.startActivity(intent);
        }
      });
    }
  } // END METHOD _initButtons
}
