package technical.test.massiv.model;

import java.time.LocalDateTime;

/**
 * Bet is used to represent the roulette bet
 *
 * @author <a href="johnibanezt@gmail.com">John D. Ibanez</a>
 */
public abstract class Bet {

	private String id;

	protected Integer money;

	protected LocalDateTime dateCreationBet;

	protected String userId;

	protected double gain;

	public Bet(Integer money, String userId) {

		this.money = money;
		this.userId = userId;
		this.gain = 0;
		this.dateCreationBet = LocalDateTime.now();
	}

	public abstract void giveProfit();

	public abstract boolean isWinner(Integer winnerNumber);

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public Integer getMoney() {

		return money;
	}

	public void setMoney(Integer money) {

		this.money = money;
	}

	public LocalDateTime getDateCreationBet() {

		return dateCreationBet;
	}

	public void setDateCreationBet(LocalDateTime dateCreationBet) {

		this.dateCreationBet = dateCreationBet;
	}

	public String getUserId() {

		return userId;
	}

	public void setUserId(String userId) {

		this.userId = userId;
	}




}
