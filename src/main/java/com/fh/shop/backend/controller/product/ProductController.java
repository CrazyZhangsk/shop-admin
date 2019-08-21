/**
 * <pre>项目名称:shop_admin_t1
 * 文件名称:ProductController.java
 * 包名:com.fh.shop.packend.controller.product
 * 创建日期:2018年12月23日下午7:06:49
 * Copyright (c) 2018, xxxxxx@163.com All Rights Reserved.</pre>
 */
package com.fh.shop.backend.controller.product;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.shop.backend.annotation.LogContent;
import com.fh.shop.backend.biz.image.IImageService;
import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.common.SystemConstants;
import com.fh.shop.backend.po.FileInfo;
import com.fh.shop.backend.po.brand.Brand;
import com.fh.shop.backend.po.image.Image;
import com.fh.shop.utils.DateUtils;
import com.fh.shop.utils.FileUtil;
import net.sf.json.JSONObject;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fh.shop.backend.biz.brand.IBrandService;
import com.fh.shop.backend.biz.log.ILogService;
import com.fh.shop.backend.biz.product.IProductService;
import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.controller.BaseController;
import com.fh.shop.backend.po.log.LogInfo;
import com.fh.shop.backend.po.product.Product;
import com.fh.shop.backend.po.user.User;


/**
 * <pre>项目名称：shop_admin_t1
 * 类名称：ProductController
 * 类描述：
 * 创建人：zsk
 * 创建时间：2018年12月23日 下午7:06:49
 * 修改人：zsk
 * 修改时间：2018年12月23日 下午7:06:49
 * 修改备注：
 * @version </pre>
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

    /**
     * 注入product的service层
     */
    @Autowired
    private IProductService productService;
    /**
     * 注入品牌的service层
     */
    @Resource(name = "brandService")
    private IBrandService brandService;

    @Resource(name = "imageService")
    private IImageService imageService;

    @RequestMapping("uploadMainImage")
    @ResponseBody
    public Map<String, String> uploadFile(@RequestParam MultipartFile mainImage, HttpServletRequest request) throws IOException {
        FileInfo fileInfo = getUploadedFile(mainImage);
        String rootPath = getRootPath(request);
        String originalFilename = fileInfo.getFileName();
        InputStream inputStream = fileInfo.getIs();
        String folderPath = rootPath+SystemConstants.PRODUCT_IMAGE_PATH;
        String uploadedName = FileUtil.copyFile(inputStream, originalFilename, folderPath);
        System.out.println(folderPath+uploadedName);
        Map<String, String> retMap = new HashMap<>();
        retMap.put("uploadedName", SystemConstants.PRODUCT_IMAGE_PATH+uploadedName);
        return retMap;
    }

    /*@RequestMapping("uploadMainImage")
    @ResponseBody
    public Map<String, String> uploadFile(@RequestParam MultipartFile mainImage, HttpServletRequest request) throws IOException {
        FileInfo fileInfo = getUploadedFile(mainImage);
        String uploadedName = FileUtil.getUUIDName(fileInfo.getFileName());
        fileInfo.setFileName(uploadedName);
        productService.uploadFile(fileInfo);
        Map<String, String> retMap = new HashMap<>();
        retMap.put("uploadedName", SystemConstants.PRODUCT_IMAGE_PATH_SOS+uploadedName);
        return retMap;
    }*/

    @RequestMapping("uploadChildImage")
    @ResponseBody
    public Map<String, String> uploadChildImage(@RequestParam MultipartFile childImages, HttpServletRequest request) throws IOException {
        FileInfo fileInfo = getUploadedFile(childImages);
        String rootPath = getRootPath(request);
        String originalFilename = fileInfo.getFileName();
        InputStream inputStream = fileInfo.getIs();
        String folderPath = rootPath+SystemConstants.PRODUCT_IMAGE_PATH;
        String uploadedName = FileUtil.copyFile(inputStream, originalFilename, folderPath);
        Map<String, String> retMap = new HashMap<>();
        retMap.put("uploadedName", SystemConstants.PRODUCT_IMAGE_PATH+uploadedName);
        return retMap;
    }

    /*@RequestMapping("updateProduct")
    public String updateProduct(Product product, @RequestParam MultipartFile[] childImages, @RequestParam MultipartFile productImage, HttpServletRequest request) throws IOException {
        FileInfo fileInfo = getUploadedFile(productImage);
        List<FileInfo> fileList = getChildFileList(childImages);
        String rootPath = getRootPath(request);
        productService.updateProduct(product, fileInfo, fileList, rootPath);
        return "redirect:/product/toProductList.jhtml";
    }*/

    @RequestMapping("updateProduct")
    public String updateProduct(Product product, HttpServletRequest request){
        String rootPath = getRootPath(request);
        productService.updateProduct(product, rootPath);
        return "redirect:/product/toProductList.jhtml";
    }

    /*@RequestMapping("add")
    public String add(Product product, @RequestParam MultipartFile productImage, @RequestParam MultipartFile[] childImages, HttpServletRequest request) throws IOException {
        String rootPath = getRootPath(request);
        FileInfo uploadedFile = getUploadedFile(productImage);
        List<FileInfo> childFileList = getChildFileList(childImages);
        //增加产品信息
        productService.add(product, uploadedFile, rootPath, childFileList);
        return "redirect:/product/toProductList.jhtml";
    }*/
    @RequestMapping("add")
    @ResponseBody
    public ServerResponse add(Product product){
        productService.addProduct(product);
        return ServerResponse.success();
    }

    @RequestMapping("exportProductExcel")
    public void exportProductExcel(Product product, HttpServletResponse response) {
        //获取数据
        List<Product> productList = productService.exportProductExcel(product);
        //生成excel表格
        XSSFWorkbook workbook = buildWorkBook(productList);
        //导出Excel表格
        FileUtil.excelDownload(workbook, response);
    }

    private XSSFWorkbook buildWorkBook(List<Product> productList) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建sheet页
        XSSFSheet sheet = workbook.createSheet("产品表");
        buildSheet(productList, workbook, sheet);
        return workbook;
    }

    private void buildSheet(List<Product> productList, XSSFWorkbook workbook, XSSFSheet sheet) {
        //创建标题
        buildTitle(workbook, sheet);
        //创建标题行
        buildHeader(sheet, workbook);
        //给行内单元格赋值
        buildBody(productList, sheet, workbook);
    }

    private void buildTitle(XSSFWorkbook workbook, XSSFSheet sheet) {
        XSSFRow titleRow = sheet.createRow(6);
        XSSFCell titleRowCell = titleRow.createCell(7);
        titleRowCell.setCellValue("产品列表");
        buildTitleStyle(workbook, sheet, titleRowCell);

    }

    private void buildTitleStyle(XSSFWorkbook workbook, XSSFSheet sheet, XSSFCell titleRowCell) {
        //合并单元格
        CellRangeAddress rangeAddress = new CellRangeAddress(6, 8, 7, 12);
        sheet.addMergedRegion(rangeAddress);
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        //背景颜色
        cellStyle.setFillForegroundColor(HSSFColor.PINK.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //水平居中
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        //垂直居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //字体大小
        XSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.RED.index);
        font.setFontHeight(26);
        font.setBold(true);
        titleRowCell.setCellStyle(cellStyle);
        cellStyle.setFont(font);
    }

    private void buildBody(List<Product> productList, XSSFSheet sheet, XSSFWorkbook workbook) {
        int startRow = 10;
        int startCol = 7;

        XSSFCellStyle cellDate = workbook.createCellStyle();
        cellDate.setFillForegroundColor(HSSFColor.RED.index);
        cellDate.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellDate.setDataFormat(workbook.createDataFormat().getFormat("yyyy-MM-dd hh:mm:ss"));

        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.RED.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        XSSFCellStyle cellCommon = workbook.createCellStyle();
        cellCommon.setDataFormat(workbook.createDataFormat().getFormat("yyyy-MM-dd hh:mm:ss"));

        for (int i = 0; i < productList.size(); i++) {
            XSSFRow contentRow = sheet.createRow(i + startRow);
            XSSFCell idCell = contentRow.createCell(startCol);
            idCell.setCellValue(productList.get(i).getId());
            XSSFCell productNameCell = contentRow.createCell(startCol + 1);
            productNameCell.setCellValue(productList.get(i).getProductName());
            XSSFCell priceCell = contentRow.createCell(startCol + 2);
            priceCell.setCellValue(productList.get(i).getProductPrice());
            XSSFCell updateTimeCell = contentRow.createCell(startCol + 3);
            updateTimeCell.setCellValue(productList.get(i).getUpdateTime());
            XSSFCell entryTimeCell = contentRow.createCell(startCol + 4);
            entryTimeCell.setCellValue(productList.get(i).getEntryTime());
            XSSFCell brandCell = contentRow.createCell(startCol + 5);
            brandCell.setCellValue(productList.get(i).getBrand().getBrandName());

            if (productList.get(i).getProductPrice() < 100) {
                idCell.setCellStyle(cellStyle);
                productNameCell.setCellStyle(cellStyle);
                priceCell.setCellStyle(cellStyle);
                updateTimeCell.setCellStyle(cellDate);
                entryTimeCell.setCellStyle(cellDate);
                brandCell.setCellStyle(cellStyle);
            } else {
                updateTimeCell.setCellStyle(cellCommon);
                entryTimeCell.setCellStyle(cellCommon);
            }

        }
    }

    private void buildHeader(XSSFSheet sheet, XSSFWorkbook workbook) {
        String[] headerArr = {"编号", "产品名称", "产品价格", "修改时间", "录入时间", "品牌名称"};
        int startRow = 9;
        int startCol = 7;
        XSSFCellStyle cellStyle = buildHeaderStyle(workbook);
        XSSFRow row = sheet.createRow(startRow);
        for (int i = 0; i < headerArr.length; i++) {
            String s = headerArr[i];
            XSSFCell headerCell = row.createCell(i + startCol);
            headerCell.setCellValue(s);
            headerCell.setCellStyle(cellStyle);
        }
    }

    private XSSFCellStyle buildHeaderStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        cellStyle.setFont(font);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return cellStyle;
    }

    @RequestMapping("exportExcelByType")
    public void exportExcelByType(Product productInfo, HttpServletResponse response) {
        //获取sheet名称信息
        List<Brand> brandList = brandService.queryBrandList();
        List<Product> productList = null;
        //创建workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        Integer brandId = productInfo.getBrand().getId();
        for (Brand brand : brandList) {
            if (brandId == -1) {
                productInfo.getBrand().setId(brand.getId());
                productList = productService.exportProductExcel(productInfo);
            } else {
                if (brandId == brand.getId()) {
                    productList = productService.exportProductExcel(productInfo);
                } else {
                    productList = new ArrayList<>();
                }
            }
            XSSFSheet sheet = workbook.createSheet(brand.getBrandName() + "【" + productList.size() + "】");
            buildSheet(productList, workbook, sheet);
        }
        //导出excel
        FileUtil.excelDownload(workbook, response);
    }

    @RequestMapping("toProductList")
    public String toPageList() {
        return "product/productList";
    }

    @RequestMapping("searchData")
    @ResponseBody
    public ServerResponse searchData(Product product, HttpServletRequest request) {
        //获取dataTable传递的相关信息
        Map<String, String[]> parameterMap = request.getParameterMap();
        //查询数据
        DataTableResult dataTableResult = productService.getProductDateTable(product, parameterMap);
        return ServerResponse.success(dataTableResult);
    }

    /**
     * <pre>deleteBatch(批量删除)
     * 创建人：zsk
     * 联系方式：Zhangsk_t@163.com
     * 创建时间：2018年12月25日 下午7:33:20
     * 修改人：zsk
     * 修改时间：2018年12月25日 下午7:33:20
     * 修改备注：
     * @param ids
     * @return</pre>
     */
    @RequestMapping("deleteBatch")
    @LogContent("批量删除了多条数据")
    public @ResponseBody
    ServerResponse deleteBatch(String ids, HttpServletRequest request) {
        /*查找子图片 并删除*/
        List<Image> imageList = imageService.findBatchProductImage(ids);
        for (Image image : imageList) {
            String realPath = request.getServletContext().getRealPath(image.getImagePath());
            FileUtil.deleteFile(realPath);
        }
        /*删除数据库中的数据*/
        imageService.delImageBatch(ids);
        /*查找主图片并删除*/
        List<Product> pathList = productService.findBatchProductImage(ids);
        for (Product product : pathList) {
            String realPath = request.getServletContext().getRealPath(product.getProductImagePath());
            File file = new File(realPath);
            if (file.exists()) {
                file.delete();
            }
        }
        productService.deleteBatch(ids);
        return ServerResponse.success();

    }

    /**
     * <pre>deleteProduct(单个删除)
     * 创建人：zsk
     * 联系方式：Zhangsk_t@163.com
     * 创建时间：2018年12月25日 下午6:51:08
     * 修改人：zsk
     * 修改时间：2018年12月25日 下午6:51:08
     * 修改备注：
     * @param
     * @return</pre>
     */
    @RequestMapping("deleteProduct")
    @ResponseBody
    public ServerResponse deleteProduct(Product product, HttpServletRequest request) {
        Product productInfo = productService.toUpdateById(product.getId());
        String productImagePath = productInfo.getProductImagePath();
        if (StringUtils.isNotEmpty(productImagePath)) {
            String realPath = request.getServletContext().getRealPath(productImagePath);
            File folder = new File(realPath);
            if (folder.exists()) {
                folder.delete();
            }
        }
        productService.deleteProduct(product.getId());
        List<Image> imageList = imageService.findProductImageList(product.getId());
        for (Image image : imageList) {
            String realPath = request.getServletContext().getRealPath(image.getImagePath());
            File file = new File(realPath);
            if (file.exists()) {
                file.delete();
            }
        }
        imageService.delImgByProductId(product.getId());
        return ServerResponse.success();
    }

    /**
     * <pre>toAdd(跳转到增加页面)
     * 创建人：zsk
     * 创建时间：2018年12月23日 下午7:11:58
     * 修改人：zsk
     * 修改时间：2018年12月23日 下午7:11:58
     * 修改备注：
     * @return</pre>
     */
    @RequestMapping("toAdd")
    public String toAdd() {
        return "/product/add";
    }



    /**
     * <pre>queryProductInfo(查询 无条件无分页)
     * 创建人：zsk
     * 联系方式：Zhangsk_t@163.com
     * 创建时间：2018年12月25日 下午7:33:43
     * 修改人：zsk
     * 修改时间：2018年12月25日 下午7:33:43
     * 修改备注：
     * @param mod
     * @return</pre>
     */
    @RequestMapping("queryProductInfo")
    public String queryProductInfo(ModelMap mod) {
        try {
            List<Product> productList = productService.queryProductInfo();
            mod.put("productList", productList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/product/productList";
    }

    /**
     * <pre>toUpdateById(回显)
     * 创建人：zsk
     * 联系方式：Zhangsk_t@163.com
     * 创建时间：2018年12月25日 下午7:34:05
     * 修改人：zsk
     * 修改时间：2018年12月25日 下午7:34:05
     * 修改备注：
     * @param id
     * @return</pre>
     */
    @RequestMapping(value = "toUpdateById/{id}")
    public ModelAndView toUpdateById(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("/product/update");
        Product product = productService.toUpdateById(id);
        List<Image> productImageList = imageService.findProductImageList(id);
        mav.addObject("product", product);
        mav.addObject("imageList", productImageList);
        return mav;
    }




}
