package yb.emojidiary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import yb.emojidiary.R;

import java.util.ArrayList;

/**
 * Created by YB on 9/25/17.
 */

public class RecycleViewActivity extends AppCompatActivity{
    Context inContext;
    int sum=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview);

        inContext = this;

        Button c = (Button)findViewById(R.id.plus);
        c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(inContext, emoticon.class));
            }
        });

        RecyclerView rv = (RecyclerView) findViewById(R.id.recycle);
        database db = new database(this);
        ArrayList<datadiary>List=db.select();
        RecyclerViewAdapter ra = new RecyclerViewAdapter(this,List);
        rv.setAdapter(ra);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
