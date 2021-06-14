package yb.emojidiary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

/**
 * Created by YB on 10/1/17.
 */

public class emoticon extends AppCompatActivity {
    final float FALSE_ALPHA = 0.5f;
    int page=-1;

    Context inContext;

    ToggleButton[] btnWeather = new ToggleButton[9];
    ToggleButton[] btnActivity = new ToggleButton[16];
    ToggleButton[] btnFeeling = new ToggleButton[20];


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnew);

        inContext = this;
        Button nextButton = (Button)findViewById(R.id.newAddNext);
        Button prevButton = (Button)findViewById(R.id.newAddPrev);

        for(int i = 0; i < btnFeeling.length; ++ i) {
            btnFeeling[i] = (ToggleButton) findViewById(R.id.emoticonToggle0 + i);
            btnFeeling[i].setChecked(false);
            btnFeeling[i].setAlpha(FALSE_ALPHA);
        }

        for(int i = 0; i < btnWeather.length; ++ i) {
            btnWeather[i] = (ToggleButton) findViewById(R.id.weatherToggle0 + i);
            btnWeather[i].setChecked(false);
            btnWeather[i].setAlpha(FALSE_ALPHA);
        }

        for(int i = 0; i < btnActivity.length; ++ i) {
            btnActivity[i] = (ToggleButton) findViewById(R.id.activityToggle0 + i);
            btnActivity[i].setChecked(false);
            btnActivity[i].setAlpha(FALSE_ALPHA);
        }


        prevButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if(page==0){
                    finish();
                }

                if(page==1){
                    View layout = findViewById(R.id.addnew_activitylayout);
                    layout.setVisibility(View.VISIBLE);
                }

                if(page==2){
                    View layout = findViewById(R.id.addnew_emoticonlayout);
                    layout.setVisibility(View.VISIBLE);
                }
                page--;
            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                if(page==0){
                    if(checkButton(btnWeather)==false){
                        return;
                    }
                    View layout = findViewById(R.id.addnew_weatherLayout);
                    layout.setVisibility(View.INVISIBLE);

                }else if(page==1){
                    if(checkButton(btnActivity)==false){
                        return;
                    }
                    View layout = findViewById(R.id.addnew_activitylayout);
                    layout.setVisibility(View.INVISIBLE);
                    
                }else if(page==2){
                    if(checkButton(btnFeeling)==false){
                        return;
                    }
                    saveDiary();
                }
                page++;


            }
        });
    }
    
    private boolean checkButton(ToggleButton[] list){
        for (int i = 0;i<list.length;i++){
            if(list[i].isChecked() == true){
                return true;
            }
        }

        Toast.makeText(inContext, "Choose ", Toast.LENGTH_SHORT).show();
        return false;
    }


    public void saveDiary(){
        String emoticon1 = "";
        String emoticon2 = "";
        String emoticon3 = "";

        for (int i = 0; i < btnWeather.length; ++i){
            if(btnWeather[i].isChecked() == true){
                emoticon1 += i + ",";
            }
        }
        for (int i = 0; i < btnActivity.length; ++i){
            if(btnActivity[i].isChecked() == true){
                emoticon2 += i + ",";
            }
        }
        for (int i = 0; i < btnFeeling.length; ++i){
            if(btnFeeling[i].isChecked() == true){
                emoticon3 += i + ",";
            }
        }

        emoticon1 = emoticon1.substring(0,emoticon1.length()-1);
        emoticon2 = emoticon2.substring(0,emoticon2.length()-1);
        emoticon3 = emoticon3.substring(0,emoticon3.length()-1);
        database db = new database(inContext);
        db.add(Calendar.getInstance(), emoticon1, emoticon2, emoticon3);
        startActivity(new Intent(inContext, RecycleViewActivity.class));
    }

    public void emoticonToggle(View v){
        ToggleButton btn = (ToggleButton) v;
        btn.isChecked();
        if(btn.isChecked()==true){
            btn.setAlpha(1f);
        }
        else{
            btn.setAlpha(FALSE_ALPHA);

        }

    }

    public void layoutClick(View v){

    }
}
