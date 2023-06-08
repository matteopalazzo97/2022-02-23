package it.polito.tdp.yelp.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Edge {
	
	private Review r1;
	private Review r2;
	private LocalDate d1;
	private LocalDate d2;
	private long peso;
	
	public Edge(Review r1, Review r2, LocalDate d1, LocalDate d2) {
		super();
		this.r1 = r1;
		this.r2 = r2;
		this.d1 = d1;
		this.d2 = d2;
		this.peso = ChronoUnit.DAYS.between(d1, d2);
	}

	@Override
	public String toString() {
		return "Edge [r1=" + r1 + ", r2=" + r2 + ", d1=" + d1 + ", d2=" + d2 + ", peso=" + peso + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((d1 == null) ? 0 : d1.hashCode());
		result = prime * result + ((d2 == null) ? 0 : d2.hashCode());
		
		result = prime * result + ((r1 == null) ? 0 : r1.hashCode());
		result = prime * result + ((r2 == null) ? 0 : r2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (d1 == null) {
			if (other.d1 != null)
				return false;
		} else if (!d1.equals(other.d1))
			return false;
		if (d2 == null) {
			if (other.d2 != null)
				return false;
		} else if (!d2.equals(other.d2))
			return false;
		if (peso != other.peso)
			return false;
		if (r1 == null) {
			if (other.r1 != null)
				return false;
		} else if (!r1.equals(other.r1))
			return false;
		if (r2 == null) {
			if (other.r2 != null)
				return false;
		} else if (!r2.equals(other.r2))
			return false;
		return true;
	}

	public Review getR1() {
		return r1;
	}

	public void setR1(Review r1) {
		this.r1 = r1;
	}

	public Review getR2() {
		return r2;
	}

	public void setR2(Review r2) {
		this.r2 = r2;
	}

	public LocalDate getD1() {
		return d1;
	}

	public void setD1(LocalDate d1) {
		this.d1 = d1;
	}

	public LocalDate getD2() {
		return d2;
	}

	public void setD2(LocalDate d2) {
		this.d2 = d2;
	}

	public long getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	
	
	

}
