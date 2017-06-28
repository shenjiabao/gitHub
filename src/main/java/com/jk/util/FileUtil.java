package com.jk.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;


/**
 * <pre>项目名称：ssh-oa    
 * 类名称：FileUtil    
 * 类描述：    处理文件上传下载
 * 创建人：杨沛yangpei310@163.com    
 * 创建时间：2016年2月26日 上午10:34:20    
 * 修改人：杨沛yangpei310@163.com     
 * 修改时间：2016年2月26日 上午10:34:20    
 * 修改备注：       
 * @version </pre>
 */
public class FileUtil {

	/**
	 * <pre>downloadFile(下载Excel模板文件)
	 * 创建人：杨沛yangpei310@163.com
	 * 创建时间：2016年2月27日 上午9:42:42
	 * 修改人：杨沛yangpei310@163.com
	 * 修改时间：2016年2月27日 上午9:42:42
	 * 修改备注：
	 * @param response --响应到浏览器
	 *@param workbook -- 下载的工作簿
	 * @param tempPath --Excel模板存放路径
	 * </pre>
	 */
	public static void downloadFile(HttpServletResponse response ,String tempPath, HSSFWorkbook workbook){

		InputStream is = null;
		BufferedInputStream bis = null;

		OutputStream  os = null;
		BufferedOutputStream bos = null;

		try {
			os = response.getOutputStream();//响应到浏览器
			bos = new BufferedOutputStream(os);

			//输出到webapp的Excel模板文件夹
			File f = new File(tempPath+"/"+PropertiesUtil.getProperties("excelTempPath")+"/zongziTemp.xls");
			//创建file文件
			f.createNewFile();
			workbook.write(f);

			//读取Excel模板文件
			is = new FileInputStream(f);
			bis = new BufferedInputStream(is);

			response.reset();
			// 不同类型的文件对应不同的MIME类型
			response.setContentType("application/x-msdownload");
			// inline在浏览器中直接显示，不提示用户下载
			// attachment弹出对话框，提示用户进行下载保存本地
			// 默认为inline方式
			response.setHeader("Content-Disposition", "attachment;filename="+"zongziTemp.xls");

			byte[] b = new byte[1024];
			int s = 0 ;
			while ((s = bis.read(b)) != -1) {
				bos.write(b, 0, s);
				bos.flush();
			}


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			closeIO(is, bis, os, bos);
		}
	}



	/**
	 * <pre>downloadFile(下载Excel模板文件)
	 * 创建人：杨沛yangpei310@163.com
	 * 创建时间：2016年2月27日 上午9:42:42
	 * 修改人：杨沛yangpei310@163.com
	 * 修改时间：2016年2月27日 上午9:42:42
	 * 修改备注：
	 * @param response --响应到浏览器
	 * @param file -- 下载的Excel模板文件
	 * </pre>
	 */
	public static void downloadFile(HttpServletResponse response , File file){
		InputStream is = null;
		BufferedInputStream bis = null;

		OutputStream  os = null;
		BufferedOutputStream bos = null;
		try {
			os = response.getOutputStream();//响应到浏览器
			bos = new BufferedOutputStream(os);
			//下载的文件
			is = new  FileInputStream(file);
			bis = new BufferedInputStream(is);


			response.reset();
			// 不同类型的文件对应不同的MIME类型
			response.setContentType("application/x-msdownload");
			// inline在浏览器中直接显示，不提示用户下载
			// attachment弹出对话框，提示用户进行下载保存本地
			// 默认为inline方式
			response.setHeader("Content-Disposition", "attachment;filename="+"zongziTemp.xls");

			byte[] b = new byte[1024];
			int s = 0 ;
			while ((s = bis.read(b)) != -1) {
				bos.write(b, 0, s);
				bos.flush();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			closeIO(is, bis, os, bos);
		}
	}
	
	
	
	/**
	 * <pre>downloadFile(下载文件)   
	 * 创建人：杨沛yangpei310@163.com    
	 * 创建时间：2016年2月27日 上午9:42:42    
	 * 修改人：杨沛yangpei310@163.com     
	 * 修改时间：2016年2月27日 上午9:42:42    
	 * 修改备注： 
	 * @param response --响应到浏览器
	 * @param request --请求格式化中文
	 * @param downPath --下载的真实路径
	 * @param fileName --原来文件名称
	 * </pre>
	 */
	public static void downloadFile(HttpServletResponse response , HttpServletRequest request , String downPath , String fileName ){
		
		InputStream is = null;
		
		BufferedInputStream bis = null;
		
		OutputStream  os = null;
		
		BufferedOutputStream bos = null;
	
		try {
			//下载的文件
//			File f = new File(downPath);
//			is = new FileInputStream(f);
			String downPathFile = downPath+"/"+fileName;
			is = new  FileInputStream(downPathFile);
			
			bis = new BufferedInputStream(is);
			
			os = response.getOutputStream();//响应到浏览器
			
			bos = new BufferedOutputStream(os);
			
			//解决浏览器兼容问题
	        if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
	        	fileName = new String(fileName.getBytes("GB2312"),"ISO-8859-1");
	        } else {
	        	// 对文件名进行编码处理中文问题
	  	        fileName = java.net.URLEncoder.encode(fileName, "UTF-8");// 处理中文文件名的问题
	  	        fileName = new String(fileName.getBytes("UTF-8"), "GBK");// 处理中文文件名的问题
	        }
	        
	        response.reset();
	        // 不同类型的文件对应不同的MIME类型
	        response.setContentType("application/x-msdownload");
	        // inline在浏览器中直接显示，不提示用户下载
	        // attachment弹出对话框，提示用户进行下载保存本地
	        // 默认为inline方式
	        response.setHeader("Content-Disposition", "attachment;filename="+fileName);
	        
	        byte[] b = new byte[1024];
	        int s = 0 ;
	        while ((s = bis.read(b)) != -1) {
	        	bos.write(b, 0, s);
	        	bos.flush();
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			closeIO(is, bis, os, bos);
		}
	}
	
	
	/**
	 * <pre>downloadFile(下载文件)   
	 * 创建人：杨沛yangpei310@163.com    
	 * 创建时间：2016年2月27日 上午9:42:42    
	 * 修改人：杨沛yangpei310@163.com     
	 * 修改时间：2016年2月27日 上午9:42:42    
	 * 修改备注： 
	 * @param response --响应到浏览器
	 * @paramrequest --请求格式化中文
	 * @param downPath --下载的真实路径
	 * @param fileName --原来文件名称
	 * </pre>
	 */
	public static void downloadFile(HttpServletResponse response , String downPath , String fileName ){
		
		InputStream is = null;
		
		BufferedInputStream bis = null;
		
		OutputStream  os = null;
		
		BufferedOutputStream bos = null;
	
		try {
			//下载的文件
//			File f = new File(downPath);
//			is = new FileInputStream(f);
			String downPathFile = downPath+"/"+fileName;
			is = new  FileInputStream(downPathFile);
			
			bis = new BufferedInputStream(is);
			
			os = response.getOutputStream();//响应到浏览器
			
			bos = new BufferedOutputStream(os);
        	// 对文件名进行编码处理中文问题
  	        fileName = java.net.URLEncoder.encode(fileName, "UTF-8");// 处理中文文件名的问题
  	        fileName = new String(fileName.getBytes("UTF-8"), "GBK");// 处理中文文件名的问题
	        
	        response.reset();
	        // 不同类型的文件对应不同的MIME类型
	        response.setContentType("application/x-msdownload");
	        // inline在浏览器中直接显示，不提示用户下载
	        // attachment弹出对话框，提示用户进行下载保存本地
	        // 默认为inline方式
	        response.setHeader("Content-Disposition", "attachment;filename="+fileName);
	        
	        byte[] b = new byte[1024];
	        int s = 0 ;
	        while ((s = bis.read(b)) != -1) {
	        	bos.write(b, 0, s);
	        	bos.flush();
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			closeIO(is, bis, os, bos);
		}
	}


	/**
	 * <pre>uploadFile(修改文件)
	 * 创建人：马俊majun310@163.com
	 * 创建时间：2016年9月24日 下午4:16:47
	 * 修改人：马俊majun310@163.com
	 * 修改时间：2016年9月24日 下午4:16:47
	 * 修改备注：
	 * @param uploadFile 上传的文件
	 * @param uuidName 之前文件uuid名称
	 * @param uploadPath 上传的文件路径
	 * @return</pre>
	 */
	//上传文件--返回值文件的UUID名称---参数：上传的文件 ，上传的文件原名称，上传的文件路径
	public static  String  updateFile(MultipartFile uploadFile ,String uuidName, String uploadPath ){

		//文件字节输入流
		FileOutputStream fos = null;
		//	缓冲字节输入流
		BufferedOutputStream bos = null;
		//文件字节输出流
		InputStream is = null;
		//缓冲字节输出流
		BufferedInputStream bis = null;

		//上传的文件路径
		File filePath = new File(uploadPath);
		//判断有无上传的文件夹
		if (! filePath.exists()) {
			filePath.mkdirs();//创建上传的文件夹
		}

		//获取一个随机数
		String uuidStr = UUID.randomUUID().toString();


		//上传到的文件--- 路径+UUID文件名
		File f = new File(uploadPath+"/"+uuidName);

		try {
			is = uploadFile.getInputStream();
			bis = new BufferedInputStream(is);
			fos = new FileOutputStream(f);
			bos = new BufferedOutputStream(fos);//缓冲流 提高效率
			byte [] b = new byte[2048];
			int s = 0;
			while (  (s = bis.read(b)) != -1 ) {
				bos.write(b, 0, s);
				bos.flush();//刷新--强制写出缓冲区
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{

			closeIO(is, bis, fos, bos);
		}
		return uuidName;
	}
	
	
	
	
	
	/**
	 * <pre>uploadFile(上传文件)
	 * 创建人：马俊majun310@163.com
	 * 创建时间：2016年9月24日 下午4:16:47
	 * 修改人：马俊majun310@163.com
	 * 修改时间：2016年9月24日 下午4:16:47
	 * 修改备注：
	 * @param uploadFile 上传的文件
	 * @param fileName 上传的文件原名称
	 * @param uploadPath 上传的文件路径
	 * @return</pre>
	 */
	//上传文件--返回值文件的UUID名称---参数：上传的文件 ，上传的文件原名称，上传的文件路径
	public static  String  uploadFile(MultipartFile uploadFile , String fileName , String uploadPath ){
		String uuidName = null;

		//文件字节输入流
		FileOutputStream fos = null;
		//	缓冲字节输入流
		BufferedOutputStream bos = null;
		//文件字节输出流
		InputStream is = null;
		//缓冲字节输出流
		BufferedInputStream bis = null;

		//上传的文件路径
		File filePath = new File(uploadPath);
		//判断有无上传的文件夹
		if (! filePath.exists()) {
			filePath.mkdirs();//创建上传的文件夹
		}

		//获取一个随机数
		String uuidStr = UUID.randomUUID().toString();

		// UUID随机数+"_" +文件后缀名   --->w34q324dssfasdf_.jpg
		uuidName = uuidStr+"_"+getSuffix(fileName);

		//上传到的文件--- 路径+UUID文件名
		File f = new File(uploadPath+"/"+uuidName);

		try {
			is = uploadFile.getInputStream();
			bis = new BufferedInputStream(is);
			fos = new FileOutputStream(f);
			bos = new BufferedOutputStream(fos);//缓冲流 提高效率
			byte [] b = new byte[2048];
			int s = 0;
			while (  (s = bis.read(b)) != -1 ) {
				bos.write(b, 0, s);
				bos.flush();//刷新--强制写出缓冲区
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{

			closeIO(is, bis, fos, bos);
		}
		return uuidName;
	}
	
	
public static void closeIO(InputStream fis , BufferedInputStream bis , OutputStream fos , BufferedOutputStream bos){
		
		try {
			if (bos != null) {
				bos.flush();
				bos.close();
				bos = null;
			}
			if (fos  != null) {
				fos.flush();
				fos.close();
				fos = null;
			}
			if (bis != null) {
				bis.close();
				bis = null;
			}
			if (fis != null) {
				fis.close();
				fis = null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
			// 截取上传文件的名称
				private static String getPrefix(String fileName) {
					int pos = fileName.lastIndexOf(".");
					String prefix = fileName.substring(0, pos);
					return prefix;
				}

				// 截取上传文件的后缀名（扩展名）
				private static String getSuffix(String fileName) {
					int pos = fileName.lastIndexOf(".");
					String suffix = fileName.substring(pos);
					return suffix;
				}
	

}
