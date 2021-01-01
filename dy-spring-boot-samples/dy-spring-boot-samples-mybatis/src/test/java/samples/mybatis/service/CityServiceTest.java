package samples.mybatis.service;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import samples.mybatis.model.City;
import samples.mybatis.service.CityService;

import java.util.List;

@SpringBootTest
public class CityServiceTest {
    @Autowired
    private CityService cityService;

    @Test
    void testGetAll() throws Exception {
        PageInfo<City> pageInfo = cityService.getAll(1, 10);
        Assertions.assertTrue(pageInfo.getTotal() > 0);
    }
}
