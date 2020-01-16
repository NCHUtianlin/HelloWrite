package com.tianlin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

/***
 * 内容
 * @entity
 * @author hetianlin
 * @date 2020.01.14
 * **/
public class Content implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Integer userid;
    @JsonFormat(pattern="yy-MM-dd Hh:mm:ss")
    private Date wdate;
    @JsonFormat(pattern="yy-MM-dd Hh:mm:ss")
    private Date wdateOlder;

	private String detail;

    private String title;

    private Integer state;
    
    private Integer pageSize;
    
    private Integer pageNow;

    public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNow() {
		return pageNow;
	}

	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}

	public Date getWdateOlder() {
		return wdateOlder;
	}

	public void setWdateOlder(Date wdateOlder) {
		this.wdateOlder = wdateOlder;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getWdate() {
        return wdate;
    }

    public void setWdate(Date wdate){
    	this.wdate = wdate;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}