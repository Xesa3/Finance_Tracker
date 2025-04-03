package com.example.finance_tracker.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import android.widget.EditText;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import com.example.finance_tracker.R;
import com.example.finance_tracker.ui.fragments.Login.LoginViewModel;
import androidx.lifecycle.ViewModelProvider;
import android.widget.Toast;

import com.example.finance_tracker.ui.fragments.Login.LoginFragmentEmail;
import android.graphics.Color;
import android.graphics.PorterDuff;



public class LoginPage extends AppCompatActivity
{
    private LoginViewModel loginViewModel;
    private Button buttonLogin; // определяем кнопку
    private EditText textPhone, textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);


        //Находим ее по id и запрещаем ее нажатие до момента пока пользователь не введет корректно данные
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setEnabled(false);

        loginViewModel.getIsButtonEnabled().observe(this, isEnabled -> {
            buttonLogin.setEnabled(isEnabled); // Активируем кнопку, если данные введены
            if (isEnabled) {
                buttonLogin.getBackground().clearColorFilter(); // Очищаем затемнение
            } else {
                buttonLogin.getBackground().setColorFilter(Color.parseColor("#66bab8b6"), PorterDuff.Mode.MULTIPLY); // Затемняем
            }

        });



        //Загрузка фрагмента с логином
        if (savedInstanceState == null) {
            // Загружаем LoginFragment, если состояние не сохранено
            loadFragment(new LoginFragmentEmail());
        }


    }



    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager(); //Получаем фрагмент менеджер который управляет фрагментами
        FragmentTransaction transaction = fragmentManager.beginTransaction(); //начинаем операцию замены фрагмента
        transaction.replace(R.id.container1, fragment); // заменяет текущий фрагмент в container1 на заданный fragment
        transaction.commit(); // Применяет замену
    }

    public void clickLogin(View view) { //Нажатие на кнопку логин и переход к главному меню
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
            finish();
    }

    public void clickSign_up(View view){
        Intent intent = new Intent(this, RegistrationPage.class);
        startActivity(intent);
        finish();
    }



    

}