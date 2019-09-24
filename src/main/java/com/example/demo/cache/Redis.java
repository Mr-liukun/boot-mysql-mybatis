package com.example.demo.cache;


import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.commons.io.IOCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;

@Component
public class Redis {

    //@Autowired
    @Resource
    private RedisTemplate<String, byte[]> redisTemplate;

    // 获取缓存
    public byte[] get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 设置缓存
    public boolean set(String key, byte[] value) {
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

    public byte[] ObjectToSeria(Object obj) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        //String string = byteArrayOutputStream.toString("UTF-8");
        byte[] by = byteArrayOutputStream.toByteArray();

        objectOutputStream.close();
        byteArrayOutputStream.close();
        return by;
    }

    public Object SeriaToObject(byte[] str) throws IOException, ClassNotFoundException {

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return object;
    }




}


