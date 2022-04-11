package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import ro.pub.cs.systems.eim.practicaltest01var07.databinding.ActivityPracticalTest01Var07SecondaryBinding;

public class PracticalTest01Var07SecondaryActivity extends AppCompatActivity {

    ButtonClickListener listener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String id = ((Button) view).getText().toString();

            Integer number1 = Integer.parseInt((((EditText) findViewById(R.id.edittextsecondary1)).getText()).toString());;
            Integer number2 = Integer.parseInt((((EditText) findViewById(R.id.edittextsecondary2)).getText()).toString());;
            Integer number3 = Integer.parseInt((((EditText) findViewById(R.id.edittextsecondary3)).getText()).toString());;
            Integer number4 = Integer.parseInt((((EditText) findViewById(R.id.edittextsecondary4)).getText()).toString());;

            if (id.equals("Sum")) {
                Integer sum = number1 + number2 + number3 + number4;
                setResult(sum);
            } else {
                Integer product = number1 * number2 * number3 * number4;
                setResult(product);
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_secondary);

        EditText number1 = findViewById(R.id.edittextsecondary1);
        EditText number2 = findViewById(R.id.edittextsecondary2);
        EditText number3 = findViewById(R.id.edittextsecondary3);
        EditText number4 = findViewById(R.id.edittextsecondary4);

        // se apeleaza intentia
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("number1")
        && intent.getExtras().containsKey("number2")
                && intent.getExtras().containsKey("number3") &&
                intent.getExtras().containsKey("number4")) {
            int number_1 = intent.getIntExtra("number1", -1);
            int number_2 = intent.getIntExtra("number2", -1);
            int number_3 = intent.getIntExtra("number3", -1);
            int number_4 = intent.getIntExtra("number4", -1);

            number1.setText(String.valueOf(number_1));
            number2.setText(String.valueOf(number_2));
            number3.setText(String.valueOf(number_3));
            number4.setText(String.valueOf(number_4));
        }

        Button sum = findViewById(R.id.sum);
        Button product = findViewById(R.id.product);

        sum.setOnClickListener(listener);
        product.setOnClickListener(listener);


    }
}