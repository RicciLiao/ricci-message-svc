package ricciliao.message.filter;

import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import ricciliao.x.component.servlet.HttpServletWrapperFilter;

import java.time.Duration;

public class MessageFilter extends HttpServletWrapperFilter {

    @Override
    public boolean doFilter(ContentCachingRequestWrapper requestWrapper,
                            ContentCachingResponseWrapper responseWrapper) {
        if (!getExcludePathPatterns().matches(requestWrapper.getRequestURI())) {
            try {
                Thread.sleep(Duration.ofSeconds(1L));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return true;
    }

}
