package com.example.demo.cache;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;

import java.util.Base64;

@Component
public class Redis {

    //@Autowired
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // 获取缓存
    public Object get(String key) {
//        //批量删除
//        List<String> list = new ArrayList<String>();
//        list.add("qwe");
//        list.add("asd");
//        redisTemplate.delete(list);

        //删除所有key
//        Set<String> set = redisTemplate.keys("*");
//        redisTemplate.delete(set);

        return redisTemplate.opsForValue().get(key);
    }

    // 设置缓存
    public boolean set(String key, Object value) {
        boolean result = false;
        try{
            redisTemplate.opsForValue().set(key, value);
            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 删除key
    public boolean delete(String key) {
        boolean result = false;
        try{
            redisTemplate.delete(key);
            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //序列化
    public String ObjectToSeria(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        byte[] by = byteArrayOutputStream.toByteArray();
        Base64.Encoder encoder = Base64.getEncoder();
        String str = encoder.encodeToString(by);
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return str;
    }

    //反序列化
    public Object SeriaToObject(String str) throws IOException, ClassNotFoundException {

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] by = decoder.decode(str);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(by);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return object;
    }

}


