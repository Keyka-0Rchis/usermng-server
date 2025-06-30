package works.keyka.usermngserver.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import jakarta.servlet.ServletContext;

import works.keyka.usermngserver.domain.UserData;


public class UserFileManager {
	
	public static void saveMap(TreeMap <Integer, UserData> userMap,ServletContext context) throws IOException{
		File dataDir = new File(context.getRealPath("/WEB-INF/data"));
		
		File file = new File(dataDir,"user.csv");
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
			bw.write("userid,username,email,isExist");
	        bw.newLine();
	        for (Map.Entry<Integer, UserData> entry : userMap.entrySet()) {
	            bw.write(
	            		 entry.getKey() + "," +
	                     entry.getValue().getName() + "," +
	                     entry.getValue().getEmail() + "," +
	                     entry.getValue().isExist()
	                     );
	            bw.newLine();
	        }
		}catch(IOException e) {
			throw new IOException("エラー：ファイルの作成に失敗しました",e);
		}
	}
	
	public static UserMap loadMap(ServletContext context) throws IOException{
		File dataDir = new File(context.getRealPath("/WEB-INF/data"));
		
		if(!dataDir.exists()) {
			dataDir.mkdir();
		}
		
		File file = new File(dataDir,"user.csv");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new IOException("エラー：ファイルの作成に失敗しました",e);
			} 
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
				bw.write("userid,username,email,isExist");
				bw.newLine();
			}catch(IOException e) {
				throw new IOException("エラー：ファイルの作成に失敗しました",e);
			}
		}
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			UserMap userMap = new UserMap();
			String line;	
			br.readLine(); 
			int lineNum = 2;	
			while((line = br.readLine()) != null) {		
				String[] importData = line.split(",");	
				try {
					int loadID = Integer.parseInt(importData[0]);
					boolean existFlag = Boolean.valueOf(importData[3]);
					userMap.addUser(loadID, new UserData(importData[1],importData[2],existFlag));
				}catch(NumberFormatException e){
					throw new NumberFormatException(lineNum + "行目が不正です。" + e);
				}
				lineNum ++ ;
			}
			return userMap;
		}catch(IOException e) {
			throw new IOException("エラー：ファイルの読み込みに失敗しました",e);
		}
	}
}
