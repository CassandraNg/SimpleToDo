package sg.edu.rp.c346.id20017476.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spnAddRemove;
    EditText etTask;
    Button btnAdd, btnDelete, btnClear;
    ListView lvTask;
    ArrayList<String> alTask;
    ArrayAdapter<String> aaTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnAddRemove = findViewById(R.id.spinnerAddRemove);
        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDelete = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);
        lvTask = findViewById(R.id.listViewTasks);

        alTask = new ArrayList<String>();

        aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTask);
        lvTask.setAdapter(aaTask);

        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        etTask.setHint("Type in a new task here");
                        btnAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (etTask.length() != 0){
                                    String newTask = etTask.getText().toString();
                                    alTask.add(newTask);
                                    aaTask.notifyDataSetChanged();
                                    etTask.setText("");
                                }else{
                                    Toast.makeText(MainActivity.this, "Input cannot be empty", Toast.LENGTH_LONG).show();
                                }

                            }
                        });
                        btnClear.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alTask.clear();
                                aaTask.notifyDataSetChanged();
                            }
                        });
                        break;
                    case 1:
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);
                        etTask.setHint("Type in the index of the task to be removed");
                        btnDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (alTask.size() == 0){
                                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_LONG).show();
                                }else{
                                    if(etTask.length() != 0){
                                        int positionTask = Integer.parseInt(etTask.getText().toString());
                                        if(positionTask > (alTask.size()-1)){
                                            Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_LONG).show();
                                        }else{
                                            alTask.remove(positionTask);
                                            aaTask.notifyDataSetChanged();
                                            etTask.setText("");
                                        }
                                    }else{
                                        Toast.makeText(MainActivity.this, "Input cannot be empty", Toast.LENGTH_LONG).show();
                                    }

                                }

                            }
                        });
                        btnClear.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alTask.clear();
                                aaTask.notifyDataSetChanged();
                            }
                        });
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
