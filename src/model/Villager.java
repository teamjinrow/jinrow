package model;

/**
 * プレイヤーの役職の１つである村人クラスです。
 * 村人陣営の基本役職であり、特殊能力は皆無です。
 * @author Y.tachibana
 *
 */
final class Villager implements Role {
	
	
	/**
	 * 所属している陣営を返します。陣営には以下の３つがあります。
	 * 村人陣営、人狼陣営、妖狐陣営
	 * このメソッドは村人陣営を返します。
	 * @return Team.VILLAGER
	 */
	public Team getTeam() {
		return Team.VILLAGER;
		
	}
	
	
	/**
	 * 占い師に占われた場合に判別する人種を返します。人種には以下の２つがあります。
	 * 人間、人狼
	 * このメソッドは人間を返します。
	 * @return Race.HUMAN
	 */
	public Race getRace() {
		return Race.HUMAN;
	}

	
	/**
	 * 役職名を返します。
	 */
	public RoleName getRoleName() {
		return RoleName.VILLAGER;
	}


	/**
	 * 夜のアクションを記述します。
	 * 村人のアクションは疑う（何もしない）です。
	 */
	public void act(Player targetPlayer) {
		//疑う
	}

}
