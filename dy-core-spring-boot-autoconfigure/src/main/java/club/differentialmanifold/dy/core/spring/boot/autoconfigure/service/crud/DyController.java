package club.differentialmanifold.dy.core.spring.boot.autoconfigure.service.crud;

import club.differentialmanifold.dy.core.spring.boot.autoconfigure.service.DyServiceUtil;
import club.differentialmanifold.dy.core.spring.boot.autoconfigure.service.crud.list.ListRequest;
import club.differentialmanifold.dy.core.spring.boot.autoconfigure.service.crud.list.page.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class DyController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected HttpSession session;
    @Autowired
    protected ServletContext application;

    public Logger getLogger() {
        return this.logger;
    }

    protected ListRequest getListRequest() {
        return getListRequest(PageRequest.DEFAULT_PAGE_NUMBER, PageRequest.DEFAULT_PAGE_SIZE);
    }

    protected ListRequest getListRequest(Integer pageNum, Integer pageSize) {
        return getListRequest(request, pageNum, pageSize);
    }

    protected ListRequest getListRequest(HttpServletRequest request, Integer pageNum, Integer pageSize){
        // search_userName_EQ
        Map<String, Object> searchParams = DyServiceUtil.getParametersStartingWith(request, "search_");
        // sort_userName
        LinkedHashMap<String, Object> sortParams = (LinkedHashMap<String, Object>) DyServiceUtil.getParametersStartingWith(request, "sort_");

        ListRequest listRequest = new ListRequest(pageNum, pageSize, sortParams, searchParams);
        return listRequest;
    }

    protected ListRequest getListRequest(Map<String, Object> searchParams, LinkedHashMap<String, Object> sortParams, Integer pageNum, Integer pageSize){
        ListRequest listRequest = new ListRequest(pageNum, pageSize, sortParams, searchParams);
        return listRequest;
    }
}
