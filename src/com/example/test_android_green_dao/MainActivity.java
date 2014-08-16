package com.example.test_android_green_dao;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.test_android_green_dao.dao.DaoMaster;
import com.example.test_android_green_dao.dao.DaoMaster.DevOpenHelper;
import com.example.test_android_green_dao.dao.DaoSession;
import com.example.test_android_green_dao.dao.Student;
import com.example.test_android_green_dao.dao.StudentDao;
import com.example.test_android_green_dao.dao.Techer;
import com.example.test_android_green_dao.dao.TecherDao;

public class MainActivity extends Activity {
	private SQLiteDatabase db;

	private DaoMaster daoMaster;
	private DaoSession daoSession;

	private TecherDao techerDao;
	private StudentDao studentDao;

	private Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db",
				null);
		db = helper.getWritableDatabase();

		daoMaster = new DaoMaster(db);
		daoSession = daoMaster.newSession();

		this.techerDao = daoSession.getTecherDao();
		this.studentDao = daoSession.getStudentDao();

		//插入Teacher
		Techer techer = new Techer(null, "老师2");
		techerDao.insert(techer);
		String[] allColumns = techerDao.getAllColumns();
		
		for (int i = 0; i < allColumns.length; i++) {
			System.out.println("Teacher表allColumns:" + i + "  ：" + allColumns[i]);
		}
		
		System.out.println("查询全部Teacher");
		List<Techer> techerList = techerDao.loadAll();
		for (int i = 0; i < techerList.size(); i++) {
			System.out.println("techerList:" + i + "  ："
					+ techerList.get(i).getName() + " ");
		}

		
		//techer.refresh();
		//插入Student
		Student student = new Student(null, "学生2", new Date(), techer.getId());
		studentDao.insert(student);
		List<Student> listSu = studentDao.loadAll();
		for (int i = 0; i < listSu.size(); i++) {
			System.out.println("data:" + listSu.get(i).getBirthday());
		}
		student.refresh();
		
		System.out.println("查询Teacher的学生:?" + techer.getName() + "   :" + "  :"
				+ techer.getStudents().size());
		techer.refresh();
		techerDao.insertOrReplace(techer);

		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
				DateFormat.MEDIUM);

		techerList.clear();
		techerList.addAll(techerDao.loadAll());
		System.out.println("!!!!!!!!!!!!!!!!!!:" + techerList.size());
		
		for (int i = 0; i < techerList.size(); i++) {
			System.out.println("aa:?" + techerList.get(i).getName() + "   :"
					+ techerList.get(i).getStudents().size());
			List<Student> studentList = techerList.get(i).getStudents();
			for (int j = 0; j < studentList.size(); j++) {
				System.out.println("获取 " + techerList.get(i).getName()
						+ "老师 的学生：" + studentList.get(j).getName() + " "
						+ studentList.get(j).getBirthday());
			}
		}

		listSu = studentDao.loadAll();
		for (int i = 0; i < listSu.size(); i++) {
			System.out.println(listSu.get(i).getTecher().getName());
		}
		
		//不支持 删除 Teacher ，相应的Student就会删除
//		techerDao.deleteByKey(1L);
//		System.out.println("删除老师之后");
//		techerList = techerDao.loadAll();
//		for (int i = 0; i < techerList.size(); i++) {
//			System.out.println("techerList:" + i + "  ："
//					+ techerList.get(i).getName() + " ");
//		}
//		listSu = studentDao.loadAll();
//		for (int i = 0; i < listSu.size(); i++) {
//			System.out.println("data:" + listSu.get(i).getBirthday());
//		}
		
		
//		studentDao.getDatabase().beginTransaction();
//		try {
//			// 增删改查
//			// ……………………
//			studentDao.getDatabase().setTransactionSuccessful();
//		} catch (Exception e) {
//			// TODO: handle exception
//			studentDao.getDatabase().endTransaction();
//		}

	}

}
