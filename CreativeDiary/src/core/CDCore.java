package core;
import com.hk.ui.HomePage;
import com.hk.ui.LockPage;

public class CDCore{
	private static LockPage lock;
	private static HomePage homePage;
	
	private CDCore() {
		
	}
	public static HomePage getHomePage() {
		if(homePage==null) {
			homePage = new HomePage();
		}
		return homePage;
	}
	
	public static LockPage getLockPage() {
		if(lock==null) {
			lock = new LockPage();
		}
		return lock;
	}
	
	public static void resetEverything() {
		lock=null;
		homePage = null;
	}
}
