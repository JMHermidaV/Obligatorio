package Entities;

import java.util.Iterator;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class CsvReaderIterator implements Iterator<String[]>
{
	private BufferedReader buff_reader;
	private RowBuilder row_builder;
	
	public CsvReaderIterator(File file)
	{
		try
		{
			this.row_builder = new RowBuilder();
			FileReader fr    = new FileReader(file);
			this.buff_reader = new BufferedReader(fr);
		}
		catch (IOException e)
		{
			throw new RuntimeException("Error al crear el FileReader", e);
		}
	}
	
	@Override
	public boolean hasNext()
	{
		try
		{
			for (int c = this.buff_reader.read(); c != -1; c = this.buff_reader.read())
			{
				char character = (char) c;
				if (this.row_builder.append_character(character)) {
					return true;
				}
			}
		}
		catch (IOException e)
		{
			throw new RuntimeException("Error al leer el archivo", e);
		}
		
		return false;
	}
	@Override
	public String[] next()
	{
		String[] record = this.row_builder.get_record();
		this.row_builder.reset();
		return record;
	}

	public RowBuilder getRow_builder() {
		return row_builder;
	}
}