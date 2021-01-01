package samples.mybatis.service;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import samples.mybatis.model.Country;

@SpringBootTest
public class CountryServiceTest {
    @Autowired
    private CountryService countryService;

    @Test
    void testGetAll() throws Exception {
        Country country = new Country();
        country.setCountryname("C");
        PageInfo<Country> countryPageInfo = countryService.getAll(country, 1, 5);
        Assertions.assertTrue(countryPageInfo.getList().size() <= 5);
    }
}
