package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

import java.util.List;

@Data
public class OrderGuestDTO extends BaseBean {

    private Long id;

    private Long orderId;

    private String guestName;

    private String guestGender;

    private List<GuestProductDTO> guestProductList;

    private List<ProductDTO> productList;

    private List<MechDTO> mechList;
}
