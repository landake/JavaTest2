import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
    public static void main(String[] args) throws Exception {

        // 创建文件流用来读取文件中的数据

       File file = new File("../JavaTest2/Exam1/tmp/SampleChapter1.pdf");

        FileInputStream fos = new FileInputStream(file);

        // 创建网络服务器接受客户请求

        ServerSocket ss = new ServerSocket(22223);

        Socket client = ss.accept();

        // 创建网络输出流并提供数据包装器

        OutputStream netOut = client.getOutputStream();

        OutputStream doc = new DataOutputStream(
                new BufferedOutputStream(netOut));

        // 创建文件读取缓冲区

        byte[] buf = new byte[2048];

        int num = fos.read(buf);

        while (num != (-1)) {// 是否读完文件

            doc.write(buf, 0, num);// 把文件数据写出网络缓冲区

            doc.flush();// 刷新缓冲区把数据写往客户端

            num = fos.read(buf);// 继续从文件中读取数据

        }
        System.out.println("文件传输完成");

        fos.close();

        doc.close();

    }
}
