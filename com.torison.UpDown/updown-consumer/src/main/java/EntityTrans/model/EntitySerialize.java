package EntityTrans.model;


import java.io.*;

/**
 * =========================
 * @Author MiniSC
 * This util is trans object to byte array
 * @Date 18.3.29
 * @Time 17.21
 * @Github MiniSC
 * =========================
 *
 */
public class EntitySerialize {

    /**
     * Object to ByteArray
     * @param obj
     * @return byte[]
     */
    public static byte[] toByteArray(Object obj)

    {
        byte[] bytes = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            bytes = byteArrayOutputStream.toByteArray();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            EntitySerialize.closeStream(byteArrayOutputStream,objectOutputStream);
        }
        return bytes;
    }

    /**
     * Bytes to Object
     * @param bytes
     * @return Object
     */
    public static Object toObject(byte[] bytes){
        Object object = null;
        ByteArrayInputStream bi=null;
        ObjectInputStream oi = null;
        try{
            bi = new ByteArrayInputStream(bytes);
            oi = new ObjectInputStream(bi);
            object = oi.readObject();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            EntitySerialize.closeStream(bi,oi);
        }
        return object;
    }

    /**
     * close Stream
     * @param closeable1
     * @param closeable2
     */
    public static void closeStream(Closeable closeable1,Closeable closeable2){
        try{
            if (closeable1!=null){
                closeable1.close();
            }
            if (closeable2!=null){
                closeable2.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
