import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import static java.lang.System.out;

/**
 * Created by Administrator on 2017/7/19.
 */
public class HelloServlet extends HttpServlet {
    public HelloServlet() {
        System.out.println("HttpServlet构造方法被调用");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试request的相关方法
        out.println("完整的请求路径：" + req.getRequestURL());
        out.println("请求资源：" + req.getRequestURI());
        out.println("查询参数：" + req.getQueryString());
        out.println("客户端浏览器ip：" + req.getRemoteAddr());
        out.println("客户浏览器的主机名：" + req.getRemoteHost());
        out.println("客户端浏览器的网络端口号：" + req.getRemotePort());
        out.println("服务器的ip地址：" + req.getLocalAddr());
        out.println("服务器的主机名：" + req.getLocalName());
        out.println("请求的方法：" + req.getMethod());


        //测试获取请求头
        /*
        host: 主机地址
        user-agent: 浏览器基本资料
        accept: 表示浏览器接受的数据类型
        accept-language: 表示浏览器接受的语言
        accept-encoding: 表示浏览器接受的压缩方式，是压缩方式，并非编码
        connection: 是否保持连接
        cache-control: 缓存时限
         */
        Enumeration <String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            String value = req.getHeader(header);
            out.printf("%s:---%s\r\n", header, value);
        }


        //setAttribute和getAttribute可以用来在进行服务端跳转的时候，在不同的Servlet之间进行数据共享

        String msg = "hello servlet-----来自servlet";
        //resp.setContentType("text/html;charset=UTF-8");
        //PrintWriter printWriter = resp.getWriter();
        //printWriter.println(msg);

        //设置contenttype 实现文件下载
        //resp.setContentType("text/hello");

        //设置响应的编码格式
        resp.setContentType("tetx/html;charset=UTF-8"); //该方法会通知浏览器使用utf-8来解码
        //或者
        //resp.setCharacterEncoding("UTF-8"); //该方法不会，所以要在响应体中添加浏览器的解码方式

        //通知浏览器不使用缓存
        resp.setDateHeader("Expires", 0);    //响应立刻过期
        resp.setHeader("Cache-Controll", "no-cache");    //缓存控制，无缓存
        resp.setHeader("pragma", "no-cache");        //为了指示IE浏览器（客户端）不要缓存页面


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void destroy() {
        //一下情况会调用destroy方法，销毁实例对象
        /*
        1.servlet 应用重新启动
        在web.xml 中可以设置应用的reloadable属性
        2.关闭tomcat服务器也会调用destroy
         */
        System.out.println("destroy调用");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //初始化方法是实例方法，所以会在构造方法调用后调用
        System.out.println("init(ServletConfig)");
    }


}
