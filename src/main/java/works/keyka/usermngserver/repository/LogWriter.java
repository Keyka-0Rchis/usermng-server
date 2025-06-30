package works.keyka.usermngserver.repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletContext;

import works.keyka.usermngserver.service.ServiceResult;


public class LogWriter {

	public static void logWrite(ServiceResult result,ServletContext context) throws IOException {
		File logDir = new File(context.getRealPath("/WEB-INF/logs"));
		//本来javaはWebContent側の世界は知らない。なので、ServletのcontextクラスでこっちにWebアプリ全般の情報を送っている。
		//logが生まれるのはtomcat側のWEB-INFの中。デプロイ先ってやつ。
		//プログラムはビルドされた後classとしてサーバー側に配置しなおされる。そこで運用されるってこと。
		
		if (!logDir.exists()) {
			logDir.mkdir();
		}
		
		File file = new File(logDir,"log.csv");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new IOException("エラー：ファイルの作成に失敗しました",e);
			} 
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(file,true))){
				bw.write("timestamp(UTC),mode,id,name,success");
			}catch(IOException e) {
				throw new IOException("エラー：ファイルの作成に失敗しました",e);
			}
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(file,true	));
		//時刻取得。これはさすがに丸暗記か？
		Instant now = Instant.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.of("UTC"));
        String formattedTimestamp = formatter.format(now);
        
        bw.newLine();
        bw.write(
        		 formattedTimestamp + "," +
        		 result.getSelectedMode() + "," +
                 result.getTargetedId() + "," +
                 result.getTargetedName() + "," +
                 result.isResultSuccess()
                );
        bw.close();
	}
}
