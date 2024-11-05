package lesson4Exceptions;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestStream implements Closeable {


    TestStream() throws IOException {
        System.out.println("construct OK");
        //throw new IOException();
    }

    int read() throws FileNotFoundException{
        new FileInputStream("file.txt");
        System.out.println("read OK");
        return 1;
    }

    public void close() throws IOException{
        System.out.println("close OK");
        throw new IOException();
    }
    public static void main(String[] args) {

        try (TestStream stream = new TestStream()){
            int a = stream.read();
        }catch (IOException e){
            e.printStackTrace();
        }

//        try {
//            stream = new lesson4Exceptions.TestStream();
//            int a = stream.read();
//            stream.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            if (stream != null){
//                try {
//                    stream.close();
//                }catch (IOException e){
//                    throw new RuntimeException(e);
//                }
//
//            }
//
//        }

    }
}

