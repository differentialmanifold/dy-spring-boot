package samples.mybatis.service;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import samples.mybatis.base.DyService;
import samples.mybatis.mapper.UserInfoMapper;
import samples.mybatis.model.UserInfo;

import java.util.List;

@Service
public class UserInfoService extends DyService<UserInfoMapper, UserInfo> {

    public List<UserInfo> getAll() {
        return selectAll();
    }

    public UserInfo getById(Integer id) {
        return selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        deleteByPrimaryKey(id);
    }

    public void save(UserInfo country) {
        if (country.getId() != null) {
            updateByPrimaryKey(country);
        } else {
            insert(country);
        }
    }

    @Transactional
    public void testTransaction(UserInfo userInfo) {

        insertSelective(userInfo);

        insertSelective(userInfo);
    }
}