//package com.salilvnair.springcloud;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.util.Map;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.cloud.sleuth.instrument.web.TraceWebServletAutoConfiguration;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.GenericFilterBean;
//import org.springframework.web.util.ContentCachingRequestWrapper;
//import org.springframework.web.util.WebUtils;
//
//import brave.Span;
//import brave.Tracer;
//
//@Component
//@Order(TraceWebServletAutoConfiguration.TRACING_FILTER_ORDER + 1)
//class MyFilter extends GenericFilterBean {
//
//    private final Tracer tracer;
//
//    MyFilter(Tracer tracer) {
//        this.tracer = tracer;
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response,
//            FilterChain chain) throws IOException, ServletException {
//        Span currentSpan = this.tracer.currentSpan();
//        if (currentSpan == null) {
//            chain.doFilter(request, response);
//            return;
//        }
//        // for readability we're returning trace id in a hex form
//        ((HttpServletResponse) response).addHeader("ZIPKIN-TRACE-ID",
//                currentSpan.context().traceIdString());
//        // we can also add some custom tags
//       
//        currentSpan.tag("custom", "chutiyee");
//        chain.doFilter(request, response);
//        
//    }
//    
//    private void getBody(ContentCachingRequestWrapper request, Map<String, Object> trace) {
//        // wrap request to make sure we can read the body of the request (otherwise it will be consumed by the actual
//        // request handler)
//        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
//        if (wrapper != null) {
//            byte[] buf = wrapper.getContentAsByteArray();
//            if (buf.length > 0) {
//                String payload;
//                try {
//                    payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
//                }
//                catch (UnsupportedEncodingException ex) {
//                    payload = "[unknown]";
//                }
//
//                trace.put("body", payload);
//            }
//        }
//    }
//
//}