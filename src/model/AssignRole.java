package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 役割を割り当てるためのクラスです。
 * シングルトンで使用します。
 * @author Y.tachibana
 *
 */
public enum AssignRole {
	INSTANCE;
	
	/**
	 * デフォルトの役職配列
	 */
	private static Role[] arrRole  = {
			new Villager(),
			new Villager(),
			new Villager(),
			new Werewolf(),
			new Werewolf(),
	}; 
	
	
	/**
	 * ゲームプレイ時の役職をまとめたリスト
	 */
	private static LinkedList<Role> roleList;
	
	
	/**
	 * ゲームプレイ時点の役職リストのサイズ（すなわちプレイヤーの参加人数）
	 */
	private static int sizeRole;
	
	
	/**
	 * 役職を割り当てたプレイヤー数を返します。
	 * 参加プレイヤー全てに役職を割り振ったかどうかを判別するために使用します。
	 */
	private static int countAssignedRole = 0;
	
	
	/**
	 * インスタンス化と同時に役職の配列をリストで取得
	 */
	static {
		roleList = new LinkedList<Role>(Arrays.asList(arrRole));
		sizeRole = roleList.size(); 
	}
	
	
	/**
	 * 役割を割りあてるメソッドです。
	 * 役割を保持する配列から無作為に役割を１つ取得し、その結果を返します。
	 */
	public static void assignRole(Player player) {
		
		synchronized (roleList) {
			Collections.shuffle(roleList);
			Role assignedRole = roleList.remove(0);
			player.setRole(assignedRole);
			countAssignedRole += 1;
			
			//　プレイヤー全員の役職が決まるまで待機
			while (sizeRole != countAssignedRole) 
				try {
					roleList.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			roleList.notifyAll();
					
		}

	}

	
	/**
	 * 役職リストのゲッター
	 * @return roleList
	 */
	public static LinkedList<Role> getRolelist() {
		return roleList;
	}
	
	/**
	 * 役職リストを設定します。
	 * リストを初期化した上で、新たな役職をリストに設定します。
	 * @param sizeVillager
	 * @param sizeWerewolf
	 * @return roleList
	 */
	public static LinkedList<Role> setRoleList(int sizeVillager, int sizeWerewolf) {
		//役職リストを初期化
		roleList.clear();
		countAssignedRole = 0;
		addVillger(sizeVillager);
		addWerewolf(sizeWerewolf);
		sizeRole = roleList.size();
		return roleList;
	}
	
	
	/**
	 * 役職リストに村人を追加します。
	 */
	private static void addVillger(int sizeVillager) {
		for (int i = 0;i < sizeVillager;i++) {
			roleList.add(new Villager());
		}
	}
	
	
	/**
	 * 役職リストに人狼を追加します。
	 */
	private static void addWerewolf(int sizeWerewolf) {
		for (int i = 0;i < sizeWerewolf;i++) {
			roleList.add(new Werewolf());
		}
	}
	
	
	/**
	 * 役職リストの人狼の数を返します
	 */
	public static int getSizeVillager() {
		int sizeVillager = 0;
		for (Role role: roleList)
			if (role.getRoleName().equals("Villager"))
				sizeVillager ++;
			
		return sizeVillager;
		
	}
	
	
	/**
	 * 役職リストの人狼の数を返します
	 */
	public static int getSizeWerewolf() {
		int sizeWerewolf = 0;
		for (Role role: roleList)
			if (role.getRoleName().equals("Werewolf"))
				sizeWerewolf ++;
			
		return sizeWerewolf;
		
	}
	
	
}
