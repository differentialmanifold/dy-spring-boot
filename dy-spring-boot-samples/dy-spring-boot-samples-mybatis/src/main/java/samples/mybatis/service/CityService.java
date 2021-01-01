package samples.mybatis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import samples.mybatis.mapper.CityMapper;
import samples.mybatis.model.City;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityMapper cityMapper;

    public PageInfo<City> getAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<City> cityList = cityMapper.selectAll();
        PageInfo<City> pageInfo = new PageInfo<>(cityList);
        return pageInfo;
    }

    public City getById(Integer id) {
        return cityMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        cityMapper.deleteByPrimaryKey(id);
    }

    public void save(City country) {
        if (country.getId() != null) {
            cityMapper.updateByPrimaryKey(country);
        } else {
            cityMapper.insert(country);
        }
    }
}
