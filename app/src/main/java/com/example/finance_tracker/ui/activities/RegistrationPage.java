package com.example.finance_tracker.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import com.example.finance_tracker.R;

import com.example.finance_tracker.ui.fragments.LoginFragmentEmail;
import com.example.finance_tracker.ui.fragments.LoginFragmentPhone;

public class RegistrationPage extends AppCompatActivity
        implements LoginFragmentEmail.OnEmailSelectedListener, LoginFragmentPhone.OnPhoneSelectedListener
{
    private Button buttonLogin; // определяем кнопку

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

        //Находим ее по id и запрещаем ее нажатие до момента пока пользователь не введет корректно данные
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setEnabled(false);


        //Загрузка фрагмента с логином
        if (savedInstanceState == null) {
            // Загружаем LoginFragment, если состояние не сохранено
            loadFragment(new LoginFragmentEmail());
        }


    }


//Реализован интерфейс для получения данных из фрагмена с email
    @Override
    public void onEmailSelected(String email, String password) {
        // Получаем данные из фрагмента
        if(!email.isEmpty() && !password.isEmpty()){
            buttonLogin.setEnabled(true);
        }

    }

//Реализован интерфейс для получения данных из фрагмена с phone
    @Override
    public void onPhoneSelected(String phone,String password){
        if(!phone.isEmpty() && !password.isEmpty()){
            buttonLogin.setEnabled(true);
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



    

}