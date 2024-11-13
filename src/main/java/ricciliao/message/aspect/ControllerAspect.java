package ricciliao.message.aspect;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.Ordered;
import ricciliao.common.component.exception.CmnParameterException;
import ricciliao.common.component.response.ResponseUtils;
import ricciliao.dynamic.aop.DynamicAspect;

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
