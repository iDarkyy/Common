package me.idarkyy.common.serialization;

import java.io.*;

public class ObjectSerializer {
    public static byte[] serialize(Serializable serializable) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        ObjectOutputStream output = new ObjectOutputStream(bos);
        output.writeObject(serializable);
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
