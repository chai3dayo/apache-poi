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

	public Map<List<Integer>, List<Integer>> testReader() {

		Map<List<Integer>, List<Integer>> pixelMap = new HashMap<List<Integer>, List<Integer>>();

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
					int redNumber = Integer.parseInt(data[2]);
					int greenNumber = Integer.parseInt(data[3]);
					int blueNumber = Integer.parseInt(data[4]);

					// create a list with key variables
					List<Integer> keysList = new ArrayList<Integer>();
					keysList.add(columnNumber);
					keysList.add(rowNumber);
					
					// create a list with value variables
					List<Integer> valuesList = new ArrayList<Integer>();
					valuesList.add(redNumber);
					valuesList.add(greenNumber);
					valuesList.add(blueNumber);

					// put pixelMap
					pixelMap.put(keysList, valuesList);

				}
				index++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return pixelMap;

	}

}
