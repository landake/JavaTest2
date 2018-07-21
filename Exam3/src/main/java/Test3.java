import net.sf.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
public class Test3 {
        private static final int SIZE = 4096;
        public static void main(String[] args) throws IOException {
            System.out.println("股票编码："+args[0]);
            String urls = "http://hq.sinajs.cn/list=" + args[0];
            URL url = new URL(urls);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(5*1000);
            System.out.println("开始获取数据。。。。。。");
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible;MSIE 5.0;Windows NT;DigExt)");
            InputStream in = conn.getInputStream();
            StringBuilder sb = new StringBuilder();
            int len = 0;
            byte[] buf = new byte[SIZE];
            while((len=in.read(buf))!=-1){
                sb.append(new String(buf,0,len));
            }
            String[] one = sb.toString().split("\"");
            String[] two = one[1].split(",");
            Map<String,String> map = new LinkedHashMap<>();
            map.put("name", two[0]);
            map.put("open", two[1]);
            map.put("close", two[2]);
            map.put("current", two[3]);
            map.put("high", two[4]);
            map.put("low", two[5]);
		/*for(String key : map.keySet()) {
			System.out.println(key + ":" + map.get(key));
		} */
            System.out.println("获取数据成功！");

            String json = JSONObject.fromObject(map).toString();
            String xml = callMapToXML(map);

            File file1 = new File("e:/股票编码.json");
            File file2 = new File("e:/股票编码.xml");
            new PutFile(json, file1 , "json").start();
            new PutFile(xml, file2 , "xml").start();

        }
        public static String callMapToXML(Map map) {
            StringBuffer sb = new StringBuffer();
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><stock>");
            mapToXMLTest2(map, sb);
            sb.append("</stock></xml>");
            return sb.toString();
        }

        private static void mapToXMLTest2(Map map, StringBuffer sb) {
            Set set = map.keySet();
            for (Iterator it = set.iterator(); it.hasNext();) {
                String key = (String) it.next();
                Object value = map.get(key);
                if (null == value)
                    value = "";
                if (value.getClass().getName().equals("java.util.ArrayList")) {
                    ArrayList list = (ArrayList) map.get(key);
                    sb.append("<" + key + ">");
                    for (int i = 0; i < list.size(); i++) {
                        HashMap hm = (HashMap) list.get(i);
                        mapToXMLTest2(hm, sb);
                    }
                    sb.append("</" + key + ">");

                } else {
                    if (value instanceof HashMap) {
                        sb.append("<" + key + ">");
                        mapToXMLTest2((HashMap) value, sb);
                        sb.append("</" + key + ">");
                    } else {
                        sb.append("<" + key + ">" + value + "</" + key + ">");
                    }

                }

            }
        }



    }




