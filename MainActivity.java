package com.jmj.mariejulio.mycontacts;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ListView lv;

    CustomAdapter adapter;

    ArrayList<Contact> arrayList = new ArrayList<Contact>();

    String[] noms = new String[]{"AVE","REGINA","GOMEZ"};
    String[] prenoms = new String[]{"MARIA","CAELI","Merveille"};

//    private EditText addnom;
//    private EditText addprenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv = (ListView) findViewById(R.id.list);
        getDataInList();
        adapter = new CustomAdapter(MainActivity.this, arrayList);
        lv.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addName();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edit(position);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                delete(position);
               return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void getDataInList() {
        for (int i = 0; i < noms.length; i++) {

//            Contact contacts = new Contact();
//            contacts.setNom(noms[i]);
//            contacts.setPrenom(prenoms[i]);

            arrayList.add(new Contact(noms[i],prenoms[i]));
        }
    }

    public void addName() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog);

        final EditText editTextnom = (EditText) dialog.findViewById(R.id.add_name);
        final EditText editTextPrenom = (EditText) dialog.findViewById(R.id.add_prenom);
        Button addbt = dialog.findViewById(R.id.addbt);
        addbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(editTextnom.getText().toString().equals("") && editTextPrenom.getText().toString().equals(""))) {
                    Contact contact = new Contact(editTextnom.getText().toString(), editTextPrenom.getText().toString());
                    arrayList.add(contact);
                    adapter.notifyDataSetChanged();
                    Toast(getResources().getString(R.string.addsuc));
                    editTextnom.setText("");
                    editTextPrenom.setText("");
                }else {
                    Toast(getResources().getString(R.string.addfail));
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void Toast(String msg){
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
    }

    public void edit(final  int position) {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog);

        final EditText editTextnom = (EditText) dialog.findViewById(R.id.add_name);
        final EditText editTextPrenom = (EditText) dialog.findViewById(R.id.add_prenom);

        editTextnom.setText(arrayList.get(position).getNom());
        editTextPrenom.setText(arrayList.get(position).getPrenom());

        Button addbt = dialog.findViewById(R.id.addbt);
        addbt.setText(getResources().getString(R.string.editbt));
        addbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(editTextnom.getText().toString().equals("") && editTextPrenom.getText().toString().equals(""))) {
                    arrayList.get(position).setNom(editTextnom.getText().toString());
                    arrayList.get(position).setPrenom(editTextPrenom.getText().toString());
                    adapter.notifyDataSetChanged();
                    Toast(getResources().getString(R.string.editsuccess));
                    dialog.dismiss();
                } else {
                    Toast(getResources().getString(R.string.editfail));
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void delete(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(getResources().getString(R.string.del))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arrayList.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast(getResources().getString(R.string.delsuc));
                    }
                });
        builder.create().show();
    }
}
