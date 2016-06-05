package model;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import model.Player.PlayerStatus;
import model.StatusManage.Phase;

/**
 * 行動処理を行う行動ロジッククラスです。
 * インスタンスを作成時に、生存プレイヤー分のバリヤーを生成します。
 * @author Y.tachibana
 *
 */
public class ActionLogic {

	/**
	 * インスタンス生成時に、生存プレイヤー分のバリヤーを生成します。
	 * バリヤーがトリップした場合は、行動結果の処理、夜から昼への変更、投票結果のリセットを行います。
	 */
	private final CyclicBarrier barrier = new CyclicBarrier(StatusManage.sizeAlivePlayer(),
												new Runnable(){
													public void run() {
														// 投票結果を処理します
														actionResult();
														// 夜から昼に変更します
														StatusManage.setPhase(Phase.昼);
														//　ターンを進めます
														StatusManage.incrementTurn();
														// 勝利判定を行います
														StatusManage.gameCheck();
														// 行動結果の初期化
														StatusManage.resetTargetByWolfWithAllPlayer();
													}
												});
	
	
	/**
	 * 各役職に応じた行動を対象プレイヤーに対して行います。
	 * 投票の後、生存しているプレイヤーの投票を待ちます。
	 * 生存しているプレイヤーが全員投票を終えたタイミングで処理を再開します。
	 * 複数のプレイヤーが同時に投票しても整合性を保てるように同期処理を行っています。
	 * 
	 */
	public void action(Player player, Player targetPlayer) {
	
		player.getRole().act(targetPlayer);
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 *　各プレイヤーの投票結果を集計し、一番票の多いプレイヤーの死亡フラグをONにします。
	 *　戻り値として、殺害されたプレイヤーのインスタンスを返します。
	 * @returnPlayer
	 */
	public static Player actionResult(){
		
		Player killedPlayer = null;
		int maxVotesCast = 0;
		ArrayList<Player> playerList = StatusManage.getPlayerList();
		for (Player player : playerList) 
			if (player.getPlayerStatus() == PlayerStatus.ALIVE && maxVotesCast < player.getTargetByWerewolf()) {
				maxVotesCast = player.getVotesCast();
				killedPlayer = player;
			} else 
			if (player.getPlayerStatus() == PlayerStatus.ALIVE && maxVotesCast == player.getTargetByWerewolf()) {
				
			}
		
		if (killedPlayer != null) { 
			killedPlayer.setDead();
			StatusManage.setMessage(killedPlayer.getPlayerName() + "さんが殺害されました。");
		} else {
			StatusManage.setMessage("何事もなく朝を迎えました。");
		}
		
		return killedPlayer;
		
	}

	
}
