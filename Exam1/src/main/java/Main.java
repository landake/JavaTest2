import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
public class Main {

    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    public static void main(String[] args) {
        try{
            Downloads.downLoadFromUrl("http://192.168.11.205:18080/trainning/SampleChapter1.pdf",
                    "SampleChapter1.pdf","../JavaTest2/Exam1/tmp");
        }catch (Exception e) {

        }
    }

}
