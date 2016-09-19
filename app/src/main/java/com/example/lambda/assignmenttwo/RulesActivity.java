package com.example.lambda.assignmenttwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by lambda on 9/18/2016.
 */
public class RulesActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rules);

    Button button = (Button)findViewById(R.id.ok_button_rules);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(RulesActivity.this, MainActivity.class);
        RulesActivity.this.startActivity(intent);
      }
    });
  }
}
