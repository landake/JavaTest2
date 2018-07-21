import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PutFile extends Thread {

        private String str= null;
        private FileWriter fw = null;
        private File file = null;
        private String fileType;

        public PutFile(String str, File file , String fileType ) {
            this.str = str;
            this.file = file;
            this.fileType = fileType;
        }
        public void run() {
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            try {
                fw = new FileWriter(file);
                fw.write(str);
                fw.close();
                System.out.println("解析为"+ fileType +"成功！");

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

