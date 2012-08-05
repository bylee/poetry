package com.poetry.install;

import java.util.Map;

public interface Install<T>
{
	Map<String, T> install() throws Exception;
}
