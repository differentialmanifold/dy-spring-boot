package club.differentialmanifold.dy.core.spring.boot.autoconfigure.service.crud;

import club.differentialmanifold.dy.core.spring.boot.autoconfigure.service.DyServiceUtil;
import club.differentialmanifold.dy.core.spring.boot.autoconfigure.service.crud.list.ListRequest;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class DyService<M extends Mapper<T>, T> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected M mapper;

    public PageInfo<T> list(ListRequest listRequest) {
        return DyServiceUtil.doSearch(listRequest, this.mapper, this.modelClass(getClass()));
    }

    protected Class modelClass(Class clazz) {
        Type genericSuperclass = clazz.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType)genericSuperclass;
        return (Class)parameterizedType.getActualTypeArguments()[1];
    }

}
