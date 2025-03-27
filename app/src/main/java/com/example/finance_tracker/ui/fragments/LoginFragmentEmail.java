package com.example.finance_tracker.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.finance_tracker.R;


public class LoginFragmentEmail extends Fragment {

    private OnEmailSelectedListener mListener;

    public interface OnEmailSelectedListener{
        void onEmailSelected(String email, String password);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_email, container, false);

        //Этот код проверяет, реализует ли активити, к которой привязан фрагмент, интерфейс OnEmailSelectedListener.
        if(getActivity() instanceof OnEmailSelectedListener){
            mListener = (OnEmailSelectedListener) getActivity();
        }


        Button button6 = view.findViewById(R.id.button6); // Кнопка со стрелочкой
        EditText textEmail = view.findViewById(R.id.editTextTextEmailAddress);
        EditText textPassword = view.findViewById(R.id.editTextTextPassword);
//Метод проверяет валидность данных на пустоту и отправляет их в активити(можно отдельно добавить класс для проверки на @ или на спец символы)
        button6.setOnClickListener(v -> validDateAndSendData(textEmail,textPassword));


        Button phoneButton = view.findViewById(R.id.buttonPhone);
        phoneButton.setOnClickListener(v -> openLoginFragmentPhone()); //Открывает фрагмент с полем для логина через телефон

        return view;
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


    //Метод проверки на валидность
    private void validDateAndSendData(EditText textEmail, EditText textPassword){
        String email = textEmail.getText().toString();
        String pass = textPassword.getText().toString();

        boolean isValid = true;

        // Проверка на пустые поля
        if(email.isEmpty()){
            textEmail.setHint("Пожалуйста введие ваш email");
            textEmail.setHintTextColor(Color.RED);
            isValid = false;
        }else{
            textEmail.setHintTextColor(Color.GRAY);
        }

        if(pass.isEmpty()){
            textPassword.setHint("Пожалуйста введие ваш pass");
            textPassword.setHintTextColor(Color.RED);
            isValid = false;
        }

        if(isValid && mListener != null){
            mListener.onEmailSelected(email,pass);
        }
    }

}