package com.example.finance_tracker.ui.fragments.Login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;

import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.finance_tracker.R;


public class LoginFragmentPhone extends Fragment {

    private LoginViewModel loginViewModel;
    private EditText textPhone, textPassword;


    //Создает фрагмент никакой логики
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_page_phone, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        //инициализация кнопки и обработка события при нажатии на нее для перехода на email
        Button emailButton = view.findViewById(R.id.buttonEmail);
        emailButton.setOnClickListener(v -> openLoginFragmentEmail());//Переход с фрагмент с email

        loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        textPhone = view.findViewById(R.id.editTextPhone);
        textPassword = view.findViewById(R.id.editTextPasswordLogin);

        //addTextChangedListener метод который позволяет отслеживать действия с полями для ввода
        textPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            //Присваиваем значения в наш класс во время ввода
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loginViewModel.setUserLogin(s.toString()); // Сохраняем логин
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        textPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            //Присваиваем значения в наш класс во время ввода
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loginViewModel.setUserPassword(s.toString()); // Сохраняем пароль
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

    }

    public void onPause() {
        super.onPause();
        // Сбрасываем состояние кнопки при уходе с фрагмента
        loginViewModel.resetButtonState();
    }

    //Функция для создания фрагмента с email и заменой с phone а сам фрагмент phone удаляется
    private void openLoginFragmentEmail(){
        Fragment emailFragment = new LoginFragmentEmail();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.findFragmentById(R.id.container1);

        //Удаялем то что находится в container1 а именно фрагмент phone
        if (currentFragment != null) {
            transaction.remove(currentFragment);
        }

        //Добавляем новый фрагмент email
        transaction.add(R.id.container1, emailFragment);
        transaction.commit();
    }


}