package com.lanmao.core.share.service;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.bean.PageDTO;
import com.lanmao.core.share.constants.Constants;
import com.lanmao.core.share.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = Constants.SERVICE_NAME)
@RequestMapping(value = "/api/order")
public interface OrderService {

    /**
     *
     * 保存核心对象
     * @param saveObject
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    BaseResult<Long> save(@RequestBody OrderDTO saveObject);


    /**
     * 查询List
     * @param queryObj
     * @return
     */
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    BaseResult<List<OrderDTO>> queryList(@RequestBody OrderDTO queryObj);


    /**
     * 查询对象
     * @param queryObj
     * @return
     */
    @RequestMapping(value = "/queryOne", method = RequestMethod.POST)
    BaseResult<OrderDTO> queryOne(@RequestBody OrderDTO queryObj);


    /**
     *
     * 根据主键Id更新
     * @param updateObj
     * @return
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    BaseResult<Integer> updateById(@RequestBody OrderDTO updateObj);


    /**
     *
     * 根据主键删除
     * @param deleteObj
     * @return
     */
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    BaseResult<Integer> deleteById(@RequestBody OrderDTO deleteObj);


    /**
     * 分页查询
     * @param pageDTO
     * @return
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
    BaseResult<PageDTO<OrderDTO>> queryPage(@RequestBody PageDTO<OrderDTO> pageDTO);
}
