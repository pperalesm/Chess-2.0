package chess;

public class Application {	
	public static void main(String[] args) {
		Game game;
		ChessNode chessNode = new ChessNode();
		while (true) {
			game = new Game(false, new DepthWithMonteCarlo(), new Dumb(), chessNode);
			System.out.println((game.run()? "Black" : "White") + " player wins!");
		}
	}
}
