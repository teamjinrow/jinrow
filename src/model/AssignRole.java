package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import model.Role.RoleName;

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
	
	private static CyclicBarrier barrier;
	
	/**
	 * ゲームプレイ時の役職をまとめたリスト
	 */
	private static LinkedList<Role> roleList;
	
	
	/**
	 * ゲームプレイ時点の役職リストのサイズ（すなわちプレイヤーの参加人数）
	 */
	private static int sizeRole;
	
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
		
		// サイクリックバリアーのインスタンスを取得
		getCyclicBarrier();
		try {
			Collections.shuffle(roleList);
			Role assignedRole = roleList.remove(0);
			player.setRole(assignedRole);
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
	 * 役職リストの村人の数を返します
	 */
	public static int getSizeVillager() {
		int sizeVillager = 0;
		for (Role role: roleList)
			if (role.getRoleName() == RoleName.VILLAGER)
				sizeVillager ++;
			
		return sizeVillager;
		
	}
	
	
	/**
	 * 役職リストの人狼の数を返します
	 */
	public static int getSizeWerewolf() {
		int sizeWerewolf = 0;
		for (Role role: roleList)
			if (role.getRoleName() == RoleName.WEREWOLF)
				sizeWerewolf ++;
			
		return sizeWerewolf;
		
	}
	
	
	/**
	 * このクラスのフィールドであるサイクリックバリヤーのインスタンスを取得します。
	 * フィールドに値がnullの場合は、sizeRoleの数値分のサイクリックバリヤーを作成しそのインスタンスを変えします。
	 * @return
	 */
	private static synchronized CyclicBarrier getCyclicBarrier() {
		if (barrier == null) {
			barrier = new CyclicBarrier(sizeRole);
		}
		return barrier;
	}
	
	
}
