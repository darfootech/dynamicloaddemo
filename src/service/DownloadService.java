package service;

import utils.HttpDownloader;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DownloadService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		System.out.println("service is on!!!");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		System.out.println("is in service now!!!");

		new Thread(new DownLoadApkThread()).start();

		return super.onStartCommand(intent, flags, startId);

	}

	class DownLoadApkThread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			String downloadUrl = "http://192.168.65.210:8000/apk/TestB.apk";
			HttpDownloader httpDownloader = new HttpDownloader();
			int downloadResult = httpDownloader.downloadFileToSDcard(
					downloadUrl, "", "TestB.apk");
			System.out.println("download apk result is : " + downloadResult);
		}

	}

}
