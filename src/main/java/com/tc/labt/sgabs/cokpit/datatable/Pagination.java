package com.tc.labt.sgabs.cokpit.datatable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Pagination <T> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static int DEFAULT_SIZE = 500;
    public static int DEFAULT_INDEX = 0;
    
    @Builder.Default
    public int recordsTotal = 0;
    @Builder.Default
    public int recordsFiltered = -1;
    public int draw;

    @XmlElement
    public List<T> data;

    public Pagination(int draw, List<T> data) {
        this.recordsTotal = data.size();
        this.draw = draw;
        this.data = data;
    }

    public void setRecordsTotal(int recordsTotal) { this.recordsTotal = recordsTotal; this.setRecordsFiltered(recordsTotal); }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
        if(recordsTotal<1 && recordsFiltered > 0)
            setRecordsTotal(recordsFiltered);
    }


    public static int getValidSize(int size){ return size <= 0 || size > 1000 ? DEFAULT_SIZE : size;  }
    public static int getValidStartIndex(int start){ return start <=0 || start > 1000 ? DEFAULT_INDEX : start;  }
}
