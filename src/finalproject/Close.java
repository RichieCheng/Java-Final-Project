package finalproject;

public class Close {
	
	static Thread t3 = new Thread(new close());
	public static void active(){
		t3.start();
	}
	public static class close implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);
		}
		
	}

}
