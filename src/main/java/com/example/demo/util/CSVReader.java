package com.example.demo.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {

	public Map<List<Integer>, String> testReader() {

		Map<List<Integer>, String> pixelMap = new HashMap<List<Integer>, String>();

		// read 393.csv
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream("C:\\393.csv"), Charset.forName("UTF-8")))) {

			String line;
			int index = 0;

			while ((line = reader.readLine()) != null) {
				if (index > 0) {
					String[] data = line.split(",");

					// convert array elements to variables
					int columnNumber = Integer.parseInt(data[0]);
					int rowNumber = Integer.parseInt(data[1]);
					String colorName = data[2];

					// create a list with key variables
					List<Integer> keysList = new ArrayList<Integer>();
					keysList.add(columnNumber);
					keysList.add(rowNumber);

					// put pixelMap
					pixelMap.put(keysList, colorName);

				}
				index++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return pixelMap;

	}

}
