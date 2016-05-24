package model;

/**
 * 役職インターフェースです。
 * 役職クラスは全てこのインタフェースを実装していなければいけません。
 * 具体的には、AbstractRoleにこのインタフェースを実装し、継承してください、
 * @author Y.tachibana
 *
 */
public interface Role {
	
	
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
	 * 人狼だと疑われるプレイヤーに票を入れる投票メソッドです。
	 */
	void vote(Player votedPlayer);
	
	
	/**
	 * 夜に行うアクションメソッドです。
	 */
	void synchAction(Player player, Player targetPlayer);
	
	
	/**
	 * 夜に行うアクションメソッドです。
	 */
	void act(Player targetPlayer);
	
	
	/**
	 * 役職の陣営を返すメソッド
	 */
	Team getTeam();
	
	
	/**
	 * 役職の人種を返すメソッド
	 */
	Race getRace();
	
	
	/**
	 * 役職名を返すメソッド
	 */
	String getRoleName();
	

}
