package com.example.droidcafe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.droidcafe.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    public static ActivityOrderBinding orderBinding;

    private String name, address, phone, note;

    private String deliveryMethod = "Default";
    private String phoneType = "Default";

    private boolean isChecked = false;

    private String date = "Default";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        orderBinding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(orderBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String dessert = intent.getStringExtra(MainActivity.INTENT_DESSERT_KEY);
        orderBinding.textDessert.setText("You ordered a " + dessert);

        String[] items = {"Item1", "Item2", "Item3"};
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            arrayList.add("Item" + i);
        }
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arrayList);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.labels_array,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderBinding.spinnerLabel.setAdapter(adapter);


//        orderBinding.radioGroupDelivery.setOnCheckedChangeListener((radioGroup, i) -> {
//            if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonSame) {
//                deliveryMethod = orderBinding.radioButtonSame.getText().toString();
//            } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonNext) {
//                deliveryMethod = orderBinding.radioButtonNext.getText().toString();
//            } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonPick) {
//                deliveryMethod = orderBinding.radioButtonPick.getText().toString();
//            }
//            Toast.makeText(this, deliveryMethod, Toast.LENGTH_SHORT).show();
//        });
//
//        orderBinding.spinnerLabel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                phoneType = adapterView.getSelectedItem().toString();
//                Toast.makeText(OrderActivity.this, phoneType, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

        orderBinding.checkBoxConfirm.setOnCheckedChangeListener((compoundButton, b) -> {
            isChecked = b;
            orderBinding.buttonOrder.setEnabled(b);
        });

        orderBinding.buttonOrder.setOnClickListener(view -> {
            showDialog();
        });

        orderBinding.textViewDate.setOnClickListener(view -> {
            DatePickerFragment datePickerFragment = new DatePickerFragment();
            datePickerFragment.show(getSupportFragmentManager(), "datePicker");
        });
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        date = (month_string +
                "/" + day_string + "/" + year_string);
    }

    void performOrder() {
        name = orderBinding.editTextName.getText().toString();
        address = orderBinding.editTextAddress.getText().toString();
        phone = orderBinding.editTextPhone.getText().toString();
        note = orderBinding.editTextNote.getText().toString();

        if (orderBinding.radioGroupDelivery.getCheckedRadioButtonId() == R.id.radioButtonSame) {
            deliveryMethod = orderBinding.radioButtonSame.getText().toString();
        } else if (orderBinding.radioGroupDelivery.getCheckedRadioButtonId() == R.id.radioButtonNext) {
            deliveryMethod = orderBinding.radioButtonNext.getText().toString();
        } else if (orderBinding.radioGroupDelivery.getCheckedRadioButtonId() == R.id.radioButtonPick) {
            deliveryMethod = orderBinding.radioButtonPick.getText().toString();
        }

        phoneType = orderBinding.spinnerLabel.getSelectedItem().toString();
        isChecked = orderBinding.checkBoxConfirm.isChecked();

//        Toast.makeText(this, name+"-"+phoneType+"-"+deliveryMethod, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
    }

    void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);

        builder.setTitle("Confirm order!");
        builder.setMessage("Are you confirming this order?");
        builder.setIcon(R.drawable.ic_action_cart);

        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            performOrder();
        });
        builder.setNegativeButton("No", ((dialogInterface, i) -> {
            Toast.makeText(this, "Order cancelled!", Toast.LENGTH_SHORT).show();
        }));
        builder.show();
    }
}