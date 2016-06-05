package model;

/**
 * プレイヤーの役職の１つである狩人クラスです。
 * 村人陣営の役職であり、夜のターンにプレイヤー一人を護衛します。
 * 護衛が成功すると、プレイヤーが襲撃されずに朝を迎えることができます。
 * 自身を護衛対象に選択することはできません。
 * @author Y.tachibana
 *
 */
final class Hunter implements Role {
	
	
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
		return RoleName.HUNTER;
	}
	

	/**
	 * 夜のアクションを記述します。
	 * 狩人のアクションは護衛です。
	 */
	public void act(Player targetPlayer) {
		//護衛
	}

}
