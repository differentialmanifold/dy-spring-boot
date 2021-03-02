package samples.mybatis.service;

import club.differentialmanifold.dy.core.spring.boot.autoconfigure.service.crud.DyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import samples.mybatis.mapper.CountryMapper;
import samples.mybatis.model.Country;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CountryService extends DyService<CountryMapper, Country> {

    public PageInfo<Country> getAll(Country country, int pageNum, int pageSize) {
        Example example = new Example(Country.class);
        Example.Criteria criteria = example.createCriteria();
        if (country.getCountryname() != null && country.getCountryname().length() > 0) {
            criteria.andLike("countryname", "%" + country.getCountryname() + "%");
        }
        if (country.getCountrycode() != null && country.getCountrycode().length() > 0) {
            criteria.andLike("countrycode", "%" + country.getCountrycode() + "%");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Country> countryList = mapper.selectByExample(example);
        PageInfo<Country> pageInfo = new PageInfo<>(countryList);
        return pageInfo;
    }

    public Country getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void save(Country country) {
        if (country.getId() != null) {
            mapper.updateByPrimaryKey(country);
        } else {
            mapper.insert(country);
        }
    }
}
