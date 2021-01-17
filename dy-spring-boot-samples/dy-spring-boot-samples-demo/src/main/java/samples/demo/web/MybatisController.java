package samples.demo.web;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import samples.mybatis.model.Country;
import samples.mybatis.service.CountryService;

@RestController
@RequestMapping("/api/mybatis")
public class MybatisController {
    @Autowired
    CountryService countryService;

    @RequestMapping("/country/list")
    public PageInfo<Country> countryList(@Param("pageNum") int pageNum,
                                         @Param("pageSize") int pageSize) {
        return countryService.getAll(new Country(), pageNum, pageSize);
    }
}