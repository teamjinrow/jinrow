package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 役割を割り当てるためのクラスです。
 * 
 * @author tachibanayuuichirou
 *
 */
public final class Role {
	
	/**
	 * とりあえず３人プレイ用の配列用意
	 */
	private String[] arrRole  = {
			"市民",
			"市民",
			"人狼"
	}; 
	
	/**
	 * 役割を割りあてるメソッドです。
	 * 役割を保持する配列から無作為に役割を１つ取得し、その結果を返します。
	 * [todo]一人は人狼に選ばれないといけないので、未完成。手抜きさーせん。
	 */
	public String assignRole() {
		
		List<String> list = Arrays.asList(arrRole);
		Collections.shuffle(list);
		arrRole = (String[])list.toArray(new String[0]);

		return arrRole[0];
	}

}
