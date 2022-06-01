package com.example.asimov;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.asimov.databinding.FragmentRegisterTeacherBinding;
import com.example.asimov.ui.login.fragments.LoginFragment;

public class RegisterTeacherFragment extends Fragment {

    private FragmentRegisterTeacherBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterTeacherBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnRegRegistrar.setOnClickListener(v -> registerTeacher());
    }

    private void registerTeacher() {
        if (
                binding.tboxRegFirstName.getText().toString().equals("")
                        || binding.tboxRegLastName.getText().toString().equals("")
                        || binding.tboxRegPhone.getText().toString().equals("")
                        || binding.tboxRegBirthdate.getText().toString().equals("")
                        || binding.tboxRegEmail.getText().toString().equals("")
                        ||binding.tboxRegPass.getText().toString().equals("")
                        || !binding.tboxRegEmail.getText().toString().contains("@")
        ){

            Toast.makeText(getContext(), "Ingrese todos los datos solicitados", Toast.LENGTH_LONG).show();
        }
        else {
            Toast toast = Toast.makeText(getContext(), "Director registrado con éxito", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0,300);
            toast.show();
            goToLogin();
        }
    }

    private void goToLogin() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment()).commit();
    }
}