package com.nefu.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CharacterEncodingFilter")
public class CharacterEncodingFilter implements Filter {
    private String encoding;
    private boolean forceEncoding;

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
        forceEncoding = Boolean.parseBoolean(config.getInitParameter("forceEncoding"));
    }

    public void destroy() {
        encoding = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 清除缓存
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        if (this.encoding != null && (this.forceEncoding || request.getCharacterEncoding() == null)) {
            request.setCharacterEncoding(this.encoding);
            if (this.forceEncoding) {
                response.setContentType("text/html;charset=" + this.encoding);
            }
        }
        String url = String.valueOf(httpServletRequest.getRequestURL());
        if (!url.equals(httpServletRequest.getContextPath()))
            System.out.println("过滤到 -> " + url);
        chain.doFilter(request, response);
    }
}
