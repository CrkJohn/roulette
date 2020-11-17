package technical.test.massiv.model;

import technical.test.massiv.model.utils.Color;

public class ColorBet  extends Bet{

	private Color color;

	public ColorBet(Integer money, String userId, Color color) {

		super(money, userId);
		this.color = color;
	}

	@Override
	public boolean isWinner(Integer winnerNumber) {

		return color.equals(Color.BLACK) == (winnerNumber % 2 != 0);
	}

	@Override
	public void giveProfit() {

		gain = money * 1.8;
	}

	public Color getColor() {

		return color;
	}

	public void setColor(Color color) {

		this.color = color;
	}

}

