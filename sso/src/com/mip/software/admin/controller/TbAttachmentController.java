package com.mip.software.admin.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mip.software.common.util.JsonHelper;
import com.mip.software.common.util.ProjectUtils;
import com.mip.software.common.util.PropertiesUtils;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 附件上传
 */
@Controller
@RequestMapping("/admin/attach")
public class TbAttachmentController extends BaseController {
	
	public static JsonHelper util = new JsonHelper();
	private int width;
	private int height;
	private Image img;
	/**
	 * 上传处理方法
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/uploadfile")
	public void upload(@RequestParam(value = "Filedata") MultipartFile file,
				HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			String mkdirName = PropertiesUtils.readValue("imgPath"); 
			String accessUrl = PropertiesUtils.readValue("accessUrl"); 
			String filename=file.getOriginalFilename();
			String prefix=filename.substring(filename.lastIndexOf("."));
			String fileNmae=ProjectUtils.getNumCode()+prefix;
			String path = mkdirName+"/"+fileNmae; 
			File imagesFile=new File(path);
			FileUtils.copyInputStreamToFile(file.getInputStream(),imagesFile);
				
				response.setContentType("application/json;charset=UTF-8");
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				response.getWriter().write(accessUrl+fileNmae);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 按照宽度还是高度进行压缩
	 * @param w int 最大宽度
	 * @param h int 最大高度
	 */
	public void resizeFix(String oldFileName,int w, int h,String newFileName) throws IOException {
		File file = new File(oldFileName);// 读入文件
		img = ImageIO.read(file);      // 构造Image对象
		width = img.getWidth(null);    // 得到源图宽
		height = img.getHeight(null);  // 得到源图长
		
		if (width / height > w / h) {
			resizeByWidth(w,newFileName);
		} else {
			resizeByHeight(h,newFileName);
		}
	}
	/**
	 * 以宽度为基准，等比例放缩图片
	 * @param w int 新宽度
	 */
	public void resizeByWidth(int w,String newFileName) throws IOException {
		int h = (int) (height * w / width);
		resize(w, h,newFileName);
	}
	/**
	 * 以高度为基准，等比例缩放图片
	 * @param h int 新高度
	 */
	public void resizeByHeight(int h,String newFileName) throws IOException {
		int w = (int) (width * h / height);
		resize(w, h,newFileName);
	}
	/**
	 * 强制压缩/放大图片到固定的大小
	 * @param w int 新宽度
	 * @param h int 新高度
	 */
	public void resize(int w, int h,String newFileName) throws IOException {
		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB ); 
		image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
		File destFile = new File(newFileName);
		FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
		// 可以正常实现bmp、png、gif转jpg
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image); // JPEG编码
		out.close();
	}
	
	
}
