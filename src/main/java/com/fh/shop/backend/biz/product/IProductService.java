/** 
 * <pre>项目名称:shop_admin_t1 
 * 文件名称:IProductService.java 
 * 包名:com.fh.shop.packend.biz.product 
 * 创建日期:2018年12月23日下午7:26:03 
 * Copyright (c) 2018, xxxxxx@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.product;

import java.util.List;
import java.util.Map;

import com.fh.shop.backend.api.product.ProductVO;
import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.po.FileInfo;
import com.fh.shop.backend.po.brand.Brand;
import com.fh.shop.backend.po.image.Image;
import com.fh.shop.backend.po.product.Product;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/** 
 * <pre>项目名称：shop_admin_t1    
 * 类名称：IProductService    
 * 类描述：    
 * 创建人：zsk    
 * 创建时间：2018年12月23日 下午7:26:03    
 * 修改人：zsk     
 * 修改时间：2018年12月23日 下午7:26:03    
 * 修改备注：       
 * @version </pre>    
 */
public interface IProductService {

	/** <pre>add(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk   
	 * 创建时间：2018年12月23日 下午7:29:18    
	 * 修改人：zsk    
	 * 修改时间：2018年12月23日 下午7:29:18    
	 * 修改备注： 
	 * @param product</pre>    
	 */
	void add(Product product,FileInfo uploadedFile,String rootPath,List<FileInfo> childFileList);

	/** <pre>queryProductInfo(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月24日 下午9:36:41    
	 * 修改人：zsk    
	 * 修改时间：2018年12月24日 下午9:36:41    
	 * 修改备注： 
	 * @return</pre>    
	 */
	List<Product> queryProductInfo();

	/** <pre>toUpdateById(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月24日 下午9:59:12    
	 * 修改人：zsk    
	 * 修改时间：2018年12月24日 下午9:59:12    
	 * 修改备注： 
	 * @param
	 * @return</pre>    
	 */
	Product toUpdateById(Integer id);

	/** <pre>deleteProduct(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月25日 下午2:42:58    
	 * 修改人：zsk    
	 * 修改时间：2018年12月25日 下午2:42:58    
	 * 修改备注： 
	 * @param id</pre>    
	 */
	void deleteProduct(Integer id);

	/** <pre>updateProduct(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月25日 下午5:13:41    
	 * 修改人：zsk    
	 * 修改时间：2018年12月25日 下午5:13:41    
	 * 修改备注： 
	 * @param product</pre>    
	 */
	void updateProduct(Product product, FileInfo fileInfo,List<FileInfo> fileList,String rootPath);

	/** <pre>deleteBatch(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月25日 下午6:52:56    
	 * 修改人：zsk    
	 * 修改时间：2018年12月25日 下午6:52:56    
	 * 修改备注： 
	 * @param ids</pre>    
	 */
	void deleteBatch(String ids);

	/** <pre>findTotalCount(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月27日 上午10:40:12    
	 * 修改人：zsk    
	 * 修改时间：2018年12月27日 上午10:40:12    
	 * 修改备注： 
	 * @param product
	 * @return</pre>    
	 */
	Long findTotalCount(Product product);

	/** <pre>searchData(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2018年12月27日 上午10:40:18    
	 * 修改人：zsk    
	 * 修改时间：2018年12月27日 上午10:40:18    
	 * 修改备注： 
	 * @param product
	 * @return</pre>    
	 */
	List<Product> searchData(Product product);

	/** <pre>findProductListByBrandId(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2019年1月2日 下午7:34:34    
	 * 修改人：zsk    
	 * 修改时间：2019年1月2日 下午7:34:34    
	 * 修改备注： 
	 * @param ids
	 * @return</pre>    
	 */
	List<Product> findProductListByBrandId(String ids);

	List<Product>  exportProductExcel(Product product);

    List<Product> findBatchProductImage(String ids);

    DataTableResult getProductDateTable(Product product, Map<String,String[]> parameterMap);

    List<ProductVO> findProductList();

	void addProduct(Product product);

	void updateProduct(Product product, String rootPath);

	FileInfo uploadFile(FileInfo fileInfo);
}
