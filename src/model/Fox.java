package model;

/**
 * プレイヤーの役職の１つである妖狐クラスです。
 * 妖狐陣営の基本役職であり、人狼に襲われても死亡しません。
 * また、占い師に占われた場合は、死亡します。
 * @author Y.tachibana
 *
 */
final class Fox extends AbstractRole {
	
	
	/**
	 * 役職名：妖狐
	 */
	private final String roleName = "Fox";
	
	
	/**
	 * 所属している陣営を返します。陣営には以下の３つがあります。
	 * 村人陣営、人狼陣営、妖狐陣営
	 * このメソッドは妖狐陣営を返します。
	 * @return Team.FOX
	 */
	public Team getTeam() {
		return Team.FOX;
		
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
	 * 役職名を取得します。
	 * @return roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	
	/**
	 * 夜のアクションを記述します。
	 * 妖狐のアクションは「何もしない」です。
	 */
	public void act(Player targetPlayer) {
		// 何もしない
	}

	
}
