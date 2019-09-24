package src;


public class Main {
	public static void main(String[] args) {
		Display display = new Display(256,512);
		display.init();
		while(true){
			display.update();
		}
	}
}
