package com.example.wilki.clashoncampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class QuestInventoryActivity extends AppCompatActivity {

    private int windowWidth;
    private int windowHeight;
    private RelativeLayout.LayoutParams layoutParams;
    private ImageView swordView;
    private Button closeInvBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_inventory);

        swordView = (ImageView) findViewById(R.id.swordImage);
        windowWidth = getWindowManager().getDefaultDisplay().getWidth();
        windowHeight = getWindowManager().getDefaultDisplay().getHeight();
        swordView.setOnTouchListener(new InvImageListener());
        closeInvBtn = (Button) findViewById(R.id.backMainMenuBtn);
    }

    public void closeInventory(View view){
        closeInvBtn.setBackgroundResource(R.drawable.clicked_button);
        startActivity(new Intent(this, QuestMapActivity.class));
    }

    public void onRestart(){
        closeInvBtn.setBackgroundResource(R.drawable.button);
    }

    private class InvImageListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            switch(motionEvent.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    int x_cord = (int)motionEvent.getRawX();
                    int y_cord = (int)motionEvent.getRawY();

                    if(x_cord>windowWidth){x_cord=windowWidth;}
                    if(y_cord>windowHeight){y_cord=windowHeight;}

                    layoutParams.leftMargin = x_cord -25;
                    layoutParams.topMargin = y_cord - 75;

                    view.setLayoutParams(layoutParams);
                    break;
                default:
                    break;
            }

            return true;
        }
    }
}
