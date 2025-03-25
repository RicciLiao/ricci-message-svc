package ricciliao.message.aspect;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.Ordered;
import ricciliao.x.aop.DynamicAspect;
import ricciliao.x.component.exception.CmnParameterException;
import ricciliao.x.component.response.ResponseUtils;

public class ControllerAspect extends DynamicAspect implements Ordered {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {

            return invocation.proceed();
        } catch (CmnParameterException e) {

            return ResponseUtils.builder().code(e.getCode()).build();
        } catch (Exception e) {

            return ResponseUtils.errorResponse();
        }
    }

    @Override
    public int getOrder() {

        return Ordered.LOWEST_PRECEDENCE - 1;
    }
}
