package samples.mybatis.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import samples.mybatis.model.UserInfo;

import java.util.List;

@SpringBootTest
public class UserInfoServiceTest {
    @Autowired
    private UserInfoService userInfoService;

    @Test
    void testGetAll() throws Exception {
        List<UserInfo> userInfoList = userInfoService.getAll();
        Assertions.assertTrue(userInfoList.size() > 0);
    }
}
