package samples.mybatis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import samples.mybatis.base.DyService;
import samples.mybatis.mapper.CityMapper;
import samples.mybatis.model.City;

import java.util.List;

@Service
public class CityService extends DyService<CityMapper, City> {

    public PageInfo<City> getAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<City> cityList = selectAll();
        PageInfo<City> pageInfo = new PageInfo<>(cityList);
        return pageInfo;
    }

    public City getById(Integer id) {
        return selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        deleteByPrimaryKey(id);
    }

    public void save(City country) {
        if (country.getId() != null) {
            updateByPrimaryKey(country);
        } else {
            insert(country);
        }
    }
}
