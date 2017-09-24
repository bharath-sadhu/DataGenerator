package com.spark.training.datagenerator;

public abstract class DataGenerator {
	protected abstract String getAddress();

	protected abstract String getName();

	protected abstract Data prepareData() throws Exception;
}
