package com.lanmao.core.service;

import com.alibaba.fastjson.JSON;
import com.lanmao.common.base.BaseService;
import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.bean.PageDTO;
import com.lanmao.common.constants.ErrorCodeEnum;
import com.lanmao.common.constants.GenderEnum;
import com.lanmao.common.constants.PayStatusEnum;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.repository.GuestProductRepository;
import com.lanmao.core.repository.OrderGuestRepository;
import com.lanmao.core.repository.OrderMechRepository;
import com.lanmao.core.repository.OrderRepository;
import com.lanmao.core.share.dto.*;
import com.lanmao.core.share.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.annotation.OrderUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private OrderGuestRepository orderGuestRepository;

    @Resource
    private GuestProductRepository guestProductRepository;

    @Resource
    private OrderMechRepository orderMechRepository;

    @Override
    public BaseResult<Long> save(@RequestBody OrderDTO orderDTO) {
        BaseResult<Long> baseResult = new BaseResult<>();
        baseResult.setCode(ErrorCodeEnum.CODE_SUCCESS.getCode());
        try {
            Long newId = orderRepository.save(orderDTO);
            baseResult.setData(newId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return baseResult;
    }

    @Override
    public BaseResult<List<OrderDTO>> queryList(@RequestBody OrderDTO queryObj) {
        return null;
    }

    @Override
    public BaseResult<OrderDTO> queryOne(@RequestBody OrderDTO queryObj) {
        return null;
    }

    @Override
    public BaseResult<Integer> updateById(@RequestBody OrderDTO updateObj) {
        return null;
    }

    @Override
    public BaseResult<Integer> deleteById(@RequestBody OrderDTO deleteObj) {
        return null;
    }

    @Override
    public BaseResult<PageDTO<OrderDTO>> queryPage(@RequestBody PageDTO<OrderDTO> pageDTO) {
        log.info("pageDTO: {}", JSON.toJSONString(pageDTO));
        BaseResult<PageDTO<OrderDTO>> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        OrderDTO params = pageDTO.getParams();
        if (params == null) {
            params = new OrderDTO();
            pageDTO.setParams(params);
        }
        pageDTO.setDefaultValue();
        final Integer page = pageDTO.getPage();
        final Integer pageSize = pageDTO.getPageSize();
        final Integer offset = (page - 1) * pageSize;
        List<OrderDTO> list = orderRepository.queryList(params);
        final int totalCount = orderRepository.countQueryList(params);
        pageDTO.setTotalCount(totalCount);
        pageDTO.setList(list);
        baseResult.setData(pageDTO);
        return baseResult;
    }

    @Override
    @Transactional
    public BaseResult<String> bookOrder(@RequestBody OrderDTO bookDTO) {
        log.info("bookOrder: {}", JSON.toJSONString(bookDTO));
        BaseResult<String> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        //校验
        CommonUtils.checkParams(bookDTO.getUserId() == null, "userId不能为空");
        CommonUtils.checkParams(StringUtils.isEmpty(bookDTO.getAddress()), "address不能为空");
        CommonUtils.checkParams(StringUtils.isEmpty(bookDTO.getLinkMobile()), "linkMobile不能为空");
        CommonUtils.checkParams(StringUtils.isEmpty(bookDTO.getLinkName()), "linkName不能为空");
        CommonUtils.checkParams(bookDTO.getBookTime() == null, "bookTime不能为空");
        CommonUtils.checkParams(CollectionUtils.isEmpty(bookDTO.getGuestList()), "guestList不能为空");
        for (OrderGuestDTO guest: bookDTO.getGuestList()) {
            List<ProductDTO> productList = guest.getProductList();
            CommonUtils.checkParams(CollectionUtils.isEmpty(productList), "productList不能为空");
            for (ProductDTO productDTO: productList) {
                CommonUtils.checkParams(productDTO.getId() == null, "productId不能为空");
            }
        }
        //入库
        String modifier = bookDTO.getModifier();
        bookDTO.setOrderNo(CommonUtils.genOrderNo());
        Long newOrderId = orderRepository.save(bookDTO);
        List<Long> orderMechIds = new ArrayList<>();
        int guestIndex = 1;
        for (OrderGuestDTO guest: bookDTO.getGuestList()) {
            guest.setOrderId(newOrderId);
            guest.setCreator(modifier);
            guest.setModifier(modifier);
            guest.setGuestName("预约人" + guestIndex++);
            guest.setGuestGender(GenderEnum.M.getCode());
            Long newGuestId = orderGuestRepository.save(guest);
            List<ProductDTO> productList = guest.getProductList();
            if (CollectionUtils.isNotEmpty(productList)) {
                for (ProductDTO productDTO: guest.getProductList()) {
                    GuestProductDTO guestProduct = new GuestProductDTO();
                    guestProduct.setGuestId(newGuestId);
                    guestProduct.setCreator(modifier);
                    guestProduct.setModifier(modifier);
                    guestProduct.setProductId(productDTO.getId());
                    guestProduct.setProductName(productDTO.getName());
                    guestProduct.setProductPrice(productDTO.getSellPrice());
                    guestProduct.setPayStatus(PayStatusEnum.NOT_PAY.getCode());
                    guestProductRepository.save(guestProduct);
                }
            }
            List<MechDTO> mechList = guest.getMechList();
            if (CollectionUtils.isNotEmpty(mechList)) {
                for (MechDTO mech: mechList) {
                    orderMechIds.add(mech.getId());
                }
            }
        }
        if (CollectionUtils.isNotEmpty(orderMechIds)) {
            for (Long mechId: orderMechIds) {
                OrderMechDTO saveMechDTO = new OrderMechDTO();
                saveMechDTO.setCreator(modifier);
                saveMechDTO.setModifier(modifier);
                saveMechDTO.setOrderId(newOrderId);
                saveMechDTO.setMechId(mechId);
                orderMechRepository.save(saveMechDTO);
            }
        }
        return baseResult;
    }

    @Override
    public BaseResult<String> payNotify(@RequestBody OrderDTO orderDTO) {
        return null;
    }
}
