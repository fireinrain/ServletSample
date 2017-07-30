import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by Administrator on 2017/7/20.
 */
public class UploadPhotoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = null;
        try {
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);

            //设置文件大小
            diskFileItemFactory.setSizeThreshold(1024 * 1024);

            List items = null;
            try {
                items = upload.parseRequest(req);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }

            //遍历所有的请求项目
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem fileItem = (FileItem) iter.next();
                if (!fileItem.isFormField()) {

                    //不是表单键值对属性的值
                    //就创建时间戳作为上传的文件名
                    filename = System.currentTimeMillis() + ".jpg";
                    String photoFolder = "C:\\Users\\Administrator\\Desktop\\tools\\ServletLearn\\web\\uploaded";
                    File file = new File(photoFolder, filename);
                    out.println(file.getName());
                    file.getParentFile().mkdirs();

                    //通过fileitem.getInputStream()获得浏览器上传文件的输入流
                    InputStream inputStream = fileItem.getInputStream();

                    //复制文件
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    byte[] bytes = new byte[1024 * 1024];
                    int length = 0;
                    while (-1 != (length = inputStream.read(bytes))) {
                        fileOutputStream.write(bytes, 0, length);
                    }
                    fileOutputStream.close();
                } else {
                    //不是文件
                    out.println(fileItem.getFieldName());
                    String value = fileItem.getString();
                    //转码为utf-8
                    value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
                    out.println(value);
                }
            }

            String html = "<img width='200' height='150' src='uploaded/%s'></img>";
            resp.setContentType("text/html");
            PrintWriter printWriter = resp.getWriter();

            printWriter.format(html, filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
