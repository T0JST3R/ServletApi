import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "Hello", urlPatterns = "/api/*")
public class HelloServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(HelloServlet.class);
    private HelloService helloService;

    public HelloServlet() {
        this(new HelloService());
    }

    public HelloServlet(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String NAME_PARAM = "name";
        final String LANG_PARAM = "lang";
        logger.info("got request with parameters" + req.getParameterMap());

        String name = req.getParameter(NAME_PARAM);
        String lang = req.getParameter(LANG_PARAM);

        resp.getWriter().write(helloService.prepareGreeting(name, lang));
    }
}
