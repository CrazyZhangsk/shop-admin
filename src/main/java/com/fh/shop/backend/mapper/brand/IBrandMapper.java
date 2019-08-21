/** 
 * <pre>项目名称:shop_admin_t1 
 * 文件名称:IBrandMapper.java 
 * 包名:com.fh.shop.packend.mapper.brand 
 * 创建日期:2018年12月28日上午9:32:19 
 * Copyright (c) 2018, Zhangsk_t@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.mapper.brand;

import java.util.List;

import com.fh.shop.backend.po.brand.Brand;
import com.fh.shop.backend.po.product.Product;

/** 
 * <pre>项目名称：shop_admin_t1    
 * 类名称：IBrandMapper    
 * 类描述：    
 * 创建人：zsk    
 * 联系方式：Zhangsk_t@163.com
 * 创建时间：2018年12月28日 上午9:32:19    
 * 修改人：zsk     
 * 修改时间：2018年12月28日 上午9:32:19    
 * 修改备注：       
 * @version </pre>    
 */
public interface IBrandMapper {

	/** <pre>findTotalCount(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月28日 上午10:00:35    
	 * 修改人：zsk    
	 * 修改时间：2018年12月28日 上午10:00:35    
	 * 修改备注： 
	 * @param brand
	 * @return</pre>    
	 */
	Long findTotalCount(Brand brand);

	/** <pre>findBrandList(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月28日 上午10:02:07    
	 * 修改人：zsk    
	 * 修改时间：2018年12月28日 上午10:02:07    
	 * 修改备注： 
	 * @param brand
	 * @return</pre>    
	 */
	List<Brand> findBrandList(Brand brand);

	/** <pre>deleteBatch(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月28日 上午10:44:38    
	 * 修改人：zsk    
	 * 修改时间：2018年12月28日 上午10:44:38    
	 * 修改备注： 
	 * @param idsList</pre>    
	 */
	void deleteBatch(List<Integer> idsList);

	/** <pre>insertBrand(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月28日 上午10:58:42    
	 * 修改人：zsk    
	 * 修改时间：2018年12月28日 上午10:58:42    
	 * 修改备注： 
	 * @param brand</pre>    
	 */
	void insertBrand(Brand brand);

	/** <pre>toUpdate(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月28日 上午11:22:52    
	 * 修改人：zsk    
	 * 修改时间：2018年12月28日 上午11:22:52    
	 * 修改备注： 
	 * @param id
	 * @return</pre>    
	 */
	Brand toUpdate(Integer id);

	/** <pre>updateBrand(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月28日 上午11:28:53    
	 * 修改人：zsk    
	 * 修改时间：2018年12月28日 上午11:28:53    
	 * 修改备注： 
	 * @param brand</pre>    
	 */
	void updateBrand(Brand brand);

	/** <pre>queryBrandist(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月29日 下午2:38:27    
	 * 修改人：zsk    
	 * 修改时间：2018年12月29日 下午2:38:27    
	 * 修改备注： 
	 * @return</pre>    
	 */
	List<Brand> queryBrandList();

}
