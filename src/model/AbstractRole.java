package model;

/**
 * 役職抽象クラスです。
 * 新規役職を追加する場合は、このクラスを継承してください。
 * @author Y.tachibana
 *
 */
abstract class AbstractRole implements Role{
	
	/**
	 * 人狼対象だと疑われているプレイヤーに対して投票を行います。
	 * 投票の後、生存しているプレイヤーの投票を待ちます。
	 * 生存しているプレイヤーが全員投票を終えたタイミングで処理を再開します。
	 * 投票を最後に行ったプレイヤーが投票結果メソッドを呼び出し、投票処理、及び投票数の初期化、昼から夜への切り替えを行います。
	 * 複数のプレイヤーが同時に投票しても整合性を保てるように同期処理を行っています。
	 * 
	 */
	public void vote(Player votedPlayer) {
		
		int sizeAlivePlayer = StatusManage.sizeAlivePlayer();
		synchronized(StatusManage.getPlayerList()) {
			
			votedPlayer.incrementVotesCast();
			StatusManage.incrementSizeVote();
			
			// 投票が最後のプレイヤーは、投票結果の処理等の各種メソッドを呼び出します。
			if (StatusManage.getSizeVote() == sizeAlivePlayer) {
				// 投票結果を処理します
				StatusManage.voteResult();
				// 昼から夜に変更します
				StatusManage.setDayOrNight(true);
			}
			
			//　プレイヤー全員が投票するまで待機
			while(StatusManage.getSizeVote() != sizeAlivePlayer) {
				try {
					StatusManage.getPlayerList().wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			StatusManage.getPlayerList().notifyAll();
			
		}
		// 投票結果をリセット
		StatusManage.resetTargetByWolfWithAllPlayer();
		
	};
	
	
	/**
	 * 夜のアクションを行います。
	 * アクションの後、生存しているプレイヤーのアクションを待ちます。
	 * 生存しているプレイヤーが全員アクションを終えたタイミングで処理を再開します。
	 * アクションを最後に行ったプレイヤーがアクション結果メソッドを呼び出し、アクション処理、夜から昼への切り替え、勝利判定を行います。
	 * 複数のプレイヤーが同時に投票しても整合性を保てるように同期処理を行っています。
	 * 
	 */
	public void synchAction(Player player, Player targetPlayer) {
		
		int sizeAlivePlayer = StatusManage.sizeAlivePlayer();
		synchronized(StatusManage.getPlayerList()) {
			player.getRole().act(targetPlayer);
			StatusManage.incrementSizeAction();
			
			// 行動が最後のプレイヤーは、アクション結果の処理等の各種メソッドを呼び出します。
			if (StatusManage.getSizeAction() == sizeAlivePlayer) {
				// 投票結果を処理します
				StatusManage.actionResult();
				// 夜から昼に変更します
				StatusManage.setDayOrNight(false);
				//　ターンを進めます
				StatusManage.incrementTurn();
				// 勝利判定を行います
				StatusManage.gameCheck();
			}
			
			//　プレイヤー全員が投票するまで待機
			while(StatusManage.getSizeAction() != sizeAlivePlayer) {
				try {
					StatusManage.getPlayerList().wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			StatusManage.getPlayerList().notifyAll();
			
		}
		// 投票結果をリセット
		StatusManage.resetVotesCastWithAllPlayer();
	};
	
}
