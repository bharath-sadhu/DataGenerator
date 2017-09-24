package com.spark.training.datagenerator;

import java.util.Map.Entry;

public class ProvideData {
	public static void main(String args[]) {
		ProvideData ob = new ProvideData();
		try {
			Data data = ob.getDataGenerator().prepareData();
			for (final Entry<String, String> preparedDataEntry : data.data.entrySet()) {
				System.out.println(preparedDataEntry.getKey() + " " + preparedDataEntry.getValue());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DataGenerator getDataGenerator() {
		return new JsonGenerator();
	}
}
