package model;

/**
 * プレイヤーの役職の１つである村人クラスです。
 * 村人陣営の基本役職であり、特殊能力は皆無です。
 * @author Y.tachibana
 *
 */
final class Villager extends AbstractRole {
	
	
	/**
	 * 役職名：村人
	 */
	private String roleName = "Villager";
	
	
	/**
	 * 所属している陣営を返します。陣営には以下の３つがあります。
	 * 村人陣営、人狼陣営、妖狐陣営
	 * このメソッドは村人陣営を返します。
	 * @return Team.VILLAGER
	 */
	public Team getTeam() {
		return Team.VILLAGER;
		
	}
	
	
	@Override
	public Race getRace() {
		return Race.HUMAN;
	}

	
	public String getRoleName() {
		return roleName;
	}

	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	/**
	 * 夜のアクションを記述します。
	 * 村人のアクションは疑う（何もしない）です。
	 */
	public void act(Player targetPlayer) {
		//疑う
	}

}
