package com.lanmao.core.share.service;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.core.share.constants.Constants;
import com.lanmao.core.share.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = Constants.SERVICE_NAME)
@RequestMapping(value = "/api/user")
public interface UserService {


    @RequestMapping(value = "/query", method = RequestMethod.POST)
    BaseResult<UserDTO> query(@RequestBody UserDTO user);


    /**
     *
     * 查询List
     * @param queryObj
     * @return
     */
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    BaseResult<List<UserDTO>> queryList(@RequestBody UserDTO queryObj);


    /**
     *
     * 新增用户
     * @param userDTO
     * @return
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    BaseResult<Long> addUser(@RequestBody UserDTO userDTO);
}
