package technical.test.massiv.model;

import org.springframework.data.mongodb.core.mapping.Document;
import technical.test.massiv.model.utils.Color;

/**
 * ColorBet is used to represent the bet given by Color.
 *
 * @author <a href="johnibanezt@gmail.com">John D. Ibanez</a>
 */
@Document("Bet")
public class ColorBet  extends Bet{

	private Color color;

	public ColorBet(Integer money, String userId, Color color) {

		super(money, userId);
		this.color = color;
	}

	public ColorBet() {

	}

	@Override
	public boolean isWinner(Integer winnerNumber) {

		return color.equals(Color.BLACK) == (winnerNumber % 2 != 0);
	}

	@Override
	public Bet getProfit() {
		ColorBet bet = new ColorBet();
		bet.setGain(this.getGain());
		bet.setId(this.getId());
		bet.setUserId(this.getUserId());
		bet.setColor(this.color);
		return bet;
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

