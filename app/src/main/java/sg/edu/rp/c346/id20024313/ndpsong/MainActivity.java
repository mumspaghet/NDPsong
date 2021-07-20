package sg.edu.rp.c346.id20024313.ndpsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tvTitle;
    EditText EtTitle;
    TextView tvSingers;
    EditText EtSingers;
    TextView tvYear;
    EditText EtYear;
    TextView tvStars;
    Button btnInsert, btnShow;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTitle = findViewById(R.id.tvTitle);
        EtTitle = findViewById(R.id.etTitle);
        tvSingers = findViewById(R.id.tvSingers);
        EtSingers = findViewById(R.id.etSingers);
        tvYear = findViewById(R.id.tvYear);
        EtYear = findViewById(R.id.etYear);
        tvStars= findViewById(R.id.tvStars);
        btnInsert = findViewById(R.id.btnUpdate);
        btnShow = findViewById(R.id.btnRemove);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);
        rg = findViewById(R.id.RG);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleInput = EtTitle.getText().toString();
                String SingersInput = EtSingers.getText().toString();
                int etYear = Integer.parseInt(EtYear.getText().toString());
                int selected = rg.getCheckedRadioButtonId();
                RadioButton selectedButton = (RadioButton) findViewById(selected);
                int stars = Integer.parseInt(selectedButton.getText().toString());
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong( titleInput, SingersInput, etYear, stars );

            if (inserted_id != -1) {
            Toast.makeText(MainActivity.this, "Insert successful", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "no", Toast.LENGTH_SHORT).show();
            }
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ShowSong.class);
                startActivity(i);
            }

        });
    }
}