package yb.emojidiary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by YB on 9/30/17.
 */

public class database extends SQLiteOpenHelper {

    Context context;
    public database(Context context){
        super(context, "diary_data",null,1);
        this.context = context;
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table diary (Date Integer unique, EmoticonName Text, EmoticonName2 Text, EmoticonName3 Text)");
    }


    public void add(Calendar time, String str, String str2, String str3){
        time.set(Calendar.HOUR_OF_DAY, 0);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);
        Long t = time.getTimeInMillis();

        try{
            getWritableDatabase().execSQL("Insert into diary values(?, ?, ?, ?)", new Object[]{t, str, str2, str3});
        }catch(SQLiteConstraintException e){
            Toast.makeText(context,"Cannot add diary more than once on the same day",Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<datadiary> select(){
        Cursor c = getReadableDatabase().rawQuery("Select * from diary Order by date desc",null);
        ArrayList<datadiary> list = new ArrayList<datadiary>();
        while(c.moveToNext()){
            long i = c.getLong(0);
            String str = c.getString(1);
            String str2= c.getString(2);
            String str3 = c.getString(3);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(i);
            list.add(new datadiary(calendar, str, str2, str3));
        }
        return list;
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
