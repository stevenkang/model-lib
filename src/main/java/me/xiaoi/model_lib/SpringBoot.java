package me.xiaoi.model_lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
@EnableAutoConfiguration
public class SpringBoot {

	
	public static void main(String[] args) {
		SpringApplication.run(SpringBoot.class, args);
	}
	
	/** 型号库地址 */
	private static final String MODEL_LIB_URL = "https://raw.githubusercontent.com/stevenkang/model-lib/master/mobile.txt";
	
	private static Map<String, JSONObject> cache = new HashMap<>();
	
	static {
		// 加载数据
		load();
	}
	
	private synchronized static int load() {
		try {
			HttpURLConnection http = (HttpURLConnection) new URL(MODEL_LIB_URL).openConnection();
			http.connect();
			//获取内容
            InputStream inputStream = http.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("#") == false) {
                	String[] res = line.split(":");
                	if (res.length == 3) {
                		JSONObject data = new JSONObject();
                    	data.put("name", res[1]);
                    	data.put("brand", res[2]);
                    	cache.put(res[0], data);
                	} else {
                		System.out.println("read line error: " + line);
                	}
                }
            }
            System.out.println("init success, found " + cache.size() + " model");
            bufferedReader.close();
            http.disconnect();
            return cache.size();
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@RequestMapping("/api/reload")
	JSONObject reload() {
		int size = load();
		JSONObject res = new JSONObject();
		res.put("code", 0);
		res.put("msg", "reload successful: " + size);
		return res;
	}
	
	@RequestMapping("/api/model/{model}")
	JSONObject modelQuery(@PathVariable String model) {
		JSONObject data = cache.get(model);
		JSONObject res = new JSONObject();
		if (data != null) {
			 res.put("code", 0);
			 res.put("msg", "success");
			 res.put("data", data);
		} else {
			res.put("code", 404);
			res.put("msg", "not found");
			System.err.println("unknown model: " + model);
		}
		return res;
	}

}
