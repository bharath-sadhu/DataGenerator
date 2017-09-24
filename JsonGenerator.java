package com.spark.training.datagenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class JsonGenerator extends DataGenerator {

	@Override
	protected String getAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Data prepareData() throws IOException {
		// TODO Auto-generated method stub
		Data dataOb = new Data();
		FileInputStream input = new FileInputStream(new File(
				"C:/Users/bharath/workspace/datagenerator/src/main/java/com/spark/training/datagenerator/test"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		String currentLine = null;
		List<String> schema = getSchema(reader.readLine());
		int primaryKeyPos = getPrimaryKeyPos(schema, "id");
		Map<String, List<String>> data = new HashMap<String, List<String>>();
		while ((currentLine = reader.readLine()) != null) {
			StringTokenizer tokenizer = new StringTokenizer(currentLine, " ");
			String key = "";
			String value = "";
			List<String> values = new ArrayList<String>();
			for (int i = 0; tokenizer.hasMoreTokens(); i++) {
				if (i == primaryKeyPos) {
					key = key + tokenizer.nextToken();
				} else {
					value = value + " " + tokenizer.nextToken();
				}
			}
			if (data.containsKey(key)) {
				data.get(key).add(value);
			} else {
				values.add(value);
				data.put(key, values);
			}
		}
		reader.close();
		dataOb.data = data;
		dataOb.schema = schema;
		dataOb.primaryKeyPos = primaryKeyPos;
		return dataOb;

	}

	protected Data innerJoin() throws IOException {
		Data data = prepareData();

		return data;
	}

	public int getPrimaryKeyPos(List<String> schema, String primaryCol) {
		int count = 0;
		for (String column : schema) {
			if (column.equalsIgnoreCase(primaryCol)) {
				return count;
			}
			count++;
		}
		return -1;
	}

	public List<String> getSchema(String line) {
		List<String> schema = new ArrayList<String>();

		StringTokenizer tokens = new StringTokenizer(line);
		while (tokens.hasMoreTokens()) {
			schema.add(tokens.nextToken());
		}
		return schema;
	}
}
