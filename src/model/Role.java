package model;

/**
 * 役職インターフェースです。
 * 役職クラスは全てこのインタフェースを実装していなければいけません。
 * 具体的には、AbstractRoleにこのインタフェースを実装し、実装後のAbstractRoleを継承してください。
 * @author Y.tachibana
 *
 */
public interface Role {
	
	
	/**
	 * 役職名の一覧をまとめた定数
	 * VILLAGER:村人
	 * WEREWOLF:人狼
	 * SEER:占い師
	 * PSYCHIC:霊媒師
	 * HUNTER:狩人
	 * FOX:妖狐
	 * @author Y.tachibana
	 */
	enum RoleName {
		VILLAGER,
		WEREWOLF,
	    SEER,
		PSYCHIC,
		HUNTER,
		FOX,
	}
	
	/**
	 * 陣営をまとめた定数
	 * 	VILLAGE:村人　WEREWOLF:人狼　FOX:妖狐
	 * @type Team
	 */
	enum Team { VILLAGER,WEREWOLF,FOX }
	
	
	/**
	 * 占われた際に判定される結果をまとめた定数
	 * @type Race
	 *
	 */
	enum Race { HUMAN, WEREWOLF }
	
	
	/**
	 * 夜に行うアクションメソッドです。
	 */
	void act(Player targetPlayer);
	
	
	/**
	 * 役職名を返すメソッド
	 */
	RoleName getRoleName();
	
	
	/**
	 * 役職の陣営を返すメソッド
	 */
	Team getTeam();
	
	
	/**
	 * 役職の人種を返すメソッド
	 */
	Race getRace();
	

}
