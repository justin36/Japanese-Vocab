package project.service;

import java.io.Reader;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// sqlSessionFactory �ν��Ͻ��� �ϳ� �����ϰ�
// sqlSession�� ��ȯ���ִ� ��ƿŬ���� 
// why? sqlSessionFactory�� �������� �ƴ� �ϳ��� �� �ִ°� ����.
public class MyBatisConfig {
	//static - �ϳ��� ���鲨�ϱ�
	private static SqlSessionFactory sqlSessionFactory;
	
	public static SqlSessionFactory getSqlSessionFactory() {
		if (sqlSessionFactory == null) {
			String resource = "mybatis-config.xml";	//��� �����ֱ�
			try {
				Reader reader = Resources.getResourceAsReader(resource);
				// 
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sqlSessionFactory;
	}
}
