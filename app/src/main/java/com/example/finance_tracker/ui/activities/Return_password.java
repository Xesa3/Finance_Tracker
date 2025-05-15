package com.example.finance_tracker.ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.finance_tracker.R;
import com.example.finance_tracker.ui.fragments.Register.RegisterFragment;
import com.example.finance_tracker.ui.fragments.Register.RegisterViewModel;
import com.example.finance_tracker.ui.fragments.return_password.ReturnPasswordViewModel;
import com.example.finance_tracker.ui.fragments.return_password.code_em_pho;
import com.example.finance_tracker.ui.fragments.return_password.code_em_phone;
import com.example.finance_tracker.ui.fragments.return_password.new_password;

public class Return_password extends AppCompatActivity {

    private ReturnPasswordViewModel rViewModel;
    private Button nextbtn;

    private int currentStep = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_return_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });;


        rViewModel = new ViewModelProvider(this).get(ReturnPasswordViewModel.class);


        nextbtn = findViewById(R.id.buttonNext);

        rViewModel.getIsNextButtonEnabled().observe(this, isEnable->{
            nextbtn.setEnabled(isEnable);
            if (isEnable) {
                nextbtn.getBackground().clearColorFilter(); // Очищаем затемнение
            } else {
                nextbtn.getBackground().setColorFilter(Color.parseColor("#66bab8b6"), PorterDuff.Mode.MULTIPLY); // Затемняем
            }
        });

        nextbtn.setOnClickListener(v-> {
            rViewModel.nextStep();
            if(rViewModel.getCurrentStep() == 3){
                clickToReturnLoginPage(v);
            }
            else{
                ShowStep(rViewModel.getCurrentStep());
            }


        });

        ShowStep(rViewModel.getCurrentStep());


    }

    private void clickToReturnLoginPage(View view){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
        finish();
    }
    private void ShowStep(int step){

        Fragment fragment = null;

        switch (step){
            case(0):
                fragment = new code_em_phone();
                break;
            case(1):
                fragment = new code_em_pho();
                break;
            case(2):
                fragment = new new_password();
                break;

            default:
                return;
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container1, fragment)
                .commit();
    }

    public void clickSign_up(View view){
        Intent intent = new Intent(this, RegistrationPage.class);
        startActivity(intent);
        finish();
    }

}