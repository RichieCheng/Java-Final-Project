package finalproject;

public class mazeuse extends FP{

	public static void move() {
		next = 31 ;
			if (mazemap[y/30][x/30]==11 )
			{
				y = y - next ;
				if (mazemap[y/30][x/30]==15)
				{
					mazemove = false;
				}
				else if (mazemap[y/30][x/30]==0)
				{
					while(mazemap[y/30][x/30]==0)
					{
						y = y - next ;
						if (mazemap[y/30][x/30]==15)
						{
							mazemove = false;
						}
					}
				}

			}
			else if (mazemap[y/30][x/30]==12)
			{
				y = y + next ;
				if (mazemap[y/30][x/30]==15)
				{
					mazemove = false;
				}
				else if (mazemap[y/30][x/30]==0)
				{
					while(mazemap[y/30][x/30]==0)
					{
						y = y + next ;
						if (mazemap[y/30][x/30]==15)
						{
							mazemove = false;
						}
					}
				}

			}
			else if (mazemap[y/30][x/30]==13)
			{
				x = x - next ;
				if (mazemap[y/30][x/30]==15)
				{
					mazemove = false;
				}
				else if (mazemap[y/30][x/30]==0)
				{
					while(mazemap[y/30][x/30]==0)
					{
						x = x - next ;
						if (mazemap[y/30][x/30]==15)
						{
							mazemove = false;
						}
					}
				}

			}
			else if (mazemap[y/30][x/30]==14)
			{
				x = x + next ;
				if (mazemap[y/30][x/30]==15)
				{
					mazemove = false;
				}
				else if (mazemap[y/30][x/30]==0)
				{
					while(mazemap[y/30][x/30]==0)
					{
						x = x + next ;
						if (mazemap[y/30][x/30]==15)
						{
							mazemove = false;
						}
					}
				}

			}

	}
}
