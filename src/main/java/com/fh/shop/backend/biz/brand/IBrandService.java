/** 
 * <pre>项目名称:shop_admin_t1 
 * 文件名称:IGrandService.java 
 * 包名:com.fh.shop.packend.biz.grand 
 * 创建日期:2018年12月28日上午9:12:28 
 * Copyright (c) 2018, Zhangsk_t@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.brand;

import java.util.List;

import com.fh.shop.backend.po.brand.Brand;
import com.fh.shop.backend.po.product.Product;

/** 
 * <pre>项目名称：shop_admin_t1    
 * 类名称：IGrandService    
 * 类描述：    
 * 创建人：zsk    
 * 联系方式：Zhangsk_t@163.com
 * 创建时间：2018年12月28日 上午9:12:28    
 * 修改人：zsk     
 * 修改时间：2018年12月28日 上午9:12:28    
 * 修改备注：       
 * @version </pre>    
 */
public interface IBrandService {

	/** <pre>findTotalCount(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月28日 上午10:00:08    
	 * 修改人：zsk    
	 * 修改时间：2018年12月28日 上午10:00:08    
	 * 修改备注： 
	 * @param brand
	 * @return</pre>    
	 */
	Long findTotalCount(Brand brand);

	/** <pre>findBrandList(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月28日 上午10:00:41    
	 * 修改人：zsk    
	 * 修改时间：2018年12月28日 上午10:00:41    
	 * 修改备注： 
	 * @param brand
	 * @return</pre>    
	 */
	List<Brand> findBrandList(Brand brand);

	/** <pre>deleteBatch(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月28日 上午10:41:01    
	 * 修改人：zsk    
	 * 修改时间：2018年12月28日 上午10:41:01    
	 * 修改备注： 
	 * @param ids</pre>    
	 */
	void deleteBatch(String ids);

	/** <pre>insertBrand(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月28日 上午10:58:06    
	 * 修改人：zsk    
	 * 修改时间：2018年12月28日 上午10:58:06    
	 * 修改备注： 
	 * @param brand</pre>    
	 */
	void insertBrand(Brand brand);

	/** <pre>toUpdate(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月28日 上午11:19:29    
	 * 修改人：zsk    
	 * 修改时间：2018年12月28日 上午11:19:29    
	 * 修改备注： 
	 * @param id
	 * @return</pre>    
	 */
	Brand toUpdate(Integer id);

	/** <pre>updateBrand(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月28日 上午11:27:51    
	 * 修改人：zsk    
	 * 修改时间：2018年12月28日 上午11:27:51    
	 * 修改备注： 
	 * @param brand</pre>    
	 */
	void updateBrand(Brand brand);

	/** <pre>queryBrandist(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月29日 下午2:37:29    
	 * 修改人：zsk    
	 * 修改时间：2018年12月29日 下午2:37:29    
	 * 修改备注： 
	 * @return</pre>    
	 */
	List<Brand> queryBrandList();

}
