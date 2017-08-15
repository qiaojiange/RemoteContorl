package com.example.remotecontrol;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    class Port{
        public Port() {
        }

        private List<String> port;

        public List<String> getPort() {
            return port;
        }

        public void setPort(List<String> port) {
            this.port = port;
        }
    }

    @Test
    public void test(){
       String str = "{\"exposureTime\":30.50}\n";

        Gson gson = new Gson();
        Temp temp = gson.fromJson(str,Temp.class);
        System.out.println("---------"+ temp.getExposureTime());
    }

    class Temp{
        private float exposureTime;

        public float getExposureTime() {
            return exposureTime;
        }

        public void setExposureTime(float exposureTime) {
            this.exposureTime = exposureTime;
        }
    }



//    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
//    }
}