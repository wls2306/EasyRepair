package com.tech.repair.controller;

import com.tech.repair.po.Company;
import com.tech.repair.service.CompanyService;
import com.tech.repair.util.UploadPathUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Api(tags = "单位模块")
@RestController
@RequestMapping("/api/company/")
public class CompanyController {

    /**
    * @Author: Wls
    * @Date: 23:49 2019/9/7
    * @Description:
     *
     * ☆☆☆☆☆☆☆☆☆ 注意 ☆☆☆☆☆☆☆☆☆
     * 数据库设有约束：
     *  Unknown error 1452 === 约束出错
     *  Company 表中的数据一旦删除
     *  其所有相关数据会一并删除
     *  Company 表中的数据一旦更改
     *  其所有相关数据会一并更改
    */


    private final Logger logger=LoggerFactory.getLogger(getClass());

    @Autowired
    private CompanyService companyService;

    /**
     * 保存单位信息
     * 同时若信息中包含图片，则处理上传图片
     */
    @PostMapping("/")
    @ApiOperation(value = "新建单位记录",notes = " 前端form属性需要为： enctype=\"multipart/form-data\" 单位编号不要填，自动生成，填了也没用！")
    public Company createCompany(HttpServletRequest req,Company c, MultipartFile[] files)throws Exception
    {
        if (files!=null)
        {
            String imgPath="";
            for (int i=0;i<files.length;i++)
            {
                MultipartFile file=files[i];
                /**
                 *  上传路径为
                 *  images/company/{companyId}/{companyId}-{i}.jpg
                 */
                String filepath= req.getSession().getServletContext().getRealPath("/")+"images\\company\\"+c.getCompanyId()+"\\"+c.getCompanyId()+"-"+i+".jpg";
                /**
                 * 由于 Linux系统与 Windows系统的文件目录符号不同
                 * 因此 UploadPathUtil 会根据操作系统对上传路径Path进行转化
                 * 保证上传服务的通用性
                 */
                filepath= UploadPathUtil.getAbsolutePath(filepath);
                /**
                 * 保存文件
                 */
                File file1=new File(filepath);
                if (!file1.exists()) {
                    file1.mkdirs();
                }

                file.transferTo(new File(filepath));

                /**
                 * 存入数据库的path 为url格式即可
                 */
                String path="image/company/"+c.getCompanyId()+"/"+c.getCompanyId()+"-"+i+".jpg";

                /**
                 * 若有多张图片则路径之间使用 ；分隔
                 */
                imgPath+=path+";";
            }
            c.setCompanyImage(imgPath);
            return (Company)companyService.saveCompany(c);
        }
        return (Company)companyService.saveCompany(c);
    }



    /**
     * @Author:Wls
     * @Date:14:59 2019/9/6
     * @Description: 获取所有单位
     */
    @GetMapping("/all")
    @ApiOperation(value ="获取所有单位记录" )
    public List<Company> getAllCompany()
    {
        return  (List<Company>) companyService.getCompany("all");
    }

    /**
     * @Author:Wls
     * @Date:15:13 2019/9/6
     * @Description: 根据编号获取单位信息
     */

    @GetMapping("/{companyId}")
    @ApiOperation(value ="根据CompanyId单位记录" )
    public Company getCompanyByCompanyId(@PathVariable("companyId")String companyId)
    {
        return (Company) companyService.getCompany(companyId);
    }
    /**
     * @Author:Wls
     * @Date:15:22 2019/9/6
     * @Description: 更新单位信息
     */
    @PutMapping("/")
    @ApiOperation(value = "更新单位信息")
    public Company updateCompanyByCompanyId(Company company)
    {
        return (Company) companyService.updateCompany(company);
    }
    /**
     * @Author:Wls
     * @Date:15:22 2019/9/6
     * @Description: 搜索单位信息
     */
    @GetMapping("/search")
    @ApiOperation(value = "根据单位名称搜索单位")
    public List<Company> searchCompanyByCompanyName(String companyName)
    {
        return companyService.searchCompanyByName("%"+companyName+"%");
    }
    /**
    * @Author: Wls
    * @Date: 0:18 2019/9/8
    * @Description: 根据编号删除单位
    */
    @DeleteMapping("/")
    @ApiOperation(value = "根据编号删除单位",notes = "☆注意！执行此操作后，数据库中所有与CompanyId 相同的数据均会被删除！☆")
    public boolean deleteCompanyByCompanyId(String companyId)
    {
        return companyService.deleteCompanyByCompanyId(companyId);
    }

}
