package Entities;

import java.util.ArrayList;

public class RowBuilder
{
	private boolean           in_quotes     = false;
	private boolean           escaped       = false;
	private ArrayList<String> record_buffer = new ArrayList<>();
	private StringBuilder     value_buffer  = new StringBuilder();

	public void reset()
	{
		this.in_quotes = false;
		this.record_buffer.clear();
	}

	// Retorna true si termino de leer un registro (fila de csv)
	public boolean append_character(char c)
	{
		if (escaped)
		{
			this.value_buffer.append(c);
			escaped = false;
			return false;
		}

		if (c == '"')
		{
			in_quotes = !in_quotes;
			return false;
		}

		if (in_quotes)
		{
			if (c == '\\')
			{
				this.escaped = true;
			}
			else
			{
				this.value_buffer.append(c);
			}

			return false;
		}

		if (c == ',')
		{
			record_buffer.add(value_buffer.toString());
			this.value_buffer.setLength(0);
			return false;
		}

		if (c == '\n')
		{
			record_buffer.add(value_buffer.toString());
			return true;
		}

		if (c == '\r')
		{
			return false;
		}

		this.value_buffer.append(c);
		return false;
	}

	public String[] get_record()
	{
		String[] record = new String[this.record_buffer.size()];

		for (int i = 0; i < this.record_buffer.size(); i += 1)
		{
			record[i] = this.record_buffer.get(i);
		}

		return record;
	}
}