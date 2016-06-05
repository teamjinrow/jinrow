package model;

import java.util.ArrayList;

import model.Player.PlayerStatus;
import model.Role.RoleName;

/**
 * ゲームステータスのデータ保存用クラスです。
 * プレイヤーのステータスを一元管理します
 * @author Y.Tachibana
 */
public final class StatusManage {
	
	
	/**
	 * プレイヤーを一元管理するリスト
	 * @type ArrayList<Player>
	 */
	private static ArrayList<Player> playerList;
	
	
	/**
	 * 投票メソッドインスタンスを保持します 
	 */
	private static VoteLogic voteLogic;
	
	
	/**
	 * 投票メソッドインスタンスを保持します 
	 */
	private static ActionLogic actionLogic;
	
	/**
	 * ターン数を表すプロパティ
	 * 昼と夜合わせて１ターン
	 * @type int
	 */
	private static int turn = 1;
	
	
	/**
	 * 昼、夜の列挙型
	 * @author Y.tachibana
	 *
	 */
	public enum Phase{昼,夜}
	
	
	/**
	 * フェーズ管理プロパティ
	 * 取りうる値は昼、夜
	 * @type boolean
	 */
	private static Phase phase = Phase.昼;

	
	/**
	 * システムメッセージ
	 * @type String
	 */
	private static String message = "";
	
	
	/**
	 * ゲーム結果メッセージ
	 * @type String
	 */
	private static String gameResultMessage = "";
	
	
	/**
	 * 生存している人狼の数 
	 */
	private static int sizeAliveWerewolf = 0;
	
	
	/**
	 * 生存している人間の数
	 */
	private static int sizeAliveHuman = 0;
	
	
	/**
	 * このクラスはインスタンス化を想定していません。
	 * そのため、Private修飾子を用いて外部からのインスタンス化を禁じています。
	 * また、本クラス内で呼び出した場合はアサーションエラーをスローします。
	 */
	private StatusManage() {
		throw new AssertionError();
	}

	
	/**
	 * static初期化子を用いて、プレイヤーリストのインスタンスを一度のみ生成します。
	 */
	static {
		 playerList = new ArrayList<Player>();
	}
	
	
	/**
	 * プレイヤーオブジェクトをプレイヤーリストに追加します。
	 * @param player
	 */
	public static void addUser(Player player) {
		
		playerList.add(player);
		
	}

	
	/**
	 * ゲームステータスを初期化します。
	 */
	public static void reset() {
		playerList = new ArrayList<Player>();
		turn = 1;
		phase = Phase.昼;
		message = "";
		gameResultMessage = "";
		sizeAliveWerewolf = 0;
		sizeAliveHuman = 0;
	}
	
	
	/**
	 * プレイヤーリストを返します。
	 * @return playerList
	 */
	public static ArrayList<Player> getPlayerList() {
		return playerList;
	}
	
	
	/**
	 * プレイヤーリストよりプレイヤー情報を返します。
	 * @return Player
	 */
	public static Player getPlayer(String playerName){
		for (Player player : playerList) {
			if (player.getPlayerName().equals(playerName)) {
				return player;
			}
		}
		Player player = null;
		return player;
	}
	
	
	/**
	 * 全プレイヤーの投票数及び投票結果を初期化します。
	 * 
	 */
	public static void resetVotesCastWithAllPlayer() {
		
		for (Player player : playerList) 
			player.resetVotesCast();
		
	}
	
	
	/**
	 * 全プレイヤーの投票結果を初期化します。
	 * 
	 */
	public static void resetTargetByWolfWithAllPlayer() {
		
		for (Player player : playerList) 
			player.resetTargetByWerewolf();
		
	}
	
	
	/**
	 * ゲームの勝敗判定を行います。
	 * 判定条件
	 * ・人狼が１匹もいない場合：村人陣営の勝利
	 * ・人狼の数が人間の数以上になった場合：人狼陣営の勝利
	 * ・上記の勝利条件を満たしたタイミングで、妖狐が生存している場合：妖狐陣営の勝利
	 * @return boolean
	 */
	public static boolean gameCheck() {
		
		//人間と人狼の数を集計
		countAliveRace();
		
		boolean isGameEnd = false;
		if (sizeAliveWerewolf == 0) {
			//村人の勝利（仮）
			isGameEnd = true;
			StatusManage.setGameResultMessage("村人の勝利です。");
		}
		
		if (sizeAliveHuman <= sizeAliveWerewolf) {
			//人狼の勝利（仮）
			isGameEnd = true;
			StatusManage.setGameResultMessage("人狼の勝利です。");
		}
		
		if (isGameEnd && isAliveFox()) {
			//妖狐の勝利
			StatusManage.setGameResultMessage("妖狐の勝利です。");
		}
		
		return isGameEnd;
		
	}
	
	
	/**
	 * 生存しているプレイヤーのサイズを返します。
	 * @return　int
	 */
	public static int sizeAlivePlayer() {
		
		int sizeAlivePlayer = 0;
		for (Player player:playerList) 
			if (player.getPlayerStatus() == PlayerStatus.ALIVE)
				sizeAlivePlayer += 1; 
		
		return sizeAlivePlayer;
		
	}
	
	
	/**
	 * 生存している人種の数を計算し、プロパティに結果を保持します。
	 */
	private static void countAliveRace() {
		
		// プロパティの初期化
		sizeAliveHuman = 0;
		sizeAliveWerewolf = 0;
		
		for (Player player: playerList) {
			if (player.getPlayerStatus() == PlayerStatus.DEAD) 
				continue;
			switch (player.getRole().getRace()) {
			case HUMAN:
				sizeAliveHuman += 1;
				break;
			case WEREWOLF:
				sizeAliveWerewolf += 1;
				break;
			}
		}
	}
	
	
	/**
	 * 妖狐が生存しているかどうかを確認する
	 * @return　boolean 生存ならばtrue 死亡ならばfalse
	 */
	private static boolean isAliveFox() {
		for (Player player:playerList) {
			if (player.getRole().getRoleName() == RoleName.FOX && player.getPlayerStatus() == PlayerStatus.ALIVE) {
				return true;
			}
		}
		
		return false;
		
	}
	
	
	/**
	 * メッセージのゲッター
	 * @return　String メッセージ
	 */
	public static String getMessage() {
		return message;
	}

	
	/**
	 * メッセージのセッター
	 * @param message
	 */
	public static void setMessage(String message) {
		StatusManage.message = message;
	}

	
	/**
	 * ゲーム決着時のメッセージを取得します。
	 * @return
	 */
	public static String getGameResultMessage() {
		return gameResultMessage;
	}

	
	/**
	 * ゲーム決着時のメッセージをセットします。
	 * @param gameResultMessage
	 */
	public static void setGameResultMessage(String gameResultMessage) {
		StatusManage.gameResultMessage = gameResultMessage;
	}

	
	/**
	 * ターン数を取得します。
	 * @return turn
	 */
	public static int getTurn() {
		return turn;
	}

	
	/**
	 * ターン数を＋１します。
	 * @param turn
	 */
	public static void incrementTurn() {
		StatusManage.turn += 1;
	}

	
	/**
	 * フェーズ（昼・夜）を返します。
	 * @return phase
	 */
	public static Phase getPhase() {
		return phase;
	}

	
	/**
	  フェーズ（昼・夜）を設定します。
	 * @param Phase
	 */
	public static void setPhase(Phase phase) {
		StatusManage.phase = phase;
	}

	
	/**
	 * 投票ロジックのインスタンスを返します。
	 * インスタンスがない場合は、新しいインスタンスを生成します。
	 * @return
	 */
	public static synchronized VoteLogic getVoteLogic() {
		if (voteLogic == null)
			voteLogic = new VoteLogic();
		return voteLogic;
	}


	/**
	 * 投票ロジックのインスタンスの参照をnullで上書きします。
	 * 投票ロジックが不要になったタイミングで使用してください。
	 */
	public static void initVoteLogic() {
		voteLogic = null;
	}


	/**
	 * 行動ロジックのインスタンスを返します。
	 * インスタンスがない場合は、新しいインスタンスを生成します。
	 * @return
	 */
	public static synchronized ActionLogic getActionLogic() {
		if (actionLogic == null)
			actionLogic = new ActionLogic();
		return actionLogic;
	}

	
	/**
	 * 行動ロジックのインスタンスの参照をnullで上書きします。
	 * 行動ロジックが不要になったタイミングで使用してください。
	 */
	public static void initActionLogic() {
		actionLogic = null;
	}

}
