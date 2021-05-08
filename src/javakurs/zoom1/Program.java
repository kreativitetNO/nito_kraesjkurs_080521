package javakurs.zoom1;

public class Program
{
	public static void main(String[] args)
	{
		var rom1 = new Rom("Auditorium", "A100", -5, true);
		var rom2 = new Rom("Lab", "L100", 25, false);
		var rom3 = new Rom("Auditorium", "A100", 20, true);
		var rom4 = new Rom("Lab", "L101", 35, true);
		var rom5 = new Rom("Seminarrom", "S101", 35, true);
		
		System.out.println(rom1);
		System.out.println(rom2);
		
		System.out.println(rom1.equals(rom2));
		System.out.println(rom1.equals(rom3));
		System.out.println(rom2.equals(rom3));
		
		var romTabell = new Rom[] { rom1, rom2, rom3, rom4, rom5 };
		
		for (Rom rom : romTabell)
		{
			if (rom.getHarDataUtstyr() && rom.getPlasser() >= 30)
			{
				System.out.println(rom);
			}
		}
		
		Rom[] romTabellFraFil = Rom.lesFraFil("romZoom.txt");
		
		if (romTabellFraFil != null)
		{
			for (Rom rom : romTabellFraFil)
			{
				System.out.println(rom);
			}
		}
	}
}


// Ikke pensum (enda)!
//visRom(romTabell, rom -> { return rom.getHarDataUtstyr() && rom.getPlasser() >= 30; });
//visRom(romTabell, rom -> { return !rom.getHarDataUtstyr(); });
// Ikke pensum (enda)!
//private static void visRomMedLambda(Rom[] romTabell, RomFilter filter)
//{
//	for (Rom rom : romTabell)
//	{
//		if (filter.run(rom))
//		{
//			System.out.println(rom);
//		}
//	}		
//}
//@FunctionalInterface
//interface RomFilter
//{
//	boolean run(Rom rom);
//}