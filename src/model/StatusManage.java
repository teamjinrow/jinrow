package model;

import java.util.HashMap;

/**
 * ゲームステータスのデータ保存用クラスです。
 * ゲーム全体のステータスをキー：ユーザ名、バリュー：ユーザーステータスのマップで保持します。
 * こんなんでデータを保持できるか分かんないんで暫定クラスです。
 * @author tachibanayuuichirou
 */
public final class StatusManage {
	
	private static HashMap<String, HashMap<String, String>> statusMap;
	
	/**
	 * このクラスはインスタンス化を想定していません。
	 * そのため、Private修飾子を用いて外部からのインスタンス化を禁じています。
	 * また、本クラス内で呼び出した場合はアサーションエラーをスローします。
	 * 分からなければ、無視してもらって構いません。
	 */
	private StatusManage() {
		throw new AssertionError();
	}
	
	/**
	 * status初期化子を用いて、ゲームステータス管理マップのインスタンスを一度のみ生成します。
	 */
	static {
		 statusMap = new HashMap<String, HashMap<String, String>>();
	}
	
	/**
	 * ユーザーのステータス管理マップを作成し、ステータスマップとマッピングします。
	 * ステータス管理マップには、役職、死亡フラグ等のゲーム状況を保持します。
	 * @param userName
	 * @param roleName
	 */
	public static void addUser(String userName, String roleName) {
		
		HashMap<String, String> userStatus = new HashMap<String, String>();
		userStatus.put("role", roleName);
		userStatus.put("deadFlag", "生存"); 
		
		statusMap.put(userName, userStatus);
		
	}
	
	/**
	 * ユーザ名を元に、ユーザーステータスマップを返します。
	 */
	public static HashMap<String, String> getUserStatus(String userName){
		HashMap<String, String> userStatus = statusMap.get(userName);
		return userStatus;
	}
	
	/**
	 * ユーザ情報を保持するステータスマップを返します。
	 */
	public static HashMap<String, HashMap<String, String>> getGameStatus(){
		return statusMap;
	}
}
