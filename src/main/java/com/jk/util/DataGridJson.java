package com.jk.util;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * <pre>项目名称：ssh-zjui    
 * 类名称：DataGridJson    
 * 类描述：     easyUI的dataGrid返回结果对象
 * 创建人：  朱冀京
 * 创建时间：2017年3月17日 下午2:48:53    
 * 修改人： 朱冀京
 * 修改时间：2017年3月17日 下午2:48:53    
 * 修改备注：       
 * @version </pre>
 */
public class DataGridJson implements Serializable {

	private static final long serialVersionUID = -9067720532898696161L;
	
	private Integer total;
	
	private List rows;
	
	private List footer;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public List getFooter() {
		return footer;
	}

	public void setFooter(List footer) {
		this.footer = footer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
	

}
