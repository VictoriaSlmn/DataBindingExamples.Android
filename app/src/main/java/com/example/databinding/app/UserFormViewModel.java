package com.example.databinding.app;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

public class UserFormViewModel extends BaseObservable {

    private String firstName;
    private String lastName;
    private final View.OnClickListener clicker;
    public final ObservableField<String> firstNameError = new ObservableField<>();
    public final ObservableBoolean isEnabled = new ObservableBoolean(false);

    public UserFormViewModel(View.OnClickListener clicker){
        this.clicker = clicker;
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public TextWatcher getFirstNameWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                firstName = s.toString();
                if(TextUtils.isEmpty(s)){
                    firstNameError.set("Введите имя!");
                    isEnabled.set(false);
                }else {
                    firstNameError.set(null);
                    isEnabled.set(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

//    public void setLastName(String firstName) {
//        this.firstName = firstName;
//        notifyPropertyChanged(com.example.databinding.app.BR.lastName);
//    }


    public TextWatcher getLastNameWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lastName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }

    public View.OnClickListener getClicker() {
        return clicker;
    }
}
