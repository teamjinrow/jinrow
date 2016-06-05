package model;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import model.Player.PlayerStatus;
import model.StatusManage.Phase;

/**
 * 投票処理を行う投票ロジッククラスです。
 * インスタンスを作成時に、生存プレイヤー分のバリヤーを生成します。
 * @author Y.tachibana
 *
 */
public final class VoteLogic {
	
	/**
	 * インスタンス生成時に、生存プレイヤー分のバリヤーを生成します。
	 * バリヤーがトリップした場合は、投票結果の処理、昼から夜への変更、投票結果のリセットを行います。
	 */
	private final CyclicBarrier barrier = new CyclicBarrier(StatusManage.sizeAlivePlayer(),
												new Runnable(){
													public void run() {
														// 投票結果を処理します
														voteResult();
														// 昼から夜に変更します
														StatusManage.setPhase(Phase.夜);
														// 投票結果の初期化
														StatusManage.resetVotesCastWithAllPlayer();
														
													}
												});
	
	
	/**
	 * 人狼対象だと疑われているプレイヤーに対して投票を行います。
	 * 投票の後、生存しているプレイヤーの投票を待ちます。
	 * 生存しているプレイヤーが全員投票を終えたタイミングで処理を再開します。
	 * 
	 */
	public void vote(Player votedPlayer) {
	
		votedPlayer.incrementVotesCast();
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 *　各プレイヤーの投票結果を集計し、一番票の多いプレイヤーの死亡フラグをONにします。
	 *　戻り値として、処刑されたプレイヤーのインスタンスを返します。
	 * [ToDo]投票数が同じ場合には再投票が実施されるように修正
	 * @return　executedPlayer
	 */
	public Player voteResult(){
		
		Player executedPlayer = null;
		int maxVotesCast = 0;
		ArrayList<Player> playerList = StatusManage.getPlayerList();
		for (Player player : playerList) 
			if (player.getPlayerStatus() == PlayerStatus.ALIVE && maxVotesCast < player.getVotesCast()) {
				maxVotesCast = player.getVotesCast();
				executedPlayer = player;
			} else 
			if (player.getPlayerStatus() == PlayerStatus.ALIVE && maxVotesCast == player.getVotesCast()) {
				
			}
		
		executedPlayer.setDead();;
		StatusManage.setMessage(executedPlayer.getPlayerName() + "さんが処刑されました。");
		
		return executedPlayer;
		
	}

	
 }
 