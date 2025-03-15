package com.tc.labt.sgabs.cokpit.datatable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataTableModel implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int draw;
	public int start;
	public int length;

	public List<Column> columns;
	public Search search;
	public List<Order> order;
}
