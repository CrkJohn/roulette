package technical.test.massiv.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationRange {

	@Value("${roulette.min.position}")
	public  Integer MIN_SPOT_BET;

	@Value("${roulette.max.position}")
	public  Integer MAX_SPOT_BET;

	@Value("${roulette.max.money}")
	public  Integer MAX_MONEY_BET;

	@Value("${roulette.min.money}")
	public Integer MIN_MONEY_BET;

	public ConfigurationRange() {

	}

	public Integer getMinSpotBet() {

		return MIN_SPOT_BET;
	}

	public Integer getMaxSpotBet() {

		return MAX_SPOT_BET;
	}

	public Integer getMaxMoneyBet() {

		return MAX_MONEY_BET;
	}

	public Integer getMinMoneyBet() {

		return MIN_MONEY_BET;
	}
}
