/** 
 * <pre>项目名称:shop_admin_t1 
 * 文件名称:GrandServiceImpl.java 
 * 包名:com.fh.shop.packend.biz.grand 
 * 创建日期:2018年12月28日上午9:13:28 
 * Copyright (c) 2018, Zhangsk_t@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.brand;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fh.shop.utils.RedisUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fh.shop.backend.mapper.brand.IBrandMapper;
import com.fh.shop.backend.po.brand.Brand;
import com.fh.shop.utils.DateUtils;
import redis.clients.jedis.Jedis;

/** 
 * <pre>项目名称：shop_admin_t1    
 * 类名称：GrandServiceImpl    
 * 类描述：    
 * 创建人：zsk    
 * 联系方式：Zhangsk_t@163.com
 * 创建时间：2018年12月28日 上午9:13:28    
 * 修改人：zsk     
 * 修改时间：2018年12月28日 上午9:13:28    
 * 修改备注：       
 * @version </pre>    
 */
@Service(value="brandService")
public class BrandServiceImpl implements IBrandService {

	@Autowired
	private IBrandMapper brandMapper;

	@Override
	public Long findTotalCount(Brand brand) {
		return brandMapper.findTotalCount(brand);
	}

	@Override
	public List<Brand> findBrandList(Brand brand) {
		List<Brand> brandList = brandMapper.findBrandList(brand);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (Brand list : brandList) {
				if (list.getEntryTime()!=null) {
					list.setEntryTimeStr(sdf.format(list.getEntryTime()));
				}
			}
		return brandList;
	}

	@Override
	public void deleteBatch(String ids) {
		List<Integer> idsList = new ArrayList<>();
		if (StringUtils.isNotEmpty(ids)) {
			String[] split = ids.split(",");
			for (String idsArr : split) {
				idsList.add(Integer.valueOf(idsArr));
			}
		}
		if (idsList.size()>0){
			brandMapper.deleteBatch(idsList);
		}
		RedisUtil.delete("brandList");

	}

	@Override
	public void insertBrand(Brand brand) {
		Date dateNow = DateUtils.getDateNow();
		brand.setUpdateTime(dateNow);
		brand.setEntryTime(dateNow);
		brandMapper.insertBrand(brand);
		RedisUtil.delete("brandList");
	}

	@Override
	public Brand toUpdate(Integer id) {
		return brandMapper.toUpdate(id);
	}

	@Override
	public void updateBrand(Brand brand) {
		brand.setUpdateTime(DateUtils.getDateNow());
		brandMapper.updateBrand(brand);
		RedisUtil.delete("brandList");
	}

	@Override
	public List<Brand> queryBrandList() {
		String brandList = RedisUtil.get("brandList");
		Gson gson = new Gson();
		if (StringUtils.isEmpty(brandList)) {
			List<Brand> list = brandMapper.queryBrandList();
			String json = gson.toJson(list);
			RedisUtil.set("brandList", json);
			return list;
		}

		Type type = new TypeToken<List<Brand>>() {
		}.getType();
		List<Brand> brands = gson.fromJson(brandList, type);
		return brands;
	}
}
