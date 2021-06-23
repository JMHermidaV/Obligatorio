package Entities;

import Entities.CsvReaderIterator;

import java.io.File;
import java.util.Iterator;

public class CsvReader implements Iterable<String[]> {
	private final File file;

	public CsvReader(File file)
	{
		this.file = file;
	}

	@Override
	public Iterator<String[]> iterator()
	{
		return new CsvReaderIterator(this.file);
	}
}