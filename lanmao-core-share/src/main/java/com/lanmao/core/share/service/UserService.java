package com.lanmao.core.share.service;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.bean.PageDTO;
import com.lanmao.core.share.constants.Constants;
import com.lanmao.core.share.dto.ChargePackageDTO;
import com.lanmao.core.share.dto.LoginDTO;
import com.lanmao.core.share.dto.UserChargeRecordDTO;
import com.lanmao.core.share.dto.UserDTO;
import javafx.scene.chart.ValueAxis;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(value = Constants.SERVICE_NAME)
@RequestMapping(value = "/api/user")
public interface UserService {


    /**
     *
     * 保存核心对象
     * @param saveObject
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    BaseResult<Long> save(@RequestBody UserDTO saveObject);


    /**
     * 查询List
     * @param queryObj
     * @return
     */
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    BaseResult<List<UserDTO>> queryList(@RequestBody UserDTO queryObj);


    /**
     * 查询对象
     * @param queryObj
     * @return
     */
    @RequestMapping(value = "/queryOne", method = RequestMethod.POST)
    BaseResult<UserDTO> queryOne(@RequestBody UserDTO queryObj);


    /**
     *
     * 根据主键Id更新
     * @param updateObj
     * @return
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    BaseResult<Integer> updateById(@RequestBody UserDTO updateObj);


    /**
     *
     * 根据主键删除
     * @param deleteObj
     * @return
     */
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    BaseResult<Integer> deleteById(@RequestBody UserDTO deleteObj);


    /**
     *
     * 用户登录
     * @param loginDTO
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    BaseResult<UserDTO> login(@RequestBody LoginDTO loginDTO);


    /**
     *
     * 查询用户余额
     * @param userDTO
     * @return
     */
    @RequestMapping(value = "/queryBalance", method = RequestMethod.POST)
    BaseResult<BigDecimal>  queryBalance(@RequestBody UserDTO userDTO);


    /**
     *
     * 查询充值套餐
     * @param chargePackageDTO
     * @return
     */
    @RequestMapping(value = "/queryChargePackages", method = RequestMethod.POST)
    BaseResult<List<ChargePackageDTO>> queryChargePackages(@RequestBody ChargePackageDTO chargePackageDTO);


    /**
     *
     * 充值下单
     * @param bookParams
     * @return
     */
    @RequestMapping(value = "/bookCharge", method = RequestMethod.POST)
    BaseResult<UserChargeRecordDTO> bookCharge(@RequestBody UserChargeRecordDTO bookParams);


    /**
     *
     * 分页查询
     * @param pageDTO
     * @return
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
    BaseResult<PageDTO<UserDTO>> queryPage(@RequestBody PageDTO<UserDTO> pageDTO);
}
