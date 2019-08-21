/** 
 * <pre>项目名称:shop_admin_t1 
 * 文件名称:ProductServiceImpl.java 
 * 包名:com.fh.shop.packend.biz.product 
 * 创建日期:2018年12月23日下午7:26:59 
 * Copyright (c) 2018, xxxxxx@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.product;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import com.fh.shop.backend.biz.BaseBiz;
import com.fh.shop.backend.biz.image.IImageService;
import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.common.SystemConstants;
import com.fh.shop.backend.po.FileInfo;
import com.fh.shop.backend.po.brand.Brand;
import com.fh.shop.backend.po.image.Image;
import com.fh.shop.backend.vo.ProductVO;
import com.fh.shop.utils.FileUtil;
import com.fh.shop.utils.HttpClientUtils;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fh.shop.backend.mapper.product.IProductMapper;
import com.fh.shop.backend.po.product.Product;
import com.fh.shop.utils.DateUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/** 
 * <pre>项目名称：shop_admin_t1    
 * 类名称：ProductServiceImpl    
 * 类描述：    
 * 创建人：zsk    
 * 创建时间：2018年12月23日 下午7:26:59    
 * 修改人：zsk     
 * 修改时间：2018年12月23日 下午7:26:59    
 * 修改备注：       
 * @version </pre>    
 */
@Service(value = "productService")
public class ProductServiceImpl extends BaseBiz implements IProductService {

	@Autowired
	private IProductMapper productMapper;

	@Resource(name="imageService")
	private IImageService imageService;
	@Override
	public void add(Product product,FileInfo uploadedFile,String rootPath,List<FileInfo> childFileList) {
		//增加主图片
		updateProductMainImg(product,uploadedFile,rootPath);
		Date dateNow = DateUtils.getDateNow();
		product.setEntryTime(dateNow);
		product.setUpdateTime(dateNow);
		productMapper.addProductInfo(product);
		//增加子图片
		updateProductChildImg(product,childFileList,rootPath);
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.packend.biz.product.IProductService#queryProductInfo()    
	 */
	@Override
	public List<Product> queryProductInfo() {
		
		return productMapper.queryProductInfo();
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.packend.biz.product.IProductService#toUpdateById(int)    
	 */
	@Override
	public Product toUpdateById(Integer id) {
		return productMapper.toUpdateById(id);
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.packend.biz.product.IProductService#deleteProduct(java.lang.Integer)    
	 */
	@Override
	public void deleteProduct(Integer id) {
		productMapper.deleteProduct(id);
		
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.packend.biz.product.IProductService#updateProduct(com.fh.shop.packend.po.product.Product)    
	 */
	@Override
	public void updateProduct(Product product, FileInfo fileInfo,List<FileInfo> fileList,String rootPath) {
		//更新主图片
		updateProductMainImg(product, fileInfo, rootPath);
		//更新子图片
		updateProductChildImg(product, fileList, rootPath);
		//更新修改时间
		product.setUpdateTime(DateUtils.getDateNow());
		//修改产品
		productMapper.updateProduct(product);		
	}

	private void updateProductChildImg(Product product, List<FileInfo> fileList, String rootPath) {
		//新增子图片信息
		addProductChildImgs(product, fileList, rootPath);
		//删除旧子图片信息
		deleteOldProductChildImg(product, rootPath);
	}

	private void deleteOldProductChildImg(Product product, String rootPath) {
		if (StringUtils.isNotEmpty(product.getImageIds())){
			String[] idsStr = product.getImageIds().split(",");
			List<Integer> idsList = new ArrayList<>();
			for (String ids : idsStr) {
				idsList.add(Integer.valueOf(ids));
			}
			imageService.delProductImage(idsList);
		}
		if (StringUtils.isNotEmpty(product.getImagePaths())){
			String[] pathStr = product.getImagePaths().split(",");
			for (String delPath : pathStr) {
				String realPath = rootPath+delPath;
				FileUtil.deleteFile(realPath);
			}
		}
	}

	private void addProductChildImgs(Product product, List<FileInfo> fileList, String rootPath) {
		List<Image> childImageList = new ArrayList<>();
		for (FileInfo image : fileList) {
			String filename = image.getFileName();
			InputStream inputStream = image.getIs();
			String realPath = rootPath+SystemConstants.PRODUCT_IMAGE_PATH;
			String uploadFileName = FileUtil.copyFile(inputStream, filename, realPath);
			Image photo = new Image();
			photo.setImagePath(SystemConstants.PRODUCT_IMAGE_PATH + uploadFileName);
			photo.setProduct(product);
			childImageList.add(photo);
		}
		if (childImageList.size()>0){
			imageService.addImage(childImageList);
		}

	}

	private void updateProductMainImg(Product product, FileInfo fileInfo, String rootPath) {
		if (fileInfo.getFileSize()>0){
			if (StringUtils.isNotEmpty(product.getProductImagePath())){
				String realPath = rootPath+product.getProductImagePath();
				/*删除旧图片*/
				FileUtil.deleteFile(realPath);
			}
			String originalFilename = fileInfo.getFileName();
			InputStream inputStream = fileInfo.getIs();
			String folderPath = rootPath+SystemConstants.PRODUCT_IMAGE_PATH;
			String uploadedName = FileUtil.copyFile(inputStream, originalFilename, folderPath);
			product.setProductImagePath(SystemConstants.PRODUCT_IMAGE_PATH+uploadedName);
		}
	}

	@Override
	public void deleteBatch(String ids) {
		String[] split = ids.split(",");
		List<Integer> idsList = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			idsList.add(Integer.valueOf(split[i]));
		}
		productMapper.deleteBatch(idsList);
	}

	@Override
		public Long findTotalCount(Product product) {
		return productMapper.findTotalCount(product);
	}

	@Override
	public List<Product> searchData(Product product) {
		return productMapper.searchData(product);
	}

	@Override
	public List<Product> findProductListByBrandId(String ids) {
		List<Integer> idsList = new ArrayList<>();
		if (StringUtils.isNotEmpty(ids)) {
			String[] idsArr = ids.split(",");
			for (String idsAr : idsArr) {
				idsList.add(Integer.valueOf(idsAr));
			}
		}
		return productMapper.findProductListByBrandId(idsList);
	}

	@Override
	public List<Product> exportProductExcel(Product product) {
		return productMapper.exportProductExcel(product);
	}


	@Override
	public List<Product> findBatchProductImage(String ids) {
		List<Integer> idsList = new ArrayList<>();
		if (StringUtils.isNotEmpty(ids)){
			String[] split = ids.split(",");
			for (String s : split) {
				idsList.add(Integer.valueOf(s));
			}
		}
		return productMapper.findBatchProductImage(idsList);
	}

	@Override
	public DataTableResult getProductDateTable(Product product, Map<String, String[]> parameterMap) {
		//排序
		buildProductOrder(product, parameterMap);
		//分页查询
		return buildProductSybase(product);
	}

	@Override
	public List<com.fh.shop.backend.api.product.ProductVO> findProductList() {
		List<Product> productList = productMapper.findProductList();
		List<com.fh.shop.backend.api.product.ProductVO> productVOList = new ArrayList<>();
		for (Product product : productList) {
			com.fh.shop.backend.api.product.ProductVO productVO = new com.fh.shop.backend.api.product.ProductVO();
			productVO.setBrandName(product.getBrand().getBrandName());
			productVO.setId(product.getId());
			productVO.setPrice(Double.valueOf(product.getProductPrice()));
			productVO.setProductName(product.getProductName());
			productVO.setProductImagePath(product.getProductImagePath());
			productVOList.add(productVO);
		}
		return productVOList;
	}

	@Override
	public void addProduct(Product product) {

		Date dateNow = DateUtils.getDateNow();
		product.setEntryTime(dateNow);
		product.setUpdateTime(dateNow);
		product.setProductImagePath(product.getMainImagePath());
		productMapper.addProductInfo(product);
		//上传新子图片
		uploadNewChildImg(product);
	}



	public void buildProductOrder(Product product, Map<String, String[]> parameterMap) {
		String field = "";
		String order = "";
		if (parameterMap.get(SystemConstants.ORDER_DIR)!=null){
			order = parameterMap.get(SystemConstants.ORDER_DIR)[0];
			String column = parameterMap.get(SystemConstants.ORDER_COLUMN)[0];
			String fieldTemp  = parameterMap.get(getOrderColumnsData(column))[0];
			/*String temp = (String) SystemConstants.FIELD_MAPPING_STATIC.get(fieldTemp);*/
			Gson gson = new Gson();
			Map map = gson.fromJson(SystemConstants.FIELD_MAPPING_STRING, Map.class);
			String temp = (String) map.get(fieldTemp);
			field = StringUtils.isEmpty((temp)) ? fieldTemp: temp;
		}
		product.setSort(order);
		product.setSortField(field);
	}

	public DataTableResult buildProductSybase(Product product) {
		//查询数据
		Long totalCount = findTotalCount(product);
		List<Product> retList = searchData(product);
		//po转vo
		List<ProductVO> productList = new ArrayList<>();
		for (Product productInfo : retList) {
			ProductVO productVO = new ProductVO();
			productVO.setId(productInfo.getId());
			productVO.setProductImagePath(productInfo.getProductImagePath());
			productVO.setEntryTimeStr(DateUtils.date2Str(productInfo.getEntryTime(), DateUtils.FULL_YEAY));
			productVO.setUpdateTimeStr(DateUtils.date2Str(productInfo.getUpdateTime(), DateUtils.FULL_YEAY));
			productVO.setProductName(productInfo.getProductName());
			productVO.setProductPrice(productInfo.getProductPrice());
			productVO.setBrandName(productInfo.getBrand().getBrandName());
			productList.add(productVO);
		}
		return DataTableResult.buildDataTableResult(productList, product.getDraw(), totalCount, totalCount);
	}

	@Override
	public void updateProduct(Product product, String rootPath) {
		//更新主图片
		if (StringUtils.isNotEmpty(product.getMainImagePath())){
			String realPath = rootPath+product.getProductImagePath();
			/*删除旧图片*/
			FileUtil.deleteFile(realPath);
			product.setProductImagePath(product.getMainImagePath());
		}
		//上传新子图片
		uploadNewChildImg(product);
		//删除旧子图片信息
		deleteOldProductChildImg(product, rootPath);
		//更新修改时间
		product.setUpdateTime(DateUtils.getDateNow());
		//修改产品
		productMapper.updateProduct(product);
	}

	@Override
	public FileInfo uploadFile(FileInfo fileInfo) {
		HttpClientUtils.upload("http://192.168.1.25:8082/api/cos/upload2COS.jhtml", fileInfo.getIs(),
				fileInfo.getFileName(), null);
		return null;
	}

	private void uploadNewChildImg(Product product) {
		String childImagePath = product.getChildImagePath();
		if (StringUtils.isNotEmpty(childImagePath)){
			List<Image> childImageList = new ArrayList<>();
			String[] split = childImagePath.substring(1,childImagePath.length()).split(",");
			for (String s : split) {
				Image image = new Image();
				image.setProduct(product);
				image.setImagePath(s);
				childImageList.add(image);
			}
			if (childImageList.size()>0){
				imageService.addImage(childImageList);
			}
		}
	}

}
