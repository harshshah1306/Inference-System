package com.example.harsh.myapplication1;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class TodoActivity extends ListActivity {
    private ListAdapter todoListAdapter;
    private TodoListSQLHelper todoListSQLHelper;
    int c=1;
    Notificationmaps n=new Notificationmaps();
    EditText a1,b1,c1,d1,a2,b2,c2,d2,a3,b3,c3,d3,a4,b4,c4,d4,a5,b5,c5,d5,a6,b6,c6,d6,a7,b7,c7,d7;
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        updateTodoList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                AlertDialog.Builder todoTaskBuilder = new AlertDialog.Builder(this);
                todoTaskBuilder.setTitle("Add Todo Task Item");
                todoTaskBuilder.setMessage("describe the Todo task...");
                todoTaskBuilder.setView(R.layout.reminder);


                todoTaskBuilder.setPositiveButton("Add Task", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        if(c==1) {
                            a1 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.activity);

                            b1 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.store);
                            c1 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.locations);
                            d1 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.raduis);
                            String todoET = c + "." + a1.getText().toString() + " AT:" + b1.getText().toString() + " IN:" + c1.getText().toString();
                            todoListSQLHelper = new TodoListSQLHelper(TodoActivity.this);
                            SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.clear();

                            //write the Todo task input into database table
                            values.put(TodoListSQLHelper.COL1_TASK, todoET);
                            sqLiteDatabase.insertWithOnConflict(TodoListSQLHelper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);

                            //update the Todo task list UI

                            updateTodoList();

                            Intent intent = new Intent(TodoActivity.this, Notificationmaps.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("activity", a1.getText().toString());
                            bundle.putString("store", b1.getText().toString());
                            bundle.putString("locations", c1.getText().toString());
                            bundle.putString("raduiss", d1.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }

                        if(c==2) {
                            a2 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.activity);

                            b2 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.store);
                            c2 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.locations);
                            d2 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.raduis);
                            String todoET = c + "." + a2.getText().toString() + " AT:" + b2.getText().toString() + " IN:" + c2.getText().toString();
                            todoListSQLHelper = new TodoListSQLHelper(TodoActivity.this);
                            SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.clear();

                            //write the Todo task input into database table
                            values.put(TodoListSQLHelper.COL1_TASK, todoET);
                            sqLiteDatabase.insertWithOnConflict(TodoListSQLHelper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);

                            //update the Todo task list UI

                            updateTodoList();

                            Intent intent = new Intent(TodoActivity.this, Notificationmaps.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("activity", a2.getText().toString());
                            bundle.putString("store", b2.getText().toString());
                            bundle.putString("locations", c2.getText().toString());
                            bundle.putString("raduiss", d2.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        if(c==3) {
                            a3 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.activity);

                            b3 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.store);
                            c3 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.locations);
                            d3 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.raduis);
                            String todoET = c + "." + a3.getText().toString() + " AT:" + b3.getText().toString() + " IN:" + c3.getText().toString();
                            todoListSQLHelper = new TodoListSQLHelper(TodoActivity.this);
                            SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.clear();

                            //write the Todo task input into database table
                            values.put(TodoListSQLHelper.COL1_TASK, todoET);
                            sqLiteDatabase.insertWithOnConflict(TodoListSQLHelper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);

                            //update the Todo task list UI

                            updateTodoList();

                            Intent intent = new Intent(TodoActivity.this, Notificationmaps.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("activity", a3.getText().toString());
                            bundle.putString("store", b3.getText().toString());
                            bundle.putString("locations", c3.getText().toString());
                            bundle.putString("raduiss", d3.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        if(c==4) {
                            a4 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.activity);

                            b4 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.store);
                            c4 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.locations);
                            d4 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.raduis);
                            String todoET = c + "." + a4.getText().toString() + " AT:" + b4.getText().toString() + " IN:" + c4.getText().toString();
                            todoListSQLHelper = new TodoListSQLHelper(TodoActivity.this);
                            SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.clear();

                            //write the Todo task input into database table
                            values.put(TodoListSQLHelper.COL1_TASK, todoET);
                            sqLiteDatabase.insertWithOnConflict(TodoListSQLHelper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);

                            //update the Todo task list UI

                            updateTodoList();

                            Intent intent = new Intent(TodoActivity.this, Notificationmaps.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("activity", a4.getText().toString());
                            bundle.putString("store", b4.getText().toString());
                            bundle.putString("locations", c4.getText().toString());
                            bundle.putString("raduiss", d4.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        if(c==5) {
                            a5 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.activity);

                            b5 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.store);
                            c5 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.locations);
                            d5 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.raduis);
                            String todoET = c + "." + a5.getText().toString() + " AT:" + b5.getText().toString() + " IN:" + c5.getText().toString();
                            todoListSQLHelper = new TodoListSQLHelper(TodoActivity.this);
                            SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.clear();

                            //write the Todo task input into database table
                            values.put(TodoListSQLHelper.COL1_TASK, todoET);
                            sqLiteDatabase.insertWithOnConflict(TodoListSQLHelper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);

                            //update the Todo task list UI

                            updateTodoList();

                            Intent intent = new Intent(TodoActivity.this, Notificationmaps.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("activity", a5.getText().toString());
                            bundle.putString("store", b5.getText().toString());
                            bundle.putString("locations", c5.getText().toString());
                            bundle.putString("raduiss", d5.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        if(c==6) {
                            a6 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.activity);

                            b6 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.store);
                            c6 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.locations);
                            d6 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.raduis);
                            String todoET = c + "." + a6.getText().toString() + " AT:" + b6.getText().toString() + " IN:" + c6.getText().toString();
                            todoListSQLHelper = new TodoListSQLHelper(TodoActivity.this);
                            SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.clear();

                            //write the Todo task input into database table
                            values.put(TodoListSQLHelper.COL1_TASK, todoET);
                            sqLiteDatabase.insertWithOnConflict(TodoListSQLHelper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);

                            //update the Todo task list UI

                            updateTodoList();

                            Intent intent = new Intent(TodoActivity.this, Notificationmaps.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("activity", a6.getText().toString());
                            bundle.putString("store", b6.getText().toString());
                            bundle.putString("locations", c6.getText().toString());
                            bundle.putString("raduiss", d6.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        if(c==7) {
                            a7 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.activity);

                            b7 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.store);
                            c7 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.locations);
                            d7 = (EditText) ((AlertDialog)
                                    dialogInterface).findViewById(R.id.raduis);
                            String todoET = c + "." + a7.getText().toString() + " AT:" + b7.getText().toString() + " IN:" + c7.getText().toString();
                            todoListSQLHelper = new TodoListSQLHelper(TodoActivity.this);
                            SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.clear();

                            //write the Todo task input into database table
                            values.put(TodoListSQLHelper.COL1_TASK, todoET);
                            sqLiteDatabase.insertWithOnConflict(TodoListSQLHelper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);

                            //update the Todo task list UI

                            updateTodoList();

                            Intent intent = new Intent(TodoActivity.this, Notificationmaps.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("activity", a7.getText().toString());
                            bundle.putString("store", b7.getText().toString());
                            bundle.putString("locations", c7.getText().toString());
                            bundle.putString("raduiss", d7.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }

                        c++;
                    }
                });

                todoTaskBuilder.setNegativeButton("Cancel", null);

                todoTaskBuilder.create().show();
                return true;

            default:
                return false;
        }
    }

    //update the todo task list UI
    private void updateTodoList() {
        todoListSQLHelper = new TodoListSQLHelper(TodoActivity.this);
        SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getReadableDatabase();

        //cursor to read todo task list from database
        Cursor cursor = sqLiteDatabase.query(TodoListSQLHelper.TABLE_NAME,
                new String[]{TodoListSQLHelper._ID, TodoListSQLHelper.COL1_TASK},
                null, null, null, null, null);

        //binds the todo task list with the UI
        todoListAdapter = new SimpleCursorAdapter(
                this,R.layout.todotask,cursor,
                new String[]{TodoListSQLHelper.COL1_TASK},
                new int[]{R.id.todoTaskTV},
                0
        );

        this.setListAdapter(todoListAdapter);
    }

    //closing the todo task item
    public void onDoneButtonClick(View view) {
        View v = (View) view.getParent();
        TextView todoTV = (TextView) v.findViewById(R.id.todoTaskTV);
        String todoTaskItem = todoTV.getText().toString();
        String deleteTodoItemSql = "DELETE FROM " + TodoListSQLHelper.TABLE_NAME +
                " WHERE " + TodoListSQLHelper.COL1_TASK + " = '" + todoTaskItem + "'";
        todoListSQLHelper = new TodoListSQLHelper(TodoActivity.this);
        SQLiteDatabase sqlDB = todoListSQLHelper.getWritableDatabase();
        sqlDB.execSQL(deleteTodoItemSql);
        updateTodoList();
    }
    public void Click(View view) {

        View v = (View) view.getParent();
        TextView todoTV1 = (TextView) v.findViewById(R.id.todoTaskTV);
        String task=todoTV1.getText().toString();
        todoTV1.setTextColor(R.drawable.green);
        todoTV1.setText(task+"\n"+"TASK COMPLETED");
    }
    public void Click1(View view) {

        View v = (View) view.getParent();
        TextView todoTV2 = (TextView) v.findViewById(R.id.todoTaskTV);
        String check = todoTV2.getText().toString();
        Intent intent = new Intent(TodoActivity.this, Notificationmaps.class);
        Bundle bundle = new Bundle();
        char[] ch=check.toCharArray();

        if (ch[0]=='1') {


            bundle.putString("activity", a1.getText().toString());
            bundle.putString("store", b1.getText().toString());
            bundle.putString("locations", c1.getText().toString());
            bundle.putString("raduiss", d1.getText().toString());

            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (ch[0]=='2') {



            bundle.putString("activity", a2.getText().toString());
            bundle.putString("store", b2.getText().toString());
            bundle.putString("locations", c2.getText().toString());
            bundle.putString("raduiss", d2.getText().toString());

            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (ch[0]=='3') {


            bundle.putString("activity", a3.getText().toString());
            bundle.putString("store", b3.getText().toString());
            bundle.putString("locations", c3.getText().toString());
            bundle.putString("raduiss", d3.getText().toString());

            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (ch[0]=='4') {


            bundle.putString("activity", a4.getText().toString());
            bundle.putString("store", b4.getText().toString());
            bundle.putString("locations", c4.getText().toString());
            bundle.putString("raduiss", d4.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (ch[0]=='5') {
            bundle.putString("activity", a5.getText().toString());
            bundle.putString("store", b5.getText().toString());
            bundle.putString("locations", c5.getText().toString());
            bundle.putString("raduiss", d5.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (ch[0]=='6') {
            bundle.putString("activity", a6.getText().toString());
            bundle.putString("store", b6.getText().toString());
            bundle.putString("locations", c6.getText().toString());
            bundle.putString("raduiss", d6.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (ch[0]=='7') {
            bundle.putString("activity", a7.getText().toString());
            bundle.putString("store", b7.getText().toString());
            bundle.putString("locations", c7.getText().toString());
            bundle.putString("raduiss", d7.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);

        }
    }
  public void scheduleAlarm(View V)
    {

             Long time = new GregorianCalendar().getTimeInMillis() + 120000*15;
             Intent intentAlarm = new Intent(this, AlarmReciever.class);
             AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
             alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
             Toast.makeText(this, "alarm scheduled", Toast.LENGTH_LONG).show();
    }
}