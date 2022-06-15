package com.example.asimov.ui.login.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.asimov.DashboardTeacherActivity;
import com.example.asimov.MainActivity;

import com.example.asimov.R;
import com.example.asimov.RegisterFragment;
import com.example.asimov.RegisterTeacherFragment;
import com.example.asimov.databinding.FragmentLoginBinding;

import com.example.asimov.data.RetrofitClient;
import com.example.asimov.data.model.LoginRequest;
import com.example.asimov.data.model.LoginResponse;
import com.example.asimov.data.service.DirectorService;
import com.example.asimov.data.service.TeacherService;
import com.example.asimov.databinding.FragmentLoginBinding;
import com.example.asimov.manager.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    SharedPreferences pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = this.getActivity().getSharedPreferences("preferencia1", Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonFirst.setOnClickListener(v -> login());
        binding.btnRegisterDirector.setOnClickListener(v -> goToRegister());
        binding.btnRegisterTeacher.setOnClickListener(v -> goToRegisterTeacher());
    }

    private void goToRegister() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new RegisterFragment()).commit();
    }

    private void goToRegisterTeacher() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new RegisterTeacherFragment()).commit();
    }

    public void login() {
        String email = binding.emailEditText.getText().toString();
        String pass = binding.passwordEditText.getText().toString();

        if (binding.btnSwitch.isChecked()) {
            loginDirector(email, pass);
        } else {
            loginTeacher(email, pass);
        }

    }

    private void loginDirector(String email, String pass) {
        DirectorService directorService = RetrofitClient.createInstance().create(DirectorService.class);
        directorService.login(new LoginRequest(email, pass)).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Wrong Email or Password ", Toast.LENGTH_SHORT).show();
                    return;
                }

                LoginResponse loginResponse = response.body();
                SessionManager.getInstance().saveAuthToken(loginResponse.getToken());
                SessionManager.getInstance().saveUserId(loginResponse.getId());
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("userType", "director");
                getActivity().startActivity(intent);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getContext(), "ERROR: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginTeacher(String email, String pass) {
        TeacherService teacherService = RetrofitClient.createInstance().create(TeacherService.class);
        teacherService.login(new LoginRequest(email, pass)).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Wrong Email or Password ", Toast.LENGTH_SHORT).show();
                    return;
                }

                LoginResponse loginResponse = response.body();
                SessionManager.getInstance().saveAuthToken(loginResponse.getToken());
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("userType", "profesor");
                getActivity().startActivity(intent);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getContext(), "ERROR: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}