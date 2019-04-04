package me.idarkyy.common.utils;

import java.io.*;

public class ObjectSerializer {
    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        ObjectOutputStream output = new ObjectOutputStream(bos);
        output.writeObject(obj);
        output.flush();

        byte[] bytes = bos.toByteArray();

        output.close();
        bos.close();

        return bytes;
    }

    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);

        Object object = ois.readObject();

        ois.close();
        bis.close();

        return object;
    }

}
