import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static java.lang.System.out;

/**
 * Created by Administrator on 2017/7/20.
 */
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试request的获取提交参数方法
        out.println("获取单值参数name:" + req.getParameter("name"));

        //获取多值参数
        String[] hobies = req.getParameterValues("hobies");
        out.println("获取多值参数hobies：" + Arrays.asList(hobies));

        //获得所有参数值，类型为map类型
        Map <String, String[]> parateter = req.getParameterMap();
        //打印出所有获得的参数---值
        Set <String> paraNames = parateter.keySet();
        for (String param : paraNames
                ) {
            String[] values = parateter.get(param);
            out.println(param + ":---" + Arrays.asList(values));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
}
