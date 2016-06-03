package model;

/**
 * プレイヤーの役職の１つである人狼クラスです。
 * 人狼陣営の基本役職であり、毎晩人狼以外のプレイヤーを一人殺害します。
 * @author Y.tachibana
 *
 */
final class Werewolf implements Role {
	
	
	/**
	 * 所属している陣営を返します。陣営には以下の３つがあります。
	 * 村人陣営、人狼陣営、妖狐陣営
	 * このメソッドは人狼陣営を返します。
	 * @return Team.WEREWOLF
	 */
	public Team getTeam() {
		return Team.WEREWOLF;
	}


	/**
	 * 占い師に占われた場合に判別する人種を返します。人種には以下の２つがあります。
	 * 人間、人狼
	 * このメソッドは人狼を返します。
	 * @return Race.WEREWOLF
	 */
	public Race getRace() {
		return Race.WEREWOLF;
	}

	
	/**
	 * 役職名を取得します。
	 * @return roleName
	 */
	public RoleName getRoleName() {
		return RoleName.WEREWOLF;
	}


	/**
	 * 夜のアクションを記述します。
	 * 人狼のアクションは「殺害する」です。
	 * 殺害候補の投票数をインクリメントし、最多の投票数を獲得した対象を殺害します。
	 * 投票数が分散した場合は、いずれか一人が自動的に殺害対象に選ばれます。
	 */
	public void act(Player targetPlayer) {
		// 殺害候補に投票
		targetPlayer.incrementVotesCast();
	};

	
}
