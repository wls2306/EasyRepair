package com.tech.repair.controller;

import com.tech.repair.po.Order;
import com.tech.repair.service.OrderService;
import com.tech.repair.util.UploadPathUtil;
import com.tech.repair.vo.OrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@RestController
@Api(tags = "工单模块")
@RequestMapping("/api/order/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * @Author:Wls
     * @Date:15:29 2019/9/10
     * @Description: 新建工单
     */
    @ApiOperation(value = "添加新工单")
    @PostMapping("/")
    public Order addOrder(Order order, MultipartFile[] files, HttpServletRequest req)throws Exception
    {
        String imageUrl="";
        if (files !=null)
        {
            for (MultipartFile file : files) {
                int i=0;
                    String filepath=req.getSession().getServletContext().getRealPath("/")+"image\\order\\"+i+".jpg";

                    filepath= UploadPathUtil.getAbsolutePath(filepath);

                    File rs=new File(filepath);
                    if (rs.exists()) {
                        rs.mkdirs();
                    }
                    file.transferTo(rs);
                    imageUrl+="image/order/"+i+".jpg;";
                i++;
            }
            order.setOrderImage(imageUrl);
            return orderService.addOrder(order);
        }
        return null;
    }

    /**
     * @Author:Wls
     * @Date:16:28 2019/9/10
     * @Description:
     */
    @ApiOperation(value = "根据公司编号获取工单")
    @GetMapping("/company")
    public List<OrderVo> getOrderByCompanyId(String companyId)
    {
        return orderService.getOrderByCompanyId(companyId);
    }

    /**
     * @Author:Wls
     * @Date:16:28 2019/9/10
     * @Description:
     */
    @ApiOperation(value = "根据用户OpenId获取工单")
    @GetMapping("/user")
    public List<OrderVo> getOrderByUserOpenId(String userOpenId)
    {
        return orderService.getOrderByUserOpenId(userOpenId);
    }

    /**
     * @Author:Wls
     * @Date:16:35 2019/9/10
     * @Description:
     */
    @ApiOperation(value = "更新工单信息")
    @PutMapping("/")
    public Order updateOrder(Order o)
    {
        return orderService.updateOrder(o);
    }



}
