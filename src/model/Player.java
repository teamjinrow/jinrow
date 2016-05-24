package model;

/**
 * プレイヤークラスです。
 * プレイヤーの基本属性と基本操作を定義しています。
 * @author Y.tachibana
 *
 */
public final class Player {
	
	
	/**
	 * ユーザ名
	 * @type String
	 */
	private String playerName;
	
	
	/**
	 * 役職
	 * @type Role
	 */
	private Role role;
	
	
	/**
	 * 死亡フラグ
	 * true:死亡　false:生存 
	 * @type boolean
	 */
	private boolean deadFlag;
	
	
	/**
	 * 得票数
	 * @type int
	 */
	private int votesCast = 0;
	
	
	/**
	 * 得票数
	 * @type int
	 */
	private int targetByWerewolf = 0;

	
	/**
	 * ユーザ名と死亡フラグを初期化します。
	 * @param playerName
	 */
	public Player(String playerName) {
		this.playerName = playerName;
		this.deadFlag = false;
		
	}
	
	
	/**
	 * プレイヤー名を取得します。
	 * @return　playerName
	 */
	public String getPlayerName() {
		return playerName;
	}

	
	/**
	 * 役職を取得します。
	 * @return role
	 */
	public Role getRole() {
		return role;
	}

	
	/**
	 * 役職を設定します。
	 * @param role
	 */
	public void setRole(Role role) {
		this.role = role;
	};

	
	/**
	 * 死亡フラグを取得します。
	 * false:生存
	 * true:死亡
	 * @return deadFlag
	 */
	public boolean isDeadFlag() {
		return deadFlag;
	}

	
	/**
	 * 死亡フラグを設定します。
	 * false:生存
	 * true:死亡
	 * @param deadFlag
	 */
	public void setDeadFlag(boolean deadFlag) {
		this.deadFlag = deadFlag;
	}
	
	
	/**
	 * 他プレイヤーからの投票数を取得します。
	 * @return　votesCast
	 */
	public int getVotesCast() {
		return votesCast;
	}
	
	
	/**
	 * 投票数をプラス１します。
	 */
	public void addVotesCast() {
		votesCast += 1;
	}
	
	
	/**
	 * 投票数を初期値に戻します。
	 */
	public void resetVotesCast() {
		votesCast = 0;
	}

	
	/**
	 * 何匹の人狼から狙われているかを示す数を取得します。
	 * @return　targetByWerewolf
	 */
	public int getTargetByWerewolf() {
		return targetByWerewolf;
	}

	
	/**
	 * 人狼から狙われている数を＋１します。
	 */
	public void addTargetByWerewolf() {
		this.targetByWerewolf += 1;
	}
	
	
	/**
	 * 人狼にターゲットにされている状態を初期値に戻します。
	 */
	public void resetTargetByWerewolf() {
		this.targetByWerewolf = 0;
	}


}
