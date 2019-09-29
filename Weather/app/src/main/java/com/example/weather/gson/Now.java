package com.example.weather.gson;

import com.google.gson.annotations.SerializedName;

public class Now {
    @SerializedName("tmp")//温度
    public String temperature;
    @SerializedName("cond")//天气
    public More more;
    public class More{
        @SerializedName("txt")
        public String info;
    }
}
