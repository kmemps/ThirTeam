package battle;

import java.util.Random;
import unit.Unit;

public class AttackType {
	private Unit attacker;
	/**
	 * A Constructor for the class that determines who is the attacker
	 * @param attacker Unit of the attacker (instance variable)
	 */
	public AttackType(Unit attacker){
		this.attacker = attacker;
	}
	
	//this method changed the stats of the player and enemy depending on their stats
	public void attackedThem(Unit target, AttackTypes type) {
		switch(type) {
			case MELEE:
				//this is for melee attacks and is dependent on their damage
				Random random = new Random();
				int randDmg = random.nextInt(4);
				int melDamage = type.getDamage();
				melDamage = ((melDamage + attacker.getStats().getStr())*-1) -(-randDmg); //strength level is just added to their base damage
				target.getStats().setHealth(melDamage+target.getStats().getDef());
				break;
				
			case RANGED:
				//ranged attacks dependent on magic
				int magDamage = type.getDamage();
				magDamage = ((magDamage + attacker.getStats().getMag())*-1); //their magic level is just added to their base damage
				target.getStats().setHealth(magDamage+target.getStats().getDef());
				break;
				
			case HEAL:
				//player heals themselves
				//extra heal is from the mag stat of the player
				int heal = type.getHeal();
				heal = (heal + attacker.getStats().getMag());
				attacker.getStats().setHealth(heal);
				break;
			
			
				
		}
	}

}
