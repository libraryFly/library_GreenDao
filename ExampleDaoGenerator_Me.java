/*
 * Copyright (C) 2011 Markus Junginger, greenrobot (http://greenrobot.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.greenrobot.daogenerator.gentest;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.PropertyType;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

/**
 * Generates entities and DAOs for the example project DaoExample.
 * 
 * Run it as a Java application (not Android).
 * 
 * @author Markus
 */
public class ExampleDaoGenerator_Me {

	public static void main(String[] args) throws Exception {
		// 该方法第一个参数用来更新数据库版本号，第二个参数为要生成的DAO类所在包路径。
		Schema schema = new Schema(1, "com.example.test_android_green_dao.dao");

		// 建立学生、教师表
		CreatTechersAndStudents(schema);
		new DaoGenerator().generateAll(schema,
				"../test_android_green_dao/src-gen");
		// 其中src-gen这个目录名需要在运行前手动创建，否则会报错。
	}

	private static void CreatTechersAndStudents(Schema schema) {
		Entity techer = schema.addEntity("Techer");
		techer.setTableName("TECHERS");
		techer.addIdProperty();
		techer.addStringProperty("name").notNull();

		Entity student = schema.addEntity("Student");
		student.setTableName("STUDENTS");
		student.addIdProperty();
		// 姓名
		student.addStringProperty("name");
		// 出生日期
		Property birthday = student.addDateProperty("birthday").getProperty();
		/**
		 * Student表中添加Teacher列
		 */
		Property sTheacherID = student.addLongProperty("teacher_id")
				.getProperty();
		student.addToOne(techer, sTheacherID);
		/**
		 * Teacher表中加入student_id列
		 */
		ToMany techerToMany = techer.addToMany(student, sTheacherID);
		// 教师表中插入学生id字段，按时间升序排列
		techerToMany.setName("students");
		techerToMany.orderDesc(birthday);

		
	}
}
