package com.vku.shop.model;

import java.util.Objects;

public class RevenueDTO {
	  private String month;
	  private double revenue;
	public RevenueDTO(String month, double revenue) {
		this.month = month;
		this.revenue = revenue;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public double getRevenue() {
		return revenue;
	}
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	@Override
	public int hashCode() {
		return Objects.hash(month, revenue);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RevenueDTO other = (RevenueDTO) obj;
		return Objects.equals(month, other.month)
				&& Double.doubleToLongBits(revenue) == Double.doubleToLongBits(other.revenue);
	}
	  
}
