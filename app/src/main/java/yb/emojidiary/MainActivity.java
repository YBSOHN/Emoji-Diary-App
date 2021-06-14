package yb.emojidiary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import yb.emojidiary.R;


public class MainActivity extends AppCompatActivity {
    Context inContext;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String name = preferences.getString("name", "");
        if(!name.equals("")){
            startActivity(new Intent(this, RecycleViewActivity.class));
            finish();
        }

        setContentView(R.layout.activity_main);
        Button b = (Button)findViewById(R.id.mainBegin);
        inContext=this;
        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(new Intent(inContext, activity_practice.class));
            }
        });


    }
}