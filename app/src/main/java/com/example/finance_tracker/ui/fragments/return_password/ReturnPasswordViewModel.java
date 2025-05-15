package com.example.finance_tracker.ui.fragments.return_password;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
public class ReturnPasswordViewModel extends ViewModel {

    private final MutableLiveData<String> email = new MutableLiveData<>();

    private final MutableLiveData<String> phone = new MutableLiveData<>();
    private final MutableLiveData<String> smsCode = new MutableLiveData<>();
    private final MutableLiveData<String> newPasswordCurrent = new MutableLiveData<>();
    private final MutableLiveData<String> newPasswordConfirm = new MutableLiveData<>();

    private final MutableLiveData<Boolean> isNextButtonEnabled = new MutableLiveData<>(false);
    private int currentStep = 0;

    public LiveData<Boolean> getIsNextButtonEnabled() {
        return isNextButtonEnabled;
    }

    public void setEmail(String value) {
        email.setValue(value);
        validateInput();
    }

    public void setPhone(String value) {
        phone.setValue(value);
        validateInput();
    }

    public void setSmsCode(String code) {
        smsCode.setValue(code);
        validateInput();
    }

    public void setNewPasswordCurrent(String password) {
        newPasswordCurrent.setValue(password);
        validateInput();
    }

    public void setNewPasswordConfirm(String password) {
        newPasswordConfirm.setValue(password);
        validateInput();
    }

    private void validateInput() {
        boolean valid = false;

        if (currentStep == 0) {
            valid = (email.getValue() != null && !email.getValue().isEmpty()) ||
                    (phone.getValue() != null && !phone.getValue().isEmpty());
        } else if (currentStep == 1) {
            valid = smsCode.getValue() != null && smsCode.getValue().length() == 6;
        } else if (currentStep == 2) {
            String pass = newPasswordCurrent.getValue();
            String confirm = newPasswordConfirm.getValue();

            valid = (pass != null && pass.length() >= 2 && pass.equals(confirm));
        }

        isNextButtonEnabled.setValue(valid);
    }

    public void nextStep() {
        currentStep++;
        validateInput(); // чтобы сбросить кнопку
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void reset() {
        email.setValue("");
        phone.setValue("");
        smsCode.setValue("");
        newPasswordConfirm.setValue("");
        newPasswordCurrent.setValue("");
        currentStep = 0;
        isNextButtonEnabled.setValue(false);
    }
}
