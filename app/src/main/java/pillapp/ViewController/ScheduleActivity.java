package pillapp.ViewController;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import pillapp.Model.Alarm;
import pillapp.Model.PillBox;

import teamqitalach.pillapp.R;
public class ScheduleActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Rangkuman");

        TableLayout stk = (TableLayout) findViewById(R.id.table_calendar);

        PillBox pillBox = new PillBox();

        List<Alarm> alarms = null;

        List<String> days = Arrays.asList("Minggu", "Senin", "Selasa",
                "Rabu", "Kamis", "Jumat", "Sabtu");

        int color = getResources().getColor(R.color.blue600);

        for (int i = 1; i < 8; i++) {

            String day = days.get(i-1);
            TableRow headerRow = new TableRow(this);
            TextView headerText = new TextView(this);

            headerText.setText(day);
            headerText.setTextColor(Color.WHITE);
            headerText.setPadding(30, 0, 0, 0);
            headerText.setTypeface(null, Typeface.BOLD);
            headerText.setGravity(Gravity.CENTER);

            headerRow.addView(headerText);
            headerRow.setBackgroundColor(color);
            stk.addView(headerRow);


            TableRow.LayoutParams params = (TableRow.LayoutParams)headerText.getLayoutParams();
            params.span = 2;
            headerText.setLayoutParams(params);

            try {
                alarms = pillBox.getAlarms(this, i);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            if(alarms.size() != 0) {
                for(Alarm alarm: alarms) {
                    TableRow tbrow = new TableRow(this);

                    TextView t1v = new TextView(this);
                    t1v.setText(alarm.getPillName());
                    t1v.setMaxEms(6);
                    t1v.setGravity(Gravity.CENTER);
                    tbrow.addView(t1v);

                    TextView t2v = new TextView(this);

                    String time = alarm.getStringTime();
                    t2v.setText(time);
                    t2v.setGravity(Gravity.CENTER);
                    tbrow.addView(t2v);

                    stk.addView(tbrow);
                }
            } else {
                TableRow tbrow = new TableRow(this);
                TextView tv = new TextView(this);
                tv.setGravity(Gravity.CENTER);
                tv.setText("Anda Tidak Mempunyai Pengingat Untuk Hari " + day + ".");

                tbrow.addView(tv);
                stk.addView(tbrow);

                //Let tv span two columns
                TableRow.LayoutParams params2 = (TableRow.LayoutParams)tv.getLayoutParams();
                params2.span = 2;
                tv.setLayoutParams(params2);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent returnHome = new Intent(getBaseContext(), MainActivity.class);
        startActivity(returnHome);
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent returnHome = new Intent(getBaseContext(), MainActivity.class);
        startActivity(returnHome);
        finish();
    }
}