package com.example.curdsqlite;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //    EditText editName,editSurname,editMarks ,editTextId;
//    Button btnAddData;
//    Button btnviewAll;
//    Button btnDelete;

    String TAG = MainActivity.class.getName();
    EditText editText_name, editText_surname, editText_Marks, editText_id;
    Button button_add, button_viewAll, button_update, button_delete;

    DatabaseHelper myDb;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(getApplicationContext());


        editText_name = findViewById(R.id.editText_name);
        editText_surname = findViewById(R.id.editText_surname);
        editText_Marks = findViewById(R.id.editText_Marks);
        editText_id = findViewById(R.id.editText_id);
        button_add = findViewById(R.id.button_add);
        button_viewAll = findViewById(R.id.button_viewAll);
        button_update = findViewById(R.id.button_update);
        button_delete = findViewById(R.id.button_delete);
        AddData();


        viewAll();
        UpdateData();
        DeleteData();
    }

    public void DeleteData() {
        button_delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editText_id.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void UpdateData() {
        button_update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editText_id.getText().toString(),
                                editText_name.getText().toString(),
                                editText_surname.getText().toString(), editText_Marks.getText().toString());
                        if (isUpdate == true)
                            Toast.makeText(MainActivity.this, "Data Update", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void AddData() {
        button_add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (TextUtils.isEmpty(editText_name.getText().toString())) {
                            Toast.makeText(MainActivity.this, "Enter EditText Name", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(editText_surname.getText().toString())) {
                            Toast.makeText(MainActivity.this, "Enter EditText Name", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(editText_Marks.getText().toString())) {
                            Toast.makeText(MainActivity.this, "Enter EditText Name", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Log.e(TAG, editText_id.getText().toString() + "=====" + editText_surname.getText().toString() + "=======" + editText_Marks.getText().toString());


                        boolean isInserted = myDb.insertData(getApplicationContext(), editText_id.getText().toString(), editText_surname.getText().toString(), editText_Marks.getText().toString());


                        if (isInserted == true)
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();


                    }
                }
        );
    }

    public void viewAll() {
        button_viewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("Surname :" + res.getString(2) + "\n");
                            buffer.append("Marks :" + res.getString(3) + "\n\n");
                        }

                        // Show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);


        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_setting) {



            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

