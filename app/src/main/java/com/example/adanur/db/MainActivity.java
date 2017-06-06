package com.example.adanur.db;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
        Databasehelper myDb;
        EditText editName,editSurname,editMarks ,editTextId;
        Button btnAddData;
        Button btnviewAll;
        Button btnDelete;

        Button btnviewUpdate;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new Databasehelper(this);

        editName = (EditText)findViewById(R.id.editText_name);
        editSurname = (EditText)findViewById(R.id.editText_surname);
        editMarks = (EditText)findViewById(R.id.editText_Marks);
        editTextId = (EditText)findViewById(R.id.editText_id);
        btnAddData = (Button)findViewById(R.id.button_add);
        btnviewAll = (Button)findViewById(R.id.view);
    btnviewUpdate= (Button)findViewById(R.id.button_update);
        btnDelete= (Button)findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
        }
public void DeleteData() {
        btnDelete.setOnClickListener(
        new View.OnClickListener() {
@Override
public void onClick(View v) {
        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
        if(deletedRows > 0)
        Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
        else
        Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
        }
        }
        );
        }
public void UpdateData() {
        btnviewUpdate.setOnClickListener(
        new View.OnClickListener() {
@Override
public void onClick(View v) {
        boolean isUpdate = myDb.updateData(editTextId.getText().toString(),
                editName.getText().toString(),
                editSurname.getText().toString(), editMarks.getText().toString());
        if(isUpdate == true)
        Toast.makeText(MainActivity.this,"Data Update",Toast.LENGTH_LONG).show();
        else
        Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
        }
        }
        );
        }
public  void AddData() {
        btnAddData.setOnClickListener(
        new View.OnClickListener() {
@Override
public void onClick(View v) {
        boolean isInserted = myDb.insertData(editName.getText().toString(),
                editSurname.getText().toString(),
                editMarks.getText().toString());
        if(isInserted == true)
        Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
        else
        Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
        }
        }
        );
        }
public void viewAll(){
        btnviewAll.setOnClickListener(
                new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            Cursor cur=myDb.getdata();
                            if(cur.getCount()==0){
                                showdata("error","nothing found");

                            }
                            StringBuffer buf=new StringBuffer();
                            while(cur.moveToNext()){
                                buf.append("name:"+cur.getString(1)+"\n");
                                buf.append("surname:"+cur.getString(2)+"\n");
                                buf.append("marks:"+cur.getString(3)+"\n");
                                buf.append("id:"+cur.getString(0)+"\n\n");

                            }
                            showdata("data",buf.toString());

                        }
                }
        );
}

public void showdata(String title,String message){
    AlertDialog.Builder b=new AlertDialog.Builder(this);
    b.setCancelable(true);
    b.setTitle(title);
    b.setMessage(message);
    b.show();
}

}
