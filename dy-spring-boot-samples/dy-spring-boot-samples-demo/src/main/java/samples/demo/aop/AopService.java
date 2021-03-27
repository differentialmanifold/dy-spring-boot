package samples.demo.aop;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("aopService")
public class AopService {
    @RequestLog(name = "user1", description = "this is user1")
    public Map<String, String> getAllUser(String input) throws Exception {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("input", input);
        return hashMap;
    }
}
