package com.example.wilki.clashoncampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateQuestFinishActivity extends AppCompatActivity {

    private boolean selected;
    private ImageView prizeSword;
    private ImageView prizeSheild;
    private Button publishQuest;
    private EditText completionMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quest_finish);

        prizeSword = (ImageView) findViewById(R.id.swordPrize);
        prizeSheild = (ImageView) findViewById(R.id.sheildPrize);
        publishQuest = (Button) findViewById(R.id.publishQuestBtn);
        completionMsg = (EditText) findViewById(R.id.editText);
        selected = false;
    }

    public void publishQuest(View view){
        // check user has selected an item
        //check user wants to publish quest
        publishQuest.setBackgroundResource(R.drawable.clicked_button);
        if(!selected){
            Toast noSelectedItem = Toast.makeText(getApplicationContext(), "To publish a quest " +
                    "you must select a prize item.", Toast.LENGTH_LONG);
            noSelectedItem.show();
        } else if(completionMsg.getText().toString().length() <= 0){
            Toast noCompletionMsg = Toast.makeText(getApplicationContext(), "To publish a quest " +
                    "you must specify a quest completion message", Toast.LENGTH_LONG);
            noCompletionMsg.show();
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
        publishQuest.setBackgroundResource(R.drawable.button);
    }

    public void onRestart(){
        publishQuest.setBackgroundResource(R.drawable.button);
    }

    public void swordSelected(View view){
        if(!selected){
            selected = true;
        } else {
            prizeSheild.setBackgroundResource(R.drawable.sheild_border);
        }
        prizeSword.setBackgroundResource(R.drawable.sword_border5_sel);
    }

    public void sheildSelected(View view){
        if(!selected){
            selected = true;
        } else {
            prizeSword.setBackgroundResource(R.drawable.sword_border5);
        }
        prizeSheild.setBackgroundResource(R.drawable.sheild_border_sel);
    }
}
