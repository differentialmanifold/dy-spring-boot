package samples.demo.web;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import samples.mybatis.base.DyController;
import samples.mybatis.model.Country;
import samples.mybatis.service.CountryService;

@Api(tags ="mybatis接口")
@RestController
@RequestMapping("/api/mybatis")
public class MybatisController extends DyController<CountryService, Country> {
    @RequestMapping(value = "/country/list", method = RequestMethod.GET)
    public PageInfo<Country> countryList(@RequestParam("pageNum") int pageNum,
                                         @RequestParam("pageSize") int pageSize) {
        return service.getAll(new Country(), pageNum, pageSize);
    }
}