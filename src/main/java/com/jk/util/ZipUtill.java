package com.jk.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtill {
	
	
	/**
	 * 把文件打成压缩包并保存在本地硬盘
	 * 
	 * @param srcfiles
	 * @param zipPath
	 */
	public static void ZipFiles(HttpServletRequest request , List<String> srcfiles, String zipPath) {
	    byte[] buf = new byte[4096];
	    ZipOutputStream out = null;
		String downPath = request.getServletContext().getRealPath( PropertiesUtil.getProperties("uploadImgPath"));
	    try {
	      // 创建zip输出流
	      out = new ZipOutputStream(new FileOutputStream(zipPath));
	      // 循环将源文件列表添加到zip文件中
	      for (int i = 0; i < srcfiles.size(); i++) {
	    	File file = new File(downPath+"/"+srcfiles.get(i));
	        FileInputStream in = new FileInputStream(file);
	        String fileName = file.getName();
	        // 将文件名作为zip的Entry存入zip文件中
	        out.putNextEntry(new ZipEntry(fileName));
	        int len;
	        while ( (len = in.read(buf)) > 0) {
	          out.write(buf, 0, len);
	        }
	        out.closeEntry();
	        in.close();
	      }
	    }
	    catch (IOException e) {
	      e.printStackTrace();
	    } finally {
	    	if (null != out) {
	    		try {
					out.close();
					out = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	
	    }
	}
	
	
	/**
	 * 把文件打成压缩包并输出到客户端浏览器中
	 * 
	 * @param request
	 * @param response
	 * @param srcFiles
	 * @param downloadZipFileName
	 */
	public static void ZipFiles(HttpServletRequest request, HttpServletResponse response,
								List<String> srcFiles, String downloadZipFileName,String realPath) {
	    byte[] buf = new byte[4096];
	    try {
	      // Create the ZIP file
	     // ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipPath));
	     ZipOutputStream out = new ZipOutputStream(response.getOutputStream());//--设置成这样可以不用保存在本地，再输出， 通过response流输出,直接输出到客户端浏览器中。
	      // Compress the files
	     if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
	    	 downloadZipFileName = new String(downloadZipFileName.getBytes("GB2312"),"ISO-8859-1");
	        } else {
	        	// 对文件名进行编码处理中文问题
	        	downloadZipFileName = java.net.URLEncoder.encode(downloadZipFileName, "UTF-8");// 处理中文文件名的问题
	        	downloadZipFileName = new String(downloadZipFileName.getBytes("UTF-8"), "GBK");// 处理中文文件名的问题
	        }
	     response.reset(); // 重点突出
	        response.setCharacterEncoding("UTF-8"); // 重点突出
	        response.setContentType("application/x-msdownload");// 不同类型的文件对应不同的MIME类型 // 重点突出
	        // inline在浏览器中直接显示，不提示用户下载
	        // attachment弹出对话框，提示用户进行下载保存本地
	        // 默认为inline方式
	        response.setHeader("Content-Disposition", "attachment;filename="+downloadZipFileName);
	      for (int i = 0; i < srcFiles.size(); i++) {
	    	File file = new File(realPath+"/"+srcFiles.get(i));
	        FileInputStream in = new FileInputStream(file);
	        // Add ZIP entry to output stream.
	        String fileName = file.getName();
	        out.putNextEntry(new ZipEntry(fileName));
	        // Transfer bytes from the file to the ZIP file
	        int len;
	        while ( (len = in.read(buf)) > 0) {
	          out.write(buf, 0, len);
	        }
	        // Complete the entry
	        out.closeEntry(); 
	      }
	      // Complete the ZIP file
	      out.close();
	      System.out.println("压缩完成.");
	    }
	    catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	
	
	
	
	/**
	 * 把文件打成压缩包并输出到客户端浏览器中
	 * 
	 * @param request
	 * @param response
	 * @param srcFiles
	 * @param realPath
	 */
	public static void ZipFiles(HttpServletRequest request, HttpServletResponse response, String[] srcFiles,String realPath) {
	    byte[] buf = new byte[4096];
	    try {
	      // Create the ZIP file
	     // ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipPath));
	     ZipOutputStream out = new ZipOutputStream(response.getOutputStream());//--设置成这样可以不用保存在本地，再输出， 通过response流输出,直接输出到客户端浏览器中。
	   
	     response.reset(); // 重点突出
	        response.setCharacterEncoding("UTF-8"); // 重点突出
	        response.setContentType("application/x-msdownload");// 不同类型的文件对应不同的MIME类型 // 重点突出
	        // inline在浏览器中直接显示，不提示用户下载
	        // attachment弹出对话框，提示用户进行下载保存本地
	        // 默认为inline方式
	        Date n = new Date();
	        response.setHeader("Content-Disposition", "attachment;filename="+n.getTime()+".zip");
	      for (int i = 0; i < srcFiles.length; i++) {
	    	File file = new File(realPath+"/"+srcFiles[i]);
	        FileInputStream in = new FileInputStream(file);
	        // Add ZIP entry to output stream.
	        String fileName = file.getName();
	        out.putNextEntry(new ZipEntry(fileName));
	        // Transfer bytes from the file to the ZIP file
	        int len;
	        while ( (len = in.read(buf)) > 0) {
	          out.write(buf, 0, len);
	        }
	        // Complete the entry
	        out.closeEntry(); 
	      }
	      // Complete the ZIP file
	      out.close();
	      System.out.println("压缩完成.");
	    }
	    catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	

	
	
	

	public static void main(String[] args) {
		List<String> fileList = new ArrayList<String>();
		fileList.add("D://testZip//1.txt");
		fileList.add("D://testZip//2.java");
		fileList.add("D://testZip//3.png");
		
		//压缩包输出路径
		String outFoler = "F://";
		
		ZipOutputStream zos = null;
		
		FileInputStream fis = null;
		
		 byte[] b = new byte[4096];
		
		try {
			zos = new ZipOutputStream(new FileOutputStream(outFoler+"/aa.zip"));
			for (int i = 0; i < fileList.size(); i++) {
				//通过 文件名  获取对应的文件
				File f = new File(fileList.get(i));
				//文件输入流
				fis = new FileInputStream(f);
				//获取 文件名称
				String fileName = f.getName();
				//new 实例化 压缩文件
				ZipEntry ze = new ZipEntry(fileName);
				//向压缩流中 存放  zipEntry压缩文件
				zos.putNextEntry(ze);
				
				int s = 0;
				while(-1 != (s = fis.read(b))){
					zos.write(b, 0, s);
				}
				zos.closeEntry();
				fis.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
	    	if (null != zos) {
	    		try {
					zos.close();
					zos = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
	    }
	}
}
