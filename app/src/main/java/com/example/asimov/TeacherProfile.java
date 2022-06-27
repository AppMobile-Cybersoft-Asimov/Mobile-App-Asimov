package com.example.asimov;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.asimov.adapters.TeacherCourseAdapter;
import com.example.asimov.data.RetrofitClient;
import com.example.asimov.databinding.ActivityTeacherProfileBinding;
import java.util.List;
import com.example.asimov.data.model.Courses;
import com.example.asimov.data.model.Teachers;
import com.example.asimov.data.service.AsimovApi;
import com.example.asimov.utils.SpacingItemDecorator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherProfile extends Fragment {

    private ActivityTeacherProfileBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(16);

        binding = ActivityTeacherProfileBinding.inflate(getLayoutInflater());
        binding.recyclerCourses.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        binding.recyclerCourses.addItemDecoration(itemDecorator);
        binding.recyclerCourses.setNestedScrollingEnabled(false);
        int id = 2;
        getTeacherById(id);
        getCoursesByTeacherId(id);
        return binding.getRoot();
    }

    private void getCoursesByTeacherId(int teacherId) {

        AsimovApi asimovApi = RetrofitClient.createInstance().create(AsimovApi.class);
        Call<List<Courses>> inter = asimovApi.getCourseByTeacherId(teacherId);

        inter.enqueue(new Callback<List<Courses>>() {
            @Override
            public void onResponse(Call<List<Courses>> call, Response<List<Courses>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "Codigo de error: " + response.code(),
                            Toast.LENGTH_SHORT).show();
                }

                List<Courses> listCourses = response.body();
                TeacherCourseAdapter teacherCourseAdapter = new TeacherCourseAdapter(listCourses);

                binding.recyclerCourses.setAdapter(teacherCourseAdapter);
            }

            @Override
            public void onFailure(Call<List<Courses>> call, Throwable t) { }
        });
    }

    private void getTeacherById(int id) {

        AsimovApi asimovApi = RetrofitClient.createInstance().create(AsimovApi.class);
        Call<Teachers> inter = asimovApi.getTeacherById(id);

        inter.enqueue(new Callback<Teachers>() {
            @Override
            public void onResponse(Call<Teachers> call, Response<Teachers> response) {
                if(!response.isSuccessful()) {
                    binding.txtTFirstName.setText("Codigo de error: "+response.code());
                    return;
                }
                Teachers teachers = response.body();

                String firstNameCard = "";
                String lastNameCard = "";
                String firstName = "";
                String lastName = "";
                String age = "";
                String email = "";
                String phone = "";
                String point = "";
                String firstNameP = "";
                String teacherProgress = "";

                double totalPoints = 1000;
                double percentage = 0;
                double currentPoints = 0;

                firstNameCard += teachers.getFirstName()+" ";
                lastNameCard += teachers.getLastName();
                firstName += "First Name: "+teachers.getFirstName();
                lastName += "Last Name: "+teachers.getLastName();
                age += "Age: "+teachers.getAge();
                email += "Email: "+teachers.getEmail();
                phone += "Phone: "+teachers.getPhone();
                point += teachers.getPoint()+" Pts.";
                firstNameP += "Teacher: "+teachers.getFirstName();
                currentPoints = teachers.getPoint();

                percentage = (currentPoints/totalPoints)*100;
                int per = (int)percentage;
                teacherProgress += String.valueOf(percentage) + "%";

                binding.txtTCardFirstName.setText(firstNameCard);
                binding.txtTCardLastName.setText(lastNameCard);
                binding.txtTFirstName.setText(firstName);
                binding.txtTLastName.setText(lastName);
                binding.txtTAge.setText(age);
                binding.txtTEmail.setText(email);
                binding.txtTPhone.setText(phone);
                binding.txtTPoint.setText(point);
                binding.txtTPCName.setText(firstNameP);
                binding.txtTProgress.setText(teacherProgress);
                binding.progressBar.setProgress(per);
            }

            @Override
            public void onFailure(Call<Teachers> call, Throwable t) { }
        });

    }
}