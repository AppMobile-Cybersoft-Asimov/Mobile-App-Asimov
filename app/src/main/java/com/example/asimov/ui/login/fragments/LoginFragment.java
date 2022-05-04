package com.example.asimov.ui.login.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.asimov.databinding.FragmentLoginBinding;
import com.example.asimov.R;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    SharedPreferences pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref=this.getActivity().getSharedPreferences("preferencia1", Context.MODE_PRIVATE);
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

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_LoginFragment_to_MainActivity);
                validateEmailandPassword();
            }
        });
    }
    public void validateEmailandPassword(){
        String[] splitEmail = binding.emailEditText.getText().toString().split("@");
        SharedPreferences.Editor editor=pref.edit();
        if(splitEmail[1]=="docente"){
            editor.putString("type","docente");
        } else{
            editor.putString("type","director");
        }
        editor.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}