package com.example.finance_tracker.ui.activities;

import android.content.Intent;
import android.os.Bundle;
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

import android.widget.Button;
import android.widget.Toast;

import com.example.finance_tracker.R;
import com.example.finance_tracker.ui.fragments.Register.RegisterFragment;
import com.example.finance_tracker.ui.fragments.Register.RegisterViewModel;
// Все так же как и в Login Page в плане методов
public class RegistrationPage extends AppCompatActivity {


    private RegisterViewModel registerViewModel;
    private Button buttonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        buttonLogin = findViewById(R.id.buttonLogin_up);
        buttonLogin.setEnabled(false); // Отключаю кнопку

        registerViewModel.getIsButtonEnabled().observe(this, isEnabled -> {
            buttonLogin.setEnabled(isEnabled); // Активируем кнопку, если данные введены
        });

        buttonLogin.setOnClickListener(v -> { //Действия с активной кнопкой
            if (Boolean.TRUE.equals(registerViewModel.getIsButtonEnabled().getValue())) {
                clickSign(v);
            }
            else {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            }
        });

        //Загрузка фрагмента с логином
        if (savedInstanceState == null) {
            // Загружаем LoginFragment, если состояние не сохранено
            loadFragment(new RegisterFragment());
        }
    }


    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager(); //Получаем фрагмент менеджер который управляет фрагментами
        FragmentTransaction transaction = fragmentManager.beginTransaction(); //начинаем операцию замены фрагмента
        transaction.replace(R.id.container1, fragment); // заменяет текущий фрагмент в container1 на заданный fragment
        transaction.commit(); // Применяет замену
    }

    public void clickSign_in(View view){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
        finish();
    }

    //Надо определится куда потом пользователя кидать
    //Логиниться или данные запоминаются и он автоматически логинится
    public void clickSign(View view){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
        finish();
    }
}