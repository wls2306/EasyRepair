package com.tech.repair.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.tech.repair.po.RepairOrder;
import com.tech.repair.service.RepairOrderService;
import com.tech.repair.util.DirectoryUtil;
import com.tech.repair.util.UploadPathUtil;
import com.tech.repair.vo.OrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@RestController
@Api(tags = "工单模块")
@RequestMapping("/api/repair_order/")
public class RepairOrderController {

    @Autowired
    private RepairOrderService repairOrderService;

    /**
     * @Author:Wls
     * @Date:15:29 2019/9/10
     * @Description: 新建工单
     */
    @ApiOperation(value = "添加新工单")
    @PostMapping("/")
    public RepairOrder addOrder(RepairOrder repairOrder, MultipartFile[] files)throws Exception
    {
        System.out.println("添加新工单");
        String imageUrl="";

        String repairOrderId="R"+repairOrder.getOrderCompanyId()+System.currentTimeMillis();

        repairOrder.setOrderId(repairOrderId);

        if (files !=null)
        {
            for (MultipartFile file : files) {

                int i=0;

                String staticPath= ClassUtils.getDefaultClassLoader().getResource("").getPath();

                String path= staticPath+"static\\images\\repair_order\\";

                System.out.println(repairOrderId);

                path+=repairOrderId;

                DirectoryUtil du=new DirectoryUtil();

                du.createParentDir(path);

                String filepath= UploadPathUtil.getAbsolutePath(path);

                File rs=new File(filepath,i+".jpg");
                    /*if (!rs.exists()) {
                        rs.mkdirs();
                    }*/

                    file.transferTo(rs);
                    imageUrl+="images/repair_order/"+repairOrderId+"/"+i+".jpg;";
                i++;
            }
        }
        repairOrder.setOrderImage(imageUrl);
        repairOrder.setOrderCreateTime(DateUtil.now());
        return repairOrderService.addOrder(repairOrder);
    }

    /**
     * @Author:Wls
     * @Date:16:28 2019/9/10
     * @Description: 根据公司编号获取工单
     */
    @ApiOperation(value = "根据公司编号获取工单")
    @GetMapping("/company")
    public List<OrderVo> getOrderByCompanyId(String companyId)
    {
        return repairOrderService.getOrderByCompanyId(companyId);
    }
    /**
     * @Author:Wls
     * @Date:16:28 2019/9/10
     * @Description: 根据用户OpenId获取工单
     */
    @ApiOperation(value = "根据用户OpenId获取工单")
    @GetMapping("/user")
    public List<OrderVo> getOrderByUserOpenId(String userOpenId)
    {
        return repairOrderService.getOrderByUserOpenId(userOpenId);
    }

    /**
     * @Author:Wls
     * @Date:16:35 2019/9/10
     * @Description:更新工单信息
     */
    @ApiOperation(value = "更新工单信息",notes = "请保证工单号 orderId 非空！！")
    @PutMapping("/")
    public RepairOrder updateOrder(RepairOrder o)
    {
        return repairOrderService.updateOrder(o);
    }

    /**
     * @Author:Wls
     * @Date:8:15 2019/9/11
     * @Description: 通过openId和companyId 查询唯一的工单
     */
    @ApiOperation(value = "通过openId和companyId 查询唯一的工单")
    @GetMapping("/oc")
    public OrderVo getOrderByUserOpenIdAndCompanyId(String userOpenId,String companyId)
    {
        return repairOrderService.getOrderByOpenIdAndCompanyId(companyId, userOpenId);
    }

    /**
     * @Author:Wls
     * @Date:8:19 2019/9/11
     * @Description: 通过orderId查询工单
     */
    @ApiOperation(value = "通过orderId查询工单")
    @GetMapping("/")
    public OrderVo getOrderByOrderId(String orderId)
    {
        return repairOrderService.getOrderByOrderId(orderId);
    }

}
