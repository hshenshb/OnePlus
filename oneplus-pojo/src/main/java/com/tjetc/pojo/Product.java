package com.tjetc.pojo;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = -7947817530673585353L;
	private int id;
	private String pname;
	private double price;
	private double preprice;
	private int cid;
	private int scid;
	private String discrip;
	private int leftover;
	private String photopath;
	private String photopath2;
	private String ptime;
	private int ishot;
	private String photopath3;
	private String sname;

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Product(int id, String pname, double price, int cid, int scid, String sname, int ishot, String photopath,
			String photopath2, String photopath3, String discrip, int leftover, double preprice) {
		super();
		this.id = id;
		this.pname = pname;
		this.price = price;
		this.cid = cid;
		this.scid = scid;
		this.sname = sname;
		this.ishot = ishot;
		this.photopath = photopath;
		this.photopath2 = photopath2;
		this.photopath3 = photopath3;
		this.discrip = discrip;
		this.leftover = leftover;
		this.preprice = preprice;
	}

	public Product(int id) {
		this.id = id;
	}

	public Product(int id, double price) {
		this.id = id;
		this.price = price;
	}

	public Product(String pname, double price, int cid, int scid, String sname, int ishot, String photopath,
				   String photopath2, String photopath3, String discrip, int leftover, double preprice) {
		super();
		this.pname = pname;
		this.price = price;
		this.cid = cid;
		this.scid = scid;
		this.sname = sname;
		this.ishot = ishot;
		this.photopath = photopath;
		this.photopath2 = photopath2;
		this.photopath3 = photopath3;
		this.discrip = discrip;
		this.leftover = leftover;
		this.preprice = preprice;
	}

	public Product(String pname, double price, int cid, int scid, int ishot, String photopath, String photopath2,
			String photopath3, String discrip, int leftover, double preprice,String ptime) {
		super();
		this.pname = pname;
		this.price = price;
		this.cid = cid;
		this.scid = scid;
		this.ishot = ishot;
		this.photopath = photopath;
		this.photopath2 = photopath2;
		this.photopath3 = photopath3;
		this.discrip = discrip;
		this.leftover = leftover;
		this.preprice = preprice;
		this.ptime=ptime;
	}

	public Product(int id, String pname, double price, int cid, int scid, int ishot, String photopath, String photopath2,
			String photopath3, String discrip, int leftover, double preprice) {
		super();
		this.id = id;
		this.pname = pname;
		this.price = price;
		this.cid = cid;
		this.scid = scid;
		this.ishot = ishot;
		this.photopath = photopath;
		this.photopath2 = photopath2;
		this.photopath3 = photopath3;
		this.discrip = discrip;
		this.leftover = leftover;
		this.preprice = preprice;
	}

	public Product(int id, String pname, double price, double preprice, int cid, int scid, String discrip, int leftover,
			String photopath, String photopath2, String ptime, int ishot, String photopath3) {
		super();
		this.id = id;
		this.pname = pname;
		this.price = price;
		this.preprice = preprice;
		this.cid = cid;
		this.scid = scid;
		this.discrip = discrip;
		this.leftover = leftover;
		this.photopath = photopath;
		this.photopath2 = photopath2;
		this.ptime = ptime;
		this.ishot = ishot;
		this.photopath3 = photopath3;
	}

	public Product() {
		super();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getPname() {
		return pname;
	}

	/**
	 * @param pname
	 *            the name to set
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the preprice
	 */
	public double getPreprice() {
		return preprice;
	}

	/**
	 * @param preprice
	 *            the preprice to set
	 */
	public void setPreprice(double preprice) {
		this.preprice = preprice;
	}

	/**
	 * @return the cid
	 */
	public int getCid() {
		return cid;
	}

	/**
	 * @param cid
	 *            the cid to set
	 */
	public void setCid(int cid) {
		this.cid = cid;
	}

	/**
	 * @return the scid
	 */
	public int getScid() {
		return scid;
	}

	/**
	 * @param scid
	 *            the scid to set
	 */
	public void setScid(int scid) {
		this.scid = scid;
	}

	/**
	 * @return the discrip
	 */
	public String getDiscrip() {
		return discrip;
	}

	/**
	 * @param discrip
	 *            the discrip to set
	 */
	public void setDiscrip(String discrip) {
		this.discrip = discrip;
	}

	/**
	 * @return the leftover
	 */
	public int getLeftover() {
		return leftover;
	}

	/**
	 * @param leftover
	 *            the leftover to set
	 */
	public void setLeftover(int leftover) {
		this.leftover = leftover;
	}

	/**
	 * @return the photopath
	 */
	public String getPhotopath() {
		return photopath;
	}

	/**
	 * @param photopath
	 *            the photopath to set
	 */
	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}

	/**
	 * @return the photopath2
	 */
	public String getPhotopath2() {
		return photopath2;
	}

	/**
	 * @param photopath2
	 *            the photopath2 to set
	 */
	public void setPhotopath2(String photopath2) {
		this.photopath2 = photopath2;
	}

	/**
	 * @return the time
	 */
	public String getPtime() {
		return ptime;
	}

	/**
	 * @param ptime
	 *            the time to set
	 */
	public void setPtime(String ptime) {
		ptime = ptime;
	}

	/**
	 * @return the isHot
	 */
	public int getIshot() {
		return ishot;
	}

	/**
	 * @param ishot
	 *            the isHot to set
	 */
	public void setIshot(int ishot) {
		this.ishot = ishot;
	}

	/**
	 * @return the photopath3
	 */
	public String getPhotopath3() {
		return photopath3;
	}

	/**
	 * @param photopath3
	 *            the photopath3 to set
	 */
	public void setPhotopath3(String photopath3) {
		this.photopath3 = photopath3;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "product [id=" + id + ", pname=" + pname + ", price=" + price + ", preprice=" + preprice + ", cid=" + cid
				+ ", scid=" + scid + ", discrip=" + discrip + ", leftover=" + leftover + ", photopath=" + photopath
				+ ", photopath2=" + photopath2 + ", ptime=" + ptime + ", ishot=" + ishot + ", photopath3=" + photopath3
				+ "]";
	}

}
