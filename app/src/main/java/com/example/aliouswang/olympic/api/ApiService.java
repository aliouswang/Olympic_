package com.example.aliouswang.olympic.api;

import com.aliouswang.retrofit.Call;
import com.aliouswang.retrofit.annotations.GET;
import com.example.aliouswang.olympic.bean.BaseBean;

public interface ApiService {

    @GET("/config/homePage")
    Call<BaseBean> getHomeConfig();

}
