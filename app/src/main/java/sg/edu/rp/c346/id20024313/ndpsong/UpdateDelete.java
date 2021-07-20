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

public class UpdateDelete extends AppCompatActivity {
    TextView tvTitle;
    EditText EtTitle;
    TextView tvSingers;
    EditText EtSingers;
    TextView tvYear;
    EditText EtYear;
    TextView tvStars;
    Button btnUpdate, btnRemove, btnCancel;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    RadioGroup rg;
    EditText etID;
    TextView tvID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        tvTitle = findViewById(R.id.tvTitle);
        EtTitle = findViewById(R.id.etTitle);
        tvSingers = findViewById(R.id.tvSingers);
        EtSingers = findViewById(R.id.etSingers);
        tvYear = findViewById(R.id.tvYear);
        EtYear = findViewById(R.id.etYear);
        tvStars = findViewById(R.id.tvStars);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnRemove = findViewById(R.id.btnRemove);
        btnCancel = findViewById(R.id.btnCancel);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);
        rg = findViewById(R.id.RG);
        etID = findViewById(R.id.etID);

        Intent i = getIntent();
        Song song = (Song) i.getSerializableExtra("song");

        etID.setText(song.getId() + "");
        EtTitle.setText(song.getTitle());
        EtSingers.setText(song.getSingers());
        EtYear.setText(song.getYear() + "");
        if (song.getStars() == 1) {
            rb1.setChecked(true);
        } else if (song.getStars() == 2) {
            rb2.setChecked(true);
        } else if (song.getStars() == 3) {
            rb3.setChecked(true);
        } else if (song.getStars() == 4) {
            rb4.setChecked(true);
        } else if (song.getStars() == 5) {
            rb5.setChecked(true);

        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(UpdateDelete.this);
                song.setTitle(EtTitle.getText().toString());
                dbh.updateSong(song);
                dbh.close();

                finish();
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(UpdateDelete.this);
                dbh.deleteSong(song.getId());

                finish();
            }
        });
    }
}