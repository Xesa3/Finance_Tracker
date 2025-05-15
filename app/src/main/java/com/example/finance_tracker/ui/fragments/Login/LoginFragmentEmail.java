package com.example.finance_tracker.ui.fragments.Login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.finance_tracker.R;
import com.example.finance_tracker.ui.activities.HomePage;
import com.example.finance_tracker.ui.activities.RegistrationPage;
import com.example.finance_tracker.ui.activities.Return_password;


//В классе LoginFragmentEmail оставил все комментари кто за что отвечает тут меняется тольно коле textphone на textEmail

public class LoginFragmentEmail extends Fragment {

    private LoginViewModel loginViewModel;
    private EditText textEmail, textPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_login_email, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Button fogotButton = view.findViewById(R.id.fogotEmail);
        fogotButton.setOnClickListener(v -> clickFogot());

        Button phoneButton = view.findViewById(R.id.buttonPhone);
        phoneButton.setOnClickListener(v -> openLoginFragmentPhone()); //Открывает фрагмент с полем для логина через телефон


        loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);


        EditText textEmail = view.findViewById(R.id.editTextEmailAddressLogin);
        EditText textPassword = view.findViewById(R.id.editTextPasswordLogin);


        textEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

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

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loginViewModel.setUserPassword(s.toString()); // Сохраняем пароль
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

    }

    public void onDestroyView() {
        super.onDestroyView();
        // Сбрасываем состояние кнопки при уходе с фрагмента
        loginViewModel.resetButtonState();
    }

    private void openLoginFragmentPhone(){ //метод для октрытия фрагмента с полем телефона
        Fragment phoneFragment = new LoginFragmentPhone();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.findFragmentById(R.id.container1);
        if (currentFragment != null) {
            transaction.remove(currentFragment);
        } //После этого фрагмент с email будет удален

        transaction.add(R.id.container1, phoneFragment);
        transaction.commit();
    }

    public void clickFogot(){
        Intent intent = new Intent(getActivity(), Return_password.class);
        startActivity(intent);

    }



}