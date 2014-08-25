package com.wonderskool.bo.utility.dataimport;

import java.io.RandomAccessFile;

public class HobbiesImportUtility {

	public static void main(String args[]) {
		try {
			RandomAccessFile rafIn = new RandomAccessFile("/home/abhishekmitt/Documents/Wonderskool/hobbies.csv", "r");
			RandomAccessFile rafOut = new RandomAccessFile("/home/abhishekmitt/Documents/Wonderskool/hobbies_out.sql", "rw");
			rafOut.setLength(0);
			String QUERY_CONST_PART = "INSERT INTO `hobby`(`id`, `client_id`, `Hobby`, `type`, `Description`) VALUES (";
			while(rafIn.getFilePointer() < rafIn.length()) {
				String dataLine = rafIn.readLine();
				String[] dataArr = dataLine.split("~");
				String hobby = dataArr[1].substring(0, 1).toUpperCase() + dataArr[1].substring(1);
				rafOut.writeBytes(QUERY_CONST_PART + dataArr[0] + ", " + 0 + ", '" + hobby
						+ "', '" + dataArr[3] +"', '" + dataArr[2] + "' );");
				rafOut.writeBytes("\r\n");
			}
			rafIn.close();
			rafOut.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
