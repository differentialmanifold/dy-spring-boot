package samples.demo.web;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import samples.mybatis.model.Country;
import samples.mybatis.service.CountryService;

@RestController
@RequestMapping("/api/mybatis")
public class MybatisController {
    @Autowired
    CountryService countryService;

    @RequestMapping(value = "/country/list", method = RequestMethod.GET)
    public PageInfo<Country> countryList(@RequestParam("pageNum") int pageNum,
                                         @RequestParam("pageSize") int pageSize) {
        return countryService.getAll(new Country(), pageNum, pageSize);
    }
}