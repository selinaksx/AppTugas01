package id.sch.smktelkom_mlg.tugas01.xirpl3032.apptugas01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    EditText etName;
    EditText etInst;
    EditText etEmail;
    Button bOK;
    RadioButton rbM, rbF;
    TextView tvHasil1, tvHasil2, tvHasil3, tvKelas, tvHasil4;
    CheckBox cbB, cbM, cbC, cbP;
    Spinner spGr, spTk;
    int nKelas;
    TextView tvTest;

    String[][] arKelas = {{"VII", "VIII", "IX"}, {"X", "XI", "XII"}};
    ArrayList<String> listKelas = new ArrayList<>();
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etName = (EditText) findViewById(R.id.editTextName);
        etInst = (EditText) findViewById(R.id.editTextInst);
        etEmail = (EditText) findViewById(R.id.editTextEmail);
        bOK = (Button) findViewById(R.id.buttonOK);
        rbM = (RadioButton) findViewById(R.id.radioButtonM);
        rbF = (RadioButton) findViewById(R.id.radioButtonF);
        tvHasil1 = (TextView) findViewById(R.id.textViewHasil);
        tvHasil2 = (TextView) findViewById(R.id.textViewHasil2);
        tvHasil3 = (TextView) findViewById(R.id.textViewHasil3);
        tvHasil4 = (TextView) findViewById(R.id.textViewHasil4);
        tvKelas = (TextView) findViewById(R.id.textViewKelas);
        cbB = (CheckBox) findViewById(R.id.checkBoxBio);
        cbM = (CheckBox) findViewById(R.id.checkBoxMath);
        cbC = (CheckBox) findViewById(R.id.checkBoxChemist);
        cbP = (CheckBox) findViewById(R.id.checkBoxPhy);
        spGr = (Spinner) findViewById(R.id.spinnerGr);
        spTk = (Spinner) findViewById(R.id.spinnerTk);

        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doProcess();
            }
        });


        cbB.setOnCheckedChangeListener(this);
        cbM.setOnCheckedChangeListener(this);
        cbC.setOnCheckedChangeListener(this);
        cbP.setOnCheckedChangeListener(this);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listKelas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTk.setAdapter(adapter);


        spGr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                listKelas.clear();
                listKelas.addAll(Arrays.asList(arKelas[pos]));
                adapter.notifyDataSetChanged();
                spTk.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void doProcess() {
        if (isValid()) {
            String name = etName.getText().toString();
            String inst = etInst.getText().toString();
            String email = etEmail.getText().toString();

            tvHasil1.setText("Nama anda " + name + " bersekolah di " + inst + " email anda " + email);
        }

    }

    private boolean isValid() {
        String hasil3 = "Anda Memilih Kelas:\n";
        int startlen = hasil3.length();
        if (cbB.isChecked()) hasil3 += cbB.getText() + "\n";
        if (cbM.isChecked()) hasil3 += cbM.getText() + "\n";
        if (cbC.isChecked()) hasil3 += cbC.getText() + "\n";
        if (cbP.isChecked()) hasil3 += cbP.getText() + "\n";

        if (hasil3.length() == startlen) hasil3 += "Tidak ada pada pilihan";

        tvHasil4.setText("" + hasil3);
        tvHasil3.setText("Grade Anda " + spGr.getSelectedItem().toString() + " Kelas " + spTk.getSelectedItem().toString());
        boolean valid = true;

        String name = etName.getText().toString();
        String inst = etInst.getText().toString();
        String email = etEmail.getText().toString();

        if (name.isEmpty()) {
            etName.setError("Nama Belum Diisi");
            valid = false;
        } else if (name.length() < 3) {
            etName.setError("Nama Minimal 3 Karakter");
            valid = false;
        } else {
            etName.setError(null);
        }
        if (inst.isEmpty()) {
            etInst.setError("Sekolah belum diisi");
            valid = false;
        } else {
            etInst.setError(null);
        }
        if (email.isEmpty()) {
            etEmail.setError("Email belum diisi");
        } else {
            etEmail.setError(null);
        }
        return valid;

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) nKelas += 1;
        else nKelas -= 1;

        tvKelas.setText("Kelas (" + nKelas + " terpilih)");

    }
}
