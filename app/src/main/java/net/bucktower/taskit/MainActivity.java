package net.bucktower.taskit;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;

import net.bucktower.taskit.tasks.Task;


public class MainActivity extends Activity {

    private EditText mTaskInput = (EditText)findViewById(R.id.task_input);
    private ListView mListView = (ListView)findViewById(R.id.task_list);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initializing Parse
        Parse.initialize(this,
                "csWV1lbGaqSsYrQs0zF5apAV1dIE5POdmhGwvMVn",
                "WNF3VlMUk2Erx4uH0KXltrCSmXaUAxSZPlKz4dOA");
        ParseAnalytics.trackAppOpened(getIntent());
        ParseObject.registerSubclass(Task.class);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createTask(){
        if (mTaskInput.getText().length() > 0){
            Task t = new Task();
            t.setDescription(mTaskInput.getText().toString());
            t.setCompleted(false);
            t.saveEventually();
            mTaskInput.setText("");
        }else{
            Context context = getApplicationContext();
            CharSequence textEmptyInput = "Nothing to add!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, textEmptyInput, duration);
            toast.show();
        }
    }
}
