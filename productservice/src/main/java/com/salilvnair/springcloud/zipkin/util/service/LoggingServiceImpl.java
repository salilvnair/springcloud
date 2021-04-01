package com.salilvnair.springcloud.zipkin.util.service;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import brave.Span;
import brave.Tracer;

@Component
public class LoggingServiceImpl implements LoggingService {
	
	@Autowired
	Tracer tracer;
	
	@Autowired
	ObjectMapper mapper;
    
    @Override
    public void logRequest(HttpServletRequest httpServletRequest, Object body) {
        
        if (body != null) {
        	Span currentSpan = this.tracer.currentSpan();
        	String bodyString = "";
    		try {
    			bodyString = mapper.writeValueAsString(body);
    		}
    		catch (JsonProcessingException e) {}
        	currentSpan.tag("request", bodyString);
        }
        
        
        
    }
    
    @Override
    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
        if(body !=null) {
        	Span currentSpan = this.tracer.currentSpan();
        	String bodyString = "";
    		try {
    			bodyString = mapper.writeValueAsString(body);
    		}
    		catch (JsonProcessingException e) {}
        	currentSpan.tag("response", bodyString);
        }
    }
    
    public Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
        Map<String, String> resultMap = new HashMap<>();
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
        
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = httpServletRequest.getParameter(key);
            resultMap.put(key, value);
        }
        
        return resultMap;
    }
    
    public Map<String, String> buildHeadersMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        
        Enumeration<?> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        
        return map;
    }
    
    public Map<String, String> buildHeadersMap(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();
        
        Collection<String> headerNames = response.getHeaderNames();
        for (String header : headerNames) {
            map.put(header, response.getHeader(header));
        }
        
        return map;
    }
}
