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
        String strPorts = "{\"port\":[\"COM1\",\"R\",\"COM5\"]}";

        Gson gson = new Gson();




        Port port = gson.fromJson(strPorts,Port.class);
        for(String p:port.getPort()){
            System.out.println(p);
        }


    }


//    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
//    }
}