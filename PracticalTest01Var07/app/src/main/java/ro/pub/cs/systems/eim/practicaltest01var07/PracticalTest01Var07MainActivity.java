package ro.pub.cs.systems.eim.practicaltest01var07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var07MainActivity extends AppCompatActivity {

    ButtonClickNavigate navigateThere = new ButtonClickNavigate();
    ButtonClickService startservice = new ButtonClickService();

    // servicii
    private IntentFilter intentFilter = new IntentFilter();
    private int serviceStatus = Constants.SERVICE_STOPPED;


    private class ButtonClickService implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (serviceStatus == Constants.SERVICE_STOPPED) {
                Intent intent1 = new Intent(getApplicationContext(), PracticalTest01Var07Service.class);

                getApplicationContext().startService(intent1);
                serviceStatus = Constants.SERVICE_STARTED;
            }
        }
    }


        // intent
    private class ButtonClickNavigate implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String id = ((Button) view).getText().toString();

            String number_one = (((EditText) findViewById(R.id.edittext1)).getText()).toString();
            String number_two = (((EditText) findViewById(R.id.edittext2)).getText()).toString();
            String number_three = (((EditText) findViewById(R.id.edittext3)).getText()).toString();
            String number_four = (((EditText) findViewById(R.id.edittext4)).getText()).toString();

            if (!(number_one.equals("") || number_two.equals("") || number_three.equals("") || number_four.equals(""))) {
                Integer number1 = Integer.parseInt(number_one);
                Integer number2 = Integer.parseInt(number_two);
                Integer number3 = Integer.parseInt(number_three);
                Integer number4 = Integer.parseInt(number_four);

                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07SecondaryActivity.class);
                intent.putExtra("number1", number1);
                intent.putExtra("number2", number2);
                intent.putExtra("number3", number3);
                intent.putExtra("number4", number4);

                startActivityForResult(intent, 2017);
            }
        }
    }

    // service
    // un ascultator cu difuzare
    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("Emi", "messageBroadcast");

            EditText number1 = findViewById(R.id.edittext1);
            EditText number2 = findViewById(R.id.edittext2);
            EditText number3 = findViewById(R.id.edittext3);
            EditText number4 = findViewById(R.id.edittext4);


            number1.setText(String.valueOf(intent.getIntExtra("random1", -1)));
            number2.setText(String.valueOf(intent.getIntExtra("random2", -1)));
            number3.setText(String.valueOf(intent.getIntExtra("random3", -1)));
            number4.setText(String.valueOf(intent.getIntExtra("random4", -1)));

        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    // se opreste serviciul
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var07Service.class);
        stopService(intent);
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        EditText number1 = findViewById(R.id.edittext1);
        EditText number2 = findViewById(R.id.edittext2);
        EditText number3 = findViewById(R.id.edittext3);
        EditText number4 = findViewById(R.id.edittext4);
        savedInstanceState.putString("number1", number1.getText().toString());
        savedInstanceState.putString("number2", number2.getText().toString());
        savedInstanceState.putString("number3", number3.getText().toString());
        savedInstanceState.putString("number4", number4.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_main);

        Button set = findViewById(R.id.set);
        set.setOnClickListener(navigateThere);

        EditText number1 = findViewById(R.id.edittext1);
        EditText number2 = findViewById(R.id.edittext2);
        EditText number3 = findViewById(R.id.edittext3);
        EditText number4 = findViewById(R.id.edittext4);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("number1")) {
                number1.setText(savedInstanceState.getString(("number1")));
            }
            if (savedInstanceState.containsKey("number2")) {
                number2.setText(savedInstanceState.getString(("number2")));
            }
            if (savedInstanceState.containsKey("number3")) {
                number3.setText(savedInstanceState.getString(("number3")));
            }
            if (savedInstanceState.containsKey("number4")) {
                number4.setText(savedInstanceState.getString(("number4")));
            }
        }

        Button service =findViewById(R.id.service);
        service.setOnClickListener(startservice);

        for (int index = 0; index < Constants.actionTypes.length; index++) {
            intentFilter.addAction(Constants.actionTypes[index]);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 2017) {
            Log.d("emi", String.valueOf(resultCode));
            Toast.makeText(this, String.valueOf(resultCode), Toast.LENGTH_LONG).show();
        }
    }
}