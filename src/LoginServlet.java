import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.System.out;

/**
 * Created by Administrator on 2017/7/19.
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        out.println("服务器端口" + req.getLocalPort());
        //设置响应的编码

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        String succesUrl = "success.html";
        String failUrl = "fail.html";

        String back_html = null;
        System.out.println("name:" + name + "----pass:" + password);


        /*
        客户端跳转有两种
        1. 状态码301，表示永久跳转
        2.状态码 302，表示零时跳转
         */

        if ("admin".equals(name) && "123".equals(password)) {
            //back_html = "<div style='color:green'>成功success</div>";
            //进行服务器端的跳转
            //this.redirect(req,resp,"serverRedirect",succesUrl);

            //进行客户端跳转
            this.redirect(req, resp, "clientRedirect", succesUrl);
        } else {
            //back_html = "<div style='color:red'>失败failed</div>";
            //this.redirect(req,resp,"serverRedirect",failUrl);

            //进行客户端跳转(302临时跳转)
            //this.redirect(req,resp,"clientRedirect",failUrl);

            //永久跳转
            resp.setStatus(301);
            resp.setHeader("Location", "fail.html");

            int i = "Adsds".length();

        }


        //PrintWriter printWriter  = resp.getWriter();
        //printWriter.println(back_html);

        //如何进行客户端的跳转呢？？
        //当然是在响应的阶段，返回相关的信息，叫浏览器再去访问跳转的页面

    }

    public void redirect(HttpServletRequest req, HttpServletResponse resp, String rediectWay, String rUrl) throws ServletException, IOException {
        switch (rediectWay) {
            case "serverRedirect":
                req.getRequestDispatcher(rUrl).forward(req, resp);
                break;
            case "clientRedirect":
                resp.sendRedirect(rUrl);
                break;
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
