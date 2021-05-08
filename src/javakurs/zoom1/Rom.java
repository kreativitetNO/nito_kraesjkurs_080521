package javakurs.zoom1;

import java.io.*;
import java.util.*;

public class Rom
{
	enum RomType { Auditorium, Seminarrom, Lab, Grupperom };
	
	private String romType;
	private String romNummer;
	private int plasser;
	private boolean harDataUtstyr;
	
	public Rom(String romType, String romNummer, int plasser, boolean harDataUtstyr)
	{
		this.setRomType(romType);
		this.setRomNummer(romNummer);
		this.setPlasser(plasser);
		this.setHarDataUtstyr(harDataUtstyr);
	}

	public Rom(String romType, String romNummer)
	{
		this(romType, romNummer, 0, false);
	}
	
	@Override
	public String toString()
	{
		return String.format("Romtype: %s\nromNummer: %s\nPlasser: %d\nHar dataustyr: %s\n",
				romType, romNummer, plasser, harDataUtstyr ? "Ja" : "Nei");
	}
	
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null) return false;
		if (o.getClass() != this.getClass()) return false;
		Rom annet = (Rom)o; // Casting
		return (this.romType.equals(annet.romType) &&
				this.romNummer.equals(annet.romNummer) &&
				this.plasser == annet.plasser &&
				this.harDataUtstyr == annet.harDataUtstyr);
	}

	public String getRomType()
	{
		return romType;
	}

	public void setRomType(String romType)
	{
		this.romType = romType;
	}

	public String getRomNummer()
	{
		return romNummer;
	}

	public void setRomNummer(String romNummer)
	{
		this.romNummer = romNummer;
	}

	public int getPlasser()
	{
		return plasser;
	}

	public void setPlasser(int plasser)
	{
		if (plasser < 0)
		{
			plasser = 0;
		}
		else
		{
			this.plasser = plasser;
		}
	}
	
	public boolean getHarDataUtstyr()
	{
		return harDataUtstyr;
	}

	public void setHarDataUtstyr(boolean harDataUtstyr)
	{
		this.harDataUtstyr = harDataUtstyr;
	}
	
	public static Rom[] lesFraFil(String filnavn)
	{
		Rom[] rom = null;
		Scanner s = null;
		var f = new File(filnavn);
		if (!f.exists())
		{
			System.out.println("Filen " + filnavn + " eksisterer ikke...");
			return rom;
		}
		try
		{
			int antall = 0;
			s = new Scanner(f);
			while (s.hasNextLine())
			{
				s.nextLine();
				antall++;
			}
			s.close();
			
			rom = new Rom[antall];
			int teller = 0;
			s = new Scanner(f);
			while (s.hasNextLine())
			{
				var line = s.nextLine();
				var felt = line.split(";");
				rom[teller++] = new Rom(felt[0], felt[1], Integer.parseInt(felt[2]), felt[3].equals("1"));
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally {
			if (s != null)
			{
				s.close();
			}
		}
		return rom;
	}
}
