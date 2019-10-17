package com.mip.software.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 文件读写工具
 * 
 * @author yeqing
 */
public class FileUtils {
	private static final Logger log = Logger.getLogger(FileUtils.class);

	/**
	 * 创建目录
	 * 如果路径中的父目录不存在，将会依次创建
	 * @param path
	 * @param overWrite
	 * @return
	 */
	public static File mkDirs(String path){
		return create(path,true,false);
	}
	
	/**
	 * 创建文件
	 * @param path
	 * @param overWrite
	 * @return
	 */
	public static File touch(String path,boolean overWrite){
		return create(path,false,overWrite);
	}
	
	/**
	 * 删除指定的文件 
	 * @param strFileName 指定绝对路径的文件名
	 * @return
	 */
	public static boolean delete(String strFileName) {
		File fileDelete = new File(strFileName);
		if (!fileDelete.exists() || !fileDelete.isFile()) {
			return false;
		}
		return fileDelete.delete();
	}
	
	/**
	 * 通用的创建方法
	 * @param path
	 * @param isDir
	 * @param overWrite
	 * @return
	 */
	public static File create(String path,boolean isDir,boolean overWrite){
		File f=new File(path);
		try{
			if(!f.exists()){
				log.debug("文件或目录不存在，开始创建");
				if(isDir){
					f.mkdirs();
				}else{
					f.createNewFile();
				}
			}else{
				if(f.isDirectory()){
					log.debug("目录已存在");
					return f;
				}else if(overWrite){
					if(f.canWrite()){
						log.debug("覆盖文件");
						f.delete();
						f.createNewFile();
					}else{
						log.error("文件无法写入");
					}
				}else{
					log.debug("文件已存在，未覆盖");
				}
			}
		}catch(Exception e){
			log.error("创建文件或目录失败"+e.getMessage());
		}
		return f;
	}
	
	/**
	 * <pre>
	 * 打开一个文件
	 * </pre>
	 */
	public static File open(String path) throws Exception {
		File file = new File(path);
		if (file.exists() && file.isFile()&&file.canRead()) {
			return file;
		} else {
			log.error("无法打开文件：" + path+"，文件不存在或者不可读。");
			return null;
		}
	}
	
	/**
	 * 加载Properties文件
	 * @param path
	 * @return
	 */
	public static Properties loadProperty(String path){
		Properties p = new Properties();
		try{
			File file = FileUtils.open(path);
			FileInputStream fin = new FileInputStream(file);
			p.load(fin);
		}catch(Exception e){
			log.error("加载Properties文件失败"+e.getMessage());
		}
		return p;
	}

	/**
	 * <pre>
	 * 在文件最后添加一行
	 * </pre>
	 */
	public static File append(String path, String content) throws Exception {
		File file = open(path);
		if (file.canWrite()) {
			FileWriter fr = new FileWriter(file, true);
			PrintWriter pr = new PrintWriter(fr);
			pr.println(content);
			pr.close();
		} else {
			log.error("文件" + path + "无法写入。");
		}
		return file;
	}

	/**
	 * <pre>
	 * 以文本方式读出所有内容
	 * </pre>
	 */
	public static String readAll(String path) throws Exception {
		File file = open(path);
		StringBuilder builder = new StringBuilder();
		if (file.canRead()) {
			FileInputStream fs=new FileInputStream(path);
			InputStreamReader ir=new InputStreamReader(fs,"utf-8");
			BufferedReader br = new BufferedReader(ir);
			String line = "";
			while ((line = br.readLine()) != null) {
				builder.append(line);
			}
			br.close();
		} else {
			log.error("无法读取文件" + path + "的内容。");
		}
		return builder.toString();
	}

	/**
	 * <pre>
	 * 以content的内容覆盖当前文件
	 * </pre>
	 */
	public static File overWrite(String path, String content) throws Exception {
		File file = open(path);
		if (file.exists()&&!file.isDirectory()&&file.canWrite()) {
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			output.write(content);
			output.close();
		} else {
			log.error("文件" + path + "不存在或者无法写入。");
		}
		return file;
	}

	/**
	 * 上传文件
	 * 
	 * @param uploadFileName
	 *          被上传的文件名称
	 * @param savePath
	 *          文件的保存路径
	 * @param uploadFile
	 *          被上传的文件
	 * @return newFileName
	 */
	public static String upload(String uploadFileName, String savePath, File uploadFile) {
		String newFileName = getRandomName(uploadFileName, savePath);
		try {
			@SuppressWarnings("resource")
			FileOutputStream fos = new FileOutputStream(savePath + newFileName);
			@SuppressWarnings("resource")
			FileInputStream fis = new FileInputStream(uploadFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newFileName;
	}
	/**
	 * 获得随机文件名,保证在同一个文件夹下不同名
	 * 
	 * @param fileName
	 * @param dir
	 * @return
	 */
	public static String getRandomName(String fileName, String dir) {
		String[] split = fileName.split("\\.");// 将文件名已.的形式拆分
		String extendFile = "." + split[split.length - 1].toLowerCase(); // 获文件的有效后缀

		Random random = new Random();
		int add = random.nextInt(1000000); // 产生随机数10000以内
		String ret = add + extendFile;
		while (isFileExist(ret, dir)) {
			add = random.nextInt(1000000);
			ret = fileName + add + extendFile;
		}
		return ret;
	}
	/**
	 * 判断文件是否存在
	 * 
	 * @param fileName
	 * @param dir
	 * @return
	 */
	public static boolean isFileExist(String fileName, String dir) {
		File files = new File(dir + fileName);
		return (files.exists()) ? true : false;
	}
	
	/**
	 * 文件上传
	 * @param inputStream
	 * @param path 上传的路径
	 * @param fileName 上传的文件名称
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static boolean uploadFile(InputStream inputStream, String path,String fileName)
			throws FileNotFoundException, IOException {
		final File uploadFile = new File(path,fileName);
		// 假如文件已存在，将旧文件删除
		uploadFile.delete();
		final OutputStream outputStream = new FileOutputStream(uploadFile);
		BufferedInputStream bufferedInputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		try {
			bufferedInputStream = new BufferedInputStream(inputStream);
			bufferedOutputStream = new BufferedOutputStream(outputStream);
			final byte temp[] = new byte[8192];
			int readBytes = 0;
			while ((readBytes = bufferedInputStream.read(temp)) != -1) {
				bufferedOutputStream.write(temp, 0, readBytes);
			}
			bufferedOutputStream.flush();
		} finally {
			if (bufferedOutputStream != null) {
				bufferedOutputStream.close();
			}
			if (bufferedInputStream != null) {
				bufferedInputStream.close();
			}
		}
		return true;
	}
	
	/**
	 * 下载方法
	 * @param path文件路径
	 * @param filerealname 下载后文件名称
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static void downloadFileWj(String filepath,String filerealname,HttpServletRequest request,HttpServletResponse response) throws Exception {
			String dir = request.getSession().getServletContext().getRealPath("");
			//通过文件路径获得File对象
	        File file = new File(dir + filepath);  
		    //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
	        response.setContentType("multipart/form-data");  
	        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)  
	        response.setHeader("Content-Disposition", "attachment;fileName="+new String(file.getName().replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));  
	        ServletOutputStream out;
	        try {  
	            FileInputStream inputStream = new FileInputStream(file);  
	            //3.通过response获取ServletOutputStream对象(out)  
	            out = response.getOutputStream();  
	            int b = 0;  
	            byte[] buffer = new byte[512];  
	            while (b != -1){  
	                b = inputStream.read(buffer);  
	                //4.写到输出流(out)中  
	                out.write(buffer,0,b);  
	            }  
	            inputStream.close();  
	            out.close();  
	            out.flush();  
	        } catch (IOException e) {  
	        	log.error("文件下载出错:"+e.getLocalizedMessage());
	        }  
	}
	
	/**
	 * 文件下载
	 * @param path
	 * @param filename
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static void downloadFile(String path, String fileName,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			response.reset();
			response.setHeader("Content-Type", "application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String((fileName).getBytes("GBK"), "ISO_8859_1"));
			OutputStream toClient = response.getOutputStream();
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			log.error("文件下载失败:" + ex.getMessage());
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out
					.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
			out.println("  <BODY>");
			out.println("<script type='text/javascript'>alert('文件不存在');</script>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
			return;
		}
	}
	
	/**
	 * 文件下载
	 * @param path
	 * @param filename
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static void zipDownloadFile(String path, String fileName,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			InputStream fis = new BufferedInputStream(new FileInputStream(path
					+ "/" + fileName.trim()));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			response.reset();
			response.setHeader("Content-Type", "application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String((fileName).getBytes("GBK"), "ISO_8859_1"));
			OutputStream toClient = response.getOutputStream();
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			log.error("文件下载失败:" + ex.getMessage());
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out
					.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
			out.println("  <BODY>");
			out.println("<script type='text/javascript'>alert('Data Empty');</script>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
			return;
		}
	}
	
	/**
	 * 设置让浏览器弹出下载对话框的Header.
	 * 根据浏览器的不同设置不同的编码格式  防止中文乱码
	 * @param fileName 下载后的文件名.
	 */
	public static void setFileDownloadHeader(HttpServletRequest request,HttpServletResponse response, String fileName) {
	    try {
	        //中文文件名支持
	        String encodedfileName = null;
	        String agent = request.getHeader("USER-AGENT");
	        if(null != agent && -1 != agent.indexOf("MSIE")){//IE
	            encodedfileName = java.net.URLEncoder.encode(fileName,"UTF-8");
	        }else if(null != agent && -1 != agent.indexOf("Mozilla")){
	            encodedfileName = new String (fileName.getBytes("UTF-8"),"iso-8859-1");
	        }else{
	            encodedfileName = java.net.URLEncoder.encode(fileName,"UTF-8");
	        }
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void main(String[] args) throws Exception {
		// FileUtil.open("c://test.txt");
		// FileUtil.append("c:\\test.txt", "添加一行");
		// FileUtil.overWrite("c:\\test.txt", "开始覆盖啦...");
//		String result = FileUtil.readAll("c:\\test.txt");
//		System.out.println(result);
		//FileUtil.touch("c:\\test.txt", true);
		//FileUtil.overWrite("c:\\test.txt", "这是测试的内容");
		FileUtils.mkDirs("c:\\mydir");
	}
}
