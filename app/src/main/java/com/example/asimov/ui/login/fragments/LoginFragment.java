package com.example.asimov.ui.login.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.asimov.MainActivity;
import com.example.asimov.databinding.FragmentLoginBinding;
import com.example.asimov.R;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    SharedPreferences pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = this.getActivity().getSharedPreferences("preferencia1", Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonFirst.setOnClickListener(v -> login());
    }

    public void login() {
        String mail = binding.emailEditText.getText().toString();

        if (!mail.contains("docente") && !mail.contains("director")) {
            Toast toast = Toast.makeText(getContext(), "USUARIO EQUIVOCADO!!!!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0,0);
            toast.show();
            return;
        }
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("userType", mail.contains("docente") ? "docente" : "director");
        getActivity().startActivity(intent);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}