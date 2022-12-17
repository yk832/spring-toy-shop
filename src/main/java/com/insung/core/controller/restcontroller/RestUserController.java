package com.insung.core.controller.restcontroller;


import com.insung.core.dto.UserDto;
import com.insung.core.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class RestUserController {

    private final UserMapper userMapper;

    @GetMapping("/idCheck")
    public String idCheck(@RequestParam String userId) throws Exception {
        Optional<UserDto> user = userMapper.findUser(userId);
        if (user.isPresent()) {
            log.info("user : {} ", user.get().toString());
            return "2";
        }
        return "1";
    }

    @PostMapping("/makeTBL")
    public Map<String, Object> makeTable(@RequestBody MultiValueMap<String,String> formData) {
        log.info("make tbl..");

        int draw = Integer.parseInt(formData.get("draw").get(0));
        int start = Integer.parseInt(formData.get("start").get(0));
        int length = Integer.parseInt(formData.get("length").get(0));

        log.info("draw = {}",draw);
        log.info("start = {}",start);
        log.info("length = {}",length);

        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", "testID");
        map1.put("name", "testName");
        map1.put("mail", "testMail");

        List<Map<String,Object>> list = new ArrayList<>();
        list.add(0, map1);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", list);
        resultMap.put("draw", 1);
        resultMap.put("recordsTotal", 9);
        resultMap.put("recordsFiltered", 5);

        System.out.println("resultMap = " + resultMap);

        return resultMap;
    }
}
