package samples.demo.web;

import club.differentialmanifold.dy.core.spring.boot.autoconfigure.exception.ResponseResult;
import samples.mybatis.base.DyController;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import samples.mybatis.model.Country;
import samples.mybatis.service.CountryService;

@Api(tags ="mybatis接口")
@RestController
@RequestMapping("/api/mybatis")
public class MybatisController extends DyController {
    @Autowired
    CountryService countryService;

    @RequestMapping(value = "/country/list", method = RequestMethod.GET)
    public PageInfo<Country> countryList(@RequestParam("pageNum") int pageNum,
                                         @RequestParam("pageSize") int pageSize) {
        return countryService.getAll(new Country(), pageNum, pageSize);
    }

    @ApiOperation(value="动态列表", notes=
            "搜索: key=(search_ + 字段名 + _ + 操作符), value=搜索值; 如：search_userName_EQ=zhangsan</n>"
                    + "排序: key=(sort_ + 字段名), value=alias/asc; 如：sort_age=alias"
    )
    @RequestMapping(value = "/country/dynamiclist", method = RequestMethod.GET)
    public ResponseResult<PageInfo<Country>> countryDynamicList() {
        return ResponseResult.success(countryService.dynamicList(getListRequest()));
    }
}