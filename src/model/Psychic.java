package model;

/**
 * プレイヤーの役職の１つである霊媒師クラスです。
 * 村人陣営の役職であり、夜が開けた時点で処刑されたプレイヤーが人狼であるか否かがわかります。
 * @author Y.tachibana
 *
 */
final class Psychic implements Role {
	
	
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
		return RoleName.PSYCHIC;
	}


	/**
	 * 夜のアクションを記述します。
	 * 霊媒師のアクションは霊を呼ぶです。
	 */
	public void act(Player targetPlayer) {
		//霊を呼ぶ
	}

}
