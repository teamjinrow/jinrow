package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 役割を割り当てるためのクラスです。
 * シングルトンで使用します。
 * @author Y.tachibana
 *
 */
public enum AssignRole {
	INSTANCE;
	
	
	/**
	 * 役職を一時的に保持するリスト
	 */
	private static List<Role> list;
	
	
	/**
	 * 役職を割り当てたプレイヤー数を返します。
	 * 参加プレイヤー全てに役職を割り振ったかどうかを判別するために使用します。
	 */
	private static int countAssignedRole = 0;
	
	
	/**
	 * インスタンス化と同時に役職の配列をリストで取得
	 */
	static {
		list = new LinkedList<Role>(Arrays.asList(StatusManage.getArrRole()));
	}
	
	
	/**
	 * 役割を割りあてるメソッドです。
	 * 役割を保持する配列から無作為に役割を１つ取得し、その結果を返します。
	 */
	public static void assignRole(Player player) {
		
		synchronized (StatusManage.getPlayerList()) {
			Collections.shuffle(list);
			Role assignedRole = list.remove(0);
			player.setRole(assignedRole);
			addCountAssignedRole();
			
			//　プレイヤー全員の役職が決まるまで待機
			while (StatusManage.getArrRole().length != countAssignedRole) 
				try {
					StatusManage.getPlayerList().wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			StatusManage.getPlayerList().notifyAll();
					
		}

	}

	
	/**
	 * プレイヤーに割り当てらてた役職数を＋１します。
	 */
	public static void addCountAssignedRole() {
		AssignRole.countAssignedRole += 1;
	}

}
