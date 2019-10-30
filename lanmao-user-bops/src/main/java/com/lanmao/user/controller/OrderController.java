package com.lanmao.user.controller;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.constants.ErrorCodeEnum;
import com.lanmao.common.lock.RedissonLockUtil;
import com.lanmao.core.share.dto.OrderDTO;
import com.lanmao.core.share.dto.UserDTO;
import com.lanmao.core.share.service.OrderService;
import com.lanmao.user.auth.LoginHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping(value = "/v1/order")
public class OrderController {

    @Resource
    private RedissonLockUtil redissonLock;

    @Resource
    private OrderService orderService;

    /**
     *
     * 下订单
     * @param bookDTO
     * @return
     */
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public BaseResult<String> book(@RequestBody OrderDTO bookDTO) {
        BaseResult<String> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        UserDTO loginDTO = LoginHolder.get();
        bookDTO.setUserId(loginDTO.getId());
        bookDTO.setCreator(loginDTO.getName());
        bookDTO.setModifier(loginDTO.getName());
        boolean lockResult = redissonLock.lock(loginDTO.getId() + "_book_order", 10);
        if (!lockResult) {
            baseResult.setCode(ErrorCodeEnum.CODE_20001.getCode());
            baseResult.setMessage("当前系统正忙,请稍候再试");
            return baseResult;
        }
        try {
            baseResult = orderService.bookOrder(bookDTO);
        } finally {
            redissonLock.unLock(loginDTO.getId() + "_book_order");
        }
        return baseResult;
    }
}
