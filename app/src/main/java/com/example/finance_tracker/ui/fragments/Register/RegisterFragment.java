package com.example.finance_tracker.ui.fragments.Register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finance_tracker.R;
import com.example.finance_tracker.ui.fragments.Login.LoginViewModel;

import android.widget.Button;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;

import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;



public class RegisterFragment extends Fragment {

    private RegisterViewModel registerViewModel;
    private EditText textPhone,textEmail,textPass, textAcceptPass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        registerViewModel = new ViewModelProvider(requireActivity()).get(RegisterViewModel.class);

        textPhone = view.findViewById(R.id.editTextPhoneLogin);
        textEmail = view.findViewById(R.id.editTextEmailAddressLogin);
        textPass = view.findViewById(R.id.editTextPasswordLogin);
        textAcceptPass = view.findViewById(R.id.editTextPasswordConfirm);

        //addTextChangedListener метод который позволяет отслеживать действия с полями для ввода
        textPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            //Присваиваем значения в наш класс во время ввода
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                registerViewModel.setUserPhone(s.toString()); // Сохраняем логин
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        textEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            //Присваиваем значения в наш класс во время ввода
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                registerViewModel.setUserMail(s.toString()); // Сохраняем пароль
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        textPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            //Присваиваем значения в наш класс во время ввода
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                registerViewModel.setUserPassword(s.toString()); // Сохраняем пароль
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        textAcceptPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            //Присваиваем значения в наш класс во время ввода
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                registerViewModel.setuserAcceptPassword(s.toString()); // Сохраняем пароль
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

    }
}