package saulmm.swypesample.swype4.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] items = new String[]{
                "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve"
        };

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (SwipeRefreshLayout) findViewById(R.id.swype);
        layout.setOnRefreshListener(this);

        // Set the refresh swype color scheme
        layout.setColorScheme(
                R.color.swype_1,
                R.color.swype_2,
                R.color.swype_3,
                R.color.swype_4);

        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
    }

    @Override
    public void onRefresh() {
        // I create a handler to stop the refresh and show a message after 3s
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.setRefreshing(false);
                Toast.makeText(MainActivity.this, "Cool !", Toast.LENGTH_LONG).show();
            }

        }, 3000);


    }
}