/** 
 * <pre>项目名称:shop_admin_t1 
 * 文件名称:IProductMapper.java 
 * 包名:com.fh.shop.packend.mapper.product 
 * 创建日期:2018年12月24日下午8:59:57 
 * Copyright (c) 2018, Zhangsk_t@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.mapper.product;

import java.util.List;

import com.fh.shop.backend.api.product.ProductVO;
import com.fh.shop.backend.po.product.Product;

/** 
 * <pre>项目名称：shop_admin_t1    
 * 类名称：IProductMapper    
 * 类描述：    
 * 创建人：zsk    
 * 联系方式：Zhangsk_t@163.com
 * 创建时间：2018年12月24日 下午8:59:57    
 * 修改人：zsk     
 * 修改时间：2018年12月24日 下午8:59:57    
 * 修改备注：       
 * @version </pre>    
 */
public interface IProductMapper {

	/** <pre>addProductInfo(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月24日 下午9:00:29    
	 * 修改人：zsk    
	 * 修改时间：2018年12月24日 下午9:00:29    
	 * 修改备注： 
	 * @param product</pre>    
	 */
	void addProductInfo(Product product);

	/** <pre>queryProductInfo(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月24日 下午9:37:10    
	 * 修改人：zsk    
	 * 修改时间：2018年12月24日 下午9:37:10    
	 * 修改备注： 
	 * @return</pre>    
	 */
	List<Product> queryProductInfo();

	/** <pre>toUpdateById(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月24日 下午9:59:53    
	 * 修改人：zsk    
	 * 修改时间：2018年12月24日 下午9:59:53    
	 * 修改备注： 
	 * @param id
	 * @return</pre>    
	 */
	Product toUpdateById(Integer id);

	/** <pre>deleteProduct(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月25日 下午2:43:41    
	 * 修改人：zsk    
	 * 修改时间：2018年12月25日 下午2:43:41    
	 * 修改备注： 
	 * @param id</pre>    
	 */
	void deleteProduct(Integer id);

	/** <pre>updateProduct(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月25日 下午5:14:27    
	 * 修改人：zsk    
	 * 修改时间：2018年12月25日 下午5:14:27    
	 * 修改备注： 
	 * @param product</pre>    
	 */
	void updateProduct(Product product);

	/** <pre>deleteBatch(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月25日 下午6:55:39    
	 * 修改人：zsk    
	 * 修改时间：2018年12月25日 下午6:55:39    
	 * 修改备注： 
	 * @param idsList</pre>    
	 */
	void deleteBatch(List<Integer> idsList);

	/** <pre>findTotalCount(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月27日 上午10:41:25    
	 * 修改人：zsk    
	 * 修改时间：2018年12月27日 上午10:41:25    
	 * 修改备注： 
	 * @param product
	 * @return</pre>    
	 */
	Long findTotalCount(Product product);

	/** <pre>searchData(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月27日 上午10:41:28    
	 * 修改人：zsk    
	 * 修改时间：2018年12月27日 上午10:41:28    
	 * 修改备注： 
	 * @param product
	 * @return</pre>    
	 */
	List<Product> searchData(Product product);

	/** <pre>findProductListByBrandId(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2019年1月2日 下午7:38:54    
	 * 修改人：zsk    
	 * 修改时间：2019年1月2日 下午7:38:54    
	 * 修改备注： 
	 * @param idsList
	 * @return</pre>    
	 */
	List<Product> findProductListByBrandId(List<Integer> idsList);

	List<Product> exportProductExcel(Product product);

    List<Product> findBatchProductImage(List<Integer> idsList);

    List<Product> findProductList();
}
