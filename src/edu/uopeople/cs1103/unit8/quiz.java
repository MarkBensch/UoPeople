package edu.uopeople.cs1103.unit8;

import java.util.ArrayList;
import java.util.List;

public class quiz{
	public static void main(String[] args){
		List<Sample> list = new ArrayList();
		Sample sample1 = new Sample(1);
		Sample sample2 = new Sample(2);
		list.add(sample1);
		Boolean contains;
		contains = list.contains(sample2);
		System.out.println("Contains: " + contains);
	}
	public static class Sample {

		private int number;
		private String color;

		public Sample(int number) {
			this.number = number;
			this.color = "blue";
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			final Sample other = (Sample) obj;
			if ((this.color == null) ? (other.color != null) : !this.color.equals(other.color)) {
				return false;
			}
			return true;
		}
	}
}
