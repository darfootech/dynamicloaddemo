package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpDownloader {

	private URL url;
	
	public InputStream getInputStreamFromURL(String urlStr){
		try {
			this.url = new URL(urlStr);
			HttpURLConnection httpURLConnection = (HttpURLConnection)this.url.openConnection();
			return httpURLConnection.getInputStream();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public int downloadFileToSDcard(String urlStr, String pathStr, String fileNameStr){
		InputStream inputStream = null;
		FileProcessor fileProcessor = new FileProcessor();
		if(fileProcessor.isFileExist(pathStr + fileNameStr)){
			return 1;
		}else{
			inputStream = getInputStreamFromURL(urlStr);
			if(inputStream == null){
				return -1;
			}
			File resultFile = fileProcessor.writeFile2SDcardFromInputStream(pathStr, fileNameStr, inputStream);
			if(resultFile == null){
				return -1;
			}
		}
		if(inputStream != null){
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return 0;
		
	}
	
}
