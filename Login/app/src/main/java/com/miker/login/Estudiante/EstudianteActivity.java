package com.miker.login.Estudiante;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.miker.login.R;
import com.miker.login.Servicio;
import com.miker.login.ServicioCurso;
import com.miker.login.curso.Curso;

import java.util.ArrayList;

import static com.miker.login.ServicioCurso.LIST_CURSO_URL;

public class EstudianteActivity extends AppCompatActivity {

    private FloatingActionButton btn_insert_update;
    private Button btn_agrega_curso;
    private TextView id;
    private TextView nombre;
    private TextView apellido1;
    private TextView apellido2;
    private Spinner cursos;
    private ArrayList<Curso> cursosEstudiante;
    private Estudiante estudiante = new Estudiante();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Components Matching
        try {
            matching();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Prepare view
        try {
            prepare_datas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void matching() throws Exception {
        // view
        setContentView(R.layout.activity_estudiante);
        //textview
        String list = Servicio.run(LIST_CURSO_URL);
        id = findViewById(R.id.identificación);
        nombre = findViewById(R.id.nombre);
        apellido1 = findViewById(R.id.apellido1);
        apellido2 = findViewById(R.id.apellido2);
        cursos = (Spinner) findViewById(R.id.cursos);
        cursos.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_item, ServicioCurso.list(list)));

        //button
        btn_insert_update = findViewById(R.id.btn_insert_update);
        btn_insert_update.show();
        btn_agrega_curso = findViewById(R.id.btn_insert_update);
    }

    private void load_estudiante(Estudiante estudiante) throws Exception {
        String list = Servicio.run(LIST_CURSO_URL);
        this.estudiante.setId(estudiante.getId());
        nombre.setText(estudiante.getNombre());
        apellido1.setText(estudiante.getApellido1());
        apellido2.setText(estudiante.getApellido2());
        //Agregar la lista de cursos de estudiantes
      //  cursos.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_item, ServicioCurso.list(list)));
     }

    private void clean() {
        id.setText("");
        nombre.setText("");
        apellido1.setText("");
        apellido2.setText("");
    }

    private void prepare_datas() throws Exception {
        Bundle extras = getIntent().getExtras();
        btn_agrega_curso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregaCurso();
            }
        });
        if (extras != null) {
            load_estudiante((Estudiante) getIntent().getSerializableExtra("object"));
            btn_insert_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    insert_update("update");
                }
            });
        } else {
            clean();
            btn_insert_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    insert_update("insert");
                }
            });
        }
    }

    private void insert_update(String type) {
        if (validateForm()) {
            Intent intent = new Intent(getBaseContext(), EstudianteActivity.class);
            intent.putExtra(type, estudiante);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    private void agregaCurso(){

            cursosEstudiante.add((Curso)cursos.getSelectedItem());

    }

    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.id.getText())) {
            id.setError("Id requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.nombre.getText())) {
            nombre.setError("Nombre requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.apellido1.getText())) {
            apellido1.setError("Primer apellido requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.apellido2.getText())) {
            apellido2.setError("Segundo apellido requerido");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Algunos errores", Toast.LENGTH_LONG).show();
            return false;
        } else {
            estudiante.setId(id.getText().toString());
            estudiante.setNombre(nombre.getText().toString());
            estudiante.setApellido1(apellido1.getText().toString());
            estudiante.setApellido2(apellido2.getText().toString());
        }
        return true;
    }


    private void setSelected(Spinner spinner, String[] options, String option) {
        int aux = 0;
        for (String o : options) {
            if (o.equals(option)) {
                spinner.setSelection(aux);
                break;
            }
            aux++;
        }
    }
}
