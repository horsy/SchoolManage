package com.school.dao;

import java.io.IOException;

public interface DatabaseDao {

	public boolean backup(String path, boolean autoBack) throws IOException;
}
