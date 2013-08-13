package com.example.testa;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import service.DownloadService;
import utils.FileProcessor;

import dalvik.system.DexClassLoader;
import android.R.id;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.util.Printer;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	private Button startButton = null;
	private Button loadButton = null;

	private void loadApk() {
		DexClassLoader classLoader = null;

		try {
			// String path = Environment.getExternalStorageDirectory() + "/"
			// + "apk" + File.separator;
			String path = Environment.getExternalStorageDirectory() + "/";
			String filename = "TestB.apk";
			System.out.println(path + filename);
			classLoader = new DexClassLoader(path + filename, path, null,
					getClassLoader());
		} catch (Exception e) {
			// TODO: handle exception
			// System.out.println("some thing is bad happed here!!!");
			e.printStackTrace();
		}

		try {
			Class mLoadClass = classLoader
					.loadClass("com.example.testb.TestBActivity");
			Constructor constructor = mLoadClass.getConstructor(new Class[] {});
			Object TestBActivity = constructor.newInstance(new Object[] {});

			Method getMoney = mLoadClass.getMethod("getMoney", null);
			getMoney.setAccessible(true);
			Object money = getMoney.invoke(TestBActivity, null);
			Toast.makeText(this, money.toString(), Toast.LENGTH_LONG).show();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// e.printStackTrace();
			System.out.println("illegalargumentexception!!!");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.startButton = (Button) this.findViewById(R.id.startbutton);
		this.loadButton = (Button) this.findViewById(R.id.loadbutton);

		this.startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println("startbutton clicked");
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, DownloadService.class);
				MainActivity.this.startService(intent);
			}
		});

		this.loadButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// MainActivity.this.loadApk();
				new FileProcessor().getApkFiles();
				MainActivity.this.loadApk();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
