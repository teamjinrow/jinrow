package model;

import java.util.ArrayList;

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
	 * ターン数を表すプロパティ
	 * 昼と夜合わせて１ターン
	 * @type int
	 */
	private static int turn = 1;
	
	
	/**
	 * 昼の行動、夜の行動を区別するためのプロパティ
	 * 昼：false
	 * 夜：true
	 * @type boolean
	 */
	private static boolean dayOrNight = false;

	
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
	 * 全プレイヤーの投票数
	 */
	private static volatile int sizeVote;

	
	/**
	 * 全プレイヤーのアクション数
	 */
	private static volatile int sizeAction;
	
	
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
		dayOrNight = false;
		message = "";
		gameResultMessage = "";
		sizeVote = 0;
		sizeAction = 0;
		sizeAliveWerewolf = 0;
		sizeAliveHuman = 0;
	}
	
	
	/**
	 * 
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
	 *　各プレイヤーの投票結果を集計し、一番票の多いプレイヤーの死亡フラグをONにします。
	 *　戻り値として、処刑されたプレイヤーのインスタンスを返します。
	 * [ToDo]投票数が同じ場合には再投票が実施されるように修正
	 * @returnPlayer
	 */
	public static Player voteResult(){
		
		Player executedPlayer = null;
		int maxVotesCast = 0;
		for (Player player : playerList) 
			if (player.isDeadFlag() == false && maxVotesCast < player.getVotesCast()) {
				maxVotesCast = player.getVotesCast();
				executedPlayer = player;
			} else 
			if (player.isDeadFlag() == false && maxVotesCast == player.getVotesCast()) {
				
			}
		
		executedPlayer.setDeadFlag(true);
		setMessage(executedPlayer.getPlayerName() + "さんが処刑されました。");
		
		return executedPlayer;
		
	}
	
	
	/**
	 *　各プレイヤーの投票結果を集計し、一番票の多いプレイヤーの死亡フラグをONにします。
	 *　戻り値として、殺害されたプレイヤーのインスタンスを返します。
	 * @returnPlayer
	 */
	public static Player actionResult(){
		
		Player killedPlayer = null;
		int maxVotesCast = 0;
		for (Player player : playerList) 
			if (player.isDeadFlag() == false && maxVotesCast < player.getTargetByWerewolf()) {
				maxVotesCast = player.getVotesCast();
				killedPlayer = player;
			} else 
			if (player.isDeadFlag() == false && maxVotesCast == player.getTargetByWerewolf()) {
				
			}
		
		if (killedPlayer != null) { 
			killedPlayer.setDeadFlag(true);
			setMessage(killedPlayer.getPlayerName() + "さんが殺害されました。");
		} else {
			setMessage("何事もなく朝を迎えました。");
		}
		
		return killedPlayer;
		
	}
	
	
	/**
	 * 全プレイヤーの投票数及び投票結果を初期化します。
	 * 
	 */
	public static void resetVotesCastWithAllPlayer() {
		
		StatusManage.sizeVote = 0;
		for (Player player : playerList) 
			player.resetVotesCast();
		
	}
	
	
	/**
	 * 全プレイヤーの投票数及び投票結果を初期化します。
	 * 
	 */
	public static void resetTargetByWolfWithAllPlayer() {
		
		StatusManage.sizeAction = 0;
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
			if (player.isDeadFlag() == false)
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
			if (player.isDeadFlag()) 
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
			if (player.getRole().getRoleName() == "Fox" && player.isDeadFlag() == false) {
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
	 * 投票総数を返します。
	 * @return　sizeVote
	 */
	public static int getSizeVote() {
		return sizeVote;
	}

	
	/**
	 * 投票総数を＋１します。
	 */
	public static void incrementSizeVote() {
		StatusManage.sizeVote += 1;
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
	 * 夜か昼かをブール値で返します。
	 * false:昼
	 * true:夜
	 * @return
	 */
	public static boolean isDayOrNight() {
		return dayOrNight;
	}

	
	/**
	 * 夜か昼かをブール値で設定します。
	 * false:昼
	 * true:夜
	 * @param dayOrNight
	 */
	public static void setDayOrNight(boolean dayOrNight) {
		StatusManage.dayOrNight = dayOrNight;
	}

	
	/**
	 * 夜に行動したプレイヤーの総数を返します。
	 * @return
	 */
	public static int getSizeAction() {
		return sizeAction;
	}

	
	/**
	 * 夜に行動したプレイヤー総数を＋１します。
	 */
	public static void incrementSizeAction() {
		StatusManage.sizeAction += 1;
	}

}
