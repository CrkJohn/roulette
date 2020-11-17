package technical.test.massiv.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("Bet")
public class PositionBet  extends Bet{

	private Integer position;

	public PositionBet(Integer money, String userId) {

		super(money, userId);
	}

	public PositionBet() {

		super();
	}

	@Override
	public boolean isWinner(Integer winnerNumber) {

		return position.equals(winnerNumber);
	}

	@Override
	public Bet getProfit() {
		PositionBet bet = new PositionBet();
		bet.setGain(this.getGain());
		bet.setId(this.getId());
		bet.setUserId(this.getUserId());
		bet.setPosition(this.position);
		return bet;
	}

	@Override
	public void giveProfit() {

		super.gain = money * 5;
	}

	public Integer getPosition() {

		return position;
	}

	public void setPosition(Integer position) {

		this.position = position;
	}


}
