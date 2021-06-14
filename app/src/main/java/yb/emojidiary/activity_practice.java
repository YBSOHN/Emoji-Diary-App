package yb.emojidiary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by YB on 9/24/17.
 */

public class activity_practice extends AppCompatActivity {
    Context inContext;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduction);

        inContext = this;

        Button a = (Button)findViewById(R.id.introDone);
        a.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {

                SharedPreferences preferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor =  preferences.edit();
                EditText i,j;
                i=(EditText)findViewById(R.id.editText);
                j=(EditText)findViewById(R.id.editText2);

                RadioButton z,v;
                z=(RadioButton)findViewById(R.id.male);
                v=(RadioButton)findViewById(R.id.female);


                String name = i.getText().toString();
                if(name.equals("")){
                    Toast.makeText(inContext, "Please input Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean gender;
                if(z.isChecked() == true){
                    gender = true;
                }else if(v.isChecked() == true){
                    gender = false;
                }else{
                    return;
                }


                String ageStr = j.getText().toString();
                if(ageStr.equals("")){
                    Toast.makeText(inContext,"Please input age",Toast.LENGTH_SHORT).show();
                    return;
                }

                int age =Integer.parseInt("12");

                editor.putString("name", name );
                editor.putBoolean("gender", gender);
                editor.putInt("age", age);

                editor.apply();



                startActivity(new Intent(inContext, RecycleViewActivity.class));
                finish();
            }
        });
    }
}
