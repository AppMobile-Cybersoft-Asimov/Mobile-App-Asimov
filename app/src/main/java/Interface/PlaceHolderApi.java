package Interface;

import java.util.List;

import Beans.Courses;
import Beans.Teachers;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlaceHolderApi {

    @GET("teachers/{id}")
    Call<Teachers> getTeacherById(@Path("id") int id);

    @GET("courses")
    Call<List<Courses>> getCourses();
}