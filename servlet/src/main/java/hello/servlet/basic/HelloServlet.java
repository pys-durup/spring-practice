package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);
        /**
         * HttpServletRequest와 HttpServletResponse는 인터페이스
         * 톰켓같은 WAS 서버들이 Servlet 표준 스펙을 구현하는 것.
         * org.apache.catalina.connector.RequestFacade@1e4b66e7 HttpServletRequest의 구현체
         * org.apache.catalina.connector.ResponseFacade@65fcb96b HttpServletResponsb의 구현체
         */

        String username = request.getParameter("username");
        System.out.println("username = " + username);
        /**
         * HTTP 스펙의 메세지를 직접 파싱하려면 개발자가 힘들다
         * 서블릿은 편리하게 사용할 수 있도록 도와준다.
         */

        response.setContentType("text/plain"); // 어떤 형식의 데이터를 응답해 줄지
        response.setCharacterEncoding("utf-8"); // 인코딩 정보 - 옛날 시스템 아니면 utf-8
        response.getWriter().write("hello " + username); // HTTP 메시지 BODY에 담아서 응답할 데이터

    }
}

