package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        // ControllerV3의 인스턴스인지 체크 -> 맞다면 참을 반환
        return (handler instanceof ControllerV3);

    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        // Object 로 한 이유는 -> 유연하게 해야하기 때문에
        // ControllerV3로 캐스팅 해도 괜찮은 이유는 suport 메서드에서 Controller V3만 지원하도록 했기 때문에
        ControllerV3 controller = (ControllerV3) handler;

        // 어댑터의 역할은 핸들러(컨트롤러)를 호출 해주고 결과를 ModelView 타입으로 맞춰서 반환해줘야 한다
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
