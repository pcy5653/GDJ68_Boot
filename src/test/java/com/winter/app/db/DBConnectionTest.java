package com.winter.app.db;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// SpringBootTest할때 쓰는 Test Annotation
@SpringBootTest 
class DBConnectionTest {
	
	// database 연결box?,,
	@Autowired
	private DataSource dataSource;
	
	@Test
	void test() throws Exception {
		// DB연결되어있으면 null X
		Connection connection = dataSource.getConnection();
		assertNotNull(connection);;
	}

}
