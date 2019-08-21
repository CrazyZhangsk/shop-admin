/** 
 * <pre>项目名称:shop_admin_t1 
 * 文件名称:GrandController.java 
 * 包名:com.fh.shop.packend.controller.grand 
 * 创建日期:2018年12月28日上午9:10:27 
 * Copyright (c) 2018, Zhangsk_t@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.controller.brand;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fh.shop.backend.biz.brand.IBrandService;
import com.fh.shop.backend.biz.product.IProductService;
import com.fh.shop.backend.common.ResponseEnum;
import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.controller.BaseController;
import com.fh.shop.backend.po.brand.Brand;
import com.fh.shop.backend.po.product.Product;
/** 
 * <pre>项目名称：shop_admin_t1    
 * 类名称：GrandController    
 * 类描述：    
 * 创建人：zsk    
 * 联系方式：Zhangsk_t@163.com
 * 创建时间：2018年12月28日 上午9:10:27    
 * 修改人：zsk     
 * 修改时间：2018年12月28日 上午9:10:27    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
@RequestMapping("/brand")
public class BrandController extends BaseController{
	
	@Resource(name="brandService")
	private IBrandService grandService;
	
	@Resource
	private IProductService productService;
	
	@RequestMapping("findBrandList2")
	public String findBrandList2(ModelMap map,Integer flag){
			List<Brand> brandList = grandService.queryBrandList();
			map.put("brandList", brandList);
			if (flag==null) {
				return "brand2/list";
			}else{
				return "brand2/brandList";
			}
	}
	/**
	 * <pre>updateBrand(修改)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月28日 上午11:26:16    
	 * 修改人：zsk    
	 * 修改时间：2018年12月28日 上午11:26:16    
	 * 修改备注： 
	 * @return</pre>
	 */
	@RequestMapping("updateBrand")
	@ResponseBody
	public String updateBrand(Brand brand,HttpServletResponse response){
		grandService.updateBrand(brand);
		return "redirect:/toBrandList.jhtml";
	}
	/**
	 * <pre>toUpdate(回显)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月28日 上午11:25:37    
	 * 修改人：zsk    
	 * 修改时间：2018年12月28日 上午11:25:37    
	 * 修改备注： 
	 * @param id
	 * @return</pre>
	 */
	@RequestMapping(value="toUpdateById/{id}")
	public ModelAndView toUpdate(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/brand/update");
		Brand brand = grandService.toUpdate(id);
		mav.addObject("brand",brand);
		return mav;
	}
	/**
	 * <pre>insertBrand(增加数据)
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月28日 上午10:57:34    
	 * 修改人：zsk    
	 * 修改时间：2018年12月28日 上午10:57:34    
	 * 修改备注： 
	 * @param brand
	 * @return</pre>
	 */
	@RequestMapping("add")
	public void insertBrand(Brand brand){
		grandService.insertBrand(brand);
	}
	/**
	 * <pre>insertBrand(跳转到增加页面)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月28日 上午10:53:28    
	 * 修改人：zsk    
	 * 修改时间：2018年12月28日 上午10:53:28    
	 * 修改备注： 
	 * @return</pre>
	 */
	@RequestMapping("toAdd")
	public String toadd(){
		return "/brand/add";
	}
	/**
	 * <pre>deleteBatch(批量删除)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月28日 上午10:35:53    
	 * 修改人：zsk    
	 * 修改时间：2018年12月28日 上午10:35:53    
	 * 修改备注： </pre>
	 */
	@RequestMapping("deleteBatch")
	@ResponseBody
	public ServerResponse deleteBatch(String ids,HttpServletResponse response){
		Map<String,Object> retMap = new HashMap<>();
			List<Product> productList =  productService.findProductListByBrandId(ids);
			if (productList.size() == 0 || productList == null) {
				grandService.deleteBatch(ids);
				return ServerResponse.success();
			}else {
				return ServerResponse.error(ResponseEnum.RELEVANCE_ERROR);
			}

	}
	/**
	 * <pre>findBrandList(分页查询)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月28日 上午10:34:48    
	 * 修改人：zsk    
	 * 修改时间：2018年12月28日 上午10:34:48    
	 * 修改备注： 
	 * @param
	 * @param brand
	 * @param
	 * @return</pre>
	 */
	@RequestMapping("findBrandList")
	@ResponseBody
	public ServerResponse findBrandList(Brand brand,Integer start, Integer length, Integer draw){
		brand.setStartPos(start);
		brand.setPageSize(length);
		Long totalCount = grandService.findTotalCount(brand);
		List<Brand> retList = grandService.findBrandList(brand);
		for (Brand brandInfo : retList) {
			brandInfo.setEntryTimeStr(DateUtils.date2Str(brandInfo.getEntryTime(),DateUtils.FULL_YEAY));
			brandInfo.setUpdateTimeStr(DateUtils.date2Str(brandInfo.getUpdateTime(),DateUtils.FULL_YEAY));
		}
		return ServerResponse.success(DataTableResult.buildDataTableResult(retList,draw,totalCount,totalCount));
	}

	@RequestMapping("toBrandList")
	public String toBrandList(){
		return "brand/brandList";
	}

	@RequestMapping("initBrandList")
	@ResponseBody
	public ServerResponse initBrandList(){
		List<Brand> brandList = grandService.queryBrandList();
		return ServerResponse.success(brandList);
	}
}
