package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        // 메시지 바디의 내용을 바이트코드로 바로 얻을 수 있다
        // 바이트 코드를 String으로 바꿔야 한다 -> Spring에서 StreamUtils를 제공 -> Spring에서 제공하는 클래스
        // 바이트를 문자로 바꾸거나 문자를 바이트로 바꿀때는 인코딩 정보를 알려줘야 한다.
        String messagebody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messagebody = " + messagebody);

        response.getWriter().write("ok");
    }
}
