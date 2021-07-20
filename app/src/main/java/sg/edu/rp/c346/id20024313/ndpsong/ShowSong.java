package sg.edu.rp.c346.id20024313.ndpsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowSong extends AppCompatActivity {
    Button btnDisplay;
    ListView listView1;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(ShowSong.this);
        al.clear();
        al.addAll(dbh.getAllSong());
        aa.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        btnDisplay = findViewById(R.id.btnDisplay);
        listView1 = findViewById(R.id.lvSongs);

        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, al);
        listView1.setAdapter(aa);
        aa.notifyDataSetChanged();
        DBHelper dbh = new DBHelper(ShowSong.this);
        al.clear();
        al.addAll(dbh.getAllSong());

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ShowSong.this);
                al.clear();
                al.addAll(dbh.getAllSong("5"));
                aa.notifyDataSetChanged();
            }
        });
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = al.get(position);

                Intent i = new Intent(ShowSong.this, UpdateDelete.class);
                i.putExtra("song", song);
                startActivity(i);
            }
        });
        {



        };
    }
}