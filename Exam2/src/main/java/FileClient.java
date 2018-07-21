import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class FileClient {
    public static void main(String[] args) throws Exception {
     String savePath="../JavaTest2/Exam2/tmp/SampleChapter1.pdf";
     String fileName="SampleChapter1.pdf";
       // File file = new File("../JavaTest2/Exam2/tmp/SampleChapter1.pdf");
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        File file = new File(saveDir + File.separator + fileName);
        file.createNewFile();
        RandomAccessFile raf = new RandomAccessFile(file, "rw");

        // 通过Socket连接文件服务器

        Socket server = new Socket(InetAddress.getLocalHost(), 22223);

        // 创建网络接受流接受服务器文件数据

        InputStream netIn = server.getInputStream();

        InputStream in = new DataInputStream(new BufferedInputStream(netIn));

        // 创建缓冲区缓冲网络数据

        byte[] buf = new byte[2048];

        int num = in.read(buf);

        while (num != (-1)) {// 是否读完所有数据

            raf.write(buf, 0, num);// 将数据写往文件

            raf.skipBytes(num);// 顺序写文件字节

            num = in.read(buf);// 继续从网络中读取文件

        }
        System.out.println("文件接收完毕");

        in.close();

        raf.close();

    }
}

