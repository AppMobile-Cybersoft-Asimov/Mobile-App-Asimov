package com.example.asimov;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.asimov.adapters.CourseAdapter;
import com.example.asimov.databinding.ActivityTeacherProfileBinding;
import java.util.ArrayList;
import java.util.List;
import Beans.Courses;
import Beans.Teachers;
import Interface.PlaceHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TeacherProfile extends Fragment {

    private List<Courses> courseData;
    private ActivityTeacherProfileBinding binding;

    //private static final String TAG = "TeacherProfile";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeacherProfileBinding.inflate(getLayoutInflater());

        binding.recyclerCourses.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        binding.recyclerCourses.setNestedScrollingEnabled(false);

        //Log.i(TAG, "Teacher.getView()" + position);

        //getTeacherById(1);
        getCourses();
        return binding.getRoot();
    }

    private void getCourses() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://asimov.azurewebsites.net/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PlaceHolderApi placeHolderApi = retrofit.create(PlaceHolderApi.class);
        Call<List<Courses>> inter = placeHolderApi.getCourses();

        inter.enqueue(new Callback<List<Courses>>() {
            @Override
            public void onResponse(Call<List<Courses>> call, Response<List<Courses>> response) {
                List<Courses> list = response.body();
                courseData = new ArrayList<Courses>();
                for(Courses co:list){
                    courseData.add(new Courses(co.getId(), co.getName(),
                            co.getDescription(), co.getState()));
                }
                CourseAdapter adapter = new CourseAdapter(courseData);
                binding.recyclerCourses.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Courses>> call, Throwable t) { }
        });
    }

    private void getTeacherById(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://asimov.azurewebsites.net/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PlaceHolderApi placeholder = retrofit.create(PlaceHolderApi.class);
        Call<Teachers> inter = placeholder.getTeacherById(id);

        inter.enqueue(new Callback<Teachers>() {
            @Override
            public void onResponse(Call<Teachers> call, Response<Teachers> response) {
                if(!response.isSuccessful()) {
                    binding.txtTFirstName.setText("Codigo de error: "+response.code());
                }
                Teachers teachers = response.body();

                String firtNameCard = "";
                String lastNameCard = "";
                String idCard = "";

                String firtName = "";
                String lastName = "";
                String age = "";
                String email = "";
                String phone = "";
                String point = "";
                String directorId = "";

                firtNameCard += teachers.getFirstName();
                lastNameCard += teachers.getLastName();
                idCard += teachers.getId();

                firtName += "First Name: "+teachers.getFirstName();
                lastName += "Last Name: "+teachers.getFirstName();
                age += "Age: "+teachers.getFirstName();
                email += "Email: "+teachers.getFirstName();
                phone += "Phone: "+teachers.getFirstName();
                point += teachers.getPoint()+" Pts.";
                directorId += "Director Id"+teachers.getDirectorId();

                binding.txtTCardFirstName.setText(firtNameCard);
                binding.txtTCardLastName.setText(lastNameCard);
                //binding.txtTId.setText(idCard);

                binding.txtTFirstName.setText(firtName);
                binding.txtTLastName.setText(lastName);
                binding.txtTAge.setText(age);
                binding.txtTEmail.setText(email);
                binding.txtTPhone.setText(phone);
                //binding.txtTDirectorId.setText(directorId);

                binding.txtTPoint.setText(point);
            }

            @Override
            public void onFailure(Call<Teachers> call, Throwable t) { }
        });

    }
}