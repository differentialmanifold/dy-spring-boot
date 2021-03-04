package samples.mybatis.base;

import club.differentialmanifold.dy.core.spring.boot.autoconfigure.exception.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import samples.mybatis.base.crud.list.ListRequest;
import samples.mybatis.base.crud.list.page.PageRequest;
import tk.mybatis.mapper.common.Mapper;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class DyController<S extends DyService<? extends Mapper<T>, T>, T> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected S service;

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

    @ApiOperation(value="动态列表", notes=
            "搜索: key=(search_ + 字段名 + _ + 操作符), value=搜索值; 如：search_userName_EQ=zhangsan</n>"
                    + "排序: key=(sort_ + 字段名), value=alias/asc; 如：sort_age=alias"
    )
    @RequestMapping(value = "/dynamiclist", method = RequestMethod.GET)
    public ResponseResult<PageInfo<T>> countryDynamicList(@RequestParam("pageNum") int pageNum,
                                                          @RequestParam("pageSize") int pageSize) {
        return ResponseResult.success(service.dynamicList(getListRequest(pageNum, pageSize)));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseResult<PageInfo<T>> selectItem(@RequestParam("pageNum") int pageNum,
                                                  @RequestParam("pageSize") int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<T> itemList = service.selectAll();
        PageInfo<T> pageInfo = new PageInfo<>(itemList);
        return ResponseResult.success(pageInfo);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseResult<Object> insertItem(@RequestBody T item) {
        service.insertSelective(item);
        return ResponseResult.success();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseResult<Object> deleteItem(@RequestParam("id") Long id) {
        service.deleteByPrimaryKey(id);
        return ResponseResult.success();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseResult<Object> updateItem(@RequestBody T item) {
        service.updateByPrimaryKeySelective(item);
        return ResponseResult.success();
    }
}
